package Pages;

import Utilities.DataUtil;
import Utilities.LogsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final By Username = By.cssSelector("#user-name");
    private final By Password = By.cssSelector("#password");
    private final By LoginButton = By.cssSelector("#login-button");
    private final WebDriver Driver;

    public LoginPage(WebDriver driver) {
        this.Driver = driver;
    }

    public LoginPage EnterUsername(String username){
        Driver.findElement(Username).sendKeys(username);
        return this;
    }

    public LoginPage EnterPassword(String password){
        Driver.findElement(Password).sendKeys(password);
        return  this;
    }

    public void ClickLogin(){
        Driver.findElement(LoginButton).click();
    }

}
