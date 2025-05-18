package SIPH.payment.onlinepayment;

import java.util.*;
import java.util.UUID;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import SIPH.payment.core.Payment;
import SIPH.payment.core.PaymentResourceDecorator;
import SIPH.payment.core.PaymentComponent;
import SIPH.payment.core.PaymentResourceComponent;
import SIPH.payment.core.PaymentServiceImpl;
import SIPH.payment.PaymentFactory;
import vmj.hibernate.integrator.RepositoryUtil;

public class PaymentResourceImpl extends PaymentResourceDecorator {

    private RepositoryUtil<Payment> repository = new RepositoryUtil<>(PaymentImpl.class);
    private RepositoryUtil<PaymentComponent> baseRepository = new RepositoryUtil<>(PaymentComponent.class);
    private PaymentServiceImpl service = new PaymentServiceImpl();

    public PaymentResourceImpl(PaymentResourceComponent record) {
        super(record);
    }

    @Override
    public List<HashMap<String, Object>> savePayment(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }
        Map<String, Object> requestBody = vmjExchange.getPayload();
        Payment payment = service.createPayment(requestBody);
        repository.saveObject(payment);
        return getAll(vmjExchange);
    }

    @Route(url = "call/onlinepayment/save")
    public List<HashMap<String, Object>> save(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) return null;
        Payment payment = create(vmjExchange);
        repository.saveObject(payment);
        return getAll(vmjExchange);
    }

    public Payment create(VMJExchange vmjExchange) {
        Map<String, Object> payload = vmjExchange.getPayload();
        String gateway = (String) payload.get("gateway");

        // Step 1: createPayment dari komponen dasar
        HashMap<String, Object> baseResult = record.createPayment(vmjExchange);
        UUID baseId = UUID.fromString(baseResult.get("id").toString());

        // Step 2: ambil object PaymentComponent dari DB
        PaymentComponent baseObject = baseRepository.getObject(baseId);

        return new PaymentImpl(baseObject, gateway);
    }

    @Route(url = "call/onlinepayment/update")
    public HashMap<String, Object> update(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) return null;

        String idStr = (String) vmjExchange.getRequestBodyForm("id");
        UUID id = UUID.fromString(idStr);

        // Delete old then create new with same ID (or update if needed)
        Payment updated = create(vmjExchange);
        repository.updateObject(updated);
        return repository.getObject(id).toHashMap();
    }

    @Route(url = "call/onlinepayment/detail")
    public HashMap<String, Object> get(VMJExchange vmjExchange) {
        return record.getPayment(vmjExchange);
    }

    @Route(url = "call/onlinepayment/list")
    public List<HashMap<String, Object>> getAll(VMJExchange vmjExchange) {
        List<Payment> list = repository.getAllObject("payment_onlinepayment");
        return transformListToHashMap(list);
    }

    public List<HashMap<String, Object>> transformListToHashMap(List<Payment> list) {
        List<HashMap<String, Object>> resultList = new ArrayList<>();
        for (Payment p : list) {
            resultList.add(p.toHashMap());
        }
        return resultList;
    }

    @Route(url = "call/onlinepayment/delete")
    public List<HashMap<String, Object>> deletePayment(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) return null;
        String idStr = (String) vmjExchange.getRequestBodyForm("id");
        UUID id = UUID.fromString(idStr);
        repository.deleteObject(id);
        return getAll(vmjExchange);
    }

    @Route(url = "call/onlinepayment/process")
    public HashMap<String, Object> processPayment(VMJExchange vmjExchange) {
        
    }

    public void authenticate() {
        // Optional
    }
}
