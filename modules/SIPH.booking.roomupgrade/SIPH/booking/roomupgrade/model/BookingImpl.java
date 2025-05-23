// BookingImpl.java
package SIPH.booking.roomupgrade;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

import SIPH.booking.core.BookingDecorator;
import SIPH.booking.core.BookingComponent;
import SIPH.room.core.RoomImpl;
import java.util.HashMap;

@Entity(name="booking_roomupgrade")
@Table(name="booking_roomupgrade")
public class BookingImpl extends BookingDecorator {

    protected String upgradedRoomType;
    protected BigDecimal upgradeCost;

    public BookingImpl(BookingComponent record, String upgradedRoomType, BigDecimal upgradeCost) {
        super(record);
        this.upgradedRoomType = upgradedRoomType;
        this.upgradeCost = upgradeCost;
        this.objectName = BookingImpl.class.getName();
    }

    public String getUpgradedRoomType() {
        return this.upgradedRoomType;
    }

    public void setUpgradedRoomType(String upgradedRoomType) {
        this.upgradedRoomType = upgradedRoomType;
    }

    public BigDecimal getUpgradeCost() {
        return this.upgradeCost;
    }

    public void setUpgradeCost(BigDecimal upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    @Override
    public void setRoomimpl(RoomImpl roomimpl) {
        record.setRoomimpl(roomimpl);
    }

    @Override
    public RoomImpl getRoomimpl() {
        return record.getRoomimpl();
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        if (record == null) return upgradeCost != null ? upgradeCost : BigDecimal.ZERO;
        BigDecimal base = record.calculateTotalPrice();
        return base.add(upgradeCost != null ? upgradeCost : BigDecimal.ZERO);
    }
    
    @Override
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = record.toHashMap();
        map.put("upgradedRoomType", this.upgradedRoomType);
        map.put("upgradeCost", this.upgradeCost);
        return map;
    }
}
