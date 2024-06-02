package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class HomePage {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    private static final Logger LOGGER = Logger.getLogger(HomePage.class.getName());



    private By signInLink= By.xpath("//a[@id='nav-link-accountList']");

    public HomePage(WebDriver driver)
    {
        this.driver=driver;
        webDriverWait= new WebDriverWait(driver,Duration.ofSeconds(60));
    }

    public AmazonSignInPage clickSignInLink()
    {
        driver.findElement(signInLink).click();
        return new AmazonSignInPage(driver);
    }











    protected void scrollIntoViewAndClick(By locator) {
        try {
            // Find the element using the locator
            WebElement element = driver.findElement(locator);

            // Scroll the element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            // Then click on the element
            element.click();
        } catch (Exception e) {
            // Log the exception
            LOGGER.severe("An error occurred while scrolling into view and clicking element: " + e.getMessage());
        }
    }
}







