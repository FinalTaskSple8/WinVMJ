package SIPH.hotel.core;
import java.util.*;

import vmj.hibernate.integrator.RepositoryUtil;
import vmj.routing.route.VMJExchange;
//add other required packages

public abstract class HotelServiceComponent implements HotelService{
	protected RepositoryUtil<Hotel> Repository;

    public HotelServiceComponent(){
        this.Repository = new RepositoryUtil<Hotel>(SIPH.hotel.core.HotelComponent.class);
    }	

    public abstract List<HashMap<String,Object>> saveHotel(VMJExchange vmjExchange);
    public abstract Hotel createHotel(Map<String, Object> requestBodye);
	public abstract Hotel createHotel(Map<String, Object> requestBody, Map<String, Object> response);    
	public abstract HashMap<String, Object> updateHotel(Map<String, Object> requestBody);
    public abstract HashMap<String, Object> getHotel(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> getAllHotel(Map<String, Object> requestBody);
    public abstract List<HashMap<String,Object>> transformListToHashMap(List<Hotel> List);
    public abstract List<HashMap<String,Object>> deleteHotel(Map<String, Object> requestBody);
	public abstract HashMap<String, Object> getHotelById(int id);

	public abstract void addRoomToHotel(Room rooms);
}
