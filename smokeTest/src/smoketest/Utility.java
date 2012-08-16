
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
//    public static void mySendKeys(WebElement e, String s ) throws Exception
//    {
//        try
//        {
//            e.sendKeys(s);
//        }
//        
//        catch( Exception ex )
//        {
//            System.out.println("Failed to send keys to: " + e );
//            System.out.println( "\nException: " + ex );
//        }        
//    }
//    
//    public static void myButtonClick( WebElement e, String s ) //throws Exception
//    {
//        try
//        {
//            e.click();
//        }
//        
//        catch( Exception ex )
//        {
//            System.out.println( "Failed to click button: " + s );
//            System.out.println( "\nException: " + ex );
//        }
//    }
    
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
    // POSSIBLY HAVE A TEARDOWN FUNCTION
}
