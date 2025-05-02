package SIPH.booking.earlycheckinout;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import SIPH.booking.core.BookingResourceDecorator;
import SIPH.booking.core.BookingImpl;
import SIPH.booking.core.BookingResourceComponent;

public class BookingResourceImpl extends BookingResourceDecorator {
    public BookingResourceImpl (BookingResourceComponent record) {
        super(record);
    }

    // @Restriced(permission = "")
    @Route(url="call/earlycheckinout/save")
    public List<HashMap<String,Object>> save(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		BookingEarlyCheckInOut bookingearlycheckinout = createBookingEarlyCheckInOut(vmjExchange);
		bookingearlycheckinoutRepository.saveObject(bookingearlycheckinout);
		return getAllBookingEarlyCheckInOut(vmjExchange);
	}

    public Booking createBookingEarlyCheckInOut(VMJExchange vmjExchange){
		boolean earlyCheckIn = (boolean) vmjExchange.getRequestBodyForm("earlyCheckIn");
		boolean lateCheckOut = (boolean) vmjExchange.getRequestBodyForm("lateCheckOut");
		
		BookingEarlyCheckInOut bookingearlycheckinout = record.createBookingEarlyCheckInOut(vmjExchange);
		BookingEarlyCheckInOut bookingearlycheckinoutdeco = BookingEarlyCheckInOutFactory.createBookingEarlyCheckInOut("SIPH.earlycheckinout.core.BookingImpl", bookingearlycheckinout, bookingId, userId, checkInDate, checkOutDate, numberOfGuests, totalPrice, status, roomId, paymentId, roomimpl
		earlyCheckIn, lateCheckOut, earlyCheckInFee, lateCheckOutFee
		);
			return bookingearlycheckinoutdeco;
	}


    public Booking createBookingEarlyCheckInOut(VMJExchange vmjExchange, int id){
		boolean earlyCheckIn = (boolean) vmjExchange.getRequestBodyForm("earlyCheckIn");
		boolean lateCheckOut = (boolean) vmjExchange.getRequestBodyForm("lateCheckOut");
		BookingEarlyCheckInOut bookingearlycheckinout = bookingearlycheckinoutRepository.getObject(id);
		int recordBookingEarlyCheckInOutId = (((BookingEarlyCheckInOutDecorator) savedBookingEarlyCheckInOut.getRecord()).getId();
		
		BookingEarlyCheckInOut bookingearlycheckinout = record.createBookingEarlyCheckInOut(vmjExchange);
		BookingEarlyCheckInOut bookingearlycheckinoutdeco = BookingEarlyCheckInOutFactory.createBookingEarlyCheckInOut("SIPH.earlycheckinout.core.BookingImpl", id, bookingearlycheckinout, bookingId, userId, checkInDate, checkOutDate, numberOfGuests, totalPrice, status, roomId, paymentId, roomimpl
		earlyCheckIn, lateCheckOut, earlyCheckInFee, lateCheckOutFee
		);
			return bookingearlycheckinoutdeco;
	}

	// @Restriced(permission = "")
    @Route(url="call/earlycheckinout/update")
    public HashMap<String, Object> updateBookingEarlyCheckInOut(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		String idStr = (String) vmjExchange.getRequestBodyForm("bookingIduserIdroomIdpaymentId");
		int id = Integer.parseInt(idStr);
		
		BookingEarlyCheckInOut bookingearlycheckinout = bookingearlycheckinoutRepository.getObject(id);
		bookingearlycheckinout = createBookingEarlyCheckInOut(vmjExchange, id);
		
		bookingearlycheckinoutRepository.updateObject(bookingearlycheckinout);
		bookingearlycheckinout = bookingearlycheckinoutRepository.getObject(id);
		//to do: fix association attributes
		
		return bookingearlycheckinout.toHashMap();
		
	}

	// @Restriced(permission = "")
    @Route(url="call/earlycheckinout/detail")
    public HashMap<String, Object> getBookingEarlyCheckInOut(VMJExchange vmjExchange){
		return record.getBookingEarlyCheckInOut(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/earlycheckinout/list")
    public List<HashMap<String,Object>> getAllBookingEarlyCheckInOut(VMJExchange vmjExchange){
		List<BookingEarlyCheckInOut> bookingearlycheckinoutList = bookingearlycheckinoutRepository.getAllObject("bookingearlycheckinout_impl");
		return transformBookingEarlyCheckInOutListToHashMap(bookingearlycheckinoutList);
	}

    public List<HashMap<String,Object>> transformBookingEarlyCheckInOutListToHashMap(List<BookingEarlyCheckInOut> BookingEarlyCheckInOutList){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < BookingEarlyCheckInOutList.size(); i++) {
            resultList.add(BookingEarlyCheckInOutList.get(i).toHashMap());
        }

        return resultList;
	}

	// @Restriced(permission = "")
    @Route(url="call/earlycheckinout/delete")
    public List<HashMap<String,Object>> deleteBookingEarlyCheckInOut(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		String idStr = (String) vmjExchange.getRequestBodyForm("bookingIduserIdroomIdpaymentId");
		int id = Integer.parseInt(idStr);
		bookingearlycheckinoutRepository.deleteObject(id);
		return getAllBookingEarlyCheckInOut(vmjExchange);
	}

	private void requestEarlyCheckIn(Real fee) {
		// TODO: implement this method
	}

	private void requestLateCheckOut(Real fee) {
		// TODO: implement this method
	}

	private Real calculateTotalPrice() {
		// TODO: implement this method
	}
	
}
