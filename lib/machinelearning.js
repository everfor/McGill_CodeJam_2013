var vector = require('./vector');

function train(m, features, weights, results, alpha, lambda) {
	var grad = gradient(m, features, weights, results, lambda);
	grad.scale(parseFloat(0 - 1) * alpha);

	weights.addition(grad);
}

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

function predict(features, weights) {
	return features.innerProductWith(weights);
}

function cost(predictions, results) {
	if (predictions.length != results.length) {
		return NaN;
	}

	var cos = 0;
	for (var i = 0, n = predictions.length; i < n; i++) {
		cos += Math.pow(predictions[i] - results[i], 2);
	}

	cos /= (2 * predictions.length);

	return cos;
}

exports.train = train;