package SIPH.room.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public interface RoomResource {
    List<HashMap<String,Object>> saveRoom(VMJExchange vmjExchange);
    HashMap<String, Object> updateRoom(VMJExchange vmjExchange);
    HashMap<String, Object> getRoom(VMJExchange vmjExchange);
    List<HashMap<String,Object>> getAllRoom(VMJExchange vmjExchange);
    List<HashMap<String,Object>> deleteRoom(VMJExchange vmjExchange);
	HashMap<String, Object> createRoom(VMJExchange vmjExhange);
    List<HashMap<String,Object>> getRoomByHotelId(VMJExchange vmjExchange);
}
