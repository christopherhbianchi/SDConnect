package entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	private String password;

	//many users of one type
	@ManyToOne	
	@JoinColumn(name = "user_type_id")
	private Type type;
	
	@ManyToMany
	@JoinTable(name="has_form",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="form_id")
	)
	private List<Form> form;

	// many users in one cohort
	@ManyToOne
	@JoinColumn(name = "cohort_id")
	private Cohort cohort;

	// one user to one profile
	@OneToOne(mappedBy = "user")
	private Profile profile;

	// one user has many posts
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	// set and gets
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

 	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Form> getForm() {
		return form;
	}

	public void setForm(List<Form> form) {
		this.form = form;
	}

	public Cohort getCohort() {
		return cohort;
	}

	public void setCohort(Cohort cohort) {
		this.cohort = cohort;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append(", type=");
		builder.append(type);
		builder.append(", cohort=");
		builder.append(cohort);
		builder.append(", profile=");
		builder.append(profile);
		builder.append("]");
		return builder.toString();
	}

}
