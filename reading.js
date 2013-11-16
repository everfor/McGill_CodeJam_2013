exports.fromCSV = function(data) {
	return {
		getDate: function() { return data[0]; },
		getRadiation: function() { return data[1]; },
		getHumidity: function() { return data[2]; },
		getTemperature: function() { return data[3]; },
		getWindSpeed: function() { return data[4]; },
		getPower: function() { return data[5]; }
	};
}