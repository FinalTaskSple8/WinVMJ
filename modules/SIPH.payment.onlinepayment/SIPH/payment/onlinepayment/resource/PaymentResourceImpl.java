package SIPH.payment.onlinepayment;
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
    @Route(url="call/onlinepayment/save")
    public List<HashMap<String,Object>> save(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		PaymentOnlinePayment paymentonlinepayment = createPaymentOnlinePayment(vmjExchange);
		paymentonlinepaymentRepository.saveObject(paymentonlinepayment);
		return getAllPaymentOnlinePayment(vmjExchange);
	}

    public Payment createPaymentOnlinePayment(VMJExchange vmjExchange){
		String gateway = (String) vmjExchange.getRequestBodyForm("gateway");
		
		PaymentOnlinePayment paymentonlinepayment = record.createPaymentOnlinePayment(vmjExchange);
		PaymentOnlinePayment paymentonlinepaymentdeco = PaymentOnlinePaymentFactory.createPaymentOnlinePayment("SIPH.onlinepayment.core.PaymentImpl", paymentonlinepayment, userId, bookingId, totalAmount, status, paymentMethod, bookingimpl
		gateway
		);
			return paymentonlinepaymentdeco;
	}


    public Payment createPaymentOnlinePayment(VMJExchange vmjExchange, int id){
		String gateway = (String) vmjExchange.getRequestBodyForm("gateway");
		PaymentOnlinePayment paymentonlinepayment = paymentonlinepaymentRepository.getObject(id);
		int recordPaymentOnlinePaymentId = (((PaymentOnlinePaymentDecorator) savedPaymentOnlinePayment.getRecord()).getId();
		
		PaymentOnlinePayment paymentonlinepayment = record.createPaymentOnlinePayment(vmjExchange);
		PaymentOnlinePayment paymentonlinepaymentdeco = PaymentOnlinePaymentFactory.createPaymentOnlinePayment("SIPH.onlinepayment.core.PaymentImpl", id, paymentonlinepayment, userId, bookingId, totalAmount, status, paymentMethod, bookingimpl
		gateway
		);
			return paymentonlinepaymentdeco;
	}

	// @Restriced(permission = "")
    @Route(url="call/onlinepayment/update")
    public HashMap<String, Object> updatePaymentOnlinePayment(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		String idStr = (String) vmjExchange.getRequestBodyForm("userIdbookingId");
		int id = Integer.parseInt(idStr);
		
		PaymentOnlinePayment paymentonlinepayment = paymentonlinepaymentRepository.getObject(id);
		paymentonlinepayment = createPaymentOnlinePayment(vmjExchange, id);
		
		paymentonlinepaymentRepository.updateObject(paymentonlinepayment);
		paymentonlinepayment = paymentonlinepaymentRepository.getObject(id);
		//to do: fix association attributes
		
		return paymentonlinepayment.toHashMap();
		
	}

	// @Restriced(permission = "")
    @Route(url="call/onlinepayment/detail")
    public HashMap<String, Object> getPaymentOnlinePayment(VMJExchange vmjExchange){
		return record.getPaymentOnlinePayment(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/onlinepayment/list")
    public List<HashMap<String,Object>> getAllPaymentOnlinePayment(VMJExchange vmjExchange){
		List<PaymentOnlinePayment> paymentonlinepaymentList = paymentonlinepaymentRepository.getAllObject("paymentonlinepayment_impl");
		return transformPaymentOnlinePaymentListToHashMap(paymentonlinepaymentList);
	}

    public List<HashMap<String,Object>> transformPaymentOnlinePaymentListToHashMap(List<PaymentOnlinePayment> PaymentOnlinePaymentList){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < PaymentOnlinePaymentList.size(); i++) {
            resultList.add(PaymentOnlinePaymentList.get(i).toHashMap());
        }

        return resultList;
	}

	// @Restriced(permission = "")
    @Route(url="call/onlinepayment/delete")
    public List<HashMap<String,Object>> deletePaymentOnlinePayment(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		String idStr = (String) vmjExchange.getRequestBodyForm("userIdbookingId");
		int id = Integer.parseInt(idStr);
		paymentonlinepaymentRepository.deleteObject(id);
		return getAllPaymentOnlinePayment(vmjExchange);
	}

	private void processPayment() {
		// TODO: implement this method
	}

	private void authenticate() {
		// TODO: implement this method
	}
	
}
