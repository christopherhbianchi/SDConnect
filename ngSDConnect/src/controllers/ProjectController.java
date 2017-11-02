package controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.ProjectDAO;
import entities.Project;

@RestController
public class ProjectController {
	
	@Autowired
	private ProjectDAO dao;
	
	//5 methods
	//create
	//update
	//delete
	//read all
	//read one
	
	
	@RequestMapping(path="/projects", method=RequestMethod.GET)
	public Set<Project> index(){
		return dao.showAll();
	}
	
	@RequestMapping(path="/projects/{projectId}", method=RequestMethod.GET)
	public Project show(@PathVariable int projectId) {
		return dao.showProjectById(projectId);
	}
	
	@RequestMapping(path="/users/{userId}/projects")
	public Project create(@RequestBody String projectJson, @PathVariable int userId) {
		return dao.createProject(userId, projectJson);
	}
	
	
	public Project update() {
		
	}
	
	public boolean destroy() {
		
	}
	
	
	
	

}
