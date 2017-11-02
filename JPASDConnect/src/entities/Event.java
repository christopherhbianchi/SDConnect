package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Event {
	
	//fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private String date;
	@Column(name="public")
	private String publicEvent;
	@ManyToOne
	@JoinColumn(name="projects_id")
	private Project project;
	
	@ManyToMany (mappedBy="eventList")
	private List<Cohort> cohortList;
	
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
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	public List<Cohort> getCohortList() {
		return cohortList;
	}
	public void setCohortList(List<Cohort> cohortList) {
		this.cohortList = cohortList;
	}
	//toString
	@Override
	public String toString() {
		return "Event [id=" + id + ", description=" + description + ", date=" + date + ", publicEvent=" + publicEvent
				+ ", project=" + project + "]";
	}
	
	

}
