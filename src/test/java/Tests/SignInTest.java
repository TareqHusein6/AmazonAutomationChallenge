package Tests;


import org.testng.Assert;
import org.testng.annotations.Test;


public class SignInTest extends TestBase{

    @Test(description = "Logout", priority = 40)
    public void logout(){
        amazonHomePage.logout();
        Assert.assertTrue(driver.getTitle().equals("Amazon Sign-In") && amazonSignInPage.emailInputIsEnabled()
                ,"Failed to navigate to email sign in page after logging out");
    }

}
