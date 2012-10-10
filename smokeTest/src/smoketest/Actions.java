package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

public class Actions extends BaseClass {

    /**
     * Login
     *
     * @param user
     */
    public void login(String user) {

        LoginPage lp = new LoginPage();
        lp.attemptLogin(user);
    }

    /**
     * Post Text on Wall
     *
     * @param textPst
     * @return
     */
    public String textPost(String textPst) {

        WallPage wp = new WallPage();
        wp.textPost(textPst);
        return wp.getTxtPost();
    }

    /**
     * Post URL on Wall
     *
     * @param urlPst
     * @return
     */
    public String urlPost(String urlPst) {
        WallPage wp = new WallPage();
        wp.urlPost(urlPst);
        return wp.getURLPost();
    }

    /**
     * Add comment on Teacher's course post
     *
     * @param urlCrsPost
     * @param txtCmntOnTchrCrsPst
     * @return
     */
    public String textCmntPost(String urlCrsPost, String txtCmntOnTchrCrsPst) {
        WallPage wp = new WallPage();
        wp.textCmntPost(urlCrsPost, txtCmntOnTchrCrsPst);
        return wp.getTextCmntPost();
    }

    /**
     * Create LiveSession in
     *
     * @param sclGrpName
     */
    public void createLiveSsn(String sclGrpName) {
        LiveSession ls = new LiveSession();
        ls.buildLiveSession(sclGrpName);
    }

    /**
     * Create Course
     *
     * @return
     */
    public String createCourse() {
        Course cr = new Course();
        cr.createCourse();
        return cr.getCrsName();
    }

    /**
     * Verify Navigated to Live Meeting creation page
     */
    public void accessLvSsnWall() {
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnleftPnlLvMtng"));
        driver.findElement(By.xpath(av.getTokenValue("btnleftPnlLvMtng"))).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnCrtSsn"));
    }

    /**
     * Create GroupCourse to
     *
     * @param courseName
     * @return
     */
    public String createGrpCourse(String courseName) {
        Course cr = new Course();
        cr.createGrpCourse(courseName);
        return cr.getGrpCrsName();
    }

    /**
     * User logs out
     */
    public void logOut() {
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToLogOut"));
        ip.isTitlePresent(driver, av.getTokenValue("loginPageTitle"));
    }

    /**
     * Navigate to MyWall page
     */
    public void navigateToMyWall() {
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToWallXPATH"));
        ip.isTitlePresent(driver, av.getTokenValue("wallPageTitle"));
    }

    /**
     * Navigates to MyCourse Page
     */
    public void navigateToMyCourse() {
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToCourseXPATH"));
        ip.isTitlePresent(driver, av.getTokenValue("crsPageTitle"));
    }

    /**
     * Navigate to Home Page
     */
    public void navigateToMyHome() {
        Utility.navigateToSubMenu(driver, av.getTokenValue("lnkToHomeXPATH"));
        ip.isTitlePresent(driver, av.getTokenValue("homePageTitle"));
    }

    /**
     * Navigate to My Contacts Wall
     *
     * @param cntct
     */
    public void navigateToContactsWall(String cntct) {
        driver.findElement(By.xpath("//*[starts-with(text(),'" + cntct + "')]")).click();
        String s = cntct.substring(0, 1).toUpperCase();
        String usrFullNm = s + cntct.substring(1) + "fstNm " + s + cntct.substring(1) + "sndNm";
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyCntctXPATH"), usrFullNm);
        driver.findElement(By.xpath("//*[contains(text(),'Wall')]")).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyHdngTxtXPATH"), usrFullNm + "`s - Wall");
    }

    /**
     * Navigate to Working Groups page
     */
    public void navigateToWorkingGroups() {
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToWrkgGrpXPATH"));
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("hdngMyWrkngGrpTEXT"));
    }

    /**
     * Navigate to My Contacts Wall
     */
    public void navigateToMyContacts() {

        String linkToContactXPATH;

        //Contacts link XPATH varies across users (Admin's & Tchr/Stdt)
        //Checking for 'contacts' TEXT in href
        String url = driver.findElement(By.xpath(av.getTokenValue("linkToCntctByAdminXPATH"))).getAttribute("href");
        int i = url.lastIndexOf("/");

        if ("Contacts".equalsIgnoreCase(url.substring(i + 1, i + 9))) {
            linkToContactXPATH = av.getTokenValue("linkToCntctByAdminXPATH");
        } else {
            linkToContactXPATH = av.getTokenValue("linkToCntctXPATH");
        }

        Utility.navigateToSubMenu(driver, linkToContactXPATH);
        ip.isTitlePresent(driver, av.getTokenValue("contactPageTitle"));
    }

    /**
     * Navigate To MySocialGroups Page
     */
    public void navigateToMySocialGroups() {

        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToSclGrpXPATH"));
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("hdngMySclGrpTEXT"));
    }

    /**
     * PesAdmin creates Working Group
     *
     * @return
     */
    public String createWorkingGroup() {
        WorkingGroup wg = new WorkingGroup();
        wg.buildWorkingGroup();
        return wg.getWrkngGrp();
    }

    /**
     * Select GroupCourse
     *
     * @param grpCrsName
     */
    public void selectGrpCourse(String grpCrsName) {

        String user = LoginPage.getUser();

        //Teacher/Student can select Course but not GroupCourse as compared to Admin users
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

    /**
     * Create ForumActivity
     *
     * @return
     */
    public String createForumActivity() {
        Activity actvty = new Activity();
        actvty.crtForumActvty();
        return actvty.getFrmActvyName();
    }

    /**
     * Create QuizActivity
     *
     * @return
     */
    public String createQuizActivity() {
        Activity actvty = new Activity();
        actvty.crtQuizActvty();
        return actvty.getQzActvyName();
    }

    /**
     * Create AllInOneAsgnmntActivity
     *
     * @return
     */
    public String createAllInOneAsgnmntActivity() {
        Activity actvty = new Activity();
        actvty.crtAllInOneAsgnmntActvty();
        return actvty.getAllInOneAsgnmntActvyName();
    }

    /**
     * Create PageResource
     *
     * @return
     */
    public String createPageResource() {
        Activity actvty = new Activity();
        actvty.createPageResource();
        return actvty.getPageActvyName();
    }

    /**
     * Create User
     *
     * @param user
     * @return
     */
    public String createUser(String user) {
        User usr = new User();
        usr.createUser(user);
        return usr.getUsrName();
    }

    /**
     * Enroll User as 'Teacher/Student' to 'GroupCourse'
     *
     * @param user
     * @param grpCrs
     */
    public void enrollUsrToRole_GrpCrs(String user, String grpCrs) {
        EnrollUser enrlUsr = new EnrollUser();
        enrlUsr.toRole_Crs(user, grpCrs);
    }

    /**
     *
     * @param tchrUsrName
     * @param grpCrsName
     */
    public void unenrolUsers(String stdtUsrName, String tchrUsrName) {
        EnrollUser enrlUsr = new EnrollUser();
        enrlUsr.frmCourse(stdtUsrName, tchrUsrName);
    }

    /**
     * Find Contact
     *
     * @param cntct
     */
    public void findContact(String cntct) {
        ip.isElementPresentByXPATH(driver, av.getTokenValue("lnkMyCntctXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("lnkMyCntctXPATH"))).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldFndCntct"));
        driver.findElement(By.xpath(av.getTokenValue("fieldFndCntct"))).sendKeys(cntct);
        driver.findElement(By.xpath(av.getTokenValue("btnFnCntct"))).click();
        ip.isElementPresentStartsWithTextByXPATH(driver, cntct);
    }

    /**
     * Create Social Group
     *
     * @return
     */
    public String createSocialGroup() {
        SocialGroup sg = new SocialGroup();
        sg.buildSocialGroup();
        return sg.getSclGrpName();
    }

    /**
     * Find Social Group
     *
     * @param sclGrp
     */
    public void findSocialGroup(String sclGrp) {
        SocialGroup sg = new SocialGroup();
        sg.findSocialGroup(sclGrp);
    }

    /**
     * Join Social Group
     *
     * @param sclGrp
     */
    public void joinSocialGroup(String sclGrp) {
        SocialGroup sg = new SocialGroup();
        sg.joinSocialGroup(sclGrp);
    }

    /**
     * Leave Social Group
     *
     * @param stdtSclGrpName
     */
    public void leaveSocialGroup(String stdtSclGrpName) {
        SocialGroup sg = new SocialGroup();
        sg.leaveSocialGroup(stdtSclGrpName);
    }

    /**
     * Delete Social Group
     *
     * @param stdtSclGrpName
     */
    public void deleteSocialGroup(String stdtSclGrpName) {

        SocialGroup sg = new SocialGroup();
        sg.deleteSocialGroup(stdtSclGrpName);
    }

    /**
     * Navigate to SocialGroup's Wall
     *
     * @param sclGrp
     */
    public void accessSclGrpWall(String sclGrp) {
        Utility.optionalClickByLINK(driver, av.getTokenValue("btnShwMreRslts"), sclGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + sclGrp + "')]")).click();
        String uprCS = sclGrp.substring(0, 1).toUpperCase();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyHdngTxtXPATH"), uprCS + sclGrp.substring(1) + " - Wall");
    }

    /**
     * Verify 'n' number of posts on Top/Recent News Link Throw exception incase
     * post is empty
     *
     * @param posts
     */
    public void vrfyURLPstsAsTop_RcntNews(String... posts) {

        for (String post : posts) {
            post.isEmpty();
            ip.isElementPresentContainsTextByXPATH(driver, post);
        }

        Utility.navigateToSubMenu(driver, av.getTokenValue("linkRecentNewsXPATH"));

        for (String post : posts) {
            post.isEmpty();
            ip.isElementPresentContainsTextByXPATH(driver, post);
        }
    }

    /**
     * Navigate to Activity Report page
     */
    public void navigateToActvtyRprt() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnLftPnlActvyRprtXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("btnLftPnlActvyRprtXPATH"))).click();
        ip.isTextPresentByCSS(driver, av.getTokenValue("hdngActvtyRprtCSS"), "Activity report");
    }

    /**
     * Verify 'n' number of Activities
     *
     * @param activities
     */
    public void verifyActivities(String... activities) {
        ip.isTextPresentByCSS(driver, av.getTokenValue("hdngActvtyRprtCSS"), "Activity report");
        for (String activity : activities) {
            ip.isElementPresentContainsTextByXPATH(driver, activity);
        }
    }
    
    /**
     * Student submits True/False quiz
     * 
     * @param quizActvtyName 
     */
    public void submitQuiz(String quizActvtyName) {
        Activity actvty = new Activity();
        actvty.submitQuiz(quizActvtyName);
    }

    /**
     * Navigate to WorkingGroup's Wall
     *
     * @param wrkngGrp
     */
    public void accessWrknGrp(String wrkngGrp) {
        //Show all WorkingGroups
        Utility.optionalClickByLINK(driver, av.getTokenValue("btnShwMreRslts"), wrkngGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + wrkngGrp + "')]")).click();
    }

    /**
     * PesAdmin adds 'n' number of member's to Working Group
     *
     * @param members
     */
    public void addMbrsToWrkngGrp(String... members) {

        WorkingGroup wg = new WorkingGroup();
        wg.addMbrsToWrkngGrp(members);
    }

    /**
     * PesAdmin removes 'n' number of member's to Working Group
     *
     * @param members
     */
    public void rmvMbrsFrmWrkngGrp(String... members) {

        WorkingGroup wg = new WorkingGroup();
        wg.rmvMbrsFrmWrkngGrp(members);
    }

    /**
     * Verify WorkingGroup & Google Doc is visible to user or not
     *
     * @param wrkngGrp
     * @param gglDocName
     */
    public void vrfyWrkngGrp_GglDoc(String wrkngGrp, String gglDocName) {
        ip.isElementPresentContainsTextByXPATH(driver, wrkngGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + wrkngGrp + "')]")).click();
        
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lnkLftPnlFilesXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lnkLftPnlFilesXPATH"))).click();
        ip.isElementPresentContainsTextByXPATH(driver, gglDocName);
    }

    /**
     * Create Google Doc
     *
     * @param wrkngGrp
     * @return
     */
    public String createGoogleDoc(String wrkngGrp) {
        WorkingGroup wg = new WorkingGroup();
        wg.createGoogleDoc(wrkngGrp);
        return wg.getGoogleDocName();
    }
}
