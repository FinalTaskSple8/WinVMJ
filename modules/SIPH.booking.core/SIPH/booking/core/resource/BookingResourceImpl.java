package SIPH.booking.core;

import java.util.*;
import java.math.BigDecimal;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.booking.BookingFactory;
//import prices.auth.vmj.annotations.Restricted;

public class BookingResourceImpl extends BookingResourceComponent {

	private BookingServiceImpl bookingServiceImpl = new BookingServiceImpl();

	 // @Restricted(permission = "")
	 @Route(url = "call/booking")
	 public HashMap<String, Object> createBooking(VMJExchange vmjExchange) {
	 	if (vmjExchange.getHttpMethod().equals("POST")) {
	 		Map<String, Object> requestBody = vmjExchange.getPayload();
	 		Booking result = bookingServiceImpl.createBooking(requestBody);
	 		return result.toHashMap();
	 	}
	 	throw new NotFoundException("Route tidak ditemukan");
	 }
	
	@Override
	public List<HashMap<String, Object>> saveBooking(VMJExchange vmjExchange) {
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		return bookingServiceImpl.saveBooking(vmjExchange);
	}


	// @Restricted(permission = "")
	@Route(url = "call/booking/update")
	public HashMap<String, Object> updateBooking(VMJExchange vmjExchange) {
		Map<String, Object> requestBody = vmjExchange.getPayload();
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		return bookingServiceImpl.updateBooking(requestBody);
	}

	// @Restricted(permission = "")
	@Route(url = "call/booking/detail")
	public HashMap<String, Object> getBooking(VMJExchange vmjExchange) {
		Map<String, Object> requestBody = vmjExchange.getPayload();
		return bookingServiceImpl.getBooking(requestBody);
	}

	// @Restricted(permission = "")
	@Route(url = "call/booking/list")
	public List<HashMap<String, Object>> getAllBooking(VMJExchange vmjExchange) {
		Map<String, Object> requestBody = vmjExchange.getPayload();
		return bookingServiceImpl.getAllBooking(requestBody);
	}

	// @Restricted(permission = "")
	@Route(url = "call/booking/delete")
	public List<HashMap<String, Object>> deleteBooking(VMJExchange vmjExchange) {
		Map<String, Object> requestBody = vmjExchange.getPayload();
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		return bookingServiceImpl.deleteBooking(requestBody);
	}

	@Override
	public void cancelBooking() {
		// TODO: implement this method
	}

	@Override
	public BigDecimal calculateTotalPrice() {
		// TODO: implement this method
		return BigDecimal.ZERO;
	}
}
