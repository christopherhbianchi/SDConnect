angular.module('pdfjsViewer')
	.component('pdfviewer', {
		
		templateUrl:"", //doesn't even need a url
		controller:function($scope) {
			
			var vm = this;
		
			$scope.pdf = {
					src: 'example.pdf',
			};
		
		},
	    
		controllerAs:"vm"
	
	
	
	});