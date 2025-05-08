package SIPH.hotel.core;
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
import SIPH.hotel.HotelFactory;
import vmj.auth.annotations.Restricted;
import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;
//add other required packages

public class HotelServiceImpl extends HotelServiceComponent{

	@Override
	public List<HashMap<String,Object>> saveHotel(Map<String, Object> requestBody){
	    Hotel hotel = createHotel(requestBody);
	    hotelRepository.saveObject(hotel);
	    return getAllHotel(requestBody);
	}
	
	@Override
	public List<HashMap<String,Object>> saveHotel(VMJExchange vmjExchange){
	    if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
	        return null;
	    }

	    // Ambil data dari VMJExchange dan ubah ke Map
	    Map<String, Object> requestBody = vmjExchange.getPayload();

	    // Pakai createHotel yang sudah ada
	    Hotel hotel = createHotel(requestBody);

	    hotelRepository.saveObject(hotel);

	    return getAllHotel(requestBody);
	}


    public Hotel createHotel(Map<String, Object> requestBody){
		String name = (String) requestBody.get("name");
		String location = (String) requestBody.get("location");
		String priceStr = (String) requestBody.get("price");
		int price = Integer.parseInt(priceStr);
		
		//to do: fix association attributes
		RoomImpl dummyRoom = new RoomImpl();
		dummyRoom.setId(UUID.randomUUID()); // INI HARUS ADA
		roomRepository.saveObject(dummyRoom);
		
		Hotel hotel = HotelFactory.createHotel(
			"SIPH.hotel.core.HotelImpl",
		 name
		, location
		, price
		, dummyRoom
		);
		hotelRepository.saveObject(hotel);
		return hotel;
	}

    public Hotel createHotel(Map<String, Object> requestBody, int id){
		String name = (String) requestBody.get("name");
		String location = (String) requestBody.get("location");
		String priceStr = (String) requestBody.get("price");
		int price = Integer.parseInt(priceStr);
		
		//to do: fix association attributes
		
		Hotel hotel = HotelFactory.createHotel("SIPH.hotel.core.HotelImpl", name, location, price);
		return hotel;
	}

    public HashMap<String, Object> updateHotel(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("id");
		int id = Integer.parseInt(idStr);
		Hotel hotel = hotelRepository.getObject(id);
		
		hotel.setName((String) requestBody.get("name"));
		hotel.setLocation((String) requestBody.get("location"));
		String priceStr = (String) requestBody.get("price");
		hotel.setPrice(Integer.parseInt(priceStr));
		
		hotelRepository.updateObject(hotel);
		
		//to do: fix association attributes
		
		return hotel.toHashMap();
		
	}

    public HashMap<String, Object> getHotel(Map<String, Object> requestBody){
    	String idStr = (String) requestBody.get("id");
        UUID targetId = UUID.fromString(idStr);

        List<HashMap<String, Object>> hotelList = getAllHotel(requestBody);
        for (HashMap<String, Object> hotel : hotelList){
            String hotelIdStr = (String) hotel.get("id");
            UUID hotelId = UUID.fromString(hotelIdStr);
            if (hotelId.equals(targetId)) {
                return hotel;
            }
        }
        return null;
	}

	public HashMap<String, Object> getHotelById(int id){
		return null;
	}

    public List<HashMap<String,Object>> getAllHotel(Map<String, Object> requestBody){
		String table = (String) requestBody.get("table_name");
		List<Hotel> List = hotelRepository.getAllObject(table);
		return transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<Hotel> List){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
	}

    public List<HashMap<String,Object>> deleteHotel(Map<String, Object> requestBody){
		String idStr = ((String) requestBody.get("id"));
		int id = Integer.parseInt(idStr);
		hotelRepository.deleteObject(id);
		return getAllHotel(requestBody);
	}

	public void addRoomToHotel(Room rooms) {
		// TODO: implement this method
	}
}
