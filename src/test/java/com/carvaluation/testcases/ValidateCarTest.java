package com.carvaluation.testcases;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.carvaluation.pages.HomePage;
import com.carvaluation.pages.ValuationPage;
import com.carvaluation.utils.DataHandler;
import java.util.Map;
import static org.testng.Assert.assertEquals;

@Listeners({ExtentITestListenerClassAdapter.class})
public class ValidateCarTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(ValidateCarTest.class);
    Map<String, String> expectedOutput = DataHandler.outputFileReader();

    @Test(dataProvider = "registrationNumberList", dataProviderClass = DataHandler.class)
    public void ValidateCarSummary(String registrationNumber) {

        log.info("Validation started for {}", registrationNumber);
        HomePage homePage = new HomePage();
        homePage.validateTitle();
        homePage.submitPopUpBanner();
        homePage.enterCarRegNumber(registrationNumber);
        homePage.submitCarRegNumber();

        ValuationPage valuationPage = new ValuationPage();
        valuationPage.validateTitle();
        assertEquals(valuationPage.getCarSummary(), expectedOutput.get(registrationNumber));
        log.info("Validation complete for {}", registrationNumber);
    }
}