angular.module("appModule")
	.component("forum", {
		templateUrl: "app/appModule/forum/forum.component.html",
			
		controller: function(topicService, postService, tagService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.currentTopics = [];
			vm.currentPosts = [];
			vm.allTags = [];
			
			var currentUserToken = postService.returnUser();
			
			vm.currentTid = null;
			
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
			
			var getAllTags = function(){
				tagService.index()
				.then(function(resp){
					vm.allTags = resp.data;
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
			getAllTags();
			
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
				vm.currentTid = tid;
				setEverythingToNull();
				vm.postView = true;
				postService.index(tid)
				.then(function(resp){
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
				topicService.destroy(tid)
				.then(function(res){
					setEverythingToNull();
					getAllTopics();
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
			vm.createTopic = function(topic){
				delete topic.tag.topics;
				topicService.create(topic)
				.then(function(res){
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
				postService.update(post)
				.then(function(res){
					setEverythingToNull();
					vm.postView = true;
					vm.getPostsPerTopic(vm.currentTid);
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
			vm.deletePost = function(pid){
				postService.destroy(pid)
				.then(function(res){
					setEverythingToNull();
					vm.postView = true;
					vm.getPostsPerTopic(vm.currentTid);
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
			vm.createPost = function(post){
				console.log("entering createPost");
				console.log("Tid: " + vm.currentTid);
				postService.create(post, vm.currentTid)
				.then(function(res){
					setEverythingToNull();
					vm.postView = true;
					vm.getPostsPerTopic(vm.currentTid);
				})
				.catch(function(error){
					console.log(error);
				});
			};
			
		},
		
		controllerAs: 'vm'
		
	});

