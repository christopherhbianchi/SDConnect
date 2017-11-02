package controllers;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
		  }
		  else {
			  res.setStatus(200);
		  }
		return posts;
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
}
