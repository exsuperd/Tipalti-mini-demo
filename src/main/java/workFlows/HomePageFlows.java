package workFlows;

import com.microsoft.playwright.Page;

import extensions.UIActions;
import extensions.Verifications;
import pageObjects.HomePage;
import utilities.Base;
import utilities.UsefulMethods;

public class HomePageFlows {

    private final HomePage homePage;
    public final Page page;
    public final UIActions uiActions;
    public final Verifications verifications;
    public final UsefulMethods usefulMethods;

    public HomePageFlows(Base base, HomePage homePage) {
        this.homePage = homePage;
        this.page = base.getPageForFlow();
        this.uiActions = base.getUiActions();
        this.verifications = base.getVerifications();
        this.usefulMethods = base.getUsefulMethods();
    }

    public void verifyHomePageTopHeaders(String[] expectedHomepageTopHeadersTexts) {
        verifications.verifyCorrectTextInEachListIndex(homePage.homePageMainHeadersTextsList.all(), expectedHomepageTopHeadersTexts);
        verifications.allListWebElementsAreEnabled(homePage.homePageMainHeadersList.all());
    }

    public void verifyLoginButtonBackgroundColor(String expectedBackgroundColor) {
        verifications.getAndVerifyLocatorBackgroundColor(homePage.loginButton, expectedBackgroundColor);
    }

    public void verifyExampleSlideCostumerListSizeAndPrintValues(int expectedStringListSize) {
        verifications.listSize(homePage.costumersSlideList.all(), expectedStringListSize);
        uiActions.printAllListItemsValuesOfSpecificAttribute(homePage.costumersSlideList.all(), "alt");
    }
}