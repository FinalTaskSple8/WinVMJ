package SIPH.payment.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

public abstract class PaymentResourceDecorator extends PaymentResourceComponent{
	protected PaymentResourceComponent record;

    public PaymentResourceDecorator(PaymentResourceComponent record) {
        this.record = record;
    }

    public HashMap<String, Object> createPayment(VMJExchange vmjExchange){
		return record.createPayment(vmjExchange);
	}

    public HashMap<String, Object> updatePayment(VMJExchange vmjExchange){
		return record.updatePayment(vmjExchange);
	}

    public HashMap<String, Object> getPayment(VMJExchange vmjExchange){
		return record.getPayment(vmjExchange);
	}

    public List<HashMap<String,Object>> getAllPayment(VMJExchange vmjExchange){
		return record.getAllPayment(vmjExchange);
	}

    public List<HashMap<String,Object>> deletePayment(VMJExchange vmjExchange){
		return record.deletePayment(vmjExchange);
	}

	public void processPayment() {
		record.processPayment();
	}
}
