/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.smoketest;

import com.lms.tests.runThrghTestNG.BaseClass;
import com.thoughtworks.selenium.SeleneseTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnrollUser extends BaseClass {

    /**
     * Enroll/Assign user to group course
     *
     * @param user
     * @param groupCourse
     */
    public void toRole_Course(String user, String groupCourse) {

        String userRole = null;

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlUsrLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsrLnkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"))).click();

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("btnEnrlUsrs"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("btnEnrlUsrs"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblEnrlmntOptnsXPATH"), "Enrolment options");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldSrchUsrXPATH"));

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("chckBxUsrXPATH"), 60);

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

            case "coordin":
                userRole = "9";
                new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctRoleXPATH")))).selectByValue(userRole);
                break;

            default:
                SeleneseTestBase.fail("'teacher'/'student' not found in userName: " + user);
        }

        Select select = new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctGrpXPATH"))));
        select.deselectByVisibleText("None");
        select.selectByVisibleText(groupCourse);

        driver.findElement(By.xpath(xpv.getTokenValue("chckBxUsrXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("btnEnrlSlctdUsrXPATH"))).click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpv.getTokenValue("chckBxUsrXPATH"))));
        driver.findElement(By.xpath(xpv.getTokenValue("btnFnshEnrlngUsrXPATH"))).click();

        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblEnrollUsersXPATH"), "Enrolled users");

        //Verify user's role & course
        verifyUsrRole_Course(user, userRole, groupCourse);
    }

    /**
     * Unassign users from group course
     *
     * @param studentUserName
     * @param teacherUserName
     */
    public void fromCourse(String studentUserName, String teacherUserName) {
        String studentFullName = studentUserName + " " + studentUserName;
        String teacherFullName = teacherUserName + " " + teacherUserName;

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlUsrLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsrLnkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlEnrlUsrLnkXPATH"))).click();

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("btnEnrlUsrs"), 60);
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("slctEnrlmntMthd"), 60);
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctEnrlmntMthd")))).selectByVisibleText("Manual enrolments");
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("linkScndNameXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("linkScndNameXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpv.getTokenValue("chckBxUnrolFrstUsrXpath"))));

        unenrollUsers(studentFullName);
        unenrollUsers(teacherFullName);
    }

    /**
     * Verify user's role & course
     *
     * @param user
     * @param userRole
     * @param groupCourse
     */
    private void verifyUsrRole_Course(String user, String userRole, String groupCourse) {

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlSiteAdminLnkVrfyUsrRoleCrsXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlSiteAdminLnkVrfyUsrRoleCrsXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlUsrsLnkVrfyUsrRoleCrsXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsrsLnkVrfyUsrRoleCrsXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlAcntsLnkUsrRoleCrsXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlAcntsLnkUsrRoleCrsXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlBrwsUsrVrfyUsrRoleCrsXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlBrwsUsrVrfyUsrRoleCrsXPATH"))).click();

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("fieldFindUsrXPATH"), 60);

        Utility.buttonRemoveUserFilter(driver, xpv.getTokenValue("btnRmvUsrFilter"));

        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldFindUsrXPATH"))).sendKeys(user);
        driver.findElement(By.xpath(xpv.getTokenValue("btnFindUsr"))).click();

        ip.isElementPresentStartsWithTextByXPATH(driver, user);

        driver.findElement(By.xpath("//*[starts-with(text(),'" + user + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("lnkCrsPrsntXPATH"));

        driver.findElement(By.xpath(xpv.getTokenValue("lnkCrsPrsntXPATH"))).click();

        String roleXpath, groupCourseXpath;
        if ("4".equals(userRole) && test.equals("regressionTests")) {
            roleXpath = "//tr[3]/td[2]/a";
            groupCourseXpath = "//tr[4]/td[2]/a";
        } else {
            roleXpath = "//td[2]/a";
            groupCourseXpath = "//tr[3]/td[2]/a";
        }
        switch (userRole) {

            case "4":
                ip.isTextPresentByXPATH(driver, roleXpath, "Non-editing teacher");
                break;

            case "5":
                ip.isTextPresentByXPATH(driver, roleXpath, "Student");
                break;

            case "9":
                ip.isTextPresentByXPATH(driver, roleXpath, "Course Coordinator");
                break;

            default:
                SeleneseTestBase.fail("'Non-editing teacher(4)'/'Student(5)' not found in userRole: " + userRole);
        }

        ip.isTextPresentByXPATH(driver, groupCourseXpath, groupCourse);
    }

    /**
     * Unassign users from group course
     *
     * @param FullName
     */
    private void unenrollUsers(String FullName) {

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
                ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[2]/div[2]", FullName, 5);
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
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtStdtUnrolXPATH"), FullName);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("btnEnrlUsrs"), 60);
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(By.xpath("//tr[" + i + "]/td[2]/div[2]"), FullName));
            Utility.illegalStateException("Cannot unenrol user from course: " + FullName);
        } catch (TimeoutException e) {
        }
    }
}
