//TEST ON FULL SET RATHER THAN FIRST HALF
knn = require('alike');
var fs = require('fs');
var csv	= require('csv');
var reading = require('./lib/reading');
var analysis = require('./lib/analysis');
var self = this;

// we will be using the standard variation instead of the variance
// sorry not sorry
function summaryObj(index, day, ToD, rad, radVar,  hum, humVar,
 temp, tempVar, wind, windVar, power, powerVar){
  {
    this.index = index,
    this.day = day,
    this.ToD = ToD,
    this.rad = rad,
    this.radVar = radVar,
    this.hum = hum,
    this.humVar = humVar,
    this.temp = temp,
    this.tempVar = tempVar,
    this.wind = wind,
    this.windVar = windVar,
    this.power = power,
    this.powerVar = powerVar
  }
}

//format for determining best overall from the reduced set
function summaryObj2(index, day, ToD, radVar,  humVar, tempVar, windVar, powerVar) {
  {
    this.index = index,
    this.day = day,
    this.ToD = ToD
    this.radVar = radVar,
    this.humVar = humVar,
    this.tempVar = tempVar,
    this.windVar = windVar,
    this.powerVar = powerVar
  }
}



var FRACTION_OF_TRAINING_SET = 0.5; // only use half of the set

var sampleOffset = 100;
var delayHours = 24;
//variable coefficicents for weighting the sort
var SORTED_SET_SIZE = 200;
var dayWeight = 0;
var ToDWeight = 0;
var radWeight = 50;
var humWeight = 0;
var tempWeight = 100;
var windWeight = 0;
var powerWeight = 150;
var radVarWeight = 0;
var humVarWeight = 0;
var tempVarWeight = 10;
var windVarWeight = 0;
var powerVarWeight = 100;

//weighting for reducing history to smaller set
weights = {
  k: SORTED_SET_SIZE,
  weights: {
    index: 0,
    day: dayWeight,
    ToD: ToDWeight,
    rad: radWeight,
    radVar: radVarWeight,
    hum: humWeight,
    humVar: humVarWeight,
    temp: tempWeight,
    tempVar: tempVarWeight,
    wind: windWeight,
    windVar: windVarWeight,
    power: powerWeight,
    powerVar: powerVarWeight
    }
}

//weighting for choosing the single best match
weights2 = {
  k: 1,
  weights: {
    index: 0,
    day: 0,
    ToD: 0,
    radVar: radVarWeight,
    humVar: humVarWeight,
    tempVar: tempVarWeight,
    windVar: windVarWeight,
    powerVar: powerVarWeight
  }
}
//array is the JSON array of the input CSV
exports.compute = function(current, callback){
    analysis.loadReadings("data_set.csv", function(historical) {
		callback(predictFuture(historical, current));
	});
}

function predictFuture(historical, current){
    //transform historical data
    var historicalSummary = transformRawHistoryToSummaryList(historical);
	var currentSummary = transformCurrSetToSummary(current);
	
	var indexBest = sortBest(historical, current, historicalSummary, currentSummary);
	var slice = slicer(historical, indexBest);
	
	//real future dates
	for(var i = 32; i < 96; i++){
		current[i].power = slice[i - 32].power;
	}
	return current.slice(32,96);
}
function slicer(historical, indexBest) {
    return historical.slice(indexBest + 32, indexBest + 96);
}

//converts initial history into the style we compare
function transformRawHistoryToSummaryList(historical) {
    var summaries = new Array();
    for(var i = 0; i < Math.floor(historical.length/2 - (24 * 4)); i ++){
        summaries.push(summary(historical, i));
    }
    return summaries;
}

//gets average variables over a 24h period frmo start index
summary = function (historical, i) {
    var day = historical[i].date.getDay();
    var ToD = historical[i].date.getHours();
    var rad = 0.0;
    var hum = 0.0;
    var temp = 0.0;
    var wind = 0.0;
    var power = 0.0;
    for(var j = 0; j < 96; j++){
        if(j < 32){
            power += historical[i + j].power;
        }
        rad += historical[i + j].radiation;
        hum += historical[i + j].humidity;
        temp += historical[i + j].temperature;
        wind += historical[i + j].windSpeed;
    }
    power /= 32;
    rad /= 96;
    hum /= 96;
    temp /= 96;
    wind /= 96;

    var radVar = 0.0;
    var humVar = 0.0;
    var tempVar = 0.0;
    var windVar = 0.0;
    var powerVar = 0.0;
    for(var j = 0; j < 96; j++){
        if(j < 32){
            powerVar += Math.pow((historical[i + j].power - power),2);
        }
        radVar += Math.pow((historical[i + j].radiation - rad),2);
        humVar += Math.pow((historical[i + j].humidity - hum),2);
        tempVar += Math.pow((historical[i + j].temperature - temp),2);
        windVar += Math.pow((historical[i + j].windSpeed - wind),2);
    }
    powerVar /= 32;
    radVar /= 96;
    humVar /= 96;
    tempVar /= 96;
    windVar /= 96;
	
    powerVar = Math.sqrt(powerVar);
    radVar = Math.sqrt(radVar);
    humVar = Math.sqrt(humVar);
    tempVar = Math.sqrt(tempVar);
    windVar = Math.sqrt(windVar);

    return new summaryObj(i, day, ToD, rad, radVar,  hum, humVar, temp,
      tempVar, wind, windVar, power, powerVar);
 
}

//gets average variables over a 24h period frmo start index
transformCurrSetToSummary = function (currPreTransform) {
    var day = currPreTransform[0].date.getDay();
    var ToD = currPreTransform[0].date.getHours();
    var rad = 0.0;
    var hum = 0.0;
    var temp = 0.0;
    var wind = 0.0;
    var power = 0.0;
    for(var j = 0; j < 96; j++){
        if(j < 32){
            power += currPreTransform[j].power;
        }
        rad += currPreTransform[j].radiation;
        hum += currPreTransform[j].humidity;
        temp += currPreTransform[j].temperature;
        wind += currPreTransform[j].windSpeed;
    }
    power /= 32;
    rad /= 96;
    hum /= 96;
    temp /= 96;
    wind /= 96;

    var radVar = 0.0;
    var humVar = 0.0;
    var tempVar = 0.0;
    var windVar = 0.0;
    var powerVar = 0.0;
    for(var j = 0; j < 96; j++){
        if(j < 32){
            powerVar += Math.pow((currPreTransform[j].power - power),2);
        }
        radVar += Math.pow((currPreTransform[j].radiation - rad),2);
        humVar += Math.pow((currPreTransform[j].humidity - hum),2);
        tempVar += Math.pow((currPreTransform[j].temperature - temp),2);
        windVar += Math.pow((currPreTransform[j].windSpeed - wind),2);
    }
    powerVar /= 32;
    radVar /= 96;
    humVar /= 96;
    tempVar /= 96;
    windVar /= 96;
	
    powerVar = Math.sqrt(powerVar);
    radVar = Math.sqrt(radVar);
    humVar = Math.sqrt(humVar);
    tempVar = Math.sqrt(tempVar);
    windVar = Math.sqrt(windVar);

    return new summaryObj((-1), day, ToD, rad, radVar,  hum, humVar, temp,
      tempVar, wind, windVar, power, powerVar);
    
}

//sorts initial set into smaller set
function sortBest(historical, current, historicalSummary, currentSummary){
    var output = knn(currentSummary, historicalSummary, weights);
    return getBestest(historical, current, output);
}

//finds best match from smaller set 
function getBestest(historical, current, contenders){
    var radVar = 0.0;
    var humVar = 0.0;
    var tempVar = 0.0;
    var windVar = 0.0;
    var powerVar = 0.0;
    var summaries = new Array();
    var numContenders = contenders.length;
    for (var l = 0; l < numContenders; l ++){
        var i = contenders[l].index;
		for(var j = 0; j < 96; j++){
			if(j < 32){
				powerVar += Math.abs((historical[i + j].power - current[j].power)/current[j].power);
			}
			radVar += Math.abs((historical[i + j].radiation - current[j].radiation)/current[j].radiation);
			humVar += Math.abs((historical[i + j].humidity - current[j].humidity)/current[j].humidity);
			tempVar += Math.abs((historical[i + j].temperature - current[j].temperature)/current[j].temperature);
			windVar += Math.abs((historical[i + j].windSpeed - current[j].windSpeed)/current[j].windSpeed);
		}
	powerVar /= 32;
	radVar /= 96;
	humVar /= 96;
	tempVar /= 96;
	windVar /= 96;
	summaries.push(new summaryObj2(i, contenders[l].day, contenders[l].ToD, radVar, humVar, tempVar, windVar, powerVar));
    } 
    var perfect = new summaryObj2(-1,-1,-1,0.0,0.0,0.0,0.0,0.0);
    var result = knn(perfect, summaries, weights2);
    return result[0].index;
}
