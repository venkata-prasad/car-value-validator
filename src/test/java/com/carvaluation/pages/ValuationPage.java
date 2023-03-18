package com.carvaluation.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

public class ValuationPage extends BasePage{

    private final String pageHeaderText = "About your car";
    private final String regSummary = "//div[@data-test-id='your-registration-number-summary']//p[2]";

    public String getCarSummary(){
        return getElementText(By.xpath(regSummary)).split(":",2)[1].trim();
    }

    public void validateTitle() {
        Assert.assertEquals(getPageTitle(),pageHeaderText);
    }
}
