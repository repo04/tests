package smoketest;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    /*public void textToWall() {

     WallPage wp = new WallPage(driver, av);
     wp.textPost();
     }
    
     public void urlToWall() {

     WallPage wp = new WallPage(driver, av);
     wp.urlPost();
     }*/
    public String textPost(String textPst) {

        WallPage wp = new WallPage(driver, av);
        wp.textPost(textPst);
        return wp.getTxtPost();
    }

    public String urlPost(String urlPst) {
        WallPage wp = new WallPage(driver, av);
        wp.urlPost(urlPst);
        return wp.getURLPost();
    }

    public void navigateToSocialGroups() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToSclGrpXPATH"));

        //Verify the Text as My Social/Working Groups gets the same Title
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

    public String createForumActivity() {
        Activity actvty = new Activity(driver, av);
        actvty.crtForumActvty();
        return actvty.getFrmActvyName();
    }

    public String createQuizActivity() {
        Activity actvty = new Activity(driver, av);
        actvty.crtQuizActvty();
        return actvty.getQzActvyName();
    }

    public String createAllInOneAsgnmntActivity() {
        Activity actvty = new Activity(driver, av);
        actvty.crtAllInOneAsgnmntActvty();
        return actvty.getAllInOneAsgnmntActvyName();
    }

    public String createPageResource() {
        Activity actvty = new Activity(driver, av);
        actvty.createPageResource();
        return actvty.getPageActvyName();
    }

    public String createUser(String user) {
        User usr = new User(driver, av);
        usr.createUser(user);
        return usr.getUsrName();
    }

    public void tearDown() {
        driver.quit();
    }

    public void navigateToMyContacts() {

        String user = LoginPage.getUser();
        String linkToContactXPATH;

        // Contacts link XPATH varies across users (Admin & Tchr/Std users)
        switch (user) {

            case "student":
            case "teacher":
                linkToContactXPATH = av.getTokenValue("linkToCntctXPATH");
                break;

            default:
                linkToContactXPATH = av.getTokenValue("linkToCntctByAdminXPATH");
                break;
        }

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, linkToContactXPATH);
        ip.isTitlePresent(driver, av.getTokenValue("contactPageTitle"));
    }

    //Enroll User as 'Teacher/Student to a Course'
    public void enrollUsrToRole_GrpCrs(String user, String grpCrs) {

        EnrollUser enrlUsr = new EnrollUser(driver, av);
        enrlUsr.toRole_Crs(user, grpCrs);
    }

    public void findContact(String cntct) {

        ip.isElementPresentByXPATH(driver, av.getTokenValue("lnkMyCntctXPATH"));

        driver.findElement(By.xpath(av.getTokenValue("lnkMyCntctXPATH"))).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldFndCntct"));
        driver.findElement(By.xpath(av.getTokenValue("fieldFndCntct"))).sendKeys(cntct);
        driver.findElement(By.xpath(av.getTokenValue("btnFnCntct"))).click();
        ip.isElementPresentStartsWithTextByXPATH(driver, cntct);
    }

    public void navigateToContactsWall(String cntct) {

        driver.findElement(By.xpath("//*[starts-with(text(),'" + cntct + "')]")).click();
        String s = cntct.substring(0, 1).toUpperCase();
        String usrFullNm = s + cntct.substring(1) + "frstNm " + s + cntct.substring(1) + "sndNm";
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyCntctXPATH"), usrFullNm);
        driver.findElement(By.xpath("//*[contains(text(),'Wall')]")).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyHdngTxtXPATH"), usrFullNm + "`s - Wall");
    }

    public void joinSocialGroup(String sclGrp) {
        ip.isElementPresentContainsTextByXPATH(driver, "Join Now");
        driver.findElement(By.xpath("//*[contains(text(),'Join Now')]")).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyJndSclGrpXPATH"), "Are you sure you want to join the group \"" + sclGrp + "\"?");

        //XPATH didn't work
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        String btnID = buttons.get(1).getAttribute("id");
        driver.findElement(By.xpath("//button[@id='" + btnID + "']")).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyJndSclGrpXPATH"), "You have successfully joined the group \"" + sclGrp + "\".");
        driver.findElement(By.xpath(av.getTokenValue("btnOkJnSclGrp"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyTxtJoined"), "Joined");
    }

    public void acessSclGrpWall(String sclGrp) {
        ip.isElementPresentContainsTextByXPATH(driver, sclGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + sclGrp + "')]")).click();
        String uprCS = sclGrp.substring(0, 1).toUpperCase();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyHdngTxtXPATH"), uprCS + sclGrp.substring(1) + " - Wall");
    }

    public void leaveSocialGroup(String stdtSclGrpName) {
        ip.isElementPresentContainsTextByXPATH(driver, stdtSclGrpName);
        ip.isElementPresentContainsTextByXPATH(driver, "Leave Group");
        driver.findElement(By.xpath("//*[contains(text(),'Leave Group')]")).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyRmvSclGrpXPATH"), "Are you sure you want to remove yourself from the group " + stdtSclGrpName + "?");

        //XPATH didn't work
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        
        String btnID = buttons.get(1).getAttribute("id");
        driver.findElement(By.xpath("//button[@id='" + btnID + "']")).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyRmvSclGrpXPATH"), "You have successfully left the group " + stdtSclGrpName);
        driver.findElement(By.xpath(av.getTokenValue("btnOkLvSclGrp"))).click();      
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + stdtSclGrpName + "')]")));
    }
}