angular.module("appModule")
	.component("userconnect", {
		templateUrl: "app/appModule/userconnect/userconnect.component.html",
		controller: function(userService, postService, $filter, 
				$location, $routeParams, authService, profileService){
			
			var vm = this;
			
			vm.profiles = [];
			
			vm.viewProfile = false;
			

			if(authService.getToken().type === 1){
				vm.deleteProfile = function(profile){
					console.log("clicked");
					profileService.destroy(profile)
					.then(function(response){
						reload(); //after it's deleted, reload our data
					})
				}
			
			}
						
			 var getAllUsers = function(){
				userService.index()
				.then(function(resp){
					vm.profiles = resp.data;
				})
				.catch(function(error){
					console.log(error);
				});
			}

			getAllUsers();
			
		},
		
		controllerAs: 'vm'
		
	});

