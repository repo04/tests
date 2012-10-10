/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import com.thoughtworks.selenium.SeleneseTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

public class EnrollUser extends BaseClass {

    /**
     * Enroll/Assign user to group course
     *
     * @param user
     * @param grpCrs
     */
    public void toRole_Crs(String user, String grpCrs) {

        String userRole = null;

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlUsrLnkXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlUsrLnkXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlEnrlUsrLnkXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlEnrlUsrLnkXPATH"))).click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnEnrlUsrs"))));
        driver.findElement(By.xpath(av.getTokenValue("btnEnrlUsrs"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("lblEnrlmntOptnsXPATH"), "Enrolment options");
        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldSrchUsrXPATH"));

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("chckBxUsrXPATH"))));

        driver.findElement(By.xpath(av.getTokenValue("fieldSrchUsrXPATH"))).sendKeys(user);
        driver.findElement(By.xpath(av.getTokenValue("fieldSrchUsrXPATH"))).sendKeys(Keys.RETURN);
        ip.isTextPresentByXPATH(driver, av.getTokenValue("lblUsrFndXPATH"), "1 user found");

        // 4 - NonEditing Teacher
        // 5 - Student
        switch (user.substring(0, 7)) {
            case "teacher":
            case "autotea":
                userRole = "4";
                new Select(driver.findElement(By.xpath(av.getTokenValue("slctRoleXPATH")))).selectByValue(userRole);
                break;

            case "student":
            case "autostu":
                userRole = "5";
                new Select(driver.findElement(By.xpath(av.getTokenValue("slctRoleXPATH")))).selectByValue(userRole);
                break;

            default:
                SeleneseTestBase.fail("'tchr'/'stdt' not found in userName: " + user);
        }

        Select select = new Select(driver.findElement(By.xpath(av.getTokenValue("slctGrpXPATH"))));
        select.deselectByVisibleText("None");
        select.selectByVisibleText(grpCrs);

        driver.findElement(By.xpath(av.getTokenValue("chckBxUsrXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("btnEnrlSlctdUsrXPATH"))).click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(av.getTokenValue("chckBxUsrXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("btnFnshEnrlngUsrXPATH"))).click();

        ip.isTextPresentByXPATH(driver, av.getTokenValue("lblEnrollUsersXPATH"), "Enrolled users");

        //Verify user's role & course
        verifyUsrRole_Course(user, userRole, grpCrs);
    }

    /**
     * Unassign users from group course
     *
     * @param stdtUsrName
     * @param tchrUsrName
     */
    public void frmCourse(String stdtUsrName, String tchrUsrName) {

        String stdtFllNm = stdtUsrName + "fstNm " + stdtUsrName + "sndNm";
        String tchrFllNm = tchrUsrName + "fstNm " + tchrUsrName + "sndNm";

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlUsrLnkXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlUsrLnkXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlEnrlUsrLnkXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlEnrlUsrLnkXPATH"))).click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnEnrlUsrs"))));
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("slctEnrlmntMthd"))));
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctEnrlmntMthd")))).selectByVisibleText("Manual enrolments");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("linkScndNameXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("linkScndNameXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(av.getTokenValue("chckBxUnrolFrstUsrXpath"))));
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(av.getTokenValue("chckBxUnrolScndUsrXpath"))));
        driver.findElement(By.xpath(av.getTokenValue("chckBxUnrolFrstUsrXpath"))).click();
        driver.findElement(By.xpath(av.getTokenValue("chckBxUnrolScndUsrXpath"))).click();
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctUnrolUsers")))).selectByValue("deleteselectedusers");
        driver.findElement(By.xpath(av.getTokenValue("goUnrolUsers"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("lblEnrollUsersXPATH"), "Delete selected user enrolments");
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtStdtUnrolXPATH"), stdtFllNm);
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtTchrUnrolXPATH"), tchrFllNm);
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(av.getTokenValue("chckBxUnrolFrstUsrXpath"))));
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(av.getTokenValue("chckBxUnrolScndUsrXpath"))));
    }

    /**
     * Verify user's role & course
     *
     * @param user
     * @param userRole
     * @param grpCrs
     */
    private void verifyUsrRole_Course(String user, String userRole, String grpCrs) {

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlSiteAdminLnkVrfyUsrRoleCrsXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlSiteAdminLnkVrfyUsrRoleCrsXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlUsrsLnkVrfyUsrRoleCrsXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlUsrsLnkVrfyUsrRoleCrsXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlAcntsLnkUsrRoleCrsXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlAcntsLnkUsrRoleCrsXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlBrwsUsrVrfyUsrRoleCrsXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlBrwsUsrVrfyUsrRoleCrsXPATH"))).click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("fieldFindUsrXPATH"))));

        Utility.btnRmUsrFilter(driver, av.getTokenValue("btnRmvUsrFilter"));

        new Select(driver.findElement(By.xpath(av.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
        driver.findElement(By.xpath(av.getTokenValue("fieldFindUsrXPATH"))).sendKeys(user);
        driver.findElement(By.xpath(av.getTokenValue("btnFindUsr"))).click();

        ip.isElementPresentStartsWithTextByXPATH(driver, user);

        driver.findElement(By.xpath("//*[starts-with(text(),'" + user + "')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("lnkCrsPrsntXPATH"));

        driver.findElement(By.xpath(av.getTokenValue("lnkCrsPrsntXPATH"))).click();

        switch (userRole) {

            case "4":
                ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyUsrRoleXPATH"), "Non-editing teacher");
                break;

            case "5":
                ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyUsrRoleXPATH"), "Student");
                break;

            default:
                SeleneseTestBase.fail("'Non-editing teacher(4)'/'Student(5)' not found in userRole: " + userRole);
        }

        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyUsrGrpCrsXPATH"), grpCrs);
    }
}
