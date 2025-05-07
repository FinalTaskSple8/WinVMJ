package SIPH.booking.core;
import vmj.routing.route.Route;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import java.util.*;
import java.time.LocalDate;
import java.math.BigDecimal;
import SIPH.room.core.RoomImpl;
import SIPH.room.core.Room;

public interface Booking {
	public UUID getUserId();
	public void setUserId(UUID userId);
	public LocalDate getCheckInDate();
	public void setCheckInDate(LocalDate checkInDate);
	public LocalDate getCheckOutDate();
	public void setCheckOutDate(LocalDate checkOutDate);
	public int getNumberOfGuests();
	public void setNumberOfGuests(int numberOfGuests);
	public BigDecimal getTotalPrice();
	public void setTotalPrice(BigDecimal totalPrice);
	public String getStatus();
	public void setStatus(String status);
	public UUID getRoomId();
	public void setRoomId(UUID roomId);
	public UUID getPaymentId();
	public void setPaymentId(UUID paymentId);
	public RoomImpl getRoomimpl();
	public void setRoomimpl(RoomImpl roomimpl);
	public UUID getId();
	public void setId(UUID id);
	HashMap<String, Object> toHashMap();
}
