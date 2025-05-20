package SIPH.booking.earlycheckinout;

import java.util.*;
import java.math.BigDecimal;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import SIPH.booking.BookingFactory;
import SIPH.booking.core.Booking;
import SIPH.booking.core.BookingComponent;
import SIPH.booking.core.BookingResourceDecorator;
import SIPH.booking.core.BookingResourceComponent;
import java.time.LocalDate;
import vmj.hibernate.integrator.RepositoryUtil;

public class BookingResourceImpl extends BookingResourceDecorator {

    private RepositoryUtil<Booking> bookingRepo;

    public BookingResourceImpl(BookingResourceComponent record) {
        super(record);
        this.bookingRepo = new RepositoryUtil<Booking>(SIPH.booking.core.BookingComponent.class);
    }

    @Route(url = "call/earlycheckinout/save")
    public HashMap<String, Object> createBooking(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) return null;

        Booking decorated = createFromPayload(vmjExchange.getPayload());
        bookingRepo.saveObject(decorated);

        return decorated.toHashMap();
    }
    
    public List<HashMap<String, Object>> saveBooking(VMJExchange vmjExchange) {
        return null;
    }
    
    private Booking createFromPayload(Map<String, Object> payload) {
        UUID userId = UUID.fromString(payload.get("userId").toString());
        LocalDate checkIn = LocalDate.parse(payload.get("checkInDate").toString());
        LocalDate checkOut = LocalDate.parse(payload.get("checkOutDate").toString());
        int guests = Integer.parseInt(payload.get("numberOfGuests").toString());
        BigDecimal total = new BigDecimal(payload.get("totalPrice").toString());
        UUID roomId = UUID.fromString(payload.get("roomId").toString());

        Booking core = BookingFactory.createBooking(
            "SIPH.booking.core.BookingImpl",
            userId, checkIn, checkOut, guests, total, roomId
        );

        Booking decorated = BookingFactory.createBooking(
            "SIPH.booking.earlycheckinout.BookingImpl",
            core,
            Boolean.parseBoolean(payload.get("earlyCheckIn").toString()),
            Boolean.parseBoolean(payload.get("lateCheckOut").toString()),
            new BigDecimal(payload.get("earlyCheckInFee").toString()),
            new BigDecimal(payload.get("lateCheckOutFee").toString())
        );

        return decorated;
    }



    @Route(url = "call/earlycheckinout/update")
    public HashMap<String, Object> update(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) return null;

        Map<String, Object> body = vmjExchange.getPayload();
        UUID id = UUID.fromString(body.get("id").toString());

        Booking base = bookingRepo.getObject(id);
        Booking decorated = createFromPayload(body);
        decorated.setId(id);  // penting: pastiin update, bukan insert baru

        bookingRepo.updateObject(decorated);
        Booking refreshed = bookingRepo.getObject(id);

        return refreshed.toHashMap();
    }

    @Route(url = "call/earlycheckinout/detail")
    public HashMap<String, Object> get(VMJExchange vmjExchange) {
        return record.getBooking(vmjExchange);
    }

    @Route(url = "call/earlycheckinout/list")
    public List<HashMap<String, Object>> getAll(VMJExchange vmjExchange) {
        List<Booking> list = bookingRepo.getAllObject("booking_earlycheckinout");
        return transformListToHashMap(list);
    }

    @Route(url = "call/earlycheckinout/delete")
    public List<HashMap<String, Object>> deleteBooking(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) return null;

        Map<String, Object> body = vmjExchange.getPayload();
        UUID id = UUID.fromString(body.get("id").toString());

        bookingRepo.deleteObject(id);
        return getAll(vmjExchange);
    }

    public List<HashMap<String, Object>> transformListToHashMap(List<? extends Booking> list) {
        List<HashMap<String, Object>> result = new ArrayList<>();
        for (Booking b : list) {
            result.add(b.toHashMap());
        }
        return result;
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return BigDecimal.ZERO;
    }
}
