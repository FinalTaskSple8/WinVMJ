package SIPH.room.core;
import java.util.*;

import vmj.routing.route.VMJExchange;

public interface RoomService {
	Room createRoom(Map<String, Object> requestBody);
	HashMap<String, Object> getRoom(Map<String, Object> requestBody);
    List<HashMap<String,Object>> saveRoom(Map<String, Object> requestBody);
    HashMap<String, Object> updateRoom(Map<String, Object> requestBody);
    Room getRoomById(UUID id);
    List<Room> getAllRoom();
    List<Room> deleteRoom(UUID id);
    List<Room> getRoomByHotelId(UUID hotelId);
}
