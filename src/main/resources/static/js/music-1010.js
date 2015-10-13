var app = angular.module('musicApp', [ 'ngSanitize' ]);
app.factory('qaFactory', function($http) {
	var factory = {};
	factory.getQaData = function() {
		return $http.get('http://localhost:8081/music/qa');
	};
	return factory;
});

app.controller('qaCtrl', function($scope, qaFactory, $sce) {
	function init() {
		qaFactory.getQaData().success(function(data) {
			$scope.questions = data;
		}).error(function(data) {
			console.log(data);
		});
	}
	;
	$scope.filterQuestions = function(items, searchText) {
		var result = {};
		angular.forEach(items,
				function(value, key) {
					if ((String(key)).toLowerCase().indexOf(
							searchText.toLowerCase()) > -1
							|| (String(value)).toLowerCase().indexOf(
									searchText.toLowerCase()) > -1) {
						result[key] = value;
					}
				});
		return result;
	};
	$scope.htmlToTrusted = function(untrustedHtml) {
		return $sce.trustAsHtml(untrustedHtml);
	};
	init();
});