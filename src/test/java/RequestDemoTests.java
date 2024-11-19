import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.Base;

@Listeners(utilities.Listeners.class)
public class RequestDemoTests extends Base {

    private boolean setupComplete = false;

    private final String[] expectedAvailableCompanySizeOptions = {"*Company size", "Under 10", "10 to 19", "20 to 49",
            "50 to 99", "100 to 149", "150 to 249", "250 to 499", "500 to 999", "1000 to 1999", "2000 to 4999",
            "5000 to 9999", "10000+"};
    private final String[] inputValues = {"someone@playtika.com", "Gaming", "100 to 149", "Moshe", "Cohen", "Playtika",
            "Israel", "098485833"};


    @BeforeClass
    public void classSetup() {
        try {
            initCore();
            usefulMethods.deleteAllFilesInAGivenPath("test-output/screenshots");
            String url = usefulMethods.getData("baseURL");
            System.out.println("Navigating to: " + url);
            page.navigate(url);
            setupComplete = true;
            homePage.topPageGetStartedButton.click();
        } catch (Exception e) {
            System.err.println("Test setup failed: " + e.getMessage());
            closePlaywright();
            throw new RuntimeException("Test setup failed", e);
        }
    }

    @Test
    public void test_01_verifyAllGetStartedWindowElementsAreDisplayed() {
        requestDemoPageFlows.verifyVisibilityRequestDemoWindowElements();
    }

    @Test
    public void test_02_verifyAvailableCompanySizeOptions() {
        requestDemoPageFlows.verifyCorrectCompanySizeOptionsAreDisplayed(expectedAvailableCompanySizeOptions);
    }

    @Test
    public void test_03_completeWithoutApproveConsent() {
        requestDemoPageFlows.completeWithoutApproveConsent(inputValues);
    }


    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (hasPlaywrightResources()) {
            closePlaywright();
        }
    }

}
