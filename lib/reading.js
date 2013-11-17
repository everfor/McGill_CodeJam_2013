exports.fromCSV = function(data) {
	return {
		date: new Date(data[0]),
		radiation: new Number(data[1]) + 0,
		humidity: new Number(data[2]) + 0,
		temperature: new Number(data[3]) + 0,
		windSpeed: new Number(data[4]) + 0,
		power: new Number(data[5]) + 0
	};
}