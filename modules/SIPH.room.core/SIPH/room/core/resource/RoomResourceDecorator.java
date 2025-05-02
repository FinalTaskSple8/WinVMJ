package SIPH.room.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

public abstract class RoomResourceDecorator extends RoomResourceComponent{
	protected RoomResourceComponent record;

    public RoomResourceDecorator(RoomResourceComponent record) {
        this.record = record;
    }

    public List<HashMap<String,Object>> saveRoom(VMJExchange vmjExchange){
		return record.saveRoom(vmjExchange);
	}

    public Room createRoom(VMJExchange vmjExchange){
		return record.createRoom(vmjExchange);
	}

    public Room createRoom(VMJExchange vmjExchange, int id){
		return record.createRoom(vmjExchange, id);
	}

    public HashMap<String, Object> updateRoom(VMJExchange vmjExchange){
		return record.updateRoom(vmjExchange);
	}

    public HashMap<String, Object> getRoom(VMJExchange vmjExchange){
		return record.getRoom(vmjExchange);
	}

    public List<HashMap<String,Object>> getAllRoom(VMJExchange vmjExchange){
		return record.getAllRoom(vmjExchange);
	}

    public List<HashMap<String,Object>> deleteRoom(VMJExchange vmjExchange){
		return record.deleteRoom(vmjExchange);
	}

	private Room getRoomByHotelId(int hotelId) {
		return record.getRoomByHotelId(hotelId);
	}
}
