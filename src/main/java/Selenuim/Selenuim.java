package Selenuim;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Selenuim {
    public static WebDriver Driver;

    public Selenuim(WebDriver driver) {
        this.Driver = driver;;
    }

    public  static void openPage(String URL){
        Driver.get(URL);
    }
    public static  void findelementXpath(String Xpath){
        By locetor = By.xpath(Xpath);
        Driver.findElement(locetor);
    }
    public static void Sendtext(By locetor,String input){
        Driver.findElement(locetor).sendKeys(input);
    }
    public  static  void buttonClick(By locetor){
        Driver.findElement(locetor).click();
    }
    public static  String gettext(By locetor){
        return Driver.findElement(locetor).getText();
    }
    public static void getDropDownOptions(WebDriver driver,By locetor,int index){
        WebElement Dropdown = driver.findElement(locetor);
        Select drop = new Select(Dropdown);
        List<WebElement> options = drop.getOptions();
        options.get(index).click();
    }
}
