package SIPH.profile.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

public abstract class ProfileResourceDecorator extends ProfileResourceComponent{
	protected ProfileResourceComponent record;

    public ProfileResourceDecorator(ProfileResourceComponent record) {
        this.record = record;
    }

    public List<HashMap<String,Object>> saveProfile(VMJExchange vmjExchange){
		return record.saveProfile(vmjExchange);
	}

    public Profile createProfile(VMJExchange vmjExchange){
		return record.createProfile(vmjExchange);
	}

    public Profile createProfile(VMJExchange vmjExchange, int id){
		return record.createProfile(vmjExchange, id);
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

	private String showProfile(int userId) {
		return record.showProfile(userId);
	}

	private boolean editProfile(int userId, String name, String email, String phoneNum) {
		return record.editProfile(userId, name, email, phoneNum);
	}
}
