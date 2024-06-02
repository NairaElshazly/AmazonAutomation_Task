package testDataModel;

import org.openqa.selenium.WebDriver;

public class TestDataModel {

    private String EmailOrMobilePhoneNumber;
    private String Password;
    private String FullName;
    private String MobileNumber;
    private String StreetName;
    private String BuildingName;
    private String CityOrArea;
    private String District;
    private String NearestLandMark;




    //Getters and setters..
    public String getEmailOrMobilePhoneNumber(){ return EmailOrMobilePhoneNumber;}
    public String getPassword(){ return Password;}

    public String getFullName(){return FullName;}




    public void setEmailOrMobilePhoneNumber(String emailOrMobilePhoneNumber) {
        this.EmailOrMobilePhoneNumber = emailOrMobilePhoneNumber;
    }
    public void setPassword(String password) {
        this.Password = password;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }


    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public String getBuildingName() {
        return BuildingName;
    }

    public void setBuildingName(String buildingName) {
        BuildingName = buildingName;
    }

    public String getCityOrArea() {
        return CityOrArea;
    }

    public void setCityOrArea(String cityOrArea) {
        CityOrArea = cityOrArea;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getNearestLandMark() {
        return NearestLandMark;
    }

    public void setNearestLandMark(String nearestLandMark) {
        NearestLandMark = nearestLandMark;
    }












}
