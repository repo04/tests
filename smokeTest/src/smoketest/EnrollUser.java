/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import com.thoughtworks.selenium.SeleneseTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author somesh.bansal
 */
public class EnrollUser extends Page {

    IsPresent ip = new IsPresent();

    public EnrollUser(WebDriver driver, AccountValues av) {
        super(driver, av);
    }

    public void toRole_Crs(String user, String grpCrs) {

        String userRole = null;

        driver.findElement(By.xpath(av.getTokenValue("lftPnlUsrLnkXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("lftPnlEnrlUsrLnkXPATH"))).click();

        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnEnrlUsrs"));
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
                userRole = "4";
                new Select(driver.findElement(By.xpath(av.getTokenValue("slctRoleXPATH")))).selectByValue(userRole);
                break;

            case "student":
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

    private void verifyUsrRole_Course(String user, String userRole, String grpCrs) {

        driver.findElement(By.xpath(av.getTokenValue("lftPnlSiteAdminLnkVrfyUsrRoleCrsXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("lftPnlUsrsLnkVrfyUsrRoleCrsXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("lftPnlAcntsLnkUsrRoleCrsXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("lftPnlBrwsUsrVrfyUsrRoleCrsXPATH"))).click();

        Boolean wait = null;
        try {
            driver.findElement(By.xpath(av.getTokenValue("btnRmvUsrFilter")));
            wait = false;
        } catch (NoSuchElementException e) {
            wait = true;
        }

        if (!wait) {
            driver.findElement(By.xpath(av.getTokenValue("btnRmvUsrFilter"))).click();
            ip.isElementPresentByXPATH(driver, av.getTokenValue("slctFindUsrXPATH"));
        }

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
