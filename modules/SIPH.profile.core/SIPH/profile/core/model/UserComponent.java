package SIPH.profile.core;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "user_component")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserComponent implements User {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    protected UUID userId;

    @Column(name = "name")
    protected String name;

    @Column(name = "email")
    protected String email;

    @Column(name = "phone_num")
    protected String phoneNum;

    public UserComponent() {
        this.userId = UUID.randomUUID();
    }

    public UserComponent(UUID userId, String name, String email, String phoneNum) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    @Override
    public UUID getUserId() {
        return userId;
    }

    @Override
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhoneNum() {
        return phoneNum;
    }

    @Override
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId.toString());
        map.put("name", name);
        map.put("email", email);
        map.put("phoneNum", phoneNum);
        return map;
    }

    @Override
    public String toString() {
        return "UserComponent{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
