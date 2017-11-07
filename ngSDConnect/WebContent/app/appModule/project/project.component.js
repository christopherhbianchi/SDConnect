angular.module("appModule")
	.component("project", {
		templateUrl: "app/appModule/project/project.component.html",
		
		controller: function(projectService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.projectList = [];
			
			var currentUserToken = projectService.returnUser();
			
			vm.projectSelected = null;
			vm.newProject = false;
			
			var getProjects = function(){
				projectService.index()
				.then(function(resp){
					vm.projectList = resp.data;
				})
				.catch(function(error){
					console.log(error);
				})
			};
			
			getProjects();
			
			vm.getIntensity = function(estHours){
				if(estHours < 5) {
					return 'green';
				}
				else if(estHours < 10) {
					return 'yellow';
				}
				else {
					return 'red';
				}
				
			};
			
			vm.displayProjects = function(){
				vm.projectSelected = null;
				vm.newProject = false;
				getProjects();
			};
			
			vm.currentAdmin = function() {
				return currentUserToken.type == 'admin';
			};
			
			vm.createProject = function(project){
				console.log(project);
				projectService.create(project)
				.then(function(resp){
					vm.displayProjects();
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
			vm.editProject = function(project){
				vm.projectSelected = project;
			};
			
			vm.updateProject = function(project){
				projectService.update(project)
				.then(function(resp){
					vm.displayProjects();
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
			vm.deleteProject = function(pid){
				projectService.destroy(pid)
				.then(function(resp){
					vm.displayProjects();
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
		},
			
		controllerAs: 'vm'
});