angular.module('authModule')
  .factory('authService', function($http, $cookies) {
    var service = {};
    
    var admin = false;
    
    var saveToken = function(user) {
      // Store the user's id and email in cookies
	    	$cookies.put("id", user.id);
	    	$cookies.put("email", user.email);
	    	$cookies.put("type", user.type.type);
    }
    
    
    service.getToken = function() {
      // Return an object with id and email properties,
      // the values are the values of the cookies
	    	var user = {
	    		"id" : $cookies.get("id"),
	    		"email" : $cookies.get("email"),
	    		"type" : $cookies.get("type")
	    		}
	    	
	    	console.log($cookies.get("id"));
	    	console.log($cookies.get("email"));
	    	console.log($cookies.get("type"));
	    	return	user;
    	
    }

    var removeToken = function() {
      //Remove both the id and email cookies
	    	$cookies.remove("id");
	    	$cookies.remove("email");
	    	$cookies.remove("type");
    }

    service.login = function(user) {
      // Use the auth/login route to authenticate the user
      // On success, use saveToken to store the users id/email
    		
    	return $http({
    		method:"POST", 

    		url: "rest/auth/login",
    		headers: {
    			"Content-Type" : "application/json"
    		},
    		data: user
   		})
   		.then(function(res){
   			saveToken(res.data);
   			console.log(res.data);
   			return res;
   		});
    	
    	
    }

    service.register = function(user) {
      // Use the auth/register route to create and authenticate the user
      // On success, use saveToken to store the users id/email
    	console.log(user);
      	return $http({
	    		method:"POST", 
	    		url: "rest/auth/register",
	    		headers: {
	    			"Content-Type" : "application/json"
	    		},
	    		data: user
		    	})
		    	.then(function(res){
		    		saveToken(res.data);
		    		return res;
		    		
	    	});
	}

    service.logout = function() {
      // Use the auth/logout route to remove the users session
      // On success, use removeToken to remove the id and email cookies
    	
      	return $http({
    		method:"POST", 
    		url: "rest/auth/logout"
      	})
	    	.then(function(res){
	    		removeToken();
	    		return res;
	 
	    	});
    	}

    return service;
  })