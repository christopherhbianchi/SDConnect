angular.module("appModule").component("profile", { 
	templateUrl: "app/appModule/profile/profile.component.html",
		controller: function(profileService, $http, $filter,
				$cookies, authService, $rootScope) {
			
			var vm = this;
			
			vm.profile = {};
			
			var reload = function(){
				profileService.index().then(function(response){
					vm.profile = response.data;
				});
			}
			reload();
			
			vm.edit = function(){
				 profileService.update(pid)
				 .then(function(res){
					 vm.selected = res.data;
					 vm.reload();
				 });
			}
			
		},
	 controllerAs : "vm"
 })
	
	