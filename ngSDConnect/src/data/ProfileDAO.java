package data;

import entities.Profile;
import entities.User;

public interface ProfileDAO {
	
	public void preworkPercentage(User user);
	public void editUserProfile(User user);
	public Profile createUserProfile(User user);
	public Boolean deleteUserProfile(int pid);
	public Profile readUserProfile(User user, int pid);
	

}
