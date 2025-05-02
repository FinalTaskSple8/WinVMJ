package SIPH.payment.core;
import java.util.*;
import com.google.gson.Gson;
import java.util.*;
import java.util.logging.Logger;
import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.payment.PaymentFactory;
import prices.auth.vmj.annotations.Restricted;
//add other required packages

public class PaymentServiceImpl extends PaymentServiceComponent{

    public List<HashMap<String,Object>> savePayment(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Payment payment = createPayment(vmjExchange);
		paymentRepository.saveObject(payment);
		return getAllPayment(vmjExchange);
	}

    public Payment createPayment(Map<String, Object> requestBody){
		String userIdStr = (String) requestBody.get("userId");
		int userId = Integer.parseInt(userIdStr);
		String bookingIdStr = (String) requestBody.get("bookingId");
		int bookingId = Integer.parseInt(bookingIdStr);
		String status = (String) requestBody.get("status");
		String paymentMethod = (String) requestBody.get("paymentMethod");
		
		//to do: fix association attributes
		Payment Payment = PaymentFactory.createPayment(
			"SIPH.payment.core.PaymentImpl",
		userId
		, bookingId
		, totalAmount
		, status
		, paymentMethod
		, bookingimpl
		);
		Repository.saveObject(payment);
		return payment;
	}

    public Payment createPayment(Map<String, Object> requestBody, int id){
		String userIdStr = (String) vmjExchange.getRequestBodyForm("userId");
		int userId = Integer.parseInt(userIdStr);
		String bookingIdStr = (String) vmjExchange.getRequestBodyForm("bookingId");
		int bookingId = Integer.parseInt(bookingIdStr);
		String status = (String) vmjExchange.getRequestBodyForm("status");
		String paymentMethod = (String) vmjExchange.getRequestBodyForm("paymentMethod");
		
		//to do: fix association attributes
		
		Payment payment = PaymentFactory.createPayment("SIPH.payment.core.PaymentImpl", userId, bookingId, totalAmount, status, paymentMethod, bookingimpl);
		return payment;
	}

    public HashMap<String, Object> updatePayment(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("userIdbookingId");
		int id = Integer.parseInt(idStr);
		Payment payment = Repository.getObject(id);
		
		String userIdStr = (String) requestBody.get("userId");
		payment.setUserId(Integer.parseInt(userIdStr));
		String bookingIdStr = (String) requestBody.get("bookingId");
		payment.setBookingId(Integer.parseInt(bookingIdStr));
		payment.setStatus((String) requestBody.get("status"));
		payment.setPaymentMethod((String) requestBody.get("paymentMethod"));
		
		Repository.updateObject(payment);
		
		//to do: fix association attributes
		
		return payment.toHashMap();
		
	}

    public HashMap<String, Object> getPayment(Map<String, Object> requestBody){
		List<HashMap<String, Object>> paymentList = getAllPayment("payment_impl");
		for (HashMap<String, Object> payment : paymentList){
			int record_id = ((Double) payment.get("record_id")).intValue();
			if (record_id == id){
				return payment;
			}
		}
		return null;
	}

	public HashMap<String, Object> getPaymentById(int id){
		String idStr = vmjExchange.getGETParam("userIdbookingId"); 
		int id = Integer.parseInt(idStr);
		Payment payment = paymentRepository.getObject(id);
		return payment.toHashMap();
	}

    public List<HashMap<String,Object>> getAllPayment(Map<String, Object> requestBody){
		String table = (String) requestBody.get("table_name");
		List<Payment> List = Repository.getAllObject(table);
		return transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<Payment> List){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
	}

    public List<HashMap<String,Object>> deletePayment(Map<String, Object> requestBody){
		String idStr = ((String) requestBody.get("id"));
		int id = Integer.parseInt(idStr);
		Repository.deleteObject(id);
		return getAllPayment(requestBody);
	}

	private void processPayment() {
		// TODO: implement this method
	}
}
