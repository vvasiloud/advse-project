angular.module('starter.services', [])

.factory('constant', function() {
	return {
		api : "http://localhost:8100/etherastickets"
	}
})

.factory('httpService', function($http, constant) {
	return{
		get: function(url, urlParams){
			var link = constant.api + url;
			return $http({
                method: 'GET',
                url: link,
                params: urlParams
            })
			.success(function(data){
		        return data;
			}).error(function(res) {
                return res;
            });
    	},

    	post: function(url, data){
    		var link = constant.api + url;
    		return $http.post(link, data).success(function(res) {
                return res;
    		}).error(function(res, status){
                return status;
            });
    	},

        put: function(url, data) {
            var link = constant.api + url;
            return $http.put(link, data).success(function(res) {
                return res;
            }).error(function(res, status){
                return status;
            });
        }
	};
})

.factory('$localstorage', function($window, $ionicHistory) {
  return {
    set: function(key, value) {
      $window.localStorage[key] = value;
    },
    get: function(key, defaultValue) {
      return $window.localStorage[key] || defaultValue;
    },
    setObject: function(key, value) {
      $window.localStorage[key] = JSON.stringify(value);
    },
    getObject: function(key) {
      return JSON.parse($window.localStorage[key] || '{}');
    },
    clear: function () {
        $window.localStorage.clear();
        $ionicHistory.clearHistory();
        $ionicHistory.clearCache();
        $ionicHistory.nextViewOptions({ disableBack: true, historyRoot: true });
      }
  };
})

.factory('resultsData', function () {

    var data = {
        flights: ''
    };

    return {
        get: function () {
            return data.flights;
        },
        set: function (results) {
            data.flights = results;
        }
    };
});