package SIPH.payment.promodiscount;

import java.util.*;

import SIPH.payment.core.Payment;
import SIPH.payment.core.PaymentServiceDecorator;
import SIPH.payment.core.PaymentServiceComponent;
import vmj.hibernate.integrator.RepositoryUtil;
import SIPH.payment.core.PaymentComponent;


public class PaymentServiceImpl extends PaymentServiceDecorator {

    private RepositoryUtil<Payment> repository = new RepositoryUtil<>(PaymentImpl.class);

    public PaymentServiceImpl(PaymentServiceComponent record) {
        super(record);
    }

    public List<HashMap<String,Object>> savePayment(Map<String, Object> requestBody){
        Payment payment = createPayment(requestBody);
        repository.saveObject(payment);
        return getAllPayment(requestBody);
    }

    @Override
    public Payment createPayment(Map<String, Object> requestBody){
        Payment base = record.createPayment(requestBody); 
        UUID baseId = base.getId();
        PaymentComponent baseObject = new RepositoryUtil<>(PaymentComponent.class).getObject(baseId); 

        String code = (String) requestBody.get("code");
        Double discountAmount = Double.valueOf(requestBody.get("discountAmount").toString());

        return new PaymentImpl(baseObject, code, discountAmount);
    }


    public void processPayment() {
        System.out.println("Promo discount payment processed.");
    }

    public void applyDiscount() {
        System.out.println("Discount applied.");
    }
}
