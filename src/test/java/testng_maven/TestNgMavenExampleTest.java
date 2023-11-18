package testng_maven;

import automation_test.data.DataStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utilities.DateUtils;
import utilities.ReadConfigFiles;

public class TestNgMavenExampleTest {
    private static final Logger LOGGER = LogManager.getLogger(TestNgMavenExampleTest.class);

    @Test(dataProvider = "MultipleColumnValues", dataProviderClass = DataStore.class)
    public void run(String name, String state, int zipCode) {
        System.out.println("Name is: " + name);
        System.out.println("State is: " + state);
        System.out.println("Zip Code is: " + zipCode);
    }
}
