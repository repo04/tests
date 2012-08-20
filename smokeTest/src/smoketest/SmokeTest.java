
package smoketest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class SmokeTest 
{ 
    public static void main(String[] args) {
        
        Tests t = new Tests();

        // Test Login as student
//        try {
//            t.setUp( "guAccountProperty" );
//            t.login( "student" );
//            t.tearDown();
//        }
//        catch( Exception ex ) {
//            System.out.println( ex );
//        }
        
        // Test navigation to wall

            t.setUp( "guAccountProperty" );
            t.login( "student" );
            t.navigateToMyWall( "student" );
            t.textToWall();
    }
}
