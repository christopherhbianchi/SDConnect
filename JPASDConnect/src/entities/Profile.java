package entities;

import javax.persistence.Entity;

@Entity
public class Profile {
	
	
	//field
	private int id;
	private String img;
	private String backgroundDescription;
	private String fname;
	private String lname;
	private String previousIndustry;
	private String codingExperience;
	private String shirtSize;
	private String cohort;
	private int userID;
	
	
	
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
	
	
	//toString
	@Override
	public String toString() {
		return "Profile [id=" + id + ", img=" + img + ", backgroundDescription=" + backgroundDescription + ", fname="
				+ fname + ", lname=" + lname + ", previousIndustry=" + previousIndustry + ", codingExperience="
				+ codingExperience + ", shirtSize=" + shirtSize + ", cohort=" + cohort + ", userID=" + userID + "]";
	}
	
	

}
