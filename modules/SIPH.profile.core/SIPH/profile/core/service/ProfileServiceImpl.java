package SIPH.profile.core;
import java.util.*;
import com.google.gson.Gson;
import java.util.*;
import java.util.logging.Logger;
import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.profile.ProfileFactory;
import vmj.auth.annotations.Restricted;
//add other required packages
import vmj.auth.model.core.User;
import vmj.auth.model.core.UserComponent;
import vmj.auth.model.core.UserImpl;
import java.util.List;
import java.util.UUID;
public class ProfileServiceImpl extends ProfileServiceComponent{
	@Override
	public Profile createProfile(Map<String, Object> requestBody, String email) {
	    // Ambil data dari request
	    String phoneNum = (String) requestBody.get("phone_number");
	    
	    List<User> user = userRepository.getListObject("auth_user_impl", "email", email );
	    System.out.println(user.get(0).getName());
	    String name = user.get(0).getName();
	    UUID userId = user.get(0).getId();
	    // Create profile instance
	    Profile profile = ProfileFactory.createProfile(
	        "SIPH.profile.core.ProfileImpl",
	        userId,
	        phoneNum,
	        name,
	        email
	    );

	    profileRepository.saveObject(profile);
	    return profile;
	}
	
	public HashMap<String, Object> getProfileByEmail(String email) {
		List<User> users = userRepository.getListObject("auth_user_impl", "email", email);
		if (users.isEmpty()) return null;

		UUID userId = users.get(0).getId();

		List<HashMap<String, Object>> allProfiles = getAllProfile(); // langsung panggil versi baru
		for (HashMap<String, Object> profile : allProfiles) {
			Object profileUserId = profile.get("userId");
			if (profileUserId != null && profileUserId.toString().equals(userId.toString())) {
				return profile;
			}
		}
		return null;
	}


	public HashMap<String, Object> updateNameAndPhoneNumber(String email, String newName, String newPhoneNumber) {
		List<User> users = userRepository.getListObject("auth_user_impl", "email", email);
		if (users.isEmpty()) throw new RuntimeException("User not found");

		List<Profile> profiles = profileRepository.getListObject("profile_impl", "email", email);
		if (profiles.isEmpty()) throw new RuntimeException("Profile not found");

		Profile profile = profiles.get(0);
		
		profile.setName(newName);
		profile.setPhoneNum(newPhoneNumber);

		profileRepository.updateObject(profile);

		return profile.toHashMap();
	}


    public HashMap<String, Object> updateProfile(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("userIdid");
		int id = Integer.parseInt(idStr);
		Profile profile = profileRepository.getObject(id);
		
		
		profileRepository.updateObject(profile);
		
		//to do: fix association attributes
		
		return profile.toHashMap();
		
	}

    public HashMap<String, Object> getProfile(Map<String, Object> requestBody){
    	String idStr = (String) requestBody.get("id");
        UUID targetId = UUID.fromString(idStr);
        
		List<HashMap<String, Object>> profileList = getAllProfile();
		for (HashMap<String, Object> profile : profileList){
			String roomIdStr = (String) profile.get("id");
            UUID profileId = UUID.fromString(roomIdStr);
            if (profileId.equals(targetId)) {
                return profile;
            }
		}
		return null;
	}

	public Profile getProfileById(UUID id){
		Profile profile = profileRepository.getObject(id);
		return profile;
	}

	public List<HashMap<String,Object>> getAllProfile(){
	    List<Profile> list = profileRepository.getAllObject("profile_impl");
	    return transformListToHashMap(list);
	}


    public List<HashMap<String,Object>> transformListToHashMap(List<Profile> List){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
	}

    public List<HashMap<String,Object>> deleteProfile(Map<String, Object> requestBody){
		String idStr = ((String) requestBody.get("id"));
		int id = Integer.parseInt(idStr);
		profileRepository.deleteObject(id);
		return getAllProfile();
	}

	public boolean editProfile(int userId, String name, String email, String phoneNum) {
		// TODO: implement this method
		return false;
	}
}
