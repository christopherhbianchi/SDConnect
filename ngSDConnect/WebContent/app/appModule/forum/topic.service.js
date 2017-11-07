angular.module('appModule')
	.factory('topicService', function($http, $filter, $location, authService){
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
					url : 'rest/topics'
				})
			}
		};
		
		service.show = function(id) {
			return $http({
				method : 'GET',
				url : 'rest/topics/' + id
			})
		};
		
		service.create = function(topic) {
			console.log(topic)
			var data = {};
			data.posts = [];
			data.posts.push(topic.posts["0"]);
			data.tag = {};
			data.tag.id = topic.tag.id;
			data.name = topic.name;
			data.posts[0].postDate = Date.now();
			data.posts[0].user = {
					id : authService.getToken().id
			}
			
			console.log("in service create");
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
					data : data
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
		
		service.getTopicByTagKeyword = function(keyword) {
			var uid = checkLogin();
			console.log("uid: " + uid);
			console.log("keyword: " + keyword);
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'GET',
					url : 'rest/topics/tags/' + uid + "/" + keyword
				})
			}
		}
		
		service.returnUser = function() {
			return authService.getToken();
		}
		
		return service;
	});