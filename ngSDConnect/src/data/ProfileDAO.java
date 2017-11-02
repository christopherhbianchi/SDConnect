package data;

import entities.Profile;


public interface ProfileDAO {
	
	public Profile editUserProfile(int uid, int pid, String profileJson);
	public Profile createUserProfile(int uid, String profileJson);
	public Boolean deleteUserProfile(int uid, int pid);
	public Profile readUserProfile(int uid, int pid);
	

}
