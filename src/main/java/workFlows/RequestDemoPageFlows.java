package workFlows;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import extensions.UIActions;
import extensions.Verifications;
import pageObjects.RequestDemoPage;
import pageObjects.HomePage;
import utilities.Base;
import utilities.UsefulMethods;

import java.util.ArrayList;
import java.util.List;


public class RequestDemoPageFlows {

    private final RequestDemoPage requestDemoPage;
    private final HomePage homePage;
    public final Page page;
    public final UIActions uiActions;
    public final Verifications verifications;
    public final UsefulMethods usefulMethods;


    public RequestDemoPageFlows(Base base, RequestDemoPage requestDemoPage, HomePage homePage) {
        this.requestDemoPage = requestDemoPage;
        this.homePage = homePage;
        this.page = base.getPageForFlow();
        this.uiActions = base.getUiActions();
        this.verifications = base.getVerifications();
        this.usefulMethods = base.getUsefulMethods();
    }


    public void verifyVisibilityRequestDemoWindowElements() {
        List<Locator> requestDemoWindowElements = new ArrayList<>();
        requestDemoWindowElements.add(0, requestDemoPage.getStartedPageMainTitle);
        requestDemoWindowElements.add(1, requestDemoPage.getStartedPageSecondaryTitle);
        requestDemoWindowElements.add(2, requestDemoPage.emailInputField);
        requestDemoWindowElements.add(3, requestDemoPage.industrySelectionDropdown);
        requestDemoWindowElements.add(4, requestDemoPage.companySizeSelectionDropdown);
        requestDemoWindowElements.add(5, requestDemoPage.phoneInputField);
        requestDemoWindowElements.add(6, requestDemoPage.privacyPolicyConsentCheckbox);
        requestDemoWindowElements.add(7, requestDemoPage.consentCheckboxText);
        requestDemoWindowElements.add(8, requestDemoPage.consentDeclarationLink);
        requestDemoWindowElements.add(9, requestDemoPage.requestDemoButton);
        requestDemoWindowElements.add(10, requestDemoPage.closeGetStartedWindowXButton);
        verifications.verifyVisibilityOfAllListMembers(requestDemoWindowElements);
    }

    public void verifyCorrectCompanySizeOptionsAreDisplayed(String[] expectedCompanySizeOptions) {
        verifications.verifyCorrectTextInEachListIndex(requestDemoPage.companySizeOptionsList.all(),
                expectedCompanySizeOptions);
    }

    public void verifyAdditionalFieldsAreAddedWhenEmailAndIndustryAreFilledOut(String email, String industry) {
        List<Locator> newAddedFields = new ArrayList<>();
        requestDemoPage.emailInputField.fill(email);
        requestDemoPage.industrySelectionDropdown.selectOption(industry);
        page.waitForSelector("input[name='firstName']").isEditable();
        newAddedFields.add(0, requestDemoPage.firstNameInputField);
        newAddedFields.add(1, requestDemoPage.lastNameInputField);
        newAddedFields.add(2, requestDemoPage.countryNameSelectionDropdown);
        verifications.allListWebElementsAreEnabled(newAddedFields);
    }

    public void completeWithoutApproveConsent(String[] inputValues) {
        page.reload();
        homePage.topPageGetStartedButton.click();
        requestDemoPage.emailInputField.fill(inputValues[0]);
        requestDemoPage.industrySelectionDropdown.selectOption(inputValues[1]);
        requestDemoPage.companySizeSelectionDropdown.selectOption(inputValues[2]);
        page.waitForSelector("input[name='firstName']").isEditable();
        page.waitForTimeout(2000);
        requestDemoPage.firstNameInputField.fill(inputValues[3]);
        requestDemoPage.lastNameInputField.fill(inputValues[4]);
        try {
            requestDemoPage.companyNameInputField.fill(inputValues[5]);
        } catch (Exception e) {
            System.out.println("The company field is not displayed");
        }
        page.waitForSelector("input[name='lastName']").inputValue().equalsIgnoreCase(inputValues[4]);
        requestDemoPage.countryNameSelectionDropdown.selectOption(inputValues[6]);
        requestDemoPage.phoneInputField.fill(inputValues[7]);
        requestDemoPage.requestDemoButton.click();
        verifications.listSize(requestDemoPage.missingFieldErrorMessage.all(), 1);
    }

}
