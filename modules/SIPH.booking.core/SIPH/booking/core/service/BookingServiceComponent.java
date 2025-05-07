package SIPH.booking.core;

import java.util.*;
import java.util.UUID;
import java.math.BigDecimal;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;

public abstract class BookingServiceComponent implements BookingService {
    protected RepositoryUtil<Booking> Repository;

    public BookingServiceComponent() {
        this.Repository = new RepositoryUtil<>(SIPH.booking.core.BookingComponent.class);
    }

    public abstract List<HashMap<String, Object>> saveBooking(VMJExchange vmjExchange);

    public abstract Booking createBooking(Map<String, Object> requestBody);

    public abstract HashMap<String, Object> updateBooking(Map<String, Object> requestBody);

    public abstract HashMap<String, Object> getBooking(Map<String, Object> requestBody);

    public abstract List<HashMap<String, Object>> getAllBooking(Map<String, Object> requestBody);

    public abstract List<HashMap<String, Object>> transformListToHashMap(List<Booking> list);

    public abstract List<HashMap<String, Object>> deleteBooking(Map<String, Object> requestBody);

    public abstract HashMap<String, Object> getBookingById(UUID id);

    public abstract void cancelBooking();

    public abstract BigDecimal calculateTotalPrice();
}
