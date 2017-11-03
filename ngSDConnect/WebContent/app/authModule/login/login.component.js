angular.module("authModule").component("login", {
	templateUrl : "app/authModule/login/login.component.html",
	controller : function(authService, $filter, $location, $http, $routeParams, $cookies) {
		var vm = this;
		
		vm.login = function(user) {
			console.log("inside login")
			authService.login(user)
			.then(function(response){
				
			$location.path("/home");
			
			})
		}
		
	},
	controllerAs : "vm"
})