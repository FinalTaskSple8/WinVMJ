package SIPH.hotel.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import vmj.routing.route.exceptions.*;
import SIPH.hotel.HotelFactory;
import vmj.auth.annotations.Restricted;
import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;

public class HotelServiceImpl extends HotelServiceComponent {

    @Override
    public List<HashMap<String,Object>> saveHotel(Map<String, Object> requestBody){
        Hotel hotel = createHotel(requestBody);
        hotelRepository.saveObject(hotel);
        return transformListToHashMap(getAllHotel()); // tolong diganti 
    }

    @Override
    public List<HashMap<String,Object>> saveHotel(VMJExchange vmjExchange){
        if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
            return null;
        }

        Map<String, Object> requestBody = vmjExchange.getPayload();
        Hotel hotel = createHotel(requestBody);
        hotelRepository.saveObject(hotel);
        return transformListToHashMap(getAllHotel()); // tolong diganti
    }

    public Hotel createHotel(Map<String, Object> requestBody){
        String name = (String) requestBody.get("name");
        String location = (String) requestBody.get("location");
        String priceStr = (String) requestBody.get("price");
        int price = Integer.parseInt(priceStr);
        Room dummyRoom = null;

        Hotel hotel = HotelFactory.createHotel(
            "SIPH.hotel.core.HotelImpl",
            name, location, price, dummyRoom
        );
        hotelRepository.saveObject(hotel);
        return hotel;
    }

    public Hotel createHotel(Map<String, Object> requestBody, int id){
        String name = (String) requestBody.get("name");
        String location = (String) requestBody.get("location");
        String priceStr = (String) requestBody.get("price");
        int price = Integer.parseInt(priceStr);

        Hotel hotel = HotelFactory.createHotel("SIPH.hotel.core.HotelImpl", name, location, price);
        return hotel;
    }

    public HashMap<String, Object> updateHotel(Map<String, Object> requestBody) {
        String idStr = (String) requestBody.get("id");
        UUID id = UUID.fromString(idStr);
        Hotel hotel = hotelRepository.getObject(id);
        if (hotel == null) throw new RuntimeException("Hotel not found");

        if (requestBody.containsKey("name")) hotel.setName((String) requestBody.get("name"));
        if (requestBody.containsKey("location")) hotel.setLocation((String) requestBody.get("location"));
        if (requestBody.containsKey("price")) {
            String priceStr = (String) requestBody.get("price");
            hotel.setPrice(Integer.parseInt(priceStr));
        }

        if (requestBody.containsKey("room")) {
            Map<String, Object> roomData = (Map<String, Object>) requestBody.get("room");
            RoomImpl room;

            if (roomData.containsKey("id")) {
                UUID roomId = UUID.fromString((String) roomData.get("id"));
                Room rawRoom = roomRepository.getObject(roomId);
                if (rawRoom == null) throw new RuntimeException("Room not found");
                if (!(rawRoom instanceof RoomImpl)) throw new RuntimeException("Room is not of type RoomImpl");

                room = (RoomImpl) rawRoom;
            } else {
                room = new RoomImpl();
                room.setId(UUID.randomUUID());
            }

            if (roomData.containsKey("number")) {
                Number numberVal = (Number) roomData.get("number");
                room.setNumber(numberVal.intValue());
            }
            if (roomData.containsKey("type")) room.setType((String) roomData.get("type"));
            if (roomData.containsKey("price")) {
                Number priceNum = (Number) roomData.get("price");
                room.setPrice(priceNum.intValue());
            }
            if (roomData.containsKey("isAvailable")) room.setIsAvailable((boolean) roomData.get("isAvailable"));

            roomRepository.saveObject(room);
            hotel.setRoomimpl(room);
        }

        hotelRepository.updateObject(hotel);
        return hotel.toHashMap();
    }

    public HashMap<String, Object> getHotel(Map<String, Object> requestBody){
        String idStr = (String) requestBody.get("id");
        UUID targetId = UUID.fromString(idStr);

        List<HashMap<String, Object>> hotelList = transformListToHashMap(getAllHotel());
        for (HashMap<String, Object> hotel : hotelList){
            String hotelIdStr = (String) hotel.get("id");
            UUID hotelId = UUID.fromString(hotelIdStr);
            if (hotelId.equals(targetId)) {
                return hotel;
            }
        }
        return null;
    }

    public List<HashMap<String,Object>> searchHotel(Map<String, Object> requestBody) {
        String location = (String) requestBody.getOrDefault("location", "");
        Integer price = requestBody.containsKey("price") ? ((Number) requestBody.get("price")).intValue() : null;
        Integer number = requestBody.containsKey("number") ? ((Number) requestBody.get("number")).intValue() : null;

        List<Hotel> allHotels = hotelRepository.getAllObject("hotel_impl");
        List<HashMap<String,Object>> results = new ArrayList<>();

        for (Hotel hotel : allHotels) {
            boolean match = true;

            if (!location.isEmpty()) {
                String hotelLocation = hotel.getLocation();
                if (hotelLocation == null || !hotelLocation.toLowerCase().contains(location.toLowerCase())) {
                    match = false;
                }
            }

            if (price != null && hotel.getPrice() != price) {
                match = false;
            }

            // Check all rooms for the hotel
            if (number != null) {
                List<Room> allRooms = roomRepository.getAllObject("room_impl");
                boolean found = false;
                for (Room room : allRooms) {
                    if (room.getHotelId() != null && room.getHotelId().equals(hotel.getId()) && room.getNumber() == number) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    match = false;
                }
            }

            if (match) results.add(hotel.toHashMap());
        }

        return results;
    }
    public Hotel getHotelById(UUID id){
        Hotel hotel = hotelRepository.getObject(id);
        return hotel;
    }

    public List<Hotel> getAllHotel(){
        List<Hotel> List = hotelRepository.getAllObject("hotel_impl");
        return List;
    }

    public List<HashMap<String,Object>> transformListToHashMap(List<Hotel> List){
        List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
    }

    public List<Hotel> deleteHotel(UUID id){
        // Hapus semua room yang terkait dengan hotel
        List<Room> rooms = roomRepository.getAllObject("room_impl");
        for (Room room : rooms) {
            if (room.getHotelId() != null && room.getHotelId().equals(id)) {
                roomRepository.deleteObject(room.getId());
            }
        }
        // Hapus hotelnya
        hotelRepository.deleteObject(id);
        return getAllHotel();
    }

    public void addRoomToHotel(Room rooms) {
        // TODO: implement this method
    }
}
