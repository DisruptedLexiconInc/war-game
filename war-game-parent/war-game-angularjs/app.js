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
myApp.controller('loginController', ['$scope', '$log', '$location', 'loginService', '$resource', '$http', function ($scope, $log, $location, loginService, $resource, $http) {                                     
    
    $log.info('User loaded login screen');
    
    $scope.loginUser = function () {
        var username = document.getElementById('login-username').value;
        var password = document.getElementById('login-password').value;
        
        $log.debug('login function called by ' + username + ' with password: ' + password);
       
        function Usercontext(id, username, password) {
            this.id = id;
            this.username = username;
            this.password = password;
        }
        
        var data = {username : username, password : password};
        
        $scope.restreturn = $http.post('http://127.0.0.1:51849/login', JSON.stringify(data)).success(function (data, status) {
            $log.log('the data is' + data);
            loginService.loggedInUser = data.username;
        }).error(function (status) {
            $log.warn('error happened: ' + status);
        });
        
        if(data !==0) {
            loginService.loggedInUser = $scope.restreturn.username;
            $location.url('/welcome');
        }else {
            alert('Login failed');
        } 
    };
}]);

myApp.controller('missionsController', ['$scope', '$log', '$routeParams', function($scope, $log, $routeParams) {                                            
   $log.info('User loaded missions screen');
}]);

myApp.controller('battleController', ['$scope', '$log', 'resource', function($scope, $log, $resource) {                                            
   $log.info('User loaded battle screen');
    
//    function usercontext(id, username, password) {
//            this.id = id;
//            this.username = username;
//            this.password = password;
//        }
//    
//    var getReturn = $resource('http://127.0.0.1:51849/users/', {}, {'query':{ method: 'GET'}}).query(data);
//    $log.debug(getReturn);
//        if(getReturn.id !=0) {
//            $scope.usercontexts = getReturn.username;
//        }else {
//           $log.warn('GET failed for battle controller');
//        }       
}]);

myApp.controller('alliesController', ['$scope', '$log', '$routeParams', function($scope, $log, $routeParams) {                                            
      $log.info('User loaded allies screen');

}]);

//SERVICES
myApp.service('loginService', function() {
    
    var self = this;
    this.loggedInUser = ''; 
    this.loggedInUserNameLength = function() {
        return self.loggedInUser.length;  
    };
});