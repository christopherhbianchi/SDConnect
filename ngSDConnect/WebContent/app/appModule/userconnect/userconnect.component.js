angular.module("appModule")
	.component("userconnect", {
		templateUrl: "app/appModule/userconnect/userconnect.component.html",
		controller: function(userService, postService, $filter, $location, $routeParams, authService){
			
			var vm = this;
			
			vm.profiles = [];
			
			vm.viewProfile = false;
						
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

