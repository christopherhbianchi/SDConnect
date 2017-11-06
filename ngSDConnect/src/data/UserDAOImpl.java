package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public User getUserById(int uid) {
		User user = em.find(User.class, uid);
		if(user.getProfile().getImg() == "null" || user.getProfile().getImg() == "" ) {
			user.getProfile().setImg("img/profilePictures/profile.png");
		}
		
		return user;
	}

}
