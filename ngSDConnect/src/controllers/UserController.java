package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.UserDAO;
import entities.User;

@RestController
public class UserController {
	
	@Autowired
	UserDAO userDao;
	
	@RequestMapping(path="users/{uid}", method=RequestMethod.GET)
	public User show(@PathVariable int uid) {
		return userDao.getUserById(uid);
	}

}
