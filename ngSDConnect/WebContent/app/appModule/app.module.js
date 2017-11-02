angular.module("appModule", ['ngRoute', "ngCookies", "authModule"]) //<!--this is how you create a module; it is like a jar; it is the entire executable portion that encapsulates the functonality-->
.config(function($routeProvider){
	
	 $routeProvider
	    .when('/', {
	    	  template: '<home></home>'  //registration and login
	    })
	    .when('/welcome', {
	      template : '<welcome></welcome>'
	    })
	    .when('/profile', {
	    	template : '<profile></profile>'
	    })
	    .when('/alumni', {
	    	template : '<alumni></alumni>'
	    })
	 	.when("/resources", {
		template: "<resources></resources>"
	 	})
	 	.when("/logout", {
		template: "<logout></logout>"
	 	})
	    .otherwise({
	    template:  '<h1>404 - NOT FOUND </h1>'
	    })
});


//.when('/todos/:id', {
//template:"<todo-list></todo-list>"
//})
//.when('/todos/notfound', {
//template:  '<h1>404 - NOT FOUND </h1>'
//})