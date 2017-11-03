angular.module("appModule").factory("alumniService",
	function($http, $filter, authService, $rootScope) {

			var alumniService = {};

			var alumnus = [];

			alumniService.index = function() {
					return $http({
						method : 'GET',
						url : "rest/profile"
					})
				}

			return alumniService;

})// end of function
			