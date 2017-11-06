angular.module("appModule").factory("userService",
	function(profileService, $http, $filter, authService, $rootScope) {

			var userService = {};

			var students = [];

			userService.index = function() {
					return $http({
						method : 'GET',
						url : "rest/profile"
					})
				}
			
			userService.getOne = function() {
				return $http({
					method:"GET", 
					url: ""
			})
		}

			return userService;

})// end of function
			