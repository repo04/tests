package com.lms.tests.smoketest;

import com.lms.tests.runThrghTestNG.BaseClass;
import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
     * @param textPost
     * @return
     */
    public String textPost(String textPost) {
        WallPage wp = new WallPage();
        wp.textPost(textPost);
        return wp.getTextPost();
    }

    /**
     * Post URL on Wall
     *
     * @param urlPost
     * @return
     */
    public String urlPost(String urlPost) {
        WallPage wp = new WallPage();
        wp.urlPost(urlPost);
        return wp.getURLPost();
    }

    /**
     * Add comment on Teacher's course post
     *
     * @param urlCoursePost
     * @param textCommentOnTeacherCoursePost
     * @return
     */
    public String textCommentPost(String urlCoursePost, String textCommentOnTeacherCoursePost) {
        WallPage wp = new WallPage();
        wp.textCommentPost(urlCoursePost, textCommentOnTeacherCoursePost);
        return wp.getTextCommentPost();
    }

    /**
     * Recommend URL Course Post
     *
     * @param teacherUrlCoursePost
     */
    public void recommendURLCoursePost(String teacherUrlCoursePost) {
        WallPage wp = new WallPage();
        wp.recommendURLCoursePost(teacherUrlCoursePost);
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
     * @param studentTextCommentOnTeacherCoursePost
     */
    public void verifyCommentOnPost(String studentTextCommentOnTeacherCoursePost) {
        WallPage wp = new WallPage();
        wp.verifyCommentOnPost(studentTextCommentOnTeacherCoursePost);
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
        return cr.getCourseName();
    }

    /**
     * @return CourseName
     */
    public String getCourseShortName() {
        Course cr = new Course();
        return cr.getCourseShortName();
    }
    
    /**
     * Delete Group Course
     *
     * @param groupCourseName
     */
    public void deleteGroupCourse(String groupCourseName) {
        Course cr = new Course();
        cr.deleteGroupCourse(groupCourseName);
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
     * @param activities
     */
    public String backupCourse(String... activities) {
        Course cr = new Course();
        cr.backupCourse(activities);
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
        cr.createGroupCourse(courseName);
        return cr.getGroupCourseName();
    }

    /**
     * Archive Course
     *
     * @param courseName
     */
    public void archiveCourse(String courseName) {
        Course cr = new Course();
        cr.archiveCourse(courseName);
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
    public void navigateToSupport(String role) {
        driver.findElement(By.xpath("//*[@id='footerlinks']/span[6]/a")).click();
        if (role.equalsIgnoreCase("Teacher")) {
            ip.isTextPresentByXPATH(driver, "//h2", "Faculty Support");
        } else {
            ip.isTextPresentByXPATH(driver, "//h2", "Student Support");
        }
    }

    /**
     * Navigate to My Contacts Wall
     *
     * @param studentUserName
     */
    public void navigateToContactsWall(String studentUserName) {
        String usrFullNmLC = studentUserName + " " + studentUserName;
        ip.isElementPresentStartsWithTextByXPATH(driver, usrFullNmLC);
        driver.findElement(By.xpath("//*[starts-with(text(),'" + usrFullNmLC + "')]")).click();
        ip.isTitlePresent(driver, usrFullNmLC + ": Public profile");
        String s = studentUserName.substring(0, 1).toUpperCase();
        String usrFullNmUC = null;
        usrFullNmUC = s + studentUserName.substring(1) + " " + s + studentUserName.substring(1);
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
    public void navigateToMyPersonalInformation() {
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
        return wg.getWorkingGroupName();
    }

    /**
     * Select GroupCourse
     *
     * @param groupCourseName
     */
    public void selectGroupCourse(String groupCourseName) {
        String user = LoginPage.getUser();

        //Teacher/Student can select Course but not GroupCourse as compared to Admin users
        switch (user) {

            case "pesAdmin":
            case "contentAdmin":
                ip.isElementPresentContainsTextByXPATH(driver, groupCourseName);
                driver.findElement(By.xpath("//*[contains(text(),'" + groupCourseName + "')]")).click();
                break;

            default:
                Utility.clickByJavaScript(driver, "//*[contains(text(),'" + groupCourseName + "')]");

        }
        ip.isTextPresentByCSS(driver, xpv.getTokenValue("lblCrsLftPnlCSS"), groupCourseName.toUpperCase());
    }

    /**
     * Select & Navigate to Course page
     *
     * @param courseName
     */
    public void selectCourse(String courseName) {
        ip.isElementPresentContainsTextByXPATH(driver, courseName);
        driver.findElement(By.xpath("//*[contains(text(),'" + courseName + "')]")).click();
        ip.isTextPresentByXPATH(driver, "//h1/a", courseName);
    }

    /**
     * Create ForumActivity
     *
     * @return
     */
    public String createForumActivity() {
        Activity activity = new Activity();
        activity.createForumActivity();
        return activity.getActivityName();
    }

    /**
     * Create Glossary activity
     *
     * @return Glossary Activity Name
     */
    public String createGlossaryActivity() {
        Activity activity = new Activity();
        activity.createGlossaryActivity();
        return activity.getActivityName();
    }

    /**
     * Create QuizActivity
     *
     * @return
     */
    public String createQuizActivity() {
        Activity activity = new Activity();
        activity.createQuizActivity();
        return activity.getActivityName();
    }

    /**
     *
     * @return
     */
    public String createPasswordQuizActivity() {
        Activity activity = new Activity();
        activity.createPasswordQuizActivity();
        return activity.getActivityName();
    }

    /**
     * Create AllInOneAsgnmntActivity
     *
     * @return
     */
    public String createAllInOneAssignmentActivity() {
        Activity activity = new Activity();
        activity.createAllInOneAssignmentActivity();
        return activity.getActivityName();
    }

    /**
     * Create PageResource
     *
     * @return
     */
    public String createPageResource() {
        Activity activity = new Activity();
        activity.createPageResource();
        return activity.getActivityName();
    }

    /**
     * Create - Syllabus Activity
     */
    public void createSyllabusActivity() {
        Activity activity = new Activity();
        activity.createSyllabusActivity();
    }

    /**
     *
     */
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
     * @param teacherUser
     * @param studentUser
     */
    public void deleteUsers(String teacherUser, String studentUser) {
        User usr = new User();
        usr.deleteUsers(teacherUser, studentUser);
    }

    /**
     * Enroll User as 'Teacher/Student' to 'GroupCourse'
     *
     * @param user
     * @param groupCourse
     */
    public void enrollUserToRole_GroupCourse(String user, String groupCourse) {
        EnrollUser enrlUsr = new EnrollUser();
        enrlUsr.toRole_Course(user, groupCourse);
    }

    /**
     *
     * @param teacherUserName
     * @param groupCourseName
     */
    public void unenrolUsers(String studentUserName, String teacherUserName) {
        EnrollUser enrlUsr = new EnrollUser();
        enrlUsr.fromCourse(studentUserName, teacherUserName);
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
     * User confirm contact request
     *
     * @param user
     */
    public void confirmContactRequest(String user) {
        Contact c = new Contact();
        c.confirmContactRequest(user);
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
     * @param studentUrlPostOnTeacherSocialGroup
     */
    public void verifyPostOnSocialGroupWall(String studentUrlPostOnTeacherSocialGroup) {
        SocialGroup sg = new SocialGroup();
        sg.verifyPostOnSocialGroupWall(studentUrlPostOnTeacherSocialGroup);
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
     * @param studentSocialGroupName
     */
    public void leaveSocialGroup(String studentSocialGroupName) {
        SocialGroup sg = new SocialGroup();
        sg.leaveSocialGroup(studentSocialGroupName);
    }

    /**
     * Delete Social Group
     *
     * @param studentSocialGroupName
     */
    public void deleteSocialGroup(String studentSocialGroupName) {

        SocialGroup sg = new SocialGroup();
        sg.deleteSocialGroup(studentSocialGroupName);
    }

    /**
     * Verify 'n' number of posts on Top/Recent News Link Throw exception incase
     * post is empty
     *
     * @param posts
     */
    public void verifyURLPostsAsTop_RecentNews(String... posts) {

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
                        Utility.getFullName(ldv.getTokenValue("ctntAdminUserName")));
                break;
            case "pesAdmin":
                ip.isTextPresentByXPATH(driver, "//div[4]/div/h2",
                        Utility.getFullName(ldv.getTokenValue("pesUserName")));
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
                ip.isTextPresentByXPATH(driver, "//li/div/div[2]/div", "Announcement from Support");
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
     * @param forumActivityName
     * @param quizActivityName
     * @param allInOneAssignmentActivityName
     * @param pageActivityName
     */
    public void deleteActivities(String forumActivityName, String quizActivityName, String allInOneAssignmentActivityName, String pageActivityName) {
        Activity actvty = new Activity();
        actvty.deleteActivities(forumActivityName, quizActivityName, allInOneAssignmentActivityName, pageActivityName);
    }

    /**
     * Add True/False question to Quiz Activity
     *
     * @param quizActivityName
     */
    public void addQuizQuestion(String quizActivityName) {
        Activity actvty = new Activity();
        actvty.addQuizQuestion(quizActivityName);
    }

    /**
     * User attempt to 'True/False' question in Quiz Assignment
     *
     * @param quizActivityName
     */
    public void submitQuiz(String quizActivityName, String password) {
        Activity actvty = new Activity();
        actvty.submitQuiz(quizActivityName, password);
    }

    /**
     *
     * @param passwordQuizName
     */
    public void generateQuizPassword(String passwordQuizName) {
        Activity actvty = new Activity();
        actvty.generateQuizPassword(passwordQuizName);
    }

    /**
     * Submit Assignment
     *
     * @param allInOneAssignmentActivityName
     */
    public void submitAssignment(String allInOneAssignmentActivityName) {
        Activity actvty = new Activity();
        actvty.submitAssignment(allInOneAssignmentActivityName);
    }

    /**
     * Grade Assignment
     *
     * @param quizActivityName
     */
    public void gradeAssignment(String allInOneAssignmentActivityName) {
        Activity actvty = new Activity();
        actvty.gradeAssignment(allInOneAssignmentActivityName);
    }

    /**
     * Verify Assignment Grade
     *
     * @param allInOneAssignmentActivityName
     */
    public void verifyAssignmentGrade(String allInOneAssignmentActivityName) {
        Activity actvty = new Activity();
        actvty.verifyAssignmentGrade(allInOneAssignmentActivityName);
    }

    /**
     * Allow Assignment to be resubmitted
     *
     * @param allInOneAssignmentActivityName
     */
    public void allowResubmitAssignment(String allInOneAssignmentActivityName, String studentUserName) {
        Activity actvty = new Activity();
        actvty.allowResubmitAssignment(allInOneAssignmentActivityName, studentUserName);
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
    public void removeMembersFromWorkingGroup(String... members) {

        WorkingGroup wg = new WorkingGroup();
        wg.removeMembersFromWorkingGroup(members);
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
     * @param googleDocumentName
     */
    public void verifyWorkingGroup_GoogleDoc(String wrkngGrp, String googleDocumentName) {
        ip.isElementPresentContainsTextByXPATH(driver, wrkngGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + wrkngGrp + "')]")).click();

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lnkLftPnlFilesXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lnkLftPnlFilesXPATH"))).click();
        ip.isElementPresentContainsTextByXPATH(driver, googleDocumentName);
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
    public void verifyPersonalInformation(String role) {
        Profile pf = new Profile();
        pf.verifyPersonalInformation(role);
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
        return actvty.getActivityName();
    }

    /**
     *
     * @param glossaryName
     * @return glossaryCategoryName
     */
    public String createGlossaryCategory(String glossaryName) {
        Activity actvty = new Activity();
        actvty.createGlossaryCategory(glossaryName);
        return actvty.getActivityName();
    }

    /**
     * Edit Glossary Activity
     *
     * @param glossaryName
     * @param studentGlossaryEntryName
     * @param glossatyCategoryName
     * @param teacherGlossaryEntryName
     */
    public void editGlossaryEntry(String glossaryName, String studentGlossaryEntryName, String glossatyCategoryName, String teacherGlossaryEntryName) {
        Activity actvty = new Activity();
        actvty.editGlossaryEntry(glossaryName, studentGlossaryEntryName, glossatyCategoryName, teacherGlossaryEntryName);
    }

    /**
     *
     * Verifies Support page
     */
    public void testSupportPage(String role) {
        Support ss = new Support();
        ss.verifySupport(role);
     }

    /**
     * Verifies Mobile section on Support page
     */
    public void testSupportMobileAppURL(String role) {
        Support ss = new Support();
        ss.verifySupportMobileAppURL(role);
    }

    public String currentDateTime() {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
    }

    /**
     * User verify Calendar on Home Page
     */
    public void verifyCalendar() {
        //Calendar XPath verifies across user role
        switch (LoginPage.getUser()) {
            case "pesAdmin":
            case "contentAdmin":
                ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtCalendarAdminXPATH"), "Calendar");
                break;
            default:
                ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtCalendarXPATH"), "Calendar");
        }
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("lnkCalendarMonthXPATH"));
    }

    /**
     * Navigate To System Compatibility page
     */
    public void navigateToSystemCompatibility() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("btnCheckYourSysCompatibiltyXPATH"))));
        String HandleBefore = driver.getWindowHandle();
        driver.findElement(By.xpath(xpv.getTokenValue("btnCheckYourSysCompatibiltyXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sysCompPageTitleXPATH"), "Home > System Compatibility");
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().contains("about:blank")) {
                driver.close();
            }
        }
        driver.switchTo().window(HandleBefore);
    }

    /**
     * Verify System Compatibility Page BreadCrumb and Introduction Part
     */
    public void systemCompatibilityUIVerify() {
        SystemCompatibility syscomptble = new SystemCompatibility();
        syscomptble.breadCrumbandIntroduction();
    }

    /**
     * Verifies content of Step 1: Component Compatibility Check
     */
    public void systemCompatibilityComponentCompatibilityUIVerify() {
        SystemCompatibility syscomptble = new SystemCompatibility();
        syscomptble.componentCompatibilityUIVerify();
    }

    /**
     * Verifies content of Step 2: Meeting Connection Diagnostic
     */
    public void systemCompatibilityMeetingConnectionDiagnosticUIVerify() {
        SystemCompatibility syscomptble = new SystemCompatibility();
        syscomptble.meetingConnectionDiagnosticUIVerify();
    }

    /**
     * Verifies content of Mobile Application section
     */
    public void systemCompatibilityMobileApplicationsUIVerify() {
        SystemCompatibility syscomptble = new SystemCompatibility();
        syscomptble.mobileApplicationsUIVerify();
    }

    /**
     * Verifies the faq section (Questions Answers) also verify the related
     * browser page opens with download option while clicking on browser
     * compatible icons
     */
    public void systemCompatibilityVerifyQuestionsAndBrowserCompatibleIcons() {
        SystemCompatibility syscomptble = new SystemCompatibility();
        syscomptble.systemCompatibilityVerifyQuestionsAndBrowserCompatibleIcons();
    }

    /**
     * Verify the number of "back to top" and "more info" links available and
     * are enabled or not on System Compatibility Page
     */
    public void systemCompatibilityVerifyBackToTopAndMoreInfoLinks() {
        SystemCompatibility syscomptble = new SystemCompatibility();
        syscomptble.backToTopAndMoreInfoLinks();
    }

    /**
     * Verify content and functionality of ExpressUploader
     */
    public void systemCompatibilityExpressUploader() {
        SystemCompatibility syscomptble = new SystemCompatibility();
        syscomptble.expressUploader();
    }

    /**
     * Verify System Compatibility Page - mobile Support Section UI
     */
    public void systemCompatibilityMobileSupportSectionUIVerify() {
        SystemCompatibility syscomptble = new SystemCompatibility();
        syscomptble.mobileSupportSectionUIVerify();
    }

    /**
     * Navigate To 2tor Site Administrator
     */
    public void navigateTo2torSiteAdministrator() {
        Utility.clickByJavaScript(driver, "//nav/ul/li/a");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("link2torAdminXPATH"), "2tor Site administration");
        driver.findElement(By.xpath(xpv.getTokenValue("link2torAdminXPATH"))).click();
    }

    /**
     * Navigate To Video Tutorials page
     */
    public void navigateToVideoTutorials() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("videoTutorialXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("videoTutorialXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("uploadVideo1XPATH"), "Upload");
    }

    /**
     * Navigate To Student Support Message page
     */
    public void navigateToStudentSupportMessage() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("supportMessageXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("supportMessageXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportLoginMessagePageHeaderXPATH"), "Student Support");
    }

    /**
     * Navigate To Login Message page
     */
    public void navigateToLoginMessage() {
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("loginMessageXPATH"), 30);
        driver.findElement(By.xpath(xpv.getTokenValue("loginMessageXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportLoginMessagePageHeaderXPATH"), "Login Message", 20);
    }

    /**
     * Navigate To Sticky Notes page
     */
    public void navigateToUserStickyNotes() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("userStickyNotesXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("userStickyNotesXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("userStickyNotesPageHeaderXPATH"), "User Sticky Notes", 30);
    }

    /**
     * Navigate To Report Course Roster Page
     */
    public void navigateToCourseRosters() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("courseRosterXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("courseRosterXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("courseRosterPageHeaderXPATH"), "Course Rosters");
    }

    /**
     * Navigate To Deleted Live Session Page
     */
    public void navigateToDeletedLiveSession() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("deletedLiveSessionXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("deletedLiveSessionXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("deletedLivesessionPageHeaderXPATH"), "Deleted Live Session Recording");
    }

    /**
     * Navigate To Email Not In Domain Page
     */
    public void navigateToEmailNotInDomain() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("emailNotInDomainXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("emailNotInDomainXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("emailNotInDomainPageHeadrXPATH"), "Total students without university domain email address");
    }

    /**
     * Navigate To Student Engagement Report
     */
    public void navigateToStudentEngagementReport() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("studentEngagementReportXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("studentEngagementReportXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentEngagementReportPageHeaderXPATH"), "Student Engagement Report");
    }

    /**
     * Verify 2tor Administrative Block -Email Not In Domain UI
     */
    public void verifySiteAdminReportEmailNotInDomainPage() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifySiteAdminReportEmailNotInDomainPage();
    }
    
    /**
     * Verify pes admin is able to see the related sections to the courses in the section drop down
     */
    public void verifySectionDropdownCourseRostersPage(String courseShortName, String groupCourse) {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifySectionDropdownCourseRostersPage(courseShortName, groupCourse);
    }

    //Commented -- As looping executes very slow on Sauce Lab
    /**
     * Verify 2tor Administrative Block - University Domain Email IDs are not
     * present in "Email Not In Domain" list
     */
    /*public void verifyUniversityDomainNotPresentInEmailNotInDomainList() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifyUniversityDomainNotPresentInEmailNotInDomainList();
    }*/

    /**
     * Verify Site Administration Section
     */
    public void verifySiteAdministrationSection() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifySiteAdministrationSection();
    }

    /**
     * Verify 2tor Administrative Block -Student Support Settings
     */
    public void verifySiteAdminStudentSupportSection() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifySiteAdminStudentSupportSection();
    }

    /**
     * Verifies the UI and functionality of Video Tutorials
     */
    public void verifySiteAdminStudentSupportVideoTutorialsPage() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifySiteAdminStudentSupportVideoTutorialsPage();
    }

    /**
     * Verify 2tor Administrative Block - Support Message
     */
    public void verifySiteAdminStudentSupportMessagePage() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifySiteAdminStudentSupportMessagePage();
    }

    /**
     * Verifies the UI of login message page
     */
    public void verifySiteAdminStudentSupportLoginMessagePage() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifySiteAdminStudentSupportLoginMessagePage();
    }

    /**
     * Verify 2tor Administrative Block -Report Settings
     */
    public void verifySiteAdministrationReportSection() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifySiteAdministrationReportSection();
    }

    /**
     * Verify pes admin can not access Services Report
     */
    public void verifyGetAccessDeniedForStudentServicesAndConfigurationReports() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifyGetAccessDeniedForStudentServicesAndConfigurationReports();
    }

    /**
     * Verify 2tor Administrative Block -Course Roster UI Verify
     */
    public void verifySiteAdminReportCourseRostersPage() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifySiteAdminReportCourseRostersPage();
    }

    /**
     * Verify 2tor Administrative Block -Deleted Live Session Section
     */
    public void verifySiteAdminReportDeletedLiveSessionPage() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifySiteAdminReportDeletedLiveSessionPage();
    }
    
    /**
     * Verify 2tor Administrative Block -Student Engagement Report UI Verify
     */
    public void verifySiteAdminReportStudentEngagementReportPage() {
        AdministrationBlock ablock = new AdministrationBlock();
        ablock.verifySiteAdminReportStudentEngagementReportPage();
    }

    //Following functional test methods affect all system users - so currently we are skipping this
    /**
     * Verify 2tor Administrative Block - Set faculty Login Message
     */
    /*
     public void adminBlockSetFacultyLoginMessage() {
     AdministrationBlock ablock=new AdministrationBlock();
     ablock. teacherSupportSettingsLoginMessage();
     } */
    
    //The below method affects all system users - so currently we are skipping this
    /**
     * Verify 2tor Administrative Block -Sticky Notes Post Text
     */
    /*
     public void adminBlockStickyNotesPostText() {
     AdministrationBlock ablock=new AdministrationBlock();
     ablock.studentSupportSettingsStickyNotesPostText();
     } */
    
    //The below method affects all system users - so currently we are skipping this
    /**
     * Verify 2tor Administrative Block -Sticky Notes Post URL
     */
    /*
     public void adminBlockStickyNotesPostURL() {
     AdministrationBlock ablock=new AdministrationBlock();
     ablock.studentSupportSettingsStickyNotesPostURL();
     } */
    
    /**
     * Verify the Student Support Message previously created by pes admin
     */
    /*
     public void studentVerificationSupportMessage() {
     AdministrationBlock ablock=new AdministrationBlock();
     ablock.studentVerificationSupportMessage();
     } */
    
    /**
     * Verify the student Login Message previously created by pes admin
     */
    /*
     public void studentVerificationLoginMessage() {
     AdministrationBlock ablock=new AdministrationBlock();
     ablock.studentVerificationLoginMessage();
     } */
    
    //The below method affects all system users - so currently we are skipping this
    /**
     * Verify the faculty Login Message previously created by pes admin
     */
    /*
     public void facultyVerificationLoginMessage() {
     AdministrationBlock ablock=new AdministrationBlock();
     ablock.facultyVerificationLoginMessage();
     } */
    
    //The below method affects all system users - so currently we are skipping this
    /**
     * Reverify the Login message
     */
    /*
     public void studentReverificationLoginMessage() {
     AdministrationBlock ablock=new AdministrationBlock();
     ablock.loginMessageAlertNotPresent();
     } */
    
    //The below method affects all system users - so currently we are skipping this
    /**
     * Pes admin disables the support message
     */
    /*
     public void disableStudentSupportMessageByPesAdmin() {
     AdministrationBlock ablock=new AdministrationBlock();
     ablock.disableStudentSupportMessageByPesAdmin();
     } */
    
    //The below method affects all system users - so currently we are skipping this
    /**
     * Pes admin disables the Login message
     */
    /*
     public void disableStudentSupportLoginMessageByPesAdmin() {
     AdministrationBlock ablock=new AdministrationBlock();
     ablock.disableLoginMessageByPesAdmin();
     } */
    
    /**
     * Navigate To Student Support page
     */
    /*public void navigateToStudentSupportPage() {
        ip.isElementPresentByLINK(driver, "Student Support");
        driver.findElement(By.linkText("Student Support")).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportPageHeadingXPATH"), "Student Support");
    }*/
}
