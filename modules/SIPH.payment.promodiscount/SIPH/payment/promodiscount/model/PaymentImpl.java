package SIPH.payment.promodiscount;

import java.util.*;
import javax.persistence.*;

import SIPH.payment.core.PaymentDecorator;
import SIPH.payment.core.PaymentComponent;
import SIPH.booking.core.BookingImpl;

@Entity(name="payment_promodiscount")
@Table(name="payment_promodiscount")
public class PaymentImpl extends PaymentDecorator {

    protected String code;
    protected Double discountAmount;

    public PaymentImpl() {
        super();
        this.objectName = PaymentImpl.class.getName();
    }

    public PaymentImpl(String code, Double discountAmount) {
        super();
        this.code = code;
        this.discountAmount = discountAmount;
        this.objectName = PaymentImpl.class.getName();
    }

    public PaymentImpl(PaymentComponent record, String code, Double discountAmount) {
        super(record);
        this.code = code;
        this.discountAmount = discountAmount;
        this.objectName = PaymentImpl.class.getName();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
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
        System.out.println("Processing payment with promo code: " + code);
    }

    public void applyDiscount() {
        System.out.println("Applying discount: " + discountAmount);
    }
}
