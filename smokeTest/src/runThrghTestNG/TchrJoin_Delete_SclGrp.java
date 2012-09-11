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
 * Teacher Logs in Find, Join & Leave Student's Social Group, Deletes own Social
 * Group
 */
@Listeners({runThrghTestNG.TestNGCustomReport.class})
public class TchrJoin_Delete_SclGrp extends BaseClass {

    Actions a = new Actions();

    /**
     * Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testTchrLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.tchrUsrName);
    }

    /**
     * Find & Join Student's Social Group
     *
     * @throws Exception
     */
    @Test
    public void testTchrJoinsStdtSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(StdtLvSsn_SclGrp_GglDoc.stdtSclGrpName);
        a.joinSocialGroup(StdtLvSsn_SclGrp_GglDoc.stdtSclGrpName);
    }

    /**
     * Leaves Student's Social Group
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"testTchrJoinsStdtSclGrp"})
    public void testTchrLeavesStdtSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.leaveSocialGroup(StdtLvSsn_SclGrp_GglDoc.stdtSclGrpName);
    }

    /**
     * Deletes own Social Group
     *
     * @throws Exception
     */
    @Test
    public void testTchrDeleteSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.deleteSocialGroup(TchrPosts_SclGrp.tchrSclGrpName);
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
