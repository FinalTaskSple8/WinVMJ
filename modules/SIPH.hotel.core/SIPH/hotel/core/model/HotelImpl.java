package SIPH.hotel.core;

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

import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;


@Entity(name="hotel_impl")
@Table(name="hotel_impl")
public class HotelImpl extends HotelComponent {

	public HotelImpl(UUID id, String name, String location, int price, RoomImpl roomimpl) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.price = price;
		this.roomimpl = roomimpl;
	}

	public HotelImpl(String name, String location, int price, RoomImpl roomimpl) {
		this.id =  id.randomUUID();;
		this.name = name;
		this.location = location;
		this.price = price;
		this.roomimpl = roomimpl;
	}

	public HotelImpl() { }

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void addRoomToHotel(Room rooms) {
		// TODO: implement this method
	}
	
	public RoomImpl getRoomimpl() {
		return this.roomimpl;
	}

	public void setRoomimpl(RoomImpl roomimpl) {
		this.roomimpl = roomimpl;
	}
	
	public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hotelMap = new HashMap<String,Object>();
		hotelMap.put("id",getId());
		hotelMap.put("name",getName());
		hotelMap.put("location",getLocation());
		hotelMap.put("price",getPrice());
		hotelMap.put("roomimpl",getRoomimpl());

        return hotelMap;
    }

}
