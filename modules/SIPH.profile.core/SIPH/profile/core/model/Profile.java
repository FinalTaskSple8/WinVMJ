package SIPH.profile.core;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import java.util.*;
import vmj.auth.model.core.User;
import vmj.auth.model.core.UserComponent;
public interface Profile {
	public UUID getUserId();
	public void setUserId(UUID userId);
	public String getName();
	public void setName(String name);
	public String getEmail();
	public void setEmail(String email);
	public String getPhoneNum();
	public void setPhoneNum(String phoneNum);
	public UUID getId();
	public void setId(UUID id);
	HashMap<String, Object> toHashMap();
}
