angular.module('authModule')
  .factory('authService', function($http, $cookies) {
    var service = {};
    
    var saveToken = function(user) {
      // TODO : Store the user's id and email in cookies
	    	$cookies.put("id", user.id);
	    	$cookies.put("email", user.email);
    }

    service.getToken = function() {
      // TODO : Return an object with id and email properties,
      // the values are the values of the cookies
   
    	var user = {
    		"id" : $cookies.get("id"),
    		"email" : $cookies.get("email")
    		}
    	
    return	user;
    	
    }

    var removeToken = function() {
      // TODO : Remove both the id and email cookies
    	$cookies.remove("id");
    	$cookies.remove("email");
    	
    }

    service.login = function(user) {
      // TODO : Use the auth/login route to authenticate the user
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
    		//some code
    		service.getToken();
    		saveToken(res.data);
    		return res;
    		
    	});
    	
    	
    }

    service.register = function(user) {
      // TODO : Use the auth/register route to create and authenticate the user
      // On success, use saveToken to store the users id/email
    	
      	return $http({
    		method:"POST", 
    		url: "rest/auth/register",
    		headers: {
    			"Content-Type" : "application/json"
    		},
    		data: user
    	})
    	.then(function(res){
    		//some code
    		saveToken();
    		return res;
    		
    	});
    }

    service.logout = function() {
      // TODO : Use the auth/logout route to remove the users session
      // On success, use removeToken to remove the id and email cookies
    	
      	return $http({
    		method:"POST", 
    		url: "rest/auth/logout"
      	})
    	.then(function(res){
    		//some code
    		removeToken();
//    		return res;
 
    	});
    }

    return service;
  })