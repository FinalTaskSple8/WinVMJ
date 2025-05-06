package SIPH.profile.core;

import java.util.*;
import javax.persistence.*;

@Entity(name = "user_impl")
@Table(name = "user_impl")
public class UserImpl extends UserComponent {

    public UserImpl(UUID userId, String name, String email, String phoneNum) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public UserImpl(String name, String email, String phoneNum) {
        this.userId = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public UserImpl() {
        this.userId = UUID.randomUUID();
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("userId", getUserId());
        userMap.put("name", getName());
        userMap.put("email", getEmail());
        userMap.put("phoneNum", getPhoneNum());
        return userMap;
    }

    @Override
    public String toString() {
        return "UserImpl{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
