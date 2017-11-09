angular.module("authModule").component("register", {
	templateUrl : "app/authModule/register/register.component.html",
	controller : function(authService, $filter, $location, $http, $routeParams, $cookies, userService, profileService) {
		var vm = this; 
		
		
		//in the vm.register, check to see if there is an email that already exists. 
		//if so, create an alert to the customer
		
		vm.dupEmail = false;
		
		vm.register = function(user) {
			console.log("inside register")
			profileService.checkEmailDuplication(user.email).then(function(response){
				if (!response.data) {
					console.log(user)
					authService.register(user)
					.then(function(response){
					$location.path("/users/"+ authService.getToken().id+"/registerprofiles");
					}) //end of then statement
				} //end of if statement
				else {
//					alert('That email is already registered. Please type another email.')
					return vm.dupEmail = true;
				}//end of else
			})
			.catch(function(err){
				console.log(err)
			})
		
			} //end of vm.register function
		},
			
			controllerAs: "vm"
	})
	


