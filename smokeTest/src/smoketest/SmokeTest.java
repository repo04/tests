
package smoketest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class SmokeTest 
{ 
    public static void main(String[] args) {
        
        Tests t = new Tests();
        
     
            t.setUp( "guAccountProperty" );
            t.testLogin( "student" );
            t.tearDown();
//        }
//        catch( Exception ex ) {
//            System.out.println( ex );
//        }
    }
}
