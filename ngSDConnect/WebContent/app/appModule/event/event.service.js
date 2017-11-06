angular.module('appModule')
	.factory('eventService', function($http, $filter, $location, authService){
		var service = {};
		
		var checkLogin = function(){
			var userIdPass = authService.getToken();
			return userIdPass.id;
	
		};
		
		service.show = function(id){  //one post
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
					method : 'GET',
					url : 'rest/events/' + id
				})
			}
		};
		
		service.index = function() {  //show all posts
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
			return $http({
				method : 'GET',
				url : 'rest/events'
					})
			}
		};
		
		service.showEventsByCohortId = function(id) {
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
			return $http({
				method:"GET",
				url: "rest/cohorts/"+id+"/events"
				})
			}
		}
		
		/// route for create --> users/{userId}/events
		service.createEvent = function(event) {
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				return $http({
				method : 'POST',
				url : 'rest/users/' + authService.getToken().id + '/events',
				headers : {
					'Content-Type' : 'application/json'
				},
				data : event
				})
			}
		}
	
		// route for update --> users/{userId}/events/{eventId}
		service.update = function(event) {
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
		
				return $http({
					method : 'PUT',
					url : 'rest/users/' + authService.getToken().id + '/events/' + event.id,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : event
				})
			}
		}
		
		
		//route for delete -->users/{userId}/events/{eventId}
		service.destroy = function(id) {
			var uid = checkLogin();
			if(isNaN(uid)) {
				$location.path('/login');
			}
			else {
				
					return $http({
						method : 'DELETE',
						url : 'rest/users/' + authService.getToken().id + '/events/' + id
					})
				}
			}
		
		service.returnUser = function() {
			return authService.getToken();
		}
		
		return service;
	});