angular.module('appModule')
	.factory('profileService', function($http, $filter, $location, authService){

	var adminService = {};
	
	var date = $filter('date')(Date.now(), 'MM/dd/yyyy'); 
	
	// public
	  adminService.index = function() {
		  var admin = authService.getToken();
		  
		  return $http({
			method : "GET",
			url : "rest/admin/"+ admin.id,
		});
		
	  }

	  profileService.create = function(admin) {
		  
		  var user = authService.getToken();
		  
		    return $http({
		      method : 'POST',
		      url : "rest/admin/" + admin.id,
		      headers : {
		        'Content-Type' : 'application/json'
		      },
		      data : admin
		    });
		  }

		profileService.update = function(admin) {
			
			 var user = authService.getToken();
			 
			return $http({
		      method : 'PUT',
		      url : "rest/admin/" + admin.id,
		      headers : {
		        'Content-Type' : 'application/json'
		      },
		      data : admin
		    });
		  }

		  profileService.getDate = function(){
			  return $filter('date')(Date.now(), 'MM/dd/yyyy');
		  }
		  return adminService;

	})