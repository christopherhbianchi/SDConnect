package controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.EventDAO;
import entities.Event;

@RestController
public class EventController {

	@Autowired 
	private EventDAO dao;
	
	@RequestMapping(path="pingEvent", method=RequestMethod.GET )
	public String ping() {
		return "pongEvent";
	}
	
	@RequestMapping(path="/events", method=RequestMethod.GET)
	public Set<Event> index() {
		return dao.showAll();
	}
	
	@RequestMapping(path="/cohorts/{cohortId}/events", method=RequestMethod.GET)
	public Set<Event> show(@PathVariable int cohortId) {
		return dao.showEventsByCohortId(cohortId);
	}
	
	@RequestMapping(path="/events/{eventId}", method=RequestMethod.GET)
	public Event showByEventId(@PathVariable int eventId) {
		return dao.showEventById(eventId);
	}
	
	@RequestMapping(path="/users/{userId}/events", method=RequestMethod.POST)
	public Event create(@PathVariable int userId, @RequestBody String eventJson) {
		return dao.createEvent(userId, eventJson);
	}
	
	@RequestMapping(path="/users/{userId}/events/{eventId}", method=RequestMethod.PUT)
	public Event update(@PathVariable int userId, @PathVariable int eventId, @RequestBody String eventJson) {
		return dao.updateEvent(userId, eventId, eventJson);
	}
	
	@RequestMapping(path="/users/{userId}/events/{eventId}", method=RequestMethod.DELETE)
	public boolean destroy(@PathVariable int eventId, @PathVariable int userId) {
		return dao.deleteEvent(userId, eventId);
	}
	
	
//  index	
//  show
//  create	
//	update
//	destroy

}
