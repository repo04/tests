package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

public class Actions extends BaseClass {
    
    Date now = new Date();

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
    public String textCommentPost(String urlCrsPost, String txtCmntOnTchrCrsPst) {
        WallPage wp = new WallPage();
        wp.textCommentPost(urlCrsPost, txtCmntOnTchrCrsPst);
        return wp.getTextCommentPost();
    }

    /**
     * Recommend URL Course Post
     *
     * @param tchrUrlCrsPost
     */
    public void recommendURLCoursePost(String tchrUrlCrsPost) {
        WallPage wp = new WallPage();
        wp.recommendURLCoursePost(tchrUrlCrsPost);
    }

    /**
     * Verify Students Post Recommendation
     */
    public void verifyPostRecommendation() {
        ip.isTextPresentByXPATH(driver, "//label[3]", "(1 People Recommend This)");
    }

    /**
     * Verify comment on Post
     *
     * @param stdtTxtCmntOnTchrCrsPost
     */
    public void verifyCommentOnPost(String stdtTxtCmntOnTchrCrsPost) {
        WallPage wp = new WallPage();
        wp.verifyCommentOnPost(stdtTxtCmntOnTchrCrsPost);
    }

    /**
     * Delete Post / Announcement
     *
     * @param deletePost
     */
    public void deletePost(String deletePost) {
        WallPage wp = new WallPage();
        wp.deletePost(deletePost);
    }

    /**
     * Create LiveSession
     *
     * @param sclGrpName
     */
    public void createLiveSession() {
        LiveSession ls = new LiveSession();
        ls.buildLiveSession();
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
     * Delete Group Course
     *
     * @param grpCrsName
     */
    public void deleteGroupCourse(String grpCrsName) {
        Course cr = new Course();
        cr.deleteGroupCourse(grpCrsName);
    }

    /**
     * Navigate to Course categories Page
     */
    public void navigateToCourseCategories() {
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlCrsLinkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlCrsLinkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlAddEditCrsXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlAddEditCrsXPATH"))).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div/h2", "Course categories");
    }

    /**
     * Take backup of course
     *
     * @param activites
     */
    public String backupCourse(String... activites) {
        Course cr = new Course();
        cr.backupCourse(activites);
        return cr.getBackupFileName();
    }

    /**
     * Restore course as new archive course
     *
     * @param activities
     */
    public void restoreAsNewArchiveCourse(String... activities) {
        Course cr = new Course();
        cr.restoreAsNewArchiveCourse(activities);
    }

    /**
     * Verify Navigated to Live Meeting creation page
     */
    public void accessLiveSessionWall() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnleftPnlLvMtng"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnleftPnlLvMtng"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnCrtSsn"));
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
     * Archive Course
     *
     * @param crsName
     */
    public void archiveCourse(String crsName) {
        Course cr = new Course();
        cr.archiveCourse(crsName);
    }

    /**
     * User logs out
     */
    public void logOut() {
        Utility.clickByJavaScript(driver, xpv.getTokenValue("linkToLogOut"));
        if (!driver.getCurrentUrl().contains(xpv.getTokenValue("loginPageURL"))) {
            Utility.illegalStateException("Current URL is not as expected.  Current URL: " + driver.getCurrentUrl());
        }
    }

    /**
     * Navigate to My Wall page
     */
    public void navigateToMyWall() {
        Utility.clickByJavaScript(driver, xpv.getTokenValue("linkToWallXPATH"));
        Utility.verifyCurrentUrl(driver, xpv.getTokenValue("myWallURL"));
    }

    /**
     * Navigates to MyCourse Page
     */
    public void navigateToMyCourse() {
        Utility.clickByJavaScript(driver, xpv.getTokenValue("linkToCourseXPATH"));
        Utility.verifyCurrentUrl(driver, xpv.getTokenValue("myCourseURL"));
    }

    /**
     * Navigate to Home Page
     */
    public void navigateToMyHome() {
        Utility.clickByJavaScript(driver, xpv.getTokenValue("lnkToHomeXPATH"));
        Utility.verifyCurrentUrl(driver, xpv.getTokenValue("homePageURL"));
    }

    /**
     * 
     */
    public void navigateToStudentSupport() {
        driver.findElement(By.xpath("//*[@id='footerlinks']/span[6]/a")).click();
        ip.isElementPresentByXPATH(driver, "//*[@id='region-main']/div/h2[1]");
    }

    /**
     * Navigate to My Contacts Wall
     *
     * @param stdtUsrName
     */
    public void navigateToContactsWall(String stdtUsrName) {
        String usrFullNmLC = stdtUsrName + " " + stdtUsrName;
        ip.isElementPresentStartsWithTextByXPATH(driver, usrFullNmLC);
        driver.findElement(By.xpath("//*[starts-with(text(),'" + usrFullNmLC + "')]")).click();
        ip.isTitlePresent(driver, usrFullNmLC + ": Public profile");
        String s = stdtUsrName.substring(0, 1).toUpperCase();
        String usrFullNmUC = null;
        usrFullNmUC = s + stdtUsrName.substring(1) + " " + s + stdtUsrName.substring(1);
        driver.findElement(By.xpath("//*[contains(text(),'Wall')]")).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("vrfyHdngTxtXPATH"), usrFullNmUC + "`s - Wall");
    }

    /**
     * Navigate to Working Groups page
     */
    public void navigateToWorkingGroups() {
        Utility.clickByJavaScript(driver, xpv.getTokenValue("linkToWrkgGrpXPATH"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngPageXPATH"), xpv.getTokenValue("hdngMyWrkngGrpTEXT"));
    }

    /**
     * Navigate to My Contacts Wall
     */
    public void navigateToMyContacts() {

        String linkToContactXPATH;

        //Contacts link XPATH varies across users (Admin's & Tchr/Stdt)
        //Checking for 'contacts' TEXT in href
        String url = driver.findElement(By.xpath(xpv.getTokenValue("linkToCntctByAdminXPATH"))).getAttribute("href");
        int i = url.lastIndexOf("/");

        if ("Contacts".equalsIgnoreCase(url.substring(i + 1, i + 9))) {
            linkToContactXPATH = xpv.getTokenValue("linkToCntctByAdminXPATH");
        } else {
            linkToContactXPATH = xpv.getTokenValue("linkToCntctXPATH");
        }

        Utility.clickByJavaScript(driver, linkToContactXPATH);
        Utility.verifyCurrentUrl(driver, xpv.getTokenValue("myContactURL"));
    }

    /**
     * Navigate To MySocialGroups Page
     */
    public void navigateToMySocialGroups() {

        Utility.clickByJavaScript(driver, xpv.getTokenValue("linkToSclGrpXPATH"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngPageXPATH"), xpv.getTokenValue("hdngMySclGrpTEXT"));
    }

    /**
     * Navigate To Grade Page
     */
    public void navigateToGrades() {
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lnkLftPnlGradeXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lnkLftPnlGradeXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngGradeXPATH"), "Grades");
    }

    /**
     * Navigate To Personal Info Page
     */
    public void navigateToMyPersonalInfo() {
        Utility.clickByJavaScript(driver, "//li[2]/ul/li[2]/a");
        ip.isTextPresentByXPATH(driver, "//h2", "Personal Information");
    }

    /**
     * Navigate to Files page
     */
    public void navigateToFiles() {
        driver.findElement(By.linkText("Files")).click();
        ip.isTextPresentByXPATH(driver, "//h2", "My Shared Files");
    }

    /**
     * Navigate to Portfolio page
     */
    public void navigateToPortfolio() {
        Utility.clickByJavaScript(driver, "//li[3]/a");
        ip.isTextPresentByXPATH(driver, "//h2", "Portfolio");
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
    public void selectGroupCourse(String grpCrsName) {

        String user = LoginPage.getUser();

        //Teacher/Student can select Course but not GroupCourse as compared to Admin users
        switch (user) {

            case "pesAdmin":
            case "contentAdmin":
                ip.isElementPresentContainsTextByXPATH(driver, grpCrsName);
                driver.findElement(By.xpath("//*[contains(text(),'" + grpCrsName + "')]")).click();
                break;

            default:
                Utility.clickByJavaScript(driver, "//*[contains(text(),'" + grpCrsName + "')]");

        }
        ip.isTextPresentByCSS(driver, xpv.getTokenValue("lblCrsLftPnlCSS"), grpCrsName.toUpperCase());
    }

    /**
     * Select & Navigate to Course page
     *
     * @param crsName
     */
    public void selectCourse(String crsName) {
        ip.isElementPresentContainsTextByXPATH(driver, crsName);
        driver.findElement(By.xpath("//*[contains(text(),'" + crsName + "')]")).click();
        //ip.isTextPresentByXPATH(driver, "//h1/a", crsName);
    }

    /**
     * Create ForumActivity
     *
     * @return
     */
    public String createForumActivity() {
        Activity actvty = new Activity();
        actvty.crtForumActvty();
        return actvty.getActivityName();
    }

    /**
     * Create Glossary activity
     *
     * @return Glossary Activity Name
     */
    public String createGlossaryActivity() {
        Activity actvty = new Activity();
        actvty.crtGlossaryActvty();
        return actvty.getGlossaryActvyName();
    }

    /**
     * Create QuizActivity
     *
     * @return
     */
    public String createQuizActivity() {
        Activity actvty = new Activity();
        actvty.crtQuizActvty();
        return actvty.getActivityName();
    }

    /**
     *
     * @return
     */
    public String createPswdQuizActivity() {
        Activity actvty = new Activity();
        actvty.crtPswdQuizActivity();
        return actvty.getActivityName();
    }

    /**
     * Create AllInOneAsgnmntActivity
     *
     * @return
     */
    public String createAllInOneAssignmentActivity() {
        Activity actvty = new Activity();
        actvty.createAllInOneAssignmentActivity();
        return actvty.getActivityName();
    }

    /**
     * Create PageResource
     *
     * @return
     */
    public String createPageResource() {
        Activity actvty = new Activity();
        actvty.createPageResource();
        return actvty.getActivityName();
    }

    /**
     * Create - Syllabus Activity
     */
    public void createSyllabusActivity() {
        Activity actvty = new Activity();
        actvty.createSyllabusActivity();
    }
    
    public void createLessonActivity() {
        Activity activity = new Activity();
        activity.createLessonActivity();
    }

    /**
     * Verify Syllabus creation
     */
    public void verifySyllabusActivity() {
        ip.isTextPresentByXPATH(driver, "//li[3]/a", "Syllabus");
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
        return usr.getUserName();
    }

    /**
     * Delete Users
     *
     * @param tchrUsr
     * @param stdtUsr
     */
    public void deleteUsers(String tchrUsr, String stdtUsr) {
        User usr = new User();
        usr.deleteUsers(tchrUsr, stdtUsr);
    }

    /**
     * Enroll User as 'Teacher/Student' to 'GroupCourse'
     *
     * @param user
     * @param grpCrs
     */
    public void enrollUserToRole_GroupCourse(String user, String grpCrs) {
        EnrollUser enrlUsr = new EnrollUser();
        enrlUsr.toRole_Course(user, grpCrs);
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
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("lnkMyCntctXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("lnkMyCntctXPATH"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldFndCntct"));
        driver.findElement(By.xpath(xpv.getTokenValue("fieldFndCntct"))).sendKeys(cntct);
        driver.findElement(By.xpath(xpv.getTokenValue("btnFnCntct"))).click();
        ip.isElementPresentStartsWithTextByXPATH(driver, cntct);
    }

    /**
     * Navigate to All Instructors/Students page
     *
     * @param user
     */
    public void navigateToAllInstructorsStudentsPage(String user) {
        if (user.equalsIgnoreCase("teacher")) {
            driver.findElement(By.xpath("(//*[contains(text(),'More...')])")).click();
            ip.isElementPresentByLINK(driver, "Email All Instructors");
        } else {
            driver.findElement(By.xpath("(//*[contains(text(),'More...')])[2]")).click();
            ip.isElementPresentByLINK(driver, "Email All Students");
        }
    }

    /**
     * Add user as contact
     *
     * @param user
     */
    public void addUserAsContact(String user) {
        Contact c = new Contact();
        c.addUserAsContact(user);
    }

    /**
     * Navigate to respective group wall
     *
     * @param sclGrpName
     */
    public void navigateToGroupWall(String grpName) {
        Utility.optionalClickByLINK(driver, xpv.getTokenValue("btnShwMreRslts"), grpName);
        driver.findElement(By.xpath("//*[contains(text(),'" + grpName + "')]")).click();
        ip.isTitlePresent(driver, grpName + " - Wall");
    }

    /**
     * Verify post exits on Social Group wall
     *
     * @param stdtUrlPostOnTchrSclGrp
     */
    public void verifyPostOnSocialGroupWall(String stdtUrlPostOnTchrSclGrp) {
        SocialGroup sg = new SocialGroup();
        sg.verifyPostOnSocialGroupWall(stdtUrlPostOnTchrSclGrp);
    }

    /**
     * Create Social Group
     *
     * @return
     */
    public String createSocialGroup() {
        SocialGroup sg = new SocialGroup();
        sg.buildSocialGroup();
        return sg.getSocialGroupName();
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

        Utility.clickByJavaScript(driver, xpv.getTokenValue("linkRecentNewsXPATH"));

        for (String post : posts) {
            post.isEmpty();
            ip.isElementPresentContainsTextByXPATH(driver, post);
        }
    }

    /**
     * Navigate to Activity Report page
     */
    public void navigateToActivityReport() {
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("btnLftPnlActvyRprtXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("btnLftPnlActvyRprtXPATH"))).click();
        ip.isTextPresentByCSS(driver, xpv.getTokenValue("hdngActvtyRprtCSS"), "Activity report");
    }

    /**
     * Navigate to Settings page
     */
    public void navigateToSettings() {
        Utility.clickByJavaScript(driver, "//div[2]/nav/ul/li[3]/ul/li/a");
        switch (LoginPage.getUser()) {
            case "contentAdmin":
                ip.isTextPresentByXPATH(driver, "//div[4]/div/h2",
                        Utility.getFullName(pv.getTokenValue("ctntAdminUserName")));
                break;
            case "pesAdmin":
                ip.isTextPresentByXPATH(driver, "//div[4]/div/h2",
                        Utility.getFullName(pv.getTokenValue("pesUserName")));
                break;
            //Teacher/Student
            default:
                ip.isTextPresentByXPATH(driver, "//h2",
                        Utility.getFullName(LoginPage.getUser()));
        }
    }

    /**
     * Verify Settings page specific to user role
     */
    public void verifySettings() {
        Settings settings = new Settings();
        settings.verifySettings();
    }

    /**
     * User verifies PES posts on Course Wall
     *
     * @param courseposts
     */
    public void verifyCoursePost(String... courseposts) {
        int i = 0;
        for (String coursepost : courseposts) {
            ip.isElementPresentContainsTextByXPATH(driver, coursepost);
            i++;
            if (i == 3) {
                new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[2]/div/div[4]/label/a/label")));
            } else if (i > 3) {
                ip.isTextPresentByXPATH(driver, "//li/div/div[2]/div", "Announcement from Student Support");
                ip.isTextPresentByXPATH(driver, "//li/div/div[2]/div[3]", coursepost);
            }
        }
    }

    /**
     * Verify 'n' number of Activities
     *
     * @param activities
     */
    public void verifyActivities(String... activities) {
        ip.isTextPresentByCSS(driver, xpv.getTokenValue("hdngActvtyRprtCSS"), "Activity report");
        for (String activity : activities) {
            ip.isElementPresentContainsTextByXPATH(driver, activity);
        }
    }

    /**
     * Delete all Activities
     *
     * @param frmActvyName
     * @param quizActvtyName
     * @param allInOneAsgnmntActvtyName
     * @param pageActvtyName
     */
    public void deleteActivites(String frmActvyName, String quizActvtyName, String allInOneAsgnmntActvtyName, String pageActvtyName) {
        Activity actvty = new Activity();
        actvty.deleteActivites(frmActvyName, quizActvtyName, allInOneAsgnmntActvtyName, pageActvtyName);
    }

    /**
     * Add True/False question to Quiz Activity
     *
     * @param quizActvtyName
     */
    public void addQuizQuestion(String quizActvtyName) {
        Activity actvty = new Activity();
        actvty.addQuizQuestion(quizActvtyName);
    }

    /**
     * User attempt to 'True/False' question in Quiz Assignment
     *
     * @param quizActvtyName
     */
    public void submitQuiz(String quizActvtyName, String password) {
        Activity actvty = new Activity();
        actvty.submitQuiz(quizActvtyName, password);
    }

    /**
     *
     * @param pswdQzName
     */
    public void generateQuizPassword(String pswdQzName) {
        Activity actvty = new Activity();
        actvty.generateQuizPassword(pswdQzName);
    }

    /**
     * Submit Assignment
     *
     * @param allInOneAsgnmntAvtvtyName
     */
    public void submitAssignment(String allInOneAsgnmntAvtvtyName) {
        Activity actvty = new Activity();
        actvty.submitAssignment(allInOneAsgnmntAvtvtyName);
    }

    /**
     * Grade Assignment
     *
     * @param quizActvtyName
     */
    public void gradeAssignment(String allInOneAsgnmntAvtvtyName) {
        Activity actvty = new Activity();
        actvty.gradeAssignment(allInOneAsgnmntAvtvtyName);
    }

    /**
     * Verify Assignment Grade
     *
     * @param allInOneAsgnmntAvtvtyName
     */
    public void verifyAssignmentGrade(String allInOneAsgnmntAvtvtyName) {
        Activity actvty = new Activity();
        actvty.verifyAssignmentGrade(allInOneAsgnmntAvtvtyName);
    }

    /**
     * Allow Assignment to be resubmitted
     *
     * @param allInOneAsgnmntAvtvtyName
     */
    public void allowResubmitAssignment(String allInOneAsgnmntAvtvtyName, String stdtUsrName) {
        Activity actvty = new Activity();
        actvty.allowResubmitAssignment(allInOneAsgnmntAvtvtyName, stdtUsrName);
    }

    /**
     * Navigate to WorkingGroup's Wall
     *
     * @param wrkngGrp
     */
    public void accessWorkingGroup(String wrkngGrp) {
        //Show all WorkingGroups
        Utility.optionalClickByLINK(driver, xpv.getTokenValue("btnShwMreRslts"), wrkngGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + wrkngGrp + "')]")).click();
    }

    /**
     * PesAdmin adds 'n' number of member's to Working Group
     *
     * @param members
     */
    public void addMembersToWorkingGroup(String... members) {

        WorkingGroup wg = new WorkingGroup();
        wg.addMembersToWorkingGroup(members);
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
     * Delete Working Group
     *
     * @param wrkngGrp
     */
    public void deleteWorkingGroup(String wrkngGrp) {
        WorkingGroup wg = new WorkingGroup();
        wg.deleteWorkingGroup(wrkngGrp);
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

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lnkLftPnlFilesXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lnkLftPnlFilesXPATH"))).click();
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

    /**
     * Create Note on specific Wall
     *
     * @param wallType
     * @return
     */
    public String createNote(String wallType) {
        Note nt = new Note();
        nt.createNote(wallType);
        return nt.getNoteName();
    }

    /**
     * Verify Note Sorting
     *
     * @param profileNote
     */
    public void verifyNoteSorting(String profileNote) {
        Note nt = new Note();
        nt.verifyNoteSorting(profileNote);
    }

    /**
     * Verify Resources
     */
    public void verifyResources() {
        Resources rs = new Resources();
        rs.verifyResources();
    }

    /**
     * Verify Footers
     */
    public void verifyFooters() {
        Footers ft = new Footers();
        ft.verifyFooters();
    }

    /**
     * Verify Resume
     */
    public void verifyResume() {
        switch (program) {
            case "usc-mat":
                new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("Resume")));
                ip.isElementPresentByLINK(driver, "Resume");
                driver.findElement(By.linkText("Resume")).click();
                ip.isTextPresentByXPATH(driver, "//h2", "Resume");
                break;
            default:
                new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("Resume")));
        }
    }

    /**
     * Verify Personal Information
     */
    public void verifyPersonalInfo() {
        Profile pf = new Profile();
        pf.verifyPersonalInfo();
    }

    /**
     * Delete Note
     *
     * @param profileNote
     */
    public void deleteNote(String profileNote) {
        Note nt = new Note();
        nt.deleteNote(profileNote);
    }

    /**
     * Upload files of multiple format(pdf, pptx, doc)
     *
     * @param files
     */
    public void uploadFiles(String... files) {
        File fl = new File();
        fl.uploadFiles(files);
    }

    /**
     * Verify files in Course
     *
     * @param files
     */
    public void verifyFilesInCourse(String... files) {
        File fl = new File();
        fl.verifyFilesInCourse(files);
    }

    /**
     * Verify files in Portfolio
     *
     * @param files
     */
    public void verifyFilesInPortfolio(String... files) {
        File fl = new File();
        fl.verifyFilesInPortfolio(files);
    }

    /**
     *
     * @param doc
     * @param pptx
     * @param pdf
     */
    public void deleteFiles(String... files) {
        File fl = new File();
        fl.deleteFiles(files);
    }

    /**
     * Verify feedback window
     */
    public void verifyFeedbackWindow() {
        Feedback fb = new Feedback();
        fb.verifyFeedbackWindow();
    }

    /**
     * Verify Help Window on Home Page
     */
    public void verifyHelpWindow() {
        Help hlp = new Help();
        hlp.verifyHelpWindow();
    }

    /**
     * Upload video
     */
    public void testUploadVideo() {
        ip.isElementClickableByXpath(driver, "//li[2]/a", 60);
        driver.findElement(By.linkText("Personal Information")).click();
        ip.isElementClickableByXpath(driver, "//p/a", 60);
        driver.findElement(By.linkText("Change Picture")).click();
        driver.switchTo().frame("_iframe-ksubox");
        ip.isTextPresentByXPATH(driver, "//div[@id='upload_btn_container']/div[2]", "Choose how would you like to upload a new profile image");
        ip.isElementClickableByXpath(driver, "//div[4]/form/input", 60);
        Utility.clickByJavaScript(driver, "//div[4]/form/input");
        Utility.actionBuilderClick(driver, "//div[4]/form/input");
        driver.findElement(By.cssSelector("object#uploader")).click();
        ip.isElementClickableByXpath(driver, "//div[6]/form/input", 60);
        Utility.clickByJavaScript(driver, "//div[6]/form/input");
        ip.isTextPresentByXPATH(driver, "//body/div[3]/div[2]/div", "Choose how would you like to upload a new profile image");
    }

    /**
     * Navigate to Content Wall Page
     *
     * @param activityName
     */
    public void navigateToContentPage(String activityName) {
        ip.isElementPresentContainsTextByXPATH(driver, activityName);
        driver.findElement(By.xpath("//*[contains(text(),'" + activityName + "')]")).click();
        ip.isElementPresentByLINK(driver, "Browse by alphabet");
    }

    /**
     * Student create Glossary entry
     *
     * @param glossaryName
     * @return glossaryEntryName
     */
    public String createGlossaryEntry(String glossaryName) {
        Activity actvty = new Activity();
        actvty.createGlossaryEntry(glossaryName);
        return actvty.getGlossaryEntryName();
    }

    /**
     *
     * @param glossaryName
     * @return glossaryCategoryName
     */
    public String createGlossaryCategory(String glossaryName) {
        Activity actvty = new Activity();
        actvty.createGlossaryCategory(glossaryName);
        return actvty.getGlossaryCategoryName();
    }

    /**
     * Edit Glossary Activity
     *
     * @param glossaryName
     * @param stdtGlossaryEntryName
     * @param glossatyCategoryName
     * @param tchrGlossaryEntryName
     */
    public void editGlossaryEntry(String glossaryName, String stdtGlossaryEntryName, String glossatyCategoryName, String tchrGlossaryEntryName) {
        Activity actvty = new Activity();
        actvty.editGlossaryEntry(glossaryName, stdtGlossaryEntryName, glossatyCategoryName, tchrGlossaryEntryName);
    }

    /**
     *
     */
    public void testStudentSupport() {
        StudentSupport ss = new StudentSupport();
        ss.verifyStudentSupport();
    }
    
    public void testStudentSupportMobileAppURL() {
        StudentSupport ss = new StudentSupport();
        ss.verifyStudentSupportMobileAppURL();
    }
    
    public String currentDateTime() {
        String time = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        return time;
    }
}
