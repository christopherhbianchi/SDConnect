package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Tag;
import entities.Topic;

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
						
			if (editedTopic.getName() != null) {
				oldTopic.setName(editedTopic.getName());
			}
			
//			if(editedTopic.getPosts() != null || editedTopic.getPosts().size() > 0) {
//				System.out.println("IN SET TOPIC");
//				
//				oldTopic.setPosts(editedTopic.getPosts());
//			}
//			if (editedTopic.getTags() ! = null) {
//			oldTopic.setTags(editedTopic.getTags());
//			}
			
			return oldTopic;
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
			
			String query = "DELETE FROM Topic t WHERE t.id= :topicId";
			em.createQuery(query).setParameter("topicId", topicId).executeUpdate();
		
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Set<Topic> getCareerResources(String word) {
		String queryString = "Select t from Tag t where t.id=:id";
		int tid = 0;
		switch(word) {
			case "resume":
				tid = 3;
				break;
			case "cover":
				tid = 4;
				break;
			case "interview":
				tid = 5;
				break;
			default:
				break;
		}
		System.out.println(tid);
		List<Tag> tempList = em.createQuery(queryString, Tag.class)
							  .setParameter("id", tid)
							  .getResultList();
		
		if(tempList.size() > 0) {
			Tag tag = tempList.get(0);
			List<Topic> topicList = tag.getTopics();
			Set<Topic> topicSet = new HashSet(topicList);
			return topicSet;
		}
		Set<Topic> topicSetDefault = new HashSet<>();
		topicSetDefault.add(showTopicById(1));
		return topicSetDefault;
	}


	
}
