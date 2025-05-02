package SIPH.profile.core;

import java.lang.Math;
import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name="profile_impl")
@Table(name="profile_impl")
public class ProfileImpl extends ProfileComponent {

	public ProfileImpl(User user, User user) {
		this.user = user;
		this.user = user;
	}

	public ProfileImpl(User user, User user) {
		this. =  .randomUUID();;
		this.user = user;
		this.user = user;
	}

	public ProfileImpl() { }

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String showProfile(int userId) {
		// TODO: implement this method
	}

	private boolean editProfile(int userId, String name, String email, String phoneNum) {
		// TODO: implement this method
	}
	
	public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> profileMap = new HashMap<String,Object>();
		profileMap.put("user",getUser());
		profileMap.put("user",getUser());

        return profileMap;
    }

}
