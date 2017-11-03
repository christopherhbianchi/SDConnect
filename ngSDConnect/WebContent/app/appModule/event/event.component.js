angular.module("appModule")
	.component("event", {
		templateUrl: "app/appModule/event/event.component.html",
		
		controller: function(eventService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.eventList = [];
			
			var getAllEvents = function() {
				eventService.index()
				.then(function(response){
					console.log("Inside get all response");
					vm.eventList = response.data;
				})
			}
			
			getAllEvents();
			
			
		},
			
		controllerAs: 'vm'
});