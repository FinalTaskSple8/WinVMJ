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
import prices.auth.vmj.annotations.Restricted;
//add other required packages

public class ProfileServiceImpl extends ProfileServiceComponent{

    public List<HashMap<String,Object>> saveProfile(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Profile profile = createProfile(vmjExchange);
		profileRepository.saveObject(profile);
		return getAllProfile(vmjExchange);
	}

    public Profile createProfile(Map<String, Object> requestBody){
		
		//to do: fix association attributes
		Profile Profile = ProfileFactory.createProfile(
			"SIPH.profile.core.ProfileImpl",
		user
		, user
		);
		Repository.saveObject(profile);
		return profile;
	}

    public Profile createProfile(Map<String, Object> requestBody, int id){
		
		//to do: fix association attributes
		
		Profile profile = ProfileFactory.createProfile("SIPH.profile.core.ProfileImpl", user, user);
		return profile;
	}

    public HashMap<String, Object> updateProfile(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("");
		int id = Integer.parseInt(idStr);
		Profile profile = Repository.getObject(id);
		
		
		Repository.updateObject(profile);
		
		//to do: fix association attributes
		
		return profile.toHashMap();
		
	}

    public HashMap<String, Object> getProfile(Map<String, Object> requestBody){
		List<HashMap<String, Object>> profileList = getAllProfile("profile_impl");
		for (HashMap<String, Object> profile : profileList){
			int record_id = ((Double) profile.get("record_id")).intValue();
			if (record_id == id){
				return profile;
			}
		}
		return null;
	}

	public HashMap<String, Object> getProfileById(int id){
		String idStr = vmjExchange.getGETParam(""); 
		int id = Integer.parseInt(idStr);
		Profile profile = profileRepository.getObject(id);
		return profile.toHashMap();
	}

    public List<HashMap<String,Object>> getAllProfile(Map<String, Object> requestBody){
		String table = (String) requestBody.get("table_name");
		List<Profile> List = Repository.getAllObject(table);
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
		Repository.deleteObject(id);
		return getAllProfile(requestBody);
	}

	private String showProfile(int userId) {
		// TODO: implement this method
	}

	private boolean editProfile(int userId, String name, String email, String phoneNum) {
		// TODO: implement this method
	}
}
