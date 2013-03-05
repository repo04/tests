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

public class Student_Support_Tests extends BaseClass {
    Actions a = new Actions();
    
    @BeforeClass(groups = {"prerequisite"})
    public void testStudentLogin(ITestContext context) throws Exception {
        if(test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            a.login(Pes_UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][0]);
        }
        else {
            a.login(context.getCurrentXmlTest().getParameter("stdtUsrName"));
        }
    }
    
    @Test(groups = {"support.uiVerify"})
    public void testStudentSupport() {
        a.navigateToStudentSupport();
        a.testStudentSupport();
    }

    @AfterClass(groups = {"prerequisite"})
    public void testStudentLogOut() throws Exception {
        a.logOut();
    }
    
}
