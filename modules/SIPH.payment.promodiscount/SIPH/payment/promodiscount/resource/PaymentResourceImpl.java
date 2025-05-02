package SIPH.payment.promodiscount;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import SIPH.payment.core.PaymentResourceDecorator;
import SIPH.payment.core.PaymentImpl;
import SIPH.payment.core.PaymentResourceComponent;

public class PaymentResourceImpl extends PaymentResourceDecorator {
    public PaymentResourceImpl (PaymentResourceComponent record) {
        super(record);
    }

    // @Restriced(permission = "")
    @Route(url="call/promodiscount/save")
    public List<HashMap<String,Object>> save(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		PaymentPromoDiscount paymentpromodiscount = createPaymentPromoDiscount(vmjExchange);
		paymentpromodiscountRepository.saveObject(paymentpromodiscount);
		return getAllPaymentPromoDiscount(vmjExchange);
	}

    public Payment createPaymentPromoDiscount(VMJExchange vmjExchange){
		String code = (String) vmjExchange.getRequestBodyForm("code");
		
		PaymentPromoDiscount paymentpromodiscount = record.createPaymentPromoDiscount(vmjExchange);
		PaymentPromoDiscount paymentpromodiscountdeco = PaymentPromoDiscountFactory.createPaymentPromoDiscount("SIPH.promodiscount.core.PaymentImpl", paymentpromodiscount, userId, bookingId, totalAmount, status, paymentMethod, bookingimpl
		code, discountAmount
		);
			return paymentpromodiscountdeco;
	}


    public Payment createPaymentPromoDiscount(VMJExchange vmjExchange, int id){
		String code = (String) vmjExchange.getRequestBodyForm("code");
		PaymentPromoDiscount paymentpromodiscount = paymentpromodiscountRepository.getObject(id);
		int recordPaymentPromoDiscountId = (((PaymentPromoDiscountDecorator) savedPaymentPromoDiscount.getRecord()).getId();
		
		PaymentPromoDiscount paymentpromodiscount = record.createPaymentPromoDiscount(vmjExchange);
		PaymentPromoDiscount paymentpromodiscountdeco = PaymentPromoDiscountFactory.createPaymentPromoDiscount("SIPH.promodiscount.core.PaymentImpl", id, paymentpromodiscount, userId, bookingId, totalAmount, status, paymentMethod, bookingimpl
		code, discountAmount
		);
			return paymentpromodiscountdeco;
	}

	// @Restriced(permission = "")
    @Route(url="call/promodiscount/update")
    public HashMap<String, Object> updatePaymentPromoDiscount(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		String idStr = (String) vmjExchange.getRequestBodyForm("userIdbookingId");
		int id = Integer.parseInt(idStr);
		
		PaymentPromoDiscount paymentpromodiscount = paymentpromodiscountRepository.getObject(id);
		paymentpromodiscount = createPaymentPromoDiscount(vmjExchange, id);
		
		paymentpromodiscountRepository.updateObject(paymentpromodiscount);
		paymentpromodiscount = paymentpromodiscountRepository.getObject(id);
		//to do: fix association attributes
		
		return paymentpromodiscount.toHashMap();
		
	}

	// @Restriced(permission = "")
    @Route(url="call/promodiscount/detail")
    public HashMap<String, Object> getPaymentPromoDiscount(VMJExchange vmjExchange){
		return record.getPaymentPromoDiscount(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/promodiscount/list")
    public List<HashMap<String,Object>> getAllPaymentPromoDiscount(VMJExchange vmjExchange){
		List<PaymentPromoDiscount> paymentpromodiscountList = paymentpromodiscountRepository.getAllObject("paymentpromodiscount_impl");
		return transformPaymentPromoDiscountListToHashMap(paymentpromodiscountList);
	}

    public List<HashMap<String,Object>> transformPaymentPromoDiscountListToHashMap(List<PaymentPromoDiscount> PaymentPromoDiscountList){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < PaymentPromoDiscountList.size(); i++) {
            resultList.add(PaymentPromoDiscountList.get(i).toHashMap());
        }

        return resultList;
	}

	// @Restriced(permission = "")
    @Route(url="call/promodiscount/delete")
    public List<HashMap<String,Object>> deletePaymentPromoDiscount(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		String idStr = (String) vmjExchange.getRequestBodyForm("userIdbookingId");
		int id = Integer.parseInt(idStr);
		paymentpromodiscountRepository.deleteObject(id);
		return getAllPaymentPromoDiscount(vmjExchange);
	}

	private void processPayment() {
		// TODO: implement this method
	}

	private void applyDiscount() {
		// TODO: implement this method
	}
	
}
