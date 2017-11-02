package data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	public Topic createTopic(int userId, String topicJson) {
		ObjectMapper mapper = new ObjectMapper();
		
		User user = em.find(User.class, userId);
		
		if (user != null) {
			try {
				
			}
			catch (Exception e) {
				
			}
		}
		
		return null;
	}
	
//	@Override
//	public Todo create(int uid, String todoJson) {
//		
//		ObjectMapper mapper = new ObjectMapper();
//		User u = em.find(User.class, uid);
//		Todo todoCreate = null;
//		if (u != null) {
//			try {
//				todoCreate = mapper.readValue(todoJson, Todo.class);
//				todoCreate.setUser(u);
//				em.persist(todoCreate);
//				em.flush();
//				System.out.println(todoCreate.toString());
//				return todoCreate;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		return null;
//	}

	@Override
	public Topic updateTopic(int userId, int topicId, String topicJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteTopic(int userId, int todoId) {
		// TODO Auto-generated method stub
		return false;
	}

}


//
//	@Override
//	public Todo update(int uid, int tid, String todoJson) {
//		ObjectMapper mapper = new ObjectMapper();
//		Todo todoCurrent = em.find(Todo.class, tid);
//		Todo todoUpdate = null;
//		User u = em.find(User.class, uid);
//
//		if (u == todoCurrent.getUser()) {
//
//			try {
//				todoUpdate = mapper.readValue(todoJson, Todo.class);
////				todoUpdate.setUser(u);
//				todoCurrent.setCompleted(todoUpdate.getCompleted());
//				todoCurrent.setCompleteDate(todoUpdate.getCompleteDate());
//				todoCurrent.setCreatedAt(todoUpdate.getCreatedAt());
//				todoCurrent.setDescription(todoUpdate.getDescription());
//				todoCurrent.setDueDate(todoUpdate.getDueDate());
//				todoCurrent.setTask(todoUpdate.getTask());
//				todoCurrent.setUpdatedAt(todoUpdate.getUpdatedAt());
//				return todoCurrent;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public boolean destroy(int uid, int tid) {
//		Todo deleteTodo = null;
//
//		try {
//			deleteTodo = em.find(Todo.class, tid);
//			em.remove(deleteTodo);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//}
//
