/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Tests 
{
    /*
     * Test verifies successful login
     * University is the name of the properties file
     * User is the role; student, teacher, pesadmin
     */
    public void testLogin( String university, String user ) throws Exception
    {
        Login login = new Login( university );
        login.attemptLogin( user );

        // Compares expected to actual page
        Utility.myVerifyCurrentPage( login.driver, login.av.getTokenValue("homePageTitle") );
        
    }
}
