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

    @DataProvider(name = "PswdQzNameActvitiesBackupFile")
    public static Iterator<Object[]> PswdQzNameActvitiesBackupFile(ITestContext context) throws Exception {
        System.out.println("init PswdQzNameActvitiesBackupFile");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.PswdQzName(context),
                ContentAdmin_Course_GroupCourseCreation.Activites(context), ContentAdmin_Course_GroupCourseCreation.GlossaryName(context),
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
     * @param grpCrsName
     * @param frmActvyName
     * @param quizActvtyName
     * @param allInOneAsgnmntActvtyName
     * @param pageActvtyName
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsActivities", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"activites.deletion"})
    public void testContentAdminActivitiesDeletion(String grpCrsName, String frmActvyName, String quizActvtyName,
            String allInOneAsgnmntActvtyName, String pageActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.deleteActivites(frmActvyName, quizActvtyName, allInOneAsgnmntActvtyName, pageActvtyName);
    }

    /**
     * Delete Group Course
     *
     * @param grpCrsName
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"groupcourse.deletion"})
    public void testContentAdminGroupCourseDeletion(String grpCrsName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.deleteGroupCourse(grpCrsName);
    }

    /**
     * Takes backup of course
     *
     * @param pswdQzName
     * @param frmActvyName
     * @param quizActvtyName
     * @param allInOneAsgnmntAvtvtyName
     * @param pageActvtyName
     * @throws Exception
     */
    @Test(dataProvider = "PswdQzNameActivities", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "course.backup"})
    public void testContentAdminBackupCourse(String pswdQzName, String frmActvyName, String quizActvtyName,
            String allInOneAsgnmntAvtvtyName, String pageActvtyName, String glossaryActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.navigateToCourseCategories();
        a.selectCourse(ContentAdmin_Course_GroupCourseCreation.crsName);
        backupFileNameArray[0][0] = a.backupCourse(pswdQzName, frmActvyName, quizActvtyName,
                allInOneAsgnmntAvtvtyName, pageActvtyName, glossaryActvtyName);
        Reporter.log("backupFileName: " + backupFileNameArray[0][0], true);
    }

    /**
     * Restore course as new archive course
     *
     * @param pswdQzName
     * @param frmActvyName
     * @param quizActvtyName
     * @param allInOneAsgnmntAvtvtyName
     * @param pageActvtyName
     * @throws Exception
     */
    @Test(dataProvider = "PswdQzNameActvitiesBackupFile", groups = {"regressionSmoke", "course.restore"})
    public void testContentAdminRestoreCourseAsNewArchiveCourse(String pswdQzName, String frmActvyName, String quizActvtyName,
            String allInOneAsgnmntAvtvtyName, String pageActvtyName, String glossaryActvtyName, String backupFile) throws Exception {
        a.navigateToMyCourse();
        a.navigateToCourseCategories();
        a.selectCourse(ContentAdmin_Course_GroupCourseCreation.crsName);
        a.restoreAsNewArchiveCourse(pswdQzName, frmActvyName, quizActvtyName,
                allInOneAsgnmntAvtvtyName, pageActvtyName, glossaryActvtyName, backupFile);
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
