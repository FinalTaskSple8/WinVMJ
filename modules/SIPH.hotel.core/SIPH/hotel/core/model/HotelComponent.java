package SIPH.hotel.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="hotel_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class HotelComponent implements Hotel{
	@Id
	protected UUID id; 
	protected String name;
	protected String location;
	protected int price;
	@ManyToOne(targetEntity=SIPH..core.Component.class)
	public  roomimpl;
	protected String objectName = HotelComponent.class.getName();

	public HotelComponent() {

	} 

	public HotelComponent(
        UUID id, String name, String location, int price, RoomImpl roomimpl
    ) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.roomimpl = roomimpl;
    }

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
	public abstract RoomImpl getRoomimpl();
	public abstract void setRoomimpl(RoomImpl roomimpl);
	
 
	public abstract void addRoomToHotel(Room rooms);

	@Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            " name='" + getName() + "'" +
            " location='" + getLocation() + "'" +
            " price='" + getPrice() + "'" +
            " roomimpl='" + getRoomimpl() + "'" +
            "}";
    }
	
}
