package utils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod
    public void setUp() {
        LogUtils.logInfo("Starting test execution...");
    }

    @AfterMethod
    public void tearDown() {
        PlaywrightDriver.getInstance().close();
        LogUtils.logInfo("Test execution completed.");
    }
}