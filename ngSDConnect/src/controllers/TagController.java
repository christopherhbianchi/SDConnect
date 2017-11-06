package controllers;

import java.util.Collection;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import data.TagDAO;
import entities.Post;
import entities.Tag;

@RestController
public class TagController {
	
	@Autowired
	TagDAO tagDao;
	
	@RequestMapping(path="tagping", method=RequestMethod.GET)
	public String tagping() {
		return "tagpong";
	}
	
	@RequestMapping(path="tags", method=RequestMethod.GET)
	public Collection<Tag> indexTag(HttpServletResponse res) {
		Set<Tag> tags = tagDao.showAll();
		 if(tags==null || tags.size()==0) {
			  res.setStatus(404);
		  }
		  else {
			  res.setStatus(200);
		  }
		return tags;
	}
	
	  @RequestMapping(path="tags/{tid}", method=RequestMethod.GET)
	  public Tag show(HttpServletRequest req, HttpServletResponse res, @PathVariable("tid") int tagId) {
		  Tag tag = tagDao.showTagById(tagId);
		  if(tag==null) {
			  res.setStatus(404);
		  }
		  else {
			  res.setStatus(200);
		  }
		  return tag;
	  }
	  
	  @RequestMapping(path="tags", method=RequestMethod.POST)
	  public Tag create(HttpServletResponse res, @RequestBody String tagJson) {
		  Tag tag = tagDao.createTag(tagJson);
		  if(tag==null) {
			  res.setStatus(400);
		  }
		  else {
			  res.setStatus(201);
		  }
		  return tag;
	  }
	  
	  @RequestMapping(path="tags/{tid}", method=RequestMethod.PUT) 
		public Tag update(	@PathVariable("tid") int tagId, 
							@RequestBody String tagJson, 
							HttpServletResponse res) {
			Tag tag = tagDao.updateTag(tagId, tagJson);
			if(tag == null) {
				res.setStatus(400);
			}
			else {
				res.setStatus(202);
			}
			return tag;	
		}
	  
	  @RequestMapping(path="tags/{tid}", method=RequestMethod.DELETE)
	  public boolean destroy(HttpServletResponse res, @PathVariable("tid") int tagId) {
		  Boolean answer = tagDao.deleteTag(tagId);
		  if(!answer) {
			  res.setStatus(406);
		  }
		  else {
			  res.setStatus(202);
		  }
		  return answer;
	  }
	  

}
