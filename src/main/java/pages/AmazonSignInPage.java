package pages;

import com.sun.net.httpserver.Authenticator;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonSignInPage extends HomePage {

    private WebDriver driver;


    By emailOrMobilePhoneNumberField= By.xpath("//input[@id='ap_email']");
    By continueButton=By.xpath("//input[@id='continue']");
    By passwordField=By.xpath("//input[@id='ap_password']");
    By signInButton=By.xpath("//input[@id='signInSubmit']");

    public AmazonSignInPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }


    public void setEmailOrMobilePhoneNumberField(String username)
    {
        driver.findElement(emailOrMobilePhoneNumberField).sendKeys(username);
        driver.findElement(continueButton).click();
    }

    public void setPasswordField(String password)
    {
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signInButton).click();
    }

    public String getLoggedInPageURL()
    {
        webDriverWait.until(ExpectedConditions.urlContains("signin"));
        return driver.getCurrentUrl();
    }




}
