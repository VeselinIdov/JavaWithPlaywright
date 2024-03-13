package utils;

import com.microsoft.playwright.Page;

public class PageUtils {
    private Page page;

    public PageUtils(Page page) {
        this.page = page;
    }

    public void clickElement(String locator) {
        page.locator(locator).click();
        LogUtils.logInfo("Clicked on element with locator: " + locator);
    }

    public void fillText(String locator, String text) {
        page.locator(locator).fill(text);
        LogUtils.logInfo("Filled text '" + text + "' into element with locator: " + locator);
    }

    public String getElementText(String locator) {
        String text = page.locator(locator).innerText();
        LogUtils.logInfo("Retrieved text '" + text + "' from element with locator: " + locator);
        return text;
    }

    public void waitForSelector(String locator) {
        page.waitForSelector(locator);
        LogUtils.logInfo("Waited for element with locator: " + locator + " to be visible");
    }

    public void scrollToElementView(String locator) {
        page.evaluate("document.querySelector('" + locator + "').scrollIntoView();");
        LogUtils.logInfo("Scrolled to element with locator: " + locator);
    }
}