exports.fromCSV = function(data) {
	return {
		date: new Date(data[0]),
		radiation: new Number(data[1]),
		humidity: new Number(data[2]),
		temperature: new Number(data[3]),
		windSpeed: new Number(data[4]),
		power: new Number(data[5])
	};
}