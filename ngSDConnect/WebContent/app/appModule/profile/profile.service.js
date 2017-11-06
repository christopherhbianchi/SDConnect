angular.module('appModule')
	.factory('profileService', function($http, $filter, $location, authService){

	var profileService = {};
	
	var date = $filter('date')(Date.now(), 'MM/dd/yyyy'); 
	
	// public
	  profileService.index = function() {
		  var user = authService.getToken();
		  
		  return $http({
			method : "GET",
			url : "rest/users/"+ user.id+ "/profiles"
		});
		
	  }

	  profileService.create = function(profile) {
		  
		  var user = authService.getToken();
		  
		    return $http({
		      method : 'POST',
		      url : "rest/users/" + user.id + "/profiles",
		      headers : {
		        'Content-Type' : 'application/json'
		      },
		      data : profile
		    });
		  }

		profileService.update = function(profile) {
			
			 var user = authService.getToken();
			 
			return $http({
		      method : 'PUT',
		      url : "rest/users/" + user.id + "/profiles",
		      headers : {
		        'Content-Type' : 'application/json'
		      },
		      data : profile
		    });
		  }

		  profileService.getDate = function(){
			  return $filter('date')(Date.now(), 'MM/dd/yyyy');
		  }
		  return profileService;

	})