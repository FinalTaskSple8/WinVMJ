package SIPH.booking.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public abstract class BookingServiceDecorator extends BookingServiceComponent{
	protected BookingServiceComponent record;

    public BookingServiceDecorator(BookingServiceComponent record) {
        this.record = record;
    }

	public BookingImpl createBooking(Map<String, Object> requestBody){
		return record.createBooking(requestBody);
	}

    public Booking createBooking(Map<String, Object> requestBody, Map<String, Object> response){
		return record.createBooking(requestBody, response);
	}

	public HashMap<String, Object> getBooking(Map<String, Object> requestBody){
		return record.getBooking(requestBody);
	}

	public List<HashMap<String,Object>> getAllBooking(Map<String, Object> requestBody){
		return record.getAllBooking(requestBody);
	}

    public List<HashMap<String,Object>> saveBooking(VMJExchange vmjExchange){
		return record.saveBooking(vmjExchange);
	}

    public HashMap<String, Object> updateBooking(Map<String, Object> requestBody){
		return record.updateBooking(requestBody);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<Booking> List){
		return record.transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> deleteBooking(Map<String, Object> requestBody){
		return record.deleteBooking(requestBody);
	}

	public HashMap<String, Object> getBookingById(int id){
        return record.getBookingById(id);
    }

	public void cancelBooking() {
		return record.cancelBooking();
	}

	public Real calculateTotalPrice() {
		return record.calculateTotalPrice();
	}
}
