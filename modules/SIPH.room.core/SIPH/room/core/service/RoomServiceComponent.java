package SIPH.room.core;
import java.util.*;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;
//add other required packages

public abstract class RoomServiceComponent implements RoomService{
	protected RepositoryUtil<Room> Repository;

    public RoomServiceComponent(){
        this.Repository = new RepositoryUtil<Room>(SIPH.room.core.RoomComponent.class);
    }	

    public abstract List<HashMap<String,Object>> saveRoom(VMJExchange vmjExchange);
    public abstract Room createRoom(Map<String, Object> requestBodye);
	public abstract Room createRoom(Map<String, Object> requestBody, Map<String, Object> response);    
	public abstract HashMap<String, Object> updateRoom(Map<String, Object> requestBody);
    public abstract HashMap<String, Object> getRoom(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> getAllRoom(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> transformListToHashMap(List<Room> List);
    public abstract List<HashMap<String,Object>> deleteRoom(Map<String, Object> requestBody);
	public abstract HashMap<String, Object> getRoomById(int id);

	private abstract Room getRoomByHotelId(int hotelId);
}
