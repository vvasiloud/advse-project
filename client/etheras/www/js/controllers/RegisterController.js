angular.module('starter.controllers')

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
});