/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 *
 * Teacher LOGS in, Find SclGroup & Create LiveSession in SclGroup,
 * Create GoogleDoc in Working Group
 * Verify All Posts on Top & RecentNews Verify Activities & resource appear
 * items on activity report
 */
public class TchrLvSsn_GglDoc extends BaseClass {

    static String gglDocName;
    Actions a = new Actions();

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testTchrLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.tchrUsrName);
    }

    /**
     * Find SclGroup & Create LiveSession in SclGroup
     *
     * @throws Exception
     */
    @Test
    public void testTchrCrtLvSsn() throws Exception {
        a.navigateToMySocialGroups();
        a.accessSclGrpWall(TchrPosts_SclGrp.tchrSclGrpName);
        a.accessLvSsnWall();
        a.createLiveSsn(TchrPosts_SclGrp.tchrSclGrpName);
    }

    /**
     * Create GoogleDoc in Working Group
     *
     * @throws Exception
     */
    @Test
    public void testTchrCrtGglDoc() throws Exception {
        a.navigateToWorkingGroups();
        gglDocName = a.createGoogleDoc(UsrCrtn_AsgnRole_WrkngGrp.wrkngGrpName);
        System.out.println("gglDocName: " + gglDocName);
        Reporter.log("gglDocName: " + gglDocName);
    }

    /**
     * Verify Activities & resource appear items on activity report
     *
     * @throws Exception
     */
    @Test
    public void testTchrVrfyActivities() throws Exception {
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
    public void testTchrLogOut() throws Exception {
        a.logOut();
    }
}
