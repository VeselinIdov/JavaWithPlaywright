import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class TestsExample extends BaseTest {

    @Test
    public void test() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserData("test123", "12345");
        loginPage.clickLoginButton();
    }

    @Test
    public void test1() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserData("test123", "12345");
        loginPage.clickLoginButton();
    }

    @Test
    public void test2() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserData("test123", "12345");
        loginPage.clickLoginButton();
    }
}