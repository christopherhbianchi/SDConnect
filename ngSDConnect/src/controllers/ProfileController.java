package controllers;

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
	
	@RequestMapping(path = "users/{uid}/profiles/{pid}", method = RequestMethod.GET)
	public Profile readUserProfile(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable int uid, 
			@PathVariable int pid) {
	
		return profileDao.readUserProfile(uid, pid);
	}

	@RequestMapping(path = "user/{uid}/profile", method = RequestMethod.POST)
	public Profile createUserProfile(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable int uid, 
			@RequestBody String profileJson) {
		
		return profileDao.createUserProfile(uid, profileJson);
	} 


	@RequestMapping(path = "user/{uid}/todo/{pid}", method = RequestMethod.PUT)
	public Profile editUserProfile(HttpServletRequest req, HttpServletResponse res,
			@PathVariable int uid,
			@PathVariable int pid, 
			@RequestBody String profileJson) {
		
		return profileDao.editUserProfile(uid, pid, profileJson);
	}

	@RequestMapping(path = "user/{uid}/profile/{pid}", method = RequestMethod.DELETE)
	public Boolean deleteUserProfile(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable int uid, 
			@PathVariable int pid) {
		
		return profileDao.deleteUserProfile(uid, pid);
	
	}
}
