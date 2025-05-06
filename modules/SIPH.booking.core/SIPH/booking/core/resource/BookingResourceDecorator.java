package SIPH.booking.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

public abstract class BookingResourceDecorator extends BookingResourceComponent{
	protected BookingResourceComponent record;

    public BookingResourceDecorator(BookingResourceComponent record) {
        this.record = record;
    }

    public Booking createBooking(VMJExchange vmjExchange){
		return record.createBooking(vmjExchange);
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

	public void cancelBooking() {
		return record.cancelBooking();
	}

	public Real calculateTotalPrice() {
		return record.calculateTotalPrice();
	}
}
