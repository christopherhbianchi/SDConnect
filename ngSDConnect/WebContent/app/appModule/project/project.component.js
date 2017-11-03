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
			
		},
			
		controllerAs: 'vm'
});