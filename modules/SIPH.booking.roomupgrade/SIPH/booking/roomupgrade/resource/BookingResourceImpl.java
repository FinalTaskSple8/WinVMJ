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
		BookingRoomUpgrade bookingroomupgrade = createBookingRoomUpgrade(vmjExchange);
		bookingroomupgradeRepository.saveObject(bookingroomupgrade);
		return getAllBookingRoomUpgrade(vmjExchange);
	}

    public Booking createBookingRoomUpgrade(VMJExchange vmjExchange){
		String upgradedRoomType = (String) vmjExchange.getRequestBodyForm("upgradedRoomType");
		
		BookingRoomUpgrade bookingroomupgrade = record.createBookingRoomUpgrade(vmjExchange);
		BookingRoomUpgrade bookingroomupgradedeco = BookingRoomUpgradeFactory.createBookingRoomUpgrade("SIPH.roomupgrade.core.BookingImpl", bookingroomupgrade, bookingId, userId, checkInDate, checkOutDate, numberOfGuests, totalPrice, status, roomId, paymentId, roomimpl
		upgradedRoomType, upgradeCost
		);
			return bookingroomupgradedeco;
	}


    public Booking createBookingRoomUpgrade(VMJExchange vmjExchange, int id){
		String upgradedRoomType = (String) vmjExchange.getRequestBodyForm("upgradedRoomType");
		BookingRoomUpgrade bookingroomupgrade = bookingroomupgradeRepository.getObject(id);
		int recordBookingRoomUpgradeId = (((BookingRoomUpgradeDecorator) savedBookingRoomUpgrade.getRecord()).getId();
		
		BookingRoomUpgrade bookingroomupgrade = record.createBookingRoomUpgrade(vmjExchange);
		BookingRoomUpgrade bookingroomupgradedeco = BookingRoomUpgradeFactory.createBookingRoomUpgrade("SIPH.roomupgrade.core.BookingImpl", id, bookingroomupgrade, bookingId, userId, checkInDate, checkOutDate, numberOfGuests, totalPrice, status, roomId, paymentId, roomimpl
		upgradedRoomType, upgradeCost
		);
			return bookingroomupgradedeco;
	}

	// @Restriced(permission = "")
    @Route(url="call/roomupgrade/update")
    public HashMap<String, Object> updateBookingRoomUpgrade(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		String idStr = (String) vmjExchange.getRequestBodyForm("bookingIduserIdroomIdpaymentId");
		int id = Integer.parseInt(idStr);
		
		BookingRoomUpgrade bookingroomupgrade = bookingroomupgradeRepository.getObject(id);
		bookingroomupgrade = createBookingRoomUpgrade(vmjExchange, id);
		
		bookingroomupgradeRepository.updateObject(bookingroomupgrade);
		bookingroomupgrade = bookingroomupgradeRepository.getObject(id);
		//to do: fix association attributes
		
		return bookingroomupgrade.toHashMap();
		
	}

	// @Restriced(permission = "")
    @Route(url="call/roomupgrade/detail")
    public HashMap<String, Object> getBookingRoomUpgrade(VMJExchange vmjExchange){
		return record.getBookingRoomUpgrade(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/roomupgrade/list")
    public List<HashMap<String,Object>> getAllBookingRoomUpgrade(VMJExchange vmjExchange){
		List<BookingRoomUpgrade> bookingroomupgradeList = bookingroomupgradeRepository.getAllObject("bookingroomupgrade_impl");
		return transformBookingRoomUpgradeListToHashMap(bookingroomupgradeList);
	}

    public List<HashMap<String,Object>> transformBookingRoomUpgradeListToHashMap(List<BookingRoomUpgrade> BookingRoomUpgradeList){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < BookingRoomUpgradeList.size(); i++) {
            resultList.add(BookingRoomUpgradeList.get(i).toHashMap());
        }

        return resultList;
	}

	// @Restriced(permission = "")
    @Route(url="call/roomupgrade/delete")
    public List<HashMap<String,Object>> deleteBookingRoomUpgrade(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		String idStr = (String) vmjExchange.getRequestBodyForm("bookingIduserIdroomIdpaymentId");
		int id = Integer.parseInt(idStr);
		bookingroomupgradeRepository.deleteObject(id);
		return getAllBookingRoomUpgrade(vmjExchange);
	}

	private void requestRoomUpgrade(String newRoomType, Real additionalCost) {
		// TODO: implement this method
	}

	private Real calculateTotalPrice() {
		// TODO: implement this method
	}
	
}
