package SIPH.payment.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="payment_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PaymentComponent implements Payment{
	@Id
	protected int userId; protected int bookingId; 
	protected int userId;
	protected int bookingId;
	protected Real totalAmount;
	protected String status;
	protected String paymentMethod;
	@ManyToOne(targetEntity=SIPH..core.Component.class)
	public  bookingimpl;
	protected String objectName = PaymentComponent.class.getName();

	public PaymentComponent() {

	} 

	public PaymentComponent(
        int userId, int bookingId, Real totalAmount, String status, String paymentMethod, BookingImpl bookingimpl
    ) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.bookingimpl = bookingimpl;
    }

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
	public abstract BookingImpl getBookingimpl();
	public abstract void setBookingimpl(BookingImpl bookingimpl);
	
 
	private abstract void processPayment();

	@Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            " bookingId='" + getBookingId() + "'" +
            " totalAmount='" + getTotalAmount() + "'" +
            " status='" + getStatus() + "'" +
            " paymentMethod='" + getPaymentMethod() + "'" +
            " bookingimpl='" + getBookingimpl() + "'" +
            "}";
    }
	
}
