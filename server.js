#!/bin/env node
//  OpenShift Node application
var express 	= require('express');
var fs      	= require('fs');

//CSV and Pulse Energy interfacing
var analysis	= require('./lib/analysis');
var reading 	= require('./lib/reading');
var pulse		= require('./lib/pulse');

// Machine Learning
var vector      = require('./lib/vector');
var ml          = require('./lib/machinelearning');


/**
 *  Define the sample application.
 */
var ForeCaster = function() {

    //  Scope.
    var self = this;


    /*  ================================================================  */
    /*  Helper functions.                                                 */
    /*  ================================================================  */

    /**
     *  Set up server IP address and port # using env variables/defaults.
     */
    self.setupVariables = function() {
        //  Set the environment variables we need.
        self.ipaddress = process.env.OPENSHIFT_NODEJS_IP;
        self.port      = process.env.OPENSHIFT_NODEJS_PORT || 8080;

        if (typeof self.ipaddress === "undefined") {
            //  Log errors on OpenShift but continue w/ 127.0.0.1 - this
            //  allows us to run/test the app locally.
            console.warn('No OPENSHIFT_NODEJS_IP var, using 127.0.0.1');
            self.ipaddress = "127.0.0.1";
        };
    };


    /**
     *  Populate the cache.
     */
    self.populateCache = function() {
        if (typeof self.zcache === "undefined") {
            self.zcache = { 'index': '' };
        }

        //  Local cache for static content.
        self.zcache['index'] = fs.readFileSync('./templates/index.html');
		self.zcache['highcharts'] = fs.readFileSync('./templates/js/highcharts.js');
		self.zcache['exporting'] = fs.readFileSync('./templates/js/modules/exporting.js');
    };


    /**
     *  Retrieve entry (content) from cache.
     *  @param {string} key  Key identifying content to retrieve from cache.
     */
    self.cache_get = function(key) { 
        return self.zcache[key]; 
    };


    /**
     *  terminator === the termination handler
     *  Terminate server on receipt of the specified signal.
     *  @param {string} sig  Signal to terminate on.
     */
    self.terminator = function(sig){
        if (typeof sig === "string") {
           console.log('%s: Received %s - terminating forecaster ...',
                       Date(Date.now()), sig);
           process.exit(1);
        }
        console.log('%s: Node server stopped.', Date(Date.now()) );
    };


    /**
     *  Setup termination handlers (for exit and a list of signals).
     */
    self.setupTerminationHandlers = function(){
        //  Process on exit and signals.
        process.on('exit', function() { self.terminator(); });

        // Removed 'SIGPIPE' from the list - bugz 852598.
        ['SIGHUP', 'SIGINT', 'SIGQUIT', 'SIGILL', 'SIGTRAP', 'SIGABRT',
         'SIGBUS', 'SIGFPE', 'SIGUSR1', 'SIGSEGV', 'SIGUSR2', 'SIGTERM'
        ].forEach(function(element, index, array) {
            process.on(element, function() { self.terminator(element); });
        });
    };


    /*  ================================================================  */
    /*  App server functions (main app logic here).                       */
    /*  ================================================================  */

    /**
     *  Create the routing table entries + handlers for the application.
     */
    self.createRoutes = function() {
        self.get_routes = {};
        self.post_routes = {};

        self.get_routes['/mcgillrobotics'] = function(req, res) {
            var link = "http://mcgillrobotics.com/shared_media/Team.jpg";
            res.send("<html><body><img src='" + link + "'></body></html>");
        };

        self.get_routes['/'] = function(req, res) {
            res.setHeader('Content-Type', 'text/html');
            res.send(self.cache_get('index'));
        };
		
		self.get_routes['/js/highcharts.js'] = function(req, res) {
			res.setHeader('Content-Type', 'application/javascript');
            res.send(self.cache_get('highcharts'));
		}
		
		self.get_routes['/js/modules/exporting.js'] = function(req, res) {
			res.setHeader('Content-Type', 'application/javascript');
            res.send(self.cache_get('exporting'));
		}
		
		//Pulse API and live chart
		self.get_routes['/pulse'] = function(req, res) {
			pulse.getLatestPulseData(function(data) {
				res.json(data);
			});
		}
		
		self.get_routes['/live'] = function(req, res) {
			pulse.getLatestPulseData(function(data) {
				res.render('live.ejs', {initialPulse: JSON.stringify(data)});
			});
		}

        self.get_routes['/testml'] = function(req, res) {
            var features = [],
                weights = new vector.Vector(new Array(1, 1, 1)),
                m = 3,
                results = [1, 2, 3],
                alpha = 0.072,
                lambda = 0;

            for (var i = 0; i < 3; i++) {
                features.push(new vector.Vector([i + 1, i + 1, i + 1]));
            }

            var result = '';
            for (var j = 0; j < 10; j++) {
                result += weights.vector + '<br>';
                ml.train(m, features, weights, results, alpha, lambda);
            }

            result += weights.vector + '<br>';

            res.send("<html><body>" + result + "</body></html>");
        }

        // Handlers for post requests
        self.post_routes['/upload'] = function(req, res) {
            // TO DO
            // File directory is './uploads/input.csv'
            fs.readFile(req.files.file.path, function (err, data) {
                var newPath = __dirname + "/uploads/input.csv";
                fs.writeFile(newPath, data, function (err) {
                    
					analysis.loadReadings(newPath, function(readings) {
						var html = "<html><body>" + JSON.stringify(readings);
						html += "</body></html>";
						res.send(html);
					});
                });
            });
        };

        self.post_routes['/mltraining'] = function(req, res) {
            fs.readFile(req.files.file.path, function (err, data) {
                var newPath = __dirname + "/uploads/input.csv";
                fs.writeFile(newPath, data, function (err) {
                    
                    analysis.loadReadings(newPath, function(readings) {
                        var m = readings.length,
                            features = [],
                            weights = new vector.Vector(new Array(20694.954389081628,1815.3521425576662,3219.1408722994197,-3334.5343224799003,4298.639031144278,-174.78245361926216)),
                            results = [],
                            alpha = 0.3,
                            lambda = 0;
                        
                        for (var i = 0; i < m; i++) {
                            features.push(new vector.Vector(Array.apply(null, new Array(6)).map(Number.prototype.valueOf,1)));
                        }
                        for (var i = 0; i < m; i++) {
                            // Weekday = 1, weekend = -1
                            features[i].vector[1] = (readings[i].date.getDay() > 0 && readings[i].date.getDay() < 6) ? 1 : -1;
                            // Radiation - Linear
                            features[i].vector[2] = parseFloat(readings[i].radiation);
                            // Time on a specific day
                            features[i].vector[3] = parseFloat(Math.abs(readings[i].date.getHours() - 12));
                            // Temperature - Should be exponential though
                            features[i].vector[4] = parseFloat(readings[i].temperature);
                            // Month - Christmas in December so power demand should be low
                            features[i].vector[5] = readings[i].date.getMonth() == 11 ? 1 : 0;
                            results[i] = parseFloat(readings[i].power);
                        }

                        ml.normalize(features);

                        result = '';
                        for (var j = 0; j < m; j++) {
                            // ml.train(m, features, weights, results, alpha, lambda);
                            // console.log('training: ' + j + ' ' + weights.vector + ' cost: ' + ml.cost(features, weights, results));
                            // result += 'training: ' + j + ' ' + weights.vector + ' cost: ' + ml.cost(features, weights, results) + '\n';
                            result += ml.predict(features[j], weights) + '  ' + results[j] + '\n';
                        }

                        // result += weights.vector + '\n';

                        res.send(result);
                    });
                });
            });
        };
    };


    /**
     *  Initialize the server (express) and create the routes and register
     *  the handlers.
     */
    self.initializeServer = function() {
        self.createRoutes();
        self.app = express();
        
        // Use body parser to parse POST requests
        // http://stackoverflow.com/a/18278655/2551775
        self.app.use(express.bodyParser());

        //  Add handlers for the app (from the routes).
        for (var r in self.get_routes) {
            self.app.get(r, self.get_routes[r]);
        }

        for (var r in self.post_routes) {
            self.app.post(r, self.post_routes[r]);
        }
    };


    /**
     *  Initializes the sample application.
     */
    self.initialize = function() {
        self.setupVariables();
        self.populateCache();
        self.setupTerminationHandlers();

        // Create the express server and routes.
        self.initializeServer();
    };


    /**
     *  Start the server (starts up the sample application).
     */
    self.start = function() {
        //  Start the app on the specific interface (and port).
        self.app.listen(self.port, self.ipaddress, function() {
            console.log('%s: Node server started on %s:%d ...',
                        Date(Date.now() ), self.ipaddress, self.port);
        });
    };

};   /*  Sample Application.  */



/**
 *  main():  Main code.
 */
var foreCaster = new ForeCaster();
foreCaster.initialize();
foreCaster.start();
