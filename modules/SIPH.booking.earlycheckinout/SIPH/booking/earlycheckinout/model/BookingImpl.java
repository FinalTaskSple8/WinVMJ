package SIPH.booking.earlycheckinout;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import SIPH.booking.core.BookingDecorator;
import SIPH.booking.core.Booking;
import SIPH.booking.core.BookingComponent;

@Entity(name="booking_earlycheckinout")
@Table(name="booking_earlycheckinout")
public class BookingImpl extends BookingDecorator {

	protected boolean earlyCheckIn;
	protected boolean lateCheckOut;
	protected Real earlyCheckInFee;
	protected Real lateCheckOutFee;
	public BookingImpl(
        super();
        this.objectName = BookingImpl.class.getName();
    }
    
    public BookingImpl(boolean earlyCheckIn, boolean lateCheckOut, Real earlyCheckInFee, Real lateCheckOutFee) {
    	super();
		this.earlyCheckIn = earlyCheckIn;
		this.lateCheckOut = lateCheckOut;
		this.earlyCheckInFee = earlyCheckInFee;
		this.lateCheckOutFee = lateCheckOutFee;
		this.objectName = BookingImpl.class.getName();
    }
	
	public BookingImpl(BookingComponent record, boolean earlyCheckIn, boolean lateCheckOut, Real earlyCheckInFee, Real lateCheckOutFee) {
		super(record);
		this.earlyCheckIn = earlyCheckIn;
		this.lateCheckOut = lateCheckOut;
		this.earlyCheckInFee = earlyCheckInFee;
		this.lateCheckOutFee = lateCheckOutFee;
		this.objectName = BookingImpl.class.getName();
	}

	public boolean getEarlyCheckIn() {
		return this.earlyCheckIn;
	}

	public void setEarlyCheckIn(boolean earlyCheckIn) {
		this.earlyCheckIn = earlyCheckIn;
	}
	public boolean getLateCheckOut() {
		return this.lateCheckOut;
	}

	public void setLateCheckOut(boolean lateCheckOut) {
		this.lateCheckOut = lateCheckOut;
	}
	public Real getEarlyCheckInFee() {
		return this.earlyCheckInFee;
	}

	public void setEarlyCheckInFee(Real earlyCheckInFee) {
		this.earlyCheckInFee = earlyCheckInFee;
	}
	public Real getLateCheckOutFee() {
		return this.lateCheckOutFee;
	}

	public void setLateCheckOutFee(Real lateCheckOutFee) {
		this.lateCheckOutFee = lateCheckOutFee;
	}

	private void requestEarlyCheckIn(Real fee) {
		// TODO: implement this method
	}

	private void requestLateCheckOut(Real fee) {
		// TODO: implement this method
	}

	private Real calculateTotalPrice() {
		// TODO: implement this method
	}

}
