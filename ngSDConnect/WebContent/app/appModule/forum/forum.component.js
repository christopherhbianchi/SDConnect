angular.module("appModule")
	.component("forum", {
		templateUrl: "app/appModule/forum/forum.component.html",
			
		controller: function(topicService, postService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.currentTopics = [];
			vm.currentPosts = [];
			
			vm.postView = false;
			
			var getAllTopics = function(){
				topicService.index()
				.then(function(resp){
					vm.currentTopics = resp.data;
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
			getAllTopics();
			
			vm.getTopicByTagKeyword = function(word){
				topicService.getTopicByTagKeyword(word)
				.then(function(resp){
					vm.currentTopics = resp.data;
				})
				.catch(function(error){
					console.log(error)
				});
			};
			
			vm.getPostsPerTopic = function(tid) {
				console.log('In getPostsPerTopic');
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
			
			vm.displayTopics = function(){
				vm.currentPosts = [];
				vm.postView = false;
				getAllTopics();
			};
			
		},
		
		controllerAs: 'vm'
		
	});

