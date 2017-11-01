package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Profile {
	
	
	//field

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="img_url")
	private String img;
	@Column(name="background_desc")
	private String backgroundDescription;
	@Column(name="first_name")
	private String fname;
	@Column(name="last_name")
	private String lname;
	@Column(name="prev_industry")
	private String previousIndustry;
	@Column(name="coding_exp")
	private String codingExperience;
	@Column(name="shirt_size")
	private String shirtSize;
	private String cohort;
	@Column(name="user_id")
	private int userID;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	//gets and sets
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getBackgroundDescription() {
		return backgroundDescription;
	}
	public void setBackgroundDescription(String backgroundDescription) {
		this.backgroundDescription = backgroundDescription;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPreviousIndustry() {
		return previousIndustry;
	}
	public void setPreviousIndustry(String previousIndustry) {
		this.previousIndustry = previousIndustry;
	}
	public String getCodingExperience() {
		return codingExperience;
	}
	public void setCodingExperience(String codingExperience) {
		this.codingExperience = codingExperience;
	}
	public String getShirtSize() {
		return shirtSize;
	}
	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}
	public String getCohort() {
		return cohort;
	}
	public void setCohort(String cohort) {
		this.cohort = cohort;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	//toString
	@Override
	public String toString() {
		return "Profile [id=" + id + ", img=" + img + ", backgroundDescription=" + backgroundDescription + ", fname="
				+ fname + ", lname=" + lname + ", previousIndustry=" + previousIndustry + ", codingExperience="
				+ codingExperience + ", shirtSize=" + shirtSize + ", cohort=" + cohort + ", userID=" + userID
				+ ", user=" + user + "]";
	}
	
	

}
