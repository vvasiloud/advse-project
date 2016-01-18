angular.module('starter.controllers')

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

});