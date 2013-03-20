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
 * Student logs in, Create Live Session in Teacher's Social Group, Creates own
 * Social Group Verify Google Doc Verify All Posts on Top/Recent News Verify
 * Activities & resource items appear in activity report
 */
public class Student_LiveSession_SocialGroup_GoogleDoc extends BaseClass {

    static String[][] stdtSclGrpArray = new String[1][1];
    static String[][] stdtUrlPostOnWrkngGrp = new String[1][1];
    Actions a = new Actions();

    @DataProvider(name = "StdtSclGrp")
    public static Object[][] StdtSclGrp(ITestContext context) throws Exception {
        System.out.println("StdtSclGrp: " + test);
        return (stdtSclGrpArray);
    }

    @DataProvider(name = "TchrStdtSclGrps")
    public static Iterator<Object[]> GrpCrsWrkngGrpUsers(ITestContext context) throws Exception {
        System.out.println("init TchrStdtSclGrps");
        return DataProviderUtility.cartesianProviderFrom(Tchr_Posts_SclGrp.TchrSclGrp(context), StdtSclGrp(context));
    }

    @DataProvider(name = "UsrsWrkngGrpTchrStdtSclGrps")
    public static Iterator<Object[]> UsrsWrkngGrpTchrStdtSclGrps(ITestContext context) throws Exception {
        System.out.println("init UsrsWrkngGrpTchrStdtSclGrps");
        return DataProviderUtility.cartesianProviderFrom(Pes_UserCreation_AssignRole_WorkingGroup.Users(context),
                Pes_UserCreation_AssignRole_WorkingGroup.WrkngGrp(context), Tchr_Posts_SclGrp.TchrSclGrp(context),
                StdtSclGrp(context));
    }
    
    @DataProvider(name = "WrkngGrpStdtURLPost")
    public static Iterator<Object[]> WrkngGrpStdtURLPost(ITestContext context) throws Exception {
        System.out.println("init WrkngGrpStdtURLPost");
        return DataProviderUtility.cartesianProviderFrom(Pes_UserCreation_AssignRole_WorkingGroup.WrkngGrp(context), stdtUrlPostOnWrkngGrp);
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testStudentLogin(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            a.login(Pes_UserCreation_AssignRole_WorkingGroup.usrsArray[0][1]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("stdtUsrName"));
        }
    }

    /**
     * Create Live Session in Teacher's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = Tchr_Posts_SclGrp.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "liveSession.studentCreate"})
    public void testStudentCreateLiveSession(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(tchrSclGrpName);
        a.accessLiveSessionWall();
        a.createLiveSession();
    }
    
    /**
     * Creates own Social Group
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "socialGroup.studentCreate"})
    public void testStudentCreateSocialGroup() throws Exception {
        a.navigateToMySocialGroups();
        stdtSclGrpArray[0][0] = a.createSocialGroup();
        System.out.println("stdtSclGrpName: " + stdtSclGrpArray[0][0]);
        Reporter.log("stdtSclGrpName: " + stdtSclGrpArray[0][0]);
    }

    /**
     * Verify WrkngGrp & Google Doc
     *
     * @throws Exception
     */
    @Test(dataProvider = "WrkngGrpGgleDoc", dataProviderClass = Tchr_LvSsn_GglDoc.class,
          groups = {"regressionSmoke", "fullSmoke", "workingGroup.studentVerifyGoogleDoc"})
    public void testStudentVerifyWorkingGroup_GoogleDoc(String wrkngGrpName, String gglDocName) throws Exception {
        a.navigateToWorkingGroups();
        a.vrfyWrkngGrp_GglDoc(wrkngGrpName, gglDocName);
    }
    
    /**
     * Student post on Working Group
     * 
     * @param wrkngGrpName
     * @param gglDocName
     * @throws Exception 
     */
    @Test(dataProvider = "WrkngGrp", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"regressionSmoke", "workingGroup.studentPost"})
    public void testStudentPostOnWorkingGroup(String wrkngGrpName) throws Exception {
        a.navigateToWorkingGroups();
        a.navigateToGroupWall(wrkngGrpName);
        stdtUrlPostOnWrkngGrp[0][0] =  a.urlPost("urlWrkngGrpPost");
        Reporter.log("stdtUrlPostOnWrkngGrp: " + stdtUrlPostOnWrkngGrp[0][0], true);
    }

    /**
     * Verify Activities & resource items appear in activity report
     *
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsActivities", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "activites.studentVerify"})
    public void testStudentVerifyActivities(String grpCrsName, String frmActvyName, String quizActvtyName, String allInOneAsgnmntAvtvtyName, String pageActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToActivityReport();
        a.verifyActivities(frmActvyName, quizActvtyName, allInOneAsgnmntAvtvtyName, pageActvtyName);
    }

    /**
     * User attempt to 'True/False' question in Quiz Assignment
     *
     * @param grpCrsName
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsQz", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "activites.submitQuiz"})
    public void testStudentSubmitQuiz(String grpCrsName, String quizActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToActivityReport();
        a.submitQuiz(quizActvtyName, "");
    }
    
    /**
     * 
     * @param grpCrsName
     * @param pswdQzName
     * @param password
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsPswdQzNamePassword", dataProviderClass = Tchr_FetchAssignmentPassword.class,
          groups = {"regressionSmoke", "fullSmoke", "pswdQuiz.submitQuiz"})
    public void testStudentSubmitPasswordQuiz(String grpCrsName, String pswdQzName, String password) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToActivityReport();
        a.submitQuiz(pswdQzName, password);
    }

    /**
     * Verify Assignment Grade
     *
     * @param grpCrsName
     * @param allInOneAsgnmntAvtvtyName
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsAssgnmnt", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "assignment.verifyGrade"})
    public void testStudentVerifyAssignmentGrade(String grpCrsName, String allInOneAsgnmntAvtvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToGrades();
        a.verifyAssignmentGrade(allInOneAsgnmntAvtvtyName);
    }
    
    /**
     * Student verify files upload in Course
     * 
     * @param grpCrsName
     * @param pdf
     * @param pptx
     * @param doc
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsFiles", dataProviderClass = Tchr_LvSsn_GglDoc.class, 
          groups = {"regressionSmoke", "files.studentVerifyInCourse"})
    public void testStudentVerifyFilesInCourse(String grpCrsName, String pdf, String pptx, String doc) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToFiles();
        a.verifyFilesInCourse(doc, pptx, pdf);
    }
    
    /**
     * Student Leave Teacher Social Group
     * 
     * @param tchrSclGrpName
     * @throws Exception 
     */
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = Tchr_Posts_SclGrp.class,
          groups = {"regressionSmoke", "fullSmoke", "socialGroup.studentLeaveTeacherSocialGroup"})
    public void testStudentLeaveTeacherSocialGroup(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.leaveSocialGroup(tchrSclGrpName);
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
