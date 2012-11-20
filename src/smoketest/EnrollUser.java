/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import com.thoughtworks.selenium.SeleneseTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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
    public void toRole_Course(String user, String grpCrs) {

        String userRole = null;

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("lftPnlUsrLnkXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsrLnkXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"))).click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("btnEnrlUsrs"))));
        driver.findElement(By.xpath(xpv.getTokenValue("btnEnrlUsrs"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblEnrlmntOptnsXPATH"), "Enrolment options");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldSrchUsrXPATH"));

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("chckBxUsrXPATH"))));

        driver.findElement(By.xpath(xpv.getTokenValue("fieldSrchUsrXPATH"))).sendKeys(user);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldSrchUsrXPATH"))).sendKeys(Keys.RETURN);
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblUsrFndXPATH"), "1 user found");

        // 4 - NonEditing Teacher
        // 5 - Student
        switch (user.substring(0, 7)) {
            case "teacher":
            case "autotea":
                userRole = "4";
                new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctRoleXPATH")))).selectByValue(userRole);
                break;

            case "student":
            case "autostu":
                userRole = "5";
                new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctRoleXPATH")))).selectByValue(userRole);
                break;

            default:
                SeleneseTestBase.fail("'tchr'/'stdt' not found in userName: " + user);
        }

        Select select = new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctGrpXPATH"))));
        select.deselectByVisibleText("None");
        select.selectByVisibleText(grpCrs);

        driver.findElement(By.xpath(xpv.getTokenValue("chckBxUsrXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("btnEnrlSlctdUsrXPATH"))).click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpv.getTokenValue("chckBxUsrXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("btnFnshEnrlngUsrXPATH"))).click();

        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblEnrollUsersXPATH"), "Enrolled users");

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

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("lftPnlUsrLnkXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsrLnkXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"))).click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("btnEnrlUsrs"))));
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("slctEnrlmntMthd"))));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctEnrlmntMthd")))).selectByVisibleText("Manual enrolments");
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("linkScndNameXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("linkScndNameXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpv.getTokenValue("chckBxUnrolFrstUsrXpath"))));

        unenrollUsers(stdtFllNm);
        unenrollUsers(tchrFllNm);        
    }

    /**
     * Verify user's role & course
     *
     * @param user
     * @param userRole
     * @param grpCrs
     */
    private void verifyUsrRole_Course(String user, String userRole, String grpCrs) {

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("lftPnlSiteAdminLnkVrfyUsrRoleCrsXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlSiteAdminLnkVrfyUsrRoleCrsXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("lftPnlUsrsLnkVrfyUsrRoleCrsXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsrsLnkVrfyUsrRoleCrsXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("lftPnlAcntsLnkUsrRoleCrsXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlAcntsLnkUsrRoleCrsXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("lftPnlBrwsUsrVrfyUsrRoleCrsXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlBrwsUsrVrfyUsrRoleCrsXPATH"))).click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("fieldFindUsrXPATH"))));

        Utility.btnRmUsrFilter(driver, xpv.getTokenValue("btnRmvUsrFilter"));

        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldFindUsrXPATH"))).sendKeys(user);
        driver.findElement(By.xpath(xpv.getTokenValue("btnFindUsr"))).click();

        ip.isElementPresentStartsWithTextByXPATH(driver, user);

        driver.findElement(By.xpath("//*[starts-with(text(),'" + user + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("lnkCrsPrsntXPATH"));

        driver.findElement(By.xpath(xpv.getTokenValue("lnkCrsPrsntXPATH"))).click();

        switch (userRole) {

            case "4":
                ip.isTextPresentByXPATH(driver, xpv.getTokenValue("vrfyUsrRoleXPATH"), "Non-editing teacher");
                break;

            case "5":
                ip.isTextPresentByXPATH(driver, xpv.getTokenValue("vrfyUsrRoleXPATH"), "Student");
                break;

            default:
                SeleneseTestBase.fail("'Non-editing teacher(4)'/'Student(5)' not found in userRole: " + userRole);
        }

        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("vrfyUsrGrpCrsXPATH"), grpCrs);
    }

    /**
     * Unassign users from group course
     * 
     * @param FllNm 
     */
    private void unenrollUsers(String FllNm) {

        int i = 1;
        int x = 1;
        while (true) {
            try {
                driver.findElement(By.xpath("//tr[" + i + "]/td[2]/div[2]"));
            } catch (NoSuchElementException e) {
                System.out.println("Total Elements i: " + i);
                break;
            }
            i++;
        }
        
        loop:
        do {
            try {
                ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[2]/div[2]", FllNm, 5);
                driver.findElement(By.xpath("//div[4]/div/form/table/tbody/tr[" + x + "]/td/input")).click();
                break loop;
            } catch (TimeoutException e) {
                System.out.println("Text not present at x: " + x);
                x++;                
            }
        } while (x < i);
        
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctUnrolUsers")))).selectByValue("deleteselectedusers");
        driver.findElement(By.xpath(xpv.getTokenValue("goUnrolUsers"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblEnrollUsersXPATH"), "Delete selected user enrolments");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtStdtUnrolXPATH"), FllNm);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("btnEnrlUsrs"))));
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(By.xpath("//tr[" + i + "]/td[2]/div[2]"), FllNm));
            Utility.illegalStateException("Cannot unenrol user from course: " + FllNm);
        } catch (TimeoutException e) {
        }
    }
}
