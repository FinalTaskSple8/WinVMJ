package SIPH.hotel.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.hotel.HotelFactory;
import prices.auth.vmj.annotations.Restricted;
//add other required packages


public class HotelResourceImpl extends HotelResourceComponent{
	
	private HotelServiceImpl hotelServiceImpl = new HotelServiceImpl();

	// @Restriced(permission = "")
    @Route(url="call/hotel/save")
    public List<HashMap<String,Object>> saveHotel(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Hotel hotel = createHotel(vmjExchange);
		hotelRepository.saveObject(hotel);
		return getAllHotel(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/hotel")
    public HashMap<String,Object> hotel(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Hotel result = hotelServiceImpl.createHotel(requestBody);
			return result.toHashMap();
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

    public Hotel createHotel(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Hotel result = hotelServiceImpl.createHotel(requestBody);
			return result.toHashMap();
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

    public Hotel createHotel(VMJExchange vmjExchange, int id){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Hotel result = hotelServiceImpl.createHotel(requestBody, id);
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


	
	private void addRoomToHotel(Room rooms) {
		// TODO: implement this method
	}
}
