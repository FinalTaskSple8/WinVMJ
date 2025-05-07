package SIPH.booking.roomupgrade;

import java.util.*;
import java.math.BigDecimal;
import java.util.UUID;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import SIPH.booking.core.Booking;
import SIPH.booking.roomupgrade.BookingImpl;
import SIPH.booking.core.BookingComponent;
import SIPH.booking.core.BookingResourceDecorator;
import SIPH.booking.core.BookingResourceComponent;
import vmj.hibernate.integrator.RepositoryUtil;

public class BookingResourceImpl extends BookingResourceDecorator {

    private RepositoryUtil<Booking> Repository = new RepositoryUtil<>(BookingImpl.class);

    public BookingResourceImpl(BookingResourceComponent record) {
        super(record);
    }

    @Route(url = "call/roomupgrade/save")
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
        String upgradedRoomType = (String) vmjExchange.getRequestBodyForm("upgradedRoomType");
        String upgradeCostStr = (String) vmjExchange.getRequestBodyForm("upgradeCost");
        BigDecimal upgradeCost = new BigDecimal(upgradeCostStr);

        HashMap<String, Object> bookingMap = record.createBooking(vmjExchange);
        Booking base = Repository.getObject(UUID.fromString(bookingMap.get("id").toString()));

        return new BookingImpl((BookingComponent) base, upgradedRoomType, upgradeCost);
    }

    public Booking create(VMJExchange vmjExchange, UUID id) {
        String upgradedRoomType = (String) vmjExchange.getRequestBodyForm("upgradedRoomType");
        String upgradeCostStr = (String) vmjExchange.getRequestBodyForm("upgradeCost");
        BigDecimal upgradeCost = new BigDecimal(upgradeCostStr);

        Booking base = Repository.getObject(id);
        return new BookingImpl((BookingComponent) base, upgradedRoomType, upgradeCost);
    }

    @Route(url = "call/roomupgrade/update")
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

    @Route(url = "call/roomupgrade/detail")
    public HashMap<String, Object> get(VMJExchange vmjExchange) {
        return record.getBooking(vmjExchange);
    }

    @Route(url = "call/roomupgrade/list")
    public List<HashMap<String, Object>> getAll(VMJExchange vmjExchange) {
        List<Booking> list = Repository.getAllObject("booking_roomupgrade");
        return transformListToHashMap(list);
    }

    public List<HashMap<String, Object>> transformListToHashMap(List<Booking> list) {
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        for (Booking b : list) {
            resultList.add(b.toHashMap());
        }
        return resultList;
    }

    @Route(url = "call/roomupgrade/delete")
    public List<HashMap<String, Object>> deleteBooking(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }
        String idStr = (String) vmjExchange.getRequestBodyForm("id");
        UUID id = UUID.fromString(idStr);
        Repository.deleteObject(id);
        return getAll(vmjExchange);
    }

    public void requestRoomUpgrade(String newRoomType, BigDecimal additionalCost) {
        // Optional: implement request upgrade logic
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return BigDecimal.ZERO;
    }
}