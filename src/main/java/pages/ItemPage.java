package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends NavBar{

    @FindBy(how = How.ID, using = "add-to-cart-button")
    WebElement addToCartButton;
    @FindBy(how = How.ID, using = "productTitle")
    WebElement itemTitle;
    @FindBy(how = How.ID, using = "attach-close_sideSheet-link")
    WebElement closePopUpButton;

    public ItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void clickAddToCartButton(){
        waitForElement(addToCartButton).click();
    }

    public String getItemTitle(){
        return waitForElement(itemTitle).getText();
    }

    public void clickClosePopUpButton(){
        waitForElement(closePopUpButton).click();
    }
    @Override
    public boolean isPageLoaded() {
        return waitForElement(addToCartButton).isEnabled();
    }
}
