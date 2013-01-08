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
 * Teacher Login, Posts on Wall & Course Wall, Creates Social Group
 *
 */
public class TchrPosts_SclGrp extends BaseClass {

    String tchrTxtWallPost;
    String tchrUrlWallPost;
    String tchrUrlPostOnStdtWall;
    static String[][] tchrUrlCrsPostArray = new String[1][1];
    static String[][] tchrSclGrpArray = new String[1][1];
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
        return DataProviderUtil.cartesianProviderFrom(Crs_GrpCrsCreation.Course(context), tchrUrlCrsPost(context));
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testTeacherLogin(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            a.login(UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][0]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("tchrUsrName"));
        }
    }

    /**
     * Post & verify Text,URL on own & Course Wall
     *
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = Crs_GrpCrsCreation.class,
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
    @Test(dataProvider = "Users", dataProviderClass = UsrCrtn_AsgnRole_WrkngGrp.class,
    groups = {"criticalsmoke", "teacherPosts.studentsWall"})
    public void testTeacherPostURLOnStudentsWall(String tchrUsrName, String stdtUsrName) throws Exception {
        a.navigateToMyContacts();
        a.navigateToContactsWall(stdtUsrName.substring(0, 4) + " " + stdtUsrName.substring(4));
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
    @Test(dataProvider = "GrpCrsPESCoursePosts", dataProviderClass = UsrCrtn_AsgnRole_WrkngGrp.class,
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
    @Test(dataProvider = "Course", dataProviderClass = Crs_GrpCrsCreation.class,
    groups = {"regressionSmoke", "activity.teacherVerifySyllabus"})
    public void testTeacherVerifySyllabusActivity(String grpCrsName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.verifySyllabusActivity();
    }
    
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
