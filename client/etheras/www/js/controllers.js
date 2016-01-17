angular.module('starter.controllers', [])

.controller('SearchController', function($scope, $state, resultsData, httpService) {
 var results = [];

  $scope.search = function(flight) {
    console.log(flight);
    var urlParams = {
      'to' : flight.to,
      'from': flight.from,
      'availableSeats': flight.seats,
      'minPrice': flight.min,
      'maxPrice': flight.max
    };

    var url = "/flights";

    httpService.get(url, urlParams).then(function(flights) {
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
      var url = "/users/" + $localstorage.get('user');
      console.log(url);
       httpService.get(url, []).success(function(data) {
          $scope.user = data;
      })
   });

  $scope.logout = function() {
    $localstorage.clear();
    $state.go('auth');
  }



})

.controller('ResultController', function($scope, resultsData, httpService, errorPopup) {

 $scope.$on('$ionicView.beforeEnter', function() {
    $scope.flights = resultsData.get();
    console.log($scope.flights);
  });

 $scope.reserve = function(flight) {
  var url = "/users/" + $localstorage.get('user') + "/reservations";
  var data = {
    'flightId' : flight.id,
    'numOfSeats': flight.seats
  };



  var response = httpService.post(url, data);
  if (response == 1) {
    var completePopup=$ionicPopup.show({
        title: "Reservation Complete",
        template: "Η κράτηση ολοκληρώθηκε με επιτυχία",
        cssClass: 'custom-popup',
        buttons:[
          {text: 'OK',
           type: 'button button-complete',
          }
        ]
      });
  }
  else {
    var errorPopup=$ionicPopup.show({
          title: "Error",
          template: "An error occured! PLease try again",
          cssClass: 'error-popup',
          buttons:[
            {text: 'OK',
             type: 'button button-complete',
            }
          ]
        });
  }
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

  $scope.error = 0;
  $scope.login = function(user) {
  
    var urlParams = {
      'username' : user.username,
      'password': user.password
    }

    var url = '/users/auth'

    httpService.get(url, urlParams).success(function(data) {
        console.log(data);
          $localstorage.set('user', data.userId)
          $state.go('tab.account');
        }).error(function(response) {
          console.log(response);
           $scope.error = "Παρουσιάστηκε κάποιο πρόβλημα. Παρακαλώ ελέξτε τα στοιχεία σας και προσπαθήστε ξανά";
        });
      
  }
});
