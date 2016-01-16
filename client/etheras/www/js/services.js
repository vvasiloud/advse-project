angular.module('starter.services', [])

.factory('constant', function() {
	return {
		api : "http://"
	}
})

.factory('httpService', function($http, constant) {
	return{
		get: function(url){
			var link = constant.api + url;
			// return $http.get(link, {cache: false, headers: 'header("Cache-Control: no-store, no-cache, must-revalidate")'})
			// .success(function(data){
			//         return data;
   			// 	})
			return $http.get(url).success(function(data) {
				return data;
			});
    	},

    	post: function(url, data){
    		var link = constant.api + url;
    		$http.post(link, data).then(function(res) {
    			return res.status;
    		});
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