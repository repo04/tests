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
 * PES Admin Logs in Create Two Users Assign/Enroll users to GrpCourse as
 * Teacher/Student roles Create Working Group & add users as members Logs out
 */
@Listeners({runThrghTestNG.TestNGCustomReport.class})
public class UsrCrtn_AsgnRole_WrkngGrp extends BaseClass {

    static String tchrUsrName;
    static String stdtUsrName;
    static String wrkngGrpName;
    Actions a = new Actions();

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, PES Admin Logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testPESAdminLgn() throws Exception {
        a.login("pesAdmin");
    }

    /**
     * Create Two Users
     *
     * @throws Exception
     */
    @Test
    public void testUsrCrtn() throws Exception {
        a.navigateToMyContacts();
        tchrUsrName = a.createUser("teacher");
        System.out.println("tchrUsrName: " + tchrUsrName);
        Reporter.log("tchrUsrName: " + tchrUsrName);

        a.navigateToMyContacts();
        stdtUsrName = a.createUser("student");
        System.out.println("stdtUsrName: " + stdtUsrName);
        Reporter.log("stdtUsrName: " + stdtUsrName);
    }

    /**
     * Assign/Enroll users to GrpCourse as Teacher/Student roles
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"testUsrCrtn"})
    public void testAsgnRole() throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        a.enrollUsrToRole_GrpCrs(tchrUsrName, Crs_GrpCrsCreation.grpCrsName);

        a.navigateToMyCourse();
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        a.enrollUsrToRole_GrpCrs(stdtUsrName, Crs_GrpCrsCreation.grpCrsName);
    }

    /**
     * Create Working Group
     *
     * @throws Exception
     */
    @Test
    public void testCrtWrkgnGrp() throws Exception {
        a.navigateToWorkingGroups();
        wrkngGrpName = a.createWorkingGroup();
        System.out.println("wrkngGrp: " + wrkngGrpName);
        Reporter.log("wrkngGrp: " + wrkngGrpName);
    }

    /**
     * Add users as members to Working Group
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"testCrtWrkgnGrp", "testAsgnRole"})
    public void testAddMbrsToWrkngGrp() throws Exception {
        a.navigateToWorkingGroups();
        System.out.println(wrkngGrpName);
        a.accessWrknGrp(wrkngGrpName);
        a.addMbrsToWrkngGrp(tchrUsrName, stdtUsrName);
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass
    public void testPESAdminLogOut() throws Exception {
        a.logOut();
    }
}
