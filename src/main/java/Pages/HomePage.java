package Pages;

import Selenuim.Selenuim;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.Logs;

import javax.swing.*;
import javax.swing.text.Utilities;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private final By PricesLabels = By.xpath("//div[contains(@class,'inventory_item_price')]");
    private final By AddButtons = By.xpath("//button[contains(@data-test,'add')]");
    private final By RemoveButtons = By.xpath("//button[contains(@data-test,'remove')]");
    private final By SortDropDown = By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select");
    private final int H2Lindex = 3;
    private final int L2Hindex = 2;
    private    List<WebElement> AllAddButtons = new ArrayList<WebElement>();
    private    List<WebElement> AllRemoveButtons = new ArrayList<WebElement>();
    private    List<WebElement> AllPricesLabel = new ArrayList<WebElement>();
    private WebDriver Driver;


    public HomePage(WebDriver driver) {
        this.Driver = driver;
    }
    public HomePage Add2CartAllElements(){

        AllAddButtons = Driver.findElements(AddButtons);

        try {

           for (WebElement button: AllAddButtons){
                       button.click();
           }

        } catch(Exception e){

        }
        return  this;
    }

    public HomePage RemoveAllElements(){
        Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        Add2CartAllElements();
        AllRemoveButtons = Driver.findElements(RemoveButtons);
        try {
            for (WebElement button : AllRemoveButtons){
                button.click();
            }

        } catch(Exception e){

        }
        return  this;
    }

    public int CheckNumberOfElements(){
        Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        try {
            String num = Driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
            System.out.println("its= "+ num);
            return Integer.parseInt(num);
        }catch (Exception e){
            return  0;
        }
    }

    public int  SortElements(String str){
        Float _1stPrice,CurrentPrice;
        if (str.toUpperCase().equals("H2L")){
            Selenuim.getDropDownOptions(Driver,SortDropDown,H2Lindex);
            AllPricesLabel = Driver.findElements(PricesLabels);
            _1stPrice = Float.parseFloat(AllPricesLabel.get(0).getText().replace("$"," "));
            for (WebElement Price : AllPricesLabel){
                CurrentPrice = Float.parseFloat(Price.getText().replace("$"," "));
                if ( CurrentPrice > _1stPrice ){
                    return  0;
                }
            }
            return  1;
        } else if (str.toUpperCase().equals("L2H")) {
            Selenuim.getDropDownOptions(Driver,SortDropDown,L2Hindex);
            AllPricesLabel = Driver.findElements(PricesLabels);
            _1stPrice = Float.parseFloat(AllPricesLabel.get(0).getText().replace("$"," "));
            for (WebElement Price : AllPricesLabel){
                CurrentPrice = Float.parseFloat(Price.getText().replace("$"," "));
                if ( CurrentPrice < _1stPrice ){
                    return  0;
                }
            }
            return  1;
        }
        return 5;
    }

    public HomePage checkName(By element){
        return  this;
    }
}
