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
	public Profile editUserProfile(int uid, int pid, String profileJson) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Profile mappedProfile = mapper.readValue(profileJson, Profile.class);
			Profile p = em.find(Profile.class, pid);
			
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
	public Boolean deleteUserProfile(int uid, int pid) {
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
	public Profile readUserProfile(int uid, int pid) {
		// TODO Auto-generated method stub
		return em.find(Profile.class, pid);
		
	}

}
