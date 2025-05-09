package SIPH.booking.core;

import java.util.*;
import java.math.BigDecimal;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.booking.BookingFactory;
import vmj.hibernate.integrator.RepositoryUtil;
//import prices.auth.vmj.annotations.Restricted;

public class BookingResourceImpl extends BookingResourceComponent {

    private BookingServiceImpl bookingServiceImpl = new BookingServiceImpl();
    private RepositoryUtil<Booking> Repository = new RepositoryUtil<>(BookingImpl.class);

    // @Restricted(permission = "")
    @Route(url = "call/booking")
    public HashMap<String, Object> createBooking(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("POST")) {
            Map<String, Object> requestBody = vmjExchange.getPayload();
            Booking result = bookingServiceImpl.createBooking(requestBody);
            return result.toHashMap();
        }
        throw new NotFoundException("Route not found");
    }
    
    @Route(url = "call/booking/save")
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
        // Gets currently selected booking and cancels it
        // This would typically be called from a specific context where the current booking is known
        // For now, just log that this method was called
        System.out.println("Cancel booking method called - implement with specific booking context");
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        // Calculate the total price for the current booking context
        // This would typically access a current booking object
        return BigDecimal.ZERO; // Default implementation defers to the service layer
    }

    // Additional helper methods for the resource implementation
    public Booking create(VMJExchange vmjExchange) {
        // Create a booking from HTTP request parameters
        Map<String, Object> requestBody = vmjExchange.getPayload();
        return bookingServiceImpl.createBooking(requestBody);
    }

    public List<HashMap<String, Object>> transformListToHashMap(List<Booking> list) {
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        for (Booking b : list) {
            resultList.add(b.toHashMap());
        }
        return resultList;
    }

    public Booking getBookingById(UUID id) {
        return Repository.getObject(id);
    }
}