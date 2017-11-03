angular.module("authModule").factory("registerService",
		function($http, $filter, authService, $rootScope) {

			var registerService = {};
			
			registerService.create = function(profile) {	
					return $http({
						method : 'POST',
						url : "rest/users/"+authService.getToken().id+"/profiles",
						headers : {
							'Content-Type' : 'application/json'
						},
						data : profile
					})
					.then(function(response) {
						return response;
					});//end of the then function

			}

		})// end of function

// Services actlike the DAO
