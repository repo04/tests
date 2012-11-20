/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 * Student logs in, Find & Join Teacher's Social Group, Post/Verify URL on
 * Teacher's SocialGroup,
 *
 */
public class StdtJnSclGrp_Post extends BaseClass {

    static String stdtUrlPostOnTchrSclGrp;
    static String stdtTxtCmntOnTchrCrsPost;
    Actions a = new Actions();

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
        System.out.println("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);
        Reporter.log("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);
    }

    /**
     * Add Comment on Teacher's CoursePost
     *
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsTchrUrlCrsPst", dataProviderClass = TchrPosts_SclGrp.class,
          groups = {"fullsmoke", "criticalsmoke", "studentComment.TeacherCoursePost"})
    public void testStudentCommentOnTeacherCoursePost(String grpCrsName, String tchrUrlCrsPost) throws Exception {
        a.selectGroupCourse(grpCrsName);
        stdtTxtCmntOnTchrCrsPost = a.textCommentPost(tchrUrlCrsPost, "txtCmntOnTchrCrsPst");
        System.out.println("stdtTxtCmntOnTchrCrsPost: " + stdtTxtCmntOnTchrCrsPost);
        Reporter.log("stdtTxtCmntOnTchrCrsPost: " + stdtTxtCmntOnTchrCrsPost);
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
