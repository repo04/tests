/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import java.util.Iterator;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.lms.tests.smoketest.Actions;
import com.lms.tests.smoketest.Utility;

/**
 * Student logs in, Create Live Session in Teacher's Social Group, Creates own
 * Social Group Verify Google Doc Verify All Posts on Top/Recent News Verify
 * Activities & resource items appear in activity report
 */
public class Student_LiveSession_SocialGroup_GoogleDoc extends BaseClass {

    String[][] studentSocialGroupNameArray = new String[1][1];
    static String[][] studentUrlPostOnWorkingGroup = new String[1][1];
    private final static String STUDENTSOCIALGROUPNAME = "STUDENTSOCIALGROUP";
    Actions a;

    @DataProvider(name = "StudentSocialGroup")
    public static Object[][] StudentSocialGroup(ITestContext context) throws Exception {
        System.out.println("StudentSocialGroup: " + test);
        return (Utility.getObject(STUDENTSOCIALGROUPNAME));
    }

    @DataProvider(name = "TeacherStudentSocialGroups")
    public static Iterator<Object[]> TeacherStudentSocialGroups(ITestContext context) throws Exception {
        System.out.println("init TeacherStudentSocialGroups");
        return DataProviderUtility.cartesianProviderFrom(Teacher_Posts_SocialGroup.TeacherSocialGroup(context), StudentSocialGroup(context));
    }

    @DataProvider(name = "UsersWorkingGroupTeacherStudentSocialGroups")
    public static Iterator<Object[]> UsersWorkingGroupTeacherStudentSocialGroups(ITestContext context) throws Exception {
        System.out.println("init UsersWorkingGroupTeacherStudentSocialGroups");
        return DataProviderUtility.cartesianProviderFrom(Pes_UserCreation_AssignRole_WorkingGroup.Users(context),
                Pes_UserCreation_AssignRole_WorkingGroup.WorkingGroup(context), Teacher_Posts_SocialGroup.TeacherSocialGroup(context),
                StudentSocialGroup(context));
    }
    
    @DataProvider(name = "WorkingGroupStudentURLPost")
    public static Iterator<Object[]> WorkingGroupStudentURLPost(ITestContext context) throws Exception {
        System.out.println("init WorkingGroupStudentURLPost");
        return DataProviderUtility.cartesianProviderFrom(Pes_UserCreation_AssignRole_WorkingGroup.WorkingGroup(context), studentUrlPostOnWorkingGroup);
    }
    
    @DataProvider(name = "AllGlossaryData")
    public static Iterator<Object[]> AllGlossaryData(ITestContext context) throws Exception {
        System.out.println("init AllGlossaryData");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context), ContentAdmin_Course_GroupCourseCreation.GlossaryName(context),
                Student_JoinSocialGroup_Post.StudentGlossaryEntryName(context), Teacher_Posts_SocialGroup.GlossaryCategoryName(context),
                Teacher_Posts_SocialGroup.TeacherGlossaryEntryName(context));
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testStudentLogInD(ITestContext context) throws Exception {
        a = new Actions(getWebdriver());
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            a.login(Pes_UserCreation_AssignRole_WorkingGroup.userNamesArray[0][1]);
        } else {
            a.login(ldv.getTokenValue("studentUserName"));
        }
    }

    /**
     * Create Live Session in Teacher's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "TeacherSocialGroup", dataProviderClass = Teacher_Posts_SocialGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "liveSession.studentCreate"})
    public void testStudentCreateLiveSession(String teacherSocialGroupName) throws Exception {
        a = new Actions(getWebdriver());
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(teacherSocialGroupName);
        a.accessLiveSessionWall();
        a.createLiveSession();
    }
    
    /**
     * Creates own Social Group
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "socialGroup.studentCreate"})
    public void testStudentCreateSocialGroup() throws Exception {
        a = new Actions(getWebdriver());
        a.navigateToMySocialGroups();
        studentSocialGroupNameArray[0][0] = a.createSocialGroup();
        Utility.put(STUDENTSOCIALGROUPNAME, studentSocialGroupNameArray[0][0]);
        System.out.println("studentSocialGroupName: " + studentSocialGroupNameArray[0][0]);
        Reporter.log("studentSocialGroupName: " + studentSocialGroupNameArray[0][0]);
    }

    /**
     * Verify WorkingGroup & Google Doc
     *
     * @throws Exception
     */
    @Test(dataProvider = "WorkingGroupGoogleDocument", dataProviderClass = Teacher_LiveSession_GoogleDoc.class,
          groups = {"regressionSmoke", "fullSmoke", "workingGroup.studentVerifyGoogleDoc"})
    public void testStudentVerifyWorkingGroup_GoogleDoc(String workingGroupName, String googleDocumentName) throws Exception {
        a.navigateToWorkingGroups();
        a.verifyWorkingGroup_GoogleDoc(workingGroupName, googleDocumentName);
    }
    
    /**
     * Student post on Working Group
     * 
     * @param workingGroupName
     * @param googleDocumentName
     * @throws Exception 
     */
    @Test(dataProvider = "WorkingGroup", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"regressionSmoke", "workingGroup.studentPost"})
    public void testStudentPostOnWorkingGroup(String workingGroupName) throws Exception {
        a.navigateToWorkingGroups();
        a.navigateToGroupWall(workingGroupName);
        studentUrlPostOnWorkingGroup[0][0] =  a.urlPost("urlWrkngGrpPost");
        Reporter.log("studentUrlPostOnWorkingGroup: " + studentUrlPostOnWorkingGroup[0][0], true);
    }

    /**
     * Verify Activities & resource items appear in activity report
     *
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseActivities", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "activities.studentVerify"})
    public void testStudentVerifyActivities(String groupCourseName, String forumActivityName, String quizActivityName, String allInOneAssignmentActivityName, String pageActivityName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.verifyActivities(forumActivityName, quizActivityName, allInOneAssignmentActivityName, pageActivityName);
    }

    /**
     * User attempt to 'True/False' question in Quiz Assignment
     *
     * @param groupCourseName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseQuiz", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "activities.submitQuiz"})
    public void testStudentSubmitQuiz(String groupCourseName, String quizActivityName) throws Exception {
        a = new Actions(getWebdriver());
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.submitQuiz(quizActivityName, "");
    }
    
    /**
     * 
     * @param groupCourseName
     * @param passwordQuizName
     * @param password
     * @throws Exception 
     */
    @Test(dataProvider = "GroupCoursePasswordQuizNamePassword", dataProviderClass = Teacher_FetchActivityPassword.class,
          groups = {"regressionSmoke", "fullSmoke", "pswdQuiz.submitQuiz"})
    public void testStudentSubmitPasswordQuiz(String groupCourseName, String passwordQuizName, String password) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.submitQuiz(passwordQuizName, password);
    }

    /**
     * Verify Assignment Grade
     *
     * @param groupCourseName
     * @param allInOneAssignmentActivityName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseAssignment", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "assignment.verifyGrade"})
    public void testStudentVerifyAssignmentGrade(String groupCourseName, String allInOneAssignmentActivityName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToGrades();
        a.verifyAssignmentGrade(allInOneAssignmentActivityName);
    }
    
    /**
     * Student verify files upload in Course
     * 
     * @param groupCourseName
     * @param pdf
     * @param pptx
     * @param doc
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseFiles", dataProviderClass = Teacher_LiveSession_GoogleDoc.class, 
          groups = {"regressionSmoke", "criticalSmoke", "files.studentVerifyInCourse"})
    public void testStudentVerifyFilesInCourse(String groupCourseName, String pdf, String pptx, String doc) throws Exception {
        a = new Actions(getWebdriver());
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToFiles();
        a.verifyFilesInCourse(doc, pptx, pdf);
    }
    
    /**
     * Student Leave Teacher Social Group
     * 
     * @param teacherSocialGroupName
     * @throws Exception 
     */
    @Test(dataProvider = "TeacherSocialGroup", dataProviderClass = Teacher_Posts_SocialGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "socialGroup.studentLeaveTeacherSocialGroup"})
    public void testStudentLeaveTeacherSocialGroup(String teacherSocialGroupName) throws Exception {
        a.navigateToMySocialGroups();
        a.leaveSocialGroup(teacherSocialGroupName);
    }
    
    /**
     * Student edit Glossary Activity
     * 
     * @param groupCourseName
     * @param glossaryName
     * @param studentGlossaryEntryName
     * @param glossaryCategoryName
     * @param teacherGlossaryEntryName
     * @throws Exception 
     */
    @Test(dataProvider = "AllGlossaryData", groups = {"regressionSmoke", "activities.studentEditGlossary"})
    public void testStudentEditGlossary(String groupCourseName, String glossaryName, 
            String studentGlossaryEntryName, String glossaryCategoryName, String teacherGlossaryEntryName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.navigateToContentPage(glossaryName);
        a.editGlossaryEntry(glossaryName, studentGlossaryEntryName, glossaryCategoryName, teacherGlossaryEntryName);        
    }
    
    //The below method affects all system users - so currently we are skipping this
    /**
     * Student verify Login Message does not appear after again logging in - Currently commented as it affects all system users
     *
     * @throws Exception
     */
    /*
    @Test(groups = {"2torAdministrativeBlock.studentVerificationLoginMessageOnRelogin"})
    public void testStudentReverificationLoginMessage() throws Exception {
        a.studentReverificationLoginMessage();
    } */
    
    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testStudentLogOutD() throws Exception {
        a = new Actions(getWebdriver());
        a.logOut();
    }
}
