package com.carvaluation.pages;

import com.carvaluation.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.carvaluation.testcases.BaseTest.driver;

public class BasePage {

    private static final Logger log = LogManager.getLogger(BasePage.class);

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void setElementText(final By locator, final String inputText) {
        getElement(locator).sendKeys(inputText);
    }

    public String getElementText(final By locator) {
        return getElement(locator).getText();
    }

    public void click(final By locator) {
        getElement(locator).click();
    }

    private WebElement getElement(final By locator) {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.of(Constants.TIMEOUT_PERIOD, ChronoUnit.SECONDS));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
