package SIPH.profile.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

public abstract class ProfileResourceDecorator extends ProfileResourceComponent{
	protected ProfileResourceComponent record;

    public ProfileResourceDecorator(ProfileResourceComponent record) {
        this.record = record;
    }

    public HashMap<String, Object> createProfile(VMJExchange vmjExchange){
		return record.createProfile(vmjExchange);
	}

    public HashMap<String, Object> updateProfile(VMJExchange vmjExchange){
		return record.updateProfile(vmjExchange);
	}

    public HashMap<String, Object> getProfile(VMJExchange vmjExchange){
		return record.getProfile(vmjExchange);
	}

    public List<HashMap<String,Object>> getAllProfile(VMJExchange vmjExchange){
		return record.getAllProfile(vmjExchange);
	}

    public List<HashMap<String,Object>> deleteProfile(VMJExchange vmjExchange){
		return record.deleteProfile(vmjExchange);
	}

	public String showProfile(int userId) {
		return record.showProfile(userId);
	}

	public boolean editProfile(int userId, String name, String email, String phoneNum) {
		return record.editProfile(userId, name, email, phoneNum);
	}
}
