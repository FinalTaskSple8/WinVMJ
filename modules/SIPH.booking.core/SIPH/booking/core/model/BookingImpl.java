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

	public BookingImpl(UUID bookingId, UUID userId, EDate checkInDate, EDate checkOutDate, EDate numberOfGuests, EDate totalPrice, String status, UUID roomId, UUID paymentId, RoomImpl roomimpl, UUID id) {
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
		this.id = id;
	}

	public BookingImpl(UUID bookingId, UUID userId, EDate checkInDate, EDate checkOutDate, EDate numberOfGuests, EDate totalPrice, String status, UUID roomId, UUID paymentId, RoomImpl roomimpl) {
		this.bookingIduserIdroomIdpaymentIdid =  bookingIduserIdroomIdpaymentIdid.randomUUID();;
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

	public UUID getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(UUID bookingId) {
		this.bookingId = bookingId;
	}
	public UUID getUserId() {
		return this.userId;
	}

	public void setUserId(UUID userId) {
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
	public UUID getRoomId() {
		return this.roomId;
	}

	public void setRoomId(UUID roomId) {
		this.roomId = roomId;
	}
	public UUID getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(UUID paymentId) {
		this.paymentId = paymentId;
	}
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void cancelBooking() {
		// TODO: implement this method
	}

	public Real calculateTotalPrice() {
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
		bookingMap.put("id",getId());

        return bookingMap;
    }

}
