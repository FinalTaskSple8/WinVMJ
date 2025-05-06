package SIPH.room.core;

import java.lang.Math;
import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name="room_impl")
@Table(name="room_impl")
public class RoomImpl extends RoomComponent {

	public RoomImpl(UUID hotelId, int number, String type, int price, boolean isAvailable, UUID id) {
		this.hotelId = hotelId;
		this.number = number;
		this.type = type;
		this.price = price;
		this.isAvailable = isAvailable;
		this.id = id;
	}

	public RoomImpl(UUID hotelId, int number, String type, int price, boolean isAvailable) {
		this.id =  UUID.randomUUID();;
		this.hotelId = hotelId;
		this.number = number;
		this.type = type;
		this.price = price;
		this.isAvailable = isAvailable;
	}

	public RoomImpl() { }

	public UUID getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(UUID hotelId) {
		this.hotelId = hotelId;
	}
	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public boolean getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Room getRoomByHotelId(int hotelId) {
		// TODO: implement this method
		return null;
	}
	
	public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> roomMap = new HashMap<String,Object>();
		roomMap.put("hotelId",getHotelId());
		roomMap.put("number",getNumber());
		roomMap.put("type",getType());
		roomMap.put("price",getPrice());
		roomMap.put("isAvailable",getIsAvailable());
		roomMap.put("id",getId());

        return roomMap;
    }

}
