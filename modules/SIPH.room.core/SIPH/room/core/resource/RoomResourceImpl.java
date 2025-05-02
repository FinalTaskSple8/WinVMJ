package SIPH.room.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.room.RoomFactory;
import prices.auth.vmj.annotations.Restricted;
//add other required packages


public class RoomResourceImpl extends RoomResourceComponent{
	
	private RoomServiceImpl roomServiceImpl = new RoomServiceImpl();

	// @Restriced(permission = "")
    @Route(url="call/room/save")
    public List<HashMap<String,Object>> saveRoom(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Room room = createRoom(vmjExchange);
		roomRepository.saveObject(room);
		return getAllRoom(vmjExchange);
	}

	// @Restriced(permission = "")
    @Route(url="call/room")
    public HashMap<String,Object> room(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Room result = roomServiceImpl.createRoom(requestBody);
			return result.toHashMap();
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

    public Room createRoom(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Room result = roomServiceImpl.createRoom(requestBody);
			return result.toHashMap();
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

    public Room createRoom(VMJExchange vmjExchange, int id){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Room result = roomServiceImpl.createRoom(requestBody, id);
			return result.toHashMap();
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

	// @Restriced(permission = "")
    @Route(url="call/room/update")
    public HashMap<String, Object> updateRoom(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")){
			return null;
		}
		return roomServiceImpl.updateRoom(requestBody);
		
	}

	// @Restriced(permission = "")
    @Route(url="call/room/detail")
    public HashMap<String, Object> getRoom(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		return roomServiceImpl.getRoom(requestBody);
	}

	// @Restriced(permission = "")
    @Route(url="call/room/list")
    public List<HashMap<String,Object>> getAllRoom(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		return roomServiceImpl.getAllRoom(requestBody);
	}

	// @Restriced(permission = "")
    @Route(url="call/room/delete")
    public List<HashMap<String,Object>> deleteRoom(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		return roomServiceImpl.deleteRoom(requestBody);
	}


	
	private Room getRoomByHotelId(int hotelId) {
		// TODO: implement this method
	}
}
