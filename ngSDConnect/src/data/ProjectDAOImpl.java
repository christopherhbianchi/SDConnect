package data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Project;
import entities.User;

@Transactional
@Repository
public class ProjectDAOImpl implements ProjectDAO{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Set<Project> showAll() {
		String query = "SELECT p FROM Project p";
		return new HashSet<>(em.createQuery(query).getResultList());
	}

	@Override
	public Project showProjectById(int projectId) {
		return em.find(Project.class, projectId);
	}

	@Override
	public Project createProject(int userId, String projectJson) {

		User u = em.find(User.class, userId);
		Project p;

		if(u.getType().getType().equals("admin")) {
		
			ObjectMapper mapper = new ObjectMapper();
			try {
				p = mapper.readValue(projectJson, Project.class);
				em.persist(p);
				em.flush();
				return p;
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Project updateProject(int userId, int projectId, String projectJson) {
		User u = em.find(User.class, userId);
		Project p = em.find(Project.class, projectId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		if(u.getType().getType().equals("admin")) {
			
			try {
				Project updateProj = mapper.readValue(projectJson, Project.class);
				
				if(!updateProj.getDescription().equals("")) {
					p.setDescription(updateProj.getDescription()); //only need setters because we're in transaction
				}
				if(!updateProj.getName().equals("")) {
					p.setName(updateProj.getName());
				}
				if(Integer.valueOf(updateProj.getEstimatedHours()) == null) { //made it an object
					p.setEstimatedHours(updateProj.getEstimatedHours());
				}
				
				return p;
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean deleteProject(int userId, int projectId) {
		User u = em.find(User.class, userId);
		Project p = em.find(Project.class, projectId);
		
		if(u.getType().getType().equals("admin")) {
			em.remove(p);
			return true;
		}
		return false;
	}
	

}
