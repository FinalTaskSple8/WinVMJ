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
		this.id =   UUID.randomUUID();
		
	}
	
	public RoomDecorator (RoomComponent record) {
		this.id =   UUID.randomUUID();
		this.record = record;
	}

	public RoomDecorator (UUID hotelId, UUID id, RoomComponent record) {
		this.hotelId =  hotelId;
		this.id = id;
		this.record = record;
	}
	
	public RoomDecorator (RoomComponent record, String objectName) {
		this.id =  UUID.randomUUID();
		this.record = record;	
		this.objectName=objectName;
	}

	public UUID getHotelId() {
		return record.getHotelId();
	}
	public void setHotelId(UUID hotelId) {
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
	public UUID getId() {
		return record.getId();
	}
	public void setId(UUID id) {
		record.setId(id);
	}

	public Room getRoomByHotelId(int hotelId) {
		return record.getRoomByHotelId(hotelId);
	}

	public HashMap<String, Object> toHashMap() {
        return this.record.toHashMap();
    }

}
