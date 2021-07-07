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

    @Test(retryAnalyzer = Retry.class)
    public void testpass() throws IOException {
        ExtentTest logInfo = null;

        try {
            BaseUtill baseUtil = new BaseUtill();
            driver = baseUtil.GetDriver();
            driver.get("https://www.amazon.in/");
            driver.manage().window().maximize();
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(WebDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
            logger = extent.createTest("executing pass scenario").assignCategory("Sanity").assignAuthor("Nagaraj");
            logger.log(Status.PASS,"Test Case passed");

        } catch (AssertionError | Exception e) {
            testStepHandle("PASS", BaseUtill.GetDriver(), logger, e);
        }
    }

    @Test(retryAnalyzer = Retry.class)
    public void testskip() {
        ExtentTest logInfo = null;
        try {
            logger = extent.createTest("skip test").assignCategory("Sanity").assignAuthor("Nagaraj");
            throw new SkipException("executing skip scenario");

        } catch (AssertionError | Exception e) {
            testStepHandle("SKIP", BaseUtill.GetDriver(), logger, e);
        }
    }

    @Test(retryAnalyzer = Retry.class)
    public void testfailed() {
        ExtentTest logInfo = null;
        try {
            logger = extent.createTest("failed test").assignCategory("Regression").assignAuthor("Nagaraj");
            BaseUtill baseUtil = new BaseUtill();
            driver = baseUtil.GetDriver();
            driver.get("https://www.amazon.in/");
            driver.manage().window().maximize();
            String currentURL = driver.getCurrentUrl();
            Assert.assertEquals(currentURL, "NoTitle");

        }catch (AssertionError | Exception e) {

            testStepHandle("FAIL", BaseUtill.GetDriver(), logger, e);
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
