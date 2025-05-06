package SIPH.room.core;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import java.util.*;

public interface Room {
	public UUID getHotelId();
	public void setHotelId(UUID hotelId);
	public int getNumber();
	public void setNumber(int number);
	public String getType();
	public void setType(String type);
	public int getPrice();
	public void setPrice(int price);
	public boolean getIsAvailable();
	public void setIsAvailable(boolean isAvailable);
	public UUID getId();
	public void setId(UUID id);
	HashMap<String, Object> toHashMap();
}
