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
		return hotelServiceImpl.getHotel(requestBody);
	}

	// @Restriced(permission = "")
    @Route(url="call/hotel/list")
    public List<HashMap<String,Object>> getAllHotel(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		return hotelServiceImpl.getAllHotel(requestBody);
	}

    
	// @Restriced(permission = "")
    @Route(url="call/hotel/delete")
    public List<HashMap<String,Object>> deleteHotel(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		return hotelServiceImpl.deleteHotel(requestBody);
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
}
