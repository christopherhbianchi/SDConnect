angular.module("appModule").component("administator", { 
	templateUrl: "app/appModule/administrator/administratpr.component.html",
		controller: function(profileService, $http, $filter,
				$cookies, authService, $rootScope) {
			
			var vm = this;
			
			vm.admin = {};
			
			var reload = function(){
				adminService.index().then(function(res){
					vm.admin = res.data;
				});
			}
			reload();
			
			vm.edit = null;
			
			vm.submit = function(admin){
				 adminService.update(admin)
				 .then(function(res){
					 vm.edit = false;
					 vm.admin = res.data;
					 reload();
				 });
			}
			
			vm.selected = null;
			
		},
	 controllerAs : "vm"
 }) 