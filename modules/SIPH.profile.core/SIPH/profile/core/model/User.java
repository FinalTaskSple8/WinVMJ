package SIPH.profile.core;

import java.util.HashMap;
import java.util.UUID;

public interface User {
    UUID getUserId();
    void setUserId(UUID userId);

    String getName();
    void setName(String name);

    String getEmail();
    void setEmail(String email);

    String getPhoneNum();
    void setPhoneNum(String phoneNum);

    HashMap<String, Object> toHashMap();
}
