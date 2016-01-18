angular.module('starter.controllers')

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
});