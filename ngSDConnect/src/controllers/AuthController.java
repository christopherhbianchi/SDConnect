package controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import data.AuthDAO;
import entities.User;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthDAO authDAO;
	
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public User register(HttpSession session, @RequestBody User user, HttpServletResponse res) {
		System.out.println("password: " + user.getPassword());
		System.out.println("cohort: " + user.getCohort());
		User u = authDAO.register(user); //this should have a user returned
		if(u != null) {
			session.setAttribute("user", u); //set the session attribute if a user returned
			res.setStatus(202);
			return user;
		}
		return null;
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public User login(HttpSession session, @RequestBody User user, HttpServletResponse res) {
		
		User u = authDAO.login(user);
		if (u != null) { //operation in impl was successful
			session.setAttribute("user", u);
			return u;
		}
		res.setStatus(401);
		return user; //return the bad user
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public Boolean logout(HttpSession session, @RequestBody User user, HttpServletResponse res) {
		
		session.removeAttribute("user");//remove the attribute from session
		if(session.getAttribute("user") == null) { //if successful...
			return true;
		}
		return false;
	}
	
	@RequestMapping(path="/unauthorized")
	public String unauth (HttpServletResponse res) {
		res.setStatus(401);
		return "unauthorized";
	}
	
	
}
