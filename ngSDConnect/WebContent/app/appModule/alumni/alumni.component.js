angular.module("appModule")
	.component("alumni", {
		templateUrl: "app/appModule/alumni/alumni.component.html",
		controller: function(alumniService, postService, $filter, $location, $routeParams){
			
			var vm = this;
			
			vm.alumnus = [];
						
			 var getAllAlumnus = function(){
				alumniService.index()
				.then(function(resp){
					console.log(resp.data);
					vm.alumnus = resp.data;
				})
				.catch(function(error){
					console.log(error);
				});
			}
			
			
			getAllAlumnus();
	
		},
		
		controllerAs: 'vm'
		
	});

