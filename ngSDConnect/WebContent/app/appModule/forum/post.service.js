angular.module('appModule')
	.factory('postService', function($http, $filter, $location, authService){
		var service = {};
		
		var checkLogin = function(){
			var userIdPass = authService.getToken();
			return userIdPass.id;
		};
		
		service.index = function(tid){
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'GET',
					url : 'rest/topics/' + tid + '/posts'
				})
			}
		};
		
		service.show = function(id) {
			return $http({
				method : 'GET',
				url : 'rest/posts/' + id
			})
		};
		
		service.create = function(post, tid) {
			console.log("in service.create");
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'POST',
					url : 'rest/topics/' + tid + '/users/' + uid + '/posts',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : post
				})
			}
		}
	
		service.update = function(post) {
			var pid = post.id;
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'PUT',
					url : 'rest/users/' + uid + '/posts/' + pid,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : post
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
					url : 'rest/users/' + uid + '/posts/' + pid
				})
			}
		}
		
		service.returnUser = function() {
			return authService.getToken();
		}
		
		return service;
	});