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
    
    public void navigateToMyWall( String user, String myWall ) {
        
        LoginPage lp = new LoginPage( driver, av );
            
        lp.attemptLogin( user );
        
        Utility.myVerifyCurrentPage( driver, av.getTokenValue( "homePageTitle") );
        
        MyWallPage wall = new MyWallPage( driver, av, myWall );
    }
    
    public void setUp( String university ) {
        
        driver = new FirefoxDriver();
        av = new AccountValues( university );
        
    }
    
    public void tearDown() {
        driver.quit();
    }
            
}
