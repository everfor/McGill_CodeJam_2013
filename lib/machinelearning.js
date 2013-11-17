/*
*	Machine Learning Algorithm
*	Linear Regression - With Lasso Regularization
*/
var vector = require('./vector');

/*	Training functions - Gradient Descent
*	@param m:Number of Training Data sets
*	@param features: Array of vectors; each vector is a feature vector of a dataset
*	@param weights: Weights for features
*	@param results: Training Set Results
*	@param alpha: Step of Linear Regression
*	@param lambda: Parameter for Lasso Regularization
*/
function train(m, features, weights, results, alpha, lambda) {
	// Get gradient
	var grad = gradient(m, features, weights, results, lambda);

	// Train weights
	grad.scale(parseFloat(0 - 1) * alpha);
	weights.addition(grad);
}

/*	Find gradient of the cost function
*	@param m:Number of Training Data sets
*	@param features: Array of vectors; each vector is a feature vector of a dataset
*	@param weights: Weights for features
*	@param results: Training Set Results
*	@param lambda: Parameter for Lasso Regularization
*/
function gradient(m, features, weights, results, lambda) {
	var gradient = new vector.Vector(Array.apply(null, new Array(weights.len)).map(Number.prototype.valueOf,0)),
		w_copy = weights.clone();

	for (var i = 0; i < m; i++) {
		var f_copy = features[i].clone();

		// Caculate X_i . Theta - Y_i
		var error = predict(f_copy, weights) - results[i];

		// X_i = X_i * error
		f_copy.scale(error);
		gradient.addition(f_copy);
	}

	// Set theta_0 to 0
	w_copy.updateSingleValue(0, 0);
	w_copy.scale(lambda);

	gradient.addition(w_copy);

	gradient.scale(1 / parseFloat(m));

	return gradient;
}

/*	Get prediction
*	@param feature: Feature vector of a dataset
*	@param weights: Weights for features
*/
function predict(feature, weights) {
	if (feature.vector[5] > 0) {
		return feature.innerProductWith(weights) * (feature.innerProductWith(weights) > 14500 ? 1.6 / 1.7 : 1.7 / 1.6);
	}
	return feature.innerProductWith(weights);
}

/*	Calculate Cost Function
*	@param predictions: Predictions for Data Sets
*	@param results: Real Results for Data Sets 
*/
function cost(features, weights, results) {
	if (features.length != results.length) {
		return NaN;
	}

	var cos = 0;
	for (var i = 0, n = features.length; i < n; i++) {
		cos += Math.pow(predict(features[i], weights) - results[i], 2);
	}

	cos /= (2 * features.length);

	return cos;
}

function normalize(features) {
	var n = features[0].len,
		means = Array.apply(null, new Array(6)).map(Number.prototype.valueOf,0),
		ranges = Array.apply(null, new Array(6)).map(Number.prototype.valueOf,1);
	for (var i = 1; i < n; i++) {
		var max = 0,
			min = 999999999;
		for (var j = 0, m = features.length; j < m; j++) {
			if (features[j].vector[i] > max) {
				max = features[j].vector[i];
			} else if (features[j].vector[i] < min) {
				min = features[j].vector[i];
			}
		}

		var mean = (max - min) / 2;
		means.push(mean);
		ranges.push(mean * 2);

		for (var j = 0, m = features.length; j < m; j++) {
			features[j].updateSingleValue(i, parseFloat((features[j].vector[i] - mean) / parseFloat(max - min)));
		}
	}

	console.log(means);
	console.log(ranges);
}

exports.train = train;
exports.cost = cost;
exports.predict = predict;
exports.normalize = normalize;