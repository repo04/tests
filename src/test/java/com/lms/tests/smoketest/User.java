/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lms.tests.runThrghTestNG.BaseClass;

public class User extends BaseClass {

    DateFormat dateFormat;
    Date now;
    private String userName;

    /**
     * PesAdmin creates user
     *
     * @param user
     */
    public void createUser(String user) {

        dateFormat = new SimpleDateFormat("ddMM-HHmm");
        now = new Date();

        switch (user) {

            case "student":
            case "autostu":
                this.userName = "student" + dateFormat.format(now);
                break;

            default:
                this.userName = "teacher" + dateFormat.format(now);
        }

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlSiteAdminXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlSiteAdminXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlUsersXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsersXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlAccntsXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlAccntsXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlAddNwUsrXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlAddNwUsrXPATH"))).click();

        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldUsrnmXPATH"));

        driver.findElement(By.xpath(xpv.getTokenValue("fieldUsrnmXPATH"))).sendKeys(userName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldPswdXPATH"))).sendKeys(ldv.getTokenValue("password"));
        driver.findElement(By.xpath(xpv.getTokenValue("fieldFirstNmXPATH"))).sendKeys(userName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldScndNmXPATH"))).sendKeys(userName);
        if (user.equalsIgnoreCase("teacher")) {
            driver.findElement(By.xpath(xpv.getTokenValue("fieldEmailXPATH"))).sendKeys("seleniumtest+" + "teacher" + dateFormat.format(now) + "@2u.com");
        } else {
            driver.findElement(By.xpath(xpv.getTokenValue("fieldEmailXPATH"))).sendKeys("seleniumtest+" + "student" + dateFormat.format(now) + "@2u.com");
        }
        driver.findElement(By.xpath(xpv.getTokenValue("fieldCityXPATH"))).sendKeys("New York");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCntryXPATH")))).selectByValue("US");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctTimeZnXPATH")))).selectByValue("America/New_York");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();

        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctFindUsrXPATH"));

        Utility.buttonRemoveUserFilter(driver, xpv.getTokenValue("btnRmvUsrFilter"));

        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldFindUsrXPATH"))).sendKeys(userName);
        driver.findElement(By.xpath(xpv.getTokenValue("btnFindUsr"))).click();

        ip.isElementPresentStartsWithTextByXPATH(driver, userName);
    }

    /**
     * Delete Users
     *
     * @param user
     */
    public void deleteUsers(String... users) {

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlSiteAdminXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlSiteAdminXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlUsersXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsersXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlAccntsXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlAccntsXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlBrwsNwUsrXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlBrwsNwUsrXPATH"))).click();

        for (String user : users) {
            ip.isElementClickableByXpath(driver, xpv.getTokenValue("fieldFindUsrXPATH"), 60);

            Utility.buttonRemoveUserFilter(driver, xpv.getTokenValue("btnRmvUsrFilter"));
            new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
            driver.findElement(By.xpath(xpv.getTokenValue("fieldFindUsrXPATH"))).sendKeys(user);
            driver.findElement(By.xpath(xpv.getTokenValue("btnFindUsr"))).click();
            ip.isElementPresentStartsWithTextByXPATH(driver, user);
            driver.findElement(By.xpath(xpv.getTokenValue("lnkDltUsrXPATH"))).click();
            String userFullNm = user + " " + user;
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyCmpltlyDltUsrXPATH"), "Are you absolutely sure you want to completely delete '" + userFullNm + "' ?");
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnCntnDltUsrXPATH"));
            driver.findElement(By.xpath(xpv.getTokenValue("btnCntnDltUsrXPATH"))).click();
            new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + userFullNm + "')]")));
        }
    }

    /**
     * @return UsrName
     */
    public String getUserName() {
        return this.userName;
    }
}
