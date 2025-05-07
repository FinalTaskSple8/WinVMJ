package SIPH.booking.roomupgrade;

import java.util.*;
import java.math.BigDecimal;

import vmj.routing.route.VMJExchange;

import SIPH.booking.core.Booking;
import SIPH.booking.core.BookingServiceDecorator;
import SIPH.booking.core.BookingServiceComponent;

public class BookingServiceImpl extends BookingServiceDecorator {

    public BookingServiceImpl(BookingServiceComponent record) {
        super(record);
    }

    public void requestRoomUpgrade(String newRoomType, BigDecimal additionalCost) {
        // Optional logic if needed
        System.out.println("Request upgrade to " + newRoomType + " with cost " + additionalCost);
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return record.calculateTotalPrice();
    }
    
    @Override
    public List<HashMap<String, Object>> saveBooking(Map<String, Object> requestBody) {
        return record.saveBooking(requestBody);
    }


    @Override
    public List<HashMap<String, Object>> saveBooking(VMJExchange vmjExchange) {
        return record.saveBooking(vmjExchange);
    }
}
