package SIPH.profile.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.OneToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.CascadeType;
//add other required packages

@MappedSuperclass
public abstract class ProfileDecorator extends ProfileComponent{
    @OneToOne(cascade=CascadeType.ALL)
	protected ProfileComponent record;

	public ProfileDecorator () {
		super();
		this.record = record;
		this. =  .randomUUID();
		
	public ProfileDecorator (ProfileComponent record) {
		this. =  .randomUUID();
		this.record = record;
	}

	public ProfileDecorator (, ProfileComponent record) {
		this. =  ;
		this.record = record;
	}
	
	public ProfileDecorator (ProfileComponent record, String objectName) {
		this. =  .randomUUID();
		this.record = record;	
		this.objectName=objectName;
	}

	public ProfileDecorator() { }

	public User getUser() {
		return record.getUser();
	}
	public void setUser(User user) {
		record.setUser(user);
	}

	private String showProfile(int userId) {
		return record.showProfile(userId);
	}

	private boolean editProfile(int userId, String name, String email, String phoneNum) {
		return record.editProfile(userId, name, email, phoneNum);
	}

	public HashMap<String, Object> toHashMap() {
        return this.record.toHashMap();
    }

}
