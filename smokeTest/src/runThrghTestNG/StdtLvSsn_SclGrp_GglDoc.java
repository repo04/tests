/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

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

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testStdtLgn(ITestContext context) throws Exception {
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
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = TchrPosts_SclGrp.class)
    public void testStdtCrtLvSsn(String tchrSclGrpName) throws Exception {
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
    @Test
    public void testStdtCrtSclGrp() throws Exception {
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
    @Test(dataProvider = "WrkngGrpGgleDoc", dataProviderClass = TchrLvSsn_GglDoc.class)
    public void testStdtVrfyWrkGrp_GglDoc(String wrkngGrpName, String gglDocName) throws Exception {
        a.navigateToWorkingGroups();
        a.vrfyWrkngGrp_GglDoc(wrkngGrpName, gglDocName);
    }

    /**
     * Verify Activities & resource items appear in activity report
     *
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsActivities", dataProviderClass = Crs_GrpCrsCreation.class)
    public void testStdtVrfyActivities(String grpCrsName, String frmActvyName, String quizActvtyName, String allInOneAsgnmntAvtvtyName, String pageActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(frmActvyName, quizActvtyName, allInOneAsgnmntAvtvtyName, pageActvtyName);
    }

    /**
     *
     * @param grpCrsName
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsQz", dataProviderClass = Crs_GrpCrsCreation.class)
    public void testSubmitQuiz(String grpCrsName, String quizActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.navigateToActvtyRprt();
        a.submitQuiz(quizActvtyName);
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass
    public void testStdtLogOut() throws Exception {
        a.logOut();
    }
}
