angular.module("appModule")
	.component("resource", {
	
		templateUrl:"app/appModule/resource/resource.component.html",
		controller:function(){
			
			//point of this is to handle requests from the html file
			//we w
			
			var vm = this;
			vm.selectedCategory = null; //if they choose a button to filter by at the top
			vm.selected = null; //if they choose a single entry
			vm.edit = null; //if somebody selects a resource to edit.. on other side we can verify they have permission
			
			var resources = [];
			
			//one to handle: 
			//reads
			//creates
			//updates
			//deletes
			
			//do this in a service first
			
			
			
		},
		controllerAs:"vm"
		
		
		
		
		
		
	})