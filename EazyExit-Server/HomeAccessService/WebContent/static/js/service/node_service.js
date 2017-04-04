'use strict';

angular.module('myApp').factory('NodeService', ['$http', '$q', 'REST_URI_NODES', function($http, $q, REST_URI_NODES){

    //var REST_SERVICE_URI = 'http://192.168.43.205:8080/HomeAccessService-1/node/';

    var factory = {
    		fetchAllNodes: fetchAllNodes,
    };

    return factory;

    function fetchAllNodes() {
        var deferred = $q.defer();
        $http.get(REST_URI_NODES)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function discoverNodes() {
        var deferred = $q.defer();
        $http.get(REST_URI_DISC)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
