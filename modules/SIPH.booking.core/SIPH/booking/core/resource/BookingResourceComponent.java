package SIPH.booking.core;
import java.util.*;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;
//add other required packages

public abstract class BookingResourceComponent implements BookingResource{
	
	public BookingResourceComponent() { }
 
    public abstract List<HashMap<String,Object>> saveBooking(VMJExchange vmjExchange);
    public abstract Booking createBooking(VMJExchange vmjExchange);
	public abstract Booking createBooking(VMJExchange vmjExchange, int id);    
	public abstract HashMap<String, Object> updateBooking(VMJExchange vmjExchange);
    public abstract HashMap<String, Object> getBooking(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> getAllBooking(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> deleteBooking(VMJExchange vmjExchange);

	private abstract void cancelBooking();

	private abstract Real calculateTotalPrice();
}
