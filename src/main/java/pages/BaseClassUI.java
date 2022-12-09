package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseClassUI {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private final int elementLoadTimeout = 15;

    public BaseClassUI(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, elementLoadTimeout);
    }

    public WebElement waitForElement(WebElement webElement){
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public abstract boolean isPageLoaded();
}
