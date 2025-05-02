package SIPH.room.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.OneToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.CascadeType;
//add other required packages

@MappedSuperclass
public abstract class RoomDecorator extends RoomComponent{
    @OneToOne(cascade=CascadeType.ALL)
	protected RoomComponent record;

	public RoomDecorator () {
		super();
		this.record = record;
		this.hotelId =  hotelId.randomUUID();
		
	public RoomDecorator (RoomComponent record) {
		this.hotelId =  hotelId.randomUUID();
		this.record = record;
	}

	public RoomDecorator (int hotelId, RoomComponent record) {
		this.hotelId =  hotelId;
		this.record = record;
	}
	
	public RoomDecorator (RoomComponent record, String objectName) {
		this.hotelId =  hotelId.randomUUID();
		this.record = record;	
		this.objectName=objectName;
	}

	public RoomDecorator() { }

	public int getHotelId() {
		return record.getHotelId();
	}
	public void setHotelId(int hotelId) {
		record.setHotelId(hotelId);
	}
	public int getNumber() {
		return record.getNumber();
	}
	public void setNumber(int number) {
		record.setNumber(number);
	}
	public String getType() {
		return record.getType();
	}
	public void setType(String type) {
		record.setType(type);
	}
	public int getPrice() {
		return record.getPrice();
	}
	public void setPrice(int price) {
		record.setPrice(price);
	}
	public boolean getIsAvailable() {
		return record.getIsAvailable();
	}
	public void setIsAvailable(boolean isAvailable) {
		record.setIsAvailable(isAvailable);
	}

	private Room getRoomByHotelId(int hotelId) {
		return record.getRoomByHotelId(hotelId);
	}

	public HashMap<String, Object> toHashMap() {
        return this.record.toHashMap();
    }

}
