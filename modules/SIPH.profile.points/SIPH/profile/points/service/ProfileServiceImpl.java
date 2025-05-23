package SIPH.profile.points;

import java.util.*;

import vmj.routing.route.VMJExchange;

import SIPH.profile.core.ProfileServiceDecorator;
import SIPH.profile.core.ProfileImpl;
import SIPH.profile.core.ProfileServiceComponent;

public class ProfileServiceImpl extends ProfileServiceDecorator {
    public ProfileServiceImpl (ProfileServiceComponent record) {
        super(record);
    }

    @Override
    public void addPoints(String id, int amount) {
        Profile profile = getProfileById(id);
        profile.addPoints(amount);
    }

    @Override
    public void redeemPoints(String id, int amount) {
        Profile profile = getProfileById(id);
        profile.redeemPoints(amount);
    }

}
