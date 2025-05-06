package SIPH.room.core;
import java.util.*;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;
//add other required packages

public abstract class RoomResourceComponent implements RoomResource{
	
	public RoomResourceComponent() { }
 
    public abstract HashMap<String, Object> createRoom(VMJExchange vmjExchange);    
	public abstract HashMap<String, Object> updateRoom(VMJExchange vmjExchange);
    public abstract HashMap<String, Object> getRoom(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> getAllRoom(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> deleteRoom(VMJExchange vmjExchange);

	public abstract Room getRoomByHotelId(int hotelId);
}
