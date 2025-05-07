package SIPH.payment.onlinepayment;

import java.util.*;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

import SIPH.payment.core.PaymentDecorator;
import SIPH.payment.core.Payment;
import SIPH.payment.core.PaymentComponent;
import SIPH.booking.core.BookingImpl;
@Entity(name="payment_onlinepayment")
@Table(name="payment_onlinepayment")
public class PaymentImpl extends PaymentDecorator {

    protected String gateway;

    public PaymentImpl() {
        super();
        this.objectName = PaymentImpl.class.getName();
    }

    public PaymentImpl(String gateway) {
        super();
        this.gateway = gateway;
        this.objectName = PaymentImpl.class.getName();
    }

    public PaymentImpl(PaymentComponent record, String gateway) {
        super(record);
        this.gateway = gateway;
        this.objectName = PaymentImpl.class.getName();
    }
    
    public PaymentImpl(UUID userId, UUID bookingId, BigDecimal totalAmount, String status, String paymentMethod, BookingImpl bookingimpl, String gateway) {
        super(new SIPH.payment.core.PaymentImpl(userId, bookingId, totalAmount, status, paymentMethod, bookingimpl, UUID.randomUUID()));
        this.gateway = gateway;
        this.objectName = PaymentImpl.class.getName();
    }


    public String getGateway() {
        return this.gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
    
    @Override
    public BookingImpl getBookingimpl() {
        return record.getBookingimpl();
    }

    @Override
    public void setBookingimpl(BookingImpl bookingimpl) {
        record.setBookingimpl(bookingimpl);
    }


    @Override
    public void processPayment() {
        System.out.println("Processing online payment via: " + gateway);
    }

    public void authenticate() {
        System.out.println("Authenticating payment through gateway: " + gateway);
    }
} 

// You may follow similar patterns to complete BookingResourceImpl and PaymentServiceImpl if needed.
