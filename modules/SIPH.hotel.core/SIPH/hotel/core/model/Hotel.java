package SIPH.hotel.core;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import java.util.*;

public interface Hotel {
	public UUID getId();
	public void setId(UUID id);
	public String getName();
	public void setName(String name);
	public String getLocation();
	public void setLocation(String location);
	public int getPrice();
	public void setPrice(int price);
	public RoomImpl getRoomimpl();
	public void setRoomimpl(RoomImpl roomimpl);
	HashMap<String, Object> toHashMap();
}
