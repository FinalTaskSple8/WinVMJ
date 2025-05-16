package SIPH.booking.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import java.time.LocalDate;
import java.math.BigDecimal;

import javax.persistence.OneToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.CascadeType;
import SIPH.room.core.Room;

//add other required packages

@MappedSuperclass
public abstract class BookingDecorator extends BookingComponent {
    @OneToOne(cascade=CascadeType.ALL)
    protected BookingComponent record;

    public BookingDecorator() {
        super();
        this.id = UUID.randomUUID();
    }

    public BookingDecorator(BookingComponent record) {
        super();
        this.id = UUID.randomUUID();
        this.record = record;
    }

    public BookingDecorator(UUID id, BookingComponent record) {
        super();
        this.id = id;
        this.record = record;
    }

    public UUID getUserId() {
        return record.getUserId();
    }

    public void setUserId(UUID userId) {
        record.setUserId(userId);
    }

    public LocalDate getCheckInDate() {
        return record.getCheckInDate();
    }

    public void setCheckInDate(LocalDate checkInDate) {
        record.setCheckInDate(checkInDate);
    }

    public LocalDate getCheckOutDate() {
        return record.getCheckOutDate();
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        record.setCheckOutDate(checkOutDate);
    }

    public int getNumberOfGuests() {
        return record.getNumberOfGuests();
    }

    public void setNumberOfGuests(int numberOfGuests) {
        record.setNumberOfGuests(numberOfGuests);
    }

    public BigDecimal getTotalPrice() {
        return record.getTotalPrice();
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        record.setTotalPrice(totalPrice);
    }

    public String getStatus() {
        return record.getStatus();
    }

    public void setStatus(String status) {
        record.setStatus(status);
    }

    public UUID getRoomId() {
        return record.getRoomId();
    }

    public void setRoomId(UUID roomId) {
        record.setRoomId(roomId);
    }

    public UUID getPaymentId() {
        return record.getPaymentId();
    }

    public void setPaymentId(UUID paymentId) {
        record.setPaymentId(paymentId);
    }

    public UUID getId() {
        return record.getId();
    }

    public void setId(UUID id) {
        record.setId(id);
    }

    public Room getRoom() {
        return record.getRoom();
    }

    public void setRoom(Room room) {
        record.setRoom(room);
    }

    public void cancelBooking() {
        record.cancelBooking();
    }

    public BigDecimal calculateTotalPrice() {
        return record.calculateTotalPrice();
    }

    public HashMap<String, Object> toHashMap() {
        return this.record.toHashMap();
    }
}
