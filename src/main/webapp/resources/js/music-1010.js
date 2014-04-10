var app = angular.module('musicApp', []);
app.controller('qaCtrl', function($scope) {
	function init() {
		qaFactory.getQaData.success(function(data) {
			$scope.questions = data;
		}).error(function(data) {
			console.log(data);
		});
	}
	init();
});
app.factory('qaFactory', function($http) {
	var factory = {};
	factory.getQaData = function() {
		return $http.get('http://localhost:8080/music/question-answers');
	};
	return factory;
});