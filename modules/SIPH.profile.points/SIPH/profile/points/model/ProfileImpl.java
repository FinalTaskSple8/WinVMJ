package SIPH.profile.points;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import SIPH.profile.core.ProfileDecorator;
import SIPH.profile.core.Profile;
import SIPH.profile.core.ProfileComponent;

@Entity(name="profile_points")
@Table(name="profile_points")
public class ProfileImpl extends ProfileDecorator {

	protected int point;
	protected String milestoneLevel;
	public ProfileImpl(
        super();
        this.objectName = ProfileImpl.class.getName();
    }
    
    public ProfileImpl(int point, String milestoneLevel) {
    	super();
		this.point = point;
		this.milestoneLevel = milestoneLevel;
		this.objectName = ProfileImpl.class.getName();
    }
	
	public ProfileImpl(ProfileComponent record, int point, String milestoneLevel) {
		super(record);
		this.point = point;
		this.milestoneLevel = milestoneLevel;
		this.objectName = ProfileImpl.class.getName();
	}

	public int getPoint() {
		return this.point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	public String getMilestoneLevel() {
		return this.milestoneLevel;
	}

	public void setMilestoneLevel(String milestoneLevel) {
		this.milestoneLevel = milestoneLevel;
	}

	private int getPoints() {
		// TODO: implement this method
	}

	private void redeemPoints(String redeem) {
		// TODO: implement this method
	}

	private void calculatePoints() {
		// TODO: implement this method
	}

}
