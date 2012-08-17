
package smoketest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class SmokeTest 
{
    public static void main(String[] args) throws Exception 
    {
        Tests t = new Tests();
        try
        {
            t.testLogin( "guAccountProperty", "student" );
        }
        catch(Exception ex)
        {
                System.out.println( "ERROR: " + ex );
        }
       
        try
        {
            t.testLogin("guAccountProperty", "teacher");
        }
        catch( Exception ex )
        {
            System.out.println( "ERROR: " + ex);
        }
      // t.testTextWallPost("guAccountProperty");
       // t.testURLWallPost("guAccountProperty");

        System.exit( 0 );
    }
}
