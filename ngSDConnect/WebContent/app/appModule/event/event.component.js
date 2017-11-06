angular.module("appModule")
	.component("event", {
		templateUrl: "app/appModule/event/event.component.html",
		
		controller: function(eventService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.eventList = [];
			
			var currentUserToken = eventService.returnUser();
			
			vm.eventSelected = null;
			vm.newEvent = false;
			
			var getAllEvents = function() {
				eventService.index()
				.then(function(response){
					vm.eventList = response.data;
				})
			}
			
			getAllEvents();
			
			vm.displayEvents = function(){
				vm.eventSelected = null;
				vm.newEvent = false;
				getAllEvents();
			};
			
			vm.currentAdmin = function() {
				return currentUserToken.type == 'admin';
			};
			
			vm.createEvent = function(event){};
			
			vm.editEvent = function(event){
				vm.eventSelected = event;
			};
			
			vm.updateEvent = function(event){
				eventService.update(event)
				.then(function(resp){
					vm.displayEvents();
				})
				.catch(function(error){
					
				});
			};
			
			vm.deleteEvent = function(eid){
				eventService.destroy(eid)
				.then(function(resp){
					vm.displayEvents();
				})
				.catch(function(error){
					
				});
			};
			
			
		},
			
		controllerAs: 'vm'
});