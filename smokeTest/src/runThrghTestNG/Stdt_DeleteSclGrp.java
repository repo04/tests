/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 * Teacher Logs in Find, Join & Leave Student's Social Group, Deletes own Social
 * Group
 */
public class Stdt_DeleteSclGrp extends BaseClass {

    Actions a = new Actions();

    /**
     * Teacher Logs in
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
     * Deletes own Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "StdtSclGrp", dataProviderClass = StdtLvSsn_SclGrp_GglDoc.class)
    public void testStdtDeleteSclGrp(String stdtSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.deleteSocialGroup(stdtSclGrpName);
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
