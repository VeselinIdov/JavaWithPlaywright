package pages;

public class LoginPage extends BasePage {

    private static final String CONSENT = "//button[@aria-label='Consent']";
    private static final String USERNAME = "//input[@id='userName']";
    private static final String PASSWORD = "//input[@id='password']";
    private static final String LOGIN_BUTTON = "//button[@id='login']";

    public void enterUserData(String username, String password) {
        pageUtils.fillText(USERNAME, username);
        pageUtils.fillText(PASSWORD, password);
    }

    public void clickConsent() {
        page.locator(CONSENT).click();
    }

    public void clickLoginButton() {
        pageUtils.clickElement(LOGIN_BUTTON);
    }
}