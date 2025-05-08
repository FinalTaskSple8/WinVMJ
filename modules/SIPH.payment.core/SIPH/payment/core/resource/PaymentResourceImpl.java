package SIPH.payment.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.payment.PaymentFactory;
//import prices.auth.vmj.annotations.Restricted;
//add other required packages

public class PaymentResourceImpl extends PaymentResourceComponent{
	
	private PaymentServiceImpl paymentServiceImpl;

	// @Restriced(permission = "")
    @Route(url="call/payment")
    public HashMap<String,Object> createPayment(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Payment result = paymentServiceImpl.createPayment(requestBody);
			return result.toHashMap();
		}
		throw new NotFoundException("Route tidak ditemukan");
	}
    
    @Override
    public List<HashMap<String, Object>> savePayment(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }

        Map<String, Object> requestBody = vmjExchange.getPayload();
        Payment result = paymentServiceImpl.createPayment(requestBody);
        
        List<HashMap<String, Object>> response = new ArrayList<>();
        response.add(result.toHashMap());
        return response;
    }


    // @Restriced(permission = "")
    @Route(url="call/payment/update")
    public HashMap<String, Object> updatePayment(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")){
			return null;
		}
		return paymentServiceImpl.updatePayment(requestBody);
		
	}

	// @Restriced(permission = "")
    @Route(url="call/payment/detail")
    public HashMap<String, Object> getPayment(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		return paymentServiceImpl.getPayment(requestBody);
	}

	// @Restriced(permission = "")
    @Route(url="call/payment/list")
    public List<HashMap<String,Object>> getAllPayment(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		return paymentServiceImpl.getAllPayment(requestBody);
	}

    
	// @Restriced(permission = "")
    @Route(url="call/payment/delete")
    public List<HashMap<String,Object>> deletePayment(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		return paymentServiceImpl.deletePayment(requestBody);
	}

	public void processPayment() {
		// TODO: implement this method
	}
}
