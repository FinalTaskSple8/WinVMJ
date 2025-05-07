package SIPH.booking.additionalservices;

import java.util.*;
import java.math.BigDecimal;
import java.util.UUID;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import SIPH.booking.core.Booking;
import SIPH.booking.additionalservices.BookingImpl;
import SIPH.booking.core.BookingComponent;
import SIPH.booking.core.BookingResourceDecorator;
import SIPH.booking.core.BookingResourceComponent;
import vmj.hibernate.integrator.RepositoryUtil;

public class BookingResourceImpl extends BookingResourceDecorator {

    private RepositoryUtil<Booking> Repository = new RepositoryUtil<>(BookingImpl.class);

    public BookingResourceImpl(BookingResourceComponent record) {
        super(record);
    }

    @Route(url = "call/additionalservices/save")
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
        String additionalServices = (String) vmjExchange.getRequestBodyForm("additionalServices");
        String servicesCostStr = (String) vmjExchange.getRequestBodyForm("servicesCost");
        BigDecimal servicesCost = new BigDecimal(servicesCostStr);

        HashMap<String, Object> bookingMap = record.createBooking(vmjExchange);
        Booking base = Repository.getObject(UUID.fromString(bookingMap.get("id").toString()));

        BookingImpl deco = new BookingImpl((BookingComponent) base, additionalServices, servicesCost);
        return deco;
    }

    public Booking create(VMJExchange vmjExchange, UUID id) {
        String additionalServices = (String) vmjExchange.getRequestBodyForm("additionalServices");
        String servicesCostStr = (String) vmjExchange.getRequestBodyForm("servicesCost");
        BigDecimal servicesCost = new BigDecimal(servicesCostStr);

        Booking base = Repository.getObject(id);
        BookingImpl deco = new BookingImpl((BookingComponent) base, additionalServices, servicesCost);
        return deco;
    }

    @Route(url = "call/additionalservices/update")
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

    @Route(url = "call/additionalservices/detail")
    public HashMap<String, Object> get(VMJExchange vmjExchange) {
        return record.getBooking(vmjExchange);
    }

    @Route(url = "call/additionalservices/list")
    public List<HashMap<String, Object>> getAll(VMJExchange vmjExchange) {
        List<Booking> list = Repository.getAllObject("booking_additionalservices");
        return transformListToHashMap(list);
    }

    public List<HashMap<String, Object>> transformListToHashMap(List<Booking> list) {
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        for (Booking b : list) {
            resultList.add(b.toHashMap());
        }
        return resultList;
    }

    @Route(url = "call/additionalservices/delete")
    public List<HashMap<String, Object>> deleteBooking(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }

        String idStr = (String) vmjExchange.getRequestBodyForm("id");
        UUID id = UUID.fromString(idStr);
        Repository.deleteObject(id);
        return getAll(vmjExchange);
    }

    public void addService(String serviceName, BigDecimal serviceCost) {
        // Optional: implement service management
    }

    public BigDecimal calculateTotalPrice() {
        return BigDecimal.ZERO; // implement if needed
    }
}
