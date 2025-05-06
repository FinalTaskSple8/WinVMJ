package SIPH.payment.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.OneToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.CascadeType;
//add other required packages

@MappedSuperclass
public abstract class PaymentDecorator extends PaymentComponent{
    @OneToOne(cascade=CascadeType.ALL)
	protected PaymentComponent record;

	public PaymentDecorator () {
		super();
		this.record = record;
		this.userIdbookingIdid =  userIdbookingIdid.randomUUID();
		
	public PaymentDecorator (PaymentComponent record) {
		this.userIdbookingIdid =  userIdbookingIdid.randomUUID();
		this.record = record;
	}

	public PaymentDecorator (UUID userIdUUID bookingIdUUID id, PaymentComponent record) {
		this.userIdbookingIdid =  userIdbookingIdid;
		this.record = record;
	}
	
	public PaymentDecorator (PaymentComponent record, String objectName) {
		this.userIdbookingIdid =  userIdbookingIdid.randomUUID();
		this.record = record;	
		this.objectName=objectName;
	}

	public PaymentDecorator() { }

	public UUID getUserId() {
		return record.getUserId();
	}
	public void setUserId(UUID userId) {
		record.setUserId(userId);
	}
	public UUID getBookingId() {
		return record.getBookingId();
	}
	public void setBookingId(UUID bookingId) {
		record.setBookingId(bookingId);
	}
	public Real getTotalAmount() {
		return record.getTotalAmount();
	}
	public void setTotalAmount(Real totalAmount) {
		record.setTotalAmount(totalAmount);
	}
	public String getStatus() {
		return record.getStatus();
	}
	public void setStatus(String status) {
		record.setStatus(status);
	}
	public String getPaymentMethod() {
		return record.getPaymentMethod();
	}
	public void setPaymentMethod(String paymentMethod) {
		record.setPaymentMethod(paymentMethod);
	}
	public UUID getId() {
		return record.getId();
	}
	public void setId(UUID id) {
		record.setId(id);
	}

	public void processPayment() {
		return record.processPayment();
	}

	public HashMap<String, Object> toHashMap() {
        return this.record.toHashMap();
    }

}
