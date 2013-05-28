/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.lms.tests.smoketest.Actions;

/**
 * Teacher Logs in Find, Join & Leave Student's Social Group, Deletes own Social
 * Group
 */
public class Student_DeleteSocialGroup extends BaseClass {

    Actions a = new Actions();

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
     * Deletes own Social Group
     *
     * @throws Exception
     */
    @Test(dataProvider = "StudentSocialGroup", dataProviderClass = Student_LiveSession_SocialGroup_GoogleDoc.class,
          groups = {"regressionSmoke", "fullSmoke", "criticalSmoke", "socialGroup.studentDelete"})
    public void testStudentDeleteSocialGroup(String studentSocialGroupName) throws Exception {
        a.navigateToMySocialGroups();
        a.navigateToGroupWall(studentSocialGroupName);
        a.deleteSocialGroup(studentSocialGroupName);
    }
    
    /**
     * Verifies Support page
     */
    @Test(groups = {"regressionSmoke", "support.studentUIVerify"})
    public void testStudentSupportPage() {
        a.navigateToMyHome();
        a.navigateToSupport("Student");
        a.testSupportPage("Student");
    }

    /**
     * Verifies Mobile section on Support page
     */
    @Test(groups = {"regressionSmoke", "support.studentMobileAppURL"})
     public void testStudentSupportMobileURL() {
        a.navigateToMyHome();
        a.navigateToSupport("Student");
        a.testSupportMobileAppURL("Student");
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