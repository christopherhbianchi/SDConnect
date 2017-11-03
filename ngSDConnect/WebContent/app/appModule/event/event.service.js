angular.module('appModule')
	.factory('eventService', function($http, $filter, $location, authService){
		var eventService = {};
		
		eventService.index = function(id){  //one post
				return $http({
					method : 'GET',
					url : 'rest/topics/' + id + '/posts'
				})
		};
		
		eventService.show = function() {  //show all posts
			return $http({
				method : 'GET',
				url : 'rest/posts/' + id
			})
		};
		
		service.create = function(post, tid) {
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
					url : 'rest/user/' + uid + '/posts/' + pid
				})
			}
		}
		
		return service;
	});