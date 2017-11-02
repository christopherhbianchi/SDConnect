package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Profile {

	// field
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "img_url")
	private String img;

	@Column(name = "background_desc")
	private String backgroundDescription;

	@Column(name = "first_name")
	private String fname;

	@Column(name = "last_name")
	private String lname;

	@Column(name = "prev_industry")
	private String previousIndustry;

	@Column(name = "coding_exp")
	private String codingExperience;

	@Column(name = "shirt_size")
	private String shirtSize;
	
	@Column(name="website_url")
	private String websiteUrl;
	
	@Column(name="github_url")
	private String githubUrl;
	
	@Column(name="linkedin_url")
	private String linkedinUrl;

	@JsonBackReference(value="profileForUser")
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	// gets and sets
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}

	public String getLinkedinUrl() {
		return linkedinUrl;
	}

	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl = linkedinUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Profile [id=");
		builder.append(id);
		builder.append(", img=");
		builder.append(img);
		builder.append(", backgroundDescription=");
		builder.append(backgroundDescription);
		builder.append(", fname=");
		builder.append(fname);
		builder.append(", lname=");
		builder.append(lname);
		builder.append(", previousIndustry=");
		builder.append(previousIndustry);
		builder.append(", codingExperience=");
		builder.append(codingExperience);
		builder.append(", shirtSize=");
		builder.append(shirtSize);
		builder.append("]");
		return builder.toString();
	}

	// toString


}
