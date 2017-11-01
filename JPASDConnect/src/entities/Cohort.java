package entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cohort {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="cohort_num")
	private int cohortNum;
	
	@Column(name="mascot_img_url")
	private String mascotImgUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCohortNum() {
		return cohortNum;
	}

	public void setCohortNum(int cohortNum) {
		this.cohortNum = cohortNum;
	}

	public String getMascotImgUrl() {
		return mascotImgUrl;
	}

	public void setMascotImgUrl(String mascotImgUrl) {
		this.mascotImgUrl = mascotImgUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cohort [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", cohortNum=");
		builder.append(cohortNum);
		builder.append("]");
		return builder.toString();
	}

}
