angular.module('starter.controllers', [])

.controller('FlightController', function($scope) {})

.controller('AccountController', function($scope, $http) {

  $http.get('js/user.json').success(function(data) {
      $scope.user = data;
  })


});
