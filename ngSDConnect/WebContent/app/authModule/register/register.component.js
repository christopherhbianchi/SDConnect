angular.module("authModule").component("register", {
	templateUrl : "app/authModule/register/register.component.html",
	controller : function(authService, $filter, $location, $http, $routeParams, $cookies, registerService) {
		var vm = this;
		
		vm.login = 
		
		vm.register = function(user, profile) {
			
			console.log("inside register")
			
			authService.register(user)
			
			.then(function(response){
				
			registerService.create(profile);
			
			$location.path("/home");
			
			})
		}
	},
			
			controllerAs: "vm"
	})
		
	
	
//angular.module("appModule").component("sdconnect", {
//	templateUrl : "app/appModule/todoList.component.html",
//	controller : function(todoService, $filter, $location, $http, $routeParams, $cookies, $scope) {
//		var vm = this;
//		
//		vm.selected = null;
//
//		vm.showComplete = false;
//
//		vm.todos = [];
//		
//		$scope.$on('todoCreate', function(e, todo) {
//			console.log(todo);
//		})
//
//		var incompleteTasks = $filter("incomplete");
//		
//		if (!vm.todo && parseInt($routeParams.id)) {
//		    $http({
//		        method : 'GET',
//		        url : 'rest/users/1/todo/' + $routeParams.id
//		      })
//		      .then(function(res) {
//		        vm.selected = res.data;
//		    
//		      })
//		      .catch(function(err) {
//		        $location.path('/notfound')
//		      })
//		}
//		     
//		vm.monitor = function(products) {
//			if (products >= 10) {
//				return 'danger';
//			}
//			if (products < 10 & products >= 5) {
//				return 'warning';
//			}
//			if (products < 5) {
//				return 'safe';
//			}
//		}
//
//		vm.grayOut = function(todo) {
//			if (todo) {
//				return 'strikeout';
//			}
//			return "";
//		}
//
//		todoService.index().then(function(response) {
//			vm.todos = response.data;
//		})
//
//		var reload = function() {
//			todoService.index().then(function(response) {
//				vm.todos = response.data;
//			})
//		}
//
//		vm.persistTodo = function(todo) {
//			todoService.update(todo).then(function(response) {
//				reload();
//				//$location.path("/");
//			})
//		}
//
//		vm.addToDo = function(todo) {
//
//			if (todo.completed) {
//				todo.completeDate = $filter('date')(Date.now())// ,
//																// 'MM/dd/yyyy'
//				todo.completeDate.toString();
//			} else {
//				todo.completeDate == "";
//			}
//			var copy = angular.copy(todo);
//			todoService.create(copy)
//			.then(function(response) {
//				reload();
//				//$location.path("/");
//			})
//		}
//
//		vm.updateTodo = function(todo) {
//			todoService.update(todo).then(function(response) {
//				reload();
//				vm.selected = todo;
//				vm.editTodo = null;
//			})
//		}
//
//		vm.deleteTodo = function(id) {
//	
//				todoService.destroy(id).then(function(response) {
//				reload();
//				//$location.path("/");	
//				})
//		}
//
//		vm.getProducts = function() {
//			var numIncomplete = incompleteTasks(vm.todos).length
//			return numIncomplete;
//		}
//
//		vm.displayToDo = function(todos) {
//			vm.selected = todos;
//			return vm.selected;
//		}
//
//		vm.editTodo = null;
//
//		vm.setEditTodo = function() {
//			vm.editTodo = angular.copy(vm.selected);
//		}
//
//		vm.displayTable = function() {
//			vm.selected = null;
//		}
//
//		vm.cancelTodo = function() {
//			vm.editTodo = null;
//		}
//
//	},
//
//	controllerAs : "vm"
//
//})	