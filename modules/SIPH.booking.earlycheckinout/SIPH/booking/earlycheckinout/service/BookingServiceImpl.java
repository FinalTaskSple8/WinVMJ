package SIPH.booking.earlycheckinout;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vmj.routing.route.VMJExchange;
import SIPH.booking.core.BookingServiceDecorator;
import SIPH.booking.core.BookingServiceComponent;

public class BookingServiceImpl extends BookingServiceDecorator {

    public BookingServiceImpl(BookingServiceComponent record) {
        super(record);
    }

    public void requestEarlyCheckIn(BigDecimal fee) {
        System.out.println("Requested early check-in with fee: " + fee);
    }

    public void requestLateCheckOut(BigDecimal fee) {
        System.out.println("Requested late check-out with fee: " + fee);
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return record.calculateTotalPrice();
    }

    @Override
    public List<HashMap<String, Object>> saveBooking(VMJExchange vmjExchange) {
        return record.saveBooking(vmjExchange);
    }

    @Override
    public List<HashMap<String, Object>> saveBooking(Map<String, Object> requestBody) {
        return record.saveBooking(requestBody);
    }
}