package SIPH.booking.core;
import vmj.routing.route.Route;
import vmj.routing.route.VMJExchange;
import java.util.*;

public interface Booking {
	public UUID getBookingId();
	public void setBookingId(UUID bookingId);
	public UUID getUserId();
	public void setUserId(UUID userId);
	public EDate getCheckInDate();
	public void setCheckInDate(EDate checkInDate);
	public EDate getCheckOutDate();
	public void setCheckOutDate(EDate checkOutDate);
	public EDate getNumberOfGuests();
	public void setNumberOfGuests(EDate numberOfGuests);
	public EDate getTotalPrice();
	public void setTotalPrice(EDate totalPrice);
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
