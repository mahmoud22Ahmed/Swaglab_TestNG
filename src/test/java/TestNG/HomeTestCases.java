package TestNG;

import Pages.HomePage;
import Pages.LoginPage;
import Utilities.DataUtil;
import Utilities.LogsUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class HomeTestCases {
    SoftAssert softAssert = new SoftAssert();
    WebDriver Driver ;
    private org.testng.asserts.SoftAssert SoftAssert;
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
        Driver.get(Utilities.DataUtil.getPropertyValue("Environment","LOGIN_URL"));
        Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void TC1_CheckAddAllElements() throws IOException {

        new LoginPage(Driver).EnterUsername(Utilities.DataUtil.getJsonData("ValidLoginData","username"))
                .EnterPassword(Utilities.DataUtil.getJsonData("ValidLoginData","password"))
                .ClickLogin();
        softAssert.assertEquals(Driver.getCurrentUrl(),Utilities.DataUtil.getPropertyValue("Environment","HOME_URL"));
        int ret =new HomePage(Driver).Add2CartAllElements().CheckNumberOfElements();
        softAssert.assertEquals(ret,6);
        softAssert.assertAll();
        ;
    }

    @Test
    public void TC2_CheckRemoveAllElements() throws IOException {
        new LoginPage(Driver).EnterUsername(Utilities.DataUtil.getJsonData("ValidLoginData","username"))
                .EnterPassword(Utilities.DataUtil.getJsonData("ValidLoginData","password"))
                .ClickLogin();
        Assert.assertEquals(Driver.getCurrentUrl(),Utilities.DataUtil.getPropertyValue("Environment","HOME_URL"));
        int ret = new HomePage(Driver).RemoveAllElements().CheckNumberOfElements();
        softAssert.assertEquals(ret,0);
        softAssert.assertAll();
        ;
    }

    @Test
    public void TC3_SortH2L() throws IOException {
        new LoginPage(Driver).EnterUsername(Utilities.DataUtil.getJsonData("ValidLoginData","username"))
                .EnterPassword(Utilities.DataUtil.getJsonData("ValidLoginData","password"))
                .ClickLogin();
        Assert.assertEquals(Driver.getCurrentUrl(),Utilities.DataUtil.getPropertyValue("Environment","HOME_URL"));
        int ret = new HomePage(Driver).SortElements("H2L");
        softAssert.assertEquals(ret,1);
        softAssert.assertAll();
    }

    @Test
    public void TC4_SortL2H() throws IOException {
        new LoginPage(Driver).EnterUsername(Utilities.DataUtil.getJsonData("ValidLoginData","username"))
                .EnterPassword(Utilities.DataUtil.getJsonData("ValidLoginData","password"))
                .ClickLogin();
        Assert.assertEquals(Driver.getCurrentUrl(),Utilities.DataUtil.getPropertyValue("Environment","HOME_URL"));
        int ret = new HomePage(Driver).SortElements("L2H");
        softAssert.assertEquals(ret,1);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void end(){
      // Driver.close();
    }
}
