package Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;


import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class ExtentReportManager {


    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    public static ExtentTest logger;
    public static ExtentTest parentTest;
    public static ExtentTest childTest;
    public static ExtentReports extent = new ExtentReports();


    @BeforeClass
    @SuppressWarnings("Duplicates")
    public static ExtentReports report() {
        String projectName = "PAW12";
        try {

            ExtentKlovReporter klovReporter = new ExtentKlovReporter(projectName);
            klovReporter.initMongoDbConnection("localhost", 27017);
            klovReporter.initKlovServerConnection("http://localhost");
            String css = ".r-img {width: 50%;}";
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Architecture", System.getProperty("os.arch"));
            extent.attachReporter(klovReporter);


            // directory where output is to be printed
            /*ExtentAventReporter avent = new ExtentAventReporter("user/build/name/");
            ExtentReports extent = new ExtentReports();
            extent.attachReporter(avent);*/
        } catch (Exception e) {
            System.out.println(e);
        }
        return extent;

    }

    @SuppressWarnings("Duplicates")
    public static void testStepHandle(String teststatus, WebDriver driver, ExtentTest test, String throwable) {
        switch (teststatus) {
            case "FAIL":
               childTest.fail(throwable);

               /* childTest.fail(MarkupHelper.createLabel("<details><summary><b><font color=red>Exception occured, click to see details:"
                        +"</font><b></summary>"+throwable+"</details> \n",ExtentColor.RED));*/


                try {
                    String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                    childTest.log(Status.FAIL, "Screenshot of failed test Case", childTest.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
                } catch (Exception e) {
                    childTest.fail("Test failed, cannot attach screenshot");
                }
                break;

            case "PASS":
                childTest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
                break;
            case "SKIP":
                childTest.skip(MarkupHelper.createLabel("Test Case is Skipped ", ExtentColor.YELLOW));
                break;
            default:
                break;
        }
    }

    public static String captureScreenShot(WebDriver driver) throws IOException {

        TakesScreenshot screen = (TakesScreenshot) driver;
        File src = screen.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir");
        String dest = path + "\\target\\Screenshots\\" + getcurrentdateandtime() + ".png";
        File target = new File(dest);
        FileUtils.copyFile(src, target);
        return dest;
    }

    private static String getcurrentdateandtime() {
        String str = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
            Date date = new Date();
            str = dateFormat.format(date);
            str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}