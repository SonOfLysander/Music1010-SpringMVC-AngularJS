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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/music.css"/>">
</head>
<body>
	<div ng-controller="qaCtrl">
		<div class='search-block'>
			<input type="text" ng-model="searchText" />
		</div>
		<div class='qa-block'>
			<div
				ng-repeat="(question, answers) in filterQuestions(questions, searchText)">
				<div class='question'>{{question}}</div>
				<div class='answer' ng-repeat="answer in answers">{{answer}}</div>
			</div>
		</div>

	</div>
</body>
</html>
