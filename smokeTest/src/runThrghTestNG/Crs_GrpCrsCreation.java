/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import smoketest.Actions;

/* 
 * Content Admin logs in 
 * Create - Course ,GrpCourse, Activities like Forum, Quiz, All In One Assignment & Page 
 * Logs out
 */
public class Crs_GrpCrsCreation extends BaseClass {

    //static String crsName;
    static String grpCrsName;
    static String frmActvyName;
    static String quizActvtyName;
    static String allInOneAsgnmntAvtvtyName;
    static String pageActvtyName;
    Actions a = new Actions();
    static String[][] crsArray = new String[1][2];
    static String[][] actvtsArray = new String[1][4];

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testCntntAdminLgn() throws Exception {
        System.out.println("testName: " + test);
        a.login("contentAdmin");
    }

    @DataProvider(name = "Course")
    public static Object[][] Course(ITestContext context) throws Exception {

        if (test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside testName: " + test);
            Object[][] abc = createData1();
            return (abc);
        } else {
            System.out.println("Inside testName: " + test);
            //Object[][] def = createData2(context);
            //return (def);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("crsNam"), context.getCurrentXmlTest().getParameter("grpCrsNam")}};
        }
    }

    @DataProvider(name = "Activites")
    public static Object[][] Activites(ITestContext context) throws Exception {

        if (test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside testName: " + test);
            return (actvtsArray);
        } else {
            System.out.println("Inside testName: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("crsNam"), context.getCurrentXmlTest().getParameter("grpCrsNam")}};
        }
    }

    /**
     * Create - Course & GrpCourse
     *
     * @throws Exception
     */
    @Test
    public void testCrsGrpCrs_Creation() throws Exception {
        
        crsArray[0][0]="crs1";
        crsArray[0][1]="crs2";

        /*a.navigateToMyCourse();
        crsArray[0][0] = a.createCourse();
        System.out.println("crsName: " + crsArray[0][0]);
        Reporter.log("crsName: " + crsArray[0][0]);

        a.navigateToMyCourse();
        crsArray[0][1] = a.createGrpCourse(crsArray[0][0]);
        System.out.println("grpCrsName: " + crsArray[0][1]);
        Reporter.log("grpCrsName: " + crsArray[0][1]);*/
    }

    /**
     * Create - Activities like Forum, Quiz, All In One Assignment & Page
     *
     * @throws Exception
     */
    /*@Test(dataProvider = "Activites", dependsOnMethods = {"testCrsGrpCrs_Creation"})
    public void testActivities_Creation(String crsNm, String grpCrsName) throws Exception {

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        actvtsArray[0][0] = a.createForumActivity();
        System.out.println("frmActvyName: " + actvtsArray[0][0]);
        Reporter.log("frmActvyName: " + actvtsArray[0][0]);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        actvtsArray[0][1] = a.createQuizActivity();
        System.out.println("quizActvtyName: " + actvtsArray[0][1]);
        Reporter.log("quizActvtyName: " + actvtsArray[0][1]);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        actvtsArray[0][2] = a.createAllInOneAsgnmntActivity();
        System.out.println("allInOneAsgnmntAvtvtyName: " + actvtsArray[0][2]);
        Reporter.log("allInOneAsgnmntAvtvtyName: " + actvtsArray[0][2]);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        actvtsArray[0][3] = a.createPageResource();
        System.out.println("pageActvtyName: " + actvtsArray[0][3]);
        Reporter.log("pageActvtyName: " + actvtsArray[0][4]);
    }*/

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run, User logsOut
     *
     * @throws Exception
     */
    @AfterClass
    public void testCntntAdminLogOut() throws Exception {
        a.logOut();
    }

    public static Object[][] createData1() {
        return (crsArray);
    }

    public Object[][] createData2(ITestContext context) {
        return new Object[][]{{context.getCurrentXmlTest().getParameter("crsNam"), context.getCurrentXmlTest().getParameter("grpCrsNam")}};
    }
}
