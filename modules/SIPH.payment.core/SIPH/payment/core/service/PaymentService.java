package SIPH.payment.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public interface PaymentService {
	Payment createPayment(Map<String, Object> requestBody);
	Payment createPayment(Map<String, Object> requestBody, Map<String, Object> response);
	HashMap<String, Object> getPayment(Map<String, Object> requestBody);
    List<HashMap<String,Object>> savePayment(Map<String, Object> requestBody);
    HashMap<String, Object> updatePayment(Map<String, Object> requestBody);
    HashMap<String, Object> getPaymentById(int id);
    List<HashMap<String,Object>> getAllPayment(Map<String, Object> requestBody);
    List<HashMap<String,Object>> deletePayment(Map<String, Object> requestBody);
	List<HashMap<String, Object>> transformListToHashMap(List<Disbursement> List);
}
