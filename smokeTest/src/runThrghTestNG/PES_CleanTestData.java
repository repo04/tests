/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 *
 * @author somesh.bansal
 */
public class PES_CleanTestData extends BaseClass {

    Actions a = new Actions();
    public static boolean status;

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, PES Admin Logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testPESAdminLgn() throws Exception {
        a.login("pesAdmin");
    }

    /**
     * 
     * @param grpCrsName
     * @param tchrUsrName
     * @param stdtUsrName
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsUsers", dataProviderClass=UsrCrtn_AsgnRole_WrkngGrp.class)
    public void testUnerolUsers(String grpCrsName, String tchrUsrName, String stdtUsrName) throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.unenrolUsers(stdtUsrName, tchrUsrName);
    }

    /**
     * 
     * @param grpCrsName
     * @param wrkngGrpName
     * @param tchrUsrName
     * @param stdtUsrName
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsWrkngGrpUsers", dataProviderClass=UsrCrtn_AsgnRole_WrkngGrp.class)
    public void testRemoveMbrsFrmWrkngGrp(String grpCrsName, String wrkngGrpName, String tchrUsrName, String stdtUsrName) throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlUsrLnkXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlUsrLnkXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlEnrlUsrLnkXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlEnrlUsrLnkXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("linkScndNameXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("linkScndNameXPATH"))).click();

        String stdtFllNm = stdtUsrName + "fstNm " + stdtUsrName + "sndNm";
        String tchrFllNm = tchrUsrName + "fstNm " + tchrUsrName + "sndNm";
        try {
            ip.isTextPresentByXPATH(driver, av.getTokenValue("txtStdtEnrolXPATH"), stdtFllNm, 30);
            ip.isTextPresentByXPATH(driver, av.getTokenValue("txtTchrEnrolXPATH"), tchrFllNm, 30);
            status = true;
        } catch (TimeoutException e) {
            status = false;
        }

        a.navigateToWorkingGroups();
        a.accessWrknGrp(wrkngGrpName);
        a.rmvMbrsFrmWrkngGrp(tchrUsrName, stdtUsrName);
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass
    public void testPESAdminLogOut() throws Exception {
        a.logOut();
    }
}
