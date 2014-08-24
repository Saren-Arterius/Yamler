import java.util.HashSet;

import net.cubespace.Yamler.Config.Config;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class SetConfig extends Config {
    public java.util.Set<String> StringSet = new HashSet<String>() {
        {
            add("Test");
        }
    };
}
