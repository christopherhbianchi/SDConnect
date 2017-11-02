angular.module("authModule").component("register", {
	templateUrl : "app/authModule/register.component.html",
	controller : function(authService, $filter, $location, $http, $routeParams, $cookies) {
		var vm = this;
		
		vm.register = function(user) {
			console.log("inside register")
		authService.register(user)
		.then(function(response){
			$location.path("/");
			})
		}
	
		
	},
			
			controllerAs: "vm"
	})
		