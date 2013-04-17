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
 * Student logs in, Find & Join Teacher's Social Group, Post/Verify URL on
 * Teacher's SocialGroup,
 *
 */
public class Student_JoinSocialGroup_Post extends BaseClass {

    static String[][] studentUrlPostOnTeacherSocialGroup = new String[1][1];
    static String[][] studentTextCommentOnTeacherCoursePost = new String[1][1];
    static String[][] noteCourseNameArray = new String[1][1];
    static String[][] noteWallNameArray = new String[1][1];
    static String[][] studentGlossaryEntryArray = new String[1][1];
    
    Actions a = new Actions();

    @DataProvider(name = "Note")
    public static Object[][] Note(ITestContext context) throws Exception {
        return (noteWallNameArray);
    }
    
    @DataProvider(name = "TeacherSocialGroupStudentUrlPost")
    public static Iterator<Object[]> TeacherSocialGroupStudentUrlPost(ITestContext context) throws Exception {
        System.out.println("init TeacherSocialGroupStudentUrlPost");
        return DataProviderUtility.cartesianProviderFrom(Teacher_Posts_SocialGroup.TeacherSocialGroup(context), studentUrlPostOnTeacherSocialGroup);
    }
    
    @DataProvider(name = "CourseStudentComment")
    public static Iterator<Object[]> StudentTextCommentOnTeacherCoursePost(ITestContext context) throws Exception {
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), studentTextCommentOnTeacherCoursePost);
    }
    
    @DataProvider(name = "StudentGlossaryEntryName")
    public static Object[][] StudentGlossaryEntryName(ITestContext context) throws Exception {
        System.out.println("init StudentGlossaryEntryName");
        return (studentGlossaryEntryArray);
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testStudentLogIn(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            a.login(Pes_UserCreation_AssignRole_WorkingGroup.userNamesArray[0][1]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("studentUserName"));
        }
    }

    /**
     * Find & Join Teacher's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "TeacherSocialGroup", dataProviderClass = Teacher_Posts_SocialGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "socialGroup.studentJoinTeachers"})
    public void testStudentJoinsTeacherSocialGroup(String teacherSocialGroupName) throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(teacherSocialGroupName);
        a.joinSocialGroup(teacherSocialGroupName);
    }

    /**
     * Post/Verify URL on Teacher's SocialGroup
     *
     * @throws Exception
     */
    @Test(dataProvider = "TeacherSocialGroup", dataProviderClass = Teacher_Posts_SocialGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "socialGroup.studentPostURLOnTeachers"})
    public void testStudentPostURLOnTeacherSocialGroup(String teacherSocialGroupName) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(teacherSocialGroupName);
        studentUrlPostOnTeacherSocialGroup[0][0] = a.urlPost("urlSclGrpPost");
        Reporter.log("studentUrlPostOnTeacherSocialGroup: " + studentUrlPostOnTeacherSocialGroup[0][0], true);
    }

    /**
     * Add Comment on Teacher's CoursePost
     *
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseTeacherUrlCoursePost", dataProviderClass = Teacher_Posts_SocialGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "wall.studentCommentOnTeacherCoursePost"})
    public void testStudentCommentOnTeacherCoursePost(String groupCourseName, String teacherUrlCoursePost) throws Exception {
        a.selectGroupCourse(groupCourseName);
        studentTextCommentOnTeacherCoursePost[0][0] = a.textCommentPost(teacherUrlCoursePost, "txtCmntOnTchrCrsPst");
        Reporter.log("studentTextCommentOnTeacherCoursePost: " + studentTextCommentOnTeacherCoursePost[0][0], true);
    }
    
    /**
     * Student verifies PES posts on Course Wall
     * 
     * @param groupCourseName
     * @param pesTextCourseSectionPost
     * @param pesTxtCoursePostCommentsOn
     * @param pesTextCoursePostCommentsOff
     * @throws Exception 
     */
    @Test(dataProvider = "GroupCoursePesCoursePosts", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"regressionSmoke", "wall.studentVerifyPESCoursePosts"})
    public void testStudentVerifyPESCoursePost(String groupCourseName, String pesTextCourseSectionPost, String pesTxtCoursePostCommentsOn, String pesTextCoursePostCommentsOff, String pesTextAnnouncementCoursePost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.verifyCoursePost(pesTextCourseSectionPost, pesTxtCoursePostCommentsOn, pesTextCoursePostCommentsOff, pesTextAnnouncementCoursePost);
    }
    
    /**
     * Student recommend Teacher's URL Course Post
     * 
     * @param groupCourseName
     * @param teacherUrlCoursePost
     * @throws Exception 
     */
    @Test(dataProvider = "GroupCourseTeacherUrlCoursePost", dataProviderClass = Teacher_Posts_SocialGroup.class,
          groups = {"regressionSmoke", "wall.studentRecommendPost"})
    public void testStudentRecommendCourseURLPost(String groupCourseName, String teacherUrlCoursePost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.recommendURLCoursePost(teacherUrlCoursePost);
    }
    
    /**
     * Submit Assignment
     *
     * @param groupCourseName
     * @param allInOneAssignmentActivityName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseAssignment", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "assignment.submit"})
    public void testStudentSubmitAssignment(String groupCourseName, String allInOneAssignmentActivityName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.submitAssignment(allInOneAssignmentActivityName);
    }

    /**
     * Create Note on Course Wall
     *
     * @param groupCourseName
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "note.createOnCourseWall"})
    public void testStudentCreateNoteOnCourseWall(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        noteCourseNameArray[0][0] = a.createNote(groupCourseName);
    }

    /**
     * Create Note on Profile Wall
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "note.createOnProfileWall"})
    public void testStudentCreateNoteOnProfileWall() throws Exception {
        a.navigateToMyWall();
        noteWallNameArray[0][0] = a.createNote("Profile");
    }
    
    /**
     * Verify Note Sorting 
     * 
     * @param profileNote
     * @throws Exception 
     */
    @Test(dataProvider = "Note", groups = {"regressionSmoke", "note.verifySorting"})
    public void testStudentVerifyNoteSorting(String profileNote) throws Exception {
        a.navigateToMyWall();
        a.verifyNoteSorting(profileNote);
    }

    /**
     * Delete Note 
     * 
     * @param profileNote
     * @throws Exception 
     */
    @Test(dataProvider = "Note", groups = {"regressionSmoke", "note.deleteProfile"})
    public void testStudentDeleteProfileNote(String profileNote) throws Exception {
        a.navigateToMyWall();
        a.deleteNote(profileNote);
    }
    
    /**
     * Verify Resources
     * 
     * @throws Exception 
     */
    @Test(groups = {"regressionSmoke", "resources.studentVerify"})
    public void testStudentVerifyResources() throws Exception {
        a.navigateToMyHome();
        a.verifyResources();
    }
    
    /**
     * Verify Footers
     * 
     * @throws Exception 
     */
    @Test(groups = {"regressionSmoke", "footers.verify"})
    public void testStudentVerifyFooters() throws Exception {
        a.navigateToMyHome();
        a.verifyFooters();
    }
    
    /**
     * Verify Syllabus Activity
     * 
     * @param groupCourseName
     * @throws Exception 
     */
    /*@Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class, 
          groups = {"regressionSmoke", "activity.studentVerifySyllabus"})
    public void testStudentVerifySyllabusActivity(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.verifySyllabusActivity();
    }*/
    
    /**
     * Student verify Resume
     * 
     * @throws Exception 
     */
    @Test(groups = {"regressionSmoke", "resume.studentVerify"})
    public void testStudentVerifyResume() throws Exception {
        a.navigateToMyWall();
        a.verifyResume();
    }
    
    /**
     * Student verify Personal Information
     * 
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "personalInformation.studentVerify"})
    public void testStudentVerifyPersonalInformation() throws Exception {
        a.navigateToMyPersonalInformation();
        a.verifyPersonalInformation();
    }
    
    /**
     * Student verify Feedback Window
     * 
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "feedback.studentVerify"})
        public void testStudentVerifyFeedbackWindow() throws Exception {
        a.navigateToMyHome();
        a.verifyFeedbackWindow();
    }
    
    /**
     * Student verify Help Window on Home Page
     * 
     * @throws Exception 
     */
    /*@Test(groups = {"regressionSmoke", "help.studentVerify"})
    public void testStudentVerifyHelpWindow() throws Exception {
        a.navigateToMyHome();
        a.verifyHelpWindow();
    }*/
    
    /**
     * Verify Settings page specific to user role
     * 
     * @throws Exception 
     */
    @Test(groups = {"regressionSmoke", "settings.studentVerify"})
    public void testStudentVerifySettings() throws Exception {
        a.navigateToSettings();
        a.verifySettings();
    }
    
    /**
     * Student create Glossary entry
     * 
     * @param groupCourseName
     * @param glossaryName
     * @throws Exception 
     */
    @Test(dataProvider = "CourseGlossaryName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "activities.studentCreateGlossaryEntry"})
    public void testStudentCreateGlossaryEntry(String groupCourseName, String glossaryName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.navigateToContentPage(glossaryName);
        studentGlossaryEntryArray[0][0] = a.createGlossaryEntry(glossaryName);
        Reporter.log("studentGlossaryEntryName: " + studentGlossaryEntryArray[0][0], true);
    }
    
    /**
     * Student verify Calendar on Home Page
     * 
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "calendar.studentVerify"})
    public void testStudentVerifyCalendar() throws Exception {
        a.navigateToMyHome();
        a.verifyCalendar();
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testStudentLogOut() throws Exception {
        a.logOut();
    }
}
