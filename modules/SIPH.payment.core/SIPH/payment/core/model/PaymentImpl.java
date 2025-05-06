package SIPH.payment.core;

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


@Entity(name="payment_impl")
@Table(name="payment_impl")
public class PaymentImpl extends PaymentComponent {

	public PaymentImpl(UUID userId, UUID bookingId, Real totalAmount, String status, String paymentMethod, BookingImpl bookingimpl, UUID id) {
		this.userId = userId;
		this.bookingId = bookingId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.bookingimpl = bookingimpl;
		this.id = id;
	}

	public PaymentImpl(UUID userId, UUID bookingId, Real totalAmount, String status, String paymentMethod, BookingImpl bookingimpl) {
		this.userIdbookingIdid =  userIdbookingIdid.randomUUID();;
		this.userId = userId;
		this.bookingId = bookingId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.bookingimpl = bookingimpl;
	}

	public PaymentImpl() { }

	public UUID getUserId() {
		return this.userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public UUID getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(UUID bookingId) {
		this.bookingId = bookingId;
	}
	public Real getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Real totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void processPayment() {
		// TODO: implement this method
	}
	
	public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> paymentMap = new HashMap<String,Object>();
		paymentMap.put("userId",getUserId());
		paymentMap.put("bookingId",getBookingId());
		paymentMap.put("totalAmount",getTotalAmount());
		paymentMap.put("status",getStatus());
		paymentMap.put("paymentMethod",getPaymentMethod());
		paymentMap.put("bookingimpl",getBookingimpl());
		paymentMap.put("id",getId());

        return paymentMap;
    }

}
