package SIPH.booking;

import SIPH.booking.core.Booking;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public class BookingFactory{
    private static final Logger LOGGER = Logger.getLogger(BookingFactory.class.getName());

    public BookingFactory() {
    }

    public static Booking createBooking(String fullyQualifiedName, Object ... base) {
        Booking record = null;
        try {
            Class<?> clz = Class.forName(fullyQualifiedName);
            Constructor<?> constructor = clz.getDeclaredConstructors()[0];
            record = (Booking) constructor.newInstance(base);
        } 
        catch (IllegalArgumentException e) {
            LOGGER.severe("Failed to create instance of Booking.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Failed to run: Check your constructor argument");
            System.exit(20);
        }
        catch (ClassCastException e) {
            LOGGER.severe("Failed to create instance of Booking.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Failed to cast the object");
            System.exit(30);
        }
        catch (ClassNotFoundException e) {
            LOGGER.severe("Failed to create instance of Booking.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Class not found: " + e.getMessage());
            System.exit(40);
        }
        catch (Exception e) {
            LOGGER.severe("Failed to create instance of Booking.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Unexpected error: " + e.getMessage());
            System.exit(50);
        }
        return record;
    }
}