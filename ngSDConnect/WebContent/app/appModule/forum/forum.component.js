angular.module("appModule")
	.component("forum", {
		templateUrl: "app/appModule/forum/forum.component.html",
			
		controller: function(topicService, postService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.currentTopics = [];
			vm.currentPosts = [];
			
			var currentUserToken = postService.returnUser();
			
			var setEverythingToNull = function(){
				vm.postView = false;
				vm.postSelected = null;
				vm.topicSelected = null;
				vm.newTopic = false;
				vm.newPost = false;
			};
			
			setEverythingToNull();
			
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
				console.log(tid);
				setEverythingToNull();
				vm.postView = true;
				postService.index(tid)
				.then(function(resp){
					console.log(resp.data);
					vm.currentPosts = resp.data;
				})
				.catch(function(error){
					console.log(error)
				})
			};
			
			vm.displayTopics = function(){
				vm.currentPosts = [];
				setEverythingToNull();
				getAllTopics();
			};
			
			vm.currentAdmin = function() {
				return currentUserToken.type == 'admin';
			};
			
			vm.currentAuthorPost = function(post) {
				var uid = currentUserToken.id;
				var compare = post.user.id;
				return uid == compare;
			};
			
			vm.editTopic = function(topic) {
				setEverythingToNull();
				vm.topicSelected = topic;
			};
			
			vm.updateTopic = function(topic){
				topicService.update(topic);
				setEverythingToNull();
			};
			
			vm.deleteTopic = function(tid){
				topicService.destroy(tid);
				setEverythingToNull();
			};
			
			vm.createTopic = function(topic){
				console.log("entering create topic");
				topicService.create(topic)
				.then(function(res){
					console.log(res.data);
					setEverythingToNull();
					getAllTopics();
				})
				.catch(function(error){
					console.log(error);
				});
				
			};
			
			vm.editPost = function(post){
				setEverythingToNull();
				vm.postSelected = post;
			};
			
			vm.updatePost = function(post){
				postService.update(post);
				setEverythingToNull();
			};
			
			vm.deletePost = function(pid){
				postService.destroy(pid);
				setEverythingToNull();
			};
			
			vm.createPost = function(post){
				postService.create(post);
				setEverythingToNull();
			};
			
		},
		
		controllerAs: 'vm'
		
	});

