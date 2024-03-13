package utils;

import com.microsoft.playwright.*;

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

    public synchronized void setupBrowser(String browserType) {
        if (browser == null) {
            switch (browserType.toLowerCase()) {
                case "chromium":
                    browser = THREAD_LOCAL_PLAYWRIGHT.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                case "firefox":
                    browser = THREAD_LOCAL_PLAYWRIGHT.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                default:
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
            browser.close();
        }
        THREAD_LOCAL_PLAYWRIGHT.get().close();
    }
}