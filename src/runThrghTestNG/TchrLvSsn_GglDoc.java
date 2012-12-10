/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.util.Iterator;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 *
 * Teacher LOGS in, Find SclGroup & Create LiveSession in SclGroup, Create
 * GoogleDoc in Working Group Verify All Posts on Top & RecentNews Verify
 * Activities & resource appear items on activity report
 */
public class TchrLvSsn_GglDoc extends BaseClass {

    static String[][] gglDocArray = new String[1][1];
    Actions a = new Actions();

    @DataProvider(name = "GglDoc")
    public static Object[][] GglDoc(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("if GglDoc: " + test);
            return (gglDocArray);
        } else {
            System.out.println("else GglDoc: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("gglDocName")}};
        }
    }

    @DataProvider(name = "WrkngGrpGgleDoc")
    public static Iterator<Object[]> WrkngGrpGgleDoc(ITestContext context) throws Exception {
        System.out.println("init WrkngGrpGgleDoc");
        return DataProviderUtil.cartesianProviderFrom(UsrCrtn_AsgnRole_WrkngGrp.WrkngGrp(context), GglDoc(context));
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testTeacherLogin(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            a.login(UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][0]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("tchrUsrName"));
        }
    }

    /**
     * Find SclGroup & Create LiveSession in SclGroup
     *
     * @throws Exception
     */
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = TchrPosts_SclGrp.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "liveSession.teacherCreate"})
    public void testTeacherCreateLiveSession(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.accessSocialGroupWall(tchrSclGrpName);
        a.accessLiveSessionWall();
        a.createLiveSession(tchrSclGrpName);
    }

    /**
     * Create GoogleDoc in Working Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "WrkngGrp", dataProviderClass = UsrCrtn_AsgnRole_WrkngGrp.class,
          groups = {"regressionSmoke", "fullSmoke", "workingGroup.teacherCreateGoogleDoc"})
    public void testTeacherCreateGoogleDoc(String wrkngGrpName) throws Exception {
        a.navigateToWorkingGroups();
        gglDocArray[0][0] = a.createGoogleDoc(wrkngGrpName);
        System.out.println("gglDocName: " + gglDocArray[0][0]);
        Reporter.log("gglDocName: " + gglDocArray[0][0]);
    }

    /**
     * Verify Activities & resource appear items on activity report
     *
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsActivities", dataProviderClass = Crs_GrpCrsCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "activites.teacherVerify"})
    public void testTeacherVerifyActivities(String grpCrsName, String frmActvyName, String quizActvtyName, String allInOneAsgnmntAvtvtyName, String pageActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToActivityReport();
        a.verifyActivities(frmActvyName, quizActvtyName, allInOneAsgnmntAvtvtyName, pageActvtyName);
    }
    
    /**
     * Grade Assignment 
     * 
     * @param grpCrsName
     * @param allInOneAsgnmntAvtvtyName
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsAssgnmnt", dataProviderClass = Crs_GrpCrsCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "assignment.grade"})
    public void testTeacherGradeAssignment(String grpCrsName, String allInOneAsgnmntAvtvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToGrades();
        a.gradeAssignment(allInOneAsgnmntAvtvtyName);
    }
    
    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testTeacherLogOut() throws Exception {
        a.logOut();
    }
}
