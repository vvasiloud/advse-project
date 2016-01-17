angular.module('starter.controllers', [])

.controller('FlightController', function($scope) {})

.controller('SearchController', function($scope, $state, resultsData, httpService) {
 var results = [];

  $scope.search = function(flight) {
    console.log(flight);
    httpService.get('js/flights.json').then(function(flights) {
      results = flights.data;
      console.log(results);
      resultsData.set(results);
      $state.go('tab.results');
   
    })
    // post request..omn success redirect to results
  }
})

.controller('AccountController', function($scope, $http, $localstorage, $state, httpService) {
   $scope.$on('$ionicView.beforeEnter', function() {
       httpService.get('/users/569bd10230228f937aa25f3b', []).success(function(data) {
        console.log(data);
          $scope.user = data;
      })
   });

  $scope.logout = function() {
    $localstorage.clear();
    $state.go('auth');
  }


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

.controller('RegisterController', function($scope, httpService, $localstorage) {

  $scope.register = function(user){
    console.log(user);
    $scope.error = 0;
    var url = '/users'
    var response = httpService.post(url, user);
    console.log(response);
    if (response == 1) {
       $localstorage.set('user', response.data.id);
       $state.go('tab.account');
    }
    else {
      $scope.error = "Παρουσιάστηκε κάποιο πρόβλημα. Παρακαλώ προσπαθήστε ξανά!"
    }

  }
})

.controller('LoginController', function($scope, httpService, $localstorage) {

  $scope.login = function(user) {
    console.log(user);
  
    var urlParams = {
      'username' : user.username,
      'password': user.password
    }

    httpService.get(url, urlParams).then(function(response) {
        console.log(response);
        if(response == 200){
          $localstorage.set('user', response.id)
          $state.go('tab.account');
        }
        else {
          $scope.error = "dasdas";
        }
     
      });
  }
});
