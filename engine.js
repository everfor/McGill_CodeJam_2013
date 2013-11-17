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
			//v
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

function obj(day, rad, radVar,  hum, humVar,
 temp,  tempVar, wind, windVar, pow, powVar){
{
  this.day = day,
  this.rad = rad,
  this.radVar = radVar,
  this.hum = hum,
  this.humVar = humVar,
  this.temp = temp,
  this.tempVar = tempVar,
  this.wind = wind,
  this.windVar = windVar,
  this.pow = pow,
  this.powVar = powVar
  }
}
exports.loadReadings("data_set.csv", transform);
function transform(datasetReadings){
  var summaries = new Array();
  for(var i = 0; i < datasetReadings.length - (24 * 4); i ++){
   summaries.push(summary(datasetReadings, i));
   console.log(summaries[i]);
  }  return summaries;
}

summary = function (datasetReadings, i) {
    var day = 0;
    var rad = 0.0;
    var hum = 0.0;
    var temp = 0.0;
    var wind = 0.0;
    var pow = 0.0;
    for(var j = 0; j < 24; j++){
       if(j < 8){
          pow += datasetReadings[i + j].power;
       }
       rad += datasetReadings[i + j].radiation;
       hum += datasetReadings[i + j].humidity;
       temp += datasetReadings[i + j].temperature;
       wind += datasetReadings[i + j].windSpeed;
    }
    pow /= 8;
    rad /= 24;
    hum /= 24;
    temp /= 24;
    wind /= 24;

    var radVar = 0.0;
    var humVar = 0.0;
    var tempVar = 0.0;
    var windVar = 0.0;
    var powVar = 0.0;
    for(var j = 0; j < 24; j++){
       if(j < 8){
          powVar += Math.pow((datasetReadings[i + j].power - pow),2);
       }
       radVar += Math.pow((datasetReadings[i + j].radiation - rad),2);
       humVar += Math.pow((datasetReadings[i + j].humidity - hum),2);
       tempVar += Math.pow((datasetReadings[i + j].temperature - temp),2);
       windVar += Math.pow((datasetReadings[i + j].windSpeed - wind),2);
    }
    powVar /= 8;
    radVar /= 24;
    humVar /= 24;
    tempVar /= 24;
    windVar /= 24;
    return new obj(day, rad, radVar,  hum, humVar,
 temp,  tempVar, wind, windVar, pow, powVar);
}

