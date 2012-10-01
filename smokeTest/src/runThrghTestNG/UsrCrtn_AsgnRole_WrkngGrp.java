/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.util.Iterator;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 * PES Admin Logs in Create Two Users Assign/Enroll users to GrpCourse as
 * Teacher/Student roles Create Working Group & add users as members Logs out
 */
public class UsrCrtn_AsgnRole_WrkngGrp extends BaseClass {

    Actions a = new Actions();
    static String[][] usrsArray = new String[1][2];
    static String[][] wrkngGrpArray = new String[1][1];

    @DataProvider(name = "Users")
    public static Object[][] Users(ITestContext context) throws Exception {

        System.out.println("init Users");

        if (test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("if Users: " + test);
            return (usrsArray);
        } else {
            System.out.println("else Users: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("tchrUsrName"), context.getCurrentXmlTest().getParameter("stdtUsrName")}};
        }
    }

    @DataProvider(name = "WrkngGrp")
    public static Object[][] WrkngGrp(ITestContext context) throws Exception {

        System.out.println("init WrkngGrp");

        if (test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("if WrkngGrp: " + test);
            return (wrkngGrpArray);
        } else {
            System.out.println("else WrkngGrp: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("wrkngGrpName")}};
        }
    }

    @DataProvider(name = "WrkngGrpUsers")
    public static Iterator<Object[]> WrkngGrpUsers(ITestContext context) throws Exception {
        System.out.println("init WrkngGrpUsers");
        return DataProviderUtil.cartesianProviderFrom(WrkngGrp(context), Users(context));
    }

    @DataProvider(name = "GrpCrsUsers")
    public static Iterator<Object[]> GrpCrsUsers(ITestContext context) throws Exception {
        System.out.println("init GrpCrsUsers");
        return DataProviderUtil.cartesianProviderFrom(Crs_GrpCrsCreation.Course(context), Users(context));
    }

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

        /*System.out.println("init testUsrCrtn");
         usrArray[0][0] = "user1";
         usrArray[0][1] = "user2";*/

        a.navigateToMyContacts();
        usrsArray[0][0] = a.createUser("teacher");
        System.out.println("tchrUsrName: " + usrsArray[0][0]);
        Reporter.log("tchrUsrName: " + usrsArray[0][0]);

        a.navigateToMyContacts();
        usrsArray[0][1] = a.createUser("student");
        System.out.println("stdtUsrName: " + usrsArray[0][1]);
        Reporter.log("stdtUsrName: " + usrsArray[0][1]);
    }

    /**
     * Assign/Enroll users to GrpCourse as Teacher/Student roles
     *
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsUsers", dependsOnMethods = {"runThrghTestNG.Crs_GrpCrsCreation.testCrsGrpCrs_Creation", "testUsrCrtn"})
    public void testAsgnRole(String grpCrsName, String tchrUsrName, String stdtUsrName) throws Exception {

        System.out.println("1st: " + grpCrsName);
        System.out.println("2nd: " + tchrUsrName);
        System.out.println("3rd: " + stdtUsrName);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.enrollUsrToRole_GrpCrs(tchrUsrName, grpCrsName);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.enrollUsrToRole_GrpCrs(stdtUsrName, grpCrsName);
    }

    /**
     * Create Working Group
     *
     * @throws Exception
     */
    @Test
    public void testCrtWrkgnGrp() throws Exception {

        /*System.out.println("init testCrtWrkgnGrp");
         wrkngGrpArray[0][0] = "wrkngGrp1";*/

        a.navigateToWorkingGroups();
        wrkngGrpArray[0][0] = a.createWorkingGroup();
        System.out.println("wrkngGrp: " + wrkngGrpArray[0][0]);
        Reporter.log("wrkngGrp: " + wrkngGrpArray[0][0]);
    }

    /**
     * Add users as members to Working Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "WrkngGrpUsers", dependsOnMethods = {"testCrtWrkgnGrp", "testUsrCrtn"})
    public void testAddMbrsToWrkngGrp(String wrkngGrpName, String tchrUsrName, String stdtUsrName) throws Exception {
        a.navigateToWorkingGroups();
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
