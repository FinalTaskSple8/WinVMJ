package SIPH.payment.promodiscount;

import java.util.*;

import vmj.routing.route.VMJExchange;

import SIPH.payment.core.PaymentServiceDecorator;
import SIPH.payment.core.PaymentImpl;
import SIPH.payment.core.PaymentServiceComponent;

public class PaymentServiceImpl extends PaymentServiceDecorator {
    public PaymentServiceImpl (PaymentServiceComponent record) {
        super(record);
    }

    
	private void processPayment() {
		// TODO: implement this method
	}

	private void applyDiscount() {
		// TODO: implement this method
	}
}
