package SIPH.booking.additionalservices;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import SIPH.booking.core.BookingResourceDecorator;
import SIPH.booking.core.BookingImpl;
import SIPH.booking.core.BookingResourceComponent;

public class BookingResourceImpl extends BookingResourceDecorator {
    public BookingResourceImpl (BookingResourceComponent record) {
        super(record);
    }

    // @Restriced(permission = "")
    @Route(url="call/additionalservices/save")
    public List<HashMap<String,Object>> save(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		  = create(vmjExchange);
		Repository.saveObject();
		return getAll(vmjExchange);
	}

    public Booking create(VMJExchange vmjExchange){
		String additionalServices = (String) vmjExchange.getRequestBodyForm("additionalServices");
		
		  = record.create(vmjExchange);
		 deco = Factory.create("SIPH.additionalservices.core.BookingImpl", , additionalServices, servicesCost);
			return deco;
	}

    public Booking create(VMJExchange vmjExchange, int id){
		String additionalServices = (String) vmjExchange.getRequestBodyForm("additionalServices");
		  = Repository.getObject(id);
		int recordId = (((Decorator) saved.getRecord()).getId();
		
		  = record.create(vmjExchange);
		 deco = Factory.create("SIPH.additionalservices.core.BookingImpl", id, , additionalServices, servicesCost);
			return deco;
	}

    // @Restriced(permission = "")
    @Route(url="call/additionalservices/update")
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
    @Route(url="call/additionalservices/detail")
    public HashMap<String, Object> get(VMJExchange vmjExchange){
		return record.getBooking(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/additionalservices/list")
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
    @Route(url="call/additionalservices/delete")
    public List<HashMap<String,Object>> deleteBooking(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		String idStr = (String) vmjExchange.getRequestBodyForm("");
		int id = Integer.parseInt(idStr);
		Repository.deleteObject(id);
		return getAll(vmjExchange);
	}

	public void addService(String serviceName, Real serviceCost) {
		// TODO: implement this method
	}

	public Real calculateTotalPrice() {
		// TODO: implement this method
	}
}
