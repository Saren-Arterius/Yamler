import java.io.File;

import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Config;
import net.cubespace.Yamler.Config.InvalidConfigurationException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Messages extends Config {
    public static Messages instance = null;

    private Messages() {

        try {
            CONFIG_HEADER = new String[] {"Messages for MapNodes"};
            CONFIG_FILE = new File("temp", "messages.yml");
            init();
        } catch (final InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    /** Get this instance. */
    public static String get(String message) {
        if (Messages.instance == null) {
            Messages.instance = new Messages();
        }

        // Check is the message is there if not use the general message.
        String newMessage;
        if (Messages.instance.messages.get(message) == null) {
            newMessage = Messages.instance.messages.get("general");
        } else {
            newMessage = Messages.instance.messages.get(message);
        }

        return newMessage;
    }

    @Comment("All messages that can be changed.")
    private final java.util.HashMap<String, String> messages = new java.util.HashMap<String, String>() {
        {
            put("general", "Did I do that!");

            // Messages
            // for
            // clocks
            put("clock.start", "&6Game starting in &a%s&6.");
            put("clock.restart",
                    "&6Server restarting in &a%s&6.");
        }
    };
}
