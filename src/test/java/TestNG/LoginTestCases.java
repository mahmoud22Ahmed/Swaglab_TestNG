package TestNG;

import Utilities.DataUtil;
import Pages.LoginPage;
import Utilities.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;


public class LoginTestCases {
    WebDriver Driver ;
    private SoftAssert SoftAssert;
    ;
    //@Parameters(value = {"Chrome"})
    @BeforeMethod(alwaysRun = true)
    //public void Setup(@Optional("Chrome") String Browser){
    public void Setup() throws IOException {
        String Browser = Utilities.DataUtil.getPropertyValue("Environment","BROWSER");
        switch (Browser.toLowerCase()){
            case  "edge":
                Driver = new EdgeDriver();
                break;
            default:
                Driver = new ChromeDriver();
        }
        Driver.get(DataUtil.getPropertyValue("Environment","LOGIN_URL"));
        Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Parameters(value = {"username","password"})
    @Test(priority = 1,groups = {"valid"})
    public void TC1_ValidLogin(@Optional("standard_user") String UserName,@Optional("secret_sauce") String Password) throws IOException {
        Logs.LOGGER.info("TC1 started");
        SoftAssert = new SoftAssert();
        /*
        //using parameters

        Driver.findElement(By.cssSelector("#user-name")).sendKeys(UserName);
        Driver.findElement(By.cssSelector("#password")).sendKeys(Password);
        */
        Logs.LOGGER.info(DataUtil.getJsonData("ValidLoginData","username"));
        Logs.LOGGER.info(DataUtil.getJsonData("ValidLoginData","password"));

        new LoginPage(Driver).EnterUsername(DataUtil.getJsonData("ValidLoginData","username"))
                             .EnterPassword(DataUtil.getJsonData("ValidLoginData","password"))
                             .ClickLogin();
        SoftAssert.assertEquals(Driver.getCurrentUrl(), DataUtil.getPropertyValue("Environment","HOME_URL"));
        SoftAssert.assertAll();
    }

    @Parameters(value = {"username_invalid","password_invalid"})
    @Test(priority = 2,groups = {"invalid"})
    public void TC2_InvalidLogin(@Optional("jslsajsalsaj") String UserName,@Optional("slsaalkslksa") String Password){
        SoftAssert = new SoftAssert();
        Driver.findElement(By.cssSelector("#user-name")).sendKeys(UserName);
        Driver.findElement(By.cssSelector("#password")).sendKeys(Password);
        Driver.findElement(By.cssSelector("#login-button")).click();
        SoftAssert.assertNotEquals(Driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        SoftAssert.assertEquals(Driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
        SoftAssert.assertAll();
    }

    @Parameters(value = {"username","password_invalid"})
    @Test(priority = 2,groups = {"invalid"})
    public void TC3_InvalidPasswordLogin(@Optional("standard_user") String UserName,@Optional("slsaalkslksa") String Password){
        SoftAssert = new SoftAssert();
        Driver.findElement(By.cssSelector("#user-name")).sendKeys(UserName);
        Driver.findElement(By.cssSelector("#password")).sendKeys(Password);
        Driver.findElement(By.cssSelector("#login-button")).click();
        SoftAssert.assertNotEquals(Driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        SoftAssert.assertEquals(Driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
        SoftAssert.assertAll();
    }

    @Parameters(value = {"username_invalid","password"})
    @Test(priority = 2,groups = {"invalid"})
    public void TC4_InvalidUsernameLogin(@Optional("standard_user") String UserName,@Optional("slsaalkslksa") String Password){
        SoftAssert = new SoftAssert();
        Driver.findElement(By.cssSelector("#user-name")).sendKeys(UserName);
        Driver.findElement(By.cssSelector("#password")).sendKeys(Password);
        Driver.findElement(By.cssSelector("#login-button")).click();
        SoftAssert.assertNotEquals(Driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        SoftAssert.assertEquals(Driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
        SoftAssert.assertAll();
    }

    @Parameters(value = {"username"})
    @Test(priority = 2,groups = {"invalid"})
    public void TC5_InvalidEmptyPasswordLogin(@Optional("standard_user") String UserName){
        SoftAssert = new SoftAssert();
        Driver.findElement(By.cssSelector("#user-name")).sendKeys(UserName);
        Driver.findElement(By.cssSelector("#login-button")).click();
        SoftAssert.assertNotEquals(Driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        SoftAssert.assertEquals(Driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(),
                "Epic sadface: Password is required");
        SoftAssert.assertAll();
    }

    @Parameters(value = {"password"})
    @Test(priority = 2,groups = {"invalid"})
    public void TC6_InvalidEmptyPasswordLogin(@Optional("standard_user") String Password){
        SoftAssert = new SoftAssert();
        Driver.findElement(By.cssSelector("#password")).sendKeys(Password);
        Driver.findElement(By.cssSelector("#login-button")).click();
        SoftAssert.assertNotEquals(Driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        SoftAssert.assertEquals(Driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(),
                "Epic sadface: Username is required");
        SoftAssert.assertAll();
    }


    @Parameters()
    @Test(priority = 2,groups = {"invalid"})
    public void TC7_InvalidEmptyLogin(){
        SoftAssert = new SoftAssert();
        Driver.findElement(By.cssSelector("#login-button")).click();
        SoftAssert.assertNotEquals(Driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        SoftAssert.assertEquals(Driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(),
                "Epic sadface: Username is required");
        SoftAssert.assertAll();
    }

    @Parameters()
    @Test(priority = 2,groups = {"invalid"})
    public void TC8_CheckCancelErrorMessageButton(){
        SoftAssert = new SoftAssert();
        Driver.findElement(By.cssSelector("#login-button")).click();
        Driver.findElement(By.cssSelector("#login_button_container > div > form > div.error-message-container.error > h3 > button")).click();
        SoftAssert.assertNotEquals(Driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        String DivContent = Driver.findElement(By.cssSelector("#login_button_container > div > form > div.error-message-container")).getText();
        SoftAssert.assertTrue(DivContent.isEmpty());
        SoftAssert.assertAll();
    }




    @AfterMethod(alwaysRun = true)
    public void end(){
        Driver.close();
    }
}
