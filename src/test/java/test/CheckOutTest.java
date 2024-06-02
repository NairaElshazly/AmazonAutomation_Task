package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testDataModel.TestDataModel;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class CheckOutTest extends BaseTests{

    @BeforeMethod
    public void validateOrderSummaryPreCondition()
    {
        TestDataModel testDataModel1= new TestDataModel();
        fillShippingAddressForm(testDataModel1);

        checkOutPage.navigateToShoppingCart();
        checkOutPage.proceedToBuy();
        checkOutPage.setShippingAddress(testDataModel1);
        checkOutPage.setShippingPaymentMethod();
    }

    @Test
    public void validateOrderSummaryOfAllItems()
    {
        boolean result=checkOutPage.reviewShippingTotalAmount();
        assertTrue(result);

    }


















    public void fillShippingAddressForm(TestDataModel JSON) {

        // Load JSON file into a Map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> data = null;
        try {
            data = objectMapper.readValue(new File("src/test/resources/ShippingAddress.Json"), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Set values in the data model
        JSON.setFullName(data.get("FullName"));
        JSON.setEmailOrMobilePhoneNumber(data.get("MobileNumber"));
        JSON.setStreetName(data.get("StreetName"));
        JSON.setBuildingName(data.get("buildingName"));
       // JSON.setCityOrArea(data.get("cityOrArea"));
       // JSON.setDistrict(data.get("District"));
        JSON.setNearestLandMark(data.get("NearestLandMark"));
    }
}
