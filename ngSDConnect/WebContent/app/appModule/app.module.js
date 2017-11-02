
angular.module("appModule", ['ngRoute', 'ngCookies', 'authModule'])
	.config(function($routeProvider){
	
		$routeProvider
			.when('/login',{
			template:'<login></login>'
			})
			.when('/', {
				template:'<login></login>' //these are the snake case names of the comps
			})
			.when('/home', {
				template:'<home></home>' //these are the snake case names of the comps
			})
			.when('/alumni', {
				template:'<alumni></alumni>' //these are the snake case names of the comps
			})
			.when('/resources',{
				template:'<resources></resources>'
			})
			.when('/profile',{
				template:'<profile></profile>'
			})
			.otherwise({
				template:'<h1>404 - NOT FOUND </h1>'
			})	
		
	})
