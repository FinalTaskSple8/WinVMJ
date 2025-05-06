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
		  = create(vmjExchange);
		Repository.saveObject();
		return getAll(vmjExchange);
	}

    public Payment create(VMJExchange vmjExchange){
		String gateway = (String) vmjExchange.getRequestBodyForm("gateway");
		
		  = record.create(vmjExchange);
		 deco = Factory.create("SIPH.onlinepayment.core.PaymentImpl", , gateway);
			return deco;
	}

    public Payment create(VMJExchange vmjExchange, int id){
		String gateway = (String) vmjExchange.getRequestBodyForm("gateway");
		  = Repository.getObject(id);
		int recordId = (((Decorator) saved.getRecord()).getId();
		
		  = record.create(vmjExchange);
		 deco = Factory.create("SIPH.onlinepayment.core.PaymentImpl", id, , gateway);
			return deco;
	}

    // @Restriced(permission = "")
    @Route(url="call/onlinepayment/update")
    public HashMap<String, Object> update(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		String idStr = (String) vmjExchange.getRequestBodyForm("");
		int id = Integer.parseInt(idStr);
		
		  = Repository.getObject(id);
		 = create(vmjExchange, id);
		
		Repository.updateObject();
		 = Repository.getObject(id);
		//to do: fix association attributes
		
		return .toHashMap();
		
	}

	// @Restriced(permission = "")
    @Route(url="call/onlinepayment/detail")
    public HashMap<String, Object> get(VMJExchange vmjExchange){
		return record.getPayment(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/onlinepayment/list")
    public List<HashMap<String,Object>> getAll(VMJExchange vmjExchange){
		List<> List = Repository.getAllObject("_impl");
		return transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<> List){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
	}

	// @Restriced(permission = "")
    @Route(url="call/onlinepayment/delete")
    public List<HashMap<String,Object>> deletePayment(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		String idStr = (String) vmjExchange.getRequestBodyForm("");
		int id = Integer.parseInt(idStr);
		Repository.deleteObject(id);
		return getAll(vmjExchange);
	}

	public void processPayment() {
		// TODO: implement this method
	}

	public void authenticate() {
		// TODO: implement this method
	}
}
