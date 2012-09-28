/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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

    static String tchrUsrName;
    static String stdtUsrName;
    static String wrkngGrpName;
    Actions a = new Actions();
    static String[][] usrArray = new String[1][2];
    static String[][] wrkngGrpArray = new String[1][1];
    static List<Object[][]> UsersWrkngGrp;

    @DataProvider(name = "Users")
    public static Object[][] Users(ITestContext context) throws Exception {

        if (test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside testName: " + test);
            return (usrArray);
        } else {
            System.out.println("Inside testName: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("tchrUsrName"), context.getCurrentXmlTest().getParameter("stdtUsrName")}};
        }
    }

    @DataProvider(name = "WrkngGrp")
    public static Object[][] WrkngGrp(ITestContext context) throws Exception {

        if (test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside testName: " + test);
            return (usrArray);
        } else {
            System.out.println("Inside testName: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("tchrUsrName"), context.getCurrentXmlTest().getParameter("stdtUsrName")}};
        }
    }

    @DataProvider(name = "UsersWrkngGrp")
    public static Iterator<Object[][]> UsersWrkngGrp(ITestContext context) throws Exception {

        if (test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside testName: " + test);
            UsersWrkngGrp.add(usrArray);
            UsersWrkngGrp.add(Crs_GrpCrsCreation.createData1());
            UsersWrkngGrp.toArray();
            System.out.println(UsersWrkngGrp.size());
            //return (UsersWrkngGrp);
            return null;
        } else {
            System.out.println("Inside testName: " + test);
            return null;
            //return new Object[][]{{context.getCurrentXmlTest().getParameter("tchrUsrName"), context.getCurrentXmlTest().getParameter("stdtUsrName")}};
        }
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
        
        usrArray[0][0]="user1";
        usrArray[0][1]="user2";
        
        /*a.navigateToMyContacts();
        usrArray[0][0] = a.createUser("teacher");
        System.out.println("tchrUsrName: " + usrArray[0][0]);
        Reporter.log("tchrUsrName: " + usrArray[0][0]);

        a.navigateToMyContacts();
        usrArray[0][1] = a.createUser("student");
        System.out.println("stdtUsrName: " + usrArray[0][1]);
        Reporter.log("stdtUsrName: " + usrArray[0][1]);*/
    }

    /**
     * Assign/Enroll users to GrpCourse as Teacher/Student roles
     *
     * @throws Exception
     */
    @Test(dataProvider = "UsersWrkngGrp", dependsOnMethods = {"runThrghTestNG.Crs_GrpCrsCreation.testCrsGrpCrs_Creation", "testUsrCrtn"})
    public void testAsgnRole(String g, String h, String i, String j) throws Exception {
        
        System.out.println("1st: " + g);
        System.out.println("2nd: " + h);
        System.out.println("3rd: " + i);
        System.out.println("4th: " + j);
        /*a.navigateToMyCourse();
         a.selectGrpCourse(UsersWrkngGrp.get(0));
         a.enrollUsrToRole_GrpCrs(tchrUsrName, grpCrsName);

         a.navigateToMyCourse();
         a.selectGrpCourse(grpCrsName);
         a.enrollUsrToRole_GrpCrs(stdtUsrName, grpCrsName);*/
    }

    /**
     * Create Working Group
     *
     * @throws Exception
     */
    /*@Test
     public void testCrtWrkgnGrp() throws Exception {
     a.navigateToWorkingGroups();
     wrkngGrpArray[0][0] = a.createWorkingGroup();
     System.out.println("wrkngGrp: " + wrkngGrpArray[0][0]);
     Reporter.log("wrkngGrp: " + wrkngGrpArray[0][0]);
     }*/
    /**
     * Add users as members to Working Group
     *
     * @throws Exception
     */
    /*@Test(dependsOnMethods = {"testCrtWrkgnGrp", "testUsrCrtn"})
     public void testAddMbrsToWrkngGrp() throws Exception {
     a.navigateToWorkingGroups();
     a.accessWrknGrp(wrkngGrpName);
     a.addMbrsToWrkngGrp(tchrUsrName, stdtUsrName);
     }*/
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
