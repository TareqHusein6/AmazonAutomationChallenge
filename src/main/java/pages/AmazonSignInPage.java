package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AmazonSignInPage extends BaseClassUI{

    @FindBy(how = How.ID, using = "ap_email")
    WebElement emailInput;
    @FindBy(how = How.ID, using = "continue")
    WebElement continueButton;
    @FindBy(how = How.ID, using = "ap_password")
    WebElement passwordInput;
    @FindBy(how = How.ID, using = "signInSubmit")
    WebElement signInButton;

    public AmazonSignInPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void setEmailInput(String email){
        waitForElement(emailInput).sendKeys(email);
    }

    public void clickContinueButton(){
        waitForElement(continueButton).click();
    }

    public void setPasswordInput(String password){
        waitForElement(passwordInput).sendKeys(password);
    }

    public boolean emailInputIsEnabled() {
        return emailInput.isEnabled();
    }

    public boolean passwordInputIsEnabled(){
        return passwordInput.isEnabled();
    }

    public void clickSignIn(){
        waitForElement(signInButton).click();
    }

    public boolean isPageLoaded(){
        return false;
    }
}
