package data;

import entities.Profile;
import entities.User;

public interface ProfileDAO {
	
	public Profile editUserProfile(int uid, String profileJson);
	public Profile createUserProfile(int uid, String profileJson);
	public Boolean deleteUserProfile(int pid);
	public Profile readUserProfile(User user, int pid);
	

}
