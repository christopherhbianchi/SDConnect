package data;

import java.util.Set;

import entities.Topic;

public interface TopicDAO {
	
	//read all
	public Set<Topic> showAll(int userId);
	//read 1
	public Topic showTopicById(int userId, int topicId);
	//create
	public Topic createTopic(int userId, String topicJson);
	//update
	public Topic updateTopic(int userId, int topicId, String topicJson);
	//delete
	public Boolean deleteTopic(int userId, int todoId);
}
