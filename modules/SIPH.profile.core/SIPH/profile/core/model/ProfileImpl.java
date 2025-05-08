package SIPH.profile.core;

import java.util.*;
import javax.persistence.*;
import vmj.auth.model.core.User;
import vmj.auth.model.core.UserComponent;

@Entity(name = "profile_impl")
@Table(name = "profile_impl")
public class ProfileImpl extends ProfileComponent {

    @Column(name = "name")
    protected String name;

    @Column(name = "email")
    protected String email;

    @Column(name = "phone_num")
    protected String phoneNum;

    public ProfileImpl(UUID userId, User user, UUID id) {
        this.userId = userId;
        this.user = user;
        this.id = id;
    }

    public ProfileImpl(UUID userId, User user) {
        this.userId = UUID.randomUUID();
        this.user = user;
    }

    public ProfileImpl() {
        this.userId = UUID.randomUUID();
    }

    @Override
    public UUID getUserId() {
        return this.userId;
    }

    @Override
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhoneNum() {
        return this.phoneNum;
    }

    @Override
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String showProfile(int userId) {
        // implement as needed
        return "Profile for userId: " + userId;
    }

    @Override
    public boolean editProfile(int userId, String name, String email, String phoneNum) {
        if (this.userId.hashCode() == userId) {
            setName(name);
            setEmail(email);
            setPhoneNum(phoneNum);
            return true;
        }
        return false;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> profileMap = new HashMap<>();
        profileMap.put("userId", getUserId());
        profileMap.put("user", getUser());
        profileMap.put("id", getId());
        profileMap.put("name", getName());
        profileMap.put("email", getEmail());
        profileMap.put("phoneNum", getPhoneNum());
        return profileMap;
    }
}
