package SIPH.room.core;

import java.util.*;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="room_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RoomComponent implements Room{
	@Id
	protected int hotelId; 
	protected int hotelId;
	protected int number;
	protected String type;
	protected int price;
	protected boolean isAvailable;
	@ManyToOne(targetEntity=SIPH..core.Component.class)
	public  hotelimpl;
	protected String objectName = RoomComponent.class.getName();

	public RoomComponent() {

	} 

	public RoomComponent(
        int hotelId, int number, String type, int price, boolean isAvailable, HotelImpl hotelimpl
    ) {
        this.hotelId = hotelId;
        this.number = number;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
        this.hotelimpl = hotelimpl;
    }

	public int getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(int hotelId) {
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
	public abstract HotelImpl getHotelimpl();
	public abstract void setHotelimpl(HotelImpl hotelimpl);
	
 
	private abstract Room getRoomByHotelId(int hotelId);

	@Override
    public String toString() {
        return "{" +
            " hotelId='" + getHotelId() + "'" +
            " number='" + getNumber() + "'" +
            " type='" + getType() + "'" +
            " price='" + getPrice() + "'" +
            " isAvailable='" + getIsAvailable() + "'" +
            " hotelimpl='" + getHotelimpl() + "'" +
            "}";
    }
	
}
