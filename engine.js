var fs      = require('fs');
var csv		= require('csv');
var reading = require('./lib/reading');knn = require('alike');

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

//exports.sampleData = exports.getReadings(__dirname + "/data/datasetReadings.csv");
// we will be using the standard variation instead of the variance// sorry not sorry
function summaryObj(index, day, rad, radVar,  hum, humVar,
 temp,  tempVar, wind, windVar, pow, powVar){
{  this.index = index,
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
}function summaryObj2(index, day, radVar,  humVar, tempVar, windVar, powVar){{  this.index = index,  this.day = day,  this.radVar = radVar,  this.humVar = humVar,  this.tempVar = tempVar,  this.windVar = windVar,  this.powVar = powVar  }}
exports.loadReadings("data_set.csv", transform);var FRACTION_OF_TRAINING_SET = 0.5; // only use half of the setvar datasetReadings = [];var summary_data;var SORTED_SET_SIZE = 200;var dayWeight = 0.05;var radWeight = 0.15;var humWeight = 0.0;var tempWeight = 0.3;var windWeight = 0.0;var powWeight = 0.5; var radVarWeight = 0.15;var humVarWeight = 0.0;var tempVarWeight = 0.3;var windVarWeight = 0.0;var powVarWeight = 0.5; weights = {    k: SORTED_SET_SIZE,	weights: {	index: 0,	day: dayWeight,	rad: radWeight,	radVar: radVarWeight,	hum: humWeight,	humVar: humVarWeight,	temp: tempWeight,	tempVar: tempVarWeight,	wind: windWeight,	windVar: windVarWeight,	pow: powWeight,	powVar: powVarWeight	}}weights2 = {    k: 1,	weights: {	index: 0,	day: dayWeight,	radVar: radVarWeight,	humVar: humVarWeight,	tempVar: tempVarWeight,	windVar: windVarWeight,	powVar: powVarWeight	}}
function transform(datasetReadings){  //datasetReadings = datasetReadings;
  var summaries = new Array();
  for(var i = 0; i < Math.floor(datasetReadings.length * 0.5) - (24 * 4); i ++){
   summaries.push(summary(datasetReadings, i));
      if(i%96==0){	//console.log(summaries[i]);   }
  }  summary_data = summaries;  predict(datasetReadings);  return summaries;
}

summary = function (datasetReadings, i) {
    var day = datasetReadings[i].date.getDay(); //Math.floor(i/96) %7 + 1;
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
    windVar /= 24;		powVar = Math.sqrt(powVar);    radVar = Math.sqrt(radVar);    humVar = Math.sqrt(humVar);    tempVar = Math.sqrt(tempVar);    windVar = Math.sqrt(windVar);	
    return new summaryObj(i, day, rad, radVar,  hum, humVar,
 temp,  tempVar, wind, windVar, pow, powVar); 
}function predict(datasetReadings){  var curr = summary(datasetReadings, Math.floor(datasetReadings.length / 2) + 2);  sortBest(datasetReadings, curr);}
function sortBest(datasetReadings, currSet){  var output = knn(currSet, summary_data, weights);  console.log(output);  //return output;  //getBestest(datasetReadings, currSet, output); }  function getBestest(datasetReadings, currSet, contenders){    var radVar = 0.0;    var humVar = 0.0;    var tempVar = 0.0;    var windVar = 0.0;    var powVar = 0.0;    var summaries = new Array();	var k = currSet.index;    for (var x in contenders){		var i = x.index;		for(var j = 0; j < 24; j++){			if(j < 8){				powVar += Math.abs((datasetReadings[i + j].power - datasetReadings[k + j].power)/datasetReadings[k + j].power);			}			radVar += Math.abs((datasetReadings[i + j].radiation - datasetReadings[k + j].radiation)/datasetReadings[k + j].radiation);			humVar += Math.abs((datasetReadings[i + j].humidity - datasetReadings[k + j].humidity)/datasetReadings[k + j].humidity);			tempVar += Math.abs((datasetReadings[i + j].temperature - datasetReadings[k + j].temperature)/datasetReadings[k + j].temperature);			windVar += Math.abs((datasetReadings[i + j].windSpeed - datasetReadings[k + j].windSpeed)/datasetReadings[k + j].windSpeed);		}		powVar /= 8;		radVar /= 24;		humVar /= 24;		tempVar /= 24;		windVar /= 24;		summaries.push(new summaryObj2(i, x.day, radVar, humVar, tempVar, windVar, powVar));    } 	var perfect = new summaryObj(-1,-1,0.0,0.0,0.0,0.0,0.0);	var result = knn(perfect, summaries, weights2);	console.log(result);	return result; }
