package SIPH.payment.promodiscount;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import SIPH.payment.core.PaymentDecorator;
import SIPH.payment.core.Payment;
import SIPH.payment.core.PaymentComponent;

@Entity(name="payment_promodiscount")
@Table(name="payment_promodiscount")
public class PaymentImpl extends PaymentDecorator {

	protected String code;
	protected EFloat discountAmount;
	public PaymentImpl(
        super();
        this.objectName = PaymentImpl.class.getName();
    }
    
    public PaymentImpl(String code, EFloat discountAmount) {
    	super();
		this.code = code;
		this.discountAmount = discountAmount;
		this.objectName = PaymentImpl.class.getName();
    }
	
	public PaymentImpl(PaymentComponent record, String code, EFloat discountAmount) {
		super(record);
		this.code = code;
		this.discountAmount = discountAmount;
		this.objectName = PaymentImpl.class.getName();
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public EFloat getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(EFloat discountAmount) {
		this.discountAmount = discountAmount;
	}

	private void processPayment() {
		// TODO: implement this method
	}

	private void applyDiscount() {
		// TODO: implement this method
	}

}
