package Base;


import Utilities.Log;
import Utilities.OptionsManager;

import org.openqa.selenium.WebDriver;
import PageObjects.LoginPageObjects;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class ThreadLocalClass {

    public static ThreadLocalClass threadLocalClass = new ThreadLocalClass();

    public static ThreadLocalClass getInstance() {
        return threadLocalClass;
    }

    private static ThreadLocal<WebDriver> t1driver = new ThreadLocal<>();

    //FACTORY DESIGN PATTERN
    public WebDriver getT1driver() {
        return t1driver.get();
    }

    public void setT1driver(WebDriver driver) {

        t1driver.set(driver);
    }

    public void closedriver() {
        t1driver.remove();
    }

    public static WebDriver initDriver(String browser) {

        WebDriver driver=null;

            if (browser.equals("chrome")) {
                OptionsManager.getChromeOptions();
             //   t1driver.set(new ChromeDriver());
                GetCurrentTimeStamp();
            } else if (browser.equals("firefox")) {
                OptionsManager.getFirefoxOptions();
            //    t1driver.set(new FirefoxDriver());
                GetCurrentTimeStamp();

            }return driver;
        }


    public static void GetCurrentTimeStamp() {
        String timestamp =
                new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
        System.out.println("Time of Launch :" + timestamp);
    }

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

    public WebDriver setups(String browser) throws IOException {

        DesiredCapabilities caps = new DesiredCapabilities();
        if (getT1driver() == null) {
            caps.setCapability("browsername", browser);
            String path = System.getProperty("user.dir");

            if (browser.contains("chrome")) {
                System.setProperty("webdriver.chrome.driver", path + "\\Drivers\\chromedriver.exe");
                ChromeOptions chromeOptions = (ChromeOptions) new ChromeOptions().setAcceptInsecureCerts(true);
                caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                t1driver.set(new ChromeDriver(chromeOptions));
                Log.info("Chrome browser initiated");
            } else if (browser.contains("firefox")) {
                System.setProperty("webdriver.gecko.driver", path + "\\Drivers\\geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions().setAcceptInsecureCerts(true);
                caps.setCapability(firefoxOptions.getBrowserName(), firefoxOptions);
                //   baseUtil.setDriver(new FirefoxDriver());
                //  ThreadLocalDriver.setTLDriver(driver);
                t1driver.set(new FirefoxDriver(firefoxOptions));
                Log.info("Firefox browser initiated");
            } else if (browser.contains("ie")) {
                System.setProperty("webdriver.ie.driver", path + "\\Drivers\\IEDriverServer.exe");
                InternetExplorerOptions explorerOptions = new InternetExplorerOptions().setAcceptInsecureCerts(true);
                //   baseUtil.setDriver(new InternetExplorerDriver());
                //  ThreadLocalDriver.setTLDriver(driver);
                t1driver.set(new InternetExplorerDriver(explorerOptions));
                Log.info("IE browser initiated");
            }
        }
        return getT1driver();
    }
}


