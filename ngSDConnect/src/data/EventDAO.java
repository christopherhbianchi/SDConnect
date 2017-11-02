package data;

import java.util.Set;

import entities.Event;
import entities.Topic;

public interface EventDAO {
	
	
	
	//read all
		public Set<Event> showAll(); //needs a user id to find a user, then grab events by cohort
		//read by cohort
		public Set<Event> showEventsByCohortId(int cohortId); //need to show only events for a cohort
		//read by single id
		public Set<Event> showEventsById(int eventId); //need to show only events for a cohort
		//create
		public Topic createEvent(int userId, String eventJson); //can check that user is an admin before adding event
		//update
		public Topic updateEvent(int userId, int eventId, String topicJson);
		//delete
		public Boolean deleteEvent(int userId, int eventId);
}
