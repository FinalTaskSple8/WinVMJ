package SIPH.booking.additionalservices;

import java.util.*;
import java.math.BigDecimal;

import vmj.routing.route.VMJExchange;

import SIPH.booking.core.Booking;
import SIPH.booking.core.BookingServiceDecorator;
import SIPH.booking.core.BookingServiceComponent;
import SIPH.booking.additionalservices.BookingImpl;

public class BookingServiceImpl extends BookingServiceDecorator {

    public BookingServiceImpl(BookingServiceComponent record) {
        super(record);
    }

    public void addService(String serviceName, BigDecimal serviceCost) {
        // Implementasi tambahan layanan
        System.out.println("Added service: " + serviceName + " with cost: " + serviceCost);
    }
    	
    @Override
    public List<HashMap<String, Object>> saveBooking(Map<String, Object> requestBody) {
        return record.saveBooking(requestBody); // Tambahkan ini
    }
    
    @Override
    public List<HashMap<String, Object>> saveBooking(VMJExchange vmjExchange) {
        return record.saveBooking(vmjExchange);
    }


    @Override
    public BigDecimal calculateTotalPrice() {
        // Bisa dihitung ulang total dari booking dan additional services
        // Placeholder: panggil calculate dari record base
        return record.calculateTotalPrice();
    }
}
