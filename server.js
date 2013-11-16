#!/bin/env node
//  OpenShift Node application
var express = require('express');
var fs      = require('fs');
var csv 	= require('csv');
var reading = require('./reading');

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

        //  Local calche for static content.
        self.zcache['index'] = fs.readFileSync('./templates/index.html');
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

        // Handlers for get requests
        self.get_routes['/mcgillrobotics'] = function(req, res) {
            var link = "http://mcgillrobotics.com/shared_media/Team.jpg";
            res.send("<html><body><img src='" + link + "'></body></html>");
        };

        self.get_routes['/'] = function(req, res) {
            res.setHeader('Content-Type', 'text/html');
            res.send(self.cache_get('index'));
        };

        // Handlers for post requests
        self.post_routes['/upload'] = function(req, res) {
            // TO DO
            // File directory is './uploads/input.csv'
            fs.readFile(req.files.filedata.path, function (err, data) {
                var newPath = __dirname + "/uploads/input.csv";
                fs.writeFile(newPath, data, function (err) {
                    fs.readFile(newPath, function(err, data) {
						if(err) throw err;
						csv().from.string(data, {comment: '#'})
						.to.array( function(data){
							var str = "<html><body>";
							for(var i = 1 ; i < data.length ; i++) {
								var aReading = reading.fromCSV(data[i]);
								str += aReading.getRadiation() + "<br/>";
							}
							str += "</body></html>";
							res.send(str);
						});
					});
                });
            });
        }
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
