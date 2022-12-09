package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class NavBar extends BaseClassUI{

    @FindBy(how = How.ID, using = "nav-link-accountList")
    WebElement navSignInButton;
    @FindBy(how = How.ID, using = "nav-item-signout")
    WebElement logoutButton;
    @FindBy(how = How.ID, using = "nav-cart-count")
    WebElement navCartCount;
    @FindBy(how = How.ID, using = "twotabsearchtextbox")
    WebElement searchBox;
    @FindBy(how = How.ID, using = "nav-search-submit-button")
    WebElement submitSearchButton;
    @FindBy(how = How.ID, using = "nav-hamburger-menu")
    WebElement allCategoriesMenuButton;

    public NavBar(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public WebElement getNavSignInButton() {
        return navSignInButton;
    }

    public void clickNavSignInButton(){
        waitForElement(navSignInButton).click();
    }

    public boolean navSignInButtonIsEnabled(){
        return navSignInButton.isEnabled();
    }

    public void logout(){
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForElement(navSignInButton)).perform();
        waitForElement(logoutButton).click();
    }

    public WebElement getNavCartCountElement(){
        return waitForElement(navCartCount);
    }

    public String getNavCartCount(){
        return waitForElement(navCartCount).getText();
    }

    public void search(String productName){
        waitForElement(searchBox).sendKeys(productName);
        waitForElement(submitSearchButton).click();

    }

    public boolean isPageLoaded(){
        return waitForElement(getNavSignInButton()).isEnabled();
    }

    public void clickAllCategoriesButton(){
        waitForElement(allCategoriesMenuButton).click();
    }

    public void clickOnMenJeansCategory(){
        clickAllCategoriesButton();
        driver.findElement(By.cssSelector("#hmenu-content > ul.hmenu.hmenu-visible > li:nth-child(12) > a.hmenu-item.hmenu-compressed-btn")).click();
        driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]//*[text()=\"Men\'s Fashion\"]/./..")).click();
        driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[13]/li[3]/a")).click();
        driver.findElement(By.xpath("//*[text()='Jeans']")).click();
    }

    public boolean jeansWasSelected(){
        return driver.findElement(By.xpath("//*[text()='Jeans']")).getAttribute("class").contains("a-text-bold");
    }
}
