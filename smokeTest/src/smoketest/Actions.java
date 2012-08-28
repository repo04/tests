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
    
    public String textToWall() {
        
        WallPage wp = new WallPage( driver, av );
        String wallText = wp.textPost();
        return wallText;
    }
  
    public void urlToWall() {
        
        WallPage wp = new WallPage( driver, av );
        wp.urlPost();
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
    
    public void accessLvSsnWall() {
        
        // Verifies if Live Session Panel is Present or Not
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnleftPnlLvMtng"));
        driver.findElement(By.xpath(av.getTokenValue("btnleftPnlLvMtng"))).click();
        //Verify Navigated to Live Meeting creation page
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnCrtSsn"));
    }
    
    public String createGrpCourse( String courseName ) {
        
        Course cr = new Course( driver, av );
        cr.createGrpCourse( courseName );
        return cr.getGrpCrsName();
    }
    
    public void navigateToMyWall()  {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToWallXPATH") );
        ip.isTitlePresent(driver, av.getTokenValue("wallPageTitle"));
    }
    
    public void navigateToMyCourse() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToCourseXPATH"));
        ip.isTitlePresent(driver, av.getTokenValue("crsPageTitle"));
    }
    
    public void navigateToMyHome() {
        
        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("lnkToHomeXPATH"));
        ip.isTitlePresent(driver, av.getTokenValue("homePageTitle"));
    }
    
    public void navigateToContactsWall() {
        
        driver.findElement(By.xpath("//*[@id='txtSearchContacts']")).sendKeys(av.getTokenValue("stdntFirstName"));
        driver.findElement(By.xpath(av.getTokenValue("btnSrchCntct"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("btnRmveContact"), "Remove Contact");
        
        driver.findElement(By.partialLinkText(av.getTokenValue("stdntFirstName"))).click();
        driver.findElement(By.xpath(av.getTokenValue("linkToContactWall"))).click();
        
        ip.isTextPresentByXPATH(driver, av.getTokenValue("textContactWallXPATH"), av.getTokenValue("textContactWall"));
    }

    public void navigateToMySocialGroups() {
        
        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToSclGrpXPATH") );
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("hdngMySclGrpTEXT"));
    }
    
    public void createWorkingGroup() {
        
        WorkingGroup wg = new WorkingGroup( driver, av);
        wg.BuildWorkingGroup();
    }
    
        
    public void navigateToWorkingGroups() {
        
        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToWrkgGrpXPATH") );
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("hdngMyWrkngGrpTEXT"));
    }


    

    
    public void verifyTopWallPost(String text) {
        ip.isElementPresentContainsTextByXPATH(driver, text );
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
    

    
    
    
    
    
    public String createForumActivity() {
        Activity activity = new Activity(driver, av);
        activity.crtForumActvty();
        return activity.getFrmActvyName();
    }

    public String createQuizActivity() {
        Activity activity = new Activity(driver, av);
        activity.crtQuizActvty();
        return activity.getQzActvyName();
    }

    public String createAllInOneAsgnmntActivity() {
        Activity activity = new Activity(driver, av);
        activity.crtAllInOneAsgnmntActvty();
        return activity.getAllInOneAsgnmntActvyName();
    }
    
    public String createPageResource() {
        Activity activity = new Activity(driver, av);
        activity.createPageResource();
        return activity.getPageActvyName();
    }    
    
    
    
    public void logOut() {
        
        // Uses js to click on the hidden element by XPATH
        Utility.navigateToSubMenu( driver, av.getTokenValue("linkToLogOut") );
        ip.isTitlePresent( driver, av.getTokenValue("loginPageTitle"));
    }
    
    public void setUp( String university ) {
        
        driver = new FirefoxDriver();
        av = new AccountValues( university );
    }
    
    public void tearDown() {
        driver.quit();
    }
}
