angular.module("appModule")
	.component("resource", {
	
		templateUrl:"app/appModule/resource/resource.component.html",
		controller:function(topicService){ //we use the topicService instead sine a resource is a topic object
			
			//point of this is to handle requests from the html file
			//we w
			
			var vm = this;
			vm.selectedCategory = null; //if they choose a button to filter by at the top
			vm.selected = null; //if they choose a single entry
			vm.edit = null; //if somebody selects a resource to edit.. on other side we can verify they have permission
			
			vm.resources = [];
			
			function reload(){
				topicService.index().then(function(response){
					vm.resources = response.data; //resources are "topic" objects
				})
			}
			
			reload(); //load right away
			
			vm.showResource = function(resource){ //can grab the id out of it later in the service
				topicService.show(resource.id).then(function(response){
					vm.selected = response.data;
				})
			}
			
			vm.createResource = function(resource){ //name is resource, but its a topic obj
				topicService.create(resource).then(function(response){
					reload(); //after it's made, reload our data
				})
			}
			
			vm.deleteResource = function(resource){ //we can pull the id out of this later in the service to give to the controller
				topicService.destroy(resource.id).then(function(response){
					reload(); //after it's deleted, reload our data
				})
			}
			
			vm.setEditResource = function(resource){ //will be passed in by html form
				vm.edit = resource; //now we can show an update form when this isn't null,
			}
			
			vm.cancelEdit = function(){
				vm.edit = null; //if they click cancel and don't want to save changes
			}
			
			
			//when they click save
			vm.updateResource = function(resource){ //think this is okay because in the service we can get userId/resourceId from the authModule
				topicService.update(resource).then(function(response){
					reload();
				})
			}
			
			vm.setCategory = function(category){
				topicService.getTopicByTagKeyword(category)//whatever the keyword is goes in there
					.then(function(response){
						vm.selectedCategory = category;
						vm.resources = response.data;
						console.log(vm.resources);
					})
			}
			
		},
		controllerAs:"vm"
		
		
		
		
		
		
	})