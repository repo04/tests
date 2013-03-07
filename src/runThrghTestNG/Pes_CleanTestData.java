/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import smoketest.Actions;
import smoketest.Utility;

/**
 *
 * @author somesh.bansal
 */
public class Pes_CleanTestData extends BaseClass {

    Actions a = new Actions();
    public static boolean tchrStatus;
    public static boolean stdtStatus;

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, PES Admin Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testPESAdminLogin() throws Exception {
        a.login("pesAdmin");
    }

    /**
     * Un-enroll users from Working Group
     *
     * @param grpCrsName
     * @param tchrUsrName
     * @param stdtUsrName
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsUsers", dataProviderClass = Pes_UsrCrtn_AsgnRole_WrkngGrp.class,
          groups = {"users.unAssignRole"})
    public void testPESAdminUnerolUsers(String grpCrsName, String tchrUsrName, String stdtUsrName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.unenrolUsers(stdtUsrName, tchrUsrName);
    }
    
    /**
     * PES Admin verify Student post deletion from Working Group
     * 
     * @param wrkngGrpName
     * @param stdtUrlPostOnWrkngGrp
     * @throws Exception 
     */
    @Test(dataProvider = "WrkngGrpStdtURLPost", dataProviderClass = Stdt_LvSsn_SclGrp_GglDoc.class,
          groups = {"regressionSmoke", "workingGroup.pesAdminDeleteAndVerifyStudentPostFromWorkingGroup"})
    public void testPESAdminDeleteAndVerifyStudentPostFromWorkingGroup(String wrkngGrpName, String stdtUrlPostOnWrkngGrp) throws Exception {
        a.navigateToWorkingGroups();
        a.navigateToGroupWall(wrkngGrpName);
        a.deletePost(stdtUrlPostOnWrkngGrp);
    }
    
    /**
     * Remove members from Working Group
     *
     * @param grpCrsName
     * @param wrkngGrpName
     * @param tchrUsrName
     * @param stdtUsrName
     * @throws Exception
     */
    @Test(dataProvider = "GrpCrsWrkngGrpUsers", dataProviderClass = Pes_UsrCrtn_AsgnRole_WrkngGrp.class,
          groups = {"workingGroup.removeMembers"})
    public void testPESAdminRemoveMembersFromWorkngGroup(String grpCrsName, String wrkngGrpName, String tchrUsrName, String stdtUsrName) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlUsrLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsrLnkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("linkScndNameXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("linkScndNameXPATH"))).click();

        String stdtFllNm;
        String tchrFllNm;

        if (tchrUsrName.substring(0, 7).contains("teacher")) {
            tchrFllNm = tchrUsrName + "fstNm " + tchrUsrName + "sndNm";
        } else {
            tchrFllNm = Utility.getFullName(tchrUsrName);            
        }

        if (stdtUsrName.substring(0, 7).contains("student")) {
            stdtFllNm = stdtUsrName + "fstNm " + stdtUsrName + "sndNm";
        } else {
            stdtFllNm = Utility.getFullName(stdtUsrName);            
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

        tchrStatus = findUser(i, tchrFllNm);
        stdtStatus = findUser(i, stdtFllNm);

        a.navigateToWorkingGroups();
        a.accessWorkingGroup(wrkngGrpName);
        a.rmvMbrsFrmWrkngGrp(tchrUsrName, stdtUsrName);
    }

    /**
     * Delete Working Group
     *
     * @param wrkngGrpName
     * @throws Exception
     */
    @Test(dataProvider = "WrkngGrp", dataProviderClass = Pes_UsrCrtn_AsgnRole_WrkngGrp.class,
          groups = {"regressionSmoke", "fullSmoke", "workingGroup.delete"})
    public void testPESAdminDeleteWorkingGroup(String wrkngGrpName) throws Exception {
        a.navigateToWorkingGroups();
        a.accessWorkingGroup(wrkngGrpName);
        a.deleteWorkingGroup(wrkngGrpName);
    }

    /**
     * Delete Users
     *
     * @throws Exception
     */
    @Test(dataProvider = "Users", dataProviderClass = Pes_UsrCrtn_AsgnRole_WrkngGrp.class,
          groups = {"regressionSmoke", "fullSmoke", "users.delete"})
    public void testPESAdminDeleteUsers(String tchrUsr, String stdtUsr) throws Exception {
        a.navigateToMyContacts();
        a.deleteUsers(tchrUsr, stdtUsr);
    }
    
    /**
     * Delete Announcement
     * 
     * @param grpCrsName
     * @param pesTxtAncmntCrsPost
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsAnnouncement", dataProviderClass = Pes_UsrCrtn_AsgnRole_WrkngGrp.class,
          groups = {"regressionSmoke", "wall.pesDeleteAnnouncement"})
    public void testPESAdminDeleteAnnouncement(String grpCrsName, String pesTxtAncmntCrsPost) throws Exception {
        a.navigateToMyCourse();
        a.selectGroupCourse(grpCrsName);
        a.deletePost(pesTxtAncmntCrsPost);
    }
    
     /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testPESAdminLogOut() throws Exception {
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
