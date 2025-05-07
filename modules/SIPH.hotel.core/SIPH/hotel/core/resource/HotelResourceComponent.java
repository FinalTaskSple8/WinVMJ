package SIPH.hotel.core;
import java.util.*;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;
//add other required packages
import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;
public abstract class HotelResourceComponent implements HotelResource{
	
	public HotelResourceComponent() { }
 
    public abstract HashMap<String, Object> createHotel(VMJExchange vmjExchange);    
	public abstract HashMap<String, Object> updateHotel(VMJExchange vmjExchange);
    public abstract HashMap<String, Object> getHotel(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> getAllHotel(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> deleteHotel(VMJExchange vmjExchange);

	public abstract void addRoomToHotel(Room rooms);
}
