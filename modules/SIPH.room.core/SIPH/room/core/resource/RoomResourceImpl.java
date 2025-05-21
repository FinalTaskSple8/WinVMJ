package SIPH.room.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.room.RoomFactory;
//import prices.auth.vmj.annotations.Restricted;
//add other required packages

public class RoomResourceImpl extends RoomResourceComponent{
	
	private RoomServiceImpl roomServiceImpl = new RoomServiceImpl();

	// @Restriced(permission = "")
    @Route(url="call/room")
    public HashMap<String,Object> createRoom(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			Room result = roomServiceImpl.createRoom(requestBody);
			System.out.println("DEBUG Response: " + result.toHashMap()); // Tambahkan ini
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
		Object idObj = requestBody.get("id");
		UUID id;
		if (idObj instanceof UUID) {
			id = (UUID) idObj;
		} else if (idObj instanceof String) {
			id = UUID.fromString((String) idObj);
		} else {
			throw new IllegalArgumentException("Invalid id type: " + (idObj != null ? idObj.getClass() : "null"));
		}
		Room room = roomServiceImpl.getRoomById(id);
		if (room == null) {
			HashMap<String, Object> error = new HashMap<>();
			error.put("message", "Room not found");
			error.put("vmjErrorCode", 4006);
			return error;
		}
		return room.toHashMap();
}

	// @Restriced(permission = "")
    @Route(url="call/room/list")
    public List<HashMap<String,Object>> getAllRoom(VMJExchange vmjExchange){
		List<Room> roomList = roomServiceImpl.getAllRoom();
		return roomServiceImpl.transformListToHashMap(roomList);
	}

    
	// @Restriced(permission = "")
    @Route(url="call/room/delete")
    public List<HashMap<String,Object>> deleteRoom(VMJExchange vmjExchange){
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
		roomServiceImpl.deleteRoom(id);
		List<Room> roomList = roomServiceImpl.getAllRoom();
		return roomServiceImpl.transformListToHashMap(roomList);
	}
    
 // @Restriced(permission = "")
    @Route(url="call/room/save-room")
    public List<HashMap<String,Object>> saveRoom(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		return null;
	}

	@Route(url="call/room/by-hotel")
	public List<HashMap<String,Object>> getRoomByHotelId(VMJExchange vmjExchange) {
		Map<String, Object> requestBody = vmjExchange.getPayload();
		Object hotelIdObj = requestBody.get("hotelId");
		UUID hotelId;
		if (hotelIdObj instanceof UUID) {
			hotelId = (UUID) hotelIdObj;
		} else if (hotelIdObj instanceof String) {
			hotelId = UUID.fromString((String) hotelIdObj);
		} else {
			throw new IllegalArgumentException("Invalid hotelId type: " + (hotelIdObj != null ? hotelIdObj.getClass() : "null"));
		}
		List<Room> rooms = roomServiceImpl.getRoomByHotelId(hotelId);
		return roomServiceImpl.transformListToHashMap(rooms);
	}
}
