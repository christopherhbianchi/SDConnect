angular.module('appModule')
	.filter('keyword' , function(topicService){
//		Note: only works for career keywords
		return function(topicList, keyword){
			var filtered = [];
			var foundKey = false;
			var careerList = topicService.careerResource(keyword);
			for(var i=0; i<topicList.length; i++) {
				for(var j=0; j<careerList.length; j++) {
					if (careerList[j].name == topicList[i].name) {
						foundKey = true;
					}
				}
				if(foundKey) {
					filtered.push(topicList[i])
				}
			}
			return filtered;
		}
});