package SIPH.payment.onlinepayment;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import SIPH.payment.core.PaymentDecorator;
import SIPH.payment.core.Payment;
import SIPH.payment.core.PaymentComponent;

@Entity(name="payment_onlinepayment")
@Table(name="payment_onlinepayment")
public class PaymentImpl extends PaymentDecorator {

	protected String gateway;
	public PaymentImpl(
        super();
        this.objectName = PaymentImpl.class.getName();
    }
    
    public PaymentImpl(String gateway) {
    	super();
		this.gateway = gateway;
		this.objectName = PaymentImpl.class.getName();
    }
	
	public PaymentImpl(PaymentComponent record, String gateway) {
		super(record);
		this.gateway = gateway;
		this.objectName = PaymentImpl.class.getName();
	}

	public String getGateway() {
		return this.gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	private void processPayment() {
		// TODO: implement this method
	}

	private void authenticate() {
		// TODO: implement this method
	}

}
