package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AmazonSignInUserHomePage;
import static org.testng.Assert.assertTrue;


public class AddProductsBelow15KToTheCartTest extends BaseTests {


    @BeforeMethod
    public void validateProductsAddedToTheCartPreConditions()
    {
        amazonSignInUserHomePage.openAllMenu();
        amazonSignInUserHomePage.setSeeAllButton();
        amazonSignInUserHomePage.setVideoGamesButton();
        amazonSignInUserHomePage.setAllVideoGamesButton();
        amazonSignInUserHomePage.setFreeShippingFilter();
        amazonSignInUserHomePage.setNewConditionLink();
        amazonSignInUserHomePage.setSortByDropDownList();

    }

    @Test
    public void validateAllProductBelow15KAddedToTheCart()
    {
        amazonSignInUserHomePage.addProductBelow15KToCart();
        amazonSignInUserHomePage.getCurrentNumberOfProductsAddedToTheCart();
        boolean result=amazonSignInUserHomePage.verifyAllProductsBelow15KAddedToTheCart();
        assertTrue(result);
    }
}
