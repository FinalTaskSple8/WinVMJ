package SIPH.booking.earlycheckinout;
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
    @Route(url="call/earlycheckinout/save")
    public List<HashMap<String,Object>> save(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		  = create(vmjExchange);
		Repository.saveObject();
		return getAll(vmjExchange);
	}

    public Booking create(VMJExchange vmjExchange){
		boolean earlyCheckIn = (boolean) vmjExchange.getRequestBodyForm("earlyCheckIn");
		boolean lateCheckOut = (boolean) vmjExchange.getRequestBodyForm("lateCheckOut");
		
		  = record.create(vmjExchange);
		 deco = Factory.create("SIPH.earlycheckinout.core.BookingImpl", , earlyCheckIn, lateCheckOut, earlyCheckInFee, lateCheckOutFee);
			return deco;
	}

    public Booking create(VMJExchange vmjExchange, int id){
		boolean earlyCheckIn = (boolean) vmjExchange.getRequestBodyForm("earlyCheckIn");
		boolean lateCheckOut = (boolean) vmjExchange.getRequestBodyForm("lateCheckOut");
		  = Repository.getObject(id);
		int recordId = (((Decorator) saved.getRecord()).getId();
		
		  = record.create(vmjExchange);
		 deco = Factory.create("SIPH.earlycheckinout.core.BookingImpl", id, , earlyCheckIn, lateCheckOut, earlyCheckInFee, lateCheckOutFee);
			return deco;
	}

    // @Restriced(permission = "")
    @Route(url="call/earlycheckinout/update")
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
    @Route(url="call/earlycheckinout/detail")
    public HashMap<String, Object> get(VMJExchange vmjExchange){
		return record.getBooking(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/earlycheckinout/list")
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
    @Route(url="call/earlycheckinout/delete")
    public List<HashMap<String,Object>> deleteBooking(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		String idStr = (String) vmjExchange.getRequestBodyForm("");
		int id = Integer.parseInt(idStr);
		Repository.deleteObject(id);
		return getAll(vmjExchange);
	}

	public void requestEarlyCheckIn(Real fee) {
		// TODO: implement this method
	}

	public void requestLateCheckOut(Real fee) {
		// TODO: implement this method
	}

	public Real calculateTotalPrice() {
		// TODO: implement this method
	}
}
