// MODULE
var myApp = angular.module('myApp', ['ngRoute', 'ngResource']);

//ROUTE CONFIG
myApp.config(function ($routeProvider) {
   
    $routeProvider
    
        .when('#/', {
            templateUrl: '/',
            controller: 'mainController'
        })
    
        .when('/missions', {
            templateUrl: 'pages/missions.html',
            controller: 'missionsController'
        })

        .when('/battle', {
            templateUrl: 'pages/battle.html',
            controller: 'battleController'
        })

        .when('/login', {
            templateUrl: 'pages/login.html',
            controller: 'loginController'
        })

        .when('/allies', {
            templateUrl: 'pages/allies.html',
            controller: 'alliesController'
        })

        .when('/welcome', {
            templateUrl: 'pages/welcome.html',
            controller: 'welcomeController'
        });
}); 

// CONTROLLERS
myApp.controller('mainController', ['$scope', '$log', 'loginService', function ($scope, $log, loginService) {
    
}]);

myApp.controller('welcomeController', ['$scope', '$log', 'loginService', function ($scope, $log, loginService) {                                           
    $log.info('Welcome ' + loginService.loggedInUser);
    
    $scope.name = function () {
        return loginService.loggedInUser;
    };
}]);

// CONTROLLERS
myApp.controller('loginController', ['$scope', '$log', '$location', 'loginService', '$http', '$resource', function ($scope, $log, $location, loginService, $http, $resource) {                                     
    
    $log.info('User loaded login screen');
    
    $scope.loginUser = function () {
        var username = document.getElementById('login-username').value;
        var password = document.getElementById('login-password').value;
        
        console.log('login function called by ' + username + ' with password: ' + password);
        loginService.loggedInUser = username;
        
        var data = '\'username\':' + username + ', \'password\':' + password;
        
        $resource('http://127.0.0.1:51849/player', {}, {'query':{ method: 'POST'}}).query(data);        
        
        alert('You have successfully been logged in: ' + loginService.userName);        
        
        $location.url('/welcome');
    }
}]);

myApp.controller('missionsController', ['$scope', '$log', '$routeParams', function($scope, $log, $routeParams) {                                            
   $log.info('User loaded missions screen');
}]);

myApp.controller('battleController', ['$scope', '$log', '$routeParams', function($scope, $log, $routeParams) {                                            
   $log.info('User loaded battle screen');
}]);

myApp.controller('alliesController', ['$scope', '$log', '$routeParams', function($scope, $log, $routeParams) {                                            
      $log.info('User loaded allies screen');

}]);

//SERVICES
myApp.service('loginService', function() {
   this.loggedInUser = ''; 
});