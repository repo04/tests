/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.lms.tests.smoketest.Actions;

/**
 * PES Admin Logs in Create Two Users Assign/Enroll users to GrpCourse as
 * Teacher/Student roles Create Working Group & add users as members Logs out
 */
public class Pes_UserCreation_AssignRole_WorkingGroup extends BaseClass {

    Actions a = new Actions();
    static String[][] userNamesArray = new String[1][2];
    static String[][] workingGroupNameArray = new String[1][1];
    static String[][] studentNameArray = new String[1][1];
    static String[][] teacherNameArray = new String[1][1];
    static String[][] pesTextCourseSectionPost = new String[1][1];
    static String[][] pesTextAnnouncementCoursePost = new String[1][1];
    static String[][] pesTextCoursePostCommentsOn = new String[1][1];
    static String[][] pesTextCoursePostCommentsOff = new String[1][1];

    @DataProvider(name = "Users")
    public static Object[][] Users(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")
                || test.equalsIgnoreCase("CriticalDataTests")) {
            System.out.println("if Users: " + test);
            return (userNamesArray);
        } else {
            System.out.println("else Users: " + test);
            return new Object[][]{{ldv.getTokenValue("teacherUserName"), ldv.getTokenValue("studentUserName")}};
        }
    }

    @DataProvider(name = "WorkingGroup")
    public static Object[][] WorkingGroup(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("if WorkingGroup: " + test);
            return (workingGroupNameArray);
        } else {
            System.out.println("else WorkingGroup: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("workingGroupName")}};
        }
    }

    @DataProvider(name = "StudentName")
    public static Object[][] StudentName(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside StudentName: " + test);
            return (studentNameArray);
        } else {
            System.out.println("Inside StudentName: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("studentUserName")}};
        }
    }

    @DataProvider(name = "WorkingGroupUsers")
    public static Iterator<Object[]> WorkingGroupUsers(ITestContext context) throws Exception {
        System.out.println("init WorkingGroupUsers");
        return DataProviderUtility.cartesianProviderFrom(WorkingGroup(context), Users(context));
    }

    @DataProvider(name = "GroupCourseUsers")
    public static Iterator<Object[]> GroupCourseUsers(ITestContext context) throws Exception {
        System.out.println("init GroupCourseUsers");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), Users(context));
    }

    @DataProvider(name = "GroupCourseWorkingGroupUsers")
    public static Iterator<Object[]> GroupCourseWorkingGroupUsers(ITestContext context) throws Exception {
        System.out.println("init GroupCourseWorkingGroupUsers");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), WorkingGroup(context), Users(context));
    }

    @DataProvider(name = "UsersDebug")
    public static Object[][] UsersDebug(ITestContext context) throws Exception {
        System.out.println("init UsersDebug");
        return (userNamesArray);
    }

    @DataProvider(name = "WorkingGroupDebug")
    public static Object[][] WorkingGroupDebug(ITestContext context) throws Exception {
        System.out.println("init WorkingGroupDebug");
        return (workingGroupNameArray);
    }

    @DataProvider(name = "WorkingGroupDebugUsers")
    public static Iterator<Object[]> WorkingGroupDebugUsers(ITestContext context) throws Exception {
        System.out.println("init WorkingGroupDebugUsers");
        return DataProviderUtility.cartesianProviderFrom(WorkingGroupDebug(context), Users(context));
    }

    @DataProvider(name = "GroupCourseWorkingGroupDebugUsers")
    public static Iterator<Object[]> GroupCourseWorkingGroupDebugUsers(ITestContext context) throws Exception {
        System.out.println("init GroupCourseWorkingGroupDebugUsers");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), WorkingGroupDebug(context), Users(context));
    }

    @DataProvider(name = "GroupCourseUsersDebug")
    public static Iterator<Object[]> GroupCourseUsersDebug(ITestContext context) throws Exception {
        System.out.println("init GroupCourseUsersDebug");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), UsersDebug(context));
    }

    @DataProvider(name = "GroupCourseAssignmentStudent")
    public static Iterator<Object[]> GroupCourseAssignmentStudent(ITestContext context) throws Exception {
        System.out.println("init GroupCourseAssignmentStudent");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), ContentAdmin_Course_GroupCourseCreation.AssignmentName(context), StudentName(context));
    }

    @DataProvider(name = "GroupCoursePesCoursePosts")
    public static Iterator<Object[]> GroupCoursePesCoursePosts(ITestContext context) throws Exception {
        System.out.println("init GroupCoursePesCoursePosts");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), pesTextCourseSectionPost, pesTextCoursePostCommentsOn, pesTextCoursePostCommentsOff, pesTextAnnouncementCoursePost);
    }

    @DataProvider(name = "GroupCourseAnnouncement")
    public static Iterator<Object[]> GroupCourseAnnouncement(ITestContext context) throws Exception {
        System.out.println("init GroupCourseAnnouncement");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), pesTextAnnouncementCoursePost);
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, PES Admin Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testPESAdminLogIn() throws Exception {
        a.login("pesAdmin");
    }

    /**
     * Create Two UserstestUsrCrtn
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "fullSmoke", "criticalDataSmoke", "users.creation"})
    public void testPESAdminUserCreation() throws Exception {
        a.navigateToMyContacts();
        userNamesArray[0][0] = a.createUser("teacher");
        System.out.println("teacherUserName: " + userNamesArray[0][0]);
        Reporter.log("teacherUserName: " + userNamesArray[0][0]);

        a.navigateToMyContacts();
        userNamesArray[0][1] = a.createUser("student");
        studentNameArray[0][0] = userNamesArray[0][1];
        System.out.println("studentUserName: " + userNamesArray[0][1]);
        Reporter.log("studentUserName: " + userNamesArray[0][1]);
    }

    /**
     * Assign/Enroll users to GrpCourse as Teacher/Student roles
     *
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseUsers", groups = {"regressionSmoke", "fullSmoke", "criticalDataSmoke", "users.assignRole"})
    public void testPESAdminAssignRole(String groupCourseName, String teacherUserName, String studentUserName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.enrollUserToRole_GroupCourse(teacherUserName, groupCourseName);

        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.enrollUserToRole_GroupCourse(studentUserName, groupCourseName);
    }

    /**
     * Create Working Group
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "fullSmoke", "workingGroup.create"})
    public void testPESAdminCreateWorkingGroup() throws Exception {
        a.navigateToWorkingGroups();
        workingGroupNameArray[0][0] = a.createWorkingGroup();
        System.out.println("wrkngGrp: " + workingGroupNameArray[0][0]);
        Reporter.log("wrkngGrp: " + workingGroupNameArray[0][0]);
    }

    /**
     * Add users as members to Working Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "WorkingGroupUsers", groups = {"regressionSmoke", "fullSmoke", "workingGroup.addMembers"})
    public void testPESAdminAddMembersToWorkingGroup(String workingGroupName, String teacherUserName, String studentUserName) throws Exception {
        a.navigateToWorkingGroups();
        a.accessWorkingGroup(workingGroupName);
        a.addMembersToWorkingGroup(teacherUserName, studentUserName);
    }

    /**
     * Post Text on Course Section
     *
     * @param groupCourseName
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "wall.courseSectionPost"})
    public void testPesAdminPostTextOnCourseSection(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        pesTextCourseSectionPost[0][0] = a.textPost("txtCrsSctnPost");
        Reporter.log("pesTextCourseSectionPost: " + pesTextCourseSectionPost[0][0], true);
    }

    /**
     * Post Text with Comments enabled on Course Wall
     *
     * @param groupCourseName
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "wall.coursePostCommentsOn"})
    public void testPesAdminPostTextOnCourseCommentsOn(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        pesTextCoursePostCommentsOn[0][0] = a.textPost("txtCrsPostCmntsOn");
        Reporter.log("pesTxtCrsPost: " + pesTextCoursePostCommentsOn[0][0], true);
        ip.isElementPresentByXPATH(driver, "//li[1]/div/div[4]/label/a/label");
    }

    /**
     * Post Text with Comments disabled on Course Wall
     *
     * @param groupCourseName
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "wall.coursePostCommentsOff"})
    public void testPesAdminPostTextOnCourseCommentsOff(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        pesTextCoursePostCommentsOff[0][0] = a.textPost("txtCrsPostCmntsOff");
        Reporter.log("pesTxtCrsPost: " + pesTextCoursePostCommentsOff[0][0], true);
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[1]/div/div[4]/label/a/label")));
    }

    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "wall.courseAnnouncementPost"})
    public void testPesAdminPostAnnouncementOnAllCourseSection(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        pesTextAnnouncementCoursePost[0][0] = a.textPost("txtAncmntCrsPost");
        Reporter.log("pesTextAnnouncementCoursePost: " + pesTextAnnouncementCoursePost[0][0], true);
    }

    /**
     * Pes Admin verify Feedback Window
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "feedback.pesAdminVerify"})
    public void testPesAdminVerifyFeedbackWindow() throws Exception {
        a.navigateToMyHome();
        a.verifyFeedbackWindow();
    }

    /**
     * PesAdmin verify Help Window on Home Page
     *
     * @throws Exception
     */
    /*@Test(groups = {"regressionSmoke", "help.pesAdminVerify"})
     public void testPesAdminVerifyHelpWindow() throws Exception {
     a.navigateToMyHome();
     a.verifyHelpWindow();
     }*/
    
    /**
     * Verify Settings page specific to user role
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "settings.pesAdminVerify"})
    public void testPesAdminVerifySettings() throws Exception {
        a.navigateToSettings();
        a.verifySettings();
    }

    /**
     * Pes Admin verify Calendar on Home Page
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "calendar.pesAdminVerify"})
    public void testPesAdminVerifyCalendar() throws Exception {
        a.navigateToMyHome();
        a.verifyCalendar();
    }

    /**
     * PesAdmin verify 2tor Administrative Settings
     *
     * @throws Exception
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifySiteAdministrationSection() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.verifySiteAdministrationSection();
    }

    /**
     * PesAdmin verify the student support settings
     *
     * @throws Exception
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifySiteAdminStudentSupportSection() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.verifySiteAdminStudentSupportSection();
    }

    /**
     * PesAdmin verify the video tutorial
     *
     * @throws Exception
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifySiteAdminVideoTutorialsPage() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToVideoTutorials();
        a.verifySiteAdminStudentSupportVideoTutorialsPage();
    }

    /**
     * PesAdmin verify the Student support message page
     *
     * @throws Exception
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifySiteAdminStudentSupportMessagePage() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToStudentSupportMessage();
        a.verifySiteAdminStudentSupportMessagePage();
    }

    /**
     * PesAdmin verify the login message page
     *
     * @throws Exception
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifyStudentSupportLoginMessagePage() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToLoginMessage();
        a.verifySiteAdminStudentSupportLoginMessagePage();
    }

    /**
     * PesAdmin verify User Sticky Notes Page
     *
     * @throws Exception
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifyStudentSupportUserStickyNotesPage() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToUserStickyNotes();
    }

    /**
     * PesAdmin verify the "Email Not In Domain" UI
     *
     * @throws Exception
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifySiteAdminReportEmailNotInDomainPage() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToEmailNotInDomain();
        a.verifySiteAdminReportEmailNotInDomainPage();
    }

    //Commented -- As looping executes very slow on Sauce Lab
    /**
     * PesAdmin verify University Domain Email IDs are not present in "Email Not
     * In Domain" list
     *
     * @throws Exception
     */
    @Test(groups = {"2torAdministrativeBlock.verifyUniversityDomainNotPresentInEmailList"})
    public void testPesAdminVerifyUniversityDomainNotPresentInEmailNotInDomainList() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToEmailNotInDomain();
        a.verifyUniversityDomainNotPresentInEmailNotInDomainList();
    }
    
    /**
     * PesAdmin verify the Report settings
     *
     * @throws Exception
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifySiteAdminReportSection() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.verifySiteAdministrationReportSection();
    }

    /**
     * Verify pes admin can not access the Service and Configuration reports
     *
     * @throws Exception
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminGetAccessDeniedForStudentServicesAndConfigurationReports() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.verifyGetAccessDeniedForStudentServicesAndConfigurationReports();
    }

    /**
     * Pes admin verify the Course Roster UI
     *
     * @throws Exception
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifySiteAdminReportCourseRostersPage() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToCourseRosters();
        a.verifySiteAdminReportCourseRostersPage();
    }

    /**
     * Pes admin verify the related sections to the courses in the section drop down in Course Roster Page
     *
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifySectionDropdownCourseRostersPage(ITestContext context, String groupCourseName) throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToCourseRosters();
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")
            ) {
            a.verifySectionDropdownCourseRostersPage(ContentAdmin_Course_GroupCourseCreation.courseName, groupCourseName);
        } else {
            a.verifySectionDropdownCourseRostersPage(context.getCurrentXmlTest().getParameter("courseName"), groupCourseName);
        }
    }
    
    /**
     * Verify 2tor Administrative Block -DeletedLiveSession Section
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifySiteAdminReportDeletedLiveSessionPage() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToDeletedLiveSession();
        a.verifySiteAdminReportDeletedLiveSessionPage();
    }

    /**
     * Verify 2tor Administrative Block -StudentEngagementReportUIVerify
     */
    @Test(groups = {"2torAdministrativeBlock.contentVerify"})
    public void testPesAdminVerifySiteAdminReportStudentEngagementReportPage() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToStudentEngagementReport();
        a.verifySiteAdminReportStudentEngagementReportPage();
    }

    //Following functional test methods affect all system users - so currently we are skipping this
    /**
     * PesAdmin Set Faculty Login Message
     *
     * @throws Exception
     */
    /*
     @Test(groups = {"2torAdministrativeBlock.contentVerify"})
     public void testPesAdminAdministrativeBlockSetFacultyLoginMessage() throws Exception {
     a.navigateTo2torSiteAdministrator();
     a.navigateToLoginMessage();
     a.adminBlockSetFacultyLoginMessage();
     } */
    
    /**
     * PesAdmin create and verify the User Sticky Notes by text - Currently
     * commented as it affects all system users
     *
     * @throws Exception
     */
    /*
     @Test(groups = {"2torAdministrativeBlock.contentVerify"})
     public void testPesAdminAdministrativeBlockStickyNotesPostText() throws Exception {
     a.navigateTo2torSiteAdministrator();
     a.navigateToUserStickyNotes();
     a.adminBlockStickyNotesPostText();
     } */
    
    /**
     * PesAdmin create and verify the User Sticky Notes by URL - Currently
     * commented as it affects all system users
     *
     * @throws Exception
     */
    /*
     @Test(groups = {"2torAdministrativeBlock.contentVerify"})
     public void testPesAdminAdministrativeBlockStickyNotesPostURL() throws Exception {
     a.navigateTo2torSiteAdministrator();
     a.navigateToUserStickyNotes();
     a.adminBlockStickyNotesPostURL();
     } */    

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testPESAdminLogOut() throws Exception {
        a.logOut();
    }
}
