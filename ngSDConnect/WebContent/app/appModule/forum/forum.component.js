angular.module("appModule")
	.component("forum", {
		templateUrl: "app/appModule/forum/forum.component.html",
			
		controller: function(topicService, postService, $filter, $location, $routeParams){
			var vm = this;
			
			vm.currentTopics = [];
			vm.currentPosts = [];
			
			vm.postView = false;
			vm.postSelected = null;
			vm.topicSelected = null;
			
			var currentUserToken = postService.returnUser();
			
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
				vm.postView = true;
				vm.postSelected = null;
				vm.topicSelected = null;
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
				vm.postSelected = null;
				vm.topicSelected = null;
				getAllTopics();
			};
			
			vm.currentAdmin = function() {
				return currentUserToken.type == 'admin';
			}
			
			vm.currentAuthorPost = function(post) {
				var uid = currentUserToken.id;
				var compare = post.user.id;
				return uid == compare;
			}
			
			vm.editTopic = function(topic) {
				vm.postView = false;
				vm.postSelected = null;
				vm.topicSelected = topic;
			}
			
			vm.updateTopic = function(topic){
				vm.postView = false;
				vm.postSelected = null;
				vm.topicSelected = null;
				console.log("in updateTopic");
				console.log(topic);
			};
			
			vm.deleteTopic = function(tid){
				vm.postView = false;
				vm.postSelected = null;
				vm.topicSelected = null;
				console.log("in deleteTopic");
				console.log(tid);
			};
			
			vm.editPost = function(post) {
				vm.postView = false;
				vm.postSelected = post;
				vm.topicSelected = null;
			}
			
			vm.updatePost = function(post){
				vm.postView = false;
				vm.postSelected = null;
				vm.topicSelected = null;
				console.log("in updatePost");
				console.log(post);
			};
			
			vm.deletePost = function(pid){
				vm.postView = false;
				vm.postSelected = null;
				vm.topicSelected = null;
				console.log("in deletePost");
				console.log(pid);
			};
			
		},
		
		controllerAs: 'vm'
		
	});

