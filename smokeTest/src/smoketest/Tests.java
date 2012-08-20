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
    
    public void login( String user ) {
        
        LoginPage lp = new LoginPage( driver, av );
        
        lp.attemptLogin( user );
    } 
    
    public void navigateToMyWall( String user ) {
        
        LoginPage lp = new LoginPage( driver, av );
            
        lp.attemptLogin( user );
        
        // Verifies login is successful
        Utility.myVerifyCurrentPage( driver, av.getTokenValue( "homePageTitle") );
        
        // Uses js to click on hidden element by element XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue( "linkToWallXPATH") );
        
        Utility.myVerifyCurrentPage( driver, av.getTokenValue("wallPageTitle") );
              
    }
    
    public void textToWall() {
        
        WallPage wp = new WallPage( driver, av );
        wp.textPost();;
    }
    
    
    public void setUp( String university ) {
        
        driver = new FirefoxDriver();
        av = new AccountValues( university );
        
    }
    
    public void tearDown() {
        driver.quit();
    }
            
}
