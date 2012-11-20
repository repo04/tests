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
public class TchrJoin_Delete_SclGrp extends BaseClass {

    Actions a = new Actions();

    /**
     * Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testTeacherLogin(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("SmokeTests")) {
            a.login(UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][0]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("tchrUsrName"));
        }
    }

    /**
     * Find & Join Student's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "StdtSclGrp", dataProviderClass = StdtLvSsn_SclGrp_GglDoc.class,
          groups = {"fullsmoke", "criticalsmoke", "studentSocialGroup.teacherJoins"})
    public void testTeacherJoinsStudentSocialGroup(String stdtSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(stdtSclGrpName);
        a.joinSocialGroup(stdtSclGrpName);
    }

    /**
     * Leaves Student's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "StdtSclGrp", dataProviderClass = StdtLvSsn_SclGrp_GglDoc.class,
          groups = {"fullsmoke", "criticalsmoke", "studentSocialGroup.teacherLeaves"})
    public void testTeacherLeavesStudentSocialGroup(String stdtSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.leaveSocialGroup(stdtSclGrpName);
    }

    /**
     * Deletes own Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = TchrPosts_SclGrp.class,
          groups = {"fullsmoke", "criticalsmoke", "teacherSocialGroup.delete"})
    public void testTeacherDeleteSocialGroup(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.deleteSocialGroup(tchrSclGrpName);
    }
    
    /**
     * Allow Assignment to be resubmitted
     * 
     * @param grpCrsName
     * @param allInOneAsgnmntAvtvtyName
     * @param stdtUsrName
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsAsgnmntStdt", dataProviderClass = UsrCrtn_AsgnRole_WrkngGrp.class,
          groups = {"fullsmoke", "activites.allowResubmitAssignment"})
    public void testTeacherAllowResubmitAssignment(String grpCrsName, String allInOneAsgnmntAvtvtyName, String stdtUsrName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToGrades();
        a.allowResubmitAssignment(allInOneAsgnmntAvtvtyName, stdtUsrName);
    }
    
    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testTeacherLogOut() throws Exception {
        a.logOut();
    }
}
