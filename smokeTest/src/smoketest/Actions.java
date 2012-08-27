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
        ip.isTitlePresent(driver, av.getTokenValue("loginPageTitle"));
        lp.attemptLogin( user );
    } 
    
    public void navigateToMyWall( String user ) {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToWallXPATH") );
        Utility.myVerifyCurrentPage( driver, av.getTokenValue("wallPageTitle") );  
    }
    
    public void navigateToContacts() {
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToContactsXPATH"));
        Utility.myVerifyCurrentPage(driver, av.getTokenValue("myContactsTitle"));
    } 
    
    public void goToContactWall() {
        driver.findElement(By.xpath("//*[@id='txtSearchContacts']")).sendKeys(av.getTokenValue("stdntFirstName"));
        driver.findElement(By.xpath(av.getTokenValue("btnSrchCntct"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("btnRmveContact"), "Remove Contact");
        
        driver.findElement(By.partialLinkText(av.getTokenValue("stdntFirstName"))).click();
        driver.findElement(By.xpath(av.getTokenValue("linkToContactWall"))).click();
        
        ip.isTextPresentByXPATH(driver, av.getTokenValue("textContactWallXPATH"), av.getTokenValue("textContactWall"));
    }
    
    public void verifyTopWallPost(String text) {
        ip.isElementPresentContainsTextByXPATH(driver, text );
    }
    
    public String textToWall() {
        
        WallPage wp = new WallPage( driver, av );
        String wallText = wp.textPost();
        return wallText;
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
    
    public void navigateToWorkingGroups() {
        
        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToWrkgGrpXPATH") );
        Utility.myVerifyCurrentPage( driver, av.getTokenValue("sclGrpTitle") );
    }
    
    public void addWorkingGroup() {
            
        WorkingGroup wg = new WorkingGroup(driver, av );
        wg.BuildWorkingGroup();
 
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
    
    public void navigateToCourse() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToCourseXPATH"));
        ip.isTitlePresent(driver, av.getTokenValue("coursePageTitle"));
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
