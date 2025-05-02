package SIPH.booking.additionalservices;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import SIPH.booking.core.BookingDecorator;
import SIPH.booking.core.Booking;
import SIPH.booking.core.BookingComponent;

@Entity(name="booking_additionalservices")
@Table(name="booking_additionalservices")
public class BookingImpl extends BookingDecorator {

	protected String additionalServices;
	protected Real servicesCost;
	public BookingImpl(
        super();
        this.objectName = BookingImpl.class.getName();
    }
    
    public BookingImpl(String additionalServices, Real servicesCost) {
    	super();
		this.additionalServices = additionalServices;
		this.servicesCost = servicesCost;
		this.objectName = BookingImpl.class.getName();
    }
	
	public BookingImpl(BookingComponent record, String additionalServices, Real servicesCost) {
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
	public Real getServicesCost() {
		return this.servicesCost;
	}

	public void setServicesCost(Real servicesCost) {
		this.servicesCost = servicesCost;
	}

	private void addService(String serviceName, Real serviceCost) {
		// TODO: implement this method
	}

	private Real calculateTotalPrice() {
		// TODO: implement this method
	}

}
