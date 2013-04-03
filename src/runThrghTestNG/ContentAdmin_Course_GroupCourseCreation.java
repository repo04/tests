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
public class ContentAdmin_Course_GroupCourseCreation extends BaseClass {

    public static String courseName;
    Actions a = new Actions();
    static String[][] groupCourseNameArray = new String[1][1];
    static String[][] quizNameArray = new String[1][1];
    static String[][] assignmentNameArray = new String[1][1];
    static String[][] activitiesArray = new String[1][4];
    static String[][] passwordQuizNameArray = new String[1][1];
    static String[][] glossaryActivityNameArray = new String[1][1];

    /**
     * ITestContext contains all the information for a given test run. A Data
     * Provider is a method that returns an array of array of objects. This
     * method will provide data to any test method that declares that its Data
     * Provider is named "Course". In this case it is being fetched from
     * 'testContentAdminCourseGroupCourseCreation' method which always get
     * executed before 'testContentAdminActivitiesCreation' (execution order is
     * maintained in TransformSmoke class, passed as listeners from smoke.xml)
     *
     *
     * @param context
     * @return
     * @throws Exception
     */
    @DataProvider(name = "Course")
    public static Object[][] Course(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside Course: " + test);
            return (groupCourseNameArray);
        } else {
            System.out.println("Inside Course: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("groupCourseName")}};
        }
    }

    @DataProvider(name = "Activites")
    public static Object[][] Activites(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside Activites: " + test);
            return (activitiesArray);
        } else {
            System.out.println("Inside Activites: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("forumActivityName"),
                            context.getCurrentXmlTest().getParameter("quizActivityName"),
                            context.getCurrentXmlTest().getParameter("allInOneAssignmentActivityName"),
                            context.getCurrentXmlTest().getParameter("pageActivityName")}};
        }
    }

    @DataProvider(name = "QuizName")
    public static Object[][] QuizName(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside QuizName: " + test);
            return (quizNameArray);
        } else {
            System.out.println("Inside QuizName: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("quizActivityName")}};
        }
    }

    @DataProvider(name = "AssignmentName")
    public static Object[][] AssignmentName(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside AssignmentName: " + test);
            return (assignmentNameArray);
        } else {
            System.out.println("Inside AssignmentName: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("allInOneAssignmentActivityName")}};
        }
    }

    @DataProvider(name = "PasswordQuizName")
    public static Object[][] PasswordQuizName(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("Inside PasswordQuizName: " + test);
            return (passwordQuizNameArray);
        } else {
            System.out.println("Inside PasswordQuizName: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("passwordQuizName")}};
        }
    }

    @DataProvider(name = "GlossaryName")
    public static Object[][] GlossaryName(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests")) {
            System.out.println("Inside GlossaryName: " + test);
            return (glossaryActivityNameArray);
        } else {
            System.out.println("Inside GlossaryName: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("glossaryActivityName")}};
        }        
    }

    @DataProvider(name = "GroupCourseActivities")
    public static Iterator<Object[]> GroupCourseActivities(ITestContext context) throws Exception {
        System.out.println("init GroupCourseActivities");
        return DataProviderUtility.cartesianProviderFrom(Course(context), Activites(context));
    }

    @DataProvider(name = "GroupCourseQuiz")
    public static Iterator<Object[]> GroupCourseQuiz(ITestContext context) throws Exception {
        System.out.println("init GroupCourseQuiz");
        return DataProviderUtility.cartesianProviderFrom(Course(context), QuizName(context));
    }

    @DataProvider(name = "GroupCourseAssignment")
    public static Iterator<Object[]> GroupCourseAssignment(ITestContext context) throws Exception {
        System.out.println("init GroupCourseAssignment");
        return DataProviderUtility.cartesianProviderFrom(Course(context), AssignmentName(context));
    }

    @DataProvider(name = "GroupCourseDebug")
    public static Object[][] GroupCourseDebug(ITestContext context) throws Exception {
        System.out.println("init GroupCourseDebug");
        return (groupCourseNameArray);
    }

    @DataProvider(name = "ActivitiesDebug")
    public static Object[][] ActivitiesDebug(ITestContext context) throws Exception {
        System.out.println("init ActivitiesDebug");
        return (activitiesArray);
    }

    @DataProvider(name = "GroupCourseActivitiesDebug")
    public static Iterator<Object[]> GroupCourseActivitiesDebug(ITestContext context) throws Exception {
        System.out.println("init GroupCourseActivitiesDebug");
        return DataProviderUtility.cartesianProviderFrom(Course(context), ActivitiesDebug(context));
    }

    @DataProvider(name = "QuizDebug")
    public static Object[][] QuizDebug(ITestContext context) throws Exception {
        System.out.println("init QuizDebug");
        return (quizNameArray);
    }

    @DataProvider(name = "GroupCourseQuizDebug")
    public static Iterator<Object[]> GroupCourseQuizDebug(ITestContext context) throws Exception {
        System.out.println("init GroupCourseQuizDebug");
        return DataProviderUtility.cartesianProviderFrom(Course(context), QuizDebug(context));
    }

    @DataProvider(name = "GroupCoursePasswordQuizName")
    public static Iterator<Object[]> GroupCoursePasswordQuizName(ITestContext context) throws Exception {
        System.out.println("init GroupCoursePasswordQuizName");
        return DataProviderUtility.cartesianProviderFrom(Course(context), PasswordQuizName(context));
    }

    @DataProvider(name = "PasswordQuizNameActivities")
    public static Iterator<Object[]> PasswordQuizNameActivities(ITestContext context) throws Exception {
        System.out.println("init PasswordQuizNameActivities");
        return DataProviderUtility.cartesianProviderFrom(PasswordQuizName(context), Activites(context), GlossaryName(context));
    }

    @DataProvider(name = "CourseGlossaryName")
    public static Iterator<Object[]> CourseGlossaryName(ITestContext context) throws Exception {
        System.out.println("init CourseGlossaryName");
        return DataProviderUtility.cartesianProviderFrom(Course(context), GlossaryName(context));
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testContentAdminLogIn() throws Exception {
        a.login("contentAdmin");
    }

    /**
     * Create - Course & GrpCourse
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "fullSmoke", "course.creation"})
    public void testContentAdminCourseGroupCourseCreation() throws Exception {
        a.navigateToMyCourse();
        a.navigateToCourseCategories();
        courseName = a.createCourse();
        Reporter.log("courseName: " + courseName, true);

        a.navigateToMyCourse();
        a.navigateToCourseCategories();
        groupCourseNameArray[0][0] = a.createGrpCourse(courseName);
        Reporter.log("groupCourseName: " + groupCourseNameArray[0][0], true);
    }

    /**
     * Create - Activities like Forum, Quiz, All In One Assignment & Page. This
     * test method declares that its data should be supplied by the Data
     * Provider named "Course"
     *
     * @throws Exception
     */
    @Test(dataProvider = "Course", groups = {"regressionSmoke", "fullSmoke", "activities.creation"})
    public void testContentAdminActivitiesCreation(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        activitiesArray[0][0] = a.createForumActivity();
        Reporter.log("forumActivityName: " + activitiesArray[0][0], true);

        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        activitiesArray[0][1] = a.createQuizActivity();
        quizNameArray[0][0] = activitiesArray[0][1];
        Reporter.log("quizActivityName: " + activitiesArray[0][1], true);

        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        activitiesArray[0][2] = a.createAllInOneAssignmentActivity();
        assignmentNameArray[0][0] = activitiesArray[0][2];
        Reporter.log("allInOneAssignmentActivityName: " + activitiesArray[0][2], true);

        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        activitiesArray[0][3] = a.createPageResource();
        Reporter.log("pageActivityName: " + activitiesArray[0][3], true);
    }

    /**
     * Create Glossary activity
     *
     * @param groupCourseName
     * @throws Exception
     */
    @Test(dataProvider = "Course", groups = {"regressionSmoke", "activities.createGlossary"})
    public void testContentAdminGlossaryActivityCreation(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        glossaryActivityNameArray[0][0] = a.createGlossaryActivity();
        Reporter.log("glossaryActivityName: " + glossaryActivityNameArray[0][0], true);
    }

    /**
     * Create - Syllabus Activity
     *
     * @param groupCourseName
     * @throws Exception
     */
    /*@Test(dataProvider = "Course", groups = {"regressionSmoke", "activity.syllabusCreation"})
     public void testSyllabus_Creation(String groupCourseName) throws Exception {
     a.navigateToMyCourse();
     a.selectGroupCourse(groupCourseName);
     a.createSyllabusActivity();
     }*/
    
    /**
     *
     * @param groupCourseName
     * @throws Exception
     */
    @Test(dataProvider = "Course", groups = {"regressionSmoke", "fullSmoke", "pswdQuiz.activityCreation"})
    public void testContentAdminCreateQuizPasswordActivity(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        passwordQuizNameArray[0][0] = a.createPasswordQuizActivity();
        Reporter.log("passwordQuizActivityName: " + passwordQuizNameArray[0][0], true);
    }

    /**
     * Add True/False question to Quiz Activity
     *
     * @param groupCourseName
     * @param quizName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseQuiz", groups = {"regressionSmoke", "fullSmoke", "activities.addQuizQuestion"})
    public void testContentAdminAddQuizQuestion(String groupCourseName, String quizActivityName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.addQuizQuestion(quizActivityName);
    }

    /**
     *
     * @param groupCourseName
     * @param passwordQuizName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCoursePasswordQuizName", groups = {"regressionSmoke", "fullSmoke", "pswdQuiz.addQuestion"})
    public void testContentAdminAddQuesToQuizPasswordActivity(String groupCourseName, String passwordQuizName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.addQuizQuestion(passwordQuizName);
    }

    /**
     * Content Admin verify Feedback Window
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "feedback.contentAdminVerify"})
    public void testContentAdminVerifyFeedbackWindow() throws Exception {
        a.navigateToMyHome();
        a.verifyFeedbackWindow();
    }

    /**
     * ContentAdmin verify Help Window on Home Page
     *
     * @throws Exception
     */
    /*@Test(groups = {"regressionSmoke", "help.contentAdminVerify"})
     public void testContentAdminVerifyHelpWindow() throws Exception {
     a.navigateToMyHome();
     a.verifyHelpWindow();
     }*/
    
    /**
     * Verify Settings page specific to user role
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "settings.contentAdminVerify"})
    public void testContentAdminVerifySettings() throws Exception {
        a.navigateToSettings();
        a.verifySettings();
    }
    
    /*
     * Verifies content admin can sucessfully 
     */
    @Test(dataProvider = "Course", groups = {"lesson.createActivity"})
    public void testContentAdminCreateLessonActivity(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectCourse(groupCourseName);
        a.createLessonActivity();
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run, User logsOut
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testContentAdminLogOut() throws Exception {
        a.logOut();
    }
}
