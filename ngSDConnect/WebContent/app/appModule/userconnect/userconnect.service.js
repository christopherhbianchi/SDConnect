angular.module("appModule").factory("userService",
	function($http, $filter, authService,  $rootScope) {

			var userService = {};

			var students = [];
			
			userService.index = function() {
				console.log("Inside UserService")
				 var user = authService.getToken();
					return $http({
						method : 'GET',
						url : "rest/profile"
					})
				}

			return userService;

})// end of function
			