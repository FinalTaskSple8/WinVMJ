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


public class ProfileServiceImpl extends ProfileServiceComponent{
	@Override
	public Profile createProfile(Map<String, Object> requestBody) {
	    // Ambil data dari request
	    String userIdStr = (String) requestBody.get("userId");
	    UUID userId = UUID.fromString(userIdStr);

	    String name = (String) requestBody.get("name");
	    String email = (String) requestBody.get("email");
	    String phoneNum = (String) requestBody.get("phoneNum");


	    // Buat dummy user
	    User user = new UserImpl(name, email);

	    // Create profile instance
	    Profile profile = ProfileFactory.createProfile(
	        "SIPH.profile.core.ProfileImpl",
	        userId,
	        user
	    );

	    profileRepository.saveObject(profile);
	    return profile;
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
        
		List<HashMap<String, Object>> profileList = getAllProfile(requestBody);
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

    public List<HashMap<String,Object>> getAllProfile(Map<String, Object> requestBody){
		String table = (String) requestBody.get("table_name");
		List<Profile> List = profileRepository.getAllObject(table);
		return transformListToHashMap(List);
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
		return getAllProfile(requestBody);
	}

	public String showProfile(int userId) {
		// TODO: implement this method
		return "";
	}

	public boolean editProfile(int userId, String name, String email, String phoneNum) {
		// TODO: implement this method
		return false;
	}
}
