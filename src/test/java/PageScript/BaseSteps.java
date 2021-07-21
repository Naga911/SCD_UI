package PageScript;

import Base.ThreadLocalClass;
import PageObjects.LoginPageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseSteps  {


    ThreadLocalClass threadLocalClass;

    protected LoginPageObjects loginPageObjects = null;

    protected WebDriverWait wait;

    public BaseSteps() {

    }

    //Screen Classes Initialization
    protected void setupCucumber() {
        System.out.println("Cucumber Base Test Before-login-test-cucumber");
        loginPageObjects = new LoginPageObjects(threadLocalClass.getT1driver());

    }
}
