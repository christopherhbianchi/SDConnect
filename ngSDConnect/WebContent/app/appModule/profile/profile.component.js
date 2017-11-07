angular.module("appModule").component("profile", { 
	templateUrl: "app/appModule/profile/profile.component.html",
		controller: function(profileService, $http, $filter,
				$cookies, authService, $rootScope) {
			
			var vm = this;
			
			vm.profile = {};
			
			var reload = function(){
				profileService.index().then(function(res){
					vm.profile = res.data;
				});
			}
			
			reload();
			
			vm.edit = null;
			
			vm.submit = function(profile){
				 profileService.update(profile)
				 .then(function(res){
					 vm.edit = false;
					 vm.profile = res.data;
					 reload();
				 });
			}
	
			
			
			vm.selected = null;
			
		},
	 controllerAs : "vm"
 }) 