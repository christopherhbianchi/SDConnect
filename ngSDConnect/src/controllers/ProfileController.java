package controllers;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.ProfileDAO;
import entities.Profile;

@RestController
public class ProfileController{
	
	@Autowired
	private ProfileDAO profileDao;
	
	@RequestMapping(path="pingProfile", method=RequestMethod.GET )
	public String ping() {
		return "pongProfile";
	}
	
	@RequestMapping(path = "profile", method = RequestMethod.GET)
	public Set<Profile> readAllProfiles(HttpServletRequest req, HttpServletResponse res) {
	
		return profileDao.readAllProfiles();
	}
	
	@RequestMapping(path = "users/search", method = RequestMethod.POST)
	public boolean checkDuplicatedEmail(HttpServletRequest req, HttpServletResponse res, @RequestBody String email) {
		return profileDao.checkDuplicatedEmail(email);
	}
	
	@RequestMapping(path = "users/{uid}/profiles", method = RequestMethod.GET)
	public Profile readUserProfile(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable int uid
			) {
		
		return profileDao.readUserProfile(uid);
	}
	
	@RequestMapping(path = "profiles/{pid}", method = RequestMethod.GET)
	public Profile readProfileById(	HttpServletRequest req, 
									HttpServletResponse res, 
									@PathVariable int pid) {
		
		return profileDao.getProfileByProfileId(pid);
	}

	@RequestMapping(path = "users/{uid}/profiles", method = RequestMethod.POST)
	public Profile createUserProfile(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable int uid, 
			@RequestBody String profileJson) {
		
		return profileDao.createUserProfile(uid, profileJson);
	} 


	@RequestMapping(path = "users/{uid}/profiles", method = RequestMethod.PUT)
	public Profile editUserProfile(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int uid,
			@RequestBody String profileJson) {
		System.out.println("Inside editUserProfile in the conetrolelr");
		return profileDao.editUserProfile(uid, profileJson);
	}

	@RequestMapping(path = "users/{uid}/profiles/{pid}", method = RequestMethod.DELETE)
	public Boolean deleteUserProfile(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable int uid, @PathVariable int pid
			) {
		
		return profileDao.deleteUserProfile(uid, pid);
	
	}
}