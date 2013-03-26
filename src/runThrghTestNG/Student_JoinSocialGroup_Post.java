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

    static String[][] stdtUrlPostOnTchrSclGrp = new String[1][1];
    static String[][] stdtTxtCmntOnTchrCrsPost = new String[1][1];
    static String[][] noteCourse = new String[1][1];
    static String[][] noteWall = new String[1][1];
    static String[][] stdtGlossaryEntryArray = new String[1][1];
    
    Actions a = new Actions();

    @DataProvider(name = "Note")
    public static Object[][] Note(ITestContext context) throws Exception {
        return (noteWall);
    }
    
    @DataProvider(name = "TchrSclGrpStdtUrlPost")
    public static Iterator<Object[]> TchrSclGrpStdtUrlPost(ITestContext context) throws Exception {
        System.out.println("init TchrSclGrpStdtUrlPost");
        return DataProviderUtility.cartesianProviderFrom(Teacher_Posts_SocialGroup.TchrSclGrp(context), stdtUrlPostOnTchrSclGrp);
    }
    
    @DataProvider(name = "CrsStdtCmnt")
    public static Iterator<Object[]> StdtTxtCmntOnTchrCrsPost(ITestContext context) throws Exception {
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), stdtTxtCmntOnTchrCrsPost);
    }
    
    @DataProvider(name = "StdtGlossaryEntryName")
    public static Object[][] StdtGlossaryEntryName(ITestContext context) throws Exception {
        System.out.println("init StdtGlossaryEntryName");
        return (stdtGlossaryEntryArray);
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
            a.login(Pes_UserCreation_AssignRole_WorkingGroup.usrsArray[0][1]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("stdtUsrName"));
        }
    }

    /**
     * Find & Join Teacher's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = Teacher_Posts_SocialGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "socialGroup.studentJoinTeachers"})
    public void testStudentJoinsTeacherSocialGroup(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(tchrSclGrpName);
        a.joinSocialGroup(tchrSclGrpName);
    }

    /**
     * Post/Verify URL on Teacher's SocialGroup
     *
     * @throws Exception
     */
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = Teacher_Posts_SocialGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "socialGroup.studentPostURLOnTeachers"})
    public void testStudentPostURLOnTeacherSocialGroup(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(tchrSclGrpName);
        stdtUrlPostOnTchrSclGrp[0][0] = a.urlPost("urlSclGrpPost");
        Reporter.log("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp[0][0], true);
    }

    /**
     * Add Comment on Teacher's CoursePost
     *
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsTchrUrlCrsPst", dataProviderClass = Teacher_Posts_SocialGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "wall.studentCommentOnTeacherCoursePost"})
    public void testStudentCommentOnTeacherCoursePost(String grpCrsName, String tchrUrlCrsPost) throws Exception {
        a.selectGroupCourse(grpCrsName);
        stdtTxtCmntOnTchrCrsPost[0][0] = a.textCommentPost(tchrUrlCrsPost, "txtCmntOnTchrCrsPst");
        Reporter.log("stdtTxtCmntOnTchrCrsPost: " + stdtTxtCmntOnTchrCrsPost[0][0], true);
    }
    
    /**
     * Student verifies PES posts on Course Wall
     * 
     * @param grpCrsName
     * @param pesTxtCrsSctnPost
     * @param pesTxtCrsPostCmntsOn
     * @param pesTxtCrsPostCmntsOff
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsPESCoursePosts", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"regressionSmoke", "wall.studentVerifyPESCoursePosts"})
    public void testStudentVerifyPESCoursePost(String grpCrsName, String pesTxtCrsSctnPost, String pesTxtCrsPostCmntsOn, String pesTxtCrsPostCmntsOff, String pesTxtAncmntCrsPost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.verifyCoursePost(pesTxtCrsSctnPost, pesTxtCrsPostCmntsOn, pesTxtCrsPostCmntsOff, pesTxtAncmntCrsPost);
    }
    
    /**
     * Student recommend Teacher's URL Course Post
     * 
     * @param grpCrsName
     * @param tchrUrlCrsPost
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsTchrUrlCrsPst", dataProviderClass = Teacher_Posts_SocialGroup.class,
          groups = {"regressionSmoke", "wall.studentRecommendPost"})
    public void testStudentRecommendCourseURLPost(String grpCrsName, String tchrUrlCrsPost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.recommendURLCoursePost(tchrUrlCrsPost);
    }
    
    /**
     * Submit Assignment
     *
     * @param grpCrsName
     * @param allInOneAsgnmntAvtvtyName
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsAssgnmnt", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "assignment.submit"})
    public void testStudentSubmitAssignment(String grpCrsName, String allInOneAsgnmntAvtvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToActivityReport();
        a.submitAssignment(allInOneAsgnmntAvtvtyName);
    }

    /**
     * Create Note on Course Wall
     *
     * @param grpCrsName
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "note.createOnCourseWall"})
    public void testStudentCreateNoteOnCourseWall(String grpCrsName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        noteCourse[0][0] = a.createNote(grpCrsName);
    }

    /**
     * Create Note on Profile Wall
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "note.createOnProfileWall"})
    public void testStudentCreateNoteOnProfileWall() throws Exception {
        a.navigateToMyWall();
        noteWall[0][0] = a.createNote("Profile");
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
     * @param grpCrsName
     * @throws Exception 
     */
    /*@Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class, 
          groups = {"regressionSmoke", "activity.studentVerifySyllabus"})
    public void testStudentVerifySyllabusActivity(String grpCrsName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
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
    @Test(groups = {"regressionSmoke", "personalInfo.studentVerify"})
    public void testStudentVerifyPersonalInfo() throws Exception {
        a.navigateToMyPersonalInfo();
        a.verifyPersonalInfo();
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
     * @param grpCrsName
     * @param glossaryName
     * @throws Exception 
     */
    @Test(dataProvider = "CourseGlossaryName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "activites.studentCreateGlossaryEntry"})
    public void testStudentCreateGlossaryEntry(String grpCrsName, String glossaryName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToActivityReport();
        a.navigateToContentPage(glossaryName);
        stdtGlossaryEntryArray[0][0] = a.createGlossaryEntry(glossaryName);
        Reporter.log("stdtGlossaryEntryName: " + stdtGlossaryEntryArray[0][0], true);
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
