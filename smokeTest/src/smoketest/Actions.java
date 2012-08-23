package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Actions {

    AccountValues av;
    WebDriver driver;
    IsPresent ip = new IsPresent();

    // Login as user: student, teacher or PES
    public void login(String user) {

        LoginPage lp = new LoginPage(driver, av);
        ip.isTitlePresent(driver, av.getTokenValue("loginPageTitle"));
        lp.attemptLogin(user);
    }

    //Parameter is not required
    public void navigateToMyWall() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToWallXPATH"));
        Utility.myVerifyCurrentPage(driver, av.getTokenValue("wallPageTitle"));
    }

    public void textToWall() {

        WallPage wp = new WallPage(driver, av);
        wp.textPost();;
    }

    public void urlToWall() {
        WallPage wp = new WallPage(driver, av);
        wp.urlPost();
    }

    public void navigateToSocialGroups() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToSclGrpXPATH"));

        //Verify the Text as My Social & Working Groups gets the same Title
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("hdngMySclGrpTEXT"));
    }

    public String createSocialGroups() {

        SocialGroup sg = new SocialGroup(driver, av);
        sg.buildSocialGroup();

        // Returns name of Social Group
        return sg.getSclGrpName();
    }

    public void createLiveSsn(String sclGrpName) {
        LiveSession ls = new LiveSession(driver, av);
        ls.buildLiveSession(sclGrpName);
    }

    //Login with ContentAdmin and create Course
    public String createCourse() {
        Course cr = new Course(driver, av);
        cr.createCourse();

        // Returns name of Course
        return cr.getCrsName();
    }

    //Login with ContentAdmin and create Group to newly created course 
    public String createGrpCourse(String courseName) {
        Course cr = new Course(driver, av);
        cr.createGrpCourse(courseName);

        // Returns name of GroupCourse
        return cr.getGrpCrsName();
    }

    public void findSocialGroup(String s) {

        driver.findElement(By.xpath(av.getTokenValue("btnFindSclGrp"))).click();

        //Verify 'Find a Social Group' Text
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("headerTxt"));

        driver.findElement(By.xpath(av.getTokenValue("fieldGrpSrchXPATH"))).sendKeys(s);

        driver.findElement(By.xpath(av.getTokenValue("btnSrchSclGrp"))).click();

        //Verify Social Group Present or not
        ip.isElementPresentByLINK(driver, s);
    }

    public void logOut() {

        // Uses js to click on the hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToLogOut"));
        ip.isTitlePresent(driver, av.getTokenValue("loginPageTitle"));
    }

    public void setUp(String university) {

        //Initiate Firefox Driver        
        driver = new FirefoxDriver();

        //Maximise the window
        driver.manage().window().maximize();
        av = new AccountValues(university);
    }

    public void navigateToCourse() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToCourseXPATH"));
        ip.isTitlePresent(driver, av.getTokenValue("coursePageTitle"));
    }

    public void selectGrpCourse(String grpCrsName) {

        ip.isElementPresentContainsTextByXPATH(driver, grpCrsName);
        driver.findElement(By.xpath("//*[contains(text(),'" + grpCrsName + "')]")).click();
        ip.isTextPresentByCSS(driver, av.getTokenValue("lblCrsLftPnlCSS"), grpCrsName.toUpperCase());
    }

    public void createForumActivity() {
        Activity actvty = new Activity(driver, av);
        actvty.crtForumActvty();
    }

    public void createQuizActivity() {
        Activity actvty = new Activity(driver, av);
        actvty.crtQuizActvty();
    }

    public void createAllInOneAsgnmntActivity() {
        Activity actvty = new Activity(driver, av);
        actvty.crtAllInOneAsgnmntActvty();
    }

    public void createPageResource() {
        Activity actvty = new Activity(driver, av);
        actvty.createPageResource();
    }

    public String createUser(String user) {
        User usr = new User(driver, av);
        usr.createUser(user);
        return usr.getUsrName();
    }

    public void tearDown() {
        driver.quit();
    }

    public void navigateToContact() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToContactXPATH"));
        ip.isTitlePresent(driver, av.getTokenValue("contactPageTitle"));
    }

    //Enroll User as 'Teacher/Student to a Course'
    public void enrollUsrToRole_GrpCrs(String user, String grpCrs) {
        
        EnrollUser enrlUsr = new EnrollUser(driver, av);
        enrlUsr.toRole_Crs(user, grpCrs);        
    }
}