<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html ng-app="musicApp">
<head>
<title>Music-1010-Study-Resource</title>
<script type="text/javascript"
	src="<c:url value="/resources/js/angular-1.2.16.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/music-1010.js"/>"></script>
</head>
<body>
	<div ng-controller="qaCtrl">
		Search: <input type="text" ng-model="searchText" />
		{{searchText}}
		<div ng-repeat="(question, answers) in filterQuestions(questions, searchText)">
			<div>{{question}}</div>
			<div ng-repeat="answer in answers">{{answer}}</div>
		</div>

	</div>
</body>
</html>
