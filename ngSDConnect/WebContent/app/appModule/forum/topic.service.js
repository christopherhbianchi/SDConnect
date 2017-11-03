angular.module('appModule')
	.factory('topicService', function($http, $filter, $location, authService){
		var service = {};
		
		var checkLogin = function(){
			var userIdPass = authService.getToken();
//			return userIdPass.id;
			return 1;
		};
		
		service.index = function(){
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'GET',
					url : 'rest/topics'
				})
			}
		};
		
		service.create = function(topic) {
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'POST',
					url : 'rest/topics',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : topic
				})
			}
		}
	
		service.update = function(topic) {
			var tid = topic.id;
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'PUT',
					url : 'rest/topics/' + tid,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : topic
				})
			}
		}
		
		service.destroy = function(tid) {
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'DELETE',
					url : 'rest/topics/' + tid
				})
			}
		}
		
		service.topicTagKeyword = function(keyword) {
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'GET',
					url : 'rest/topics/tags/' + keyword
				})
			}
		}
		
		return service;
	});