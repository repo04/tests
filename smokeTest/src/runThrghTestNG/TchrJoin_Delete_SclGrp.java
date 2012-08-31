/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;


import org.testng.annotations.Test;

/**
 * Teacher Logs in 
 * Find, Join & Leave Student's Social Group 
 * Deletes own Social Group
 * Verify All Posts on Top/Recent News
 *
 * @author somesh.bansal
 */
public class TchrJoin_Delete_SclGrp extends BaseClass {

    //Teacher Logs in
    @Test
    public void testTchrLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.tchrUsrName);
    }

    //Find & Join Student's Social Group 
    @Test(dependsOnMethods = {"testTchrLgn"})
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
    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrDeleteSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.deleteSocialGroup(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
    }

    //Verify All Posts on Top/Recent News
    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrVrfyStdtURLPsts_Top_RcntNews() throws Exception {
        a.navigateToMyHome();
        a.vrfyURLPstsAsTopNews_RcntNews(TchrPosts_SclGrp_GglDoc.tchrUrlWallPost, TchrPosts_SclGrp_GglDoc.tchrUrlCrsPost, TchrPosts_SclGrp_GglDoc.tchrUrlPostOnStdtWall, StdtPosts_SclGrp_GglDoc.stdtUrlPostOnTchrSclGrp);
    }
}
