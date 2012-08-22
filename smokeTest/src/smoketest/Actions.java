package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Actions {
    
    AccountValues av;
    WebDriver driver;
    IsPresent ip = new IsPresent();
    
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
        wp.textPost();
    }
     
   public void urlToWall() {
        WallPage wp = new WallPage( driver, av );
        wp.urlPost();
    }
    
    public void navigateToSocialGroups() {
        
        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToWrkgGrpXPATH") );
        Utility.myVerifyCurrentPage( driver, av.getTokenValue("sclGrpTitle") );
    }
    
    public void navigateToWorkingGroups() {
        
        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToWrkgGrpXPATH") );
        Utility.myVerifyCurrentPage( driver, av.getTokenValue("sclGrpTitle") );
    }
    
    public String createSocialGroups() {
        
        SocialGroup sg = new SocialGroup( driver, av );
        sg.buildSocialGroup();
        
        // Returns name of Social Group
        return sg.getSclGrpName();
    }
    
    public void findSocialGroup( String sclGrpName ) {
        SocialGroup sg = new SocialGroup( driver, av );
        sg.joinSocialGroup(sclGrpName);
    }
    
    public void deleteSocialGroup( String s ) {
        
    }
    
    public void createLiveSsn( String sclGrpName ) {
        
        LiveSession ls = new LiveSession( driver, av );
        ls.buildLiveSession( sclGrpName );
    }
    
    public String createCourse() {
        Course cr = new Course( driver, av );
        cr.createCourse();
        
        return cr.getCrsName();
    }
    
    public String createGrpCourse( String courseName ) {
        
        Course cr = new Course( driver, av );
        cr.createGrpCourse( courseName );
        return cr.getGrpCrsName();
    }
         
    
    public void logOut() {
        
        // Uses js to click on the hidden element by XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToLogOut") );
        Utility.myVerifyCurrentPage( driver, av.getTokenValue("loginPageTitle"));
    }
    
    public void setUp( String university ) {
        
        driver = new FirefoxDriver();
        av = new AccountValues( university );
    }
    
    public void tearDown() {
        driver.quit();
    }
}
