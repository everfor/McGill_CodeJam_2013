//TODO: preprocess once, rather than everytime node is run
//TODO: input real current file to compare with history
//TODO: fiddle with weights to find good matches
//TODO: figure out how to do the fiddling over a large test set

knn = require('alike');
var fs = require('fs');
var csv        = require('csv');
var reading = require('./lib/reading');
var self = this;
var datasetReadings;

//CSV input into memory
exports.loadReadings = function(file, callback) {
        var fileContents = fs.readFileSync(file);
        csv().from.string(fileContents, {comment: '#'}).to.array( 
        function(data) { 
                datasetReadings = new Array(data.length - 1);
                for(var i = 1 ; i < data.length ; i++) {
                        datasetReadings[i - 1] = reading.fromCSV(data[i]);
                        //console.log(datasetReadings[i - 1].power);
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

exports.loadReadings("data_set.csv", transform);

var FRACTION_OF_TRAINING_SET = 0.5; // only use half of the set
var datasetReadings = [];
var summary_data;

var sampleOffset = 100;
var delayHours = 16;
//variable coefficicents for weighting the sort
var SORTED_SET_SIZE = 200;
var dayWeight = 0;
var ToDWeight = 0;
var radWeight = 0;
var humWeight = 0;
var tempWeight = 500;
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

//converts initial history into the style we compare
function transform() {
    var summaries = new Array();
    for(var i = 0; i < Math.floor(datasetReadings.length * 0.5) - (24 * 4); i ++){
        summaries.push(summary(i));
    }
    summary_data = summaries;
    predict();
    return summaries;
}

//gets average variables over a 24h period frmo start index
summary = function (i) {
    var day = datasetReadings[i].date.getDay();
    var ToD = datasetReadings[i].date.getHours();
    var rad = 0.0;
    var hum = 0.0;
    var temp = 0.0;
    var wind = 0.0;
    var power = 0.0;
    for(var j = 0; j < 96; j++){
        if(j < 32){
            power += datasetReadings[i + j].power;
        }
        rad += datasetReadings[i + j].radiation;
        hum += datasetReadings[i + j].humidity;
        temp += datasetReadings[i + j].temperature;
        wind += datasetReadings[i + j].windSpeed;
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
            powerVar += Math.pow((datasetReadings[i + j].power - power),2);
        }
        radVar += Math.pow((datasetReadings[i + j].radiation - rad),2);
        humVar += Math.pow((datasetReadings[i + j].humidity - hum),2);
        tempVar += Math.pow((datasetReadings[i + j].temperature - temp),2);
        windVar += Math.pow((datasetReadings[i + j].windSpeed - wind),2);
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

//wrapper function for finding the best match
function predict(){
    var curr = summary(Math.floor(datasetReadings.length / 2) + sampleOffset);
    console.log(datasetReadings[curr.index]);
    console.log(datasetReadings[curr.index + 4 * delayHours]);
    sortBest(curr, getBestest);
}

//sorts initial set into smaller set
function sortBest(currSet, callback){
    var output = knn(currSet, summary_data, weights);
    //console.log(output);
    callback(currSet, output);
}

//finds best match from smaller set 
function getBestest(currSet, contenders){
    var radVar = 0.0;
    var humVar = 0.0;
    var tempVar = 0.0;
    var windVar = 0.0;
    var powerVar = 0.0;
    var summaries = new Array();
    var k = currSet.index;
    var numContenders = contenders.length;
    for (var l = 0; l < numContenders; l ++){
        //console.log(contenders[l].index);
        var i = contenders[l].index;
        for(var j = 0; j < 96; j++){
            if(j < 32){
                powerVar += Math.abs((datasetReadings[i + j].power - datasetReadings[k + j].power)/datasetReadings[k + j].power);
            }
            radVar += Math.abs((datasetReadings[i + j].radiation - datasetReadings[k + j].radiation)/datasetReadings[k + j].radiation);
            humVar += Math.abs((datasetReadings[i + j].humidity - datasetReadings[k + j].humidity)/datasetReadings[k + j].humidity);
            tempVar += Math.abs((datasetReadings[i + j].temperature - datasetReadings[k + j].temperature)/datasetReadings[k + j].temperature);
            windVar += Math.abs((datasetReadings[i + j].windSpeed - datasetReadings[k + j].windSpeed)/datasetReadings[k + j].windSpeed);
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
    console.log(datasetReadings[result[0].index]);
    console.log(datasetReadings[result[0].index + 4 * delayHours]);
    return result[0];
}