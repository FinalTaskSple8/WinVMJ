package SIPH.booking.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public interface BookingResource {
    List<HashMap<String,Object>> saveBooking(VMJExchange vmjExchange);
    HashMap<String, Object> updateBooking(VMJExchange vmjExchange);
    HashMap<String, Object> getBooking(VMJExchange vmjExchange);
    List<HashMap<String,Object>> getAllBooking(VMJExchange vmjExchange);
    List<HashMap<String,Object>> deleteBooking(VMJExchange vmjExchange);
	HashMap<String, Object> Booking(VMJExchange vmjExhange);
}
