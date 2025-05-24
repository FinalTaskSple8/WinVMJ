package SIPH.room;

import SIPH.room.core.Room;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;
import java.util.Arrays;

public class RoomFactory {
    private static final Logger LOGGER = Logger.getLogger(RoomFactory.class.getName());

    public RoomFactory() {}

    public static Room createRoom(String fullyQualifiedName, Object... base) {
        Room record = null;
        try {
            Class<?> clz = Class.forName(fullyQualifiedName);
            Constructor<?>[] constructors = clz.getDeclaredConstructors();

            for (Constructor<?> constructor : constructors) {
                try {
                    record = (Room) constructor.newInstance(base);
                    return record;
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }

            throw new IllegalArgumentException("No suitable constructor found for: " + fullyQualifiedName);

        } catch (IllegalArgumentException e) {
            LOGGER.severe("Failed to create instance of Room.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Constructor argument mismatch");
            System.exit(20);
        } catch (ClassCastException e) {
            LOGGER.severe("Failed to create instance of Room.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Failed to cast the object");
            System.exit(30);
        } catch (ClassNotFoundException e) {
            LOGGER.severe("Failed to create instance of Room.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Class not found or decorator error");
            System.exit(40);
        } catch (Exception e) {
            LOGGER.severe("Failed to create instance of Room.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            e.printStackTrace();
            System.exit(50);
        }

        return null;
    }
}
