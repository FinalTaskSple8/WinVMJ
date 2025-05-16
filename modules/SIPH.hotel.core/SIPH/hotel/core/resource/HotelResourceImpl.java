package SIPH.hotel.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.hotel.HotelFactory;
//import prices.auth.vmj.annotations.Restricted;
//add other required packages
import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;
public class HotelResourceImpl extends HotelResourceComponent{
	
	private HotelServiceImpl hotelServiceImpl = new HotelServiceImpl();

	// @Restriced(permission = "")
    @Route(url="call/hotel")
    public HashMap<String,Object> createHotel(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Hotel result = hotelServiceImpl.createHotel(requestBody);
			return result.toHashMap();
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

    // @Restriced(permission = "")
    @Route(url="call/hotel/update")
    public HashMap<String, Object> updateHotel(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")){
			return null;
		}
		return hotelServiceImpl.updateHotel(requestBody);
		
	}

	// @Restriced(permission = "")
    @Route(url="call/hotel/detail")
	public HashMap<String, Object> getHotel(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		String idStr = (String) requestBody.get("id");
		UUID id = UUID.fromString(idStr);
		Hotel hotel = hotelServiceImpl.getHotelById(id);
		if (hotel == null) {
			HashMap<String, Object> error = new HashMap<>();
			error.put("message", "Hotel not found");
			error.put("vmjErrorCode", 4006);
			return error;
		}
		return hotel.toHashMap();
	}

	// @Restriced(permission = "")
    @Route(url="call/hotel/list")
    public List<HashMap<String,Object>> getAllHotel(VMJExchange vmjExchange){
		List<Hotel> hotelList = hotelServiceImpl.getAllHotel();
		return hotelServiceImpl.transformListToHashMap(hotelList);
	}

    
	// @Restriced(permission = "")
    @Route(url="call/hotel/delete")
	public List<HashMap<String,Object>> deleteHotel(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Object idObj = requestBody.get("id");
		UUID id;
		if (idObj instanceof UUID) {
			id = (UUID) idObj;
		} else if (idObj instanceof String) {
			id = UUID.fromString((String) idObj);
		} else {
			throw new IllegalArgumentException("Invalid id type: " + (idObj != null ? idObj.getClass() : "null"));
		}
		// Panggil service untuk hapus hotel
		hotelServiceImpl.deleteHotel(id);
		// Ambil list hotel terbaru
		List<Hotel> hotelList = hotelServiceImpl.getAllHotel();
		return hotelServiceImpl.transformListToHashMap(hotelList);
	}
    
    // @Restricted(permission = "")
    @Route(url = "call/hotel/save")
    @Override
    public List<HashMap<String, Object>> saveHotel(VMJExchange vmjExchange) {
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }
        return hotelServiceImpl.saveHotel(vmjExchange);
    }


	public void addRoomToHotel(Room rooms) {
		// TODO: implement this method
	}
	
//	@Route(url="call/hotel/update-with-room")
//	public HashMap<String, Object> updateHotelWithRoom(VMJExchange vmjExchange){
//		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
//			return null;
//		}
//		Map<String, Object> requestBody = vmjExchange.getPayload();
//		return hotelServiceImpl.updateHotelWithRoom(requestBody);
//	}
	
	@Route(url = "call/hotel/search")
	public List<HashMap<String, Object>> searchHotel(VMJExchange vmjExchange) {
	    Map<String, Object> requestBody = vmjExchange.getPayload(); 
	    return hotelServiceImpl.searchHotel(requestBody);
	}

}
