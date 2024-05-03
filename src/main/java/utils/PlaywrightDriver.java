package utils;

import com.microsoft.playwright.*;
import core.ConfigReader;

public class PlaywrightDriver {

    private static final ThreadLocal<Playwright> THREAD_LOCAL_PLAYWRIGHT = ThreadLocal.withInitial(Playwright::create);
    private static final ThreadLocal<Browser> THREAD_LOCAL_BROWSER = new ThreadLocal<>();
    private Page page;

    public void setupBrowser() {
        String browserType = ConfigReader.getValue("browser");
        if (browserType == null) {
            String errorMessage = "Browser type is not specified in the configuration.";
            LogUtils.logError(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        try {
            switch (browserType.toLowerCase()) {
                case "chromium" ->
                        THREAD_LOCAL_BROWSER.set(THREAD_LOCAL_PLAYWRIGHT.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome")));
                case "firefox" ->
                        THREAD_LOCAL_BROWSER.set(THREAD_LOCAL_PLAYWRIGHT.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                default -> throw new IllegalArgumentException("Invalid browser type: " + browserType);
            }
            maximizeBrowser();
            LogUtils.logInfo("Browser launched: " + browserType);
        } catch (PlaywrightException e) {
            String errorMessage = "Error while launching browser: " + e.getMessage();
            LogUtils.logError(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private void maximizeBrowser() {
        this.page = THREAD_LOCAL_BROWSER.get().newPage(new Browser.NewPageOptions().setViewportSize(1920, 1080));
        this.page.evaluate("window.moveTo(0, 0); window.resizeTo(screen.width, screen.height);");
    }

    public static void close() {
        if (THREAD_LOCAL_BROWSER.get() != null) {
            LogUtils.logInfo("Closing the browser...");
            THREAD_LOCAL_BROWSER.get().close();
            THREAD_LOCAL_BROWSER.remove();
            LogUtils.logInfo("Browser closed.");
        }
        LogUtils.logInfo("Playwright resources closed.");
    }

    public Page getPage() {
        if (this.page == null) {
            String errorMessage = "Page is not initialized.";
            LogUtils.logError(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
        return this.page;
    }

    public static Playwright getPlaywright() {
        return THREAD_LOCAL_PLAYWRIGHT.get();
    }
}