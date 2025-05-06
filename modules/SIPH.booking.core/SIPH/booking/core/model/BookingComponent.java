package SIPH.booking.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="booking_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BookingComponent implements Booking{
	@Id
	protected UUID bookingId; protected UUID userId; protected UUID roomId; protected UUID paymentId; protected UUID id; 
	protected UUID bookingId;
	protected UUID userId;
	protected EDate checkInDate;
	protected EDate checkOutDate;
	protected EDate numberOfGuests;
	protected EDate totalPrice;
	protected String status;
	protected UUID roomId;
	protected UUID paymentId;
	@ManyToOne(targetEntity=SIPH..core.Component.class)
	public  roomimpl;
	protected String objectName = BookingComponent.class.getName();

	public BookingComponent() {

	} 

	public BookingComponent(
        UUID bookingId, UUID userId, EDate checkInDate, EDate checkOutDate, EDate numberOfGuests, EDate totalPrice, String status, UUID roomId, UUID paymentId, RoomImpl roomimpl, UUID id
    ) {
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
	public abstract RoomImpl getRoomimpl();
	public abstract void setRoomimpl(RoomImpl roomimpl);
	
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
 
	public abstract void cancelBooking();

	public abstract Real calculateTotalPrice();

	@Override
    public String toString() {
        return "{" +
            " bookingId='" + getBookingId() + "'" +
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
