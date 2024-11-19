package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {
    public final Page page;
    public final Locator tipaltiLogo;
    public final Locator homePageMainHeadersList;
    public final Locator homePageMainHeadersTextsList;
    public final Locator topPageGetStartedButton;
    public final Locator loginButton;
    public final Locator exampleCostumerSlideAnimation;
    public final Locator costumersSlideList;

    public HomePage(Page page) {
        this.page = page;

        tipaltiLogo = page.locator("div.navbar div.logo");
        homePageMainHeadersList = page.locator("ul li.main-nav__tab-item a");
        homePageMainHeadersTextsList = page.locator("section.navbar-wrapper ul li a.main-nav__tab-item-link p");
        topPageGetStartedButton = page.locator("div.btn-getStarted-area a.btn-get-started");
        loginButton = page.locator("nav.main-nav a.btn-login");
        exampleCostumerSlideAnimation = page.locator("div.customer-logo-carousel div:nth-child(1)");
        costumersSlideList = page.locator("div.customer-logo-carousel div:nth-child(1) a img");
    }
}
