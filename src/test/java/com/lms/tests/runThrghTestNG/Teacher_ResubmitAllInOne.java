/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import com.lms.tests.smoketest.Actions;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 
 */
public class Teacher_ResubmitAllInOne extends BaseClass {

    Actions a = new Actions();    
    static String[][] teacherResbubmitAllInOne = new String[1][1];
    
    @DataProvider(name = "TeacherResbubmitAllInOneText")
    public static Object[][] TeacherResbubmitAllInOneText(ITestContext context) throws Exception {
        System.out.println("init TeacherResbubmitAllInOneText");
        return (teacherResbubmitAllInOne);
    }

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
     * Allow AllInOne to be resubmitted
     *
     * @param groupCourseName
     * @param allInOneAssignmentActivityName
     * @param studentUserName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseAssignmentStudent", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
    groups = {"regressionSmoke", "fullSmoke", "allinone.teacherAllowStudentToResubmit"})
    public void testTeacherAllowStudentToResubmitAllInOne(String groupCourseName, String allInOneAssignmentActivityName, String studentUserName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.navigateToGrades();
        teacherResbubmitAllInOne[0][0] = a.allowStudentToResubmitAllInOne(allInOneAssignmentActivityName, studentUserName);
        Reporter.log("teacherResbubmitAllInOneText: " + teacherResbubmitAllInOne[0][0], true);
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
