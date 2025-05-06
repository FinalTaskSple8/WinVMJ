package SIPH.booking.core;
import java.util.*;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;
//add other required packages

public abstract class BookingResourceComponent implements BookingResource{
	
	public BookingResourceComponent() { }
 
    public abstract Booking createBooking(VMJExchange vmjExchange);    
	public abstract HashMap<String, Object> updateBooking(VMJExchange vmjExchange);
    public abstract HashMap<String, Object> getBooking(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> getAllBooking(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> deleteBooking(VMJExchange vmjExchange);

	public abstract void cancelBooking();

	public abstract Real calculateTotalPrice();
}
