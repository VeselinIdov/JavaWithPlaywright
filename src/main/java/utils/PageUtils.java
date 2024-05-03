package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class PageUtils {
    private Page page;

    public PageUtils(Page page) {
        this.page = page;
    }

    public void clickElement(String locator) {
        try {
            page.locator(locator).click();
            LogUtils.logInfo("Clicked on element with locator: " + locator);
        } catch (PlaywrightException e) {
            LogUtils.logError("Error while clicking element with locator " + locator + ": " + e.getMessage());
        }
    }

    public void fillText(String locator, String text) {
        try {
            page.locator(locator).fill(text);
            LogUtils.logInfo("Filled text '" + text + "' into element with locator: " + locator);
        } catch (PlaywrightException e) {
            LogUtils.logError("Error while filling text into element with locator " + locator + ": " + e.getMessage());
        }
    }

    public String getElementText(String locator) {
        try {
            String text = page.locator(locator).innerText();
            LogUtils.logInfo("Retrieved text '" + text + "' from element with locator: " + locator);
            return text;
        } catch (PlaywrightException e) {
            LogUtils.logError("Error while retrieving text from element with locator " + locator + ": " + e.getMessage());
            return String.valueOf(e);
        }
    }

    public void waitForSelector(String locator) {
        try {
            page.waitForSelector(locator);
            LogUtils.logInfo("Waited for element with locator: " + locator + " to be visible");
        } catch (PlaywrightException e) {
            LogUtils.logError("Error while waiting for element with locator " + locator + ": " + e.getMessage());
        }
    }

    public void scrollToElementView(String locator) {
        try {
            page.evaluate("document.querySelector('" + locator + "').scrollIntoView();");
            LogUtils.logInfo("Scrolled to element with locator: " + locator);
        } catch (PlaywrightException e) {
            LogUtils.logError("Error while scrolling to element with locator " + locator + ": " + e.getMessage());
        }
    }

    public void performDoubleClick(String selector) {
        try {
            LogUtils.logInfo("Performing double click on element: " + selector);
            page.dblclick(selector);
        } catch (PlaywrightException e) {
            LogUtils.logError("Error while performing double click on element " + selector + ": " + e.getMessage());
        }
    }

    public void acceptAlert() {
        try {
            LogUtils.logInfo("Accepting alert");
            page.waitForPopup(() -> page.click("text=OK"));
        } catch (PlaywrightException e) {
            LogUtils.logError("Error while accepting alert: " + e.getMessage());
        }
    }

    public void cancelAlert() {
        try {
            LogUtils.logInfo("Dismissing alert");
            page.waitForPopup(() -> page.click("text=Cancel"));
        } catch (PlaywrightException e) {
            LogUtils.logError("Error while dismissing alert: " + e.getMessage());
        }
    }

    public void sendKeysToAlertAndAccept(String text) {
        try {
            LogUtils.logInfo("SendKeys to alert with text and accept it");
            page.fill("[role=alert]", text);
            page.click("text=OK");
        } catch (PlaywrightException e) {
            LogUtils.logError("Error while sending keys to alert and accepting it: " + e.getMessage());
        }
    }

    public void switchToFrame(String index) {
        try {
            LogUtils.logInfo("Switching to frame with index: " + index);
            page.frame(index);
        } catch (PlaywrightException e) {
            LogUtils.logError("Error while switching to frame with index " + index + ": " + e.getMessage());
        }
    }

    public void switchToDefaultContent() {
        try {
            LogUtils.logInfo("Switching to default content");
            page.frame(null);
        } catch (PlaywrightException e) {
            LogUtils.logError("Error while switching to default content: " + e.getMessage());
        }
    }
}