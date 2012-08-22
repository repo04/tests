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
        wp.textPost();;
    }
     
   public void urlToWall() {
        WallPage wp = new WallPage( driver, av );
        wp.urlPost();
    }
    
    public void navigateToSocialGroups() {
        
        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToSclGrpXPATH") );
        Utility.myVerifyCurrentPage( driver, av.getTokenValue("sclGrpTitle") );
    }
    
    public String createSocialGroups() {
        
        SocialGroup sg = new SocialGroup( driver, av );
        sg.buildSocialGroup();
        
        // Returns name of Social Group
        return sg.getSclGrpName();
    }
    
    public void findSocialGroup( String s ) {
        
        driver.findElement(By.xpath(av.getTokenValue("btnFindSclGrp"))).click();
        ip.isTextPresentByXPATH( driver,  av.getTokenValue("headerAreaXPATH"), av.getTokenValue("headerTxtXPATH"));
        
        driver.findElement(By.xpath(av.getTokenValue("fieldGrpSrchXPATH"))).sendKeys(s);
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
