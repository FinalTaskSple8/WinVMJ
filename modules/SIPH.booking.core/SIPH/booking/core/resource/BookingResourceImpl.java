package SIPH.booking.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.booking.BookingFactory;
import prices.auth.vmj.annotations.Restricted;
//add other required packages


public class BookingResourceImpl extends BookingResourceComponent{
	
	private BookingServiceImpl bookingServiceImpl = new BookingServiceImpl();

	// @Restriced(permission = "")
    @Route(url="call/booking/save")
    public List<HashMap<String,Object>> saveBooking(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Booking booking = createBooking(vmjExchange);
		bookingRepository.saveObject(booking);
		return getAllBooking(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/booking")
    public HashMap<String,Object> booking(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Booking result = bookingServiceImpl.createBooking(requestBody);
			return result.toHashMap();
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

    public Booking createBooking(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Booking result = bookingServiceImpl.createBooking(requestBody);
			return result.toHashMap();
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

    public Booking createBooking(VMJExchange vmjExchange, int id){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Booking result = bookingServiceImpl.createBooking(requestBody, id);
			return result.toHashMap();
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

	// @Restriced(permission = "")
    @Route(url="call/booking/update")
    public HashMap<String, Object> updateBooking(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")){
			return null;
		}
		return bookingServiceImpl.updateBooking(requestBody);
		
	}

	// @Restriced(permission = "")
    @Route(url="call/booking/detail")
    public HashMap<String, Object> getBooking(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		return bookingServiceImpl.getBooking(requestBody);
	}

	// @Restriced(permission = "")
    @Route(url="call/booking/list")
    public List<HashMap<String,Object>> getAllBooking(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		return bookingServiceImpl.getAllBooking(requestBody);
	}

	// @Restriced(permission = "")
    @Route(url="call/booking/delete")
    public List<HashMap<String,Object>> deleteBooking(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		return bookingServiceImpl.deleteBooking(requestBody);
	}


	
	private void cancelBooking() {
		// TODO: implement this method
	}

	
	private Real calculateTotalPrice() {
		// TODO: implement this method
	}
}
