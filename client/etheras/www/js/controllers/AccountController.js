angular.module('starter.controllers')

.controller('AccountController', function($scope, $http, $localstorage, $state, httpService) {
   $scope.$on('$ionicView.beforeEnter', function() {
      var url = "/users/" + $localstorage.get('user');
       httpService.get(url, []).success(function(data) {
          $scope.user = data;
      })
   });

  $scope.logout = function() {
    $localstorage.clear();
    $state.go('auth');
  }

  $scope.edit = function() {
    $state.go('tab.edit');
  }
});