angular.module("appModule")
	.component("forum", {
		templateUrl: "app/appModule/forum/forum.component.html",
			
		controller: function(topicService, postService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.currentTopics = [];
			vm.currentPosts = [];
			
			
		},
		
		controllerAs: 'vm'
		
	});

