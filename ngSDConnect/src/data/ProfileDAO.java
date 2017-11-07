package data;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Profile;


public interface ProfileDAO {

	public Profile editUserProfile(int uid, String profileJson);
	public Profile createUserProfile(int uid, String profileJson);
	public Boolean deleteUserProfile(int uid, int pid);
	public Profile readUserProfile(int uid);
	public Set<Profile> readAllProfiles();
	public boolean checkDuplicatedEmail(String userEmail);

}
