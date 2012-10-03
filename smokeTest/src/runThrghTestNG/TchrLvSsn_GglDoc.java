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

        System.out.println("init GglDoc");

        if (test.equalsIgnoreCase("SmokeTests")) {
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
    @BeforeClass
    public void testTchrLgn(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("SmokeTests")) {
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
    //@Test(dataProvider = "TchrSclGrp", dataProviderClass = TchrPosts_SclGrp.class, dependsOnMethods = {"runThrghTestNG.TchrPosts_SclGrp.testTchrCrtSclGrp"})
    @Test
    public void testTchrCrtLvSsn(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.accessSclGrpWall(tchrSclGrpName);
        a.accessLvSsnWall();
        a.createLiveSsn(tchrSclGrpName);
    }

    /**
     * Create GoogleDoc in Working Group
     *
     * @throws Exception
     */
    //@Test(dataProvider = "WrkngGrp", dataProviderClass = UsrCrtn_AsgnRole_WrkngGrp.class, dependsOnMethods = {"runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAddMbrsToWrkngGrp"})
    @Test
    public void testTchrCrtGglDoc(String wrkngGrpName) throws Exception {
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
    //@Test(dataProvider = "GrpCrsActivities", dataProviderClass = Crs_GrpCrsCreation.class, dependsOnMethods = {"runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAsgnRole", "runThrghTestNG.Crs_GrpCrsCreation.testActivities_Creation"})
    @Test
    public void testTchrVrfyActivities(String grpCrsName, String frmActvyName, String quizActvtyName, String allInOneAsgnmntAvtvtyName, String pageActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(frmActvyName, quizActvtyName, allInOneAsgnmntAvtvtyName, pageActvtyName);
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass
    public void testTchrLogOut() throws Exception {
        a.logOut();
    }
}
