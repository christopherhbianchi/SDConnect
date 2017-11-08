angular.module('appModule')
	.component('pdfViewer', {
		
		templateUrl:"app/appModule/pdfviewer/pdfviewer.component.html",
		controller:function($routeParams) {
			
			var vm = this;
		
			vm.pdf = {
					src: 'img/resume/' + $routeParams.pdf, //url to where the pdf is saved
							//"js/ngPDF/angular-pdfjs-viewer/demo/example.pdf"
			};
		
		},
	    
		controllerAs:"vm"

			
	
	
	});