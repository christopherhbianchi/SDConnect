angular.module('appModule')
	.filter('nocareer' , function(){
		return function(topicList){
			var filtered = [];
			var foundTag = false;
			for(var i = 0; i < topicList.length; i++) {
				tagList = topicList.getTags();
				for(var j = 0; i < topicList.length; j++) {
					if(topicList[j].getType() == 'resume'
					 || topicList[j].getType() == 'cover letter'
					 || topicList[j].getType() == 'interview') {
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