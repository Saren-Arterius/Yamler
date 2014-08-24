package performance;

import net.cubespace.Yamler.Config.InvalidConfigurationException;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import performance.config.PerformanceConfig;
import base.BaseTest;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class PerformanceTest extends BaseTest {
    @Override
    @BeforeSuite
    public void setup() throws Exception {
        config = new PerformanceConfig();
        filename = "performanceConfig.yml";

        before();
    }

    @Test(priority = 1)
    public void loadPerformance() throws InvalidConfigurationException {
        final long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            config.init(file);
        }

        final long end = System.currentTimeMillis() - start;

        Assert.assertTrue(end < 20000, "" + end);
    }

    @Test(priority = 2)
    public void savePerformance() throws InvalidConfigurationException {
        final long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            config.save(file);
        }

        final long end = System.currentTimeMillis() - start;

        Assert.assertTrue(end < 20000, "" + end);
    }
}
