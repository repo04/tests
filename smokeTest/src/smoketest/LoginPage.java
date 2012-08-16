/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

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
            throw new IllegalStateException( "Did not navigate to correct Login page. "
                                           + "\nCurrent page: " + driver.getTitle() );
        }
    }
}

