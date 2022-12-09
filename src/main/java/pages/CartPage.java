package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BaseClassUI{

    //didn't use @findby because span can't be found by it
    String cartPageUrl = "https://www.amazon.com/gp/cart/view.html?ref_=nav_cart";
    private String itemQuantityCssSelector = "#a-autoid-0-announce > span.a-dropdown-prompt";
    private String productTitleXpath = "//ul/li[1]/span/a/span[1]/span/span[1]";
    private String deleteItemCssSelector = "span.a-size-small.sc-action-delete > span > input";
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public int getItemQuantity(){
        return Integer.parseInt(driver.findElement(By.cssSelector(itemQuantityCssSelector)).getAttribute("innerHTML"));
    }

    public String getProductTitle(){
        return driver.findElement(By.xpath(productTitleXpath)).getAttribute("innerHTML");
    }
    @Override
    public boolean isPageLoaded(){
        wait.until(ExpectedConditions.titleIs("Amazon.com Shopping Cart"));
        return driver.getTitle().equals("Amazon.com Shopping Cart");
    }

    public void navigateTo(){
        driver.get(cartPageUrl);
    }

    public void deleteItemFromCart(){
        driver.findElement(By.cssSelector(deleteItemCssSelector)).click();
    }
}
