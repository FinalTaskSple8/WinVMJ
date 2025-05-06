package SIPH.profile.core;
import java.util.*;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;
//add other required packages

public abstract class ProfileResourceComponent implements ProfileResource{
	
	public ProfileResourceComponent() { }
 
    public abstract HashMap<String, Object> createProfile(VMJExchange vmjExchange);    
	public abstract HashMap<String, Object> updateProfile(VMJExchange vmjExchange);
    public abstract HashMap<String, Object> getProfile(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> getAllProfile(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> deleteProfile(VMJExchange vmjExchange);

	public abstract String showProfile(int userId);

	public abstract boolean editProfile(int userId, String name, String email, String phoneNum);
}
