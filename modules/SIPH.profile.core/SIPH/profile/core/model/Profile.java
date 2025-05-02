package SIPH.profile.core;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import java.util.*;

public interface Profile {
	public int getUserId();
	public void setUserId(int userId);
	public String getName();
	public void setName(String name);
	public String getEmail();
	public void setEmail(String email);
	public String getPhoneNum();
	public void setPhoneNum(String phoneNum);
	public User getUser();
	public void setUser(User user);
	public User getUser();
	public void setUser(User user);
	org.eclipse.uml2.uml.internal.impl.ClassImpl@fdc109 (name: User, visibility: public) (isLeaf: false, isAbstract: false, isFinalSpecialization: false) (isActive: false)org.eclipse.uml2.uml.internal.impl.ClassImpl@e85a0c4 (name: ProfileImpl, visibility: <unset>) (isLeaf: false, isAbstract: false, isFinalSpecialization: false) (isActive: false)
	HashMap<String, Object> toHashMap();
}
