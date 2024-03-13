package utils;

import com.microsoft.playwright.*;
import core.ConfigReader;
import utils.LogUtils;

public class PlaywrightDriver {

    private static final ThreadLocal<Playwright> THREAD_LOCAL_PLAYWRIGHT = ThreadLocal.withInitial(Playwright::create);
    private Browser browser;
    private Page page;

    private PlaywrightDriver() {
    }

    public static PlaywrightDriver getInstance() {
        return new PlaywrightDriver();
    }

    public static Playwright getPlaywright() {
        return THREAD_LOCAL_PLAYWRIGHT.get();
    }

    public synchronized void setupBrowser() {
        if (browser == null) {
            String browserType = ConfigReader.getValue("browser");
            if (browserType == null) {
                throw new IllegalArgumentException("Browser type is not specified in the configuration.");
            }
            switch (browserType.toLowerCase()) {
                case "chromium":
                    LogUtils.logInfo("Launching Chromium browser...");
                    browser = THREAD_LOCAL_PLAYWRIGHT.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
                    break;
                case "firefox":
                    LogUtils.logInfo("Launching Firefox browser...");
                    browser = THREAD_LOCAL_PLAYWRIGHT.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                default:
                    LogUtils.logError("Invalid browser type: " + browserType);
                    throw new IllegalArgumentException("Invalid browser type: " + browserType);
            }
            // Maximize the browser window
            page = browser.newPage(new Browser.NewPageOptions().setViewportSize(1920, 1080));
            page.evaluate("window.moveTo(0, 0); window.resizeTo(screen.width, screen.height);");
        }
    }

    public synchronized Page getPage() {
        return page;
    }

    public synchronized void close() {
        if (browser != null) {
            LogUtils.logInfo("Closing the browser...");
            browser.close();
        }
        THREAD_LOCAL_PLAYWRIGHT.get().close();
        LogUtils.logInfo("Playwright resources closed.");
    }
}
