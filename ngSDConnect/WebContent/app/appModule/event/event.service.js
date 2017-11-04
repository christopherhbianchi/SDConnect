angular.module('appModule')
	.factory('eventService', function($http, $filter, $location, authService){
		var eventService = {};
		
		eventService.show = function(id){  //one post
				return $http({
					method : 'GET',
					url : 'rest/events/' + id
				})
		};
		
		eventService.index = function() {  //show all posts
			return $http({
				method : 'GET',
				url : 'rest/events'
					})
		};
		
		eventService.showEventsByCohortId = function(id) {
			return $http({
				method:"GET",
				url: "rest/cohorts/"+id+"/events"
			})
		}
		
		/// route for create --> users/{userId}/events
		eventService.createEvent = function(event) {
			
					return $http({
					method : 'POST',
					url : 'rest/users/' + authService.getToken().id + '/events',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : event
				})
		}
	
		// route for update --> users/{userId}/events/{eventId}
		eventService.update = function(event) {
		
				return $http({
					method : 'PUT',
					url : 'rest/users/' + authService.getToken().id + '/events/' + event.id,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : event
				})
			}
		
		
		//route for delete -->users/{userId}/events/{eventId}
	eventService.destroy = function(id) {
			
				return $http({
					method : 'DELETE',
					url : 'rest/users/' + authService.getToken().id + '/events/' + id
				})
			}
		
		return eventService;
	});