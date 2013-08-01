/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import com.lms.tests.smoketest.Actions;
import java.util.Iterator;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 
 */
public class Student_AllInOneEnable extends BaseClass {
    
    Actions a = new Actions();
    
    @DataProvider(name = "GroupCourseAssignmentResubmissionText")
    public static Iterator<Object[]> GroupCourseAssignmentResubmissionText(ITestContext context) throws Exception {
        System.out.println("init GroupCourseAssignmentResubmissionText");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.GroupCourse(context), ContentAdmin_Course_GroupCourseCreation.AssignmentName(context),
                Teacher_ResubmitAllInOne.TeacherResbubmitAllInOneText(context));
    }

    /**
     * Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testStudentLogIn(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("RegressionTests") || test.equalsIgnoreCase("SmokeTests")) {
            a.login(Pes_UserCreation_AssignRole_WorkingGroup.userNamesArray[0][1]);
        } else {
            a.login(ldv.getTokenValue("studentUserName"));
        }
    }

    /**
     * 
     * @param groupCourseName
     * @param allInOneAssignmentActivityName
     * @param resubmissionText
     * @throws Exception 
     */
    @Test(dataProvider = "GroupCourseAssignmentResubmissionText", groups = {"regressionSmoke", "fullSmoke", "allinone.studentVerifyItCanBeResubmitted"})
    public void testStudentVerifyAllInOneCanBeResubmitted(String groupCourseName, String allInOneAssignmentActivityName, String resubmissionText) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToActivityReport();
        a.navigateToActivity(allInOneAssignmentActivityName);
        a.verifyAllInOneCanBeResubmitted(resubmissionText);
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
