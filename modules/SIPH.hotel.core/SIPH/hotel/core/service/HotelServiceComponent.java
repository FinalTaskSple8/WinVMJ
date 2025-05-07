package SIPH.hotel.core;
import java.util.*;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;
//add other required packages
import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;
public abstract class HotelServiceComponent implements HotelService{
	protected RepositoryUtil<Hotel> hotelRepository;

    public HotelServiceComponent(){
        this.hotelRepository = new RepositoryUtil<Hotel>(SIPH.hotel.core.HotelComponent.class);
    }	

    public abstract List<HashMap<String,Object>> saveHotel(VMJExchange vmjExchange);
    public abstract Hotel createHotel(Map<String, Object> requestBody);
	public abstract HashMap<String, Object> updateHotel(Map<String, Object> requestBody);
    public abstract HashMap<String, Object> getHotel(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> getAllHotel(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> transformListToHashMap(List<Hotel> List);
    public abstract List<HashMap<String,Object>> deleteHotel(Map<String, Object> requestBody);
	public abstract HashMap<String, Object> getHotelById(int id);

	public abstract void addRoomToHotel(Room rooms);
}
