package SIPH.booking.additionalservices;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

import SIPH.booking.core.BookingDecorator;
import SIPH.booking.core.BookingComponent;
import SIPH.room.core.RoomImpl;

@Entity(name = "booking_additionalservices")
@Table(name = "booking_additionalservices")
public class BookingImpl extends BookingDecorator {

    protected String additionalServices;
    protected BigDecimal servicesCost;

    public BookingImpl() {
        super();
        this.objectName = BookingImpl.class.getName();
    }

    public BookingImpl(String additionalServices, BigDecimal servicesCost) {
        super();
        this.additionalServices = additionalServices;
        this.servicesCost = servicesCost;
        this.objectName = BookingImpl.class.getName();
    }

    public BookingImpl(BookingComponent record, String additionalServices, BigDecimal servicesCost) {
        super(record);
        this.additionalServices = additionalServices;
        this.servicesCost = servicesCost;
        this.objectName = BookingImpl.class.getName();
    }

    public String getAdditionalServices() {
        return this.additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public BigDecimal getServicesCost() {
        return this.servicesCost;
    }

    public void setServicesCost(BigDecimal servicesCost) {
        this.servicesCost = servicesCost;
    }

    @Override
    public void setRoomimpl(RoomImpl roomimpl) {
        record.setRoomimpl(roomimpl);
    }

    @Override
    public RoomImpl getRoomimpl() {
        return record.getRoomimpl();
    }

    public void addService(String serviceName, BigDecimal serviceCost) {
        // Optional: implement service addition logic if needed
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        if (record == null) return servicesCost != null ? servicesCost : BigDecimal.ZERO;
        BigDecimal base = record.calculateTotalPrice();
        return base.add(servicesCost != null ? servicesCost : BigDecimal.ZERO);
    }
}
