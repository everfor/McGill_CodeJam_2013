var fs      = require('fs');
var csv		= require('csv');
var reading = require('./reading');

var self = this;

exports.loadReadings = function(file, callback) {
	var fileContents = fs.readFileSync(file);
	csv().from.string(fileContents, {comment: '#'}).to.array( 
	function(data) { 
		var datasetReadings = new Array(data.length - 1);
		for(var i = 1 ; i < data.length ; i++) {
			datasetReadings[i - 1] = reading.fromCSV(data[i]);
		}
		
		//Interpolate the weather data
		for(var i = 0 ; i < datasetReadings.length - 4; i += 4) {
			var next = i + 4;
			var stepRad = (datasetReadings[next].radiation - datasetReadings[i].radiation) / 4;
			datasetReadings[i + 1].radiation = datasetReadings[i].radiation + stepRad;
			datasetReadings[i + 2].radiation = datasetReadings[i + 1].radiation + stepRad;
			datasetReadings[i + 3].radiation = datasetReadings[i + 2].radiation + stepRad;
			
			var stepHum = (datasetReadings[next].humidity - datasetReadings[i].humidity) / 4;
			datasetReadings[i + 1].humidity = datasetReadings[i].humidity + stepHum;
			datasetReadings[i + 2].humidity = datasetReadings[i + 1].humidity + stepHum;
			datasetReadings[i + 3].humidity = datasetReadings[i + 2].humidity + stepHum;
			
			var stepTemp = (datasetReadings[next].temperature - datasetReadings[i].temperature) / 4;
			datasetReadings[i + 1].temperature = datasetReadings[i].temperature + stepTemp;
			datasetReadings[i + 2].temperature = datasetReadings[i + 1].temperature + stepTemp;
			datasetReadings[i + 3].temperature = datasetReadings[i + 2].temperature + stepTemp;
			
			var stepWind = (datasetReadings[next].windSpeed - datasetReadings[i].windSpeed) / 4;
			datasetReadings[i + 1].windSpeed = datasetReadings[i].windSpeed + stepWind;
			datasetReadings[i + 2].windSpeed = datasetReadings[i + 1].windSpeed + stepWind;
			datasetReadings[i + 3].windSpeed = datasetReadings[i + 2].windSpeed + stepWind;
		}
		
		/* For the last three values, we have nothing to interpolate with, so just repeat the last value
		*/
		var end = datasetReadings.length;
		datasetReadings[end - 3].radiation = datasetReadings[end - 4].radiation;
		datasetReadings[end - 2].radiation = datasetReadings[end - 4].radiation;
		datasetReadings[end - 1].radiation = datasetReadings[end - 4].radiation;
		
		datasetReadings[end - 3].humidity = datasetReadings[end - 4].humidity;
		datasetReadings[end - 2].humidity = datasetReadings[end - 4].humidity;
		datasetReadings[end - 1].humidity = datasetReadings[end - 4].humidity;
		
		datasetReadings[end - 3].temperature = datasetReadings[end - 4].temperature;
		datasetReadings[end - 2].temperature = datasetReadings[end - 4].temperature;
		datasetReadings[end - 1].temperature = datasetReadings[end - 4].temperature;
		
		datasetReadings[end - 3].windSpeed = datasetReadings[end - 4].windSpeed;
		datasetReadings[end - 2].windSpeed = datasetReadings[end - 4].windSpeed;
		datasetReadings[end - 1].windSpeed = datasetReadings[end - 4].windSpeed;
		
		callback(datasetReadings);
	});
};

//exports.sampleData = exports.getReadings(__dirname + "/data/data_set.csv");