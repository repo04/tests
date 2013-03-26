/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * This method will be invoked by TestNG to give you a chance to modify a TestNG
 * annotation read from your test classes. You can change the values you need by
 * calling any of the setters on the ITest interface.
 *
 * Note that only one of the three parameters testClass, testConstructor and
 * testMethod will be non-null.
 *
 * @param annotation The annotation that was read from your test class.
 * @param testClass If the annotation was found on a class, this parameter
 * represents this class (null otherwise).
 * @param testConstructor If the annotation was found on a constructor, this
 * parameter represents this constructor (null otherwise).
 * @param testMethod If the annotation was found on a method, this parameter
 * represents this method (null otherwise).
 */
public class TransformSmoke implements IAnnotationTransformer {

    String DependentMethods[] = null;

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {

        if ("testContentAdminCourseGroupCourseCreation".equals(testMethod.getName())) {
            System.out.println("Inside testContentAdminCourseGroupCourseCreation");
            DependentMethods = new String[1];
            DependentMethods[0] = "testContentAdminCourseGroupCourseCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testContentAdminAddQuizQuestion".equals(testMethod.getName())) {
            System.out.println("Inside testContentAdminAddQuizQuestion");
            DependentMethods = new String[1];
            DependentMethods[0] = "testContentAdminCourseGroupCourseCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testPESAdminAssignRole".equals(testMethod.getName())) {
            System.out.println("Inside testPESAdminAssignRole");
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testContentAdminCourseGroupCourseCreation";
            DependentMethods[1] = "testPESAdminUserCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testPESAdminAddMembersToWorkingGroup".equals(testMethod.getName())) {
            System.out.println("Inside testPESAdminAddMembersToWorkingGroup");
            DependentMethods = new String[2];
            DependentMethods[0] = "testPESAdminCreateWorkingGroup";
            DependentMethods[1] = "testPESAdminAssignRole";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherPostsOn_Wall_CourseWall".equals(testMethod.getName())
                || "testTeacherCreateSocialGroup".equals(testMethod.getName())
                || "testStudentCreateSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPESAdminAssignRole";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentJoinsTeacherSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside testStudentJoinsTeacherSocialGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_Posts_SocialGroup.testTeacherCreateSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentPostURLOnTeacherSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside testStudentPostURLOnTeacherSocialGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "testStudentJoinsTeacherSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentCommentOnTeacherCoursePost".equals(testMethod.getName())) {
            System.out.println("Inside testStudentCommentOnTeacherCoursePost");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_Posts_SocialGroup.testTeacherPostsOn_Wall_CourseWall";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherCreateLiveSession".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherCreateLiveSession");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_Posts_SocialGroup.testTeacherCreateSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherCreateGoogleDoc".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherCreateGoogleDoc");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPESAdminAddMembersToWorkingGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherVerifyActivities".equals(testMethod.getName())
                || "testStudentVerifyActivities".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testContentAdminCourseGroupCourseCreation";
            DependentMethods[1] = "runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPESAdminAssignRole";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testTeacherGradeAssignment".equals(testMethod.getName())
                || "testTeacherAllowResubmitAssignment".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherGradeAssignment");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Student_JoinSocialGroup_Post.testStudentSubmitAssignment";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentVerifyAssignmentGrade".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_LiveSession_GoogleDoc.testTeacherGradeAssignment";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentSubmitQuiz".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testContentAdminAddQuizQuestion";
            DependentMethods[1] = "runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPESAdminAssignRole";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentCreateLiveSession".equals(testMethod.getName())) {
            System.out.println("Inside testStudentCreateLiveSession");
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.Student_JoinSocialGroup_Post.testStudentJoinsTeacherSocialGroup";
            DependentMethods[1] = "runThrghTestNG.Teacher_LiveSession_GoogleDoc.testTeacherCreateLiveSession";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentVerifyWorkingGroup_GoogleDoc".equals(testMethod.getName())) {
            System.out.println("Inside testStudentVerifyWorkingGroup_GoogleDoc");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_LiveSession_GoogleDoc.testTeacherCreateGoogleDoc";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherJoinsStudentSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherJoinsStudentSocialGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Student_LiveSession_SocialGroup_GoogleDoc.testStudentCreateSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherLeavesStudentSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherLeavesStudentSocialGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "testTeacherJoinsStudentSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherDeleteSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherDeleteSocialGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_Posts_SocialGroup.testTeacherCreateSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentDeleteSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside testStudentDeleteSocialGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Student_LiveSession_SocialGroup_GoogleDoc.testStudentCreateSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testPESAdminDeleteWorkingGroup".equals(testMethod.getName())) {
            System.out.println("Inside testPESAdminDeleteWorkingGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Student_LiveSession_SocialGroup_GoogleDoc.testStudentVerifyWorkingGroup_GoogleDoc";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
        }
        
        //GroupName = PswdQuiz
        if ("testContentAdminAddQuesToQuizPasswordActivity".equals(testMethod.getName())) {
            System.out.println("Inside testContentAdminAddQuesToQuizPasswordActivity");
            DependentMethods = new String[1];
            DependentMethods[0] = "testContentAdminCreateQuizPasswordActivity";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherGenerateQuizPassword".equals(testMethod.getName())) {
            System.out.println("Inside testContentAdminAddQuesToQuizPasswordActivity");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testContentAdminAddQuesToQuizPasswordActivity";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherFetchQuizPassword".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherFetchQuizPassword");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_Posts_SocialGroup.testTeacherGenerateQuizPassword";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentSubmitPasswordQuiz".equals(testMethod.getName())) {
            System.out.println("Inside testStudentSubmitPasswordQuiz");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_FetchAssignmentPassword.testTeacherFetchQuizPassword";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testPESAdminDeleteUsers".equals(testMethod.getName())) {
            System.out.println("Inside testPESAdminDeleteUsers");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Student_DeleteSocialGroup.testStudentDeleteSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
        }

        if ("testPESAdminArchiveCourse".equals(testMethod.getName())) {
            System.out.println("Inside testPESAdminArchiveCourse");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Pes_CleanTestData.testPESAdminDeleteUsers";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
        }

        if ("testTeacherVerifyFullSmokeTestEmails".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifyFullSmokeTestEmails");
            DependentMethods = new String[5];
            DependentMethods[0] = "runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPESAdminAddMembersToWorkingGroup";
            DependentMethods[1] = "runThrghTestNG.Student_JoinSocialGroup_Post.testStudentJoinsTeacherSocialGroup";
            DependentMethods[2] = "runThrghTestNG.Student_JoinSocialGroup_Post.testStudentCommentOnTeacherCoursePost";
            DependentMethods[3] = "runThrghTestNG.Teacher_JoinDelete_SocialGroup.testTeacherJoinsStudentSocialGroup";
            DependentMethods[4] = "runThrghTestNG.Pes_ArchiveCourse.testPESAdminArchiveCourse";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentVerifyFullSmokeTestEmails".equals(testMethod.getName())) {
            System.out.println("Inside testStudentVerifyFullSmokeTestEmails");
            DependentMethods = new String[4];
            DependentMethods[0] = "runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPESAdminAddMembersToWorkingGroup";
            DependentMethods[1] = "runThrghTestNG.Student_JoinSocialGroup_Post.testStudentJoinsTeacherSocialGroup";
            DependentMethods[2] = "runThrghTestNG.Teacher_JoinDelete_SocialGroup.testTeacherJoinsStudentSocialGroup";
            DependentMethods[3] = "runThrghTestNG.Pes_ArchiveCourse.testPESAdminArchiveCourse";
            annotation.setDependsOnMethods(DependentMethods);
        }
    }
}