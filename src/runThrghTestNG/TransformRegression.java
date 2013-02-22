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
        
        if ("testActivities_Creation".equals(testMethod.getName())) {
            System.out.println("Inside testActivities_Creation");
            DependentMethods = new String[1];
            DependentMethods[0] = "testCourseGroupCourse_Creation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testAddQuizQuestion".equals(testMethod.getName())) {
            System.out.println("Inside testAddQuizQuestion");
            DependentMethods = new String[1];
            DependentMethods[0] = "testActivities_Creation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testAssignRole".equals(testMethod.getName())) {
            System.out.println("Inside testAssignRole");
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.Crs_GrpCrsCreation.testCourseGroupCourse_Creation";
            DependentMethods[1] = "testUserCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testAddMembersToWorkingGroup".equals(testMethod.getName())) {
            System.out.println("Inside testAddMembersToWorkingGroup");
            DependentMethods = new String[2];
            DependentMethods[0] = "testCreateWorkingGroup";
            DependentMethods[1] = "testAssignRole";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherPostsOn_Wall_CourseWall".equals(testMethod.getName())
                || "testTeacherCreateSocialGroup".equals(testMethod.getName())
                || "testStudentCreateSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAssignRole";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentJoinsTeacherSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside testStudentJoinsTeacherSocialGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTeacherCreateSocialGroup";
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
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTeacherPostsOn_Wall_CourseWall";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherCreateLiveSession".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherCreateLiveSession");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTeacherCreateSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherCreateGoogleDoc".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherCreateGoogleDoc");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAddMembersToWorkingGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherVerifyActivities".equals(testMethod.getName())
                || "testStudentVerifyActivities".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.Crs_GrpCrsCreation.testActivities_Creation";
            DependentMethods[1] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAssignRole";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testTeacherGradeAssignment".equals(testMethod.getName())
                || "testTeacherAllowResubmitAssignment".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherGradeAssignment");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.StdtJnSclGrp_Post.testSubmitAssignment";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testVerifyAssignmentGrade".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrLvSsn_GglDoc.testTeacherGradeAssignment";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testSubmitQuiz".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.Crs_GrpCrsCreation.testAddQuizQuestion";
            DependentMethods[1] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAssignRole";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentCreateLiveSession".equals(testMethod.getName())) {
            System.out.println("Inside testStudentCreateLiveSession");
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.StdtJnSclGrp_Post.testStudentJoinsTeacherSocialGroup";
            DependentMethods[1] = "runThrghTestNG.TchrLvSsn_GglDoc.testTeacherCreateLiveSession";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentVerifyWorkingGroup_GoogleDoc".equals(testMethod.getName())) {
            System.out.println("Inside testStudentVerifyWorkingGroup_GoogleDoc");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrLvSsn_GglDoc.testTeacherCreateGoogleDoc";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherJoinsStudentSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherJoinsStudentSocialGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.StdtLvSsn_SclGrp_GglDoc.testStudentCreateSocialGroup";
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
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTeacherCreateSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentDeleteSocialGroup".equals(testMethod.getName())) {
            System.out.println("Inside testStudentDeleteSocialGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.StdtLvSsn_SclGrp_GglDoc.testStudentCreateSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testDeleteWorkingGroup".equals(testMethod.getName())) {
            System.out.println("Inside testDeleteWorkingGroup");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.StdtLvSsn_SclGrp_GglDoc.testStudentVerifyWorkingGroup_GoogleDoc";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
        }

        if ("testDeleteUsers".equals(testMethod.getName())) {
            System.out.println("Inside testDeleteUsers");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Stdt_DeleteSclGrp.testStudentDeleteSocialGroup";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
        }

        if ("testArchiveCourse".equals(testMethod.getName())) {
            System.out.println("Inside testArchiveCourse");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.PES_CleanTestData.testDeleteUsers";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
        }

        if ("testCreateNoteOnProfileWall".equals(testMethod.getName())) {
            System.out.println("Inside testCreateNoteOnProfileWall");
            DependentMethods = new String[1];
            DependentMethods[0] = "testCreateNoteOnCourseWall";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testVerifyNoteSorting".equals(testMethod.getName())) {
            System.out.println("Inside testVerifyNoteSorting");
            DependentMethods = new String[1];
            DependentMethods[0] = "testCreateNoteOnProfileWall";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testDeleteProfileNote".equals(testMethod.getName())) {
            System.out.println("Inside testDeleteProfileNote");
            DependentMethods = new String[1];
            DependentMethods[0] = "testVerifyNoteSorting";
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
            DependentMethods[0] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testPesAdminPostAnnouncementOnAllCourseSection";
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
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTeacherVerifyPESCoursePosts";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testStudentCommentOnTeacherCoursePost".equals(testMethod.getName())) {
            System.out.println("Inside testStudentCommentOnTeacherCoursePost");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTeacherPostsOn_Wall_CourseWall";
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
            DependentMethods[0] = "runThrghTestNG.StdtJnSclGrp_Post.testStudentRecommendCourseURLPost";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
         if ("testTeacherVerifyStudentsComment".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifyStudentsComment");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.StdtJnSclGrp_Post.testStudentCommentOnTeacherCoursePost";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testTeacherDeleteCourseURLPost".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherDeleteCourseURLPost");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrJoin_Delete_SclGrp.testTeacherVerifyStudentsPostRecommendation";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testPesDeleteAnnouncement".equals(testMethod.getName())) {
            System.out.println("Inside testPesDeleteAnnouncement");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrJoin_Delete_SclGrp.testTeacherDeleteCourseURLPost";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        //Syllabus
        if ("testStudentVerifySyllabusActivity".equals(testMethod.getName())) {
            System.out.println("Inside testStudentVerifySyllabusActivity");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Crs_GrpCrsCreation.testSyllabus_Creation";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testTeacherVerifySyllabusActivity".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifySyllabusActivity");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Crs_GrpCrsCreation.testSyllabus_Creation";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        //GroupName = Files
        if ("testTeacherVerifyFilesInPortfolio".equals(testMethod.getName())) {
            System.out.println("Inside testTeacherVerifyFilesInPortfolio");
            DependentMethods = new String[1];
            DependentMethods[0] = "testTeacherUploadFilesInCourse";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testStudentVerifyFilesUploaded".equals(testMethod.getName())) {
            System.out.println("Inside testStudentVerifyFilesUploaded");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrLvSsn_GglDoc.testTeacherVerifyFilesInPortfolio";
            annotation.setDependsOnMethods(DependentMethods);
        }
    }
}