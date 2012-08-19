/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Tests {
    
    AccountValues av;
    WebDriver driver;
    
    public void testLogin( String user ) {
        
        LoginPage lp = new LoginPage( driver, av );
        
        lp.attemptLogin( user );
    } 
    
    public void navigateToMyWall( String university, String user ) {
        
        LoginPage lp = new LoginPage( driver, av );
            
        lp.attemptLogin( user );
        
        Utility.myVerifyCurrentPage( driver, av.getTokenValue( "homePageTitle") );
    }
    
    public void setUp( String university ) {
        
        driver = new FirefoxDriver();
        av = new AccountValues( university );
        
    }
    
    public void tearDown() {
        driver.quit();
    }
            
}
