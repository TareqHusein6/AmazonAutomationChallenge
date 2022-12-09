package Tests;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AmazonHomePage;
import pages.AmazonSignInPage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected String email;
    protected String password;
    protected String url = "http://www.amazon.com";
    protected WebDriver driver;
    protected WebDriverWait wait;
    private final int elementLoadTimeout = 15;
    protected AmazonSignInPage amazonSignInPage;
    protected AmazonHomePage amazonHomePage;

    @BeforeTest(description = "Get credentials from properties file")
    public void getCredentials() throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String configFilePath = rootPath + "config.properties";
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(configFilePath));
        email = appProps.getProperty("email");
        password = appProps.getProperty("password");
    }

    @BeforeTest(description = "init")
    public void init(){
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        this.wait = new WebDriverWait(driver, elementLoadTimeout);
    }

    @Test(description = "Navigate to Sign in page", priority = 10)
    public void navigateToSignInPage(){
        amazonHomePage = new AmazonHomePage(driver);
        amazonHomePage.clickNavSignInButton();
        amazonSignInPage = new AmazonSignInPage(driver);
        Assert.assertTrue(driver.getTitle().equals("Amazon Sign-In") && amazonSignInPage.emailInputIsEnabled()
                ,"Failed to navigate to email sign in page");
    }

    @Test(description = "Enter Email Address", priority = 20)
    public void enterEmailAddress(){
        amazonSignInPage = new AmazonSignInPage(driver);
        amazonSignInPage.setEmailInput(email);
        amazonSignInPage.clickContinueButton();
        Assert.assertTrue(amazonSignInPage.passwordInputIsEnabled(),
                "Failed to navigate to password sign in page");
    }

    @Test(description = "Enter password", priority = 30)
    public void enterPassword(){
        amazonSignInPage.setPasswordInput(password);
        amazonSignInPage.clickSignIn();
        wait.until(ExpectedConditions.titleIs("Amazon.com. Spend less. Smile more."));//singing in usually takes more than 1 second, so we need to wait
        Assert.assertTrue(amazonHomePage.navSignInButtonIsEnabled(),
                "Failed to navigate to home page after signing in");
    }

    @AfterClass(alwaysRun = true)
    public void wrapUp(){
        driver.quit();
    }
}
