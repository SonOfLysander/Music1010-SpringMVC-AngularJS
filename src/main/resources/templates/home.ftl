<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html ng-app="musicApp">
<head>
    <title>Music-1010-Study-Resource</title>
    <script src="/js/angular-1.2.16.min.js"></script>
    <script src="/js/angular-sanitize-1.2.16.min.js"></script>
    <script src="/js/music-1010.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/music.css"/>
</head>
<body>
<div ng-controller="qaCtrl">
    <div class='search-block'>
        <input type="text" ng-model="searchText"/>
    </div>
    <div class='qa-block'>
        <div ng-repeat="(question, answers) in filterQuestions(questions, searchText)">
            <div class='question' ng-bind-html='htmlToTrusted(question)'></div>
            <div class='answer' ng-repeat="answer in answers" ng-bind-html='htmlToTrusted(answer)'></div>
        </div>
    </div>

</div>
</body>
</html>
