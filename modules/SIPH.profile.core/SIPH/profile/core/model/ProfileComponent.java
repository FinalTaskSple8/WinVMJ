package SIPH.profile.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="profile_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProfileComponent implements Profile{
	@Id
	
	@ManyToOne(targetEntity=SIPH..core.Component.class)
	protected  user;
	@ManyToOne(targetEntity=SIPH..core.Component.class)
	public  user;
	protected String objectName = ProfileComponent.class.getName();

	public ProfileComponent() {

	} 

	public ProfileComponent(
        User user, User user
    ) {
        this.user = user;
        this.user = user;
    }

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public abstract User getUser();
	public abstract void setUser(User user);
	
 
	private abstract String showProfile(int userId);

	private abstract boolean editProfile(int userId, String name, String email, String phoneNum);

	@Override
    public String toString() {
        return "{" +
            " user='" + getUser() + "'" +
            " user='" + getUser() + "'" +
            "}";
    }
	
}
