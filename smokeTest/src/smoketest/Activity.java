/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author somesh.bansal
 */
public class Activity extends Page {

    Date now = new Date();
    IsPresent ip = new IsPresent();

    public Activity(WebDriver driver, AccountValues av) {
        super(driver, av);
    }

    public void crtForumActvty() {

        String forumName = "SmkTstForum " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String forumIntro = "SmkTstForumIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                
        ip.isElementPresentContainsTextByXPATH(driver, av.getTokenValue("lnkLftPnlTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + av.getTokenValue("lnkLftPnlTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Forum");

        createActivity(forumName, forumIntro);
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngActvtyTextXPATH"), forumIntro);
    }

    public void crtQuizActvty() {
        String quizName = "SmkTstQuiz " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String quizIntro = "SmkTstQuizIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);

        ip.isElementPresentContainsTextByXPATH(driver, av.getTokenValue("lnkLftPnlTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + av.getTokenValue("lnkLftPnlTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Quiz");

        createActivity(quizName, quizIntro);
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngActvtyTextXPATH"), quizIntro);
    }

    public void crtAllInOneAsgnmntActvty() {
        String allInOneAsgnmntName = "SmkTstAllInOneAsgnmnt " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String allInOneAsgnmntIntro = "SmkTstAllInOneAsgnmntIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);

        ip.isElementPresentContainsTextByXPATH(driver, av.getTokenValue("lnkLftPnlTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + av.getTokenValue("lnkLftPnlTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("All in one assignment");

        createActivity(allInOneAsgnmntName, allInOneAsgnmntIntro);
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngActvtyTextXPATH"), allInOneAsgnmntIntro);
    }

    public void createPageResource() {
        String pageName = "SmkTstPage " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String pageContent = "SmkTstPageContent " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);

        ip.isElementPresentContainsTextByXPATH(driver, av.getTokenValue("lnkLftPnlTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + av.getTokenValue("lnkLftPnlTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("slctAddRescXPATH"));
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctAddRescXPATH")))).selectByVisibleText("Page");
        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldActvyNameXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("fieldActvyNameXPATH"))).sendKeys(pageName);
        driver.findElement(By.xpath(av.getTokenValue("fieldRescContentXPATH"))).sendKeys(pageContent);
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngRescTextXPATH"), pageContent);
    }

    private void createActivity(String forumName, String forumIntro) {

        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldActvyNameXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("fieldActvyNameXPATH"))).sendKeys(forumName);
        driver.findElement(By.xpath(av.getTokenValue("fieldActvyIntroXPATH"))).sendKeys(forumIntro);
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();
    }
}
