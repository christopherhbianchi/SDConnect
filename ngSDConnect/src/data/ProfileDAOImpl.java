package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Profile;
import entities.User;

public class ProfileDAOImpl implements ProfileDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void preworkPercentage(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editUserProfile(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Profile createUserProfile(int uid, String profileJson) {
		// TODO Auto-generated method stub
		Object mapper = new ObjectMapper();
		
		try {
			Profile mappedProfile = mapper.readValue(profileJson, Profile.class);
			
		}
		
	}

	@Override
	public Boolean deleteUserProfile(int pid) {
		// TODO Auto-generated method stub
		Profile p = em.find(Profile.class, pid);
		
		try {
			em.remove(p);
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
	}

	@Override
	public Profile readUserProfile(User user, int pid) {
		// TODO Auto-generated method stub
		return em.find(Profile.class, pid);
		
	}

}
