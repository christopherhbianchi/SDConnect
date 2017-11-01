package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	//fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String email;
	
	private String password;
	
	@Column(name="user_type")
	private String type;
	
	@Column(name="schedule_id")
	private int scheduleID;
	
	@Column(name="cohort_id")
	private int cohortID;
	
	@OneToOne(mappedBy="user")
	private Profile profile;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	//set and gets
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}
	public int getCohortID() {
		return cohortID;
	}
	public void setCohortID(int cohortID) {
		this.cohortID = cohortID;
	}

	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	//toString
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", type=" + type + ", schedule_ID="
				+ scheduleID + ", cohort_ID=" + cohortID + "]";
	
	}
}
