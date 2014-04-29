<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html ng-app="musicApp">
<head>
<title>Music-1010-Study-Resource</title>
<script type="text/javascript"
	src="<c:url value="/resources/js/angular-1.2.16.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/angular-sanitize-1.2.16.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/music-1010.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/music.css"/>">
</head>
<body style='background-color: #FFF !important;'>
	<div ng-controller="qaCtrl">
		<div class='all-cards'>
			<span class='questionGroup'
				ng-repeat="(question, answers) in questions">
				<div class='card' ng-repeat="answer in answers"'>
					<div class='card-question' ng-bind-html='htmlToTrusted(question)'></div>
					<div class='card-answer'>
						<span class='card-answer' ng-bind-html='htmlToTrusted(answer)'></span>
					</div>
				</div>
			</span>
		</div>

	</div>
</body>
</html>
