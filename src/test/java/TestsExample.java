import api.controllers.UserController;
import api.pojo.UserRequestData;
import api.pojo.UserResponseData;
import com.microsoft.playwright.APIResponse;
import core.JSONUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class TestsExample extends BaseTest {

    @Test
    public void invalidLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserData("test123", "12345");
        loginPage.clickLoginButton();
    }

    @Test
    public void createUserApiTest() {
       APIResponse createdUserResponse = UserController.createUser("token");
       Assert.assertEquals(createdUserResponse.status(), 201);

        UserRequestData userRequestDataData = JSONUtils.deserializeResponse(createdUserResponse.text(), UserRequestData.class);
        Assert.assertEquals("testJob", userRequestDataData.getJob());
        Assert.assertEquals("testName", userRequestDataData.getName());

        APIResponse getUserByIdResponse = UserController.getUsers("null","2");
        Assert.assertEquals( getUserByIdResponse.status(), 200);
    }
}