angular.module("authModule").component("logout", {
	templateUrl : "app/authModule/logout.component.html",
	controller : function(authService, $filter, $location, $http, $routeParams, $cookies) {
		var vm = this;
		
		vm.logout = function() {
			console.log("inside logout")
		authService.logout()
		.then(function(response){
			$location.path("/login");
		})
		}
	},
	controllerAs : "vm"
})