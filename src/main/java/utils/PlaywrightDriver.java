package utils;

import com.microsoft.playwright.*;

public class PlaywrightDriver {

    private static final ThreadLocal<Playwright> playwright = ThreadLocal.withInitial(Playwright::create);
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    private PlaywrightDriver() {}

    public static PlaywrightDriver getInstance() {
        return new PlaywrightDriver();
    }

    public void setupBrowser(String browserType) {
        if (browser.get() == null) {
            launchBrowser(browserType);
        }
    }

    private void launchBrowser(String browserType) {
        Playwright playwright = PlaywrightDriver.playwright.get();
        switch (browserType.toLowerCase()) {
            case "chromium":
                browser.set(playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                browser.set(playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            default:
                throw new IllegalArgumentException("Invalid browser type: " + browserType);
        }
        context.set(browser.get().newContext());
        page.set(context.get().newPage());
    }

    public Page getPage() {
        return page.get();
    }

    public void close() {
        playwright.get().close();
    }
}
