angular.module("appModule")
	.component("navigation", {
	
		templateUrl:"app/appModule/navigation/navigation.component.html",
		controller:function(authService){
			
			var vm = this;
			
			vm.isLoggedInCookie = function(){
				if(authService.getToken().id){
					return true;
				}
				return false;
			}
			
		},
		controllerAs:"vm"		
	});