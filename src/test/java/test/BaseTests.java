package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.AmazonSignInPage;
import pages.AmazonSignInUserHomePage;
import pages.CheckOutPage;
import pages.HomePage;
import testDataModel.TestDataModel;

import static org.testng.Assert.assertTrue;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;
    protected AmazonSignInUserHomePage amazonSignInUserHomePage;
    protected CheckOutPage checkOutPage;
    protected TestDataModel testDataModel;



    @BeforeClass
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.eg/");
        driver.manage().window().maximize();

        homePage= new HomePage(driver);
        amazonSignInUserHomePage= new AmazonSignInUserHomePage(driver);
        checkOutPage= new CheckOutPage(driver);



        AmazonSignInPage amazonSignInPage = homePage.clickSignInLink();
        amazonSignInPage.setEmailOrMobilePhoneNumberField("naira.assem1999@gmail.com");
        amazonSignInPage.setPasswordField("01278969715");
        assertTrue(amazonSignInPage.getLoggedInPageURL().contains("signin"));
    }



    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }


}
