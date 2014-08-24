package converter.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import net.cubespace.Yamler.Config.Config;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class SimpleObjectConfig extends Config {
    public java.util.Map TestMap = new HashMap<String, String>() {
        {
            put("test", "test");
        }
    };

    public Set           TestSet = new HashSet<String>() {
        {
            add("test");
        }
    };
}
