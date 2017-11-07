angular.module('appModule')
	.factory('projectService', function($http, $filter, $location, authService){
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
					url : 'rest/projects'
				})
			}
		};
		
		service.show = function(pid){
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'GET',
					url : 'rest/projects/' + pid
				})
			}
		};
		
		service.create = function(project) {
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'POST',
					url : 'rest/users/' + uid + '/projects',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : project
				})
			}
		}
	
		service.update = function(project) {
			var pid = project.id;
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'PUT',
					url : 'rest/users/' + uid + '/projects/' + pid,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : project
				})
			}
		}
		
		service.destroy = function(pid) {
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'DELETE',
					url : 'rest/users/' + uid + '/projects/' + pid
				})
			}
		}
		
		service.returnUser = function() {
			return authService.getToken();
		}
		
		return service;
	});