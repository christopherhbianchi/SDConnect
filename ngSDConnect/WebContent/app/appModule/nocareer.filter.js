angular.module('appModule')
	.filter('nocareer' , function(){
		return function(topicList){
			var filtered = [];
			var foundTag = false;
			for(var i = 0; i < topicList.length; i++) {
				tagList = topicList[i].tags;
				console.log(tagList);
				for(var j = 0; i < topicList.length; j++) {
					if(topicList[j].type == 'resume'
					 || topicList[j].type == 'cover letter'
					 || topicList[j].type == 'interview') {
						foundTag = true
					}
				}
				if(!foundTag) {
					filtered.push(topicList[i])
				}
				foundTag = false;
			}
			return filtered;
		}
});