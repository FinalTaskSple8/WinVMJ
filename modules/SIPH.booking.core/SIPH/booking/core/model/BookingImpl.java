package SIPH.booking.core;

import java.lang.Math;
import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name="booking_impl")
@Table(name="booking_impl")
public class BookingImpl extends BookingComponent {

	public BookingImpl(int bookingId, int userId, EDate checkInDate, EDate checkOutDate, EDate numberOfGuests, EDate totalPrice, String status, int roomId, int paymentId, RoomImpl roomimpl) {
		this.bookingId = bookingId;
		this.userId = userId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numberOfGuests = numberOfGuests;
		this.totalPrice = totalPrice;
		this.status = status;
		this.roomId = roomId;
		this.paymentId = paymentId;
		this.roomimpl = roomimpl;
	}

	public BookingImpl(int bookingId, int userId, EDate checkInDate, EDate checkOutDate, EDate numberOfGuests, EDate totalPrice, String status, int roomId, int paymentId, RoomImpl roomimpl) {
		this.bookingIduserIdroomIdpaymentId =  bookingIduserIdroomIdpaymentId.randomUUID();;
		this.bookingId = bookingId;
		this.userId = userId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numberOfGuests = numberOfGuests;
		this.totalPrice = totalPrice;
		this.status = status;
		this.roomId = roomId;
		this.paymentId = paymentId;
		this.roomimpl = roomimpl;
	}

	public BookingImpl() { }

	public int getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public EDate getCheckInDate() {
		return this.checkInDate;
	}

	public void setCheckInDate(EDate checkInDate) {
		this.checkInDate = checkInDate;
	}
	public EDate getCheckOutDate() {
		return this.checkOutDate;
	}

	public void setCheckOutDate(EDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public EDate getNumberOfGuests() {
		return this.numberOfGuests;
	}

	public void setNumberOfGuests(EDate numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	public EDate getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(EDate totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public int getRoomId() {
		return this.roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	private void cancelBooking() {
		// TODO: implement this method
	}

	private Real calculateTotalPrice() {
		// TODO: implement this method
	}
	
	public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> bookingMap = new HashMap<String,Object>();
		bookingMap.put("bookingId",getBookingId());
		bookingMap.put("userId",getUserId());
		bookingMap.put("checkInDate",getCheckInDate());
		bookingMap.put("checkOutDate",getCheckOutDate());
		bookingMap.put("numberOfGuests",getNumberOfGuests());
		bookingMap.put("totalPrice",getTotalPrice());
		bookingMap.put("status",getStatus());
		bookingMap.put("roomId",getRoomId());
		bookingMap.put("paymentId",getPaymentId());
		bookingMap.put("roomimpl",getRoomimpl());

        return bookingMap;
    }

}
