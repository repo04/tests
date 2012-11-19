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
 * Student logs in, Create Live Session in Teacher's Social Group, Creates own
 * Social Group Verify Google Doc Verify All Posts on Top/Recent News Verify
 * Activities & resource items appear in activity report
 */
public class StdtLvSsn_SclGrp_GglDoc extends BaseClass {

    static String[][] stdtSclGrpArray = new String[1][1];
    Actions a = new Actions();

    @DataProvider(name = "StdtSclGrp")
    public static Object[][] StdtSclGrp(ITestContext context) throws Exception {
        System.out.println("init StdtSclGrp");

        if (test.equalsIgnoreCase("SmokeTests") || test.equalsIgnoreCase("CriticalTests")) {
            System.out.println("if StdtSclGrp: " + test);
            return (stdtSclGrpArray);
        } else {
            System.out.println("else StdtSclGrp: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("stdtSclGrpName")}};
        }
    }

    @DataProvider(name = "TchrStdtSclGrps")
    public static Iterator<Object[]> GrpCrsWrkngGrpUsers(ITestContext context) throws Exception {
        System.out.println("init TchrStdtSclGrps");
        return DataProviderUtil.cartesianProviderFrom(TchrPosts_SclGrp.TchrSclGrp(context), StdtSclGrp(context));
    }

    @DataProvider(name = "UsrsWrkngGrpTchrStdtSclGrps")
    public static Iterator<Object[]> UsrsWrkngGrpTchrStdtSclGrps(ITestContext context) throws Exception {
        System.out.println("init UsrsWrkngGrpTchrStdtSclGrps");
        return DataProviderUtil.cartesianProviderFrom(UsrCrtn_AsgnRole_WrkngGrp.Users(context),
                UsrCrtn_AsgnRole_WrkngGrp.WrkngGrp(context), TchrPosts_SclGrp.TchrSclGrp(context),
                StdtSclGrp(context));
    }
    
    @DataProvider(name = "StdtSclGrpDebug")
    public static Object[][] StdtSclGrpDebug(ITestContext context) throws Exception {
        System.out.println("init StdtSclGrpDebug");
        return (stdtSclGrpArray);
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testStudentLogin(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("SmokeTests")) {
            a.login(UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][1]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("stdtUsrName"));
        }
    }

    /**
     * Create Live Session in Teacher's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = TchrPosts_SclGrp.class,
          groups = {"fullsmoke", "criticalsmoke", "stdtLvSsn.create"})
    public void testStudentCreateLiveSession(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.accessSclGrpWall(tchrSclGrpName);
        a.accessLvSsnWall();
        a.createLiveSsn(tchrSclGrpName);
    }

    /**
     * Creates own Social Group
     *
     * @throws Exception
     */
    @Test(groups = {"fullsmoke", "criticalsmoke", "stdtSclGrp.create"})
    public void testStudentCreateSocialGroup() throws Exception {
        a.navigateToMySocialGroups();
        stdtSclGrpArray[0][0] = a.createSocialGroup();
        System.out.println("stdtSclGrpName: " + stdtSclGrpArray[0][0]);
        Reporter.log("stdtSclGrpName: " + stdtSclGrpArray[0][0]);
    }

    /**
     * Verify WrkngGrp & Google Doc
     *
     * @throws Exception
     */
    @Test(dataProvider = "WrkngGrpGgleDoc", dataProviderClass = TchrLvSsn_GglDoc.class,
          groups = {"fullsmoke", "wrkngGrp.stdtVrfyGglDoc"})
    public void testStudentVerifyWorkingGroup_GoogleDoc(String wrkngGrpName, String gglDocName) throws Exception {
        a.navigateToWorkingGroups();
        a.vrfyWrkngGrp_GglDoc(wrkngGrpName, gglDocName);
    }

    /**
     * Verify Activities & resource items appear in activity report
     *
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsActivities", dataProviderClass = Crs_GrpCrsCreation.class,
          groups = {"fullsmoke", "activites.stdtVrfy"})
    public void testStudentVerifyActivities(String grpCrsName, String frmActvyName, String quizActvtyName, String allInOneAsgnmntAvtvtyName, String pageActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(frmActvyName, quizActvtyName, allInOneAsgnmntAvtvtyName, pageActvtyName);
    }

    /**
     * User attempt to 'True/False' question in Quiz Assignment
     *
     * @param grpCrsName
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsQz", dataProviderClass = Crs_GrpCrsCreation.class,
          groups = {"fullsmoke", "criticalsmoke", "activites.sbmtQuiz"})
    public void testSubmitQuiz(String grpCrsName, String quizActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.navigateToActvtyRprt();
        a.submitQuiz(quizActvtyName);
    }
    
    /**
     * Verify Assignment Grade
     * 
     * @param grpCrsName
     * @param allInOneAsgnmntAvtvtyName
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsAssgnmnt", dataProviderClass = Crs_GrpCrsCreation.class,
          groups = {"fullsmoke", "activites.vrfyAsgnmntGrade"})
    public void testVerifyAssignmentGrade(String grpCrsName, String allInOneAsgnmntAvtvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.navigateToGrades();
        a.vrfyAsgnmntGrade(allInOneAsgnmntAvtvtyName);
    }
    
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
