package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomePage extends NavBar{

    public AmazonHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

}
