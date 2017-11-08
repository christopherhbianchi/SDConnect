angular.module("appModule")
	.component("userconnect", {
		templateUrl: "app/appModule/userconnect/userconnect.component.html",
		controller: function(userService, postService, $filter, 
				$location, $routeParams, authService, profileService) {
			
			var vm = this;
			
			vm.profiles = [];
			vm.guestProfile = null;
			
			vm.viewProfile = false;
			
			vm.userType = function(){
			if(authService.getToken().type === 'admin'){
				return true;
			}
			return false;
			}
			
			var reload = function(){
				vm.guestProfile = null;
				userService.index().then(function(res){
					vm.profiles = res.data;
					vm.userType();
					console.log("Inside Reload");
					console.log(vm.userType());
				});
			}

			reload();
			
			vm.deleteProfile = function(profile){
				console.log("clicked");
				profileService.destroy(profile)
				.then(function(response){
					reload(); //after it's deleted, reload our data
				})
			}
						
			 var getAllUsers = function(){
				vm.guestProfile = null;
				userService.index()
				.then(function(resp){
					vm.profiles = resp.data;
				})
				.catch(function(error){
					console.log(error);
				});
			}

			getAllUsers();
			
			vm.gotoProfile = function(pid) {
				console.log("gotoProfile");
				profileService.getOneProfile(pid)
				.then(function(res){
					console.log(res.data);
					vm.guestProfile = res.data;
				})
				.catch(function(error){
					console.log(error);
				});
			}
			
			vm.gotoAllUsers = function(){
				getAllUsers();
			}
			
		},
		
		controllerAs: 'vm'
		
	});

