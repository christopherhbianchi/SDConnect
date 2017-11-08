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
			.when('/userconnect', {
				template:'<userconnect></userconnect>' //these are the snake case names of the comps
			})
			.when('/resources',{
				template:'<resource></resource>'
			})
			.when('/resources/resumes',{ // go from resources to resume when clicking the card
				template:'<resumes></resumes>'
			})
			.when('/resources/studentSites',{ // go from resources to resume when clicking the card
				template:'<student-sites></student-sites>'
			})
			.when('/resources/:pdf',{
				template:'<pdf-viewer><pdf-viewer>' //maybe  <resource></resource>
			})
			.when('/users/:id/profiles',{
				template:'<profile></profile>'
			})
			.when('/profile',{
				template:'<profile></profile>'
			})
			.when('/users/:id/registerprofiles',{
				template:'<registerprofile></registerprofile>'
			})
			.otherwise({
				template:'<navigation></navigation><h1>404 - NOT FOUND </h1>'
			})	
		
	})