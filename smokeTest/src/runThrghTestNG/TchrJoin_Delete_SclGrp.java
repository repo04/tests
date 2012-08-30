/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.annotations.Test;

/**
 *
 * @author somesh.bansal
 */
public class TchrJoin_Delete_SclGrp extends BaseClass {

    @Test
    public void testTchrLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.tchrUsrName);
    }

    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrJoinsStdtSclGrp() throws Exception{
        a.navigateToMySocialGroups();
        a.findSocialGroup(StdtPosts_SclGrp_GglDoc.stdtSclGrpName);
        a.joinSocialGroup(StdtPosts_SclGrp_GglDoc.stdtSclGrpName);
    }

    @Test(dependsOnMethods = {"testTchrJoinsStdtSclGrp"})
    public void testTchrLeavesStdtSclGrp() throws Exception{
        a.navigateToMySocialGroups();
        a.leaveSocialGroup(StdtPosts_SclGrp_GglDoc.stdtSclGrpName);
    }

    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrDeleteSclGrp() throws Exception{
        a.navigateToMySocialGroups();
        a.deleteSocialGroup(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
    }

    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrVrfyURLPsts_TopNews_RcntNews() throws Exception{
        a.navigateToMyHome();
        a.vrfyURLPstsAsTopNews_RcntNews(TchrPosts_SclGrp_GglDoc.tchrUrlWallPost, TchrPosts_SclGrp_GglDoc.tchrUrlCrsPost, TchrPosts_SclGrp_GglDoc.tchrUrlPostOnStdtWall, StdtPosts_SclGrp_GglDoc.stdtUrlPostOnTchrSclGrp);
    }
}
