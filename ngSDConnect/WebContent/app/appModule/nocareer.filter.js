angular.module('appModule')
	.filter('nocareer' , function(){
		return function(topicList){
			var filtered = [];
			var foundTag = false;
			var resumeList = topicService.careerResource('resume');
			var interviewList = topicService.careerResource('interview');
			var coverList = topicService.careerResource('cover');
			var careerList = resumeList.concat(interviewList).concat(coverList);
			console.log(careerList);
			for(var i=0; i<topicList.length; i++) {
				for(var j=0; j<careerList.length; j++) {
					if (careerList[j].name == topicList[i].name) {
						foundKey = true;
					}
				}
				if(!foundKey) {
					filtered.push(topicList[i])
				}
			}
			return filtered;
		}
});