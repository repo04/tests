/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author somesh.bansal
 */
public class User extends Page {

    DateFormat dateFormat;
    Date now;
    IsPresent ip = new IsPresent();
    private String userName;

    public User(WebDriver driver, AccountValues av) {
        super(driver, av);
    }

    public void createUser(String user) {

        dateFormat = new SimpleDateFormat("ddMM-HHmm");
        now = new Date();

        switch (user) {

            case "student":
                this.userName = "smktststdt" + dateFormat.format(now);
                break;

            case "teacher":
                this.userName = "smktsttchr" + dateFormat.format(now);
                break;
        }

        driver.findElement(By.xpath(av.getTokenValue("lftPnlSiteAdminXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("lftPnlUsersXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("lftPnlAccntsXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("lftPnlAddNwUsrXPATH"))).click();

        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldUsrnmXPATH"));

        driver.findElement(By.xpath(av.getTokenValue("fieldUsrnmXPATH"))).sendKeys(userName);
        driver.findElement(By.xpath(av.getTokenValue("fieldPswdXPATH"))).sendKeys("Tech@123");
        driver.findElement(By.xpath(av.getTokenValue("fieldFirstNmXPATH"))).sendKeys(userName + "frstNm");
        driver.findElement(By.xpath(av.getTokenValue("fieldScndNmXPATH"))).sendKeys(userName + "sndNm");
        driver.findElement(By.xpath(av.getTokenValue("fieldEmailXPATH"))).sendKeys(userName + "@gmail.com");
        driver.findElement(By.xpath(av.getTokenValue("fieldCityXPATH"))).sendKeys("New York");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCntryXPATH")))).selectByValue("US");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctTimeZnXPATH")))).selectByValue("America/New_York");
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();

        ip.isElementPresentByXPATH(driver, av.getTokenValue("slctFindUsrXPATH"));

        //Need to check and perform functionality if Element is present or not 
        //Limitation - Webdriver throws 'NoSuchElementException' Exception incase element is not found
        Boolean wait = null;
        try {
            driver.findElement(By.xpath(av.getTokenValue("btnRmvUsrFilter")));
            wait = false;
        } catch (NoSuchElementException e) {
            wait = true;
        }

        if (!wait) {
            driver.findElement(By.xpath(av.getTokenValue("btnRmvUsrFilter"))).click();
            ip.isElementPresentByXPATH(driver, av.getTokenValue("btnFindUsr"));
        }

        new Select(driver.findElement(By.xpath(av.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
        driver.findElement(By.xpath(av.getTokenValue("fieldFindUsrXPATH"))).sendKeys(userName);
        driver.findElement(By.xpath(av.getTokenValue("btnFindUsr"))).click();

        ip.isElementPresentStartsWithTextByXPATH(driver, userName);

    }

    public String getUsrName() {
        return this.userName;
    }
}
