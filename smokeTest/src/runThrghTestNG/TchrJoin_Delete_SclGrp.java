/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 * Teacher Logs in Find, Join & Leave Student's Social Group Deletes own Social
 * Group Verify All Posts on Top/Recent News
 */
@Listeners({runThrghTestNG.TestNGCustomReport.class})
public class TchrJoin_Delete_SclGrp extends BaseClass {

    Actions a = new Actions();

    //Teacher Logs in
    @BeforeClass
    public void testTchrLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.tchrUsrName);
    }

    //Find & Join Student's Social Group 
    @Test
    public void testTchrJoinsStdtSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(StdtPosts_SclGrp_GglDoc.stdtSclGrpName);
        a.joinSocialGroup(StdtPosts_SclGrp_GglDoc.stdtSclGrpName);
    }

    //Leaves Student's Social Group 
    @Test(dependsOnMethods = {"testTchrJoinsStdtSclGrp"})
    public void testTchrLeavesStdtSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.leaveSocialGroup(StdtPosts_SclGrp_GglDoc.stdtSclGrpName);
    }

    //Deletes own Social Group
    @Test
    public void testTchrDeleteSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.deleteSocialGroup(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
    }

    //Verify All Posts on Top/Recent News
    @Test
    public void testTchrVrfyStdtURLPsts_Top_RcntNews() throws Exception {
        a.navigateToMyHome();
        a.vrfyURLPstsAsTop_RcntNews(TchrPosts_SclGrp_GglDoc.tchrUrlWallPost, TchrPosts_SclGrp_GglDoc.tchrUrlCrsPost, StdtPosts_SclGrp_GglDoc.stdtUrlPostOnTchrSclGrp);
    }

    @AfterClass
    public void testTchrLogOut() throws Exception {
        a.logOut();
    }
}
