package net.cubespace.Yamler;

import java.io.IOException;

import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class YamlerPlugin extends Plugin {
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
