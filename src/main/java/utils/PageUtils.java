package utils;

import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PageUtils {
    private static final Logger LOGGER = LogManager.getLogger(PageUtils.class);

    private Page page;

    public PageUtils(Page page) {
        this.page = page;
    }

    public void clickElement(String locator) {
        page.locator(locator).click();
        LOGGER.info("Clicked on element with locator: " + locator);
    }

    public void fillText(String locator, String text) {
        page.locator(locator).fill(text);
        LOGGER.info("Filled text '" + text + "' into element with locator: " + locator);
    }

    public String getElementText(String locator) {
        String text = page.locator(locator).innerText();
        LOGGER.info("Retrieved text '" + text + "' from element with locator: " + locator);
        return text;
    }

    public void waitForSelector(String locator) {
        page.waitForSelector(locator);
        LOGGER.info("Waited for element with locator: " + locator + " to be visible");
    }

    public void scrollToElementView(String locator) {
        page.evaluate("document.querySelector('" + locator + "').scrollIntoView();");
        LOGGER.info("Scrolled to element with locator: " + locator);
    }
}
