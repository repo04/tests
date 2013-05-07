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
 * Teacher LogIn, Posts on Wall & Course Wall, Creates Social Group
 *
 */
public class Teacher_Posts_SocialGroup extends BaseClass {

    String teacherTextWallPost;
    String teacherUrlWallPost;
    String teacherUrlPostOnStudentWall;
    static String[][] teacherUrlCoursePostArray = new String[1][1];
    static String[][] teacherSocialGroupNameArray = new String[1][1];
    static String[][] teacherGlossaryEntryNameArray = new String[1][1];
    static String[][] teacherGlossaryCategoryNameArray = new String[1][1];
    Actions a = new Actions();

    @DataProvider(name = "teacherUrlCoursePost")
    public static Object[][] teacherUrlCoursePost(ITestContext context) throws Exception {
        System.out.println("init teacherUrlCoursePost");
        return (teacherUrlCoursePostArray);
    }

    @DataProvider(name = "TeacherSocialGroup")
    public static Object[][] TeacherSocialGroup(ITestContext context) throws Exception {
        System.out.println("init TeacherSocialGroup");
        return (teacherSocialGroupNameArray);
    }

    @DataProvider(name = "GroupCourseTeacherUrlCoursePost")
    public static Iterator<Object[]> GroupCourseTeacherUrlCoursePost(ITestContext context) throws Exception {
        System.out.println("init GroupCourseTeacherUrlCoursePost");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), teacherUrlCoursePost(context));
    }

    @DataProvider(name = "TeacherGlossaryEntryName")
    public static Object[][] TeacherGlossaryEntryName(ITestContext context) throws Exception {
        System.out.println("init TeacherGlossaryEntryName");
        return (teacherGlossaryEntryNameArray);
    }

    @DataProvider(name = "GlossaryCategoryName")
    public static Object[][] GlossaryCategoryName(ITestContext context) throws Exception {
        System.out.println("init GlossaryCategoryName");
        return (teacherGlossaryCategoryNameArray);
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testTeacherLogIn(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")
                || test.equalsIgnoreCase("CriticalDataTests")) {
            a.login(Pes_UserCreation_AssignRole_WorkingGroup.userNamesArray[0][0]);
        } else {
            a.login(ldv.getTokenValue("teacherUserName"));
        }
    }

    /**
     * Post & verify Text,URL on own & Course Wall
     *
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "wall.teacherPostsOnProfileCourseWall"})
    public void testTeacherPostsOn_Wall_CourseWall(String groupCourseName) throws Exception {
        a.navigateToMyWall();
        teacherTextWallPost = a.textPost("txtWallPost");
        System.out.println("teacherTextWallPost: " + teacherTextWallPost);
        Reporter.log("teacherTextWallPost: " + teacherTextWallPost);

        a.navigateToMyWall();
        teacherUrlWallPost = a.urlPost("urlWallPost");
        System.out.println("teacherUrlWallPost: " + teacherUrlWallPost);
        Reporter.log("teacherUrlWallPost: " + teacherUrlWallPost);

        a.selectGroupCourse(groupCourseName);
        teacherUrlCoursePostArray[0][0] = a.urlPost("urlCrsPost");
        System.out.println("teacherUrlCoursePost: " + teacherUrlCoursePostArray[0][0]);
        Reporter.log("teacherUrlCoursePost: " + teacherUrlCoursePostArray[0][0]);
    }

    /**
     * Create SocialGroup
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "socialGroup.teacherCreate"})
    public void testTeacherCreateSocialGroup() throws Exception {
        a.navigateToMySocialGroups();
        teacherSocialGroupNameArray[0][0] = a.createSocialGroup();
        System.out.println("teacherSocialGroupName: " + teacherSocialGroupNameArray[0][0]);
        Reporter.log("teacherSocialGroupName: " + teacherSocialGroupNameArray[0][0]);
    }

    /**
     *
     * @param teacherUserName
     * @param studentUserName
     * @throws Exception
     */
    @Test(dataProvider = "Users", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
    groups = {"criticalSmoke", "teacherPosts.studentsWall"})
    public void testTeacherPostURLOnStudentsWall(String teacherUserName, String studentUserName) throws Exception {
        a.navigateToMyContacts();
        a.navigateToContactsWall(studentUserName);
        teacherUrlPostOnStudentWall = a.textPost("tchrUrlPostOnStdtWall");
        System.out.println("teacherUrlPostOnStudentWall: " + teacherUrlPostOnStudentWall);
        Reporter.log("teacherUrlPostOnStudentWall: " + teacherUrlPostOnStudentWall);
    }

    /**
     * Teacher verifies PES posts on Course Wall
     *
     * @param groupCourseName
     * @param pesTextCourseSectionPost
     * @param pesTxtCoursePostCommentsOn
     * @param pesTextCoursePostCommentsOff
     * @throws Exception
     */
    @Test(dataProvider = "GroupCoursePesCoursePosts", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
    groups = {"regressionSmoke", "wall.teacherVerifyPESCoursePosts"})
    public void testTeacherVerifyPESCoursePosts(String groupCourseName, String pesTextCourseSectionPost, String pesTxtCoursePostCommentsOn, String pesTextCoursePostCommentsOff, String pesTextAnnouncementCoursePost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.verifyCoursePost(pesTextCourseSectionPost, pesTxtCoursePostCommentsOn, pesTextCoursePostCommentsOff, pesTextAnnouncementCoursePost);
    }

    /**
     * Verify Syllabus Activity
     *
     * @param groupCourseName
     * @throws Exception
     */
    /*@Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
     groups = {"regressionSmoke", "activity.teacherVerifySyllabus"})
     public void testTeacherVerifySyllabusActivity(String groupCourseName) throws Exception {
     a.navigateToMyCourse();
     a.selectGroupCourse(groupCourseName);
     a.verifySyllabusActivity();
     }*/
    
    /**
     * Teacher verify Resume
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "resume.teacherVerify"})
    public void testTeacherVerifyResume() throws Exception {
        a.navigateToMyWall();
        a.verifyResume();
    }

    /**
     * Teacher verify Personal Information
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "personalInformation.teacherVerify"})
    public void testTeacherVerifyPersonalInformation() throws Exception {
        a.navigateToMyPersonalInformation();
        a.verifyPersonalInformation();
    }

    /**
     * Teacher verify Feedback Window
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "feedback.teacherVerify"})
    public void testTeacherVerifyFeedbackWindow() throws Exception {
        a.navigateToMyHome();
        a.verifyFeedbackWindow();
    }

    /**
     *
     * @param groupCourseName
     * @param passwordQuizName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCoursePasswordQuizName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "pswdQuiz.generatePassword"})
    public void testTeacherGenerateQuizPassword(String groupCourseName, String passwordQuizName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.generateQuizPassword(passwordQuizName);
    }

    /**
     * Teacher Add Student as contact from Course
     *
     * @param groupCourseName
     * @param teacherUserName
     * @param studentUserName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseUsers", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"regressionSmoke", "criticalTestDataSmoke", "contact.teacherAddStudentAsContactfromCourse"})
    public void testTeacherAddStudentAsContactfromCourse(String groupCourseName, String teacherUserName, String studentUserName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToAllInstructorsStudentsPage("student");
        a.addUserAsContact(studentUserName);
    }    
    
    /**
     * Verify Settings page specific to user role
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "settings.teacherVerify"})
    public void testTeacherVerifySettings() throws Exception {
        a.navigateToSettings();
        a.verifySettings();
    }

    /**
     * Teacher create Glossary entry
     *
     * @param groupCourseName
     * @param glossaryName
     * @throws Exception
     */
    @Test(dataProvider = "CourseGlossaryName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "activities.teacherCreateGlossaryEntry"})
    public void testTeacherCreateGlossaryEntry(String groupCourseName, String glossaryName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.navigateToContentPage(glossaryName);
        teacherGlossaryEntryNameArray[0][0] = a.createGlossaryEntry(glossaryName);
        Reporter.log("teacherGlossaryEntryName: " + teacherGlossaryEntryNameArray[0][0], true);
    }

    /**
     * Teacher create Glossary Category
     *
     * @throws Exception
     */
    @Test(dataProvider = "CourseGlossaryName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "activities.teacherCreateGlossaryCategory"})
    public void testTeacherCreateGlossaryCategory(String groupCourseName, String glossaryName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.navigateToContentPage(glossaryName);
        teacherGlossaryCategoryNameArray[0][0] = a.createGlossaryCategory(glossaryName);
        Reporter.log("teacherGlossaryCategoryName: " + teacherGlossaryCategoryNameArray[0][0], true);
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
