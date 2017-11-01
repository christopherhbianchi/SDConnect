package entities;

import javax.persistence.Column;

public class Event {
	
	//fields
	private int id;
	private String description;
	private String date;
	@Column(name="public")
	private String publicEvent;
	@Column(name="projects_id")
	private int projectsID;
	
	//gets and sets
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPublicEvent() {
		return publicEvent;
	}
	public void setPublicEvent(String publicEvent) {
		this.publicEvent = publicEvent;
	}
	public int getProjectsID() {
		return projectsID;
	}
	public void setProjectsID(int projectsID) {
		this.projectsID = projectsID;
	}
	
	//toString
	@Override
	public String toString() {
		return "Event [id=" + id + ", description=" + description + ", date=" + date + ", publicEvent=" + publicEvent
				+ ", projectsID=" + projectsID + "]";
	}
	
	

}
