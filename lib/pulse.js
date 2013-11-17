var http	= require("http");
var util	= require("util");

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
function getFromPulse(type, requestCallback, future) {

	var extra = "";
	if(future == true) {
		extra = "&start=" + (new Date()).toISOString();
	}
	
	//This request gives us the information we want from one day in the past
	var baseRequest = "http://api.pulseenergy.com/pulse/1/points/%d/data.json?key=%s&interval=day" + extra;
	var parametrizedRequest = util.format(baseRequest, type, KEY);
	var req = http.request(parametrizedRequest, function(res) {
		var output = '';
		res.on('data', function(chunk) {
			output += chunk;
		});
		
		res.on('end', function() {
			var obj = JSON.parse(output);
			
			if(!future) {
				/* We get power 4 times per hour, but other data only once per hour. For some reason, the Pulse API gives 
				us null power readings for recent readings (sometimes up to 3). Hence, we take an extra hour to make sure.*/
				var last9Hours;
				if(type == POWER)
					last9Hours = obj.data.slice(obj.data.length - 36, obj.data.length);
				else
					last9Hours = obj.data.slice(obj.data.length - 9, obj.data.length);
				
				requestCallback(last9Hours, future);
			} else {
				var next17Hours;
				if(type == POWER)
					next17Hours = obj.data.slice(0, 68);
				else
					next17Hours = obj.data.slice(0, 17);
					
				requestCallback(next17Hours, future);
			}			
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
function populateEntries(entries, endCallback, type, data, future) {
	for(var i = 0 ; i < data.length ; i++) {
		//Check if the entry exists. If not, create it.
		var date = data[i][0];
		var entry = entries[date];
		if(typeof entry === "undefined") {
			entries[date] = {};
			entry = entries[date];
			entry["date"] = new Date(date);
			entry["date"].setHours(entry["date"].getHours() - 5);
		}
		
		//Add the correct property to it
		entry[type] = data[i][1];
	}
	
	completed += 1;
	//Hey, all the callbacks are completed! Notify whomever it may concern.
	if(completed == 5) {
		endCallback(interpolate(entries, future));
	}
}

function interpolate(entries, future) {
	var tidyEntries = tidyData(entries);
	//Skip the first entries which do not have weather readings (at most 3)
	var start = 0;
	while(typeof tidyEntries[start].radiation === "undefined")
		start++;
	
	//Interpolate between known values
	for(var i = start ; i < tidyEntries.length - 4 + start - 1; i += 4) {
		
		var next = i + 4;
		var stepRad = (tidyEntries[next].radiation - tidyEntries[i].radiation) / 4;
		tidyEntries[i + 1].radiation = tidyEntries[i].radiation + stepRad;
		tidyEntries[i + 2].radiation = tidyEntries[i + 1].radiation + stepRad;
		tidyEntries[i + 3].radiation = tidyEntries[i + 2].radiation + stepRad;
		
		var stepHum = (tidyEntries[next].humidity - tidyEntries[i].humidity) / 4;
		tidyEntries[i + 1].humidity = tidyEntries[i].humidity + stepHum;
		tidyEntries[i + 2].humidity = tidyEntries[i + 1].humidity + stepHum;
		tidyEntries[i + 3].humidity = tidyEntries[i + 2].humidity + stepHum;
		
		var stepTemp = (tidyEntries[next].temperature - tidyEntries[i].temperature) / 4;
		tidyEntries[i + 1].temperature = tidyEntries[i].temperature + stepTemp;
		tidyEntries[i + 2].temperature = tidyEntries[i + 1].temperature + stepTemp;
		tidyEntries[i + 3].temperature = tidyEntries[i + 2].temperature + stepTemp;
		
		var stepWind = (tidyEntries[next].windSpeed - tidyEntries[i].windSpeed) / 4;
		tidyEntries[i + 1].windSpeed = tidyEntries[i].windSpeed + stepWind;
		tidyEntries[i + 2].windSpeed = tidyEntries[i + 1].windSpeed + stepWind;
		tidyEntries[i + 3].windSpeed = tidyEntries[i + 2].windSpeed + stepWind;
	}
	
	//For outlying values which cannot be interpolated, we extend from the closest values
	var startDiffRad = tidyEntries[start + 1].radiation - tidyEntries[start].radiation;
	var startDiffHum = tidyEntries[start + 1].humidity - tidyEntries[start].humidity;
	var startDiffTemp = tidyEntries[start + 1].temperature - tidyEntries[start].temperature;
	var startDiffWind = tidyEntries[start + 1].windSpeed - tidyEntries[start].windSpeed;
	for(var i = 0 ; i < start ; i++) {
		tidyEntries[i].radiation = tidyEntries[start].radiation - (start - i) * startDiffRad;
		tidyEntries[i].humidity = tidyEntries[start].humidity - (start - i) * startDiffHum;
		tidyEntries[i].temperature = tidyEntries[start].temperature - (start - i) * startDiffTemp;
		tidyEntries[i].windSpeed = tidyEntries[start].windSpeed - (start - i) * startDiffWind;
	}
	
	var skew = 0;
	if(future)
		skew = 1;
	
	var end = tidyEntries.length - 4 + start - skew; //Last index with data everywhere
	var endDiffRad = tidyEntries[end].radiation - tidyEntries[end - 1].radiation;
	var endDiffHum = tidyEntries[end].humidity - tidyEntries[end - 1].humidity;
	var endDiffTemp = tidyEntries[end].temperature - tidyEntries[end - 1].temperature;
	var endDiffWind = tidyEntries[end].windSpeed - tidyEntries[end - 1].windSpeed;
	for(var i = 1 ; i < 4 - start ; i++) {
		tidyEntries[end + i].radiation = tidyEntries[end].radiation + i * endDiffRad;
		tidyEntries[end + i].humidity = tidyEntries[end].humidity + i * endDiffHum;
		tidyEntries[end + i].temperature = tidyEntries[end].temperature + i * endDiffTemp;
		tidyEntries[end + i].windSpeed = tidyEntries[end].windSpeed + i * endDiffWind;
	}
	
	return tidyEntries;
}

function tidyData(entries) {
	//Take the reading objects into a non-associative array
	var unassociatedEntries = new Array(entries.length);
	var i = 0;
	for(var key in entries) {
		unassociatedEntries[i] = entries[key];
		i++;
	}
	
	//Sort in ascending order according to the date
	unassociatedEntries.sort(function(a, b) {
		var aa = a.date.valueOf();
		var bb = b.date.valueOf();
		return aa < bb ? -1 : (aa > bb ? 1 : 0);
	});
	
	//Check the last four entries, to verify if we have to drop null power reading entries
	var dropCount = 0;
	for(var i = unassociatedEntries.length - 1 ; i > unassociatedEntries.length - 4 ; i--) {
		if(!(unassociatedEntries[i].power))
			dropCount++;
	}
	
	return unassociatedEntries.slice(4 - dropCount, unassociatedEntries.length - dropCount);
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
exports.getPulseData = function(future, callback) {
	completed = 0;
	var latestValues = {};

	/* Curry the same function for different fields	
	Only the data parameter will change from function call to function call */
	var populateRadiation = curry(populateEntries, null, latestValues, callback, "radiation");
	var populateHumidity = curry(populateEntries, null, latestValues, callback, "humidity");
	var populateTemperature = curry(populateEntries, null, latestValues, callback, "temperature");
	var populateWindSpeed = curry(populateEntries, null, latestValues, callback, "windSpeed");
	var populatePower = curry(populateEntries, null, latestValues, callback, "power");
	
	getFromPulse(POWER, populatePower, future);
	getFromPulse(RADIATION, populateRadiation, future);
	getFromPulse(HUMIDITY, populateHumidity, future);
	getFromPulse(TEMPERATURE, populateTemperature, future);
	getFromPulse(WIND_SPEED, populateWindSpeed, future);
};