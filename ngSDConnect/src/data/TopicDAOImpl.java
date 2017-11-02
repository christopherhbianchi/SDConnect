package data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		Topic t = em.find(Topic.class, topicId);
		return null;
	}

	@Override
	public Topic createTopic(int userId, String topicJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic updateTopic(int userId, int topicId, String topicJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteTopic(int userId, int todoId) {
		// TODO Auto-generated method stub
		return null;
	}

}
