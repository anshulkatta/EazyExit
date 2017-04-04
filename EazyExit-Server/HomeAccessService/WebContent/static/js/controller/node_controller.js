'use strict';

angular.module('myApp').controller('NodeController', ['$scope', 'NodeService', function($scope, NodeService) {
    var self = this;
    self.user={id:null,nodename:''};
    self.users=[];

    self.submit = submit;
   
    fetchAllNodes();

    function fetchAllNodes(){
    	NodeService.fetchAllNodes()
            .then(
            function(d) {
                self.nodes = d;
            },
            function(errResponse){
                console.error('Error while fetching Nodes');
            }
        );
    }

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function reset(){
        self.user={id:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
