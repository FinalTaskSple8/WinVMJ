package SIPH.booking.core;
import java.util.*;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;
//add other required packages

public abstract class BookingServiceComponent implements BookingService{
	protected RepositoryUtil<Booking> Repository;

    public BookingServiceComponent(){
        this.Repository = new RepositoryUtil<Booking>(SIPH.booking.core.BookingComponent.class);
    }	

    public abstract List<HashMap<String,Object>> saveBooking(VMJExchange vmjExchange);
    public abstract Booking createBooking(Map<String, Object> requestBodye);
	public abstract Booking createBooking(Map<String, Object> requestBody, Map<String, Object> response);    
	public abstract HashMap<String, Object> updateBooking(Map<String, Object> requestBody);
    public abstract HashMap<String, Object> getBooking(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> getAllBooking(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> transformListToHashMap(List<Booking> List);
    public abstract List<HashMap<String,Object>> deleteBooking(Map<String, Object> requestBody);
	public abstract HashMap<String, Object> getBookingById(int id);

	private abstract void cancelBooking();

	private abstract Real calculateTotalPrice();
}
