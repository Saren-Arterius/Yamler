package update;

import java.io.File;
import java.io.FileWriter;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import base.BaseTest;
import base.Util;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class UpdateConfig extends BaseTest {
    @Override
    @BeforeSuite
    public void setup() throws Exception {
        filename = "updateConfig.yml";

        file = new File(filename);
        if (file.exists()) {
            file.delete();
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("IsEnabled: true\n");
        } catch (final Exception e) {
            throw e;
        }

        config = new UpdateConfigConfig();
    }

    @Test(priority = 1)
    public void init() throws Exception {
        config.init(file);
    }

    @Test(priority = 2)
    public void save() throws Exception {
        final String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "IsEnabled: true\n" + "Enabled: false\n");
    }
}
