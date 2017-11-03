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
	
	@RequestMapping(path = "users/{uid}/profiles", method = RequestMethod.GET)
	public Profile readUserProfile(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable int uid 
			) {
	
		return profileDao.readUserProfile(uid);
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
		
		return profileDao.editUserProfile(uid, profileJson);
	}

	@RequestMapping(path = "users/{uid}/profiles", method = RequestMethod.DELETE)
	public Boolean deleteUserProfile(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable int uid
			) {
		
		return profileDao.deleteUserProfile(uid);
	
	}
}
