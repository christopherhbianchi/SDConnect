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
			
			vm.createEvent = function(event){
				eventService.createEvent(event)
				.then(function(resp){
					vm.displayEvents();
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
			vm.editEvent = function(event){
				vm.eventSelected = event;
				vm.eventSelected = angular.copy(event);
//				console.log(vm.eventSelected.date);
				var splitDate = vm.eventSelected.date.split("-");
				splitDate = splitDate.map(Number);
				var newDate = new Date(splitDate[0], splitDate[1] - 1 , splitDate[2]);
				vm.eventSelected.date = newDate;
			};
			
			vm.updateEvent = function(event){
				eventService.update(event)
				.then(function(resp){
					vm.displayEvents();
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
			vm.deleteEvent = function(eid){
				eventService.destroy(eid)
				.then(function(resp){
					vm.displayEvents();
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
			
		},
			
		controllerAs: 'vm'
});