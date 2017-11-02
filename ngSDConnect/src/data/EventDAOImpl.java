package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Cohort;
import entities.Event;
import entities.User;

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
		String query = "SELECT e FROM Event e";
		List<Event> events = em.createQuery(query).getResultList();
		List<Event> filteredEvents = new ArrayList<>();
		for(Event e : events) {
			for(Cohort c : e.getCohortList()) {
				if(c.getId() == cohortId) {
					filteredEvents.add(e);
				}
			}
		}
		return new HashSet<>(filteredEvents);
	}

	@Override
	public Event showEventById(int eventId) {
		return em.find(Event.class, eventId);
	}

	@Override
	public Event createEvent(int userId, String eventJson) {
		User u = em.find(User.class, userId);
		Event e;

		if(u.getType().getType().equals("admin")) {
		
			ObjectMapper mapper = new ObjectMapper();
			try {
				e = mapper.readValue(eventJson, Event.class);
				em.persist(e);
				em.flush();
				return e;
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
		
	}

	@Override
	public Event updateEvent(int userId, int eventId, String eventJson) {
		User u = em.find(User.class, userId);
		Event e = em.find(Event.class, eventId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		if(u.getType().getType().equals("admin")) {
			
			try {
				Event updateEv = mapper.readValue(eventJson, Event.class);
				
				if(!updateEv.getDescription().equals("")) {
					e.setDescription(updateEv.getDescription()); //only need setters because we're in transaction
				}
				if(!updateEv.getDate().equals("")) {
					e.setDate(updateEv.getDate());
				}
				if(!updateEv.getPublicEvent().equals("")) {
					e.setPublicEvent(updateEv.getPublicEvent());
				}
				
				return e;
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}

		}
		
		return null;
	}

	@Override
	public boolean deleteEvent(int userId, int eventId) {
		User u = em.find(User.class, userId);
		Event e = em.find(Event.class, eventId);
		
		if(u.getType().getType().equals("admin")) {
			em.remove(e);
			return true;
		}
		return false;
	}

}
