package SIPH.profile.core;
import java.util.*;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;
//add other required packages

public abstract class ProfileServiceComponent implements ProfileService{
	protected RepositoryUtil<Profile> profileRepository;

    public ProfileServiceComponent(){
        this.profileRepository = new RepositoryUtil<Profile>(SIPH.profile.core.ProfileComponent.class);
    }	

    public abstract Profile createProfile(Map<String, Object> requestBody);  
	public abstract HashMap<String, Object> updateProfile(Map<String, Object> requestBody);
    public abstract HashMap<String, Object> getProfile(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> getAllProfile(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> transformListToHashMap(List<Profile> List);
    public abstract List<HashMap<String,Object>> deleteProfile(Map<String, Object> requestBody);
	public abstract HashMap<String, Object> getProfileById(int id);

	public abstract String showProfile(int userId);

	public abstract boolean editProfile(int userId, String name, String email, String phoneNum);
}
