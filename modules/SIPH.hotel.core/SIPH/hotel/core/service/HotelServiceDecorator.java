package SIPH.hotel.core;
import java.util.*;
import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;
import vmj.routing.route.VMJExchange;

public abstract class HotelServiceDecorator extends HotelServiceComponent{
	protected HotelServiceComponent record;

    public HotelServiceDecorator(HotelServiceComponent record) {
        this.record = record;
    }

	public Hotel createHotel(Map<String, Object> requestBody){
		return record.createHotel(requestBody);
	}

	public HashMap<String, Object> getHotel(Map<String, Object> requestBody){
		return record.getHotel(requestBody);
	}

	public List<Hotel> getAllHotel(){
		return record.getAllHotel();
	}

    public List<HashMap<String,Object>> saveHotel(VMJExchange vmjExchange){
		return record.saveHotel(vmjExchange);
	}

    public HashMap<String, Object> updateHotel(Map<String, Object> requestBody){
		return record.updateHotel(requestBody);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<Hotel> List){
		return record.transformListToHashMap(List);
	}

    public List<Hotel> deleteHotel(UUID id){
		return record.deleteHotel(id);
	}

	public Hotel getHotelById(UUID id){
        return record.getHotelById(id);
    }

	public void addRoomToHotel(Room rooms) {
		record.addRoomToHotel(rooms);
		return;
	}
}
