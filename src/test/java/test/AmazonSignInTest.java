package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import pages.AmazonSignInPage;
import testDataModel.TestDataModel;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertTrue;
public class AmazonSignInTest extends BaseTests {



    @Test
    public void validateSignInSuccessfully()
    {
        fillSignInForm(testDataModel);
        AmazonSignInPage amazonSignInPage = homePage.clickSignInLink();
        amazonSignInPage.setEmailOrMobilePhoneNumberField("naira.assem1999@gmail.com");
        amazonSignInPage.setPasswordField("01278969715");
        assertTrue(amazonSignInPage.getLoggedInPageURL().contains("signin"));
    }


    public void fillSignInForm(TestDataModel JSON) {

        // Load JSON file into a Map
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> data = null;
        try {
            data = objectMapper.readValue(new File("src/test/resources/SignIn.Json"), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Set values in the data model
        JSON.setEmailOrMobilePhoneNumber(data.get("Email"));
        JSON.setPassword(data.get("Password"));

    }

}
