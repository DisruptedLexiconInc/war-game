// MODULE
var myApp = angular.module('myApp', ['ngRoute', 'ngResource', 'ngCookies']);

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
myApp.controller('mainController', ['$rootScope', '$log', function ($rootScope, $log) {

    if (typeof $rootScope.user === 'undefined' || $rootScope.user.id < 1) {
        $log.debug("user not logged in. redirecting to login page.");
        $location.url('/login');
    } else {
        $log.info('Home ' + $rootScope.user.username);
    }
}]);

myApp.controller('welcomeController', ['$rootScope', '$log', '$location', function ($rootScope, $log, $location) {
    $log.info("Loading welcome page.");

    $log.debug("Welcome scope user");
    $log.debug($rootScope.user);
    if (typeof $rootScope.user === 'undefined' || $rootScope.user.id < 1) {
        $log.debug("user not logged in. redirecting to login page.");
        $location.url('/login');

    } else {
        $log.info('Welcome ' + $rootScope.user.username);
    }

}]);

// CONTROLLERS
myApp.controller('loginController', ['$rootScope', '$scope', '$log', '$location', '$http', '$cookieStore', function ($rootScope, $scope, $log, $location, $http, $cookieStore) {
    $log.info('User loaded login screen. scope user below');
    $log.debug($rootScope.user);
    //
    //    if ($rootScope.user === null) {
    //        $rootScope.user = userFactory();
    //        $log.debug($rootScope.user);
    //    }

    if (typeof $rootScope.user != 'undefined') {
        // && $rootScope.user.id > 1
        $log.debug('User already loged in. Redirecting to home page: ' + $rootScope.user.username);
        $location.url('/welcome');
    }

    $scope.loginUser = function () {
        var username = document.getElementById('login-username').value;
        var password = document.getElementById('login-password').value;

        $log.debug('login function called by ' + username + ' with password: ' + password);

        var data = {
            username: username,
            password: password
        };

        $http.post('http://127.0.0.1:51849/login', JSON.stringify(data)).success(function (data, status) {
            $log.debug('JSON login response is' + JSON.stringify(data));

            $cookieStore.put('user', data);
            $rootScope.user = data;

            $log.debug($rootScope.user);
            if ($rootScope.user.id > 0) {
                $log.debug('user successfully logged in. redirecting to welcome screen.');
                $location.url('/welcome');
            } else {
                $log.warn('users login failed.');
                alert('Login failed');
            }
        }).error(function (status) {
            $log.warn('error happened: ' + status);
            scope.logout();
        });
    };

    //    $scope.getUser = function () {
    //        return $rootScope.user;
    //    };
}]);

myApp.controller('battleController', ['$rootScope', '$log', '$http', '$location', function ($rootScope, $log, $http, $location) {
    $log.info('User loaded battle screen ');

    if (typeof $rootScope.user === 'undefined' || $rootScope.user.id < 1) {
        $log.debug("user not logged in. redirecting to login page.");

        $location.url('/login');
    } else {

        $rootScope.opponents = [];
        $rootScope.add = function (data) {
            $rootScope.opponents.push(data);
        };

        var sendData = {
            id: $rootScope.user.id,
            username: $rootScope.user.username,
            password: $rootScope.user.password
        };

        $http.post('http://127.0.0.1:51849/battles/opponent', JSON.stringify($rootScope.user)).success(function (data, status) {
            $log.debug('***opponent response');
            $log.debug(data);

            angular.forEach(data, function (singData, index) {
                $log.debug(index + ' ' + singData);
                $rootScope.add(singData);
            });
        }).error(function (status) {
            $log.warn('failed to get opponents: ' + status);
        });
    }
}]);

myApp.controller('missionsController', ['$rootScope', '$log', '$routeParams', '$location', '$http', function ($rootScope, $log, $routeParams, $location, $http) {
    $log.info('User loaded missions screen');

    if (typeof $rootScope.user === 'undefined' || $rootScope.user.id < 1) {
        $log.debug("user not logged in. redirecting to login page.");

        $location.url('/login');
    } else {

        $rootScope.missions = [];
        $rootScope.addMission = function (data) {
            $rootScope.missions.push(data);
        };

        $http.get('http://127.0.0.1:51849/missions/').success(function (data, status) {
            $log.debug('JSON missions rsponse is' + JSON.stringify(data));

            angular.forEach(data, function (singData, index) {
                $log.debug(index + ' ' + singData);
                $rootScope.addMission(singData);
            });

        }).error(function (status) {
            $log.warn('error happened: ' + status);
        });
    }
}]);

myApp.controller('alliesController', ['$rootScope', '$log', '$http', '$routeParams', '$location', function ($rootScope, $log, $http, $routeParams, $location) {
    $log.info('User loaded allies screen');

    if (typeof $rootScope.user === 'undefined' || $rootScope.user.id < 1) {
        $log.debug("user not logged in. redirecting to login page.");

        $location.url('/login');
    } else {
        $rootScope.alliances = [];
        $rootScope.addAlliance = function (data) {
            $rootScope.alliances.push(data);
        };

        $http.get('http://127.0.0.1:51849/alliances/').success(function (data, status) {
            $log.debug('JSON alliances rsponse is' + JSON.stringify(data));

            angular.forEach(data, function (singData, index) {
                $log.debug(index + ' ' + singData);
                $rootScope.addAlliance(singData);
            });

        }).error(function (status) {
            $log.warn('error happened: ' + status);
        });
    }
}]);

myApp.controller('logoutController', ['$rootScope', '$log', '$location', function ($rootScope, $log, $location) {
    $log.info('User loaded logout screen ');


    $rootScope.user = undefined;


    $location.url('/login');
}]);

//SERVICES

//FACTORIES
myApp.factory('User', function userFactory() {
    var fac = {
        loggedIn: false,
        id: '',
        username: '',
        password: '',
        email: '',
        level: '',
        experience: '',
        cash: '',
        energy: ''
    };

    return fac;
});