package SIPH.booking.roomupgrade;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import SIPH.booking.core.BookingDecorator;
import SIPH.booking.core.Booking;
import SIPH.booking.core.BookingComponent;

@Entity(name="booking_roomupgrade")
@Table(name="booking_roomupgrade")
public class BookingImpl extends BookingDecorator {

	protected String upgradedRoomType;
	protected Real upgradeCost;
	public BookingImpl(
        super();
        this.objectName = BookingImpl.class.getName();
    }
    
    public BookingImpl(String upgradedRoomType, Real upgradeCost) {
    	super();
		this.upgradedRoomType = upgradedRoomType;
		this.upgradeCost = upgradeCost;
		this.objectName = BookingImpl.class.getName();
    }
	
	public BookingImpl(BookingComponent record, String upgradedRoomType, Real upgradeCost) {
		super(record);
		this.upgradedRoomType = upgradedRoomType;
		this.upgradeCost = upgradeCost;
		this.objectName = BookingImpl.class.getName();
	}

	public String getUpgradedRoomType() {
		return this.upgradedRoomType;
	}

	public void setUpgradedRoomType(String upgradedRoomType) {
		this.upgradedRoomType = upgradedRoomType;
	}
	public Real getUpgradeCost() {
		return this.upgradeCost;
	}

	public void setUpgradeCost(Real upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	private void requestRoomUpgrade(String newRoomType, Real additionalCost) {
		// TODO: implement this method
	}

	private Real calculateTotalPrice() {
		// TODO: implement this method
	}

}
