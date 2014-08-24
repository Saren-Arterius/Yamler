package realconfig.servermenu.config;

import java.util.ArrayList;
import java.util.List;

import net.cubespace.Yamler.Config.Config;

public class Menus extends Config {
    private List<Servers> servers = new ArrayList<>();

    public void setTitle(String title) {
    }

    public void setServers(List<Servers> servers) {
        this.servers = servers;
    }
}
