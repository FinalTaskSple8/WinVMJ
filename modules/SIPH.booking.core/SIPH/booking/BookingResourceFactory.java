package SIPH.booking;

import SIPH.booking.core.BookingResource;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;
import java.util.Arrays;

public class BookingResourceFactory {
    private static final Logger LOGGER = Logger.getLogger(BookingFactory.class.getName());

    public BookingResourceFactory() {}

    public static BookingResource createBookingResource(String fullyQualifiedName, Object ... base) {
        BookingResource record = null;
        try {
            Class<?> clz = Class.forName(fullyQualifiedName);
            Constructor<?>[] constructorList = clz.getDeclaredConstructors();
            Constructor<?> constructor = null;

            for (int i = 0; i < constructorList.length; i++) {
                try {
                    constructor = constructorList[i];
                    System.out.println("Trying constructor: " + constructor.toString());
                    System.out.println("With arguments: " + Arrays.toString(base));
                    record = (BookingResource) constructor.newInstance(base);
                    break; // success
                } catch (IllegalArgumentException e) {
                    if (i < constructorList.length - 1) {
                        System.out.println("Constructor doesn't match. Trying next...");
                        continue;
                    } else {
                        throw e;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Failed to create instance of Booking.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Failed to run: Check your constructor argument");
            System.exit(20);
        } catch (ClassCastException e) {
            LOGGER.severe("Failed to create instance of Booking.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Failed to cast the object");
            System.exit(30);
        } catch (ClassNotFoundException e) {
            LOGGER.severe("Failed to create instance of Booking.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Decorator can't be applied to the object");
            System.exit(40);
        } catch (Exception e) {
            LOGGER.severe("Failed to create instance of Booking.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            e.printStackTrace();
            System.exit(50);
        }

        return record;
    }
}
