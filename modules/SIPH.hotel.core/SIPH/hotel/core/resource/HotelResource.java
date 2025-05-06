package SIPH.hotel.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public interface HotelResource {
    List<HashMap<String,Object>> saveHotel(VMJExchange vmjExchange);
    HashMap<String, Object> updateHotel(VMJExchange vmjExchange);
    HashMap<String, Object> getHotel(VMJExchange vmjExchange);
    List<HashMap<String,Object>> getAllHotel(VMJExchange vmjExchange);
    List<HashMap<String,Object>> deleteHotel(VMJExchange vmjExchange);
	HashMap<String, Object> createHotel(VMJExchange vmjExhange);
}
