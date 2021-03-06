/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import com.lms.tests.smoketest.Actions;
import com.lms.tests.smoketest.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author somesh.bansal
 */
public class Pes_CleanTestData extends BaseClass {

    Actions a = new Actions();
    public static boolean teacherStatus;
    public static boolean studentStatus;

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, Pes Admin Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testPesAdminLogIn() throws Exception {
        a.login("pesAdmin");
    }

    /**
     * Un-enroll users from Working Group
     *
     * @param groupCourseName
     * @param teacherUserName
     * @param studentUserName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseUsers", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"users.unAssignRole"})
    public void testPesAdminUnerolUsers(String groupCourseName, String teacherUserName, String studentUserName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.unenrolUsers(studentUserName, teacherUserName);
    }
    
    /**
     * Pes Admin verify Student post deletion from Working Group
     * 
     * @param workingGroupName
     * @param studentUrlPostOnWorkingGroup
     * @throws Exception 
     */
    @Test(dataProvider = "WorkingGroupStudentURLPost", dataProviderClass = Student_LiveSession_SocialGroup_GoogleDoc.class,
          groups = {"regressionSmoke", "workingGroup.pesAdminDeleteAndVerifyStudentPostFromWorkingGroup"})
    public void testPesAdminDeleteAndVerifyStudentPostFromWorkingGroup(String workingGroupName, String studentUrlPostOnWorkingGroup) throws Exception {
        a.navigateToWorkingGroups();
        a.navigateToGroupWall(workingGroupName);
        a.deletePost(studentUrlPostOnWorkingGroup);
    }
    
    /**
     * Remove members from Working Group
     *
     * @param groupCourseName
     * @param workingGroupName
     * @param teacherUserName
     * @param studentUserName
     * @throws Exception
     */
    @Test(dataProvider = "GroupCourseWorkingGroupUsers", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"workingGroup.removeMembers"})
    public void testPesAdminRemoveMembersFromWorkngGroup(String groupCourseName, String workingGroupName, String teacherUserName, String studentUserName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlUsrLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsrLnkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("linkScndNameXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("linkScndNameXPATH"))).click();

        String studentFullName;
        String teacherFullName;

        if (teacherUserName.substring(0, 7).contains("teacher")) {
            teacherFullName = teacherUserName + "fstNm " + teacherUserName + "sndNm";
        } else {
            teacherFullName = Utility.getFullName(teacherUserName);            
        }

        if (studentUserName.substring(0, 7).contains("student")) {
            studentFullName = studentUserName + "fstNm " + studentUserName + "sndNm";
        } else {
            studentFullName = Utility.getFullName(studentUserName);            
        }

        int i = 1;
        while (true) {
            try {
                driver.findElement(By.xpath("//tr[" + i + "]/td/div[2]"));
            } catch (NoSuchElementException e) {
                System.out.println("Total Elements i: " + i);
                break;
            }
            i++;
        }

        teacherStatus = findUser(i, teacherFullName);
        studentStatus = findUser(i, studentFullName);

        a.navigateToWorkingGroups();
        a.accessWorkingGroup(workingGroupName);
        a.removeMembersFromWorkingGroup(teacherUserName, studentUserName);
    }

    /**
     * Delete Working Group
     *
     * @param workingGroupName
     * @throws Exception
     */
    @Test(dataProvider = "WorkingGroup", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "workingGroup.delete"})
    public void testPesAdminDeleteWorkingGroup(String workingGroupName) throws Exception {
        a.navigateToWorkingGroups();
        a.accessWorkingGroup(workingGroupName);
        a.deleteWorkingGroup(workingGroupName);
    }

    /**
     * Delete Users
     *
     * @throws Exception
     */
    @Test(dataProvider = "Users", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"regressionSmoke", "fullSmoke", "users.delete"})
    public void testPesAdminDeleteUsers(String teacherUser, String studentUser) throws Exception {
        a.navigateToMyContacts();
        a.deleteUsers(teacherUser, studentUser);
    }
    
    /**
     * Delete Announcement
     * 
     * @param groupCourseName
     * @param pesTextAnnouncementCoursePost
     * @throws Exception 
     */
    @Test(dataProvider = "GroupCourseAnnouncement", dataProviderClass = Pes_UserCreation_AssignRole_WorkingGroup.class,
          groups = {"regressionSmoke", "wall.pesDeleteAnnouncement"})
    public void testPesAdminDeleteAnnouncement(String groupCourseName, String pesTextAnnouncementCoursePost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(groupCourseName);
        a.deletePost(pesTextAnnouncementCoursePost);
    }
    
    //The below method affects all system users - so currently we are skipping this
    /**
     * Pes admin disables the student support message created previously - Currently commented as it affects all system users
     *
     */
    /*
    @Test(groups = {"2torAdministrativeBlock.supportAndLoginMessageDisableByPesAdmin"})
    public void testPesAdmindisableStudentSupportMessage() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToStudentSupportMessage();
        a.disableStudentSupportMessageByPesAdmin();
    } */

    //The below method affects all system users - so currently we are skipping this
    /**
     * Pes admin disables the student support Login message created previously - Currently commented as it affects all system users
     *
     */
    /*
    @Test(groups = {"2torAdministrativeBlock.supportAndLoginMessageDisableByPesAdmin"})
    public void testPesAdmindisableStudentSupportLoginMessage() throws Exception {
        a.navigateTo2torSiteAdministrator();
        a.navigateToLoginMessage();
        a.disableStudentSupportLoginMessageByPesAdmin();
    } */
    
    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testPesAdminLogOut() throws Exception {
        a.logOut();
    }

    /**
     * Remove members from Working Group
     * 
     * @param FllNm
     */
    private boolean findUser(int i, String FllNm) {
        System.out.println("full name: " + FllNm);
        int x = 1;
        boolean status;
        
        loop:
        do {
            try {
                ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td/div[2]", FllNm, 5);
                status = true;
                break loop;
            } catch (TimeoutException e) {
                System.out.println("Text not present at x: " + x);
                x++;
                status = false;
            }
        } while (x < i);
        return status;
    }
}
