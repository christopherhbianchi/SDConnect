angular.module("authModule").component("registerprofile", {
	templateUrl : "app/appModule/profile/registerprofile.component.html",
	controller : function(authService, $filter, $location, $http, $routeParams, $cookies, profileService) {
		var vm = this; 
		
		vm.profile = {};
		
		vm.nameCheck = false; //variable used to check if user wrote their 
							//first name and last name prior to viewing the homepage
		
		vm.register = function(user) {
			console.log("inside register")			
			authService.register(user)
			.then(function(response){
				$location.path("/users/"+ authService.getToken().id +"/registerprofiles");
				profileService.index().then(function(res){
					vm.profile = res.data;
				})//end of profileService index function
			})
		}
		
		vm.submit = function(profile){
			
			if(profile.fname && profile.lname) {
			 profileService.create(profile)
			 .then(function(res){
				 vm.profile = res.data;
				 $location.path("/profile");
			 })
			}
			else {
				vm.nameCheck = true;
			}
		}
	},
			controllerAs: "vm"
})
