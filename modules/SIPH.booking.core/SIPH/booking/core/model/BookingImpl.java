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
import java.time.LocalDate;
import java.math.BigDecimal;
import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;


@Entity(name="booking_impl")
@Table(name="booking_impl")
public class BookingImpl extends BookingComponent {

    public BookingImpl(UUID userId, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests, BigDecimal totalPrice, String status, UUID roomId, UUID paymentId, RoomImpl roomimpl, UUID id) {
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

    public BookingImpl(UUID userId, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests, BigDecimal totalPrice, String status, UUID roomId, UUID paymentId, RoomImpl roomimpl) {
        this.id = UUID.randomUUID();
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

    public UUID getUserId() {
        return this.userId;
    }
    
    public RoomImpl getRoomimpl() {
        return this.roomimpl;
    }

    public void setRoomimpl(RoomImpl roomimpl) {
        this.roomimpl = roomimpl;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    
    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    
    public LocalDate getCheckOutDate() {
        return this.checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
    public int getNumberOfGuests() {
        return this.numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
    
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
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
        this.status = "CANCELLED";
    }

    public BigDecimal calculateTotalPrice() {
		// Calculate number of days
		long days = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
		if (days <= 0) {
			days = 1;
		}
		
		// Get base price per room
		BigDecimal basePrice = BigDecimal.valueOf(100); // Default base price per room per day
		if (roomimpl != null) {
			// If room price is available, use it instead
			basePrice = roomimpl.getPrice();
		}
		
		// Calculate number of rooms needed (each room fits max 2 guests)
		int numberOfRooms = (int) Math.ceil(numberOfGuests / 2.0);
		if (numberOfRooms < 1) {
			numberOfRooms = 1; // Minimum 1 room regardless of guests
		}
		
		// Calculate total price: base price * days * number of rooms
		return basePrice.multiply(BigDecimal.valueOf(days * numberOfRooms));
	}

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> bookingMap = new HashMap<String,Object>();
        bookingMap.put("userId", getUserId());
        bookingMap.put("checkInDate", getCheckInDate());
        bookingMap.put("checkOutDate", getCheckOutDate());
        bookingMap.put("numberOfGuests", getNumberOfGuests());
        bookingMap.put("totalPrice", getTotalPrice());
        bookingMap.put("status", getStatus());
        bookingMap.put("roomId", getRoomId());
        bookingMap.put("paymentId", getPaymentId());
        bookingMap.put("roomimpl", getRoomimpl());
        bookingMap.put("id", getId());

        return bookingMap;
    }
}