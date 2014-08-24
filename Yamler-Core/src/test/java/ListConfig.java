import java.util.ArrayList;

import net.cubespace.Yamler.Config.Config;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ListConfig extends Config {
    public ArrayList<ListSubConfig> TestList = new ArrayList<ListSubConfig>() {
        {
            add(new ListSubConfig());
        }
    };
}
