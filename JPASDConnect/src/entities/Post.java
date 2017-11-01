package entities;

import java.sql.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String message;
	
	private Date post_dt;
	
	//many posts in one thread
	@ManyToOne
	@JoinColumn(name="thread_id")
	private Thread thread;
	
	//many posts belong to one user
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	private String link;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getPost_dt() {
		return post_dt;
	}

	public void setPost_dt(Date post_dt) {
		this.post_dt = post_dt;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Post [id=");
		builder.append(id);
		builder.append(", message=");
		builder.append(message);
		builder.append(", post_dt=");
		builder.append(post_dt);
		builder.append(", thread=");
		builder.append(thread);
		builder.append(", link=");
		builder.append(link);
		builder.append("]");
		return builder.toString();
	}
	
}
