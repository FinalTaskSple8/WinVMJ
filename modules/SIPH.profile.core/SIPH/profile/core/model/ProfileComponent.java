package SIPH.profile.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import vmj.auth.model.core.User;
import vmj.auth.model.core.UserComponent;

@Entity
@Table(name="profile_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProfileComponent implements Profile{
	@Id
	protected UUID id;
	protected UUID userId; 
	
	protected String objectName = ProfileComponent.class.getName();

	public ProfileComponent() {
	} 

	public ProfileComponent(
        UUID userId
    ) {
        this.userId = userId;
        this.id = UUID.randomUUID();
    }

	public UUID getUserId() {
		return this.userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
 
	public abstract String showProfile(int userId);

	public abstract boolean editProfile(int userId, String name, String email, String phoneNum);

	@Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            " id='" + getId() + "'" +
            "}";
    }
	
}
