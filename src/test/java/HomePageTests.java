

import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import utilities.Base;

@Listeners(utilities.Listeners.class)
public class HomePageTests extends Base {

    private boolean setupComplete = false;
    private final String[] expectedHomePageTopHeadersTexts = {"Products", "Solutions", "Resources", "Pricing", "Company"};
    private final String expectedLoginButtonBackgroundColor = "rgb(20, 20, 20)";
    private final int expectedCustomerSlideListSize = 46;


    @BeforeClass
    public void classSetup() {
        try {
            initCore();
            usefulMethods.deleteAllFilesInAGivenPath("test-output/screenshots");
            String url = usefulMethods.getData("baseURL");
            System.out.println("Navigating to: " + url);
            page.navigate(url);
            setupComplete = true;
        } catch (Exception e) {
            System.err.println("Test setup failed: " + e.getMessage());
            closePlaywright();
            throw new RuntimeException("Test setup failed", e);
        }
    }

    @Test
    public void test_01_verifyHomePageTopTabs() {
        homePageFlows.verifyHomePageTopHeaders(expectedHomePageTopHeadersTexts);
    }

    @Test
    public void test_02_verifyLoginButtonBackgroundColor() {
        homePageFlows.verifyLoginButtonBackgroundColor(expectedLoginButtonBackgroundColor);
    }

    @Test
    public void test_03_verifyNumberOfCustomersDisplayedInSlideAnimationAndPrintTheirValues() {
        homePageFlows.verifyExampleSlideCostumerListSizeAndPrintValues(expectedCustomerSlideListSize);
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (hasPlaywrightResources()) {
            closePlaywright();
        }
    }
}


