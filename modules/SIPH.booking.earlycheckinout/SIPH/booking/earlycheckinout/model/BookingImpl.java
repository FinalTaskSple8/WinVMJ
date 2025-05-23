package SIPH.booking.earlycheckinout;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

import SIPH.booking.core.BookingDecorator;
import SIPH.booking.core.BookingComponent;
import SIPH.room.core.RoomImpl;
import java.util.HashMap;

@Entity(name = "booking_earlycheckinout")
@Table(name = "booking_earlycheckinout")
public class BookingImpl extends BookingDecorator {

    protected boolean earlyCheckIn;
    protected boolean lateCheckOut;
    protected BigDecimal earlyCheckInFee;
    protected BigDecimal lateCheckOutFee;

    public BookingImpl(BookingComponent record, boolean earlyCheckIn, boolean lateCheckOut, BigDecimal earlyCheckInFee, BigDecimal lateCheckOutFee) {
        super(record);
        this.earlyCheckIn = earlyCheckIn;
        this.lateCheckOut = lateCheckOut;
        this.earlyCheckInFee = earlyCheckInFee;
        this.lateCheckOutFee = lateCheckOutFee;
        this.objectName = BookingImpl.class.getName();
    }

    public boolean getEarlyCheckIn() { return this.earlyCheckIn; }
    public void setEarlyCheckIn(boolean earlyCheckIn) { this.earlyCheckIn = earlyCheckIn; }

    public boolean getLateCheckOut() { return this.lateCheckOut; }
    public void setLateCheckOut(boolean lateCheckOut) { this.lateCheckOut = lateCheckOut; }

    public BigDecimal getEarlyCheckInFee() { return this.earlyCheckInFee; }
    public void setEarlyCheckInFee(BigDecimal earlyCheckInFee) { this.earlyCheckInFee = earlyCheckInFee; }

    public BigDecimal getLateCheckOutFee() { return this.lateCheckOutFee; }
    public void setLateCheckOutFee(BigDecimal lateCheckOutFee) { this.lateCheckOutFee = lateCheckOutFee; }

    @Override
    public RoomImpl getRoomimpl() { return record.getRoomimpl(); }
    @Override
    public void setRoomimpl(RoomImpl roomimpl) { record.setRoomimpl(roomimpl); }

    @Override
    public BigDecimal calculateTotalPrice() {
        BigDecimal base = record != null ? record.calculateTotalPrice() : BigDecimal.ZERO;
        BigDecimal earlyFee = earlyCheckInFee != null ? earlyCheckInFee : BigDecimal.ZERO;
        BigDecimal lateFee = lateCheckOutFee != null ? lateCheckOutFee : BigDecimal.ZERO;
        return base.add(earlyFee).add(lateFee);
    }

    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = record.toHashMap();
        map.put("earlyCheckIn", this.earlyCheckIn);
        map.put("lateCheckOut", this.lateCheckOut);
        map.put("earlyCheckInFee", this.earlyCheckInFee);
        map.put("lateCheckOutFee", this.lateCheckOutFee);
        return map;
    }
}
