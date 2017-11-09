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
	  
	  profileService.getOneProfile = function(pid) {
		  return $http({
				method : "GET",
				url : "rest/profiles/" + pid
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
			 console.log("Inside profileService update!")
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
		  
		  
		  profileService.destroy = function(profile) {
			  var user = authService.getToken();
			  console.log("clicked");
					return $http({
						method : 'DELETE',
						url : 'rest/users/' + user.id + '/profiles/' + profile.id,
					})
				
			}
		  
		  profileService.checkEmailDuplication = function(userEmail) {			  
			  return $http({
				method : "POST",
				url : "rest/users/search",
				headers : { 
					"Content-type":"application/json"
				},      //request body is a string thats a user email(controller), change to post
				data : userEmail //dao is fine, controller needs to take the post
			}); 
		  }
		  
		  return profileService;

	})