var http	= require("http");
var util	= require("util");
var reading = require("./reading");

//Types of request
const RADIATION 	= 66094;
const HUMIDITY 		= 66095;
const TEMPERATURE 	= 66077;
const WIND_SPEED 	= 66096;
const POWER			= 50578;

//API key
const KEY = "A8E17F2F71CB8E5F8287934908244C36";

var completed = 0;
var self = this;

/* 	Type: which type of data to obtain from Pulse
	requestCallback: callback for when this request ends
*/
function getFromPulse(type, requestCallback) {	
	//This request gives us the information we want from one day in the past
	var baseRequest = "http://api.pulseenergy.com/pulse/1/points/%d/data.json?key=%s&interval=day";
	var parametrizedRequest = util.format(baseRequest, type, KEY);
	
	var req = http.request(parametrizedRequest, function(res) {
		var output = '';
		res.on('data', function(chunk) {
			output += chunk;
		});
		
		res.on('end', function() {
			var obj = JSON.parse(output);
			var last8Hours;
			
			//We get power 4 times per hour, but other data only once per hour
			if(type == POWER)
				last8Hours = obj.data.slice(obj.data.length - 32, obj.data.length);
			else
				last8Hours = obj.data.slice(obj.data.length - 8, obj.data.length);
				
			requestCallback(last8Hours);
		});
	});
	
	req.on('error', function(err) {
		throw err;
	});
	
	req.end();
}

/*
	entries: an object / associative array containing readings
	endCallback: function to call when all the requests to Pulse are completed
	type: The type of data obtained
	data: Array of pairs (date and the data itself)
*/
function populateEntries(entries, endCallback, type, data) {
	for(var i = 0 ; i < data.length ; i++) {
		//Check if the entry exists. If not, create it
		var date = data[i][0];
		var entry = entries[date];
		if(typeof entry === "undefined") {
			entries[date] = {};
			entry = entries[date];
			entry["date"] = date;
		}
		
		//Add the correct property to it
		entry[type] = data[i][1];
	}
	
	completed += 1;
	//Hey, all the callbacks are completed! Notify whomever it may concern.
	if(completed == 5) {
		//TODO Interpolate to fill in the blanks!!
		
		endCallback(entries);
	}
}

function interpolate(entries) {

}

function curry (fn, scope) {
    var scope = scope || self;
    var args = [];
    for (var i=2, len = arguments.length; i < len; ++i) {
        args.push(arguments[i]);
    };

    return function() {
		for (var i = 0, len = arguments.length; i < len; ++i) {
			args.push(arguments[i]);
		};
	    fn.apply(scope, args);
    };
}

// callback: called when all the data has been obtained
exports.getLatestPulseData = function(callback) {
	completed = 0;
	var latestValues = {};

	/* Curry the same function for different fields	
	Only the data parameter will change from function call to function call */
	var populateRadiation = curry(populateEntries, null, latestValues, callback, "radiation");
	var populateHumidity = curry(populateEntries, null, latestValues, callback, "humidity");
	var populateTemperature = curry(populateEntries, null, latestValues, callback, "temperature");
	var populateWindSpeed = curry(populateEntries, null, latestValues, callback, "windSpeed");
	var populatePower = curry(populateEntries, null, latestValues, callback, "power");
	
	getFromPulse(POWER, populatePower);
	getFromPulse(RADIATION, populateRadiation);
	getFromPulse(HUMIDITY, populateHumidity);
	getFromPulse(TEMPERATURE, populateTemperature);
	getFromPulse(WIND_SPEED, populateWindSpeed);
};