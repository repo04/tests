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
public class TransformRegression implements IAnnotationTransformer {

    String DependentMethods[] = null;

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {

        if ("testContentAdminActivitiesCreation".equals(testMethod.getName())) {
            System.out.println("Inside testContentAdminActivitiesCreation");
            DependentMethods = new String[1];
            DependentMethods[0] = "testContentAdminCourseGroupCourseCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testContentAdminAddQuizQuestion".equals(testMethod.getName())) {
            System.out.println("Inside testContentAdminAddQuizQuestion");
            DependentMethods = new String[1];
            DependentMethods[0] = "testContentAdminActivitiesCreation";
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
                || "testStudentCreateSocialGroup".equals(testMethod.getName())
                || "testTeacherAddStudentAsContactfromCourse".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPESAdminAssignRole";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testTeacherVerifyPersonalInformation".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifyPersonalInformation");
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
        
        if ("testStudentConfirmContactRequest".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_Posts_SocialGroup.testTeacherAddStudentAsContactfromCourse";
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
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testContentAdminActivitiesCreation";
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

        if ("testTeacherVerifyStudentPostOnOwnSocialGroupWall".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifyStudentPostOnOwnSocialGroupWall");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Student_JoinSocialGroup_Post.testStudentPostURLOnTeacherSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        //Student_LiveSession_SocialGroup_GoogleDoc
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

        if ("testStudentLeaveTeacherSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside testStudentLeaveTeacherSocialGroup");
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.Student_JoinSocialGroup_Post.testStudentJoinsTeacherSocialGroup";
            DependentMethods[1] = "testStudentCreateLiveSession";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentPostOnWorkingGroup".equals(testMethod.getName())) {
            System.out.println("Inside testStudentPostOnWorkingGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPESAdminAddMembersToWorkingGroup";
            annotation.setDependsOnMethods(DependentMethods);            
        }
        
        //Teacher_JoinDelete_SocialGroup
        if ("testTeacherVerifyStudentsPostExistenceEvenStudentLeftTeacherSocialGroupWall".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifyStudentsPostExistenceEvenStudentLeftTeacherSocialGroupWall");
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.Teacher_LiveSession_GoogleDoc.testTeacherVerifyStudentPostOnOwnSocialGroupWall";
            DependentMethods[1] = "runThrghTestNG.Student_LiveSession_SocialGroup_GoogleDoc.testStudentLeaveTeacherSocialGroup";
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
            DependentMethods[0] = "runThrghTestNG.Teacher_JoinDelete_SocialGroup.testTeacherVerifyStudentsPostExistenceEvenStudentLeftTeacherSocialGroupWall";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
        }

        //Student_DeleteSocialGroup
        if ("testStudentDeleteSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside testStudentDeleteSocialGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Student_LiveSession_SocialGroup_GoogleDoc.testStudentCreateSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        //Pes_CleanTestData
        if ("testPESAdminDeleteAndVerifyStudentPostFromWorkingGroup".equals(testMethod.getName())) {
            System.out.println("Inside testPESAdminDeleteAndVerifyStudentPostFromWorkingGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Student_LiveSession_SocialGroup_GoogleDoc.testStudentPostOnWorkingGroup";
            annotation.setDependsOnMethods(DependentMethods);            
        }

        if ("testPESAdminDeleteWorkingGroup".equals(testMethod.getName())) {
            System.out.println("Inside testPESAdminDeleteWorkingGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "testPESAdminDeleteAndVerifyStudentPostFromWorkingGroup";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
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

        if ("testStudentCreateNoteOnProfileWall".equals(testMethod.getName())) {
            System.out.println("Inside testStudentCreateNoteOnProfileWall");
            DependentMethods = new String[1];
            DependentMethods[0] = "testStudentCreateNoteOnCourseWall";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentVerifyNoteSorting".equals(testMethod.getName())) {
            System.out.println("Inside testStudentVerifyNoteSorting");
            DependentMethods = new String[1];
            DependentMethods[0] = "testStudentCreateNoteOnProfileWall";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentDeleteProfileNote".equals(testMethod.getName())) {
            System.out.println("Inside testStudentDeleteProfileNote");
            DependentMethods = new String[1];
            DependentMethods[0] = "testStudentVerifyNoteSorting";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testPesAdminPostTextOnCourseCommentsOn".equals(testMethod.getName())) {
            System.out.println("Inside testPesAdminPostTextOnCourseCommentsOn");
            DependentMethods = new String[1];
            DependentMethods[0] = "testPesAdminPostTextOnCourseSection";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testPesAdminPostTextOnCourseCommentsOff".equals(testMethod.getName())) {
            System.out.println("Inside testPesAdminPostTextOnCourseCommentsOff");
            DependentMethods = new String[1];
            DependentMethods[0] = "testPesAdminPostTextOnCourseCommentsOn";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testPesAdminPostAnnouncementOnAllCourseSection".equals(testMethod.getName())) {
            System.out.println("Inside testPesAdminPostAnnouncementOnAllCourseSection");
            DependentMethods = new String[1];
            DependentMethods[0] = "testPesAdminPostTextOnCourseCommentsOff";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherPostsOn_Wall_CourseWall".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherPostsOn_Wall_CourseWall");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPesAdminPostAnnouncementOnAllCourseSection";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherVerifyPESCoursePosts".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifyPESCoursePosts");
            DependentMethods = new String[1];
            DependentMethods[0] = "testTeacherPostsOn_Wall_CourseWall";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentVerifyPESCoursePost".equals(testMethod.getName())) {
            System.out.println("Inside testStudentVerifyPESCoursePost");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_Posts_SocialGroup.testTeacherVerifyPESCoursePosts";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentCommentOnTeacherCoursePost".equals(testMethod.getName())) {
            System.out.println("Inside testStudentCommentOnTeacherCoursePost");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_Posts_SocialGroup.testTeacherPostsOn_Wall_CourseWall";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentRecommendCourseURLPost".equals(testMethod.getName())) {
            System.out.println("Inside testStudentRecommendCourseURLPost");
            DependentMethods = new String[1];
            DependentMethods[0] = "testStudentVerifyPESCoursePost";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherVerifyStudentsPostRecommendation".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifyStudentsPostRecommendation");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Student_JoinSocialGroup_Post.testStudentRecommendCourseURLPost";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherVerifyStudentsComment".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifyStudentsComment");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Student_JoinSocialGroup_Post.testStudentCommentOnTeacherCoursePost";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherDeleteCourseURLPost".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherDeleteCourseURLPost");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_JoinDelete_SocialGroup.testTeacherVerifyStudentsPostRecommendation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testPESAdminDeleteAnnouncement".equals(testMethod.getName())) {
            System.out.println("Inside testPESAdminDeleteAnnouncement");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_JoinDelete_SocialGroup.testTeacherDeleteCourseURLPost";
            annotation.setDependsOnMethods(DependentMethods);
        }

        //Syllabus
        if ("testStudentVerifySyllabusActivity".equals(testMethod.getName())) {
            System.out.println("Inside testStudentVerifySyllabusActivity");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testSyllabus_Creation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherVerifySyllabusActivity".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifySyllabusActivity");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testSyllabus_Creation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        //GroupName = Files
        if ("testTeacherUploadFilesInCourse".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherUploadFilesInCourse");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPESAdminAssignRole";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testTeacherVerifyFilesInPortfolio".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifyFilesInPortfolio");
            DependentMethods = new String[1];
            DependentMethods[0] = "testTeacherUploadFilesInCourse";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentVerifyFilesInCourse".equals(testMethod.getName())) {
            System.out.println("Inside testStudentVerifyFilesInCourse");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_LiveSession_GoogleDoc.testTeacherVerifyFilesInPortfolio";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherDeleteFiles".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherDeleteFiles");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_LiveSession_GoogleDoc.testTeacherUploadFilesInCourse";
            annotation.setDependsOnMethods(DependentMethods);
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
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testContentAdminAddQuesToQuizPasswordActivity";
            DependentMethods[1] = "runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPESAdminAssignRole";
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
            DependentMethods[0] = "runThrghTestNG.Teacher_FetchActivityPassword.testTeacherFetchQuizPassword";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        //GroupName = BackUp / Restore
        if ("testContentAdminBackupCourse".equals(testMethod.getName())) {
            System.out.println("Inside testContentAdminBackupCourse");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testContentAdminActivitiesCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testContentAdminRestoreCourseAsNewArchiveCourse".equals(testMethod.getName())) {
            System.out.println("Inside testContentAdminRestoreCourseAsNewArchiveCourse");
            DependentMethods = new String[1];
            DependentMethods[0] = "testContentAdminBackupCourse";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        //GroupName = Glossary
        if ("testContentAdminGlossaryActivityCreation".equals(testMethod.getName())) {
            System.out.println("Inside testContentAdminGlossaryActivityCreation");
            DependentMethods = new String[1];
            DependentMethods[0] = "testContentAdminCourseGroupCourseCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testTeacherCreateGlossaryEntry".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherCreateGlossaryEntry");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testContentAdminGlossaryActivityCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testTeacherCreateGlossaryCategory".equals(testMethod.getName())) {
            System.out.println("Inside testContentAdminRestoreCourseAsNewArchiveCourse");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testContentAdminGlossaryActivityCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testStudentCreateGlossaryEntry".equals(testMethod.getName())) {
            System.out.println("Inside testStudentCreateGlossaryEntry");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testContentAdminGlossaryActivityCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testStudentEditGlossary".equals(testMethod.getName())) {
            System.out.println("Inside testStudentEditGlossary");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Teacher_Posts_SocialGroup.testTeacherCreateGlossaryEntry";
            DependentMethods[0] = "runThrghTestNG.Teacher_Posts_SocialGroup.testTeacherCreateGlossaryCategory";
            DependentMethods[0] = "runThrghTestNG.Student_JoinSocialGroup_Post.testStudentCreateGlossaryEntry";
            annotation.setDependsOnMethods(DependentMethods);
        }
    }
}