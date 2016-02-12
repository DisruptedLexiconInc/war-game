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
    
        .when('/logout', {
            templateUrl: 'pages/logout.html',
            controller: 'logoutController'
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
    $log.info('Welcome ' + loginService.username);
    
    $scope.name = function () {
        return loginService.username;
    };
}]);

// CONTROLLERS
myApp.controller('loginController', ['$scope', '$log', '$location', 'loginService', '$http', function ($scope, $log, $location, loginService, $http) {    
    $log.info('User loaded login screen');
    
    if(loginService.id > 0) {
        $log.debug('User already loged in. Redirecting to home page: ' + loginService.username);
         $location.url('/welcome');        
    }
    
    $scope.loginUser = function () {
        var username = document.getElementById('login-username').value;
        var password = document.getElementById('login-password').value;
        
        $log.debug('login function called by ' + username + ' with password: ' + password);
        
        var data = {username : username, password : password};
        
        $http.post('http://127.0.0.1:51849/login', JSON.stringify(data)).success(function (data, status) {
            $log.debug('JSON login rsponse is' + JSON.stringify(data));
            loginService.update(data);
            
            $log.debug(loginService);
            if(loginService.id > 0) {
                $log.debug('user successfully logged in. redirecting to welcome screen.');
                $location.url('/welcome');
            }else {
                $log.warn('users login failed.');
                alert('Login failed');
            } 
        }).error(function (status) {
            $log.warn('error happened: ' + status);
            loginService.username = "";
        });
    };
}]);

myApp.controller('battleController', ['$scope', '$log', '$http', 'loginService', function($scope, $log, $http, loginService) {                                            
   $log.info('User loaded battle screen ' + loginService.username);
    
    function Usercontext(id, username, password) {
            this.id = id;
            this.username = username;
            this.password = password;
    }
    
    $scope.items = [];
    $scope.add = function(data) {
        $scope.items.push(data);
    }
    
    var sendData = {id: loginService.id, username : loginService.username, password : loginService.password};
    
    $http.post('http://127.0.0.1:51849/battles/opponent', sendData).success(function (data, status) {
        $log.debug('***opponent response');
        $log.debug(data);
        
        angular.forEach(data, function(singData, index) {
            $log.debug(index + ' ' + singData);
            $scope.add(singData);
        })
    }).error(function (status) {
        $log.warn('failed to get opponents: ' + status);
    });
}]);

myApp.controller('alliesController', ['$scope', '$log', '$routeParams', function($scope, $log, $routeParams) {                                            
      $log.info('User loaded allies screen');

}]);

myApp.controller('missionsController', ['$scope', '$log', '$routeParams', function($scope, $log, $routeParams) {                                            
   $log.info('User loaded missions screen');
}]);

myApp.controller('logoutController', ['$scope', '$log', 'loginService', '$location', function($scope, $log, loginService, $location) {                                            
   $log.info('User loaded logout screen ' + loginService.username);
    
    loginService.logout();
    
    $location.url('/login');
}]);

//SERVICES
myApp.service('loginService', function() {
    
    var self = this;
    this.id = '';
    this.username = '';
    this.password = ''
    
    this.update = function(data) {
        this.username = data.username;
        this.id = data.id;
    }
    
    this.logout = function() {
        this.username = "";
        this.id = "";
    }
});