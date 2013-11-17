// Vector class
Vector = function(vectorArray) {
	var self = this;

	self.vector = vectorArray;
	self.len = vectorArray === null ? 0 : vectorArray.length;

	// Update certain components of the vector
	self.update = function(indexes, values) {
		if (indexes.length != values.length) {
			return false;
		}

		for (var i = 0, n = indexes.length; i < n; i++) {
			if (indexes[i] < self.len) {
				self.vector[indexes[i]] = values[i];
			}
		}
	};

	self.updateSingleValue = function(index, value) {
		if (index < self.len) {
			self.vector[index] = value;
		}
	}

	// Replace the current vector with a new one
	self.replace = function(newVector) {
		self.vector = newVector;
		self.len = newVector === null ? 0 : newVector.length;
	};

	// Add another vector and store it into self
	self.addition = function(another) {
		if (self.len != another.len) {
			return;
		}

		for (var i = 0; i < self.len; i++) {
			self.updateSingleValue(i, parseFloat(self.vector[i]) + parseFloat(another.vector[i]));
		}
	};

	// Add another vector and return it instead of storing
	self.addReturn = function(another) {
		sum = [];

		if (self.len != another.len) {
			return null;
		}

		for (var i = 0; i < self.len; i++) {
			sum.push(parseFloat(self.vector[i]) + parseFloat(another.vector[i]));
		}

		return sum;
	};

	// Return the inner products
	self.innerProductWith = function(another) {
		if (self.len != another.len) {
			return NaN;
		}

		product = 0;
		for (var i = 0; i < self.len; i++) {
			product += self.vector[i] * another.vector[i];
		}

		return product;
	};

	// Multiply with another vector component by component and store
	self.multiplyByComponent = function(another) {
		if (self.len != another.len) {
			return;
		}

		for (var i = 0; i < self.len; i++) {
			self.updateSingleValue(i, self.vector[i] * another.vector[i]);
		}
	};

	// Take the square of each component
	self.square = function() {
		self.multiplyByComponent(self);
	};

	// Scalar product
	self.scale = function(scalar) {
		for (var i = 0; i < self.len; i++) {
			self.updateSingleValue(i, self.vector[i] * scalar);
		}
	};

	// Copy a vector
	self.clone = function() {
		var clone = new Vector(self.vector.slice(0));

		return clone;
	}
}

exports.Vector = Vector;