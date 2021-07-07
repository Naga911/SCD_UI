package PageScript;

import Base.BaseUtill;

import Utilities.Log;
import Utilities.Retry;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentTest;

import java.io.IOException;
import java.time.Duration;

import Report.ExtentReportManager;


public class LoginPage extends ExtentReportManager {

    private WebDriver driver = BaseUtill.GetDriver();
    ITestResult getresult;


    @BeforeTest
    public void setupLoginSteps() {
        System.out.println("Before-login-test");
        BaseSteps baseSteps = new BaseSteps();
        baseSteps.setupCucumber();
    }

    @Test
    public void testpass() throws IOException {
        ExtentTest logInfo = null;

        try {
            BaseUtill baseUtil = new BaseUtill();
            driver = baseUtil.GetDriver();
            driver.get("https://www.amazon.in/");
            driver.manage().window().maximize();
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(WebDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
            parentTest = extent.createTest("executing scenarios").assignCategory("Sanity").assignAuthor("Nagaraj");
            childTest=parentTest.createNode("Verification of pass test scenario");


            childTest.log(Status.PASS,"Test Case passed");

        } catch (AssertionError | Exception e) {
            testStepHandle("PASS", BaseUtill.GetDriver(), childTest, e);
        }
    }

    @Test
    public void testskip() {
        ExtentTest logInfo = null;
        try {

          //  parentTest = extent.createTest("skip test").assignCategory("Sanity").assignAuthor("Nagaraj");
            childTest=parentTest.createNode("Verification of skip test scenario");

            throw new SkipException("executing skip scenario");

        } catch (AssertionError | Exception e) {
            testStepHandle("SKIP", BaseUtill.GetDriver(), childTest, e);
        }
    }

    @Test
    public void testfailed() {
        ExtentTest logInfo = null;
        try {
            parentTest = extent.createTest("scenarios for Failed Case").assignCategory("Regression").assignAuthor("Nagaraj");

            childTest=parentTest.createNode("Verification of failed test scenario");
            BaseUtill baseUtil = new BaseUtill();
            driver = baseUtil.GetDriver();
            driver.get("https://www.amazon.in/");
            driver.manage().window().maximize();
            String currentURL = driver.getCurrentUrl();
            Assert.assertEquals(currentURL, "NoTitle");

        }catch (AssertionError | Exception e) {

            testStepHandle("FAIL", BaseUtill.GetDriver(), childTest, e);
        }

    }


    /*@AfterMethod
    public void aftermethod(ITestResult iTestResult) throws IOException {


    }*/
    /*public String takescreenshot(String methodName) {
        String fileName = getScreenshotName(methodName);
        String directory = System.getProperty("user.dir") + "\\target\\Screenshots\\";
        System.out.println(directory);
        new File(directory).mkdirs();
        String paths = directory + fileName;
        try {

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(paths));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paths;
    }

    public static String getScreenshotName(String methodName) {
        Date d = new Date();
        String fileName = methodName + "_" + d.toString().replace(":", "_").replace("", "_") + ".png";
        return fileName;
    }*/


    // @AfterClass
    public void tearDown() {
        Log.info("Tests are ending");
        driver.quit();
    }


}
