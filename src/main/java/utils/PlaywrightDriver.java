package utils;

import com.microsoft.playwright.*;
import core.ConfigReader;

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
                    browser = THREAD_LOCAL_PLAYWRIGHT.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                case "firefox":
                    LogUtils.logInfo("Launching Firefox browser...");
                    browser = THREAD_LOCAL_PLAYWRIGHT.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                default:
                    LogUtils.logError("Invalid browser type: " + browserType);
                    throw new IllegalArgumentException("Invalid browser type: " + browserType);
            }
            BrowserContext context = browser.newContext();
            page = context.newPage();
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