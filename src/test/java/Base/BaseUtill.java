package Base;


import Utilities.Log;
import Utilities.ThreadLocalDriver;
import org.openqa.selenium.WebDriver;
import PageObjects.LoginPageObjects;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class BaseUtill {

    private static WebDriver driver;


    public BaseUtill() throws IOException {//parameterised constructor
        setup();
    }

    public static WebDriver GetDriver() { //encapsulation

        return driver;

    }

   /* public void setDriver(WebDriver Driver) {
        driver = Driver;
    }

    public void initializePageObjectClasses() {
        PageObjects.LoginPageObjects loginPageObjects = new LoginPageObjects(driver);
    }*/

    public static Properties LoadProperties() {
        String path = System.getProperty("user.dir");
        System.out.println(path);
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(path + "\\src\\test\\resources\\config.properties"));
        } catch (IOException e) {
            System.out.println("Device Configuration properties file cannot be found");
        }
        return prop;
    }
   // @Parameters({"BrowserType"})
    public static WebDriver setup() throws IOException {
        String browser = LoadProperties().getProperty("browsertype");
        if (GetDriver() == null) {
            String path = System.getProperty("user.dir");

            if (browser.contains("chrome")) {
                System.setProperty("webdriver.chrome.driver", path + "\\Drivers\\chromedriver.exe");
                ChromeOptions chromeOptions = (ChromeOptions) new ChromeOptions().setAcceptInsecureCerts(true);
                //  baseUtil.setDriver(new ChromeDriver());

             //   ThreadLocalDriver.setTLDriver(driver);
                driver = new ChromeDriver(chromeOptions);
                Log.info("Chrome browser initiated");
            } else if (browser.contains("firefox")) {
                System.setProperty("webdriver.gecko.driver", path + "\\Drivers\\geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions().setAcceptInsecureCerts(true);
                //   baseUtil.setDriver(new FirefoxDriver());
              //  ThreadLocalDriver.setTLDriver(driver);
                driver = new FirefoxDriver(firefoxOptions);
                Log.info("Firefox browser initiated");
            } else if (browser.contains("ie")) {
                System.setProperty("webdriver.ie.driver", path + "\\Drivers\\IEDriverServer.exe");
                InternetExplorerOptions explorerOptions = new InternetExplorerOptions().setAcceptInsecureCerts(true);
                //   baseUtil.setDriver(new InternetExplorerDriver());
              //  ThreadLocalDriver.setTLDriver(driver);
                driver = new InternetExplorerDriver(explorerOptions);
                Log.info("IE browser initiated");
            }
        }
        return GetDriver();
    }
}


