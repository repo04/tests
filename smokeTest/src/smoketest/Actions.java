package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Actions {

    AccountValues av;
    WebDriver driver;
    IsPresent ip = new IsPresent();

    public void login(String user) {

        LoginPage lp = new LoginPage(driver, av);
        ip.isTitlePresent(driver, av.getTokenValue("loginPageTitle"));
        lp.attemptLogin(user);
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

    public void createLiveSsn(String sclGrpName) {
        LiveSession ls = new LiveSession(driver, av);
        ls.buildLiveSession(sclGrpName);
    }

    public String createCourse() {
        Course cr = new Course(driver, av);
        cr.createCourse();
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
        return cr.getGrpCrsName();
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

    public void navigateToMyWall() {

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToWallXPATH"));
        Utility.myVerifyCurrentPage(driver, av.getTokenValue("wallPageTitle"));
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

    public void navigateToContactsWall(String cntct) {

        driver.findElement(By.xpath("//*[starts-with(text(),'" + cntct + "')]")).click();
        String s = cntct.substring(0, 1).toUpperCase();
        String usrFullNm = s + cntct.substring(1) + "fstNm " + s + cntct.substring(1) + "sndNm";
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyCntctXPATH"), usrFullNm);
        driver.findElement(By.xpath("//*[contains(text(),'Wall')]")).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyHdngTxtXPATH"), usrFullNm + "`s - Wall");
    }

    public void navigateToWorkingGroups() {

        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToWrkgGrpXPATH"));
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("hdngMyWrkngGrpTEXT"));
    }

    public void navigateToMyContacts() {

        String linkToContactXPATH;

        //Contacts link XPATH varies across users (Admin & Tchr/Std)
        //Checking for 'Contacts' TEXT in href
        String url = driver.findElement(By.xpath(av.getTokenValue("linkToCntctByAdminXPATH"))).getAttribute("href");
        int i = url.lastIndexOf("/");

        if ("Contacts".equalsIgnoreCase(url.substring(i + 1, i + 9))) {
            linkToContactXPATH = av.getTokenValue("linkToCntctByAdminXPATH");
        } else {
            linkToContactXPATH = av.getTokenValue("linkToCntctXPATH");
        }

        // Uses js to click on hidden element by XPATH
        Utility.navigateToSubMenu(driver, linkToContactXPATH);
        ip.isTitlePresent(driver, av.getTokenValue("contactPageTitle"));
    }

    public void navigateToMySocialGroups() {

        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToSclGrpXPATH"));
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("hdngMySclGrpTEXT"));
    }

    public String createWorkingGroup() {

        WorkingGroup wg = new WorkingGroup(driver, av);
        wg.BuildWorkingGroup();
        return wg.getWrkngGrp();
    }

    public void selectGrpCourse(String grpCrsName) {

        String user = LoginPage.getUser();

        //Teacher/Student can select Course but not Group Course as compared to Admin users
        switch (user) {

            case "pesAdmin":
            case "contentAdmin":
                ip.isElementPresentContainsTextByXPATH(driver, grpCrsName);
                driver.findElement(By.xpath("//*[contains(text(),'" + grpCrsName + "')]")).click();
                break;

            default:
                Utility.navigateToSubMenu(driver, "//*[contains(text(),'" + grpCrsName + "')]");

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

    public String createSocialGroups() {

        SocialGroup sg = new SocialGroup(driver, av);
        sg.buildSocialGroup();
        return sg.getSclGrpName();
    }

    public void findSocialGroup(String sclGrp) {

        driver.findElement(By.xpath(av.getTokenValue("btnFindSclGrp"))).click();

        //Verify 'Find a Social Group' Text
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("headerTxt"));
        driver.findElement(By.xpath(av.getTokenValue("fieldGrpSrchXPATH"))).sendKeys(sclGrp);
        driver.findElement(By.xpath(av.getTokenValue("btnSrchSclGrp"))).click();

        //Verify Social Group Present or not
        ip.isElementPresentByLINK(driver, sclGrp);
    }

    public void joinSocialGroup(String sclGrp) {
        SocialGroup sg = new SocialGroup(driver, av);
        sg.joinSocialGroup(sclGrp);
    }

    public void leaveSocialGroup(String stdtSclGrpName) {

        SocialGroup sg = new SocialGroup(driver, av);
        sg.leaveSocialGroup(stdtSclGrpName);
    }

    public void deleteSocialGroup(String stdtSclGrpName) {

        SocialGroup sg = new SocialGroup(driver, av);
        sg.deleteSocialGroup(stdtSclGrpName);
    }

    public void acessSclGrpWall(String sclGrp) {
        ip.isElementPresentContainsTextByXPATH(driver, sclGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + sclGrp + "')]")).click();
        String uprCS = sclGrp.substring(0, 1).toUpperCase();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyHdngTxtXPATH"), uprCS + sclGrp.substring(1) + " - Wall");
    }

    public void vrfyURLPstsAsTopNews_RcntNews(String... posts) {

        for (String post : posts) {
            ip.isElementPresentContainsTextByXPATH(driver, post);
        }
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkRecentNewsXPATH"));
        for (String post : posts) {
            ip.isElementPresentContainsTextByXPATH(driver, post);
        }
    }

    public void navigateToActvtyRprt() {
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnLftPnlActvyRprtXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("btnLftPnlActvyRprtXPATH"))).click();
        ip.isTextPresentByCSS(driver, av.getTokenValue("hdngActvtyRprtCSS"), "Activity report");
    }

    public void verifyActivities(String... activities) {
        ip.isTextPresentByCSS(driver, av.getTokenValue("hdngActvtyRprtCSS"), "Activity report");
        for (String activity : activities) {
            ip.isElementPresentContainsTextByXPATH(driver, activity);
        }
    }

    public void accessWrknGrp(String wrkngGrp) {
        ip.isElementPresentContainsTextByXPATH(driver, wrkngGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + wrkngGrp + "')]")).click();
    }

    public void addMbrsToWrkngGrp(String... members) {

        WorkingGroup wg = new WorkingGroup(driver, av);
        wg.addMbrsToWrkngGrp(members);
    }

    public void vrfyWrkngGrp_GglDoc(String wrkngGrp, String gglDocName) {
        ip.isElementPresentContainsTextByXPATH(driver, wrkngGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + wrkngGrp + "')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("lnkLftPnlFilesXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("lnkLftPnlFilesXPATH"))).click();
        ip.isElementPresentContainsTextByXPATH(driver, gglDocName);
    }

    public String createGoogleDoc(String wrkngGrp) {
        WorkingGroup wg = new WorkingGroup(driver, av);
        wg.createGoogleDoc(wrkngGrp);
        return wg.getGoogleDocName();
    }
}
