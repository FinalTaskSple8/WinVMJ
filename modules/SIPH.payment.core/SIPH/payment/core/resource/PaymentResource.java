package SIPH.payment.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public interface PaymentResource {
    List<HashMap<String,Object>> savePayment(VMJExchange vmjExchange);
    HashMap<String, Object> updatePayment(VMJExchange vmjExchange);
    HashMap<String, Object> getPayment(VMJExchange vmjExchange);
    List<HashMap<String,Object>> getAllPayment(VMJExchange vmjExchange);
    List<HashMap<String,Object>> deletePayment(VMJExchange vmjExchange);
	HashMap<String, Object> createPayment(VMJExchange vmjExhange);
}
