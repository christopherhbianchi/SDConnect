angular.module('authModule', ["ngCookies", "ngRoute"])
.config(function($routeProvider){
	
	$routeProvider
	.when("/register", {
		template: "<register></register>"
	})
	
	.when("/login", {
		template: "<login></login>"
	})
	
	.when("/logout", {
		template: "<logout></logout>"
	})
	
	.otherwise ({
		template: "<h1>404 BOZO</h1>"
	})
	
})
