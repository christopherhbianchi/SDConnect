angular.module("appModule")
	.component("resource", {
	
		templateUrl:"app/appModule/resource/resource.component.html",
		controller:function(topicService, tagService, $location){ //we use the topicService instead since a resource is a topic object
			
			//point of this is to handle requests from the html file
			//we w
			
			var vm = this;
			vm.selectedCategory = null; //if they choose a button to filter by at the top
			vm.selected = null; //if they choose a single entry
			vm.edit = null; //if somebody selects a resource to edit.. on other side we can verify they have permission
			vm.add = null; //if somebody wants to add, we set it here
			vm.selectorOptions = []; //need to populate this with tags i want
			vm.clicked = null; //to have the resume populate
			
			vm.resources = [];
			
			function reload(){
				topicService.index().then(function(response){
					vm.resources = response.data; //resources are "topic" objects
				})
			}
			
			function loadDesiredTags(){
				tagService.index().then(function(response){
					response.data.forEach(function(element){
						if(element.type === "Resume" || element.type === "CoverLetter" || element.type === "Interview"){
							vm.selectorOptions.push(element);
						}
					});
				});
			}
			
			reload(); //load right away
			loadDesiredTags(); //load tag options right away
			
			vm.showResource = function(resource){ //can grab the id out of it later in the service
				topicService.show(resource.id).then(function(response){
					vm.selected = response.data;
//					vm.selectedCategory = null;
					console.log(response.data);
				})
			}
			
			vm.setAddResource = function(){
				vm.add = true;
			}
			
			vm.createResource = function(resource){ //name is resource, but its a topic obj
				topicService.createResource(resource).then(function(response){ //can attach this function to a save button
					reload(); //after it's made, reload our data
					vm.add = null;//once it's added, turn it off
				})
			}
			
			vm.cancelCreation = function(){
				vm.add = null; //if they cancel they go back to category page
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
				console.log("in setCategory")
				topicService.getTopicByTagKeyword(category)//whatever the keyword is goes in there
					.then(function(response){
						vm.selectedCategory = category;
						vm.resources = response.data;
						console.log(vm.resources);
						vm.selected = null;
					})
			}
			
			//for grabbing the link for the pdf viewer
			vm.showPostAndResume = function(post) {
				var pdfName = post.user.email.split("@")[0] + ".pdf";
				
				$location.path("/resources/" + pdfName);
				vm.clicked = true;
			}
			
		},
		controllerAs:"vm"
		
		
		
		
		
		
	})