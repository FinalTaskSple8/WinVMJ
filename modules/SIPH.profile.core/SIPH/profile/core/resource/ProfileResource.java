package SIPH.profile.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public interface ProfileResource {
    List<HashMap<String,Object>> saveProfile(VMJExchange vmjExchange);
    HashMap<String, Object> updateProfile(VMJExchange vmjExchange);
    HashMap<String, Object> getProfile(VMJExchange vmjExchange);
    List<HashMap<String,Object>> getAllProfile(VMJExchange vmjExchange);
    List<HashMap<String,Object>> deleteProfile(VMJExchange vmjExchange);
	HashMap<String, Object> Profile(VMJExchange vmjExhange);
}
