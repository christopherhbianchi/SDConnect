angular.module("appModule")
	.component("userconnect", {
		templateUrl: "app/appModule/userconnect/userconnect.component.html",
		controller: function(userService, postService, $filter, $location, $routeParams, authService, profileService){
			
			var vm = this;
			
			vm.profiles = [];
						
			 var getAllUsers = function(){
				userService.index()
				.then(function(resp){
					console.log(resp.data);
					vm.profiles = resp.data;
				})
				.catch(function(error){
					console.log(error);
				});
			}

			getAllUsers();
			
			
//			vm.viewProfile = function(profile) {
//				console.log("Inside Show profile")
//				 profileService.index(profile)
//				 .then(function(res){
////					 $location.path("/users/" +authService.getToken().id+ "/profiles/"+profile.id);
//					console.log("Inside the response")
//					 vm.profiles = res.data;
//					 reload();
//				 });
//				 
//			
//			}
		
		},
		
		controllerAs: 'vm'
		
	});

