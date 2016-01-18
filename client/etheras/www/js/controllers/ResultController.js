angular.module('starter.controllers')

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
});