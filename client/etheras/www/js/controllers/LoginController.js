angular.module('starter.controllers')

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
