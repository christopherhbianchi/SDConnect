package data;

import java.util.Set;

import entities.Event;
import entities.Project;

public interface ProjectDAO {

	
	//read all
			public Set<Project> showAll(); //needs a user id to find a user, then grab events by cohort
			//read by cohort
			
			//dont need projects by cohort cuz event has that functionality
			
			public Project showProjectById(int projectId); //need to show only events for a cohort
			//create
			public Project createProject(int userId, String projectJson); //can check that user is an admin before adding event
			//update
			public Project updateProject(int userId, int projectId, String projectJson);
			//delete
			public boolean deleteProject(int userId, int projectId);
}
