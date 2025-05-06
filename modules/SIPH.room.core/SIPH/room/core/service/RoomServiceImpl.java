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
		return getAllRoom(vmjExchange.getPayload());
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

        List<HashMap<String, Object>> roomList = getAllRoom(requestBody);
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

    public List<HashMap<String,Object>> getAllRoom(Map<String, Object> requestBody){
		String table = (String) requestBody.get("table_name");
		List<Room> List = roomRepository.getAllObject(table);
		return transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<Room> List){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
	}

    public List<HashMap<String,Object>> deleteRoom(Map<String, Object> requestBody){
		String idStr = ((String) requestBody.get("id"));
		int id = Integer.parseInt(idStr);
		roomRepository.deleteObject(id);
		return getAllRoom(requestBody);
	}
    
    public List<HashMap<String,Object>> saveRoom(Map<String, Object> requestBody){

		return null;
	}

	public Room getRoomByHotelId(int hotelId) {
		// TODO: implement this method
		return null;
	}
}
