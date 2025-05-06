package SIPH.profile.points;

import java.util.*;
import javax.persistence.*;

import SIPH.profile.core.ProfileDecorator;
import SIPH.profile.core.Profile;
import SIPH.profile.core.ProfileComponent;

@Entity(name = "profile_points")
@Table(name = "profile_points")
public class ProfileImpl extends ProfileDecorator {

    @Column(name = "point")
    protected int point;

    @Column(name = "milestone_level")
    protected String milestoneLevel;

    public ProfileImpl() {
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

    public int getPoints() {
        // TODO: implement logic if needed
        return this.point;
    }

    public void redeemPoints(String redeem) {
        // TODO: implement redeem logic
    }

    public void calculatePoints() {
        // TODO: implement calculation logic
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = super.toHashMap();
        map.put("point", this.point);
        map.put("milestoneLevel", this.milestoneLevel);
        return map;
    }
    
    @Override
    public SIPH.profile.core.User getUser() {
        return record.getUser(); // delegasi ke record
    }

    @Override
    public void setUser(SIPH.profile.core.User user) {
        record.setUser(user);
    }
    
    @Override
    public String getPhoneNum() {
        return record.getPhoneNum();
    }

    @Override
    public void setPhoneNum(String phoneNum) {
        record.setPhoneNum(phoneNum);
    }

    @Override
    public String getEmail() {
        return record.getEmail();
    }

    @Override
    public void setEmail(String email) {
        record.setEmail(email);
    }

    @Override
    public String getName() {
        return record.getName();
    }

    @Override
    public void setName(String name) {
        record.setName(name);
    }


}
