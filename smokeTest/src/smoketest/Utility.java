
package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utility 
{
    public Utility()
    {
        
    }
    
    public static void mySendKeys( WebElement e, String s )
    {
        e.sendKeys(s);
    }
    
    public static void myButtonClick( WebElement e )
    {
        e.click();
    }
    
    public static void myVerifyCurrentPage( WebDriver driver, String page )
    {
        if( !page.equals(driver.getTitle()) )
        {
            throw new IllegalStateException( "Did not successfuly navigate to " + page
                                           + ".  \nThe Current Page: " + driver.getTitle() );
        }
    }
    
    public static void myTearDown( WebDriver driver )
    {
        driver.quit();
    }
}
