/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

public class Activity extends BaseClass {

    Date now = new Date();
    private String forumName;
    private String quizName;
    private String allInOneAsgnmntName;
    private String pageName;

    /**
     * Create & Verify Forum Activity
     */
    public void crtForumActvty() {
        this.forumName = "SmkTstForum " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String forumIntro = "SmkTstForumIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        ip.isElementPresentContainsTextByXPATH(driver, av.getTokenValue("lnkLftPnlTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + av.getTokenValue("lnkLftPnlTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Forum");
        createActivity(forumName, forumIntro);

        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngActvtyTextXPATH"), forumIntro);
    }

    /**
     * Create & Verify Quiz Activity
     */
    public void crtQuizActvty() {
        this.quizName = "SmkTstQuiz " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String quizIntro = "SmkTstQuizIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        ip.isElementPresentContainsTextByXPATH(driver, av.getTokenValue("lnkLftPnlTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + av.getTokenValue("lnkLftPnlTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Quiz");
        createActivity(quizName, quizIntro);

        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngActvtyTextXPATH"), quizIntro);
    }

    /**
     * Create & Verify AllInOneAsgnmnt Activity
     */
    public void crtAllInOneAsgnmntActvty() {
        this.allInOneAsgnmntName = "SmkTstAllInOneAsgnmnt " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String allInOneAsgnmntIntro = "SmkTstAllInOneAsgnmntIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        ip.isElementPresentContainsTextByXPATH(driver, av.getTokenValue("lnkLftPnlTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + av.getTokenValue("lnkLftPnlTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("All in one assignment");
        createActivity(allInOneAsgnmntName, allInOneAsgnmntIntro);

        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngActvtyTextXPATH"), allInOneAsgnmntIntro);
    }

    /**
     * Create & Verify Page Resource
     */
    public void createPageResource() {
        this.pageName = "SmkTstPage " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
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

    /**
     * Create Activity
     *
     * @param forumName
     * @param forumIntro
     */
    private void createActivity(String forumName, String forumIntro) {
        WebElement actvtyNm = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("fieldActvyNameXPATH"))));
        WebElement actvtyIntroNm = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("fieldActvyIntroXPATH"))));

        //This is to verify actvtyName passes correct value 
        value:
        while (true) {
            actvtyNm.clear();
            actvtyIntroNm.clear();
            actvtyNm.sendKeys(forumName);
            actvtyIntroNm.sendKeys(forumIntro);
            try {
                new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(av.getTokenValue("fieldActvyNameXPATH")), forumName));
                new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(av.getTokenValue("fieldActvyIntroXPATH")), forumIntro));
                break value;
            } catch (TimeoutException e) {
            }
        }
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();
    }

    /**
     * @return ForumName
     */
    public String getFrmActvyName() {
        return this.forumName;
    }

    /**
     * @return QuizName
     */
    public String getQzActvyName() {
        return this.quizName;
    }

    /**
     * @return AllInOneAsgnmntName
     */
    public String getAllInOneAsgnmntActvyName() {
        return this.allInOneAsgnmntName;
    }

    /**
     * @return PageName
     */
    public String getPageActvyName() {
        return this.pageName;
    }
}
