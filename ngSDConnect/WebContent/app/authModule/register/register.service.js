angular.module("authModule").factory("registerService",
		function($http, $filter, authService, $rootScope) {

			var registerService = {};
			
			registerService.create = function(user) {	
					return $http({
						method : 'POST',
						url : "rest/users/"+authService.getToken().id+"/profiles",
						headers : {
							'Content-Type' : 'application/json'
						},
						data : user
						})
						.then(function(response) {
							return response;
						});//end of the then function

			}
			return registerService;

		})// end of function

// Services actlike the DAO
