package Base;

import Utilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;

public class BrowserFactory {


    public WebDriver createBrowserInstance(String browser)
    {
        WebDriver driver=null;
        DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browsername", browser);
            String path = System.getProperty("user.dir");

            if (browser.contains("chrome")) {
                System.setProperty("webdriver.chrome.driver", path + "\\Drivers\\chromedriver.exe");
                ChromeOptions chromeOptions = (ChromeOptions) new ChromeOptions().setAcceptInsecureCerts(true);
                caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                driver=new ChromeDriver(chromeOptions);
                Log.info("Chrome browser initiated");
            } else if (browser.contains("firefox")) {
                System.setProperty("webdriver.gecko.driver", path + "\\Drivers\\geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions().setAcceptInsecureCerts(true);
                caps.setCapability(firefoxOptions.getBrowserName(), firefoxOptions);
                //   baseUtil.setDriver(new FirefoxDriver());
                //  ThreadLocalDriver.setTLDriver(driver);
                driver=new FirefoxDriver(firefoxOptions);
                Log.info("Firefox browser initiated");
            } else if (browser.contains("ie")) {
                System.setProperty("webdriver.ie.driver", path + "\\Drivers\\IEDriverServer.exe");
                InternetExplorerOptions explorerOptions = new InternetExplorerOptions().setAcceptInsecureCerts(true);
                //   baseUtil.setDriver(new InternetExplorerDriver());
                //  ThreadLocalDriver.setTLDriver(driver);
                driver=new InternetExplorerDriver(explorerOptions);
                Log.info("IE browser initiated");
            }
        return driver;
    }

    }



