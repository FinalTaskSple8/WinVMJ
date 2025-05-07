package SIPH.payment.promodiscount;

import java.util.*;
import java.util.UUID;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import SIPH.payment.core.Payment;
import SIPH.payment.core.PaymentComponent;
import SIPH.payment.core.PaymentServiceComponent;
import SIPH.payment.core.PaymentResourceDecorator;
import SIPH.payment.core.PaymentResourceComponent;
import vmj.hibernate.integrator.RepositoryUtil;

public class PaymentResourceImpl extends PaymentResourceDecorator {

    private RepositoryUtil<Payment> repository = new RepositoryUtil<>(PaymentImpl.class);
    private final PaymentServiceImpl service;

    public PaymentResourceImpl(PaymentResourceComponent record, PaymentServiceComponent recordService) {
        super(record);
        this.service = new PaymentServiceImpl(recordService);
    }
    
    @Override
    public List<HashMap<String, Object>> savePayment(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) return null;

        Map<String, Object> requestBody = vmjExchange.getPayload();
        Payment payment = service.createPayment(requestBody);
        repository.saveObject(payment);
        return getAll(vmjExchange);
    }
    
    @Route(url="call/promodiscount/save")
    public List<HashMap<String,Object>> save(VMJExchange vmjExchange){
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) return null;
        Payment payment = create(vmjExchange);
        repository.saveObject(payment);
        return getAll(vmjExchange);
    }

    public Payment create(VMJExchange vmjExchange){
    	Map<String, Object> payload = vmjExchange.getPayload();
    	String code = (String) payload.get("code");
    	Double discountAmount = Double.valueOf(payload.get("discountAmount").toString());

    	HashMap<String, Object> baseMap = record.createPayment(vmjExchange); 
    	UUID baseId = UUID.fromString(baseMap.get("id").toString()); // ambil ID
    	PaymentComponent baseObject = new RepositoryUtil<>(PaymentComponent.class).getObject(baseId);

    	return new PaymentImpl(baseObject, code, discountAmount);

    }
    
    @Route(url="call/promodiscount/update")
    public HashMap<String, Object> update(VMJExchange vmjExchange){
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) return null;

        UUID id = UUID.fromString((String) vmjExchange.getRequestBodyForm("id"));
        Payment updated = create(vmjExchange);
        repository.updateObject(updated);
        return repository.getObject(id).toHashMap();
    }

    @Route(url="call/promodiscount/detail")
    public HashMap<String, Object> get(VMJExchange vmjExchange){
        return record.getPayment(vmjExchange);
    }

    @Route(url="call/promodiscount/list")
    public List<HashMap<String,Object>> getAll(VMJExchange vmjExchange){
        List<Payment> list = repository.getAllObject("payment_promodiscount");
        return transformListToHashMap(list);
    }

    public List<HashMap<String,Object>> transformListToHashMap(List<Payment> list){
        List<HashMap<String,Object>> result = new ArrayList<>();
        for (Payment p : list) result.add(p.toHashMap());
        return result;
    }

    @Route(url="call/promodiscount/delete")
    public List<HashMap<String,Object>> deletePayment(VMJExchange vmjExchange){
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) return null;
        UUID id = UUID.fromString((String) vmjExchange.getRequestBodyForm("id"));
        repository.deleteObject(id);
        return getAll(vmjExchange);
    }
}
