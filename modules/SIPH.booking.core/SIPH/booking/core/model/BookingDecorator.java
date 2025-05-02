package SIPH.booking.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.OneToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.CascadeType;
//add other required packages

@MappedSuperclass
public abstract class BookingDecorator extends BookingComponent{
    @OneToOne(cascade=CascadeType.ALL)
	protected BookingComponent record;

	public BookingDecorator () {
		super();
		this.record = record;
		this.bookingIduserIdroomIdpaymentId =  bookingIduserIdroomIdpaymentId.randomUUID();
		
	public BookingDecorator (BookingComponent record) {
		this.bookingIduserIdroomIdpaymentId =  bookingIduserIdroomIdpaymentId.randomUUID();
		this.record = record;
	}

	public BookingDecorator (int bookingIdint userIdint roomIdint paymentId, BookingComponent record) {
		this.bookingIduserIdroomIdpaymentId =  bookingIduserIdroomIdpaymentId;
		this.record = record;
	}
	
	public BookingDecorator (BookingComponent record, String objectName) {
		this.bookingIduserIdroomIdpaymentId =  bookingIduserIdroomIdpaymentId.randomUUID();
		this.record = record;	
		this.objectName=objectName;
	}

	public BookingDecorator() { }

	public int getBookingId() {
		return record.getBookingId();
	}
	public void setBookingId(int bookingId) {
		record.setBookingId(bookingId);
	}
	public int getUserId() {
		return record.getUserId();
	}
	public void setUserId(int userId) {
		record.setUserId(userId);
	}
	public EDate getCheckInDate() {
		return record.getCheckInDate();
	}
	public void setCheckInDate(EDate checkInDate) {
		record.setCheckInDate(checkInDate);
	}
	public EDate getCheckOutDate() {
		return record.getCheckOutDate();
	}
	public void setCheckOutDate(EDate checkOutDate) {
		record.setCheckOutDate(checkOutDate);
	}
	public EDate getNumberOfGuests() {
		return record.getNumberOfGuests();
	}
	public void setNumberOfGuests(EDate numberOfGuests) {
		record.setNumberOfGuests(numberOfGuests);
	}
	public EDate getTotalPrice() {
		return record.getTotalPrice();
	}
	public void setTotalPrice(EDate totalPrice) {
		record.setTotalPrice(totalPrice);
	}
	public String getStatus() {
		return record.getStatus();
	}
	public void setStatus(String status) {
		record.setStatus(status);
	}
	public int getRoomId() {
		return record.getRoomId();
	}
	public void setRoomId(int roomId) {
		record.setRoomId(roomId);
	}
	public int getPaymentId() {
		return record.getPaymentId();
	}
	public void setPaymentId(int paymentId) {
		record.setPaymentId(paymentId);
	}

	private void cancelBooking() {
		return record.cancelBooking();
	}

	private Real calculateTotalPrice() {
		return record.calculateTotalPrice();
	}

	public HashMap<String, Object> toHashMap() {
        return this.record.toHashMap();
    }

}
