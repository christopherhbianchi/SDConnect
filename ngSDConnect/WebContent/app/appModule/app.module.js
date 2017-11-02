
angular.module("appModule", ['ngRoute', 'ngCookies', 'authModule'])
	.config(function($routeProvider){
	
		$routeProvider
			.when('/', {
				template:'<home></home>' //these are the snake case names of the comps
			})
			.when('/alumni', {
				template:'<alumni></alumni>' //these are the snake case names of the comps
			})
			.when('/login',{
				template:'<login></login>'
			})
			.when('/resources',{
				template:'<resources></resources>'
			})
			.when('/profile',{
				template:'<profile></profile>'
			})
		
		
		
		
		
		
		
		
	})
=======
angular.module("appModule", ['ngRoute', "ngCookies", "authModule"]) //<!--this is how you create a module; it is like a jar; it is the entire executable portion that encapsulates the functonality-->
.config(function($routeProvider){
	
	 $routeProvider
	    .when('/', {
	    	  template: '<login></login>'  //registration and login
	    })
	    .when('/home', {
	      template : '<home></home>'
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
>>>>>>> 4975c16204485c65ff63fcb9883f037e0d93f7bf
