package data;

import java.util.Set;

import entities.Tag;
import entities.Topic;

public interface TopicDAO {
	
	//read all
	public Set<Topic> showAll(); //does not need id to show all topics
	//read 1
	public Topic showTopicById(int topicId); //all users can see every topic, no need for id
	//create
	public Topic createTopic(String topicJson);
	//update
	public Topic updateTopic(int topicId, String topicJson);
	//delete
	public boolean deleteTopic(int topicId);
	
	public Set<Topic> getCareerResources(String word);
	
}
