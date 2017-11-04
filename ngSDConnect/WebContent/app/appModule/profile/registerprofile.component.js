angular.module("authModule").component("registerprofile", {
	templateUrl : "app/appModule/profile/registerprofile.component.html",
	controller : function(authService, $filter, $location, $http, $routeParams, $cookies, profileService) {
		var vm = this; 
		
		vm.profile = {};
		
		vm.register = function(user) {
			console.log("inside register")
			console.log(user);
			console.log(authService.getToken().id);
			console.log(user.id);
			
			authService.register(user)
			.then(function(response){
				$location.path("/users/"+ authService.getToken().id +"/registerprofiles");
			})
		}
		
		vm.submit = function(profile){
			 profileService.create(profile)
			 .then(function(res){
				 vm.profile = res.data;
				 $location.path("/users/:id/profiles");
			 });
		}
	},
			controllerAs: "vm"
})

		
	
