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
 * Student logs in
 * Find & Join Teacher's Scl Grp
 * Post/Verify URL on Tchr's SclGrp 
 * Create Live Session in Teacher's Social Group 
 * Creates own Social Group
 * Verify Google Doc 
 * Verify All Posts on Top/Recent News 
 * Verify Activities & resource items appear in activity report
 */
@Listeners({runThrghTestNG.TestNGCustomReport.class})
public class StdtPosts_SclGrp_GglDoc extends BaseClass {

    static String stdtSclGrpName;
    static String stdtUrlPostOnTchrSclGrp;
    Actions a = new Actions();

    //Student logs in
    @BeforeClass
    public void testStdtLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.stdtUsrName);
    }

    //Creates own Social Group
    @Test
    public void testStdtCrtSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        stdtSclGrpName = a.createSocialGroups();
        System.out.println("stdtSclGrpName: " + stdtSclGrpName);
        Reporter.log("stdtSclGrpName: " + stdtSclGrpName);
    }

    //Find & Join Teacher's Social Group 
    @Test
    public void testStdtJoinsTchrSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        a.joinSocialGroup(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
    }

    //Verify WrkngGrp & Google Doc
    @Test
    public void testStdtVrfyWrkGrp_GglDoc() throws Exception {
        a.navigateToWorkingGroups();
        a.vrfyWrkngGrp_GglDoc(UsrCrtn_AsgnRole_WrkngGrp.wrkngGrpName, TchrPosts_SclGrp_GglDoc.gglDocName);
    }

    //Post/Verify URL on Tchr's Scl Grp
    @Test(dependsOnMethods = {"testStdtJoinsTchrSclGrp"})
    public void testStdtPostURLOnTchrSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.accessSclGrpWall(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        stdtUrlPostOnTchrSclGrp = a.urlPost("urlSclGrpPost");
        System.out.println("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);
        Reporter.log("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);
    }

    //Create Live Session in Teacher's Social Group
    @Test(dependsOnMethods = {"testStdtJoinsTchrSclGrp"})
    public void testStdtCrtLvSsn() throws Exception {
        a.navigateToMySocialGroups();
        a.accessSclGrpWall(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        a.accessLvSsnWall();
        a.createLiveSsn(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
    }

    //Verify All Posts on Top/Recent News  
    @Test(dependsOnMethods = {"testStdtPostURLOnTchrSclGrp"})
    public void testStdtVrfyURLPsts_Top_RcntNews() throws Exception {
        a.navigateToMyHome();
        a.vrfyURLPstsAsTop_RcntNews(stdtUrlPostOnTchrSclGrp, TchrPosts_SclGrp_GglDoc.tchrUrlCrsPost, TchrPosts_SclGrp_GglDoc.tchrUrlWallPost);
    }

    //Verify Activities & resource items appear in activity report
    @Test
    public void testStdtVrfyActivities() throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(Crs_GrpCrsCreation.frmActvyName, Crs_GrpCrsCreation.quizActvtyName, Crs_GrpCrsCreation.allInOneAsgnmntAvtvtyName, Crs_GrpCrsCreation.pageActvtyName);
    }

    @AfterClass
    public void testStdtLogOut() throws Exception {
        a.logOut();
    }
}
