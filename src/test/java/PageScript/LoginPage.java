package PageScript;

import Base.BrowserFactory;
import Base.ThreadLocalClass;

import Utilities.Log;
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
import java.util.Arrays;

import Report.ExtentReportManager;

public class LoginPage extends ExtentReportManager {

    private WebDriver driver;
    ThreadLocalClass threadLocalClass;

    @Parameters("browser")
    @BeforeMethod
    public void LaunchApp(String browser) {
        BrowserFactory browserFactory = new BrowserFactory();

        ThreadLocalClass.getInstance().setT1driver(browserFactory.createBrowserInstance(browser));
        driver = ThreadLocalClass.getInstance().getT1driver();
    }

    @Test
    public void testpass() throws IOException {
        try {
            System.out.println("hello we are working");
            driver.get("https://www.amazon.in/");

            driver.manage().window().maximize();
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(WebDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
            parentTest = extent.createTest("executing scenarios").assignCategory("Sanity").assignAuthor("Nagaraj");
            childTest = parentTest.createNode("Verification of pass test scenario");
            childTest.log(Status.PASS, "Test Case passed");

        } catch (AssertionError | Exception e) {
            String e1 = Arrays.toString(e.getStackTrace());
            testStepHandle("PASS", driver, childTest, e1);
        }
    }

    @Test
    public void testskip() {
        ExtentTest logInfo = null;
        try {

            parentTest = extent.createTest("skip test").assignCategory("Sanity").assignAuthor("Nagaraj");
            childTest = parentTest.createNode("Verification of skip test scenario");

            throw new SkipException("executing skip scenario");

        } catch (AssertionError | Exception e) {
            String e1 = Arrays.toString(e.getStackTrace());
            testStepHandle("SKIP", driver, childTest, e1);
        }
    }

    @Test
    public void testfailed() {
        ExtentTest logInfo = null;
        try {
            parentTest = extent.createTest("scenarios for Failed Case").assignCategory("Regression").assignAuthor("Nagaraj");

            childTest = parentTest.createNode("Verification of failed test scenario");


            driver.get("https://www.amazon.in/");
            driver.manage().window().maximize();
            String currentURL = driver.getCurrentUrl();
            Assert.assertEquals(currentURL, "NoTitle");

        } catch (AssertionError | Exception e) {
            String e1 = Arrays.toString(e.getStackTrace());
            testStepHandle("FAIL", driver, childTest, e1);
        }

    }

    @AfterMethod
    public void aftermethod(ITestResult result) {
        String methodname = result.getMethod().getMethodName();
        if (result.getStatus() == ITestResult.FAILURE) {
            String exceptionmessage = Arrays.toString(result.getThrowable().getStackTrace());
            childTest.fail("<details><summary><b><font color=red>Exception occured, click to see details:"
                    + "</font><b></summary>" + exceptionmessage.replaceAll(",", "<br") + "</details> \n");
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
        threadLocalClass.closedriver();
    }


}
