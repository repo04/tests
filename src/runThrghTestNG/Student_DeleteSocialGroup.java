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
public class Student_DeleteSocialGroup extends BaseClass {

    Actions a = new Actions();

    /**
     * Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testStudentLogIn(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            a.login(Pes_UserCreation_AssignRole_WorkingGroup.usrsArray[0][1]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("stdtUsrName"));
        }
    }

    /**
     * Deletes own Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "StdtSclGrp", dataProviderClass = Student_LiveSession_SocialGroup_GoogleDoc.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "socialGroup.studentDelete"})
    public void testStudentDeleteSocialGroup(String stdtSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(stdtSclGrpName);
        a.deleteSocialGroup(stdtSclGrpName);
    }
    
    
    /**
     * 
     */
    @Test(groups = {"regressionSmoke", "support.uiVerify"})
    public void testStudentSupport() {
        a.navigateToStudentSupport();
        a.testStudentSupport();
    }
    

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testStudentLogOut() throws Exception {
        a.logOut();
    }
}
