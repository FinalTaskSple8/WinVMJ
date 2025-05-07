package SIPH.booking.core;

import java.util.*;
import java.math.BigDecimal;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;

public abstract class BookingResourceComponent implements BookingResource {

	public BookingResourceComponent() { }

	public abstract HashMap<String, Object> createBooking(VMJExchange vmjExchange);    
	public abstract HashMap<String, Object> updateBooking(VMJExchange vmjExchange);
	public abstract HashMap<String, Object> getBooking(VMJExchange vmjExchange);
	public abstract List<HashMap<String,Object>> getAllBooking(VMJExchange vmjExchange);
	public abstract List<HashMap<String,Object>> deleteBooking(VMJExchange vmjExchange);

	public abstract void cancelBooking();
	public abstract BigDecimal calculateTotalPrice();
}
