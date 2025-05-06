package SIPH.booking.roomupgrade;
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
    @Route(url="call/roomupgrade/save")
    public List<HashMap<String,Object>> save(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		  = create(vmjExchange);
		Repository.saveObject();
		return getAll(vmjExchange);
	}

    public Booking create(VMJExchange vmjExchange){
		String upgradedRoomType = (String) vmjExchange.getRequestBodyForm("upgradedRoomType");
		
		  = record.create(vmjExchange);
		 deco = Factory.create("SIPH.roomupgrade.core.BookingImpl", , upgradedRoomType, upgradeCost);
			return deco;
	}

    public Booking create(VMJExchange vmjExchange, int id){
		String upgradedRoomType = (String) vmjExchange.getRequestBodyForm("upgradedRoomType");
		  = Repository.getObject(id);
		int recordId = (((Decorator) saved.getRecord()).getId();
		
		  = record.create(vmjExchange);
		 deco = Factory.create("SIPH.roomupgrade.core.BookingImpl", id, , upgradedRoomType, upgradeCost);
			return deco;
	}

    // @Restriced(permission = "")
    @Route(url="call/roomupgrade/update")
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
    @Route(url="call/roomupgrade/detail")
    public HashMap<String, Object> get(VMJExchange vmjExchange){
		return record.getBooking(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/roomupgrade/list")
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
    @Route(url="call/roomupgrade/delete")
    public List<HashMap<String,Object>> deleteBooking(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		String idStr = (String) vmjExchange.getRequestBodyForm("");
		int id = Integer.parseInt(idStr);
		Repository.deleteObject(id);
		return getAll(vmjExchange);
	}

	public void requestRoomUpgrade(String newRoomType, Real additionalCost) {
		// TODO: implement this method
	}

	public Real calculateTotalPrice() {
		// TODO: implement this method
	}
}
