package utils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private static final PlaywrightDriver playwrightDriver = PlaywrightDriver.getInstance();

    @BeforeMethod
    public void setUp() {
        playwrightDriver.setupBrowser("chromium");
    }

    @AfterMethod
    public void tearDown() {
        playwrightDriver.close();
    }
}
