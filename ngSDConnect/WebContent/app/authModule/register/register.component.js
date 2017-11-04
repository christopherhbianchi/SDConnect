angular.module("authModule").component("register", {
	templateUrl : "app/authModule/register/register.component.html",
	controller : function(authService, $filter, $location, $http, $routeParams, $cookies, registerService) {
		var vm = this;
		
		vm.login = 
		
		vm.register = function(user) {
			console.log("inside register")
			authService.register(user)
			.then(function(response){
				//registerService.create(response.data);
				$location.path("/users/"+ user.id +"/profiles");
			})
		}
	},
			
			controllerAs: "vm"
	})
		
	
