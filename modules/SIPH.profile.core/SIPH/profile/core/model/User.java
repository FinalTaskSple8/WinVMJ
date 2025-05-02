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


@Entity(name="_impl")
@Table(name="_impl")
public class User extends User {

	public User(int userId, String name, String email, String phoneNum) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
	}

	public User(int userId, String name, String email, String phoneNum) {
		this.userId =  userId.randomUUID();;
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
	}

	public User() { }

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	
	public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> Map = new HashMap<String,Object>();
		Map.put("userId",getUserId());
		Map.put("name",getName());
		Map.put("email",getEmail());
		Map.put("phoneNum",getPhoneNum());

        return Map;
    }

}
