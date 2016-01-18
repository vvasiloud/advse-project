angular.module('starter.controllers', [])

.controller('SearchController', function($scope, $state, resultsData, httpService, $ionicPopup) {
 var results = [];
   $scope.$on('$ionicView.beforeEnter', function() {
      var url = "/cities";
      var urlParams = {
        'type': 'from'
      };
       httpService.get(url, urlParams).success(function(data) {
          $scope.cities = data;
      })
   });

  $scope.search = function(flight) {

    var urlParams = {
      'to' : flight.to,
      'from': flight.from,
      'minPrice': flight.min,
      'maxPrice': flight.max
    };

    var url = "/flights";

    httpService.get(url, urlParams).success(function(flights) {
      results = flights;
      resultsData.set(results);
      $state.go('tab.results');
    }).error(function(res){
        var errorPopup=$ionicPopup.show({
            title: "",
            template: "Δεν υπάρχουν πτήσεις που να ταιριάζουν στην αναζήτηση.",
            cssClass: 'error-popup',
            buttons:[
              {text: 'OK',
               type: 'button button-complete',
              }
            ]
          });
    })
  }
})

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
})

.controller('ResultController', function($scope, resultsData, httpService, $ionicPopup, $localstorage, $state) {

 $scope.$on('$ionicView.beforeEnter', function() {
    $scope.flights = resultsData.get();
  });

 $scope.reserve = function(flight) {
  var url = "/users/" + $localstorage.get('user') + "/reservations";
  var data = {
    'flightID' : flight.id,
    'numOfSeats': flight.seats
  };

  httpService.post(url, data).success(function(response) { 
        
        $state.go('tab.account');
    
    }).error(function(response) {

        var errorPopup=$ionicPopup.show({
            title: "Error",
            template: "Παρουσιάστηκε κάποιο πρόβλημα! Παρακαλώ ελέξτε το υπόλοιπό σας και τον αριθμό θέσεων και προσπαθήστε ξανά!",
            cssClass: 'error-popup',
            buttons:[
              {text: 'OK',
               type: 'button button-complete',
              }
            ]
          });
    });
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

.controller('RegisterController', function($scope, httpService, $localstorage, $state) {

  $scope.register = function(user){
    $scope.error = 0;
    var url = '/users'
    httpService.post(url, user).then(function(response) {

    if (response.status == 201) {

       $localstorage.set('user', response.data.id);

        var urlParams = {
          'username' : user.username,
          'password': user.password
        }

      var url = '/users/auth'

      httpService.get(url, urlParams).success(function(data) {
            $localstorage.set('user', data.userId)
            $state.go('tab.account');
          }).error(function(response) {
             $state.go('auth/login');
          });
    }
    else {
      $scope.error = "Παρουσιάστηκε κάποιο πρόβλημα. Παρακαλώ προσπαθήστε ξανά!"
    }

    });
    
  }
})

.controller('EditController', function($scope, $http, $localstorage, $state, httpService) {
   $scope.$on('$ionicView.beforeEnter', function() {
      var url = "/users/" + $localstorage.get('user');
       httpService.get(url, []).success(function(data) {
          $scope.user = data;
      })
   });

  $scope.edit = function(user) {
    var url = "/users/" + $localstorage.get('user');
       httpService.put(url, user).success(function(response) {
          $state.go('tab.account');
      }).error(function(response){
         var errorPopup=$ionicPopup.show({
            title: "Error",
            template: "Παρουσιάστηκε κάποιο πρόβλημα! Παρακαλώ προσπαθήστε ξανά!",
            cssClass: 'error-popup',
            buttons:[
              {text: 'OK',
               type: 'button button-complete',
              }
            ]
          });
      })
  }

})

.controller('LoginController', function($scope, httpService, $localstorage, $state) {

  $scope.error = 0;
  $scope.login = function(user) {
  
    var urlParams = {
      'username' : user.username,
      'password': user.password
    }

    var url = '/users/auth'

    httpService.get(url, urlParams).success(function(data) {
          $localstorage.set('user', data.userId)
          $state.go('tab.account');
        }).error(function(response) {
           $scope.error = "Παρουσιάστηκε κάποιο πρόβλημα. Παρακαλώ ελέξτε τα στοιχεία σας και προσπαθήστε ξανά";
        });
      
  }
});
