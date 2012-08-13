package simpleTest;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginPage 
{
    // Verifies the loaded page is successful LoginPage 
    public LoginPage( WebDriver driver, String pageTitle )
    {        
        // Grabs the page title from the browser and compares to the expected from Property file
        if( !pageTitle.equals(driver.getTitle()) )
        {
            throw new IllegalStateException( "Did not navigate to correct page. "
                                           + "Current page: " + driver.getTitle() );
        }
    }
}
