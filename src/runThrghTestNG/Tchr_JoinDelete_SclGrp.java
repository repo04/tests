/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 * Teacher Logs in Find, Join & Leave Student's Social Group, Deletes own Social
 * Group
 */
public class Tchr_JoinDelete_SclGrp extends BaseClass {

    Actions a = new Actions();
    
    /**
     * Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testTeacherLogin(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            a.login(Pes_UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][0]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("tchrUsrName"));
        }
    }

    /**
     * Find & Join Student's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "StdtSclGrp", dataProviderClass = Stdt_LvSsn_SclGrp_GglDoc.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "socialGroup.teacherJoinStudents"})
    public void testTeacherJoinsStudentSocialGroup(String stdtSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(stdtSclGrpName);
        a.joinSocialGroup(stdtSclGrpName);
    }

    /**
     * Leaves Student's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "StdtSclGrp", dataProviderClass = Stdt_LvSsn_SclGrp_GglDoc.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "socialGroup.teacherLeaveStudents"})
    public void testTeacherLeavesStudentSocialGroup(String stdtSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.leaveSocialGroup(stdtSclGrpName);
    }
    
    /**
     * Teacher verify Students post existence even Student left Teacher Social Group
     * 
     * @param tchrSclGrpName
     * @param stdtUrlPostOnTchrSclGrp
     * @throws Exception 
     */
    @Test(dataProvider = "TchrSclGrpStdtUrlPost", dataProviderClass = Stdt_JnSclGrp_Post.class,
          groups = {"regressionSmoke", "socialGroup.teacherVerifyStudentsPostExistenceEvenStudentLeftTeacherSocialGroupWall"})
    public void testTeacherVerifyStudentsPostExistenceEvenStudentLeftTeacherSocialGroupWall(String tchrSclGrpName, String stdtUrlPostOnTchrSclGrp) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(tchrSclGrpName);
        a.verifyPostOnSocialGroupWall(stdtUrlPostOnTchrSclGrp);
    }

    /**
     * Deletes own Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = Tchr_Posts_SclGrp.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "socialGroup.teacherDelete"})
    public void testTeacherDeleteSocialGroup(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(tchrSclGrpName);
        a.deleteSocialGroup(tchrSclGrpName);
    }

    /**
     * Allow Assignment to be resubmitted
     *
     * @param grpCrsName
     * @param allInOneAsgnmntAvtvtyName
     * @param stdtUsrName
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsAsgnmntStdt", dataProviderClass = Pes_UsrCrtn_AsgnRole_WrkngGrp.class,
          groups = {"regressionSmoke", "fullSmoke", "assignment.allowResubmit"})
    public void testTeacherAllowResubmitAssignment(String grpCrsName, String allInOneAsgnmntAvtvtyName, String stdtUsrName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToGrades();
        a.allowResubmitAssignment(allInOneAsgnmntAvtvtyName, stdtUsrName);
    }

    /**
     * Verify Students Post Recommendation
     * 
     * @param grpCrsName
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "wall.teacherVerifyStudentsPostRecommendation"})
    public void testTeacherVerifyStudentsPostRecommendation(String grpCrsName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.verifyPostRecommendation();
    }
    
    /**
     * Verify Students comment on Post
     * 
     * @param grpCrsName
     * @param stdtTxtCmntOnTchrCrsPost
     * @throws Exception 
     */
    @Test(dataProvider = "CrsStdtCmnt", dataProviderClass = Stdt_JnSclGrp_Post.class,
          groups = {"regressionSmoke", "wall.teacherVerifyStudentsCommentOnPost"})
    public void testTeacherVerifyStudentsComment(String grpCrsName, String stdtTxtCmntOnTchrCrsPost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.verifyCommentOnPost(stdtTxtCmntOnTchrCrsPost);
    }

    /**
     * Delete Post
     * 
     * @param grpCrsName
     * @param tchrUrlCrsPost
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsTchrUrlCrsPst", dataProviderClass = Tchr_Posts_SclGrp.class,
          groups = {"regressionSmoke", "wall.teacherDeleteCourseURLPost"})
    public void testTeacherDeleteCourseURLPost(String grpCrsName, String tchrUrlCrsPost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.deletePost(tchrUrlCrsPost);
    }
    
    /**
     * Teacher delete single file uploaded in Course
     * 
     * @param grpCrsName
     * @param pdf
     * @param pptx
     * @param doc
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsFiles", dataProviderClass = Tchr_LvSsn_GglDoc.class,
          groups = {"regressionSmoke", "files.teacherDelete"})
    public void testTeacherDeleteFiles(String grpCrsName, String pdf, String pptx, String doc) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToFiles();
        a.deleteFiles(doc, pptx, pdf);
    }
    
    @Test(groups = {"image.upload"})
    public void testTeacherUploadVideo() throws Exception {
        a.navigateToMyWall();
        a.testUploadVideo();        
    }
    
    /**
     * Teacher verify Help Window on Home Page
     * 
     * @throws Exception 
     */
    /*@Test(groups = {"regressionSmoke", "help.teacherVerify"})
    public void testTeacherVerifyHelpWindow() throws Exception {
        a.navigateToMyHome();
        a.verifyHelpWindow();
    }*/

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
