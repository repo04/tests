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
    public void testLogin( String university, String user ) throws Exception
    {
        Login l = new Login( university );
        
        l.attemptLogin(user);
        
        
        
        
//        WebDriver driver = new FirefoxDriver();
//        AccountValues v = new AccountValues( university );
//        HomePage h = new HomePage( driver, v.getTokenValue("homePageTitle") );
//        
//        try 
//        {
//            Login l = new Login( university, user );
//            
//            
//        } 
//        catch (Exception ex) 
//        {
//            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
