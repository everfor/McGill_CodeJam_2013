var ml 		= require('./machinelearning');
var vector	= require('./vector');

var means = [  0, 1, 513.5, 360, 11.5, 4872401723.124452, 0.5 ],	// Means of features
	ranges = [ 1, 2, 1027, 720, 23, 9744803446.248903, 1 ],	// Ranges of features
	weights = new vector.Vector(
		new Array(17093.862529391074,1842.9387766915077,612.2699466131371,-4017.5312398588926,5728.063077130966,-5204.672034748349,-668.8350487258651)),	// Weights from training
	cost = 2151388.5327213774;

// Parse readings into array of feature vectors
function parse(readings) {
	var features = [];
	for (var i = 0, n = readings.length; i < n; i++) {
		features.push(new vector.Vector(Array.apply(null, new Array(7)).map(Number.prototype.valueOf,1)));
	}

	for (var i = 0, n = readings.length; i < n; i++) {
        // Weekday = 1, weekend = -1
        features[i].vector[1] = (readings[i].date.getDay() > 0 && readings[i].date.getDay() < 6) ? 1 : -1;
        // Radiation - Linear
        features[i].vector[2] = parseFloat(readings[i].radiation);
        // Time on a specific day
        features[i].vector[3] = parseFloat(Math.abs(readings[i].date.getHours() * 60 + readings[i].date.getMinutes() - 720));
        // Temperature - Should be exponential though
        features[i].vector[4] = readings[i].temperature > 10 ? readings[i].temperature - 10 : 0;
        // Temperature Exp
        features[i].vector[5] = Math.exp(readings[i].temperature - 10);
        // Month - Christmas in December so power demand should be low
        features[i].vector[6] = ((readings[i].date.getMonth() == 11 && readings[i].date.getDate() >= 15)
                                || (readings[i].date.getMonth() == 0 && readings[i].date.getDate() <= 10)) ? 1 : 0;

		for (j = 1; j < features[0].len; j++) {
			features[i].updateSingleValue(j, parseFloat((features[i].vector[j] - means[j]) / ranges[j]));
		}
	}

	return features;
}

// Give the prediction
function predict(features) {
	predictions = [];
	for (var i = 0, n = features.length; i < n; i++) {
		predictions.push(ml.predict(features[i], weights));
	}

	return predictions;
}

function higherConfidenceInterval(prediction) {
	return prediction + Math.sqrt(cost);
}

function lowerConfidenceInterval(prediction) {
	return prediction - Math.sqrt(cost);
}

exports.parse = parse;
exports.predict = predict;