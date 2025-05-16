package SIPH.room.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

public abstract class RoomResourceDecorator extends RoomResourceComponent{
	protected RoomResourceComponent record;

    public RoomResourceDecorator(RoomResourceComponent record) {
        this.record = record;
    }

    public HashMap<String, Object> createRoom(VMJExchange vmjExchange){
		return record.createRoom(vmjExchange);
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

	public List<HashMap<String,Object>> getRoomByHotelId(VMJExchange vmjExchange) {
		return record.getRoomByHotelId(vmjExchange);
	}
}
