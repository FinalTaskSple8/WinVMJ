package SIPH.payment.core;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import java.util.*;

public interface Payment {
	public UUID getUserId();
	public void setUserId(UUID userId);
	public UUID getBookingId();
	public void setBookingId(UUID bookingId);
	public Real getTotalAmount();
	public void setTotalAmount(Real totalAmount);
	public String getStatus();
	public void setStatus(String status);
	public String getPaymentMethod();
	public void setPaymentMethod(String paymentMethod);
	public BookingImpl getBookingimpl();
	public void setBookingimpl(BookingImpl bookingimpl);
	public UUID getId();
	public void setId(UUID id);
	HashMap<String, Object> toHashMap();
}
