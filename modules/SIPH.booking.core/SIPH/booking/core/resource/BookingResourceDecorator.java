package SIPH.booking.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

public abstract class BookingResourceDecorator extends BookingResourceComponent{
	protected BookingResourceComponent record;

    public BookingResourceDecorator(BookingResourceComponent record) {
        this.record = record;
    }

    public List<HashMap<String,Object>> saveBooking(VMJExchange vmjExchange){
		return record.saveBooking(vmjExchange);
	}

    public Booking createBooking(VMJExchange vmjExchange){
		return record.createBooking(vmjExchange);
	}

    public Booking createBooking(VMJExchange vmjExchange, int id){
		return record.createBooking(vmjExchange, id);
	}

    public HashMap<String, Object> updateBooking(VMJExchange vmjExchange){
		return record.updateBooking(vmjExchange);
	}

    public HashMap<String, Object> getBooking(VMJExchange vmjExchange){
		return record.getBooking(vmjExchange);
	}

    public List<HashMap<String,Object>> getAllBooking(VMJExchange vmjExchange){
		return record.getAllBooking(vmjExchange);
	}

    public List<HashMap<String,Object>> deleteBooking(VMJExchange vmjExchange){
		return record.deleteBooking(vmjExchange);
	}

	private void cancelBooking() {
		return record.cancelBooking();
	}

	private Real calculateTotalPrice() {
		return record.calculateTotalPrice();
	}
}
