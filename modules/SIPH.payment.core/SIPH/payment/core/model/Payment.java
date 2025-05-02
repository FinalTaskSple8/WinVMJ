package SIPH.payment.core;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import java.util.*;

public interface Payment {
	public int getUserId();
	public void setUserId(int userId);
	public int getBookingId();
	public void setBookingId(int bookingId);
	public Real getTotalAmount();
	public void setTotalAmount(Real totalAmount);
	public String getStatus();
	public void setStatus(String status);
	public String getPaymentMethod();
	public void setPaymentMethod(String paymentMethod);
	public BookingImpl getBookingimpl();
	public void setBookingimpl(BookingImpl bookingimpl);
	org.eclipse.uml2.uml.internal.impl.ClassImpl@16d59837 (name: PaymentImpl, visibility: <unset>) (isLeaf: false, isAbstract: false, isFinalSpecialization: false) (isActive: false)
	HashMap<String, Object> toHashMap();
}
