
package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utility 
{
    public Utility()
    {
        
    }
    
    public static void mySendKeys(WebElement e, String s ) throws Exception
    {
        try
        {
            e.sendKeys(s);
        }
        
        catch( Exception ex )
        {
            System.out.println("Failed to send keys to: " + e );
            System.out.println( "Exception: " + ex );
        }        
    }
    
    public static void myButtonClick( WebElement e, String s ) throws Exception
    {
        try
        {
            e.click();
        }
        
        catch( Exception ex )
        {
            System.out.println( "Failed to click button: " + s );
            System.out.println( "Exception: " + ex );
        }
    }
    
    // POSSIBLY HAVE A TEARDOWN FUNCTION
}
