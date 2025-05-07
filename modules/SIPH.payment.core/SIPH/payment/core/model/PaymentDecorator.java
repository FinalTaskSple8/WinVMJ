package SIPH.payment.core;

import java.util.*;
import java.math.BigDecimal;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@MappedSuperclass
public abstract class PaymentDecorator extends PaymentComponent {

    @OneToOne(cascade = CascadeType.ALL)
    protected PaymentComponent record;

    public PaymentDecorator() {
        super();
        this.id = UUID.randomUUID();
    }

    public PaymentDecorator(PaymentComponent record) {
        this();
        this.record = record;
    }

    public PaymentDecorator(UUID userId, UUID bookingId, UUID id, PaymentComponent record) {
        this.record = record;
        this.userId = userId;
        this.bookingId = bookingId;
        this.id = id;
    }

    public PaymentDecorator(PaymentComponent record, String objectName) {
        this(record);
        this.objectName = objectName;
    }

    @Override
    public UUID getUserId() {
        return record.getUserId();
    }

    @Override
    public void setUserId(UUID userId) {
        record.setUserId(userId);
    }

    @Override
    public UUID getBookingId() {
        return record.getBookingId();
    }

    @Override
    public void setBookingId(UUID bookingId) {
        record.setBookingId(bookingId);
    }

    @Override
    public BigDecimal getTotalAmount() {
        return record.getTotalAmount();
    }

    @Override
    public void setTotalAmount(BigDecimal totalAmount) {
        record.setTotalAmount(totalAmount);
    }

    @Override
    public String getStatus() {
        return record.getStatus();
    }

    @Override
    public void setStatus(String status) {
        record.setStatus(status);
    }

    @Override
    public String getPaymentMethod() {
        return record.getPaymentMethod();
    }

    @Override
    public void setPaymentMethod(String paymentMethod) {
        record.setPaymentMethod(paymentMethod);
    }

    @Override
    public UUID getId() {
        return record.getId();
    }

    @Override
    public void setId(UUID id) {
        record.setId(id);
    }

    @Override
    public void processPayment() {
        record.processPayment();
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        return record.toHashMap();
    }
}
