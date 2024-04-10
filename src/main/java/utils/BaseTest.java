package utils;

import api.BaseRequest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod
    public void setUp() {
        LogUtils.logInfo("Starting test execution...");
    }

    @AfterMethod
    public void tearDown() {
        PlaywrightDriver.close();
        BaseRequest.disposeAPIRequestContext();
        LogUtils.logInfo("Test execution completed.");
    }
}