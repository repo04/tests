/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import com.lms.tests.smoketest.Actions;
import com.lms.tests.smoketest.Utility;
import java.util.Iterator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Student logs in, Find & Join Teacher's Social Group, Post/Verify URL on
 * Teacher's SocialGroup,
 *
 */
public class Student_JoinSocialGroup_Post extends BaseClass {

    static String[][] studentUrlPostOnTeacherSocialGroup = new String[1][1];
    static String[][] studentTextCommentOnTeacherCoursePost = new String[1][1];
    static String[][] noteCourseNameArray = new String[1][1];
    static String[][] noteWallNameArray = new String[1][1];
    static String[][] studentGlossaryEntryArray = new String[1][1];
    static String[][] studentSubmitAllInOneForReview = new String[1][1];
    String studentPostHTMLOnOwnWall;
    Actions a = new Actions();

    @DataProvider(name = "Note")
    public static Object[][] Note(ITestContext context) throws Exception {
        return (noteWallNameArray);
    }

    @DataProvider(name = "TeacherSocialGroupStudentUrlPost")
    public static Iterator<Object[]> TeacherSocialGroupStudentUrlPost(ITestContext context) throws Exception {
        System.out.println("init TeacherSocialGroupStudentUrlPost");
        return DataProviderUtility.cartesianProviderFrom(Teacher_Posts_SocialGroup.TeacherSocialGroup(context), studentUrlPostOnTeacherSocialGroup);
    }

    @DataProvider(name = "CourseStudentComment")
    public static Iterator<Object[]> StudentTextCommentOnTeacherCoursePost(ITestContext context) throws Exception {
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.GroupCourse(context), studentTextCommentOnTeacherCoursePost);
    }

    @DataProvider(name = "StudentGlossaryEntryName")
    public static Object[][] StudentGlossaryEntryName(ITestContext context) throws Exception {
        System.out.println("init StudentGlossaryEntryName");
        return (studentGlossaryEntryArray);
    }

    @DataProvider(name = "StudentAllInOneReviewText")
    public static Object[][] StudentAllInOneReviewText(ITestContext context) throws Exception {
        System.out.println("init StudentAllInOneReviewText");
        return (studentSubmitAllInOneForReview);
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testStudentLogIn(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")
                || test.equalsIgnoreCase("CriticalDataTests")) {
            a.login(Pes_UserCreation_AssignRole_WorkingGroup.userNamesArray[0][1]);
        } else {
            a.login(ldv.getTokenValue("studentUserName"));
        }
    }

    /**
     * Student Change Password from Force Password Change Page on First Time
     * Login
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "users.studentForceChangePasswordOnFirstLogin"})
    public void testStudentForceChangePasswordOnFirstLogin() throws Exception {
        a.forceChangePasswordOnFirstLogin();
        //Bug --> LMSII-3484
        Utility.clickByJavaScript(driver, xpv.getTokenValue("lnkToHomeXPATH"));
        boolean alert = false;
        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.alertIsPresent());
            alert = true;
        } catch (TimeoutException e) {
            // Do Nothing
        }
        if (alert) {
            Utility.waitForAlertToBeAccepted(driver, 60, "Your password has been successfully changed");
        }
        Utility.verifyCurrentUrl(driver, xpv.getTokenValue("homePageURL"));
    }

    /**
     * Find & Join Teacher's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "TeacherSocialGroup", dataProviderClass = Teacher_Posts_SocialGroup.class,
    groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "socialGroup.studentJoinTeachers"})
    public void testStudentJoinsTeacherSocialGroup(String teacherSocialGroupName) throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(teacherSocialGroupName);
        a.joinSocialGroup(teacherSocialGroupName);
    }

    /**
     * Post/Verify URL on Teacher's SocialGroup
     *
     * @throws Exception
     */
    @Test(dataProvider = "TeacherSocialGroup", dataProviderClass = Teacher_Posts_SocialGroup.class,
    groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "socialGroup.studentPostURLOnTeachers"})
    public void testStudentPostURLOnTeacherSocialGroup(String teacherSocialGroupName) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(teacherSocialGroupName);
        studentUrlPostOnTeacherSocialGroup[0][0] = a.urlPost("urlSclGrpPost");
        Reporter.log("studentUrlPostOnTeacherSocialGroup: " + studentUrlPostOnTeacherSocialGroup[0][0], true);
    }

    /**
     * Add Comment on Teacher's CoursePost
     *
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseTeacherUrlCoursePost", dataProviderClass = Teacher_Posts_SocialGroup.class,
    groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "wall.studentCommentOnTeacherCoursePost"})
    public void testStudentCommentOnTeacherCoursePost(String groupCourseName, String teacherUrlCoursePost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        studentTextCommentOnTeacherCoursePost[0][0] = a.textCommentPost(teacherUrlCoursePost, "txtCmntOnTchrCrsPst");
        Reporter.log("studentTextCommentOnTeacherCoursePost: " + studentTextCommentOnTeacherCoursePost[0][0], true);
    }

    /**
     * Student verifies Pes posts on Course Wall
     *
     * @param groupCourseName
     * @param pesTextCourseSectionPost
     * @param pesTxtCoursePostCommentsOn
     * @param pesTextCoursePostCommentsOff
     * @throws Exception
     */
    @Test(dataProvider = "GroupCoursePesCoursePosts", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
    groups = {"regressionSmoke", "wall.studentVerifyPesCoursePosts"})
    public void testStudentVerifyPesCoursePost(String groupCourseName, String pesTextCourseSectionPost, String pesTxtCoursePostCommentsOn, String pesTextCoursePostCommentsOff, String pesTextAnnouncementCoursePost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.verifyCoursePost(pesTextCourseSectionPost, pesTxtCoursePostCommentsOn, pesTextCoursePostCommentsOff, pesTextAnnouncementCoursePost);
    }

    /**
     * Student recommend Teacher's URL Course Post
     *
     * @param groupCourseName
     * @param teacherUrlCoursePost
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseTeacherUrlCoursePost", dataProviderClass = Teacher_Posts_SocialGroup.class,
    groups = {"regressionSmoke", "wall.studentRecommendPost"})
    public void testStudentRecommendCourseURLPost(String groupCourseName, String teacherUrlCoursePost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.recommendURLCoursePost(teacherUrlCoursePost);
    }

    /**
     * Submit Assignment
     *
     * @param groupCourseName
     * @param allInOneAssignmentActivityName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseAssignment", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "fullSmoke", "allinone.studentUploadFileAndSendForReview"})
    public void testStudentUploadFileAndSendAllInOneForReview(String groupCourseName, String allInOneAssignmentActivityName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.navigateToActivity(allInOneAssignmentActivityName);
        studentSubmitAllInOneForReview[0][0] = a.uploadFileAndSendAllInOneForReview();
        Reporter.log("studentSubmitAllInOneForReviewText: " + studentSubmitAllInOneForReview[0][0], true);
    }

    /**
     * Create Note on Course Wall
     *
     * @param groupCourseName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourse", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "note.createOnCourseWall"})
    public void testStudentCreateNoteOnCourseWall(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        noteCourseNameArray[0][0] = a.createNote(groupCourseName);
    }

    /**
     * Create Note on Profile Wall
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "note.createOnProfileWall"})
    public void testStudentCreateNoteOnProfileWall() throws Exception {
        a.navigateToMyWall();
        noteWallNameArray[0][0] = a.createNote("Profile");
    }

    /**
     * Verify Note Sorting
     *
     * @param profileNote
     * @throws Exception
     */
    @Test(dataProvider = "Note", groups = {"regressionSmoke", "note.verifySorting"})
    public void testStudentVerifyNoteSorting(String profileNote) throws Exception {
        a.navigateToMyWall();
        a.verifyNoteSorting(profileNote);
    }

    /**
     * Delete Note
     *
     * @param profileNote
     * @throws Exception
     */
    @Test(dataProvider = "Note", groups = {"regressionSmoke", "note.deleteProfile"})
    public void testStudentDeleteProfileNote(String profileNote) throws Exception {
        a.navigateToMyWall();
        a.deleteNote(profileNote);
    }

    /**
     * Verify Resources
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "resources.studentVerify"})
    public void testStudentVerifyResources() throws Exception {
        a.navigateToMyHome();
        a.verifyResources();
    }

    /**
     * Verify Footers
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "footers.studentVerify"})
    public void testStudentVerifyFooters() throws Exception {
        a.navigateToMyHome();
        a.verifyFooters();
    }

    /**
     * Verify Syllabus Activity
     *
     * @param groupCourseName
     * @throws Exception
     */
    /*@Test(dataProvider = "GroupCourse", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class, 
     groups = {"regressionSmoke", "activity.studentVerifySyllabus"})
     public void testStudentVerifySyllabusActivity(String groupCourseName) throws Exception {
     a.navigateToMyCourse();
     a.selectGroupCourse(groupCourseName);
     a.verifySyllabusActivity();
     }*/
    /**
     * Student verify Resume
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "resume.studentVerify"})
    public void testStudentVerifyResume() throws Exception {
        a.navigateToMyWall();
        a.verifyResume();
    }

    /**
     * Student verify Personal Information
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "personalInformation.studentVerify"})
    public void testStudentVerifyPersonalInformation() throws Exception {
        a.navigateToMyPersonalInformation();
        a.verifyPersonalInformation("student");
    }

    /**
     * Student verify Feedback Window
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "feedback.studentVerify"})
    public void testStudentVerifyFeedbackWindow() throws Exception {
        a.navigateToMyHome();
        a.verifyFeedbackWindow();
    }

    /**
     * Student verify Help Window on Home Page
     *
     * @throws Exception
     */
    /*@Test(groups = {"regressionSmoke", "help.studentVerify"})
     public void testStudentVerifyHelpWindow() throws Exception {
     a.navigateToMyHome();
     a.verifyHelpWindow();
     }*/
    /**
     * Verify Settings page specific to user role
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "settings.studentVerify"})
    public void testStudentVerifySettings() throws Exception {
        a.navigateToSettings();
        a.verifySettings();
    }

    /**
     * Student create Glossary entry
     *
     * @param groupCourseName
     * @param glossaryName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseGlossaryName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "activities.studentCreateGlossaryEntry"})
    public void testStudentCreateGlossaryEntry(String groupCourseName, String glossaryName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.navigateToContentPage(glossaryName);
        studentGlossaryEntryArray[0][0] = a.createGlossaryEntry(glossaryName);
        Reporter.log("studentGlossaryEntryName: " + studentGlossaryEntryArray[0][0], true);
    }

    /**
     * Student verify Calendar on Home Page
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "calendar.studentVerify"})
    public void testStudentVerifyCalendar() throws Exception {
        a.navigateToMyHome();
        a.verifyCalendar();
    }

    /**
     * Student confirm contact request
     *
     * @param teacherUserName
     * @param studentUserName
     * @throws Exception
     */
    @Test(dataProvider = "Users", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
    groups = {"regressionSmoke", "criticalDataSmoke", "contact.studentConfirmRequest"})
    public void testStudentConfirmContactRequest(String teacherUserName, String studentUserName) throws Exception {
        a.navigateToMyContacts();
        a.confirmContactRequest(teacherUserName);
    }

    /**
     * Student post HTML on own Wall
     */
    @Test(groups = {"regressionSmoke", "wall.studentHTMLWallPost"})
    public void testStudentPostHTMLOnOwnWall() {
        a.navigateToMyWall();
        studentPostHTMLOnOwnWall = a.textPost("txtHTMLWallPost");
        Reporter.log("studentPostHTMLOnOwnWall: " + studentPostHTMLOnOwnWall, true);
    }

    /**
     * View Reveal Password button for All In One Assignment
     *
     * @param groupCourseName
     * @param allInOneAssignmentActivityNameWithRevealPassword
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseAllInOneActivityNameWithRevealPassword", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "content.studentViewRevealPasswordButtonForAllInOne"})
    public void testStudentViewRevealPasswordButtonForAllInOneAssignemnt(String groupCourseName, String allInOneAssignmentActivityNameWithRevealPassword) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.navigateToActivity(allInOneAssignmentActivityNameWithRevealPassword);
        a.viewRevealPasswordButtonForAllInOneAssignemnt();
    }

    /**
     * Verify Student has read only access to Offline Activity created
     *
     * @param groupCourseName
     * @param offlineActivityName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseOfflineActivityName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "content.studentVerifyReadOnlyAccessToOfflineActivity"})
    public void testStudentVerifyReadOnlyAccessToOfflineActivity(String groupCourseName, String offlineActivityName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.verifyReadOnlyAccessToOfflineActivity(offlineActivityName);
    }

    /**
     * Student verify the elements on the right sidebar of course work page
     *
     * @param groupCourseName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourse", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "content.studentVerifyRightSideBarOfCourseworkPage"})
    public void testStudentVerifyRightSidebarOfCourseWorkPage(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToCourseWorkPage();
        a.verifyRightSidebarOfCourseWorkPage();
    }

    /**
     * Verify Email Address Is Visible To Student When "Allow everyone to see my
     * Email address" Is Selected For Teacher
     *
     * @param teacherUserName
     * @param studentUserName
     * @throws Exception
     */
    @Test(dataProvider = "Users", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
    groups = {"users.studentViewTeachersEmailAddress"})
    public void testStudentViewTeachersEmailAddressAsAllowEveryoneToSeeEmailAddressIsSelected(String teacherUserName, String studentUserName) throws Exception {
        a.navigateToMyContacts();
        a.verifyUsersEmailAddressIsVisibleAsAllowEveryoneToSeeEmailAddressIsSelected(teacherUserName);
    }

    //The below method affects all system users - so currently we are skipping this
    /**
     * Student verify Support Message - Currently commented as it affects all
     * system users
     *
     * @throws Exception
     */
    /*
     @Test(groups = {"2torAdministrativeBlock.studentSupportMessageVerification"})
     public void testStudentVerifySupportMessage() throws Exception {
     a.navigateToStudentSupportPage();
     a.studentVerificationSupportMessage();
     } */
    
    //The below method affects all system users - so currently we are skipping this
    /**
     * Student verify Login Message - Currently commented as it affects all
     * system users
     *
     * @throws Exception
     */
    /*
     @Test(groups = {"2torAdministrativeBlock.studentLoginMessageVerification"})
     public void testStudentVerifyLoginMessage() throws Exception {
     a.studentVerificationLoginMessage();
     } */
    
    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testStudentLogOut() throws Exception {
        a.logOut();
    }
}
