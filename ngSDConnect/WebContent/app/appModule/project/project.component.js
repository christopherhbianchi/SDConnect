angular.module("appModule")
	.component("project", {
		templateUrl: "app/appModule/project/project.component.html",
		
		controller: function(projectService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.projectList = [];
			
			var getProjects = function(){
				projectService.index()
				.then(function(resp){
					console.log(resp.data);
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
			
		},
			
		controllerAs: 'vm'
});