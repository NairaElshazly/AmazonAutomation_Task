package pages;

import com.sun.net.httpserver.Authenticator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import testDataModel.TestDataModel;

import java.time.Duration;

public class CheckOutPage extends HomePage{


    By goToTheBasket= By.xpath("//a[@href='/-/en/cart?ref_=ewc_gtc']");

    By shoppingCart= By.xpath("//a[@id='nav-cart']");

    By fullNameField =By.xpath("//input[@id='address-ui-widgets-enterAddressFullName']");
    By mobileNumberField = By.xpath("//input[@id='address-ui-widgets-enterAddressPhoneNumber']");
    By streetNameField= By.xpath("//input[@id='address-ui-widgets-enterAddressLine1']");
    By buildingNameOrNoField= By.xpath("//input[@id='address-ui-widgets-enter-building-name-or-number']");
    By cityOrAreaField= By.xpath("//input[@id='address-ui-widgets-enterAddressCity']");
    By districtField= By.xpath("//input[@id='address-ui-widgets-enterAddressDistrictOrCounty']");
    By nearestLandMarkField = By.xpath("//input[@id='address-ui-widgets-landmark']");
    By addAddressButton= By.xpath("//span[@id='address-ui-widgets-form-submit-button-announce']");
    By cashOnDeliveryRadioButton= By.xpath("//input[@id='pp-ysxnwZ-74']");
    By useThisPaymentMethodButton = By.xpath("//span[@id='pp-ysxnwZ-75-announce']");
    By proceedToBuyButton= By.xpath("//input[@name='proceedToRetailCheckout']");


    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToShoppingCart()
    {
        driver.findElement(shoppingCart).click();
    }

    public void proceedToBuy()
    {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(proceedToBuyButton));
        driver.findElement(proceedToBuyButton).click();
    }

    public void selectCityField(TestDataModel testDataModel)
    {
        //webDriverWait.until(ExpectedConditions.elementToBeClickable(cityOrAreaField)).sendKeys(testDataModel.getCityOrArea());
        driver.findElement(cityOrAreaField).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[@class='autoOp'][1]")))).click();

        driver.findElement(By.xpath("//li[@class=\"autoOp\"][1]")).click();
    }

    public void setDistrictField(TestDataModel testDataModel)
    {

       // webDriverWait.until(ExpectedConditions.elementToBeClickable(districtField)).sendKeys(testDataModel.getDistrict());
        driver.findElement(districtField).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[@class='autoOp'][1]")))).click();
    }

    public void setMobileNumber(TestDataModel testDataModel)
    {
        String mobileNumber = testDataModel.getEmailOrMobilePhoneNumber();
        if (mobileNumber != null && !mobileNumber.isEmpty()) {
            driver.findElement(mobileNumberField).sendKeys(mobileNumber);
        } else {
            System.out.println("Mobile number is null or empty.");
        }
    }



    public void setShippingAddress(TestDataModel testDataModel)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(fullNameField).sendKeys(testDataModel.getFullName());
        setMobileNumber(testDataModel);
        driver.findElement(streetNameField).sendKeys(testDataModel.getStreetName());
        driver.findElement(buildingNameOrNoField).sendKeys(testDataModel.getBuildingName());
        selectCityField(testDataModel);
        setDistrictField(testDataModel);
        driver.findElement(nearestLandMarkField).sendKeys(testDataModel.getNearestLandMark());
        driver.findElement(addAddressButton).click();

    }

    public void setShippingPaymentMethod()
    {
        driver.findElement(cashOnDeliveryRadioButton).click();
        driver.findElement(useThisPaymentMethodButton).click();
    }

    public boolean reviewShippingTotalAmount()
    {
        driver.findElement(By.xpath("//div[2]/div/a")).click();

        String orderItemsText= driver.findElement(By.xpath("(//td[@class='a-text-right aok-nowrap a-nowrap'])[1]")).getText().replaceAll("[^0-9.]", "");
        String orderActualTotalSummaryText= driver.findElement(By.xpath("//td[@class='a-color-price a-size-medium a-text-right grand-total-price aok-nowrap a-text-bold a-nowrap']")).getText().replaceAll("[^0-9.]", "");
        String orderShippingHandleText= driver.findElement(By.xpath("(//td[@class='a-text-right aok-nowrap a-nowrap'])[2]")).getText().replaceAll("[^0-9.]", "");
        String orderTotalText=driver.findElement(By.xpath("(//td[@class='a-text-right aok-nowrap a-nowrap'])[3]")).getText().replaceAll("[^0-9.]", "");
        String orderPromotionAppliedText=driver.findElement(By.xpath("(//td[@class='a-text-right aok-nowrap a-nowrap'])[4]")).getText().replaceAll("[^0-9.]", "");


        double orderItems= Double.parseDouble(orderItemsText);
        double shippingHandle = Double.parseDouble(orderShippingHandleText);
        double orderTotal=Double.parseDouble(orderTotalText);
        double orderPromotionApplied=Double.parseDouble(orderPromotionAppliedText);
        double orderActualTotalSummary= Double.parseDouble(orderActualTotalSummaryText);

        double calculatedTotal= orderItems + shippingHandle + orderTotal + orderPromotionApplied ;

        return Double.compare(calculatedTotal , orderActualTotalSummary)==0;

    }
}
