import Selenuim.Selenuim;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import static Selenuim.Selenuim.getDropDownOptions;

public class Main {
    public static WebDriver chrome = new ChromeDriver();
    public static void main(String[] args){
        Selenuim driver = new Selenuim(chrome);
        System.out.println("hello automation");
        driver.openPage("https://the-internet.herokuapp.com/dropdown");
        /*findelementXpath("//input[@id='username']");
        Sendtext(By.id("username"),"tomsmith");
        Sendtext(By.cssSelector("input#password"),"SuperSecretPassword");
        buttonClick(By.className("radius"));
        String str = gettext(By.xpath("//div[@id='flash']")); */


       // String str = gettext(By.cssSelector("#table1 > tbody > tr:nth-child(1) > td:nth-child(2)"));;
        //System.out.println(str);

        //driver.getDropDownOptions(By.cssSelector("#dropdown"));
        if (true){
            System.out.println("------------pass------------");
        }
        else{
            System.out.println("------------fail------------");
        }
        System.out.println(chrome.getTitle());
        //chrome.close();
    }

}
