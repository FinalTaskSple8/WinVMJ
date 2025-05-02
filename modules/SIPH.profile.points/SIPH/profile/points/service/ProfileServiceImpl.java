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
