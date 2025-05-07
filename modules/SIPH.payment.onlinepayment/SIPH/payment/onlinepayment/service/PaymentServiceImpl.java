package SIPH.payment.onlinepayment;

import java.util.*;
import java.math.BigDecimal;

import SIPH.payment.core.Payment;
import SIPH.payment.core.PaymentServiceDecorator;
import SIPH.payment.core.PaymentServiceComponent;
import SIPH.payment.PaymentFactory;
import SIPH.booking.core.BookingImpl;
import vmj.hibernate.integrator.RepositoryUtil;

public class PaymentServiceImpl extends PaymentServiceDecorator {

    private RepositoryUtil<Payment> repository = new RepositoryUtil<>(PaymentImpl.class);
    private RepositoryUtil<BookingImpl> bookingRepository = new RepositoryUtil<>(BookingImpl.class);


    public PaymentServiceImpl(PaymentServiceComponent record) {
        super(record);
    }

    public PaymentServiceImpl() {
        super(null);
    }

    public List<HashMap<String,Object>> savePayment(Map<String, Object> requestBody) {
        Payment payment = createPayment(requestBody);
        repository.saveObject(payment);
        return getAllPayment(requestBody);
    }

    @Override
    public Payment createPayment(Map<String, Object> requestBody) {
        UUID userId = UUID.fromString((String) requestBody.get("userId"));
        UUID bookingId = UUID.fromString((String) requestBody.get("bookingId"));
        BigDecimal totalAmount = new BigDecimal((String) requestBody.get("totalAmount"));
        String status = (String) requestBody.get("status");
        String paymentMethod = (String) requestBody.get("paymentMethod");
        String gateway = (String) requestBody.get("gateway");

        BookingImpl bookingimpl = bookingRepository.getObject(bookingId);
        return new PaymentImpl(userId, bookingId, totalAmount, status, paymentMethod, bookingimpl, gateway);
    }


    public HashMap<String, Object> updatePayment(Map<String, Object> requestBody) {
        UUID id = UUID.fromString((String) requestBody.get("id"));
        Payment payment = repository.getObject(id);

        payment.setStatus((String) requestBody.get("status"));
        payment.setPaymentMethod((String) requestBody.get("paymentMethod"));
        ((PaymentImpl) payment).setGateway((String) requestBody.get("gateway"));

        repository.updateObject(payment);
        return payment.toHashMap();
    }

    public HashMap<String, Object> getPayment(Map<String, Object> requestBody) {
        UUID id = UUID.fromString((String) requestBody.get("id"));
        Payment payment = repository.getObject(id);
        return payment.toHashMap();
    }

    public List<HashMap<String,Object>> getAllPayment(Map<String, Object> requestBody) {
        List<Payment> list = repository.getAllObject("payment_onlinepayment");
        List<HashMap<String,Object>> result = new ArrayList<>();
        for (Payment p : list) result.add(p.toHashMap());
        return result;
    }

    public List<HashMap<String,Object>> deletePayment(Map<String, Object> requestBody) {
        UUID id = UUID.fromString((String) requestBody.get("id"));
        repository.deleteObject(id);
        return getAllPayment(requestBody);
    }

    public void processPayment() {
        // Implement if needed
    }

    public void authenticate() {
        // Implement if needed
    }
}
