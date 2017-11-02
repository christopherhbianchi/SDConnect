angular.module("appModule")
	.component("forum", {
		templateUrl: "app/appModule/forum/forum.component.html",
			
		controller: function(topicService, postService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.currentTopics = [];
			vm.currentPosts = [];
			
			vm.postView = false;
			
			var getAllTopics = function(){
				vm.postView = false;
				topicService.index()
				.then(function(resp){
					console.log(resp.data);
					vm.currentTopics = resp.data;
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
			getAllTopics();
			
			var getPostsPerTopic = function(tid) {
				vm.postView = true;
				postService.index(tid)
				.then(function(resp){
					console.log(resp.data);
					vm.currentPosts = resp.data;
				})
				.catch(function(error){
					console.log(error)
				})
			}
			
		},
		
		controllerAs: 'vm'
		
	});

