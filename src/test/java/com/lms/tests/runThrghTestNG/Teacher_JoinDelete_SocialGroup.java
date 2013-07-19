/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import com.lms.tests.smoketest.Actions;
import java.util.Iterator;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Teacher Logs in Find, Join & Leave Student's Social Group, Deletes own Social
 * Group
 */
public class Teacher_JoinDelete_SocialGroup extends BaseClass {
    
    static String[][] teacherGradeStudentAllInOne = new String[1][1];

    @DataProvider(name = "GroupCourseAssignmentGradingText")
    public static Iterator<Object[]> GroupCourseAssignmentGradingText(ITestContext context) throws Exception {
        System.out.println("init GroupCourseAssignmentGradingText");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.GroupCourse(context), ContentAdmin_Course_GroupCourseCreation.AssignmentName(context),
                Student_LiveSession_SocialGroup_GoogleDoc.StudentSubmitAssignmentForGradingText(context));
    }
    
    @DataProvider(name = "TeacherGradeStudentAllInOneText")
    public static Object[][] TeacherGradeStudentAllInOneText(ITestContext context) throws Exception {
        System.out.println("init TeacherGradeStudentAllInOneText");
        return (teacherGradeStudentAllInOne);
    }
    
    Actions a = new Actions();

    /**
     * Teacher Logs in
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
     * Find & Join Student's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "StudentSocialGroup", dataProviderClass = Student_LiveSession_SocialGroup_GoogleDoc.class,
    groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "socialGroup.teacherJoinStudents"})
    public void testTeacherJoinsStudentSocialGroup(String studentSocialGroupName) throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(studentSocialGroupName);
        a.joinSocialGroup(studentSocialGroupName);
    }

    /**
     * Leaves Student's Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "StudentSocialGroup", dataProviderClass = Student_LiveSession_SocialGroup_GoogleDoc.class,
    groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "socialGroup.teacherLeaveStudents"})
    public void testTeacherLeavesStudentSocialGroup(String studentSocialGroupName) throws Exception {
        a.navigateToMySocialGroups();
        a.leaveSocialGroup(studentSocialGroupName);
    }

    /**
     * Teacher verify Students post existence even Student left Teacher Social Group
     *
     * @param teacherSocialGroupName
     * @param studentUrlPostOnTeacherSocialGroup
     * @throws Exception
     */
    @Test(dataProvider = "TeacherSocialGroupStudentUrlPost", dataProviderClass = Student_JoinSocialGroup_Post.class,
    groups = {"regressionSmoke", "socialGroup.teacherVerifyStudentsPostExistenceEvenStudentLeftTeacherSocialGroupWall"})
    public void testTeacherVerifyStudentsPostExistenceEvenStudentLeftTeacherSocialGroupWall(String teacherSocialGroupName, String studentUrlPostOnTeacherSocialGroup) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(teacherSocialGroupName);
        a.verifyPostOnSocialGroupWall(studentUrlPostOnTeacherSocialGroup);
    }

    /**
     * Deletes own Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "TeacherSocialGroup", dataProviderClass = Teacher_Posts_SocialGroup.class,
    groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "socialGroup.teacherDelete"})
    public void testTeacherDeleteSocialGroup(String teacherSocialGroupName) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(teacherSocialGroupName);
        a.deleteSocialGroup(teacherSocialGroupName);
    }

    /**
     * Verify Students Post Recommendation
     *
     * @param groupCourseName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourse", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
    groups = {"regressionSmoke", "wall.teacherVerifyStudentsPostRecommendation"})
    public void testTeacherVerifyStudentsPostRecommendation(String groupCourseName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.verifyPostRecommendation();
    }

    /**
     * Verify Students comment on Post
     *
     * @param groupCourseName
     * @param studentTextCommentOnTeacherCoursePost
     * @throws Exception
     */
    @Test(dataProvider = "CourseStudentComment", dataProviderClass = Student_JoinSocialGroup_Post.class,
    groups = {"regressionSmoke", "wall.teacherVerifyStudentsCommentOnPost"})
    public void testTeacherVerifyStudentsComment(String groupCourseName, String studentTextCommentOnTeacherCoursePost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.verifyCommentOnPost(studentTextCommentOnTeacherCoursePost);
    }

    /**
     * Delete Post
     *
     * @param groupCourseName
     * @param teacherUrlCoursePost
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseTeacherUrlCoursePost", dataProviderClass = Teacher_Posts_SocialGroup.class,
    groups = {"regressionSmoke", "wall.teacherDeleteCourseURLPost"})
    public void testTeacherDeleteCourseURLPost(String groupCourseName, String teacherUrlCoursePost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.deletePost(teacherUrlCoursePost);
    }

    /**
     * Teacher delete single file uploaded in Course
     *
     * @param groupCourseName
     * @param pdf
     * @param pptx
     * @param doc
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseFiles", dataProviderClass = Teacher_LiveSession_GoogleDoc.class,
    groups = {"regressionSmoke", "criticalSmoke", "fullSmoke", "files.teacherDelete"})
    public void testTeacherDeleteFiles(String groupCourseName, String pdf, String pptx, String doc) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
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
     * Teacher verify Calendar on Home Page
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "calendar.teacherVerify"})
    public void testTeacherVerifyCalendar() throws Exception {
        a.navigateToMyHome();
        a.verifyCalendar();
    }

    /**
     * Verifies Support page
     */
    @Test(groups = {"regressionSmoke", "support.teacherUIVerify"})
    public void testTeacherSupportPage() {
        a.navigateToMyHome();
        a.navigateToSupport("Teacher");
        a.testSupportPage("Teacher");
    }

    /**
     * Verifies Mobile section on Support page
     */
    @Test(groups = {"regressionSmoke", "support.teacherMobileAppURL"})
    public void testTeacherSupportMobileURL() {
        a.navigateToMyHome();
        a.navigateToSupport("Teacher");
        a.testSupportMobileAppURL("Teacher");
    }

    /**
     * Grade Assignment - testTeacherGradeAssignment
     *
     * @param groupCourseName
     * @param allInOneAssignmentActivityName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseAssignmentGradingText", groups = {"regressionSmoke", "fullSmoke", "allinone.teacherVerifyStudentsGradedSubmissionThenAddGradeAndCommentOnGradePage"})
    public void testTeacherVerifyStudentsAllInOneGradedSubmissionThenAddGradeAndCommentOnGradePage(String groupCourseName, String allInOneAssignmentActivityName, String studentAssignmentGradingText) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToGrades();        
        teacherGradeStudentAllInOne[0][0] = a.verifyStudentsAllInOneFinalSubmissionThenAddGradeAndCommentOnGradePage(allInOneAssignmentActivityName, studentAssignmentGradingText);
        Reporter.log("teacherGradeStudentAllInOneText: " + teacherGradeStudentAllInOne[0][0], true);
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
