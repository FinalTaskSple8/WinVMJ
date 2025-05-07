package SIPH.payment.core;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import java.util.*;
import java.math.BigDecimal; 
import SIPH.booking.core.BookingImpl;

public interface Payment {
    UUID getUserId();
    void setUserId(UUID userId);
    UUID getBookingId();
    void setBookingId(UUID bookingId);
    BigDecimal getTotalAmount();
    void setTotalAmount(BigDecimal totalAmount);
    String getStatus();
    void setStatus(String status);
    String getPaymentMethod();
    void setPaymentMethod(String paymentMethod);
    BookingImpl getBookingimpl(); 
    void setBookingimpl(BookingImpl bookingimpl);
    UUID getId();
    void setId(UUID id);
    HashMap<String, Object> toHashMap();
    void processPayment();
}
