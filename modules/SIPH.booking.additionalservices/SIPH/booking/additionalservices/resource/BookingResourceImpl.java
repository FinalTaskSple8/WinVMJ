package SIPH.booking.additionalservices;
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
    @Route(url="call/additionalservices/save")
    public List<HashMap<String,Object>> save(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		BookingAdditionalServices bookingadditionalservices = createBookingAdditionalServices(vmjExchange);
		bookingadditionalservicesRepository.saveObject(bookingadditionalservices);
		return getAllBookingAdditionalServices(vmjExchange);
	}

    public Booking createBookingAdditionalServices(VMJExchange vmjExchange){
		String additionalServices = (String) vmjExchange.getRequestBodyForm("additionalServices");
		
		BookingAdditionalServices bookingadditionalservices = record.createBookingAdditionalServices(vmjExchange);
		BookingAdditionalServices bookingadditionalservicesdeco = BookingAdditionalServicesFactory.createBookingAdditionalServices("SIPH.additionalservices.core.BookingImpl", bookingadditionalservices, bookingId, userId, checkInDate, checkOutDate, numberOfGuests, totalPrice, status, roomId, paymentId, roomimpl
		additionalServices, servicesCost
		);
			return bookingadditionalservicesdeco;
	}


    public Booking createBookingAdditionalServices(VMJExchange vmjExchange, int id){
		String additionalServices = (String) vmjExchange.getRequestBodyForm("additionalServices");
		BookingAdditionalServices bookingadditionalservices = bookingadditionalservicesRepository.getObject(id);
		int recordBookingAdditionalServicesId = (((BookingAdditionalServicesDecorator) savedBookingAdditionalServices.getRecord()).getId();
		
		BookingAdditionalServices bookingadditionalservices = record.createBookingAdditionalServices(vmjExchange);
		BookingAdditionalServices bookingadditionalservicesdeco = BookingAdditionalServicesFactory.createBookingAdditionalServices("SIPH.additionalservices.core.BookingImpl", id, bookingadditionalservices, bookingId, userId, checkInDate, checkOutDate, numberOfGuests, totalPrice, status, roomId, paymentId, roomimpl
		additionalServices, servicesCost
		);
			return bookingadditionalservicesdeco;
	}

	// @Restriced(permission = "")
    @Route(url="call/additionalservices/update")
    public HashMap<String, Object> updateBookingAdditionalServices(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		String idStr = (String) vmjExchange.getRequestBodyForm("bookingIduserIdroomIdpaymentId");
		int id = Integer.parseInt(idStr);
		
		BookingAdditionalServices bookingadditionalservices = bookingadditionalservicesRepository.getObject(id);
		bookingadditionalservices = createBookingAdditionalServices(vmjExchange, id);
		
		bookingadditionalservicesRepository.updateObject(bookingadditionalservices);
		bookingadditionalservices = bookingadditionalservicesRepository.getObject(id);
		//to do: fix association attributes
		
		return bookingadditionalservices.toHashMap();
		
	}

	// @Restriced(permission = "")
    @Route(url="call/additionalservices/detail")
    public HashMap<String, Object> getBookingAdditionalServices(VMJExchange vmjExchange){
		return record.getBookingAdditionalServices(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/additionalservices/list")
    public List<HashMap<String,Object>> getAllBookingAdditionalServices(VMJExchange vmjExchange){
		List<BookingAdditionalServices> bookingadditionalservicesList = bookingadditionalservicesRepository.getAllObject("bookingadditionalservices_impl");
		return transformBookingAdditionalServicesListToHashMap(bookingadditionalservicesList);
	}

    public List<HashMap<String,Object>> transformBookingAdditionalServicesListToHashMap(List<BookingAdditionalServices> BookingAdditionalServicesList){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < BookingAdditionalServicesList.size(); i++) {
            resultList.add(BookingAdditionalServicesList.get(i).toHashMap());
        }

        return resultList;
	}

	// @Restriced(permission = "")
    @Route(url="call/additionalservices/delete")
    public List<HashMap<String,Object>> deleteBookingAdditionalServices(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		String idStr = (String) vmjExchange.getRequestBodyForm("bookingIduserIdroomIdpaymentId");
		int id = Integer.parseInt(idStr);
		bookingadditionalservicesRepository.deleteObject(id);
		return getAllBookingAdditionalServices(vmjExchange);
	}

	private void addService(String serviceName, Real serviceCost) {
		// TODO: implement this method
	}

	private Real calculateTotalPrice() {
		// TODO: implement this method
	}
	
}
