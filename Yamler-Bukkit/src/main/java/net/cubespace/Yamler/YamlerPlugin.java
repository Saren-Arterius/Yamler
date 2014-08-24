package net.cubespace.Yamler;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class YamlerPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        try {
            final Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (final IOException e) {
            e.printStackTrace();
            getLogger().info("Could not start metrics");
        }
    }
}
