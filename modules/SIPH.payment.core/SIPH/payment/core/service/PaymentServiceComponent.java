package SIPH.payment.core;
import java.util.*;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;
//add other required packages

public abstract class PaymentServiceComponent implements PaymentService{
	protected RepositoryUtil<Payment> Repository;

    public PaymentServiceComponent(){
        this.Repository = new RepositoryUtil<Payment>(SIPH.payment.core.PaymentComponent.class);
    }	

    public abstract List<HashMap<String,Object>> savePayment(VMJExchange vmjExchange);
    public abstract Payment createPayment(Map<String, Object> requestBodye);
	public abstract Payment createPayment(Map<String, Object> requestBody, Map<String, Object> response);    
	public abstract HashMap<String, Object> updatePayment(Map<String, Object> requestBody);
    public abstract HashMap<String, Object> getPayment(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> getAllPayment(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> transformListToHashMap(List<Payment> List);
    public abstract List<HashMap<String,Object>> deletePayment(Map<String, Object> requestBody);
	public abstract HashMap<String, Object> getPaymentById(int id);

	public abstract void processPayment();
}
