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
 * Student logs in Find & Join Teacher's Scl Grp Post/Verify URL on Tchr's
 * SclGrp Create Live Session in Teacher's Social Group Creates own Social Group
 * Verify Google Doc Verify All Posts on Top/Recent News Verify Activities &
 * resource items appear in activity report
 */
@Listeners({runThrghTestNG.TestNGCustomReport.class})
public class StdtPosts_SclGrp_GglDoc extends BaseClass {

    static String stdtSclGrpName;
    static String stdtUrlPostOnTchrSclGrp;
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
     * Find & Join Teacher's Social Group
     *
     * @throws Exception
     */
    @Test
    public void testStdtJoinsTchrSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        a.joinSocialGroup(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
    }

    /**
     * Post/Verify URL on Teacher's SocialGroup
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"testStdtJoinsTchrSclGrp"})
    public void testStdtPostURLOnTchrSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.accessSclGrpWall(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        stdtUrlPostOnTchrSclGrp = a.urlPost("urlSclGrpPost");
        System.out.println("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);
        Reporter.log("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);
    }

    /**
     * Create Live Session in Teacher's Social Group
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"testStdtPostURLOnTchrSclGrp"}, alwaysRun = true)
    public void testStdtCrtLvSsn() throws Exception {
        a.navigateToMySocialGroups();
        a.accessSclGrpWall(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        a.accessLvSsnWall();
        a.createLiveSsn(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
    }

    /**
     * Creates own Social Group
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"testStdtCrtLvSsn"}, alwaysRun = true)
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
    @Test(dependsOnMethods = {"testStdtCrtSclGrp"}, alwaysRun = true)
    public void testStdtVrfyWrkGrp_GglDoc() throws Exception {
        a.navigateToWorkingGroups();
        a.vrfyWrkngGrp_GglDoc(UsrCrtn_AsgnRole_WrkngGrp.wrkngGrpName, TchrPosts_SclGrp_GglDoc.gglDocName);
    }

    /**
     * Verify Activities & resource items appear in activity report
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"testStdtVrfyWrkGrp_GglDoc"}, alwaysRun = true)
    public void testStdtVrfyActivities() throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(Crs_GrpCrsCreation.frmActvyName, Crs_GrpCrsCreation.quizActvtyName, Crs_GrpCrsCreation.allInOneAsgnmntAvtvtyName, Crs_GrpCrsCreation.pageActvtyName);
    }

    /**
     * Verify All Posts (stdtUrlPostOnTchrSclGrp, tchrUrlWallPost,
     * tchrUrlCrsPost) on Top/Recent News
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"testStdtPostURLOnTchrSclGrp", "testStdtVrfyActivities"})
    public void testStdtVrfyURLPsts_Top_RcntNews() throws Exception {
        a.navigateToMyHome();
        a.vrfyURLPstsAsTop_RcntNews(stdtUrlPostOnTchrSclGrp, TchrPosts_SclGrp_GglDoc.tchrUrlWallPost, TchrPosts_SclGrp_GglDoc.tchrUrlCrsPost);
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
