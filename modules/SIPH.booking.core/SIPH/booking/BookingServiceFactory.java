package SIPH.booking;

import SIPH.booking.core.BookingService;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public class BookingServiceFactory {
    private static final Logger LOGGER = Logger.getLogger(BookingFactory.class.getName());

    public BookingServiceFactory() {
    }

    public static BookingService createBookingService(String fullyQualifiedName, Object ... base) {
        BookingService record = null;
        try {
            Class<?> clz = Class.forName(fullyQualifiedName);
            Constructor<?> constructor = clz.getDeclaredConstructors()[0];
            record = (BookingService) constructor.newInstance(base);
        } 
        catch (IllegalArgumentException e) {
            LOGGER.severe("Failed to create instance of BookingService.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Failed to run: Check your constructor argument");
            System.exit(20);
        }
        catch (ClassCastException e) {
            LOGGER.severe("Failed to create instance of BookingService.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Failed to cast the object");
            System.exit(30);
        }
        catch (ClassNotFoundException e) {
            LOGGER.severe("Failed to create instance of BookingService.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Class not found: " + e.getMessage());
            System.exit(40);
        }
        catch (Exception e) {
            LOGGER.severe("Failed to create instance of BookingService.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Unexpected error: " + e.getMessage());
            System.exit(50);
        }
        return record;
    }
}