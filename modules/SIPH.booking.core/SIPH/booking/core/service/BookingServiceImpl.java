package SIPH.booking.core;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.NotFoundException;

import SIPH.booking.BookingFactory;
import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;
import vmj.hibernate.integrator.RepositoryUtil;

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
        
        // Payment ID may be optional at booking creation time
        UUID paymentId = null;
        if (requestBody.containsKey("paymentId") && requestBody.get("paymentId") != null) {
            paymentId = UUID.fromString((String) requestBody.get("paymentId"));
        } else {
            paymentId = UUID.randomUUID(); // Generate a placeholder payment ID
        }
        
        UUID id = UUID.randomUUID();

        // RoomImpl association - this would need to be fetched from the database
        RoomImpl roomimpl = null;
        // In a real implementation, we would query for the room:
        // roomimpl = roomRepository.getObject(roomId);

        Booking booking = BookingFactory.createBooking(
            "SIPH.booking.core.BookingImpl",
            userId, checkInDate, checkOutDate, numberOfGuests,
            totalPrice, status, roomId, paymentId, roomimpl, id
        );
        return booking;
    }

    public Booking createBooking(Map<String, Object> requestBody, UUID id) {
        // Same as above, but with provided UUID
        UUID userId = UUID.fromString((String) requestBody.get("userId"));
        LocalDate checkInDate = LocalDate.parse((String) requestBody.get("checkInDate"));
        LocalDate checkOutDate = LocalDate.parse((String) requestBody.get("checkOutDate"));
        int numberOfGuests = Integer.parseInt((String) requestBody.get("numberOfGuests"));
        BigDecimal totalPrice = new BigDecimal((String) requestBody.get("totalPrice"));
        String status = (String) requestBody.get("status");
        UUID roomId = UUID.fromString((String) requestBody.get("roomId"));
        UUID paymentId = null;
        if (requestBody.containsKey("paymentId") && requestBody.get("paymentId") != null) {
            paymentId = UUID.fromString((String) requestBody.get("paymentId"));
        } else {
            paymentId = UUID.randomUUID();
        }
        
        // RoomImpl association
        RoomImpl roomimpl = null;
        
        Booking booking = BookingFactory.createBooking(
            "SIPH.booking.core.BookingImpl",
            userId, checkInDate, checkOutDate, numberOfGuests,
            totalPrice, status, roomId, paymentId, roomimpl, id
        );
        return booking;
    }

    public HashMap<String, Object> updateBooking(Map<String, Object> requestBody) {
        UUID id = UUID.fromString((String) requestBody.get("id"));
        Booking booking = Repository.getObject(id);

        // Update only fields that are provided
        if (requestBody.containsKey("numberOfGuests")) {
            booking.setNumberOfGuests(Integer.parseInt((String) requestBody.get("numberOfGuests")));
        }
        if (requestBody.containsKey("status")) {
            booking.setStatus((String) requestBody.get("status"));
        }
        if (requestBody.containsKey("checkInDate")) {
            booking.setCheckInDate(LocalDate.parse((String) requestBody.get("checkInDate")));
        }
        if (requestBody.containsKey("checkOutDate")) {
            booking.setCheckOutDate(LocalDate.parse((String) requestBody.get("checkOutDate")));
        }
        if (requestBody.containsKey("totalPrice")) {
            booking.setTotalPrice(new BigDecimal((String) requestBody.get("totalPrice")));
        }
        if (requestBody.containsKey("roomId")) {
            booking.setRoomId(UUID.fromString((String) requestBody.get("roomId")));
        }
        if (requestBody.containsKey("paymentId")) {
            booking.setPaymentId(UUID.fromString((String) requestBody.get("paymentId")));
        }

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
        // This would be implemented to cancel a booking in a specific context
        // For example, it might be used within a specific UI action
        System.out.println("Cancel booking method called - service implementation");
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        // This is a general implementation - real calculation is done in BookingImpl
        return BigDecimal.ZERO;
    }
}