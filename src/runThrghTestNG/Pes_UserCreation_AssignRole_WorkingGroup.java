/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

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
import smoketest.Actions;

/**
 * PES Admin Logs in Create Two Users Assign/Enroll users to GrpCourse as
 * Teacher/Student roles Create Working Group & add users as members Logs out
 */
public class Pes_UserCreation_AssignRole_WorkingGroup extends BaseClass {

    Actions a = new Actions();
    static String[][] usrsArray = new String[1][2];
    static String[][] wrkngGrpArray = new String[1][1];
    static String[][] stdtName = new String[1][1];
    static String[][] tchrName = new String[1][1];
    static String[][] pesTxtCrsSctnPost = new String[1][1];
    static String[][] pesTxtAncmntCrsPost = new String[1][1];
    static String[][] pesTxtCrsPostCmntsOn = new String[1][1];
    static String[][] pesTxtCrsPostCmntsOff = new String[1][1];

    @DataProvider(name = "Users")
    public static Object[][] Users(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("if Users: " + test);
            return (usrsArray);
        } else {
            System.out.println("else Users: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("tchrUsrName"), context.getCurrentXmlTest().getParameter("stdtUsrName")}};
        }
    }

    @DataProvider(name = "WrkngGrp")
    public static Object[][] WrkngGrp(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("if WrkngGrp: " + test);
            return (wrkngGrpArray);
        } else {
            System.out.println("else WrkngGrp: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("wrkngGrpName")}};
        }
    }

    @DataProvider(name = "StdtName")
    public static Object[][] StdtName(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside StdtName: " + test);
            return (stdtName);
        } else {
            System.out.println("Inside StdtName: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("stdtUsrName")}};
        }
    }

    @DataProvider(name = "WrkngGrpUsers")
    public static Iterator<Object[]> WrkngGrpUsers(ITestContext context) throws Exception {
        System.out.println("init WrkngGrpUsers");
        return DataProviderUtility.cartesianProviderFrom(WrkngGrp(context), Users(context));
    }

    @DataProvider(name = "GrpCrsUsers")
    public static Iterator<Object[]> GrpCrsUsers(ITestContext context) throws Exception {
        System.out.println("init GrpCrsUsers");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), Users(context));
    }

    @DataProvider(name = "GrpCrsWrkngGrpUsers")
    public static Iterator<Object[]> GrpCrsWrkngGrpUsers(ITestContext context) throws Exception {
        System.out.println("init GrpCrsUsers");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), WrkngGrp(context), Users(context));
    }

    @DataProvider(name = "UsersDebug")
    public static Object[][] UsersDebug(ITestContext context) throws Exception {
        System.out.println("init UsersDebug");
        return (usrsArray);
    }

    @DataProvider(name = "WrkngGrpDebug")
    public static Object[][] WrkngGrpDebug(ITestContext context) throws Exception {
        System.out.println("init WrkngGrpDebug");
        return (wrkngGrpArray);
    }

    @DataProvider(name = "WrkngGrpDebugUsrs")
    public static Iterator<Object[]> WrkngGrpDebugUsrs(ITestContext context) throws Exception {
        System.out.println("init WrkngGrpDebugUsrs");
        return DataProviderUtility.cartesianProviderFrom(WrkngGrpDebug(context), Users(context));
    }

    @DataProvider(name = "GrpCrsWrkngGrpDebugUsers")
    public static Iterator<Object[]> GrpCrsWrkngGrpDebugUsers(ITestContext context) throws Exception {
        System.out.println("init GrpCrsWrkngGrpDebugUsers");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), WrkngGrpDebug(context), Users(context));
    }

    @DataProvider(name = "GrpCrsUsersDebug")
    public static Iterator<Object[]> GrpCrsUsersDebug(ITestContext context) throws Exception {
        System.out.println("init GrpCrsUsersDebug");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), UsersDebug(context));
    }

    @DataProvider(name = "GrpCrsAsgnmntStdt")
    public static Iterator<Object[]> GrpCrsAsgnmntStdt(ITestContext context) throws Exception {
        System.out.println("init GrpCrsAsgnmntStdt");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), ContentAdmin_Course_GroupCourseCreation.AssgnmntName(context), StdtName(context));
    }

    @DataProvider(name = "GrpCrsPESCoursePosts")
    public static Iterator<Object[]> GrpCrsPESCoursePosts(ITestContext context) throws Exception {
        System.out.println("init GrpCrsPESCoursePosts");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), pesTxtCrsSctnPost, pesTxtCrsPostCmntsOn, pesTxtCrsPostCmntsOff, pesTxtAncmntCrsPost);
    }
    
    @DataProvider(name = "GrpCrsAnnouncement")
    public static Iterator<Object[]> GrpCrsAnnouncement(ITestContext context) throws Exception {
        System.out.println("init GrpCrsAnnouncement");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), pesTxtAncmntCrsPost);
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, PES Admin Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testPESAdminLogin() throws Exception {
        a.login("pesAdmin");
    }

    /**
     * Create Two UserstestUsrCrtn
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "fullSmoke", "users.creation"})
    public void testPESAdminUserCreation () throws Exception {
        a.navigateToMyContacts();
        usrsArray[0][0] = a.createUser("teacher");
        System.out.println("tchrUsrName: " + usrsArray[0][0]);
        Reporter.log("tchrUsrName: " + usrsArray[0][0]);

        a.navigateToMyContacts();
        usrsArray[0][1] = a.createUser("student");
        stdtName[0][0] = usrsArray[0][1];
        System.out.println("stdtUsrName: " + usrsArray[0][1]);
        Reporter.log("stdtUsrName: " + usrsArray[0][1]);
    }

    /**
     * Assign/Enroll users to GrpCourse as Teacher/Student roles
     *
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsUsers", groups = {"regressionSmoke", "fullSmoke", "users.assignRole"})
    public void testPESAdminAssignRole(String grpCrsName, String tchrUsrName, String stdtUsrName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.enrollUserToRole_GroupCourse(tchrUsrName, grpCrsName);

        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.enrollUserToRole_GroupCourse(stdtUsrName, grpCrsName);
    }

    /**
     * Create Working Group
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "fullSmoke", "workingGroup.create"})
    public void testPESAdminCreateWorkingGroup() throws Exception {
        a.navigateToWorkingGroups();
        wrkngGrpArray[0][0] = a.createWorkingGroup();
        System.out.println("wrkngGrp: " + wrkngGrpArray[0][0]);
        Reporter.log("wrkngGrp: " + wrkngGrpArray[0][0]);
    }

    /**
     * Add users as members to Working Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "WrkngGrpUsers", groups = {"regressionSmoke", "fullSmoke", "workingGroup.addMembers"})
    public void testPESAdminAddMembersToWorkingGroup(String wrkngGrpName, String tchrUsrName, String stdtUsrName) throws Exception {
        a.navigateToWorkingGroups();
        a.accessWorkingGroup(wrkngGrpName);
        a.addMembersToWorkingGroup(tchrUsrName, stdtUsrName);
    }

    /**
     * Post Text on Course Section
     *
     * @param grpCrsName
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "wall.courseSectionPost"})
    public void testPesAdminPostTextOnCourseSection(String grpCrsName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        pesTxtCrsSctnPost[0][0] = a.textPost("txtCrsSctnPost");
        Reporter.log("pesTxtCrsSctnPost: " + pesTxtCrsSctnPost[0][0], true);
    }
    
    /**
     * Post Text with Comments enabled on Course Wall
     *
     * @param grpCrsName
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "wall.coursePostCommentsOn"})
    public void testPesAdminPostTextOnCourseCommentsOn(String grpCrsName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        pesTxtCrsPostCmntsOn[0][0] = a.textPost("txtCrsPostCmntsOn");
        Reporter.log("pesTxtCrsPost: " + pesTxtCrsPostCmntsOn[0][0], true);
        ip.isElementPresentByXPATH(driver, "//li[1]/div/div[4]/label/a/label");
    }

    /**
     * Post Text with Comments disabled on Course Wall
     *
     * @param grpCrsName
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "wall.coursePostCommentsOff"})
    public void testPesAdminPostTextOnCourseCommentsOff(String grpCrsName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        pesTxtCrsPostCmntsOff[0][0] = a.textPost("txtCrsPostCmntsOff");
        Reporter.log("pesTxtCrsPost: " + pesTxtCrsPostCmntsOff[0][0], true);
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[1]/div/div[4]/label/a/label")));
    }
    
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "wall.courseAnnouncementPost"})
    public void testPesAdminPostAnnouncementOnAllCourseSection(String grpCrsName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        pesTxtAncmntCrsPost[0][0] = a.textPost("txtAncmntCrsPost");
        Reporter.log("pesTxtAncmntCrsPost: " + pesTxtAncmntCrsPost[0][0], true);
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
