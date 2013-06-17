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
public class Teacher_LiveSession_GoogleDoc extends BaseClass {

    static String[][] googleDocumentNameArray = new String[1][1];
    static String[][] filesArray = new String[1][3];
    Actions a = new Actions();

    @DataProvider(name = "GoogleDocument")
    public static Object[][] GoogleDocument(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            System.out.println("if GoogleDocument: " + test);
            return (googleDocumentNameArray);
        } else {
            System.out.println("else GoogleDocument: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("googleDocumentName")}};
        }
    }

    @DataProvider(name = "WorkingGroupGoogleDocument")
    public static Iterator<Object[]> WorkingGroupGoogleDocument(ITestContext context) throws Exception {
        System.out.println("init WorkingGroupGoogleDocument");
        return DataProviderUtility.cartesianProviderFrom(Pes_UserCreation_AssignRole_WorkingGroup.WorkingGroup(context), GoogleDocument(context));
    }
    
    @DataProvider(name = "Files")
    public static Object[][] Files(ITestContext context) throws Exception {
        filesArray[0][0] = "UploadPDF_2Mb.pdf";
        filesArray[0][1] = "UploadPPT_4Mb.pptx";
        filesArray[0][2] = "UploadWord_14Mb.doc";
        return (filesArray);        
    }
    
    @DataProvider(name = "GroupCourseFiles")
    public static Iterator<Object[]> GroupCourseFiles(ITestContext context) throws Exception {
        System.out.println("init GroupCourseFiles");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.GroupCourse(context), Files(context));
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
            a.login(Pes_UserCreation_AssignRole_WorkingGroup.userNamesArray[0][0]);
        } else {
            a.login(ldv.getTokenValue("teacherUserName"));
        }
    }

    /**
     * Find SclGroup & Create LiveSession in SclGroup
     *
     * @throws Exception
     */
    @Test(dataProvider = "TeacherSocialGroup", dataProviderClass = Teacher_Posts_SocialGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "liveSession.teacherCreate"})
    public void testTeacherCreateLiveSession(String teacherSocialGroupName) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(teacherSocialGroupName);
        a.accessLiveSessionWall();
        a.createLiveSession();
    }

    /**
     * Create GoogleDoc in Working Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "WorkingGroup", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "workingGroup.teacherCreateGoogleDoc"})
    public void testTeacherCreateGoogleDoc(String workingGroupName) throws Exception {
        a.navigateToWorkingGroups();
        googleDocumentNameArray[0][0] = a.createGoogleDoc(workingGroupName);
        System.out.println("googleDocumentName: " + googleDocumentNameArray[0][0]);
        Reporter.log("googleDocumentName: " + googleDocumentNameArray[0][0]);
    }

    /**
     * Verify Activities & resource appear items on activity report
     *
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseActivities", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "activities.teacherVerify"})
    public void testTeacherVerifyActivities(String groupCourseName, String forumActivityName, String quizActivityName, String allInOneAssignmentActivityName, String pageActivityName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.verifyActivities(forumActivityName, quizActivityName, allInOneAssignmentActivityName, pageActivityName);
    }

    /**
     * Grade Assignment
     *
     * @param groupCourseName
     * @param allInOneAssignmentActivityName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseAssignment", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "assignment.grade"})
    public void testTeacherGradeAssignment(String groupCourseName, String allInOneAssignmentActivityName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToGrades();
        a.gradeAssignment(allInOneAssignmentActivityName);
    }

    /**
     * Teacher upload files of multiple formats(pdf, pptx, doc)
     * 
     * @param groupCourseName
     * @param pdf
     * @param pptx
     * @param doc
     * @throws Exception 
     */
    @Test(dataProvider = "GroupCourseFiles", groups = {"regressionSmoke", "criticalSmoke", "files.teacherUploadInCourse"})
    public void testTeacherUploadFilesInCourse(String groupCourseName, String pdf, String pptx, String doc) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToFiles();
        a.uploadFiles(pdf, pptx, doc);
    }

    /**
     * Teacher verify all uploaded files in Portfolio
     * 
     * @param groupCourseName
     * @param pdf
     * @param pptx
     * @param doc
     * @throws Exception 
     */
    @Test(dataProvider = "Files", groups = {"regressionSmoke", "criticalSmoke", "files.teacherVerifyInPortfolio"})
    public void testTeacherVerifyFilesInPortfolio(String pdf, String pptx, String doc) throws Exception {
        a.navigateToMyHome();
        a.navigateToPortfolio();
        a.verifyFilesInPortfolio(doc, pptx, pdf);
    }
    
    /**
     * Teacher verify Student post on own Social Group wall
     * 
     * @param teacherSocialGroupName
     * @param studentUrlPostOnTeacherSocialGroup
     * @throws Exception 
     */
    @Test(dataProvider = "TeacherSocialGroupStudentUrlPost", dataProviderClass = Student_JoinSocialGroup_Post.class,
          groups = {"regressionSmoke", "socialGroup.teacherVerifyStudentPostOnOwnSocialGroupWall"})
    public void testTeacherVerifyStudentPostOnOwnSocialGroupWall(String teacherSocialGroupName, String studentUrlPostOnTeacherSocialGroup) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(teacherSocialGroupName);
        a.verifyPostOnSocialGroupWall(studentUrlPostOnTeacherSocialGroup);
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
