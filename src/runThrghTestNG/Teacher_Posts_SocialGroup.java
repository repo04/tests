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

    String tchrTxtWallPost;
    String tchrUrlWallPost;
    String tchrUrlPostOnStdtWall;
    static String[][] tchrUrlCrsPostArray = new String[1][1];
    static String[][] tchrSclGrpArray = new String[1][1];
    static String[][] tchrGlossaryEntryArray = new String[1][1];
    static String[][] tchrGlossaryCategoryArray = new String[1][1];
    
    Actions a = new Actions();

    @DataProvider(name = "tchrUrlCrsPost")
    public static Object[][] tchrUrlCrsPost(ITestContext context) throws Exception {
        System.out.println("init tchrUrlCrsPost");
        return (tchrUrlCrsPostArray);
    }

    @DataProvider(name = "TchrSclGrp")
    public static Object[][] TchrSclGrp(ITestContext context) throws Exception {
        System.out.println("init TchrSclGrp");
        return (tchrSclGrpArray);
    }

    @DataProvider(name = "GrpCrsTchrUrlCrsPst")
    public static Iterator<Object[]> GrpCrsTchrUrlCrsPst(ITestContext context) throws Exception {
        System.out.println("init GrpCrsTchrUrlCrsPst");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), tchrUrlCrsPost(context));
    }
    
    @DataProvider(name = "TchrGlossaryEntryName")
    public static Object[][] TchrGlossaryEntryName(ITestContext context) throws Exception {
        System.out.println("init TchrGlossaryEntryName");
        return (tchrGlossaryEntryArray);
    }
    
    @DataProvider(name = "GlossaryCategoryName")
    public static Object[][] GlossaryCategoryName(ITestContext context) throws Exception {
        System.out.println("init GlossaryCategoryName");
        return (tchrGlossaryCategoryArray);
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testTeacherLogIn(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            a.login(Pes_UserCreation_AssignRole_WorkingGroup.usrsArray[0][0]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("tchrUsrName"));
        }
    }

    /**
     * Post & verify Text,URL on own & Course Wall
     *
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "wall.teacherPostsOnProfileCourseWall"})
    public void testTeacherPostsOn_Wall_CourseWall(String grpCrsName) throws Exception {
        a.navigateToMyWall();
        tchrTxtWallPost = a.textPost("txtWallPost");
        System.out.println("tchrTxtWallPost: " + tchrTxtWallPost);
        Reporter.log("tchrTxtWallPost: " + tchrTxtWallPost);

        a.navigateToMyWall();
        tchrUrlWallPost = a.urlPost("urlWallPost");
        System.out.println("tchrUrlWallPost: " + tchrUrlWallPost);
        Reporter.log("tchrUrlWallPost: " + tchrUrlWallPost);

        a.selectGroupCourse(grpCrsName);
        tchrUrlCrsPostArray[0][0] = a.urlPost("urlCrsPost");
        System.out.println("tchrUrlCrsPost: " + tchrUrlCrsPostArray[0][0]);
        Reporter.log("tchrUrlCrsPost: " + tchrUrlCrsPostArray[0][0]);
    }

    /**
     * Create SocialGroup
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "socialGroup.teacherCreate"})
    public void testTeacherCreateSocialGroup() throws Exception {
        a.navigateToMySocialGroups();
        tchrSclGrpArray[0][0] = a.createSocialGroup();
        System.out.println("tchrSclGrpName: " + tchrSclGrpArray[0][0]);
        Reporter.log("tchrSclGrpName: " + tchrSclGrpArray[0][0]);
    }

    /**
     *
     * @param tchrUsrName
     * @param stdtUsrName
     * @throws Exception
     */
    @Test(dataProvider = "Users", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
    groups = {"criticalsmoke", "teacherPosts.studentsWall"})
    public void testTeacherPostURLOnStudentsWall(String tchrUsrName, String stdtUsrName) throws Exception {
        a.navigateToMyContacts();
        a.navigateToContactsWall(stdtUsrName);
        tchrUrlPostOnStdtWall = a.textPost("tchrUrlPostOnStdtWall");
        System.out.println("tchrUrlPostOnStdtWall: " + tchrUrlPostOnStdtWall);
        Reporter.log("tchrUrlPostOnStdtWall: " + tchrUrlPostOnStdtWall);
    }

    /**
     * Teacher verifies PES posts on Course Wall
     *
     * @param grpCrsName
     * @param pesTxtCrsSctnPost
     * @param pesTxtCrsPostCmntsOn
     * @param pesTxtCrsPostCmntsOff
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsPESCoursePosts", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
    groups = {"regressionSmoke", "wall.teacherVerifyPESCoursePosts"})
    public void testTeacherVerifyPESCoursePosts(String grpCrsName, String pesTxtCrsSctnPost, String pesTxtCrsPostCmntsOn, String pesTxtCrsPostCmntsOff, String pesTxtAncmntCrsPost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.verifyCoursePost(pesTxtCrsSctnPost, pesTxtCrsPostCmntsOn, pesTxtCrsPostCmntsOff, pesTxtAncmntCrsPost);
    }

    /**
     * Verify Syllabus Activity
     *
     * @param grpCrsName
     * @throws Exception
     */
    /*@Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
     groups = {"regressionSmoke", "activity.teacherVerifySyllabus"})
     public void testTeacherVerifySyllabusActivity(String grpCrsName) throws Exception {
     a.navigateToMyCourse();
     a.selectGroupCourse(grpCrsName);
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
    @Test(groups = {"regressionSmoke", "personalInfo.teacherVerify"})
    public void testTeacherVerifyPersonalInfo() throws Exception {
        a.navigateToMyPersonalInfo();
        a.verifyPersonalInfo();
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
     * @param grpCrsName
     * @param pswdQzName
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsPswdQzName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"criticalsmoke", "regressionSmoke", "fullSmoke", "pswdQuiz.generatePassword"})
    public void testTeacherGenerateQuizPassword(String grpCrsName, String pswdQzName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToActivityReport();
        a.generateQuizPassword(pswdQzName);
    }
    
    /**
     * Teacher Add Student as contact from Course
     * 
     * @param grpCrsName
     * @param tchrUsrName
     * @param stdtUsrName
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsUsers", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"regressionSmoke", "contact.teacherAddStudentAsContactfromCourse"})
    public void testTeacherAddStudentAsContactfromCourse(String grpCrsName, String tchrUsrName, String stdtUsrName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToAllInstructorsStudentsPage("student"); 
        a.addUserAsContact(stdtUsrName); 
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
     * @param grpCrsName
     * @param glossaryName
     * @throws Exception 
     */
    @Test(dataProvider = "CourseGlossaryName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "activites.teacherCreateGlossaryEntry"})
    public void testTeacherCreateGlossaryEntry(String grpCrsName, String glossaryName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToActivityReport();
        a.navigateToContentPage(glossaryName);
        tchrGlossaryEntryArray[0][0] = a.createGlossaryEntry(glossaryName);
        Reporter.log("tchrGlossaryEntryName: " + tchrGlossaryEntryArray[0][0], true);
    }
    
    /**
     * Teacher create Glossary Category
     * 
     * @throws Exception 
     */
    @Test(dataProvider = "CourseGlossaryName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "activites.teacherCreateGlossaryCategory"})
    public void testTeacherCreateGlossaryCategory(String grpCrsName, String glossaryName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToActivityReport();
        a.navigateToContentPage(glossaryName);
        tchrGlossaryCategoryArray[0][0] = a.createGlossaryCategory(glossaryName);
        Reporter.log("tchrGlossaryCategoryName: " + tchrGlossaryCategoryArray[0][0], true);
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
