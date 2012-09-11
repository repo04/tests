/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 * Student logs in, Create Live Session in Teacher's Social Group,
 * Creates own Social Group
 * Verify Google Doc Verify All Posts on Top/Recent News Verify Activities &
 * resource items appear in activity report
 */
@Listeners({runThrghTestNG.TestNGCustomReport.class})
public class StdtLvSsn_SclGrp_GglDoc extends BaseClass {

    static String stdtSclGrpName;
    Actions a = new Actions();

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testStdtLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.stdtUsrName);
    }

    /**
     * Create Live Session in Teacher's Social Group
     *
     * @throws Exception
     */
    @Test
    public void testStdtCrtLvSsn() throws Exception {
        a.navigateToMySocialGroups();
        a.accessSclGrpWall(TchrPosts_SclGrp.tchrSclGrpName);
        a.accessLvSsnWall();
        a.createLiveSsn(TchrPosts_SclGrp.tchrSclGrpName);
    }

    /**
     * Creates own Social Group
     *
     * @throws Exception
     */
    @Test
    public void testStdtCrtSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        stdtSclGrpName = a.createSocialGroup();
        System.out.println("stdtSclGrpName: " + stdtSclGrpName);
        Reporter.log("stdtSclGrpName: " + stdtSclGrpName);
    }

    /**
     * Verify WrkngGrp & Google Doc
     *
     * @throws Exception
     */
    @Test
    public void testStdtVrfyWrkGrp_GglDoc() throws Exception {
        a.navigateToWorkingGroups();
        a.vrfyWrkngGrp_GglDoc(UsrCrtn_AsgnRole_WrkngGrp.wrkngGrpName, TchrLvSsn_GglDoc.gglDocName);
    }

    /**
     * Verify Activities & resource items appear in activity report
     *
     * @throws Exception
     */
    @Test
    public void testStdtVrfyActivities() throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(Crs_GrpCrsCreation.frmActvyName, Crs_GrpCrsCreation.quizActvtyName, Crs_GrpCrsCreation.allInOneAsgnmntAvtvtyName, Crs_GrpCrsCreation.pageActvtyName);
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
