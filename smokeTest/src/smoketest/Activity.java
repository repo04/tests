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
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();
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
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctQuizAttmpts")))).selectByVisibleText("Unlimited");
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();
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
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();
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
    }

    /**
     * Add True/False question to Quiz Activity
     *
     * @param quizActvtyName
     */
    public void addQuizQuestion(String quizActvtyName) {
        driver.findElement(By.xpath("//*[starts-with(text(),'" + quizActvtyName + "')]")).click();
        driver.findElement(By.xpath(av.getTokenValue("btnEditQzXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtEditQzScrnXPATH"), "Editing quiz: AutoQuiz");
        driver.findElement(By.xpath(av.getTokenValue("fieldQzGradeXPATH"))).clear();
        driver.findElement(By.xpath(av.getTokenValue("fieldQzGradeXPATH"))).sendKeys("1");
        driver.findElement(By.xpath(av.getTokenValue("btnSaveGradeXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnAddQzQstnXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("btnAddQzQstnXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtQzQstnTypeScrnXPATH"), "Choose a question type to add");
        driver.findElement(By.xpath(av.getTokenValue("radioBtnQstnTypeXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtRdBtnQstnTypeXPATH"), "A simple form of multiple choice "
                + "question with just the two choices 'True' and 'False'.");
        driver.findElement(By.xpath(av.getTokenValue("btnNxtScrnXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtAddQzQstnScrnXPATH"), "Adding a True/False question");
        driver.findElement(By.xpath(av.getTokenValue("fieldQzQstnNmXPATH"))).sendKeys("Capital");
        driver.findElement(By.xpath(av.getTokenValue("fieldQzQstnTxtXPATH"))).sendKeys("NewYork is capital of USA?");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrrctAnsXPATH")))).selectByVisibleText("False");
        driver.findElement(By.xpath(av.getTokenValue("btnSvQzQstnXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtVrfyQzQstnXPATH"), "NewYork is capital of USA?");
    }

    /**
     * User attempt to 'True/False' question in Quiz Assignment
     *
     * @param quizActvtyName
     */
    public void submitQuiz(String quizActvtyName) {
        driver.findElement(By.xpath("//*[starts-with(text(),'" + quizActvtyName + "')]")).click();
        int i = 1;

        Boolean attempt;
        try {
            ip.isTextPresentByXPATH(driver, av.getTokenValue("txtVrfyPrvAtmptXPATH"), "Summary of your previous attempts");
            attempt = true;
        } catch (TimeoutException e) {
            attempt = false;
        }

        if (attempt) {
            click:
            for (; i < 101; i++) {
                try {
                    new WebDriverWait(driver, 15).until(ExpectedConditions.
                            presenceOfElementLocated(By.xpath("//tr[" + i + "]/td[5]/a")));
                    System.out.println("i value: " + i);
                } catch (TimeoutException e) {
                    System.out.println("catch i value: " + i);
                    break click;
                }
            }
        }

        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnEditQzXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("btnEditQzXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtSbmtQzQstnXPATH"), "NewYork is capital of USA?");

        WebElement yesRadioButton = driver.findElement(By.xpath(av.getTokenValue("radioBtnFlsOptnXPATH")));
        yesRadioButton.click();

        driver.findElement(By.xpath(av.getTokenValue("btnNxtSbmtQzQstnXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtVrfyAnsSvdXPATH"), "Answer saved");
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtVrfySmryAttmptXPATH"), "Summary of attempt");
        driver.findElement(By.xpath(av.getTokenValue("btnSbmtQzAnsXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtVrfyAnsCntChngXPATH"), "Once you submit, you will no longer be able to "
                + "change your answers for this attempt.");
        driver.findElement(By.xpath(av.getTokenValue("btnFnllySvQzAnsXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtVrfyGradeScrXPATH"), "1.00 out of a maximum of 1.00 (100%)");
        ip.isElementPresentByXPATH(driver, av.getTokenValue("imgQzAnsXPATH"));
        driver.findElement(By.xpath("//*[contains(text(),'Finish review')]")).click();
        ip.isTextPresentByXPATH(driver, "//tr[" + i + "]/td[3]", "(100%)");
        new WebDriverWait(driver, 60).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//tr[" + i + "]/td[5]/a")));
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
