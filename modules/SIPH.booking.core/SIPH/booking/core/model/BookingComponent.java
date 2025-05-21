package SIPH.booking.core;
import java.time.LocalDate;
import java.math.BigDecimal;

import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;
import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;

@Entity
@Table(name="booking_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BookingComponent implements Booking{
	@Id
	protected UUID id; 
	protected UUID userId; protected UUID roomId; protected UUID paymentId;
	protected LocalDate checkInDate;
	protected LocalDate checkOutDate;
	protected int numberOfGuests;
	protected BigDecimal totalPrice;
	protected String status;
	
	@ManyToOne(targetEntity=RoomImpl.class)
	public RoomImpl roomimpl;
	protected String objectName = BookingComponent.class.getName();

	public BookingComponent() {

	} 

	public BookingComponent(UUID userId, LocalDate checkInDate, LocalDate checkOutDate,
            int numberOfGuests, BigDecimal totalPrice, UUID roomId) {
		this.id = UUID.randomUUID();
		this.userId = userId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numberOfGuests = numberOfGuests;
		this.totalPrice = totalPrice;
		this.roomId = roomId;
	}

	public UUID getUserId() {
		return this.userId;
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
	public abstract RoomImpl getRoomimpl();
	public abstract void setRoomimpl(RoomImpl roomimpl);
	
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
 
	public abstract void cancelBooking();

	public abstract BigDecimal calculateTotalPrice();

	@Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            " checkInDate='" + getCheckInDate() + "'" +
            " checkOutDate='" + getCheckOutDate() + "'" +
            " numberOfGuests='" + getNumberOfGuests() + "'" +
            " totalPrice='" + getTotalPrice() + "'" +
            " status='" + getStatus() + "'" +
            " roomId='" + getRoomId() + "'" +
            " paymentId='" + getPaymentId() + "'" +
            " roomimpl='" + getRoomimpl() + "'" +
            " id='" + getId() + "'" +
            "}";
    }
	
}
