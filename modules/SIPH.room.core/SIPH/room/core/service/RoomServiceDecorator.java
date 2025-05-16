package SIPH.room.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public abstract class RoomServiceDecorator extends RoomServiceComponent{
	protected RoomServiceComponent record;

    public RoomServiceDecorator(RoomServiceComponent record) {
        this.record = record;
    }

	public Room createRoom(Map<String, Object> requestBody){
		return record.createRoom(requestBody);
	}

	public HashMap<String, Object> getRoom(Map<String, Object> requestBody){
		return record.getRoom(requestBody);
	}

	public List<Room> getAllRoom(){
		return record.getAllRoom();
	}

    public List<HashMap<String,Object>> saveRoom(VMJExchange vmjExchange){
		return record.saveRoom(vmjExchange);
	}

    public HashMap<String, Object> updateRoom(Map<String, Object> requestBody){
		return record.updateRoom(requestBody);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<Room> List){
		return record.transformListToHashMap(List);
	}

    public List<Room> deleteRoom(UUID id){
		return record.deleteRoom(id);
	}

	public HashMap<String, Object> getRoomById(UUID id){
        return record.getRoomById(id);
    }

	public List<Room> getRoomByHotelId(UUID hotelId) {
		return record.getRoomByHotelId(hotelId);
	}
}
