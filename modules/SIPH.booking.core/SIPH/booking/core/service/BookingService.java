package SIPH.booking.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public interface BookingService {
	Booking createBooking(Map<String, Object> requestBody);
	Booking createBooking(Map<String, Object> requestBody, Map<String, Object> response);
	HashMap<String, Object> getBooking(Map<String, Object> requestBody);
    List<HashMap<String,Object>> saveBooking(Map<String, Object> requestBody);
    HashMap<String, Object> updateBooking(Map<String, Object> requestBody);
    HashMap<String, Object> getBookingById(int id);
    List<HashMap<String,Object>> getAllBooking(Map<String, Object> requestBody);
    List<HashMap<String,Object>> deleteBooking(Map<String, Object> requestBody);
	List<HashMap<String, Object>> transformListToHashMap(List<Disbursement> List);
}
