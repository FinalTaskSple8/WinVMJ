package SIPH.room.core;
import java.util.*;
import com.google.gson.Gson;
import java.util.*;
import java.util.logging.Logger;
import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.room.RoomFactory;
import vmj.auth.annotations.Restricted;
//add other required packages

public class RoomServiceImpl extends RoomServiceComponent{

    public List<HashMap<String,Object>> saveRoom(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Room room = createRoom(vmjExchange.getPayload());
		roomRepository.saveObject(room);
		return transformListToHashMap(getAllRoom());
	}

    public Room createRoom(Map<String, Object> requestBody){
		String numberStr = (String) requestBody.get("number");
		int number = Integer.parseInt(numberStr);
		String type = (String) requestBody.get("type");
		String priceStr = (String) requestBody.get("price");
		int price = Integer.parseInt(priceStr);
		boolean isAvailable = (boolean) requestBody.get("isAvailable");
		String hotelIdStr = (String) requestBody.get("hotelId");
		UUID hotelId = UUID.fromString(hotelIdStr);
		
		//to do: fix association attributes
		Room room = RoomFactory.createRoom(
			"SIPH.room.core.RoomImpl",
		hotelId
		, number
		, type
		, price
		, isAvailable
		);
		System.out.println("DEBUG requestBody: " + requestBody);
		roomRepository.saveObject(room);
		return room;
	}

    public Room createRoom(Map<String, Object> requestBody, int id){
		String numberStr = (String) requestBody.get("number");
		int number = Integer.parseInt(numberStr);
		String type = (String) requestBody.get("type");
		String priceStr = (String) requestBody.get("price");
		int price = Integer.parseInt(priceStr);
		boolean isAvailable = (boolean) requestBody.get("isAvailable");
		String hotelIdStr = (String) requestBody.get("hotelId");
		UUID hotelId = UUID.fromString(hotelIdStr);
		
		//to do: fix association attributes
		
		Room room = RoomFactory.createRoom("SIPH.room.core.RoomImpl", hotelId, number, type, price, isAvailable);
		return room;
	}

    public HashMap<String, Object> updateRoom(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("hotelIdid");
		int id = Integer.parseInt(idStr);
		Room room = roomRepository.getObject(id);
		
		String numberStr = (String) requestBody.get("number");
		room.setNumber(Integer.parseInt(numberStr));
		room.setType((String) requestBody.get("type"));
		String priceStr = (String) requestBody.get("price");
		room.setPrice(Integer.parseInt(priceStr));
		room.setIsAvailable((Boolean) requestBody.get("isAvailable"));
		
		roomRepository.updateObject(room);
		
		//to do: fix association attributes
		
		return room.toHashMap();
		
	}

    public HashMap<String, Object> getRoom(Map<String, Object> requestBody) {
        String idStr = (String) requestBody.get("id");
        UUID targetId = UUID.fromString(idStr);

        List<HashMap<String, Object>> roomList = transformListToHashMap(getAllRoom());
        for (HashMap<String, Object> room : roomList) {
            String roomIdStr = (String) room.get("id");
            UUID roomId = UUID.fromString(roomIdStr);
            if (roomId.equals(targetId)) {
                return room;
            }
        }
        return null;
    }


	public HashMap<String, Object> getRoomById(UUID id){

        return null;
	}

    public List<Room> getAllRoom(){
		return roomRepository.getAllObject("room_impl");
		
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<Room> List){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
	}

    public List<Room> deleteRoom(UUID id){
		roomRepository.deleteObject(id);
		return getAllRoom();
	}
    
    public List<HashMap<String,Object>> saveRoom(Map<String, Object> requestBody){

		return null;
	}

	public List<Room> getRoomByHotelId(UUID hotelId) {
		List<Room> rooms = getAllRoom();
		List<Room> result = new ArrayList<>();
		for (Room room : rooms) {
			if (room.getHotelId() != null && room.getHotelId().equals(hotelId)) {
				result.add(room);
			}
		}
		return result;
	}
}
