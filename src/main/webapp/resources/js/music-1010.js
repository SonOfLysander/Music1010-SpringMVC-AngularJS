var app = angular.module('musicApp', []);
app.factory('qaFactory', function($http) {
	var factory = {};
	factory.getQaData = function() {
		return $http.get('http://localhost:8080/music/question-answers');
	};
	return factory;
});

app.controller('qaCtrl', function($scope, qaFactory) {
	function init() {
		qaFactory.getQaData().success(function(data) {
			$scope.questions = data;
		}).error(function(data) {
			console.log(data);
		});
	};
	$scope.filterQuestions = function(items, searchText){
		var result = {};
		angular.forEach(items, function(value, key){
			if ((String(key)).toLowerCase().indexOf(searchText.toLowerCase()) > -1){
				result[key] = value;
			}
		});
		return result;
	};
	init();
});