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

	public PaymentImpl(int userId, int bookingId, Real totalAmount, String status, String paymentMethod, BookingImpl bookingimpl) {
		this.userId = userId;
		this.bookingId = bookingId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.bookingimpl = bookingimpl;
	}

	public PaymentImpl(int userId, int bookingId, Real totalAmount, String status, String paymentMethod, BookingImpl bookingimpl) {
		this.userIdbookingId =  userIdbookingId.randomUUID();;
		this.userId = userId;
		this.bookingId = bookingId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.bookingimpl = bookingimpl;
	}

	public PaymentImpl() { }

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(int bookingId) {
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

	private void processPayment() {
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

        return paymentMap;
    }

}
