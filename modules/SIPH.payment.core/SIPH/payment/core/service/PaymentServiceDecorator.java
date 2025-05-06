package SIPH.payment.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public abstract class PaymentServiceDecorator extends PaymentServiceComponent{
	protected PaymentServiceComponent record;

    public PaymentServiceDecorator(PaymentServiceComponent record) {
        this.record = record;
    }

	public PaymentImpl createPayment(Map<String, Object> requestBody){
		return record.createPayment(requestBody);
	}

    public Payment createPayment(Map<String, Object> requestBody, Map<String, Object> response){
		return record.createPayment(requestBody, response);
	}

	public HashMap<String, Object> getPayment(Map<String, Object> requestBody){
		return record.getPayment(requestBody);
	}

	public List<HashMap<String,Object>> getAllPayment(Map<String, Object> requestBody){
		return record.getAllPayment(requestBody);
	}

    public List<HashMap<String,Object>> savePayment(VMJExchange vmjExchange){
		return record.savePayment(vmjExchange);
	}

    public HashMap<String, Object> updatePayment(Map<String, Object> requestBody){
		return record.updatePayment(requestBody);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<Payment> List){
		return record.transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> deletePayment(Map<String, Object> requestBody){
		return record.deletePayment(requestBody);
	}

	public HashMap<String, Object> getPaymentById(int id){
        return record.getPaymentById(id);
    }

	public void processPayment() {
		return record.processPayment();
	}
}
