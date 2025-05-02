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
import prices.auth.vmj.annotations.Restricted;
//add other required packages

public class HotelServiceImpl extends HotelServiceComponent{

    public List<HashMap<String,Object>> saveHotel(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		Hotel hotel = createHotel(vmjExchange);
		hotelRepository.saveObject(hotel);
		return getAllHotel(vmjExchange);
	}

    public Hotel createHotel(Map<String, Object> requestBody){
		String id = (String) requestBody.get("id");
		String name = (String) requestBody.get("name");
		String location = (String) requestBody.get("location");
		String priceStr = (String) requestBody.get("price");
		int price = Integer.parseInt(priceStr);
		
		//to do: fix association attributes
		Hotel Hotel = HotelFactory.createHotel(
			"SIPH.hotel.core.HotelImpl",
		id
		, name
		, location
		, price
		);
		Repository.saveObject(hotel);
		return hotel;
	}

    public Hotel createHotel(Map<String, Object> requestBody, int id){
		String name = (String) vmjExchange.getRequestBodyForm("name");
		String location = (String) vmjExchange.getRequestBodyForm("location");
		String priceStr = (String) vmjExchange.getRequestBodyForm("price");
		int price = Integer.parseInt(priceStr);
		
		//to do: fix association attributes
		
		Hotel hotel = HotelFactory.createHotel("SIPH.hotel.core.HotelImpl", name, location, price);
		return hotel;
	}

    public HashMap<String, Object> updateHotel(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("");
		int id = Integer.parseInt(idStr);
		Hotel hotel = Repository.getObject(id);
		
		hotel.setName((String) requestBody.get("name"));
		hotel.setLocation((String) requestBody.get("location"));
		String priceStr = (String) requestBody.get("price");
		hotel.setPrice(Integer.parseInt(priceStr));
		
		Repository.updateObject(hotel);
		
		//to do: fix association attributes
		
		return hotel.toHashMap();
		
	}

    public HashMap<String, Object> getHotel(Map<String, Object> requestBody){
		List<HashMap<String, Object>> hotelList = getAllHotel("hotel_impl");
		for (HashMap<String, Object> hotel : hotelList){
			int record_id = ((Double) hotel.get("record_id")).intValue();
			if (record_id == id){
				return hotel;
			}
		}
		return null;
	}

	public HashMap<String, Object> getHotelById(int id){
		String idStr = vmjExchange.getGETParam(""); 
		int id = Integer.parseInt(idStr);
		Hotel hotel = hotelRepository.getObject(id);
		return hotel.toHashMap();
	}

    public List<HashMap<String,Object>> getAllHotel(Map<String, Object> requestBody){
		String table = (String) requestBody.get("table_name");
		List<Hotel> List = Repository.getAllObject(table);
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
		Repository.deleteObject(id);
		return getAllHotel(requestBody);
	}

	private void addRoomToHotel(Room rooms) {
		// TODO: implement this method
	}
}
