package data;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
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
//			if(newTopic.getPosts().size() == 0) {
//				List<Post> posts = new ArrayList<>();
//				newTopic.setPosts(posts);
//			}
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
	public Set<Topic> getByTagKeyword(String word) {
		System.out.println("in dao before");
		String queryString = "Select t from Tag t where t.type=:name";
		List<Topic> topicList = null;
		List<Tag> tagList = em.createQuery(queryString, Tag.class)
							   .setParameter("name", word)
							   .getResultList();
		if(tagList.size() > 0) {
			Tag tag = tagList.get(0);
			topicList = tag.getTopics();
			System.out.println("*********Topics: " + topicList);
			Set<Topic> topicSet = new HashSet<>(topicList);
			System.out.println("in dao after in if");
			return topicSet;
		}
		System.out.println("in dao after");
		return null;
	}

	@Override
	public Set<Topic> getNonCareerTopics() {
		Set<Topic> topics = showAll();
		Set<Topic> answer = new HashSet<>();
		for(Topic t : topics) {
			String type = t.getTag().getType();
			if ( ! (type.equals("Resume") || type.equals("Interview") || type.equals("CoverLetter") ) ) {
//				System.out.println("************** Adding non-career topic **********************************");
				answer.add(t);
			}
		}
		return answer;
	}

}
