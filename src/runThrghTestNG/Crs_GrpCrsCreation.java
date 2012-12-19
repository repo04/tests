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

/* 
 * Content Admin logs in 
 * Create - Course ,GrpCourse, Activities like Forum, Quiz, All In One Assignment & Page 
 * Logs out
 */
public class Crs_GrpCrsCreation extends BaseClass {

    public static String crsName;
    Actions a = new Actions();
    static String[][] crsArray = new String[1][1];
    static String[][] qzNameArray = new String[1][1];
    static String[][] assgnmntName = new String[1][1];
    static String[][] actvtsArray = new String[1][4];

    @DataProvider(name = "Course")
    public static Object[][] Course(ITestContext context) throws Exception {
        System.out.println("init Course");
        if (test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside Course: " + test);
            return (crsArray);
        } else {
            System.out.println("Inside Course: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("grpCrsName")}};
        }
    }

    @DataProvider(name = "Activites")
    public static Object[][] Activites(ITestContext context) throws Exception {

        if (test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside Activites: " + test);
            return (actvtsArray);
        } else {
            System.out.println("Inside Activites: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("frmActvyName"),
                            context.getCurrentXmlTest().getParameter("quizActvtyName"),
                            context.getCurrentXmlTest().getParameter("allInOneAsgnmntAvtvtyName"),
                            context.getCurrentXmlTest().getParameter("pageActvtyName")}};
        }
    }

    @DataProvider(name = "QuizName")
    public static Object[][] QuizName(ITestContext context) throws Exception {

        if (test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside QuizName: " + test);
            return (qzNameArray);
        } else {
            System.out.println("Inside QuizName: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("quizActvtyName")}};
        }
    }
    
    @DataProvider(name = "AssgnmntName")
    public static Object[][] AssgnmntName(ITestContext context) throws Exception {

        if (test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside AssgnmntName: " + test);
            return (assgnmntName);
        } else {
            System.out.println("Inside AssgnmntName: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("allInOneAsgnmntAvtvtyName")}};
        }
    }

    @DataProvider(name = "GrpCrsActivities")
    public static Iterator<Object[]> GrpCrsActivities(ITestContext context) throws Exception {
        System.out.println("init GrpCrsActivities");
        return DataProviderUtil.cartesianProviderFrom(Course(context), Activites(context));
    }

    @DataProvider(name = "GrpCrsQz")
    public static Iterator<Object[]> GrpCrsQz(ITestContext context) throws Exception {
        System.out.println("init GrpCrsQz");
        return DataProviderUtil.cartesianProviderFrom(Course(context), QuizName(context));
    }
    
    @DataProvider(name = "GrpCrsAssgnmnt")
    public static Iterator<Object[]> GrpCrsAssgnmnt(ITestContext context) throws Exception {
        System.out.println("init GrpCrsAssgnmnt");
        return DataProviderUtil.cartesianProviderFrom(Course(context), AssgnmntName(context));
    }

    @DataProvider(name = "GrpCrsDebug")
    public static Object[][] GrpCrsDebug(ITestContext context) throws Exception {
        System.out.println("init GrpCrsDebug");
        return (crsArray);
    }

    @DataProvider(name = "ActivitiesDebug")
    public static Object[][] ActivitiesDebug(ITestContext context) throws Exception {
        System.out.println("init ActivitiesDebug");
        return (actvtsArray);
    }

    @DataProvider(name = "GrpCrsActivitiesDebug")
    public static Iterator<Object[]> GrpCrsActivitiesDebug(ITestContext context) throws Exception {
        System.out.println("init GrpCrsActivitiesDebug");
        return DataProviderUtil.cartesianProviderFrom(Course(context), ActivitiesDebug(context));
    }

    @DataProvider(name = "QzDebug")
    public static Object[][] QzDebug(ITestContext context) throws Exception {
        System.out.println("init QzDebug");
        return (qzNameArray);
    }

    @DataProvider(name = "GrpCrsQzDebug")
    public static Iterator<Object[]> GrpCrsQzDebug(ITestContext context) throws Exception {
        System.out.println("init GrpCrsQzDebug");
        return DataProviderUtil.cartesianProviderFrom(Course(context), QzDebug(context));
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testCntntAdminLgn() throws Exception {
        a.login("contentAdmin");
    }

    /**
     * Create - Course & GrpCourse
     *
     * @throws Exception
     */
    @Test(groups = {"fullsmoke", "course.creation"})
    public void testCrsGrpCrs_Creation() throws Exception {

        a.navigateToMyCourse();
        crsName = a.createCourse();
        System.out.println("crsName: " + crsName);
        Reporter.log("crsName: " + crsName);

        a.navigateToMyCourse();
        crsArray[0][0] = a.createGrpCourse(crsName);
        System.out.println("grpCrsName: " + crsArray[0][0]);
        Reporter.log("grpCrsName: " + crsArray[0][0]);
    }

    /**
     * Create - Activities like Forum, Quiz, All In One Assignment & Page
     *
     * @throws Exception
     */
    @Test(dataProvider = "Course", groups = {"fullsmoke", "activites.creation"})
    public void testActivities_Creation(String grpCrsName) throws Exception {

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        actvtsArray[0][0] = a.createForumActivity();
        System.out.println("frmActvyName: " + actvtsArray[0][0]);
        Reporter.log("frmActvyName: " + actvtsArray[0][0]);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        actvtsArray[0][1] = a.createQuizActivity();
        qzNameArray[0][0] = actvtsArray[0][1];
        System.out.println("quizActvtyName: " + actvtsArray[0][1]);
        Reporter.log("quizActvtyName: " + actvtsArray[0][1]);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        actvtsArray[0][2] = a.createAllInOneAsgnmntActivity();
        assgnmntName[0][0] = actvtsArray[0][2];
        System.out.println("allInOneAsgnmntAvtvtyName: " + actvtsArray[0][2]);
        Reporter.log("allInOneAsgnmntAvtvtyName: " + actvtsArray[0][2]);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        actvtsArray[0][3] = a.createPageResource();
        System.out.println("pageActvtyName: " + actvtsArray[0][3]);
        Reporter.log("pageActvtyName: " + actvtsArray[0][3]);
    }

    /**
     * Add True/False question to Quiz Activity
     *
     * @param grpCrsName
     * @param quizName
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsQz", groups = {"fullsmoke", "activites.addQuizQstn"})
    public void testAddQuizQuestion(String grpCrsName, String quizActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.addQuizQuestion(quizActvtyName);
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run, User logsOut
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testCntntAdminLogOut() throws Exception {
        a.logOut();
    }
}