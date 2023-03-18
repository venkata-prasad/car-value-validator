package com.carvaluation.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends BasePage{

    private final String pageHeaderText = "Value your car with our free car valuation calculator | Cazoo";
    private final String regInput = "vrm";
    private final String valuationButton = "//form[@action='/sell/']//button[@type='submit']";
    private final String popUpButton = "//span[normalize-space()='Accept All']";

    public void enterCarRegNumber(final String regNumber){
        setElementText(By.id(regInput),regNumber);
    }

    public void submitPopUpBanner() {
        click(By.xpath(popUpButton));
    }

    public void validateTitle() {
        Assert.assertEquals(getPageTitle(),pageHeaderText);
    }

    public void submitCarRegNumber(){
        click(By.xpath(valuationButton));
    }
}
