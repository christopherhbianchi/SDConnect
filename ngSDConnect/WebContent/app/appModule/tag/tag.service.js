angular.module('appModule')
	.factory('tagService', function($http, $filter, $location, authService){
		var service = {};
		
		var checkLogin = function(){
			var userIdPass = authService.getToken();
			return userIdPass.id;
		};
		
		service.index = function(){
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'GET',
					url : 'rest/tags'
				})
			}
		};
		
		return service;
	});