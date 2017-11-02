package data;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Topic;
import entities.User;

@Transactional
@Repository
public class TopicDAOImpl implements TopicDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Set<Topic> showAll() {
		String query = "SELECT t FROM Topic t";
		Set<Topic> topics = new HashSet<>(em.createQuery(query).getResultList());
		return topics;
	}
	
	@Override
	public Topic showTopicById(int topicId) {
		Topic topicById = em.find(Topic.class, topicId);
		return topicById;
	}

	@Override
	public Topic createTopic(String topicJson) {
		ObjectMapper mapper = new ObjectMapper();
		
		Topic newTopic = null;
		
		try {
			newTopic = mapper.readValue(topicJson, Topic.class);
			em.persist(newTopic);
			em.flush();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return newTopic;
	}


	@Override
	public Topic updateTopic(int topicId, String topicJson) {
		ObjectMapper mapper = new ObjectMapper();
		
		Topic oldTopic = em.find(Topic.class, topicId);
		Topic editedTopic = null;
		
		try {
			editedTopic = mapper.readValue(topicJson, Topic.class);
			oldTopic.setName(editedTopic.getName());
			oldTopic.setPosts(editedTopic.getPosts());
			oldTopic.setTags(editedTopic.getTags());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public boolean deleteTopic(int topicId) {
		Topic deleteTopic = null;
		
		try {
			deleteTopic = em.find(Topic.class, topicId);
			em.remove(deleteTopic);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
