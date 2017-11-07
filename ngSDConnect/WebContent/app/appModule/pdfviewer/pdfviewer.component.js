angular.module('appModule')
	.component('pdfViewer', {
		
		templateUrl:"app/appModule/pdfviewer/pdfviewer.component.html",
		controller:function() {
			
			var vm = this;
		
			vm.pdf = {
					src: 'js/ngPDF/angular-pdfjs-viewer/demo/example.pdf', //url to where the pdf is saved
			};
		
		},
	    
		controllerAs:"vm"
	
	
	
	});