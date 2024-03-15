package pages;

import com.microsoft.playwright.Page;
import core.ConfigReader;
import core.Environments;
import utils.LogUtils;
import utils.PageUtils;
import utils.PlaywrightDriver;

public abstract class BasePage {
    protected final Page page;
    protected PageUtils pageUtils;

    protected BasePage() {
        PlaywrightDriver playwrightDriver = PlaywrightDriver.getInstance();
        playwrightDriver.setupBrowser();
        this.page = playwrightDriver.getPage();
        pageUtils = new PageUtils(page);
        this.page.navigate(Environments.switchEnvironment(ConfigReader.getValue("current.env")));
        LogUtils.logInfo("BasePage initialized successfully.");

    }
}