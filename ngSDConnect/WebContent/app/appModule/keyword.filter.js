angular.module('appModule')
	.filter('keyword' , function(){
		return function(topicList, keyword){
			var filtered = [];
			var foundKey = false;
			for(var i = 0; i < topicList.length; i++) {
				tagList = topicList[i].tags;
				for(var j = 0; i < topicList.length; j++) {
					if(topicList[j].type == keyword)
					foundTag = true;
				}
				if(foundTag) {
					filtered.push(topicList[i])
				}
				foundTag = false;
			}
			return filtered;
		}
});