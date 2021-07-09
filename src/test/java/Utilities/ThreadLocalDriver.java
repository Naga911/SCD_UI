package Utilities;


import org.openqa.selenium.WebDriver;

public class ThreadLocalDriver {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public synchronized static void setTLDriver(WebDriver driver) {
        tlDriver.set(driver);
    }

    public synchronized static WebDriver getTLDriver() {
        return tlDriver.get();
    }
}