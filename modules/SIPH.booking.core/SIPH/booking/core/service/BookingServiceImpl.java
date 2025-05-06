package SIPH.booking.core;
import java.util.*;
import com.google.gson.Gson;
import java.util.*;
import java.util.logging.Logger;
import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.booking.BookingFactory;
import prices.auth.vmj.annotations.Restricted;
//add other required packages

public class BookingServiceImpl extends BookingServiceComponent{

    public List<HashMap<String,Object>> saveBooking(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Booking booking = createBooking(vmjExchange);
		bookingRepository.saveObject(booking);
		return getAllBooking(vmjExchange);
	}

    public Booking createBooking(Map<String, Object> requestBody){
		String status = (String) requestBody.get("status");
		
		//to do: fix association attributes
		Booking Booking = BookingFactory.createBooking(
			"SIPH.booking.core.BookingImpl",
		bookingId
		, userId
		, checkInDate
		, checkOutDate
		, numberOfGuests
		, totalPrice
		, status
		, roomId
		, paymentId
		, roomimpl
		, id
		);
		Repository.saveObject(booking);
		return booking;
	}

    public Booking createBooking(Map<String, Object> requestBody, int id){
		String status = (String) vmjExchange.getRequestBodyForm("status");
		
		//to do: fix association attributes
		
		Booking booking = BookingFactory.createBooking("SIPH.booking.core.BookingImpl", bookingId, userId, checkInDate, checkOutDate, numberOfGuests, totalPrice, status, roomId, paymentId, roomimpl);
		return booking;
	}

    public HashMap<String, Object> updateBooking(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("bookingIduserIdroomIdpaymentIdid");
		int id = Integer.parseInt(idStr);
		Booking booking = Repository.getObject(id);
		
		booking.setStatus((String) requestBody.get("status"));
		
		Repository.updateObject(booking);
		
		//to do: fix association attributes
		
		return booking.toHashMap();
		
	}

    public HashMap<String, Object> getBooking(Map<String, Object> requestBody){
		List<HashMap<String, Object>> bookingList = getAllBooking("booking_impl");
		for (HashMap<String, Object> booking : bookingList){
			int record_id = ((Double) booking.get("record_id")).intValue();
			if (record_id == id){
				return booking;
			}
		}
		return null;
	}

	public HashMap<String, Object> getBookingById(int id){
		String idStr = vmjExchange.getGETParam("bookingIduserIdroomIdpaymentIdid"); 
		int id = Integer.parseInt(idStr);
		Booking booking = bookingRepository.getObject(id);
		return booking.toHashMap();
	}

    public List<HashMap<String,Object>> getAllBooking(Map<String, Object> requestBody){
		String table = (String) requestBody.get("table_name");
		List<Booking> List = Repository.getAllObject(table);
		return transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<Booking> List){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
	}

    public List<HashMap<String,Object>> deleteBooking(Map<String, Object> requestBody){
		String idStr = ((String) requestBody.get("id"));
		int id = Integer.parseInt(idStr);
		Repository.deleteObject(id);
		return getAllBooking(requestBody);
	}

	public void cancelBooking() {
		// TODO: implement this method
	}

	public Real calculateTotalPrice() {
		// TODO: implement this method
	}
}
