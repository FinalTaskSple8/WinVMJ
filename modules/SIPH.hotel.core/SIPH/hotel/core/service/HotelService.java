package SIPH.hotel.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public interface HotelService {
	Hotel createHotel(Map<String, Object> requestBody);
	HashMap<String, Object> getHotel(Map<String, Object> requestBody);
    List<HashMap<String,Object>> saveHotel(Map<String, Object> requestBody);
    HashMap<String, Object> updateHotel(Map<String, Object> requestBody);
    HashMap<String, Object> getHotelById(int id);
    List<HashMap<String,Object>> getAllHotel(Map<String, Object> requestBody);
    List<HashMap<String,Object>> deleteHotel(Map<String, Object> requestBody);
}
