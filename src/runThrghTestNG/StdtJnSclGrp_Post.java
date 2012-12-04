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
public class StdtJnSclGrp_Post extends BaseClass {

    static String stdtUrlPostOnTchrSclGrp;
    static String[][] stdtTxtCmntOnTchrCrsPost = new String[1][1];
    static String[][] noteCourse = new String[1][1];
    static String[][] noteWall = new String[1][1];
    Actions a = new Actions();

    @DataProvider(name = "Note")
    public static Object[][] Note(ITestContext context) throws Exception {
        return (noteWall);
    }
    
    @DataProvider(name = "CrsStdtCmnt")
    public static Iterator<Object[]> StdtTxtCmntOnTchrCrsPost(ITestContext context) throws Exception {
        return DataProviderUtil.cartesianProviderFrom(Crs_GrpCrsCreation.Course(context), stdtTxtCmntOnTchrCrsPost);
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testStudentLogin(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("SmokeTests")) {
            a.login(UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][1]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("stdtUsrName"));
        }
    }

    /**
     * Find & Join Teacher's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = TchrPosts_SclGrp.class,
          groups = {"fullsmoke", "criticalsmoke", "teacherSocialGroup.studentJoins"})
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
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = TchrPosts_SclGrp.class,
          groups = {"fullsmoke", "criticalsmoke", "teacherSocialGroup.studentPostURL"})
    public void testStudentPostURLOnTeacherSocialGroup(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.accessSocialGroupWall(tchrSclGrpName);
        stdtUrlPostOnTchrSclGrp = a.urlPost("urlSclGrpPost");
        Reporter.log("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp, true);
    }

    /**
     * Add Comment on Teacher's CoursePost
     *
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsTchrUrlCrsPst", dataProviderClass = TchrPosts_SclGrp.class,
          groups = {"fullsmoke", "criticalsmoke", "wall.studentCommentOnTeacherCoursePost"})
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
    @Test(dataProvider = "GrpCrsPESCoursePosts", dataProviderClass = UsrCrtn_AsgnRole_WrkngGrp.class,
          groups = {"regressionsmoke", "wall.studentVerifyPESCoursePosts"})
    public void testStudentVerifyPESCoursePost(String grpCrsName, String pesTxtCrsSctnPost, String pesTxtCrsPostCmntsOn, String pesTxtCrsPostCmntsOff) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.verifyCoursePost(pesTxtCrsSctnPost, pesTxtCrsPostCmntsOn, pesTxtCrsPostCmntsOff);
    }
    
    /**
     * Student recommend Teacher's URL Course Post
     * 
     * @param grpCrsName
     * @param tchrUrlCrsPost
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsTchrUrlCrsPst", dataProviderClass = TchrPosts_SclGrp.class,
          groups = {"regressionsmoke", "wall.studentRecommendPost"})
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
    @Test(dataProvider = "GrpCrsAssgnmnt", dataProviderClass = Crs_GrpCrsCreation.class,
          groups = {"fullsmoke", "activites.submitAssignment"})
    public void testSubmitAssignment(String grpCrsName, String allInOneAsgnmntAvtvtyName) throws Exception {
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
    @Test(dataProvider = "Course", dataProviderClass = Crs_GrpCrsCreation.class,
          groups = {"regressionsmoke", "note.createOnCourseWall"})
    public void testCreateNoteOnCourseWall(String grpCrsName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        noteCourse[0][0] = a.createNote(grpCrsName);
    }

    /**
     * Create Note on Profile Wall
     *
     * @throws Exception
     */
    @Test(groups = {"regressionsmoke", "note.createOnProfileWall"})
    public void testCreateNoteOnProfileWall() throws Exception {
        a.navigateToMyWall();
        noteWall[0][0] = a.createNote("Profile");
    }
    
    /**
     * Verify Note Sorting 
     * 
     * @param profileNote
     * @throws Exception 
     */
    @Test(dataProvider = "Note", groups = {"regressionsmoke", "note.verifySorting"})
    public void testVerifyNoteSorting(String profileNote) throws Exception {
        a.navigateToMyWall();
        a.verifyNoteSorting(profileNote);
    }

    /**
     * Delete Note 
     * 
     * @param profileNote
     * @throws Exception 
     */
    @Test(dataProvider = "Note", groups = {"regressionsmoke", "note.deleteProfile"})
    public void testDeleteProfileNote(String profileNote) throws Exception {
        a.navigateToMyWall();
        a.deleteNote(profileNote);
    }
    
    /**
     * Verify Resources
     * 
     * @throws Exception 
     */
    @Test(groups = {"regressionsmoke", "resources.verify"})
    public void testVerifyResources() throws Exception {
        a.navigateToMyHome();
        a.verifyResources();
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
