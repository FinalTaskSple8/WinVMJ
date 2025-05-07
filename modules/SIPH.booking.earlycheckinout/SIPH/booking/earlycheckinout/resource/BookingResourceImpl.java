package SIPH.booking.earlycheckinout;

import java.util.*;
import java.math.BigDecimal;
import java.util.UUID;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import SIPH.booking.core.Booking;
import SIPH.booking.core.BookingComponent;
import SIPH.booking.core.BookingResourceDecorator;
import SIPH.booking.core.BookingResourceComponent;
import vmj.hibernate.integrator.RepositoryUtil;

public class BookingResourceImpl extends BookingResourceDecorator {

    private RepositoryUtil<Booking> Repository = new RepositoryUtil<>(BookingImpl.class);

    public BookingResourceImpl(BookingResourceComponent record) {
        super(record);
    }

    @Route(url = "call/earlycheckinout/save")
    @Override
    public List<HashMap<String, Object>> saveBooking(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }
        Booking booking = create(vmjExchange);
        Repository.saveObject(booking);
        return getAll(vmjExchange);
    }

    public Booking create(VMJExchange vmjExchange) {
        boolean earlyCheckIn = Boolean.parseBoolean((String) vmjExchange.getRequestBodyForm("earlyCheckIn"));
        boolean lateCheckOut = Boolean.parseBoolean((String) vmjExchange.getRequestBodyForm("lateCheckOut"));
        BigDecimal earlyFee = new BigDecimal((String) vmjExchange.getRequestBodyForm("earlyCheckInFee"));
        BigDecimal lateFee = new BigDecimal((String) vmjExchange.getRequestBodyForm("lateCheckOutFee"));

        HashMap<String, Object> bookingMap = record.createBooking(vmjExchange);
        Booking base = Repository.getObject(UUID.fromString(bookingMap.get("id").toString()));

        return new BookingImpl((BookingComponent) base, earlyCheckIn, lateCheckOut, earlyFee, lateFee);
    }

    public Booking create(VMJExchange vmjExchange, UUID id) {
        boolean earlyCheckIn = Boolean.parseBoolean((String) vmjExchange.getRequestBodyForm("earlyCheckIn"));
        boolean lateCheckOut = Boolean.parseBoolean((String) vmjExchange.getRequestBodyForm("lateCheckOut"));
        BigDecimal earlyFee = new BigDecimal((String) vmjExchange.getRequestBodyForm("earlyCheckInFee"));
        BigDecimal lateFee = new BigDecimal((String) vmjExchange.getRequestBodyForm("lateCheckOutFee"));

        Booking base = Repository.getObject(id);
        return new BookingImpl((BookingComponent) base, earlyCheckIn, lateCheckOut, earlyFee, lateFee);
    }

    @Route(url = "call/earlycheckinout/update")
    public HashMap<String, Object> update(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }
        String idStr = (String) vmjExchange.getRequestBodyForm("id");
        UUID id = UUID.fromString(idStr);

        Booking updated = create(vmjExchange, id);
        Repository.updateObject(updated);

        Booking refreshed = Repository.getObject(id);
        return refreshed.toHashMap();
    }

    @Route(url = "call/earlycheckinout/detail")
    public HashMap<String, Object> get(VMJExchange vmjExchange) {
        return record.getBooking(vmjExchange);
    }

    @Route(url = "call/earlycheckinout/list")
    public List<HashMap<String, Object>> getAll(VMJExchange vmjExchange) {
        List<Booking> list = Repository.getAllObject("booking_earlycheckinout");
        return transformListToHashMap(list);
    }

    public List<HashMap<String, Object>> transformListToHashMap(List<Booking> list) {
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        for (Booking b : list) {
            resultList.add(b.toHashMap());
        }
        return resultList;
    }

    @Route(url = "call/earlycheckinout/delete")
    public List<HashMap<String, Object>> deleteBooking(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }
        String idStr = (String) vmjExchange.getRequestBodyForm("id");
        UUID id = UUID.fromString(idStr);
        Repository.deleteObject(id);
        return getAll(vmjExchange);
    }

    public void requestEarlyCheckIn(BigDecimal fee) {
        // Optional: implement if needed
    }

    public void requestLateCheckOut(BigDecimal fee) {
        // Optional: implement if needed
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return BigDecimal.ZERO;
    }
}
