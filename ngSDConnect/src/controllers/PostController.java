package controllers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.PostDAO;
import entities.Post;

@RestController
public class PostController {
	
	@Autowired
	PostDAO postDao;
	
	@RequestMapping(path="postping", method=RequestMethod.GET)
	public String postping() {
		return "postpong";
	}
	
	@RequestMapping(path="topics/{tid}/posts", method=RequestMethod.GET)
	public Collection<Post> indexPost(HttpServletResponse res, @PathVariable("tid") int topicId) {
		Set<Post> posts = postDao.showAllByTopicId(topicId);
		 if(posts==null || posts.size()==0) {
			  res.setStatus(404);
			  return new HashSet<Post>();
		  }
		  else {
			  res.setStatus(200);
			  return posts;
		  }
		
	}
	
	  @RequestMapping(path="posts/{pid}", method=RequestMethod.GET)
	  public Post show(HttpServletRequest req, HttpServletResponse res, @PathVariable("pid") int postId) {
		  Post post = postDao.showPostById(postId);
		  if(post==null) {
			  res.setStatus(404);
		  }
		  else {
			  res.setStatus(200);
		  }
		  return post;
	  }
	  
	  @RequestMapping(path="topics/{tid}/users/{uid}/posts", method=RequestMethod.POST)
	  public Post create(HttpServletResponse res, 
			  			@PathVariable("uid") int userId, 
			  			@PathVariable("tid") int topicId,
			  			@RequestBody String postJson) {
		  Post post = postDao.createPost(userId, topicId, postJson);
		  if(post==null) {
			  res.setStatus(400);
		  }
		  else {
			  res.setStatus(201);
		  }
		  return post;
	  }
	  
	  @RequestMapping(path="users/{uid}/posts/{pid}", method=RequestMethod.PUT)
		public Post update(HttpServletResponse res, @PathVariable("pid") int postId, @PathVariable("uid") int userId, @RequestBody String postJson) {
			Post post = postDao.updatePost(userId, postId, postJson);
			if(post==null) {
				res.setStatus(400);
			}
			else {
				res.setStatus(200);
			}
			return post;
		}
	  
	  @RequestMapping(path="users/{uid}/posts/{pid}", method=RequestMethod.DELETE)
	  public Boolean destroy(HttpServletResponse res, @PathVariable("uid") int userId, @PathVariable("pid") int postId) {
		  Boolean answer = postDao.deletePost(userId, postId);
		  if(!answer) {
			  res.setStatus(406);
		  }
		  else {
			  res.setStatus(202);
		  }
		  return answer;
	  }
	  
	  
}
