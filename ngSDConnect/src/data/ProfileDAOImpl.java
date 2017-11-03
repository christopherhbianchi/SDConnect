package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Profile;
import entities.User;

@Transactional
@Repository
public class ProfileDAOImpl implements ProfileDAO {
	
	@PersistenceContext
	private EntityManager em;


	@Override
	public Profile editUserProfile(int uid, String profileJson) {
		
		User u = em.find(User.class, uid); //profile from db
		//profile has a user object
		Profile p = u.getProfile();
		if(u.getId() == uid) { //may need to go
			
		//put this at the end of your entire code
		
		
				ObjectMapper mapper = new ObjectMapper();
				
				try {
					//profile from a json string, that we use to get info from for an existing profile
					Profile mappedProfile = mapper.readValue(profileJson, Profile.class);
					
					if(!mappedProfile.getBackgroundDescription().equals("")) {
						p.setBackgroundDescription(mappedProfile.getBackgroundDescription());
					}
					
					
		//			String query = "SELECT p FROM Profile p WHERE p.id = :pid";
		//			Profile poww = em.createQuery(query, Profile.class)
		//							.setParameter("pid", pid)
		//							.getResultList()
		//							.get(0);
		//			
					//*********************************************************
					p.setImg(mappedProfile.getImg());
					p.setBackgroundDescription(mappedProfile.getBackgroundDescription());
					p.setFname(mappedProfile.getFname());
					p.setLname(mappedProfile.getLname());
					p.setPreviousIndustry(mappedProfile.getPreviousIndustry());
					p.setCodingExperience(mappedProfile.getCodingExperience());
					p.setShirtSize(mappedProfile.getShirtSize());
					p.setWebsiteUrl(mappedProfile.getWebsiteUrl());
					p.setGithubUrl(mappedProfile.getGithubUrl());
					p.setLinkedinUrl(mappedProfile.getLinkedinUrl());
					
					//***********************************************************
					
					return p;
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
		}
		return null;
		
	}

	@Override
	public Profile createUserProfile(int uid, String profileJson) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Profile mappedProfile = mapper.readValue(profileJson, Profile.class);
			User u = em.find(User.class, uid);
			mappedProfile.setUser(u);
			
			em.persist(mappedProfile);
			em.flush();
			return mappedProfile;
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public Boolean deleteUserProfile(int uid) {
		// TODO Auto-generated method stub
		User u = em.find(User.class, uid);
		em.remove(u.getProfile());
		
		if(u.getProfile() == null) {
			return true;
		}
		
		return false;
	}

	@Override
	public Profile readUserProfile(int uid) {
		// TODO Auto-generated method stub
		return em.find(User.class, uid).getProfile();
		
	}

}
