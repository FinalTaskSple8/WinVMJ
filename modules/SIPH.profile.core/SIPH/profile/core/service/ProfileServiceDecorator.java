package SIPH.profile.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public abstract class ProfileServiceDecorator extends ProfileServiceComponent{
	protected ProfileServiceComponent record;

    public ProfileServiceDecorator(ProfileServiceComponent record) {
        this.record = record;
    }

	public Profile createProfile(Map<String, Object> requestBody){
		return record.createProfile(requestBody);
	}


	public HashMap<String, Object> getProfile(Map<String, Object> requestBody){
		return record.getProfile(requestBody);
	}

	public List<HashMap<String,Object>> getAllProfile(Map<String, Object> requestBody){
		return record.getAllProfile(requestBody);
	}


    public HashMap<String, Object> updateProfile(Map<String, Object> requestBody){
		return record.updateProfile(requestBody);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<Profile> List){
		return record.transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> deleteProfile(Map<String, Object> requestBody){
		return record.deleteProfile(requestBody);
	}

	public HashMap<String, Object> getProfileById(int id){
        return record.getProfileById(id);
    }

	public String showProfile(int userId) {
		return record.showProfile(userId);
	}

	public boolean editProfile(int userId, String name, String email, String phoneNum) {
		return record.editProfile(userId, name, email, phoneNum);
	}
}
