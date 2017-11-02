package data;

import java.util.Set;

import entities.Topic;

public interface TopicDAO {
	
	//read all
	public Set<Topic> showAll(); //does not need id to show all topics
	//read 1
	public Topic showTopicById(int topicId); //all users can see every topic, no need for id
	//create
	public Topic createTopic(int userId, String topicJson);
	//update
	public Topic updateTopic(int userId, int topicId, String topicJson);
	//delete
	public Boolean deleteTopic(int userId, int todoId);
}
