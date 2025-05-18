package SIPH.payment.core;

import java.util.*;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import SIPH.booking.core.*;

@Entity(name = "payment_impl")
@Table(name = "payment_impl")
public class PaymentImpl extends PaymentComponent {

    public PaymentImpl(
        UUID userId,
        UUID bookingId,
        BigDecimal totalAmount,
        String status,
        String paymentMethod,
        // BookingImpl bookingimpl,
        UUID id
    ) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        // this.bookingimpl = bookingimpl;
        this.id = id;
    }

    // public PaymentImpl(
    //     UUID userId,
    //     UUID bookingId,
    //     BigDecimal totalAmount,
    //     String status,
    //     String paymentMethod,
    //     BookingImpl bookingimpl
    // ) {
    //     this(userId, bookingId, totalAmount, status, paymentMethod, bookingimpl, UUID.randomUUID());
    // }

    public PaymentImpl() {
        super();
    }

    @Override
    public void processPayment() {
        System.out.println("Processing payment for ID: " + id);
        this.status = "settlement";
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> paymentMap = new HashMap<>();
        paymentMap.put("userId", getUserId());
        paymentMap.put("bookingId", getBookingId());
        paymentMap.put("totalAmount", getTotalAmount());
        paymentMap.put("status", getStatus());
        paymentMap.put("paymentMethod", getPaymentMethod());
        paymentMap.put("bookingimpl", getBookingimpl());
        paymentMap.put("id", getId());
        return paymentMap;
    }
    
    @Override
    public BookingImpl getBookingimpl() {
        return this.bookingimpl;
    }

    @Override
    public void setBookingimpl(BookingImpl bookingimpl) {
        this.bookingimpl = bookingimpl;
    }

}
