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
import prices.auth.vmj.annotations.Restricted;
//add other required packages

public class RoomServiceImpl extends RoomServiceComponent{

    public List<HashMap<String,Object>> saveRoom(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Room room = createRoom(vmjExchange);
		roomRepository.saveObject(room);
		return getAllRoom(vmjExchange);
	}

    public Room createRoom(Map<String, Object> requestBody){
		String hotelIdStr = (String) requestBody.get("hotelId");
		int hotelId = Integer.parseInt(hotelIdStr);
		String numberStr = (String) requestBody.get("number");
		int number = Integer.parseInt(numberStr);
		String type = (String) requestBody.get("type");
		String priceStr = (String) requestBody.get("price");
		int price = Integer.parseInt(priceStr);
		boolean isAvailable = (boolean) requestBody.get("isAvailable");
		
		//to do: fix association attributes
		Room Room = RoomFactory.createRoom(
			"SIPH.room.core.RoomImpl",
		hotelId
		, number
		, type
		, price
		, isAvailable
		, hotelimpl
		);
		Repository.saveObject(room);
		return room;
	}

    public Room createRoom(Map<String, Object> requestBody, int id){
		String hotelIdStr = (String) vmjExchange.getRequestBodyForm("hotelId");
		int hotelId = Integer.parseInt(hotelIdStr);
		String numberStr = (String) vmjExchange.getRequestBodyForm("number");
		int number = Integer.parseInt(numberStr);
		String type = (String) vmjExchange.getRequestBodyForm("type");
		String priceStr = (String) vmjExchange.getRequestBodyForm("price");
		int price = Integer.parseInt(priceStr);
		boolean isAvailable = (boolean) vmjExchange.getRequestBodyForm("isAvailable");
		
		//to do: fix association attributes
		
		Room room = RoomFactory.createRoom("SIPH.room.core.RoomImpl", hotelId, number, type, price, isAvailable, hotelimpl);
		return room;
	}

    public HashMap<String, Object> updateRoom(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("hotelId");
		int id = Integer.parseInt(idStr);
		Room room = Repository.getObject(id);
		
		String hotelIdStr = (String) requestBody.get("hotelId");
		room.setHotelId(Integer.parseInt(hotelIdStr));
		String numberStr = (String) requestBody.get("number");
		room.setNumber(Integer.parseInt(numberStr));
		room.setType((String) requestBody.get("type"));
		String priceStr = (String) requestBody.get("price");
		room.setPrice(Integer.parseInt(priceStr));
		room.setIsAvailable((String) requestBody.get("isAvailable"));
		
		Repository.updateObject(room);
		
		//to do: fix association attributes
		
		return room.toHashMap();
		
	}

    public HashMap<String, Object> getRoom(Map<String, Object> requestBody){
		List<HashMap<String, Object>> roomList = getAllRoom("room_impl");
		for (HashMap<String, Object> room : roomList){
			int record_id = ((Double) room.get("record_id")).intValue();
			if (record_id == id){
				return room;
			}
		}
		return null;
	}

	public HashMap<String, Object> getRoomById(int id){
		String idStr = vmjExchange.getGETParam("hotelId"); 
		int id = Integer.parseInt(idStr);
		Room room = roomRepository.getObject(id);
		return room.toHashMap();
	}

    public List<HashMap<String,Object>> getAllRoom(Map<String, Object> requestBody){
		String table = (String) requestBody.get("table_name");
		List<Room> List = Repository.getAllObject(table);
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
		Repository.deleteObject(id);
		return getAllRoom(requestBody);
	}

	private Room getRoomByHotelId(int hotelId) {
		// TODO: implement this method
	}
}
