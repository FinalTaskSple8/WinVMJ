package SIPH.profile.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public interface ProfileService {
	Profile createProfile(Map<String, Object> requestBody);
	HashMap<String, Object> getProfile(Map<String, Object> requestBody);
    HashMap<String, Object> updateProfile(Map<String, Object> requestBody);
    HashMap<String, Object> getProfileById(int id);
    List<HashMap<String,Object>> getAllProfile(Map<String, Object> requestBody);
    List<HashMap<String,Object>> deleteProfile(Map<String, Object> requestBody);
}
