package data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Event;
import entities.Topic;

@Transactional
@Repository
public class EventDAOImpl implements EventDAO{
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Set<Event> showAll() {
		String query = "SELECT e FROM Event e";
		return new HashSet<>(em.createQuery(query).getResultList());
	}

	@Override
	public Set<Event> showEventsByCohortId(int cohortId) {
		String query = "SELECT e FROM "
		return null;
	}

	@Override
	public Set<Event> showEventsById(int eventId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic createEvent(int userId, String eventJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topic updateEvent(int userId, int eventId, String topicJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteEvent(int userId, int eventId) {
		// TODO Auto-generated method stub
		return null;
	}

}
