package SIPH.profile;

import SIPH.profile.core.Profile;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public class ProfileFactory{
    private static final Logger LOGGER = Logger.getLogger(ProfileFactory.class.getName());

    public ProfileFactory()
    {

    }

    public static Profile createProfile(String fullyQualifiedName, Object ... base) {
        Profile record = null;
        try {
            Class<?> clz = Class.forName(fullyQualifiedName);
            Constructor<?>[] constructors = clz.getDeclaredConstructors();

            for (Constructor<?> constructor : constructors) {
                Class<?>[] paramTypes = constructor.getParameterTypes();

                if (paramTypes.length != base.length) continue;

                boolean compatible = true;
                for (int i = 0; i < paramTypes.length; i++) {
                    if (base[i] != null && !paramTypes[i].isAssignableFrom(base[i].getClass())) {
                        compatible = false;
                        break;
                    }
                }

                if (compatible) {
                    constructor.setAccessible(true);
                    record = (Profile) constructor.newInstance(base);
                    return record;
                }
            }

            throw new IllegalArgumentException("No matching constructor found for Profile");

        } 
        catch (IllegalArgumentException e)
        {
            LOGGER.severe("Failed to create instance of Profile.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Failed to run: Check your constructor argument");
            System.exit(20);
        }
        catch (ClassCastException e)
        {   LOGGER.severe("Failed to create instance of Profile.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Failed to cast the object");
            System.exit(30);
        }
        catch (ClassNotFoundException e)
        {
            LOGGER.severe("Failed to create instance of Profile.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            LOGGER.severe("Decorator can't be applied to the object");
            System.exit(40);
        }
        catch (Exception e)
        {
            LOGGER.severe("Failed to create instance of Profile.");
            LOGGER.severe("Given FQN: " + fullyQualifiedName);
            System.exit(50);
        }
        return record;
    }

}
