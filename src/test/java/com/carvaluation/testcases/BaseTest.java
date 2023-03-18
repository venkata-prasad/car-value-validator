package com.carvaluation.testcases;

import com.aventstack.extentreports.service.ExtentTestManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.carvaluation.utils.Constants;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


public class BaseTest {

    public static WebDriver driver;

    @BeforeTest
    public void beforeTest(){

    }

    @BeforeMethod
    @Parameters(value={"browserName"})
    public void beforeMethod(final String browserName) {
        SetupBrowser(browserName);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.of(Constants.TIMEOUT_PERIOD, ChronoUnit.SECONDS));
        driver.get(Constants.BASE_URL);
    }

    @AfterMethod
    public void AfterMethod(ITestResult iTestResult) {
        if(iTestResult.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().addScreenCaptureFromBase64String(getScreenshot(driver));
        }
        driver.quit();
    }

    @AfterTest
    public void AfterTest(){

    }

    private void SetupBrowser(final String browserName){
        switch(browserName){
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = null;
        }
    }

    private String getScreenshot(WebDriver driver){

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

}
