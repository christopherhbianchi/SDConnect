angular.module("appModule")
	.factory("resourceService",function($http, $filter, $rootScope){
		
		var service = {}
		
		service.index = function(){
			
			return $http({
			
				method:"GET",
				url:""
				
			})
		}
		
		
		
		
	})