package controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.TopicDAO;
import entities.Topic;

@RestController
public class TopicController {

	@Autowired
	private TopicDAO topic; 
	
	@RequestMapping(path="pingTopics", method=RequestMethod.GET)
	public String pingTopic() {
		return "pong";
	}
	
//  GET /topics/{id}
	@RequestMapping(path="topics", method=RequestMethod.GET)
	public Set<Topic> index() {
		return topic.showAll();
	}

//  GET /topics/{id}
	@RequestMapping(path="topics/{id}", method=RequestMethod.GET)
	public Topic show(@PathVariable int tid) {
		return topic.showTopicById(tid);
	}

//  POST /topics/{id}
	@RequestMapping(path="topics", method=RequestMethod.POST)
	public Topic create(@RequestBody String topicJson) {
		// TODO Auto-generated method stub
		return topic.createTopic(topicJson);
	}

//  PUT /topics/{id}
	@RequestMapping(path="topics/{id}", method=RequestMethod.PUT)
	public Topic update(@PathVariable int id, @RequestBody String topicJson) {
		// TODO Auto-generated method stub
		return topic.updateTopic(id, topicJson);
	}


//  DELETE /topics/{id}
	@RequestMapping(path="topics/{id}", method=RequestMethod.DELETE)
	public boolean destroy(@PathVariable int id) {
		// TODO Auto-generated method stub
		return topic.deleteTopic(id);
	}
	
	@RequestMapping(path="topics/tags/{word}", method=RequestMethod.GET)
	public Set<Topic> getCareerResource(@PathVariable String word) {
		return topic.getCareerResources(word);
	}

	
}
