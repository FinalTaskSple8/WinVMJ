package SIPH.payment.core;


import java.math.BigDecimal;
import java.util.*;

import SIPH.booking.BookingFactory;
import SIPH.booking.core.Booking;
import SIPH.booking.core.BookingImpl;
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
import vmj.auth.annotations.Restricted;
//add other required packages

public class PaymentServiceImpl extends PaymentServiceComponent{

    public List<HashMap<String,Object>> savePayment(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Payment payment = createPayment(vmjExchange.getPayload());
		paymentRepository.saveObject(payment);
		return getAllPayment(vmjExchange.getPayload());

	}
    
    @Override
    public List<HashMap<String,Object>> savePayment(Map<String, Object> requestBody) {
        Payment payment = createPayment(requestBody);
        paymentRepository.saveObject(payment);
        return getAllPayment(requestBody);
    }


    public Payment createPayment(Map<String, Object> requestBody){
        UUID userId = UUID.fromString((String) requestBody.get("userId"));
        UUID bookingId = UUID.fromString((String) requestBody.get("bookingId"));
        BigDecimal totalAmount = new BigDecimal((String) requestBody.get("totalAmount"));
        String status = (String) requestBody.get("status");
        String paymentMethod = (String) requestBody.get("paymentMethod");
        UUID id = UUID.randomUUID();

        // bookingimpl bisa null atau dummy dulu kalau belum di-link
        // BookingImpl bookingimpl = new BookingImpl();
        BookingImpl bookingimpl = null;

        Payment payment = PaymentFactory.createPayment(
            "SIPH.payment.core.PaymentImpl",
            userId,
            bookingId,
            totalAmount,
            status,
            paymentMethod,
            // bookingimpl,
            id
        );

        paymentRepository.saveObject(payment);
        return payment;
    }

    public HashMap<String, Object> updatePayment(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("userIdbookingIdid");
		int id = Integer.parseInt(idStr);
		Payment payment = paymentRepository.getObject(id);
		
		payment.setStatus((String) requestBody.get("status"));
		payment.setPaymentMethod((String) requestBody.get("paymentMethod"));
		
		paymentRepository.updateObject(payment);
		
		//to do: fix association attributes
		
		return payment.toHashMap();
		
	}

    public HashMap<String, Object> getPayment(Map<String, Object> requestBody) {
        String idStr = (String) requestBody.get("id");
        UUID targetId = UUID.fromString(idStr);

        List<HashMap<String, Object>> paymentList = getAllPayment(requestBody);
        for (HashMap<String, Object> payment : paymentList) {
            String paymentIdStr = (String) payment.get("id");
            UUID paymentId = UUID.fromString(paymentIdStr);
            if (paymentId.equals(targetId)) {
                return payment;
            }
        }
        return null;
    }


	public HashMap<String, Object> getPaymentById(UUID id){
		return null;
	}

    public List<HashMap<String,Object>> getAllPayment(Map<String, Object> requestBody){
		String table = (String) requestBody.get("table_name");
		List<Payment> List = paymentRepository.getAllObject(table);
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
		paymentRepository.deleteObject(id);
		return getAllPayment(requestBody);
	}

	public void processPayment() {
		// TODO: implement this method
	}
}
