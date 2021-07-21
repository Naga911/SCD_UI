package Utilities;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class OptionsManager {

    //Get Chrome Options
    public static ChromeOptions getChromeOptions() {
            String path = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", path + "\\Drivers\\chromedriver.exe");
            ChromeOptions options = (ChromeOptions) new ChromeOptions().setAcceptInsecureCerts(true);
            options.setAcceptInsecureCerts(true);
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-popup-blocking");
            //driver = new ChromeDriver(options);
           // new ChromeDriver(options);

            return options;

        }

    //Get Firefox Options
    public static FirefoxOptions getFirefoxOptions () {

            String path = System.getProperty("user.dir");
            System.setProperty("webdriver.gecko.driver", path + "\\Drivers\\geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();
            //Accept Untrusted Certificates
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(false);
            //Use No Proxy Settings
            profile.setPreference("network.proxy.type", 0);
            profile.setPreference("dom.disable_beforeunload", true);
            //Set Firefox profile to capabilities
            //  options.setCapability(FirefoxDriver.PROFILE, profile);
            //    new FirefoxDriver(options);
            return options;
        }
    }
