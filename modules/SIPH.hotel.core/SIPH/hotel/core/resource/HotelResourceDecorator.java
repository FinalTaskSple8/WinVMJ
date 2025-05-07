package SIPH.hotel.core;
import java.util.*;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;
public abstract class HotelResourceDecorator extends HotelResourceComponent{
	protected HotelResourceComponent record;

    public HotelResourceDecorator(HotelResourceComponent record) {
        this.record = record;
    }

    public HashMap<String, Object> createHotel(VMJExchange vmjExchange){
		return record.createHotel(vmjExchange);
	}

    public HashMap<String, Object> updateHotel(VMJExchange vmjExchange){
		return record.updateHotel(vmjExchange);
	}

    public HashMap<String, Object> getHotel(VMJExchange vmjExchange){
		return record.getHotel(vmjExchange);
	}

    public List<HashMap<String,Object>> getAllHotel(VMJExchange vmjExchange){
		return record.getAllHotel(vmjExchange);
	}

    public List<HashMap<String,Object>> deleteHotel(VMJExchange vmjExchange){
		return record.deleteHotel(vmjExchange);
	}

	public void addRoomToHotel(Room rooms) {
		record.addRoomToHotel(rooms);
		return;
	}
}
