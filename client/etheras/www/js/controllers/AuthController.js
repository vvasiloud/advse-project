angular.module('starter.controllers')

.controller('AuthController', function($scope, $state) {
  $scope.login = function() {
    $state.go("login");
  }

  $scope.register = function() {
    $state.go("register");
  }
});