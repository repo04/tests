/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

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

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlSiteAdminXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlSiteAdminXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlUsersXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlUsersXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlAccntsXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlAccntsXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlAddNwUsrXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlAddNwUsrXPATH"))).click();

        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldUsrnmXPATH"));

        driver.findElement(By.xpath(av.getTokenValue("fieldUsrnmXPATH"))).sendKeys(userName);
        driver.findElement(By.xpath(av.getTokenValue("fieldPswdXPATH"))).sendKeys("Moodle1!");
        driver.findElement(By.xpath(av.getTokenValue("fieldFirstNmXPATH"))).sendKeys(userName + "fstNm");
        driver.findElement(By.xpath(av.getTokenValue("fieldScndNmXPATH"))).sendKeys(userName + "sndNm");
        if (user.equalsIgnoreCase("teacher")) {
            driver.findElement(By.xpath(av.getTokenValue("fieldEmailXPATH"))).sendKeys("2torteacher+" + userName.substring(7) + "@gmail.com");
        } else {
            driver.findElement(By.xpath(av.getTokenValue("fieldEmailXPATH"))).sendKeys("2torstudent+" + userName.substring(7) + "@gmail.com");
        }
        driver.findElement(By.xpath(av.getTokenValue("fieldCityXPATH"))).sendKeys("New York");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCntryXPATH")))).selectByValue("US");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctTimeZnXPATH")))).selectByValue("America/New_York");
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();

        ip.isElementPresentByXPATH(driver, av.getTokenValue("slctFindUsrXPATH"));

        Utility.btnRmUsrFilter(driver, av.getTokenValue("btnRmvUsrFilter"));

        new Select(driver.findElement(By.xpath(av.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
        driver.findElement(By.xpath(av.getTokenValue("fieldFindUsrXPATH"))).sendKeys(userName);
        driver.findElement(By.xpath(av.getTokenValue("btnFindUsr"))).click();

        ip.isElementPresentStartsWithTextByXPATH(driver, userName);
    }

    /**
     * Delete Users
     *
     * @param user
     */
    public void deleteUsers(String... users) {

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlSiteAdminXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlSiteAdminXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlUsersXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlUsersXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlAccntsXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlAccntsXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlBrwsNwUsrXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlBrwsNwUsrXPATH"))).click();

        for (String user : users) {
            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("fieldFindUsrXPATH"))));

            Utility.btnRmUsrFilter(driver, av.getTokenValue("btnRmvUsrFilter"));
            new Select(driver.findElement(By.xpath(av.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
            driver.findElement(By.xpath(av.getTokenValue("fieldFindUsrXPATH"))).sendKeys(user);
            driver.findElement(By.xpath(av.getTokenValue("btnFindUsr"))).click();
            ip.isElementPresentStartsWithTextByXPATH(driver, user);
            driver.findElement(By.xpath(av.getTokenValue("lnkDltUsrXPATH"))).click();
            String userFullNm = user + "fstNm " + user + "sndNm";
            ip.isTextPresentByXPATH(driver, av.getTokenValue("txtVrfyCmpltlyDltUsrXPATH"), "Are you absolutely sure you want to completely delete '" + userFullNm + "' ?");
            ip.isElementPresentByXPATH(driver, av.getTokenValue("btnCntnDltUsrXPATH"));
            driver.findElement(By.xpath(av.getTokenValue("btnCntnDltUsrXPATH"))).click();
            new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + userFullNm + "')]")));
        }
    }

    /**
     * @return UsrName
     */
    public String getUsrName() {
        return this.userName;
    }
}
