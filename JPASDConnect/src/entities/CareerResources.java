package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CareerResources {
	
	//field

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String type;
	
	@Column(name="upload_id")
	private String uploadID;
	
	private String link;
	
	//gets and sets
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUploadID() {
		return uploadID;
	}
	public void setUploadID(String uploadID) {
		this.uploadID = uploadID;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	//toString
	@Override
	public String toString() {
		return "CareerResources [id=" + id + ", type=" + type + ", uploadID=" + uploadID + ", link=" + link + "]";
	}
	

}
