package smoketest;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    public void navigateToMyWall() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToWallXPATH"));
        Utility.myVerifyCurrentPage(driver, av.getTokenValue("wallPageTitle"));
    }

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

    public void acessLvSsnWall() {
        //Verify Live Session Panel present or not
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnleftPnlLvMtng"));
        driver.findElement(By.xpath(av.getTokenValue("btnleftPnlLvMtng"))).click();
        //Verify Navigated to Live Meeting creation page
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnCrtSsn"));
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

    public void navigateToMyCourse() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToCourseXPATH"));
        ip.isTitlePresent(driver, av.getTokenValue("coursePageTitle"));
    }

    public void navigateToMyHome() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("lnkToHomeXPATH"));
        ip.isTitlePresent(driver, av.getTokenValue("homePageTitle"));
    }

    public void navigateToContactsWall(String cntct) {

        driver.findElement(By.xpath("//*[starts-with(text(),'" + cntct + "')]")).click();
        String s = cntct.substring(0, 1).toUpperCase();
        String usrFullNm = s + cntct.substring(1) + "frstNm " + s + cntct.substring(1) + "sndNm";
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyCntctXPATH"), usrFullNm);
        driver.findElement(By.xpath("//*[contains(text(),'Wall')]")).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyHdngTxtXPATH"), usrFullNm + "`s - Wall");
    }

    public void navigateToWorkingGroups() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToWrkgGrpXPATH"));
        
        //Verify the Text as My Social/Working Groups gets the same Title
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("hdngMyWrkngGrpTEXT"));
    }

    public void navigateToMyContacts() {

        String user = LoginPage.getUser();
        String linkToContactXPATH;
        
        if(user.equalsIgnoreCase("pesAdmin"))
        {
            user = user + "   ";
        }

        // Contacts link XPATH varies across users (Admin & Tchr/Std)
        switch (user.substring(6, 10)) {

            case "stdt":
            case "tchr":
                linkToContactXPATH = av.getTokenValue("linkToCntctXPATH");
                break;

            default:
                linkToContactXPATH = av.getTokenValue("linkToCntctByAdminXPATH");
        }

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, linkToContactXPATH);
        ip.isTitlePresent(driver, av.getTokenValue("contactPageTitle"));
    }

    public void navigateToMySocialGroups() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToSclGrpXPATH"));

        //Verify the Text as My Social/Working Groups gets the same Title
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("hdngMySclGrpTEXT"));
    }

    public String createWorkingGroup() {

        WorkingGroup wg = new WorkingGroup(driver, av);
        wg.BuildWorkingGroup();
        return wg.getWrkngGrp();
    }

    public void selectGrpCourse(String grpCrsName) {

        String user = LoginPage.getUser();
        
        if(user.equalsIgnoreCase("pesAdmin"))
        {
            user = user + "   ";
        }

        //Teacher/Student can select Course but not Group Course as compared to Admin users
        switch (user.substring(6, 10)) {

            case "stdt":
            case "tchr":
                Utility.navigateToSubMenu(driver, "//*[contains(text(),'" + grpCrsName + "')]");
                break;

            default:
                ip.isElementPresentContainsTextByXPATH(driver, grpCrsName);
                driver.findElement(By.xpath("//*[contains(text(),'" + grpCrsName + "')]")).click();
                break;
        }
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

    public void deleteSocialGroup(String stdtSclGrpName) {

        ip.isElementPresentContainsTextByXPATH(driver, stdtSclGrpName);
        driver.findElement(By.xpath("//*[contains(text(),'" + stdtSclGrpName + "')]")).click();

        ip.isElementPresentByXPATH(driver, "//input[@id='sgroup_delete']");

        driver.findElement(By.xpath("//input[@id='sgroup_delete']")).click();

        //Get a handle to the open alert, prompt or confirmation
        final Alert alert = driver.switchTo().alert();

        (new WebDriverWait(driver, 60)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return alert.getText().contentEquals("Do you really want to delete this group?");
            }
        });

        //And acknowledge the alert (equivalent to clicking "OK")
        alert.accept();

        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + stdtSclGrpName + "')]")));

    }

    public void VrfyPstsAsTopNews_RcntNews(String tchrUrlWallPost, String tchrUrlCrsPost, String tchrUrlPostOnStdtWall) {
        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlWallPost);
        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlCrsPost);
        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlPostOnStdtWall);
        driver.findElement(By.linkText("link=Recent News")).click();

        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlWallPost);
        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlCrsPost);
        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlPostOnStdtWall);
    }

    public void VrfyPstsAsTopNews_RcntNews(String tchrUrlWallPost, String tchrUrlCrsPost, String tchrUrlPostOnStdtWall, String stdtUrlPostOnTchrSclGrp) {
        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlWallPost);
        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlCrsPost);
        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlPostOnStdtWall);
        ip.isElementPresentContainsTextByXPATH(driver, stdtUrlPostOnTchrSclGrp);

        driver.findElement(By.xpath("//*[contains(text(),'Recent News')]")).click();

        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlWallPost);
        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlCrsPost);
        ip.isElementPresentContainsTextByXPATH(driver, tchrUrlPostOnStdtWall);
        ip.isElementPresentContainsTextByXPATH(driver, stdtUrlPostOnTchrSclGrp);
    }

    public void navigateToActvtyRprt() {
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnLftPnlActvyRprtXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("btnLftPnlActvyRprtXPATH"))).click();
        ip.isTextPresentByCSS(driver, av.getTokenValue("hdngActvtyRprtCSS"), "Activity report");
    }

    public void verifyActivities(String frmActvyName, String quizActvtyName, String allInOneAsgnmntAvtvtyName, String pageActvtyName) {
        ip.isTextPresentByCSS(driver, av.getTokenValue("hdngActvtyRprtCSS"), "Activity report");
        ip.isElementPresentContainsTextByXPATH(driver, frmActvyName);
        ip.isElementPresentContainsTextByXPATH(driver, quizActvtyName);
        ip.isElementPresentContainsTextByXPATH(driver, allInOneAsgnmntAvtvtyName);
        ip.isElementPresentContainsTextByXPATH(driver, pageActvtyName);
    }

    public void accessWrknGrp(String wrkngGrp) {
        ip.isElementPresentContainsTextByXPATH(driver, wrkngGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + wrkngGrp + "')]")).click();
    }

    public void addMbrsToWrkngGrp(String wrkngGrp, String tchrUsrName, String stdtUsrName) {

        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnEditWrkngGrpXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("btnEditWrkngGrpXPATH"))).click();
        ip.isElementPresentContainsTextByXPATH(driver, "Manage Members");
        driver.findElement(By.xpath("//*[contains(text(),'Manage Members')]")).click();

        //ip.isTextPresentByXPATH(driver, av.getTokenValue("addMbrsWrkGrpTxtXPATH"), "My Contacts \n Users Associated to the Course");
        ip.isTextPresentByXPATH(driver, "//td[2]/h3", "Members");
        ip.isTextPresentByXPATH(driver, "//td[4]/h3", "Non Members");        

        Select select = new Select(driver.findElement(By.xpath(av.getTokenValue("slctMbrsXPATH"))));

        String tchrFullNm = tchrUsrName + "frstNm " + tchrUsrName + "sndNm(Non-editing teacher)";
        select.selectByVisibleText(tchrFullNm);
        driver.findElement(By.xpath(av.getTokenValue("lnkAddMbrXPATH"))).click();

        String stdtFullNm = stdtUsrName + "frstNm " + stdtUsrName + "sndNm(Student)";
        select.selectByVisibleText(stdtFullNm);
        driver.findElement(By.xpath(av.getTokenValue("lnkAddMbrXPATH"))).click();

        driver.findElement(By.xpath(av.getTokenValue("btnSaveMbrsXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("lblMbrsUpdtdTxtXPATH"), "The members updated for the group.");
    }

    public void verifyWrkngGrp(String wrkngGrp) {
        ip.isElementPresentContainsTextByXPATH(driver, wrkngGrp);
    }
}
