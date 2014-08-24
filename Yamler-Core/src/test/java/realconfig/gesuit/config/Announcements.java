package realconfig.gesuit.config;

import java.util.HashMap;

import net.cubespace.Yamler.Config.Config;
import realconfig.gesuit.config.sub.AnnouncementEntry;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Announcements extends Config {
    public Boolean                            Enabled       = true;
    public HashMap<String, AnnouncementEntry> Announcements = new HashMap<>();
}
