package entities;

public class Projects {
	
	//fields
	private int id;
	private String description;
	private int estimatedHours;
	private String name;
	
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
	public int getEstimatedHours() {
		return estimatedHours;
	}
	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//toString
	@Override
	public String toString() {
		return "Projects [id=" + id + ", description=" + description + ", estimatedHours=" + estimatedHours + ", name="
				+ name + "]";
	}
	
	

}
