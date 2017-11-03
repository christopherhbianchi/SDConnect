angular.module('appModule')
	.factory('profileService', function($http, $filter, $location, authService){


var profileService = {};
	
	var date = $filter('date')(Date.now(), 'MM/dd/yyyy'); 
	
	// public
	  profileService.index = function() {
		var userId = authService.getToken().id;
		var profileId = authService.getToken().id;  0
		  
		  return $http({
			method : "GET",
		//	url : "rest/users/"+ uid +"/profiles" + pid,
			url : "rest/users/1/profiles"
		});
	  }

	  profileService.create = function(uid) {
		    return $http({
		      method : 'POST',
		      url : "rest/users/" + uid + "/profiles",
		      headers : {
		        'Content-Type' : 'application/json'
		      },
		      data : profile
		    });
		  }

		profileService.update = function(uid,pid) {
			return $http({
		      method : 'PUT',
		      url : "rest/users/" + uid + "/profiles",
		      headers : {
		        'Content-Type' : 'application/json'
		      },
		      data : profile
		    });
		  }

		profileService.destroy = function(uid, pid) {
		   return $http({
		      method : 'DELETE',
		      url : "rest/users/" + uid + "/profiles",
		    });
		  }
		  
		  profileService.getDate = function(){
			  
			  return $filter('date')(Date.now(), 'MM/dd/yyyy');
			  
		  }
	
	
	})


