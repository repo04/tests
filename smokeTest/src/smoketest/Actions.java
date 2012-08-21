package smoketest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Actions {
    
    AccountValues av;
    WebDriver driver;
    
    // Login as user: student, teacher or PES
    public void login( String user ) {
        
        LoginPage lp = new LoginPage( driver, av );        
        lp.attemptLogin( user );
    } 
    
    public void navigateToMyWall( String user ) {
        
        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToWallXPATH") );
        Utility.myVerifyCurrentPage( driver, av.getTokenValue("wallPageTitle") );
              
    }
    
    public void textToWall() {
        
        WallPage wp = new WallPage( driver, av );
        wp.textPost();;
    }
    
    public void urlToWall() {
        WallPage wp = new WallPage( driver, av );
        wp.urlPost();
    }
    
    public void navigateToMySocialGroups() {
        
        // Uses js to click on hidden element by XPATH
    }
    
    public void setUp( String university ) {
        
        driver = new FirefoxDriver();
        av = new AccountValues( university );
    }
    
    public void tearDown() {
        driver.quit();
    }
}
