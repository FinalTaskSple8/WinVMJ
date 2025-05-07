package SIPH.payment.core;

import java.util.*;
import java.math.BigDecimal;

import javax.persistence.*;

import SIPH.booking.core.BookingImpl;

@Entity
@Table(name = "payment_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PaymentComponent implements Payment {

    @Id
    protected UUID id;

    protected UUID userId;
    protected UUID bookingId;

    protected BigDecimal totalAmount; // GANTI Real → BigDecimal
    protected String status;
    protected String paymentMethod;

    @ManyToOne(targetEntity = BookingImpl.class)
    protected BookingImpl bookingimpl;

    protected String objectName = PaymentComponent.class.getName();

    public PaymentComponent() {}

    public PaymentComponent(
        UUID userId, UUID bookingId, BigDecimal totalAmount,
        String status, String paymentMethod, BookingImpl bookingimpl, UUID id
    ) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.bookingimpl = bookingimpl;
        this.id = id;
    }

    @Override
    public UUID getUserId() {
        return this.userId;
    }

    @Override
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public UUID getBookingId() {
        return this.bookingId;
    }

    @Override
    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    @Override
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    @Override
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public abstract BookingImpl getBookingimpl();

    @Override
    public abstract void setBookingimpl(BookingImpl bookingimpl);

    @Override
    public abstract void processPayment();

    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", bookingId='" + getBookingId() + "'" +
            ", totalAmount='" + getTotalAmount() + "'" +
            ", status='" + getStatus() + "'" +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", bookingimpl='" + getBookingimpl() + "'" +
            ", id='" + getId() + "'" +
            "}";
    }
}
