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
 * Teacher LOGS in, Find SclGroup & Create LiveSession in SclGroup, Create
 * GoogleDoc in Working Group Verify All Posts on Top & RecentNews Verify
 * Activities & resource appear items on activity report
 */
public class Tchr_LvSsn_GglDoc extends BaseClass {

    static String[][] gglDocArray = new String[1][1];
    static String[][] filesArray = new String[1][3];
    Actions a = new Actions();

    @DataProvider(name = "GglDoc")
    public static Object[][] GglDoc(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("if GglDoc: " + test);
            return (gglDocArray);
        } else {
            System.out.println("else GglDoc: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("gglDocName")}};
        }
    }

    @DataProvider(name = "WrkngGrpGgleDoc")
    public static Iterator<Object[]> WrkngGrpGgleDoc(ITestContext context) throws Exception {
        System.out.println("init WrkngGrpGgleDoc");
        return DataProviderUtil.cartesianProviderFrom(Pes_UsrCrtn_AsgnRole_WrkngGrp.WrkngGrp(context), GglDoc(context));
    }
    
    @DataProvider(name = "Files")
    public static Object[][] Files(ITestContext context) throws Exception {
        filesArray[0][0] = "UploadPDF_2Mb.pdf";
        filesArray[0][1] = "UploadPPT_4Mb.pptx";
        filesArray[0][2] = "UploadWord_14Mb.doc";
        return (filesArray);        
    }
    
    @DataProvider(name = "GrpCrsFiles")
    public static Iterator<Object[]> GrpCrsFiles(ITestContext context) throws Exception {
        System.out.println("init GrpCrsFiles");
        return DataProviderUtil.cartesianProviderFrom(CntAdmin_Crs_GrpCrsCreation.Course(context), Files(context));
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
            a.login(Pes_UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][0]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("tchrUsrName"));
        }
    }

    /**
     * Find SclGroup & Create LiveSession in SclGroup
     *
     * @throws Exception
     */
    @Test(dataProvider = "TchrSclGrp", dataProviderClass = Tchr_Posts_SclGrp.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalsmoke", "liveSession.teacherCreate"})
    public void testTeacherCreateLiveSession(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.accessSocialGroupWall(tchrSclGrpName);
        a.accessLiveSessionWall();
        a.createLiveSession();
    }

    /**
     * Create GoogleDoc in Working Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "WrkngGrp", dataProviderClass = Pes_UsrCrtn_AsgnRole_WrkngGrp.class,
          groups = {"regressionSmoke", "fullSmoke", "workingGroup.teacherCreateGoogleDoc"})
    public void testTeacherCreateGoogleDoc(String wrkngGrpName) throws Exception {
        a.navigateToWorkingGroups();
        gglDocArray[0][0] = a.createGoogleDoc(wrkngGrpName);
        System.out.println("gglDocName: " + gglDocArray[0][0]);
        Reporter.log("gglDocName: " + gglDocArray[0][0]);
    }

    /**
     * Verify Activities & resource appear items on activity report
     *
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsActivities", dataProviderClass = CntAdmin_Crs_GrpCrsCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "activites.teacherVerify"})
    public void testTeacherVerifyActivities(String grpCrsName, String frmActvyName, String quizActvtyName, String allInOneAsgnmntAvtvtyName, String pageActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToActivityReport();
        a.verifyActivities(frmActvyName, quizActvtyName, allInOneAsgnmntAvtvtyName, pageActvtyName);
    }

    /**
     * Grade Assignment
     *
     * @param grpCrsName
     * @param allInOneAsgnmntAvtvtyName
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsAssgnmnt", dataProviderClass = CntAdmin_Crs_GrpCrsCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "assignment.grade"})
    public void testTeacherGradeAssignment(String grpCrsName, String allInOneAsgnmntAvtvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToGrades();
        a.gradeAssignment(allInOneAsgnmntAvtvtyName);
    }

    /**
     * Teacher upload files of multiple formats(pdf, pptx, doc)
     * 
     * @param grpCrsName
     * @param pdf
     * @param pptx
     * @param doc
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsFiles", groups = {"regressionSmoke", "files.teacherUploadInCourse"})
    public void testTeacherUploadFilesInCourse(String grpCrsName, String pdf, String pptx, String doc) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.navigateToFiles();
        a.uploadFiles(pdf, pptx, doc);
    }

    /**
     * Teacher verify all uploaded files in Portfolio
     * 
     * @param grpCrsName
     * @param pdf
     * @param pptx
     * @param doc
     * @throws Exception 
     */
    @Test(dataProvider = "Files", groups = {"regressionSmoke", "files.teacherVerifyInPortfolio"})
    public void testTeacherVerifyFilesInPortfolio(String pdf, String pptx, String doc) throws Exception {
        a.navigateToMyHome();
        a.navigateToPortfolio();
        a.verifyFilesInPortfolio(doc, pptx, pdf);
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
