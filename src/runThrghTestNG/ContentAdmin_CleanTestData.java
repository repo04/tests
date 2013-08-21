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
 *
 *
 */
public class ContentAdmin_CleanTestData {

    Actions a = new Actions();
    static String[][] backupFileNameArray = new String[1][1];

    @DataProvider(name = "CoursePasswordQuizNameActvitiesBackupFile")
    public static Iterator<Object[]> CoursePasswordQuizNameActvitiesBackupFile(ITestContext context) throws Exception {
        System.out.println("init CoursePasswordQuizNameActvitiesBackupFile");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.CourseName(context), ContentAdmin_Course_GroupCourseCreation.PasswordQuizName(context),
                ContentAdmin_Course_GroupCourseCreation.Activities(context), ContentAdmin_Course_GroupCourseCreation.GlossaryName(context),
                backupFileNameArray);
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
     * Delete all Activities
     *     
     * @param groupCourseName
     * @param forumActivityName
     * @param quizActivityName
     * @param allInOneAssignmentActivityName
     * @param pageActivityName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseActivities", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"activities.deletion"})
    public void testContentAdminActivitiesDeletion(String groupCourseName, String forumActivityName, String quizActivityName,
            String allInOneAssignmentActivityName, String pageActivityName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.deleteActivities(forumActivityName, quizActivityName, allInOneAssignmentActivityName, pageActivityName);
    }

    /**
     * Delete Group Course
     *     
     * @param groupCourseName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourse", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"groupcourse.deletion"})
    public void testContentAdminGroupCourseDeletion(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.deleteGroupCourse(groupCourseName);
    }

    /**
     * Takes backup of course
     *     
     * @param passwordQuizName
     * @param forumActivityName
     * @param quizActivityName
     * @param allInOneAssignmentActivityName
     * @param pageActivityName
     * @throws Exception
     */
    @Test(dataProvider = "CoursePasswordQuizNameActivities", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "criticalSmoke", "course.backup"})
    public void testContentAdminBackupCourse(String courseName, String passwordQuizName, String forumActivityName, String quizActivityName,
            String allInOneAssignmentActivityName, String pageActivityName, String glossaryActivityName) throws Exception {
        a.navigateToMyCourse();
        a.navigateToCourseCategories();
        a.selectCourse(courseName);
        backupFileNameArray[0][0] = a.backupCourse(passwordQuizName, forumActivityName, quizActivityName,
                allInOneAssignmentActivityName, pageActivityName, glossaryActivityName);
        Reporter.log("backupFileName: " + backupFileNameArray[0][0], true);
    }

    /**
     * Restore course as new archive course
     *     
     * @param passwordQuizName
     * @param forumActivityName
     * @param quizActivityName
     * @param allInOneAssignmentActivityName
     * @param pageActivityName
     * @throws Exception
     */
    @Test(dataProvider = "CoursePasswordQuizNameActvitiesBackupFile", groups = {"regressionSmoke",
        "criticalSmoke", "course.restore"})
    public void testContentAdminRestoreCourseAsNewArchiveCourse(String courseName, String passwordQuizName, String forumActivityName, String quizActivityName,
            String allInOneAssignmentActivityName, String pageActivityName, String glossaryActivityName, String backupFile) throws Exception {
        a.navigateToMyCourse();
        a.navigateToCourseCategories();
        a.selectCourse(courseName);
        a.restoreAsNewArchiveCourse(passwordQuizName, forumActivityName, quizActivityName,
                allInOneAssignmentActivityName, pageActivityName, glossaryActivityName, backupFile);
    }

    /**
     * Verify that coursework unit should not be expandable (by default) when
     * 'Disable date in section' check box is checked & vice versa
     *     
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourse", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "content.contentAdminVerifyCourseworkUnitExpandableOrNot"})
    public void testContentAdminVerifyCourseworkUnitExpandableOrNotWhileChangingDisableDateField(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.courseworkUnitExpandableOrNotWhileChangingDisableDateField();
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