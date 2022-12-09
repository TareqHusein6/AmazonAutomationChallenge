package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ItemPage;

public class CartTest extends TestBase{

    private ItemPage itemPage;
    private CartPage cartPage;
    private String selectedItemName;

    @Test(description = "Navigate to product needed",priority = 40)
    public void navigateToProductNeeded(){
        String searchWord = "Pillow";
        amazonHomePage.search(searchWord);
        wait.until(ExpectedConditions.titleIs("Amazon.com : "+searchWord));
    }

    @Test(description = "Choose item from page",priority = 50)
    public void chooseItemFromPage(){
        String xpathItemImageLink = "//div[2]/div/div/div/div/div/div/div[1]/span/a";
        driver.findElement(By.xpath(xpathItemImageLink)).click();
        itemPage = new ItemPage(driver);
        Assert.assertTrue(itemPage.isPageLoaded(),"Item page not loaded");
    }

    @Test(description = "Add Item to cart",priority = 60)
    public void addItemToCart(){
        Assert.assertTrue(itemPage.getNavCartCount().equals("0"),"Cart is not empty");
        selectedItemName = itemPage.getItemTitle();
        itemPage.clickAddToCartButton();
        itemPage.clickClosePopUpButton();
        wait.until(ExpectedConditions.textToBePresentInElement(itemPage.getNavCartCountElement(),"1"));
    }

    @Test(description = "Validate cart page",priority = 70)
    public void validateCartPage(){
        cartPage = new CartPage(driver);
        cartPage.navigateTo();
        Assert.assertTrue(cartPage.isPageLoaded(),"Cart page not loaded");
        Assert.assertTrue(cartPage.getProductTitle().equals(selectedItemName)
                        && cartPage.getItemQuantity() == 1,
                "Cart not showing the correct product.");
    }

    @Test(description = "deleteItemFromCart", priority = 80)
    public void deleteItemFromCart(){
        cartPage.deleteItemFromCart();
        Assert.assertTrue(itemPage.getNavCartCount().equals("0"),"Item was not deleted");
    }
}
