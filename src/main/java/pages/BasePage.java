package pages;

import com.microsoft.playwright.Page;
import utils.PageUtils;
import utils.PlaywrightDriver;

public class BasePage {
    protected final Page page;
    protected PageUtils pageUtils;

    protected BasePage() {
        this.page = PlaywrightDriver.getInstance().getPage();
        pageUtils = new PageUtils(page);
        this.page.navigate("https://demoqa.com/login");
    }
}
