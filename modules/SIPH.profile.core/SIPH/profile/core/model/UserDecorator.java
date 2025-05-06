package SIPH.profile.core;

import java.util.*;
import javax.persistence.*;

@Entity(name = "user_decorator_impl")
@Table(name = "user_decorator_impl")
public class UserDecorator extends UserComponent {

    public UserDecorator(UUID userId, String name, String email, String phoneNum) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public UserDecorator(String name, String email, String phoneNum) {
        this.userId = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public UserDecorator() {
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
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", getUserId());
        map.put("name", getName());
        map.put("email", getEmail());
        map.put("phoneNum", getPhoneNum());
        return map;
    }
}
