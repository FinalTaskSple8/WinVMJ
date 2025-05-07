package SIPH.booking.core;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.NotFoundException;

import SIPH.booking.BookingFactory;
import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;

public class BookingServiceImpl extends BookingServiceComponent {

    public List<HashMap<String, Object>> saveBooking(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }
        // CONVERT PAYLOAD
        Map<String, Object> requestBody = vmjExchange.getPayload();
        Booking booking = createBooking(requestBody);
        Repository.saveObject(booking);
        return getAllBooking(requestBody);
    }
    
    @Override
    public List<HashMap<String, Object>> saveBooking(Map<String, Object> requestBody) {
        Booking booking = createBooking(requestBody);
        Repository.saveObject(booking);
        return getAllBooking(requestBody);
    }


    public Booking createBooking(Map<String, Object> requestBody) {
        // Extract values from requestBody
        UUID userId = UUID.fromString((String) requestBody.get("userId"));
        LocalDate checkInDate = LocalDate.parse((String) requestBody.get("checkInDate"));
        LocalDate checkOutDate = LocalDate.parse((String) requestBody.get("checkOutDate"));
        int numberOfGuests = Integer.parseInt((String) requestBody.get("numberOfGuests"));
        BigDecimal totalPrice = new BigDecimal((String) requestBody.get("totalPrice"));
        String status = (String) requestBody.get("status");
        UUID roomId = UUID.fromString((String) requestBody.get("roomId"));
        UUID paymentId = UUID.fromString((String) requestBody.get("paymentId"));
        UUID id = UUID.randomUUID();

        // RoomImpl association to be fixed if needed
        RoomImpl roomimpl = null;

        Booking booking = BookingFactory.createBooking(
            "SIPH.booking.core.BookingImpl",
            userId, checkInDate, checkOutDate, numberOfGuests,
            totalPrice, status, roomId, paymentId, roomimpl, id
        );
        return booking;
    }

    public Booking createBooking(Map<String, Object> requestBody, UUID id) {
        // Same as above, but with provided UUID
        // Implement if needed
        return null;
    }

    public HashMap<String, Object> updateBooking(Map<String, Object> requestBody) {
        UUID id = UUID.fromString((String) requestBody.get("id"));
        Booking booking = Repository.getObject(id);

        booking.setNumberOfGuests(Integer.parseInt((String) requestBody.get("numberOfGuests")));
        booking.setStatus((String) requestBody.get("status"));

        Repository.updateObject(booking);
        return booking.toHashMap();
    }

    public HashMap<String, Object> getBooking(Map<String, Object> requestBody) {
        UUID id = UUID.fromString((String) requestBody.get("id"));
        Booking booking = Repository.getObject(id);
        return booking.toHashMap();
    }

    public HashMap<String, Object> getBookingById(UUID id) {
        Booking booking = Repository.getObject(id);
        return booking.toHashMap();
    }

    public List<HashMap<String, Object>> getAllBooking(Map<String, Object> requestBody) {
        String table = (String) requestBody.getOrDefault("table_name", "booking_impl");
        List<Booking> list = Repository.getAllObject(table);
        return transformListToHashMap(list);
    }

    public List<HashMap<String, Object>> transformListToHashMap(List<Booking> list) {
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        for (Booking b : list) {
            resultList.add(b.toHashMap());
        }
        return resultList;
    }

    public List<HashMap<String, Object>> deleteBooking(Map<String, Object> requestBody) {
        UUID id = UUID.fromString((String) requestBody.get("id"));
        Repository.deleteObject(id);
        return getAllBooking(requestBody);
    }

    @Override
    public void cancelBooking() {
        // implement logic
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return BigDecimal.ZERO; // placeholder
    }
}
