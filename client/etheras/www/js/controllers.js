angular.module('starter.controllers', [])

.controller('FlightController', function($scope) {})

.controller('SearchController', function($scope, $state, resultsData, httpService) {
 var results = [];

  $scope.search = function(flight) {
    console.log(flight);
    httpService.get('js/flights.json').then(function(flights) {
      results = flights.data;
      console.log(results);
      resultsData.set(results)
      $state.go('tab.results');
   
    })
    // post request..omn success redirect to results
  }
})

.controller('AccountController', function($scope, $http) {

  $http.get('js/user.json').success(function(data) {
      $scope.user = data;
  })


})

.controller('ResultController', function($scope, resultsData) {

 $scope.$on('$ionicView.beforeEnter', function() {
    $scope.flights = resultsData.get();
    console.log($scope.flights);
  });

 $scope.reserve = function(flight) {
  console.log(flight);
 }

})

.controller('AuthController', function($scope, $state) {
  $scope.login = function() {
    $state.go("login");
  }

  $scope.register = function() {
    $state.go("register");
  }
})

.controller('RegisterController', function($scope) {})

.controller('ReserveController', function($scope) {})

.controller('LoginController', function($scope) {});
