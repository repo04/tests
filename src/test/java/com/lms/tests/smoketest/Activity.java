/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.smoketest;

import com.lms.tests.runThrghTestNG.BaseClass;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class Activity extends BaseClass {

    Date now = new Date();
    private String questionTitle, question, ans;
    private String name;
    private String intro;
    private String unitEndDate;
    private String unitStartDate;
    private String dateAndTime;
    private String assignmentText;
    StackTraceElement[] stackTraceElements;
    Actions a = new Actions();

    public void createLessonActivity() {
        String currentDateTime = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        System.out.println("######&(*&#(@&*$&*(@#&$*(&@*#$&(*&$*(&*(#&*&$#*@&$*(@#&$*(@#&*(@#&(*^%&^%&^@&*^%&*");
    }

    /*
     * This can be used to grab the current method.  We can use this if we really need to include the activity type
     * into the string when creating the activities below.  Using this would be expensive.
     */
    public String getExecutingMethod() {
        String temp = Thread.currentThread().getStackTrace()[1].getMethodName();
        return temp;
    }

    /**
     * Create & Verify Forum Activity
     */
    public void createForumActivity() {
        this.dateAndTime = a.currentDateTime();
        this.name = test + " Forum " + this.dateAndTime;
        this.intro = test + " intro " + this.dateAndTime;
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Forum");
        createActivity(this.name, this.intro);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), this.intro);
    }

    /**
     * Create & Verify Quiz Activity
     */
    public void createQuizActivity() {
        this.dateAndTime = a.currentDateTime();
        if (!test.equalsIgnoreCase("CriticalDataTests")) {
            this.name = test + " Quiz " + this.dateAndTime;
            this.intro = test + " intro " + this.dateAndTime;
        } else {
            this.name = "AutoQuiz";
            this.intro = "AutoQuizIntro";
        }
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Quiz");
        createActivity(this.name, this.intro);
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctQuizAttmpts")))).selectByVisibleText("Unlimited");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), this.intro);
    }

    /**
     *
     */
    public void createPasswordQuizActivity() {
        this.dateAndTime = a.currentDateTime();
        this.name = test + " Password " + this.dateAndTime;
        this.intro = test + " intro " + this.dateAndTime;
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Quiz");
        createActivity(this.name, this.intro);
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctQuizAttmpts")))).selectByVisibleText("Unlimited");
        driver.findElement(By.xpath("//fieldset[6]/div[2]/div/div[2]/input")).sendKeys("Password1");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), this.intro);
    }

    /**
     * Create & Verify AllInOneAsgnmnt Activity
     */
    public void createAllInOneAssignmentActivity() {
        this.dateAndTime = a.currentDateTime();
        this.name = test + " All in One " + this.dateAndTime;
        this.intro = test + " intro " + this.dateAndTime;
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("All in one assignment");
        createActivity(this.name, this.intro);
        driver.findElement(By.xpath("//div[3]/fieldset/span/input")).click();
        driver.findElement(By.xpath("//div[4]/fieldset/span/input")).click();
        ip.isElementClickableByXpath(driver, "//div[4]/fieldset/select[3]", 60);
        new Select(driver.findElement(By.xpath("//div[4]/fieldset/select[3]"))).selectByValue("2014");
        String lateSubmission = new Select(driver.findElement(By.xpath("//select[@id='id_preventlate']"))).getFirstSelectedOption().getText();
        if (!lateSubmission.equalsIgnoreCase("No")) {
            Utility.illegalStateException("All in one assignment's prevent late submission value differs, "
                    + "expected: 'No' but actual: '" + lateSubmission + "'");
        }
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, "//div[3]/div/div[4]/div/div/div/div/div", this.intro);
    }

    /**
     * Create Glossary activity
     */
    public void createGlossaryActivity() {
        this.dateAndTime = a.currentDateTime();
        this.name = test + " Glossary " + this.dateAndTime;
        this.intro = test + " intro " + this.dateAndTime;
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Glossary");
        createActivity(this.name, this.intro);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), this.intro);
    }

    /**
     * Create & Verify Page Resource
     */
    public void createPageResource() {
        this.dateAndTime = a.currentDateTime();
        this.name = test + " Page " + this.dateAndTime;

        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddRescXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddRescXPATH")))).selectByVisibleText("Page");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldActvyNameXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("fieldActvyNameXPATH"))).sendKeys(this.name);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldRescContentXPATH"))).sendKeys(this.name);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngRescTextXPATH"), this.name);
    }

    /**
     * Create - Syllabus Activity
     */
    public void createSyllabusActivity() {
        this.dateAndTime = a.currentDateTime();
        this.intro = test + " Syllabus intro " + this.dateAndTime;

        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddSyllbsActvyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddSyllbsActvyXPATH")))).selectByVisibleText("Page");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldActvyNameXPATH"));

        driver.findElement(By.xpath(xpv.getTokenValue("fieldActvyNameXPATH"))).sendKeys("Syllabus");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldRescContentXPATH"))).sendKeys(this.intro);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngRescTextXPATH"), this.intro);
        ip.isTextPresentByXPATH(driver, "//li[3]/a", "Syllabus");
        driver.findElement(By.linkText("Syllabus")).click();
        ip.isTextPresentByCSS(driver, "li.listentry > a", "Syllabus");
    }

    /**
     * Create Glossary entry
     *
     * @param glossaryName
     */
    public void createGlossaryEntry(String glossaryName) {
        this.dateAndTime = a.currentDateTime();
        String conceptEntry;

        ip.isElementClickableByXpath(driver, "//div/input[2]", 60);
        driver.findElement(By.xpath("//div/input[2]")).click();
        ip.isTextPresentByXPATH(driver, "//h2", glossaryName);

        this.name = test + "Glossary Entry " + this.dateAndTime;
        conceptEntry = test + " Glossary Concept " + this.dateAndTime;

        driver.findElement(By.xpath("//input[@id='id_concept']")).sendKeys(this.name);
        ip.isElementClickableByXpath(driver, "//*[@id='id_definition_editor_toolbargroup']/span", 60);
        Utility.typeInContentEditableIframe(driver, 1, conceptEntry);
        driver.findElement(By.xpath("//fieldset/input")).click();
        ip.isTextPresentByXPATH(driver, "//h3/span", this.name);
        driver.findElement(By.linkText("Browse by category")).click();
        ip.isTextPresentByXPATH(driver, "//td[2]/b", "All categories");
        ip.isTextPresentByXPATH(driver, "//div[4]/div[3]/div", "No entries found in this section");
        new Select(driver.findElement(By.xpath("//select"))).selectByIndex(1);
        ip.isTextPresentByXPATH(driver, "//td[2]/b", "Entries without category");

        if (LoginPage.getUser().contains("teacher")) {
            ip.isTextPresentByXPATH(driver, "//h3/span", this.name);
        } else {
            ip.isTextPresentByXPATH(driver, "//table[3]/tbody/tr/td/div/h3/span", this.name);
        }

        driver.findElement(By.linkText("Browse by date")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("By creation date")));
        driver.findElement(By.cssSelector("a[title=\"By creation date ascending\"]")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("By creation date")));
        driver.findElement(By.cssSelector("a[title=\"By creation date change to descending\"]")).click();
        ip.isTextPresentByXPATH(driver, "//h3/span", this.name);
        driver.findElement(By.linkText("Browse by Author")).click();
        ip.isTextPresentByXPATH(driver, "//h2", Utility.getFullName(LoginPage.getUser()));
        ip.isTextPresentByXPATH(driver, "//h3/span", this.name);
        driver.findElement(By.linkText("Browse by alphabet")).click();

        if (LoginPage.getUser().contains("teacher")) {
            ip.isTextPresentByXPATH(driver, "//h3/span", this.name);
        } else {
            ip.isTextPresentByXPATH(driver, "//table[2]/tbody/tr/td/div/h3/span", this.name);
        }
    }

    /**
     * Create Glossary category
     *
     * @param glossaryName
     */
    public void createGlossaryCategory(String glossaryName) {
        this.dateAndTime = a.currentDateTime();
        this.name = test + " Glossary Category " + this.dateAndTime;

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Browse by category")));
        driver.findElement(By.linkText("Browse by category")).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div[3]/div", "No entries found in this section");
        driver.findElement(By.xpath("//td/div/form/div/input")).click();
        ip.isElementClickableByXpath(driver, "//div/input", 60);
        driver.findElement(By.xpath("//div/input")).click();
        ip.isElementClickableByXpath(driver, "//td[2]/input", 60);
        driver.findElement(By.xpath("//td[2]/input")).sendKeys(this.name);
        new Select(driver.findElement(By.xpath("//select"))).selectByValue("1");
        driver.findElement(By.xpath("//div/input[6]")).click();
        ip.isTextPresentByXPATH(driver, "//td/span", this.name);
        ip.isTextPresentByXPATH(driver, "//td/span[2]", "(0 Entries)");
        driver.findElement(By.xpath("//form/div/input")).click();
        ip.isElementClickableByXpath(driver, "//select", 60);

        List<WebElement> allOptions = driver.findElement(By.xpath("//select")).findElements(By.tagName("option"));
        int i = 0;
        for (WebElement option : allOptions) {
            if (this.name.equals(option.getText())) {
                break;
            }
            i++;
        }
        new Select(driver.findElement(By.xpath("//select"))).selectByIndex(i);
        ip.isTextPresentByXPATH(driver, "//td[2]/b", this.name);
    }

    /**
     * Edit Glossary entry
     *
     * @param glossaryName
     * @param studentGlossaryEntryName
     * @param glossaryCategoryName
     * @param teacherGlossaryEntryName
     */
    public void editGlossaryEntry(String glossaryName, String studentGlossaryEntryName, String glossaryCategoryName, String teacherGlossaryEntryName) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title=\"Edit\"] > img.iconsmall")));
        driver.findElement(By.cssSelector("a[title=\"Edit\"] > img.iconsmall")).click();
        ip.isTextPresentByXPATH(driver, "//h2", glossaryName);
        new Select(driver.findElement(By.xpath("//div[3]/div[2]/select"))).selectByVisibleText(glossaryCategoryName);
        driver.findElement(By.xpath("//fieldset/input")).click();
        ip.isTextPresentByXPATH(driver, "//h3/span", studentGlossaryEntryName);
        driver.findElement(By.linkText("Browse by category")).click();
        ip.isTextPresentByXPATH(driver, "//b", "All categories");
        ip.isTextPresentByXPATH(driver, "//h2", glossaryCategoryName.toUpperCase());
        ip.isTextPresentByXPATH(driver, "//h3/span", studentGlossaryEntryName);
        new Select(driver.findElement(By.xpath("//select"))).selectByIndex(1);
        ip.isTextPresentByXPATH(driver, "//td[2]/b", "Entries without category");
        ip.isTextPresentByXPATH(driver, "//h3/span", teacherGlossaryEntryName);
        List<WebElement> allOptions = driver.findElement(By.xpath("//select")).findElements(By.tagName("option"));
        int i = 0;
        for (WebElement option : allOptions) {
            if (glossaryCategoryName.equals(option.getText())) {
                break;
            }
            i++;
        }
        new Select(driver.findElement(By.xpath("//select"))).selectByIndex(i);
        ip.isTextPresentByXPATH(driver, "//h3/span", studentGlossaryEntryName);
    }

    /**
     * Create Activity
     *
     * @param activityName
     * @param activityIntroName
     */
    private void createActivity(String activityName, String activityIntroName) {
        WebElement elementActivityName = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("fieldActvyNameXPATH"))));
        WebElement elementActivityIntroName = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("fieldActvyIntroXPATH"))));

        //This will check multiple times has activityName send correct value 
        int i = 1;
        Boolean result;
        value:
        do {
            elementActivityName.clear();
            elementActivityIntroName.clear();
            elementActivityName.sendKeys(activityName);
            elementActivityIntroName.sendKeys(activityIntroName);
            try {
                new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpv.getTokenValue("fieldActvyNameXPATH")), activityName));
                new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpv.getTokenValue("fieldActvyIntroXPATH")), activityIntroName));
                result = false;
                break value;
            } catch (TimeoutException e) {
                i++;
                result = true;
            }
        } while (i < 5);

        if (result) {
            Utility.illegalStateException("Unable to send expected Name: " + activityName + " or Intro: " + activityIntroName);
        }
    }

    /**
     * Add True/False question to Quiz Activity
     *
     * @param quizActivityName
     */
    public void addQuizQuestion(String quizActivityName) {
        if (quizActivityName.contains("Password")) {
            questionTitle = "Unit";
            question = "1 Kg equals 1000 grams";
            ans = "True";
        } else {
            questionTitle = "Capital";
            question = "New York City is the capital of the United States";
            ans = "False";
        }

        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentContainsTextByXPATH(driver, quizActivityName);
        Utility.clickByJavaScript(driver, "//*[contains(text(),'" + quizActivityName + "')]");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnEditQzXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnEditQzXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtEditQzScrnXPATH"), "Editing quiz: " + quizActivityName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldQzGradeXPATH"))).clear();
        driver.findElement(By.xpath(xpv.getTokenValue("fieldQzGradeXPATH"))).sendKeys("1");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSaveGradeXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("btnAddQzQstnXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("btnAddQzQstnXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtQzQstnTypeScrnXPATH"), "Choose a question type to add");
        driver.findElement(By.xpath(xpv.getTokenValue("radioBtnQstnTypeXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtRdBtnQstnTypeXPATH"), "A simple form of multiple choice "
                + "question with just the two choices 'True' and 'False'.");
        driver.findElement(By.xpath(xpv.getTokenValue("btnNxtScrnXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtAddQzQstnScrnXPATH"), "Adding a True/False question");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldQzQstnNmXPATH"))).sendKeys(questionTitle);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldQzQstnTxtXPATH"))).sendKeys(question);
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrrctAnsXPATH")))).selectByVisibleText(ans);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSvQzQstnXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyQzQstnXPATH"), question);
    }

    /**
     * User attempt to 'True/False' question in Quiz Assignment
     *
     * @param quizActivityName
     */
    public void submitQuiz(String quizActivityName, String password) {
        driver.findElement(By.xpath("//*[starts-with(text(),'" + quizActivityName + "')]")).click();
        int i = 1;
        int rows;

        Boolean attempt;
        try {
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyPrvAtmptXPATH"), "Summary of your previous attempts", 15);
            attempt = true;
        } catch (TimeoutException e) {
            attempt = false;
        }

        if (attempt) {
            rows = driver.findElements(By.xpath("//div[@id='region-main']/div/table/tbody/tr")).size();
            i = rows + 1;
            Reporter.log("This is students '" + i + "' Quiz Attempt", true);
        }

        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnEditQzXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnEditQzXPATH"))).click();

        WebElement yesRadioButton;

        if (!password.isEmpty()) {
            ip.isTextPresentByXPATH(driver, "//div[5]/div/div/p", "To attempt this quiz you need to know the quiz password");
            driver.findElement(By.xpath("//div/input")).sendKeys(password);
            driver.findElement(By.xpath("//div/input[5]")).click();
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtSbmtQzQstnXPATH"), "1 Kg equals 1000 grams");
            yesRadioButton = driver.findElement(By.xpath(xpv.getTokenValue("radioBtnTrueOptnXPATH")));
        } else {
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtSbmtQzQstnXPATH"), "New York City is the capital of the United States");
            yesRadioButton = driver.findElement(By.xpath(xpv.getTokenValue("radioBtnFlsOptnXPATH")));
        }
        yesRadioButton.click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name=\"next\"]")));
        driver.findElement(By.cssSelector("input[name=\"next\"]")).click();

        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyAnsSvdXPATH"), "Answer saved");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfySmryAttmptXPATH"), "Summary of attempt");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmtQzAnsXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyAnsCntChngXPATH"), "Once you submit, you will no longer be able to "
                + "change your answers for this attempt.");
        driver.findElement(By.xpath(xpv.getTokenValue("btnFnllySvQzAnsXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyGradeScrXPATH"), "1.00 out of a maximum of 1.00 (100%)");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("imgQzAnsXPATH"));
        driver.findElement(By.xpath("//*[contains(text(),'Finish review')]")).click();
        ip.isTextPresentByXPATH(driver, "//tr[" + i + "]/td[3]", "(100%)");
        new WebDriverWait(driver, 60).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//tr[" + i + "]/td[5]/a")));
        driver.findElement(By.xpath(xpv.getTokenValue("lnkLftPnlGradeXPATH"))).click();
        ip.isTextPresentByCSS(driver, xpv.getTokenValue("hdngGradeXPATH"), "Grades");
        int x = locateElement(quizActivityName);
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[2]", "(100%)");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/div", "(100%)");
    }

    /**
     *
     */
    public void uploadFileAndSendAllInOneForReview() {
        ip.isElementPresentByLINK(driver, "Submissions");
        driver.findElement(By.linkText("Submissions")).click();
        String file = null;
        try {
            file = directory.getCanonicalPath() + java.io.File.separator + "data"
                    + java.io.File.separator + "Review_4Mb.pptx";
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        ip.isElementClickableByXpath(driver, "//form/button", 60);
        WebElement elm = driver.findElement(By.xpath("//div[2]/div/div/form/input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", elm);
        elm.sendKeys(file);
        driver.findElement(By.xpath("//form/button")).click();
        ip.isElementPresentByLINK(driver, "Review_4Mb.pptx");

        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.
                xpath("//div[2]/span/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a/span")));
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[title=\"Click to start upload.\"]")));

        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
        assignmentText = "reviewFile" + dateFormat.format(now);
        Utility.typeInContentEditableIframe(driver, 2, assignmentText);
        ip.isElementClickableByXpath(driver, "//button[2]", 60);
        driver.findElement(By.xpath("//button[2]")).click();
        ip.isTextPresentByCSS(driver, "#confirmBox > p",
                "By sending for review, you are granting your professor access to your submission page. "
                + "Any changes you make will be reflected in your professor's view. Proceed?");
        driver.findElement(By.cssSelector("a.button.green")).click();
        ip.isTextPresentByCSS(driver, "#confirmBox > p",
                "Your assignment has been sent for review.");
        driver.findElement(By.cssSelector("a.button.green")).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/div[2]/div/div[2]",
                "Sent for Review");
    }

    /**
     *
     * @param reviewAssignmentText
     */
    public void reviewAndAddFeedbackToAllInOneOnSubmissionPage(String reviewAssignmentText) {
        ip.isElementPresentByLINK(driver, "Submissions");
        driver.findElement(By.linkText("Submissions")).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/div[2]/div/div[2]",
                "Sent for Review");
        ip.isTextPresentByXPATH(driver, "//td/a", "Review_4Mb.pptx");
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("iframes count:" + iframes.size());
        for (WebElement frame : iframes) {
            driver.switchTo().frame(frame.getAttribute("id"));
            break;
        }
        WebElement editableTxtArea = driver.switchTo().activeElement();
        Assert.assertEquals(editableTxtArea.getText(), reviewAssignmentText);
        driver.switchTo().defaultContent();

        String file = null;
        try {
            file = directory.getCanonicalPath() + java.io.File.separator + "data"
                    + java.io.File.separator + "Feedback_4Mb.pptx";
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        ip.isElementClickableByXpath(driver, "//div[2]/input", 60);
        WebElement elment = driver.findElement(By.xpath("//div[4]/div/div[2]/div/div/form/input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elment);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", elment);
        elment.sendKeys(file);
        driver.findElement(By.xpath("//div[4]/div/div[2]/div/div/form/button")).click();
        ip.isElementPresentByLINK(driver, "Feedback_4Mb.pptx");
        driver.navigate().refresh();
        ip.isElementPresentByLINK(driver, "Submissions");
        driver.findElement(By.linkText("Submissions")).click();
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
        assignmentText = "feedbackByTeacher" + dateFormat.format(now);
        Utility.typeInContentEditableIframe(driver, 2, assignmentText);
        driver.findElement(By.xpath("//div[5]/div/button")).click();
        ip.isTextPresentByCSS(driver, "#confirmBox > p",
                "Are you sure you want to submit feedback for this assignment submission?");
        driver.findElement(By.cssSelector("a.button.green")).click();
        ip.isTextPresentByCSS(driver, "#confirmBox > p",
                "Your feedback has been submitted.");
        driver.findElement(By.cssSelector("a.button.green")).click();
        driver.navigate().refresh();
        ip.isElementPresentByLINK(driver, "Submissions");
        driver.findElement(By.linkText("Submissions")).click();
        String text = Utility.getTextFromContentEditableIframe(driver, 2);
        Assert.assertEquals(text, assignmentText);
    }

    /**
     *
     * @param feedbackAssignmentText
     */
    public void updateAllInOneBasedOnFeedbackAndSubmitForGrading(String feedbackAssignmentText) {
        ip.isElementPresentByLINK(driver, "Submissions");
        driver.findElement(By.linkText("Submissions")).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/div[2]/div/div/div", "Your submission has feedback");
        ip.isTextPresentByXPATH(driver, "//div/div[2]/div/div/div[2]/div/div/div", "Grade");
        ip.isTextPresentByXPATH(driver, "//div[2]/div/div/div[2]/div/div/div[2]", "/ 100");
        ip.isTextPresentByXPATH(driver, "//td/a", "Feedback_4Mb.pptx");
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("iframes count:" + iframes.size());
        for (WebElement frame : iframes) {
            driver.switchTo().frame(frame.getAttribute("id"));
            break;
        }
        WebElement editableTxtArea = driver.switchTo().activeElement();
        Assert.assertEquals(editableTxtArea.getText(), feedbackAssignmentText);
        driver.switchTo().defaultContent();

        //'Send for Marking' button with onclick attribute is not clicked by Selenium CLICK command for Chrome Browser
        //Robot code provides the work around to perform the operation. 
        //Hence Exception is thrown incase button is not clicked after multiple (5) tries
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.
                xpath("//div[2]/span/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a/span")));
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[title=\"Click to start upload.\"]")));
        String HandleBefore = driver.getWindowHandle();
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
        assignmentText = "asgnmntSbmttdFrGrdngBsdOnFdbck" + dateFormat.format(now);
        Utility.typeInContentEditableIframe(driver, 2, assignmentText);
        ip.isElementClickableByXpath(driver, "//button[3]", 60);
        driver.findElement(By.xpath("//button[3]")).click();
        ip.isTextPresentByCSS(driver, "#confirmBox > p",
                "Are you sure you want to submit this assignment for grading?");
        driver.findElement(By.cssSelector("a.button.green")).click();
        ip.isTextPresentByCSS(driver, "#confirmBox > p",
                "Your assignment has been submitted for grading.");
        driver.findElement(By.cssSelector("a.button.green")).click();
        String text = Utility.getTextFromContentEditableIframe(driver, 2);
        Assert.assertEquals(text, assignmentText);
        ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/div[2]/div/div[2]",
                "Submitted for Grading");
        boolean wndwFnd;
        try {
            Utility.waitForNumberOfWindowsToEqual(driver, 30, 2);
            wndwFnd = true;
            System.out.println("feedback window found");
        } catch (TimeoutException e) {
            wndwFnd = false;
            System.out.println("feedback window not found");
            //Utility.illegalStateException("Feedback Window did not triggered");
        }

        if (wndwFnd) {
            int y = 1;
            for (String handle : driver.getWindowHandles()) {
                System.out.println("window handle: " + handle);
                driver.switchTo().window(handle);
                if (y == driver.getWindowHandles().size()) {
                    System.out.println("inside feedback window");
                    driver.close();
                }
                y++;
            }
        }
        driver.switchTo().window(HandleBefore);
    }

    /**
     *
     * @param allInOneAssignmentActivityName
     */
    public void verifyAllInOneCannotBeGraded(String allInOneAssignmentActivityName) {
        ip.isElementPresentContainsTextByXPATH(driver, allInOneAssignmentActivityName);
        int x = locateElement(allInOneAssignmentActivityName);
        int y = x + 1;
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/span", "0 of 1");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[4]/span", "0 of 1");
        driver.findElement(By.xpath("//tr[" + x + "]/td/span/a/span")).click();
        ip.isTextPresentByXPATH(driver, "//td[3]/div/div", "/ 100");
        ip.invisibilityOfElementByXpath(driver, "//div/input");
    }

    /**
     *
     * @param allInOneAssignmentActivityName
     * @param studentAssignmentGradingText
     */
    public void verifyStudentsAllInOneFinalSubmissionThenAddGradeAndCommentOnGradePage(String allInOneAssignmentActivityName, String studentAssignmentGradingText) {
        ip.isElementPresentContainsTextByXPATH(driver, allInOneAssignmentActivityName);
        int x = locateElement(allInOneAssignmentActivityName);
        int y = x + 1;
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/span", "1 of 1");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[4]/span", "0 of 1");
        driver.findElement(By.xpath("//tr[" + x + "]/td/span/a/span")).click();
        ip.isElementClickableByXpath(driver, "//tr[" + y + "]/td/div/table/tbody/tr[2]/td[3]/div/div/input", 60);
        driver.findElement(By.xpath("//tr[" + y + "]/td/div/table/tbody/tr[2]/td[4]/div/span/a")).click();
        ip.isTextPresentByXPATH(driver, "//td/div/div/div/div", "Submitted for Grading");
        String text = Utility.getTextFromContentEditableIframe(driver, 1);
        Assert.assertEquals(text, studentAssignmentGradingText);
        driver.findElement(By.xpath("//tr[" + y + "]/td/div/table/tbody/tr[2]/td[3]/div/div/input")).clear();
        driver.findElement(By.xpath("//tr[" + y + "]/td/div/table/tbody/tr[2]/td[3]/div/div/input")).sendKeys("62");
        driver.findElement(By.xpath("//div[2]/span/a")).click();
        ip.isTextPresentByXPATH(driver, "//div/li/a", "Feedback_4Mb.pptx");
        ip.isElementClickableByXpath(driver, "//td[2]/div[2]/a", 60);
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
        assignmentText = "asgnmntGraded" + dateFormat.format(now);
        new WebDriverWait(driver, 60).until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("span.mceIcon.mce_justifyfull")));
        Utility.typeInContentEditableIframe(driver, 1, assignmentText);
        driver.findElement(By.xpath("//tr[" + y + "]/td/div/div/a")).click();
        ip.isTextPresentByCSS(driver, "#confirmBox > p",
                "Are you sure that you wish to save and release the grade & feedback for this assignment?");
        driver.findElement(By.cssSelector("a.button.green")).click();
        ip.isTextPresentByCSS(driver, "#confirmBox > p",
                "Your grading changes have been saved.");
        driver.findElement(By.cssSelector("a.button.green")).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lnkLftPnlGradeXPATH"), 60);
        Utility.actionBuilderClick(driver, xpv.getTokenValue("lnkLftPnlGradeXPATH"));
        ip.isTextPresentByCSS(driver, xpv.getTokenValue("hdngGradeXPATH"), "Grades");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[4]/span", "1 of 1");
        /*driver.findElement(By.xpath("//tr[" + x + "]/td/a")).click();
         ip.isElementPresentByLINK(driver, "Submissions");
         driver.findElement(By.linkText("Submissions")).click();
         ip.isElementPresentByLINK(driver, "View submissions for review or grading.");*/
    }

    /**
     *
     * @param allInOneAssignmentActivityName
     * @param assignmentGradedText
     */
    public void verifyAllInOneGradeAndTeachersCommentOnSubmissionAndGradePage(String allInOneAssignmentActivityName, String assignmentGradedText) {
        ip.isElementPresentContainsTextByXPATH(driver, allInOneAssignmentActivityName);
        int x = locateElement(allInOneAssignmentActivityName);
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[2]", "62/100 (62%)");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/div", "62/100 (62%)");
        driver.findElement(By.xpath("//tr[" + x + "]/td[3]/div[2]/a")).click();
        ip.isTextPresentByXPATH(driver, "//td/div/div/div/div/div/div", "Grade");
        ip.isTextPresentByXPATH(driver, "//td/div/div/div/div/div/div[2]", "62 / 100");
        ip.isTextPresentByXPATH(driver, "//div[3]/div/table/tbody/tr/td/a", "Feedback_4Mb.pptx");
        new WebDriverWait(driver, 60).until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("span.mceIcon.mce_insertunorderedlist")));
        String text = Utility.getTextFromContentEditableIframe(driver, 1);
        Assert.assertEquals(text, assignmentGradedText);
        driver.findElement(By.linkText(allInOneAssignmentActivityName)).click();
        ip.isElementPresentByLINK(driver, "Submissions");
        driver.findElement(By.linkText("Submissions")).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/div[2]/div/div/div", "Your submission has been graded");
        ip.isTextPresentByXPATH(driver, "//div[2]/div/div/div[2]/div/div/div[2]", "62 / 100");
        ip.isTextPresentByXPATH(driver, "//td/a", "Feedback_4Mb.pptx");
        new WebDriverWait(driver, 60).until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("span.mceIcon.mce_insertunorderedlist")));
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("iframes count:" + iframes.size());
        for (WebElement frame : iframes) {
            driver.switchTo().frame(frame.getAttribute("id"));
            break;
        }
        WebElement editableTxtArea = driver.switchTo().activeElement();
        Assert.assertEquals(editableTxtArea.getText(), assignmentGradedText);
        driver.switchTo().defaultContent();
    }

    /**
     *
     * @param allInOneAssignmentActivityName
     * @param studentUserName
     */
    public void allowStudentToResubmitAllInOne(String allInOneAssignmentActivityName, String studentUserName) {
        ip.isElementPresentContainsTextByXPATH(driver, allInOneAssignmentActivityName);
        int x = locateElement(allInOneAssignmentActivityName);

        int z = 1;
        loop:
        while (true) {
            driver.findElement(By.xpath("//tr[" + x + "]/td/span/a/span")).click();
            ip.isElementClickableByXpath(driver, "//div[2]/span/a", 60);
            driver.findElement(By.xpath("//div[2]/span/a")).click();
            int y = x + 1;
            if (z == 1) {
                ip.isElementClickableByXpath(driver, "//div/li/a[2]", 60);
                driver.findElement(By.xpath("//div/li/a[2]")).click();
                ip.isTextPresentByXPATH(driver, "//div/div/div/div/div[2]/span",
                        "Are you sure you want to delete the document?");
                driver.findElement(By.xpath("//td[2]/table/tbody/tr[2]/td[2]")).click();
                ip.isTextPresentByXPATH(driver, "//div/div/div/div/div[2]/span",
                        "File successfully deleted.");
                driver.findElement(By.xpath("//td/table/tbody/tr[2]/td[2]")).click();
                new WebDriverWait(driver, 60).until(ExpectedConditions.
                        invisibilityOfElementLocated(By.linkText("Feedback_4Mb.pptx")));
            }
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
            assignmentText = "resubmit" + dateFormat.format(now);
            new WebDriverWait(driver, 60).until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("span.mceIcon.mce_justifyfull")));
            Utility.typeInContentEditableIframe(driver, 1, assignmentText);
            Utility.actionBuilderClick(driver, "//td/a[2]");
            ip.isTextPresentByCSS(driver, "#confirmBox > p",
                    "Are you sure that you wish to save and release the grade & feedback for this assignment?");
            driver.findElement(By.cssSelector("a.button.green")).click();
            ip.isTextPresentByCSS(driver, "#confirmBox > p",
                    "Your grading changes have been saved.");
            driver.findElement(By.cssSelector("a.button.green")).click();
            ip.isElementClickableByXpath(driver, "//div[2]/span/a", 60);
            driver.navigate().refresh();
            ip.isElementClickableByXpath(driver, "//tr[" + x + "]/td/span/a/span", 60);
            driver.findElement(By.xpath("//tr[" + x + "]/td/span/a/span")).click();
            WebElement elm;
            switch (program) {
                case "usc-mat":
                case "usc-msw":
                    elm = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//tr[" + y + "]/td/div/table/tbody/tr[2]/td[6]/span/a")));
                    break;
                default:
                    elm = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//tr[" + y + "]/td/div/table/tbody/tr[2]/td[5]/span/a")));
            }
            if (z < 6) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elm);
                ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", elm);
                elm.click();
                try {
                    ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtAlrtAlwResbmtAsgntXPATH"),
                            "Do you want to allow " + studentUserName + " to resubmit this assignment?", 30);
                    break loop;
                } catch (TimeoutException e) {
                    z++;
                    System.out.println("count: " + z);
                    driver.navigate().refresh();
                    ip.isElementClickableByXpath(driver, "//tr[" + x + "]/td/span/a/span", 60);
                }
            } else {
                Utility.illegalStateException("Selenium Chrome Browser limitation: Unable to click on Image button after multiple tries also, works fine for FF");
            }
        }
        driver.findElement(By.xpath(xpv.getTokenValue("btnCrfrmAlwResbmtAsgntXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtAlrtAlwResbmtAsgntXPATH"), "Allowed Resubmit to " + studentUserName + " and mail sent.");
        driver.findElement(By.xpath(xpv.getTokenValue("btnResbmtdAsgntXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("lnkLftPnlGradeXPATH"))).click();
        ip.isTextPresentByCSS(driver, xpv.getTokenValue("hdngGradeXPATH"), "Grades");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/span", "0 of 1");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[4]/span", "0 of 1");
    }

    /**
     *
     * @param resubmissionText
     */
    public void verifyAllInOneCanBeResubmitted(String resubmissionText) {
        ip.isElementPresentByLINK(driver, "Submissions");
        driver.findElement(By.linkText("Submissions")).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/div[2]/div/div/div", "Your submission has feedback");
        ip.isTextPresentByXPATH(driver, "//div/div[2]/div/div/div[2]/div/div/div", "Grade");
        ip.isTextPresentByXPATH(driver, "//div[2]/div/div/div[2]/div/div/div[2]", "/ 100");
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("iframes count:" + iframes.size());
        for (WebElement frame : iframes) {
            driver.switchTo().frame(frame.getAttribute("id"));
            break;
        }
        WebElement editableTxtArea = driver.switchTo().activeElement();
        Assert.assertEquals(editableTxtArea.getText(), resubmissionText);
        driver.switchTo().defaultContent();
        ip.isElementPresentByLINK(driver, "Review_4Mb.pptx");
        driver.findElement(By.xpath("//td/button")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.
                invisibilityOfElementLocated(By.linkText("Review_4Mb.pptx")));
    }

    /**
     * Delete all Activities
     *
     * @param forumActivityName
     * @param quizActivityName
     * @param allInOneAssignmentActivityName
     * @param pageActivityName
     */
    public void deleteActivities(String... activities) {
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        for (String activity : activities) {
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("imgDltActvtyXPATH"));
            Utility.clickByJavaScript(driver, xpv.getTokenValue("imgDltActvtyXPATH"));
            if (activity.contains("Forum")) {
                ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/p", "Are you absolutely sure you want to completely delete Forum '" + activity + "' ?");
            } else if (activity.contains("Quiz")) {
                ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/p", "Are you absolutely sure you want to completely delete Quiz '" + activity + "' ?");
            } else if (activity.contains("All in One")) {
                ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/p", "Are you absolutely sure you want to completely delete Assignment '" + activity + "' ?");
            } else if (activity.contains("Page")) {
                ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/p", "Are you absolutely sure you want to completely delete Page '" + activity + "' ?");
            } else {
                ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/p", "Are you absolutely sure you want to completely delete Glossary '" + activity + "' ?");
            }
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnCnfrmDltActvtyXPATH"));
            driver.findElement(By.xpath(xpv.getTokenValue("btnCnfrmDltActvtyXPATH"))).click();
        }
    }

    /**
     *
     * @param passwordQuizName
     */
    public void generateQuizPassword(String passwordQuizName) {
        driver.findElement(By.xpath("//*[starts-with(text(),'" + passwordQuizName + "')]")).click();
        ip.isElementPresentByID(driver, "generate_pwds_btn");
        driver.findElement(By.id("generate_pwds_btn")).click();
        Utility.waitForAlertToBeAccepted(driver, 60, "This will e-mail you and all other instructors in this section a list of passwords for this quiz. "
                + "If you already generated this, a new set of passwords will be created, "
                + "rendering the old set invalid. Students will need to use the latest set.");
        Utility.waitForAlertToBeAccepted(driver, 60, "Passwords have been emailed.");
        //new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElement(By.xpath("//*[contains(text(),'//div[5]/div/div[4]/div[3]')]"), "Last generated by autoteacher1 autoteacher1 on 02-14-2013"));
        //new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElement(By.xpath("//*[contains(text(),'//div[3]/div[3]')]"), "Last generated by autoteacher1 autoteacher1 on 02-14-2013"));
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("generate_pwds_btn")));
    }

    /**
     *
     * @param elementName
     * @return
     */
    private int locateElement(String elementName) {
        int x = 2;
        do {
            try {
                ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td/a", elementName, 5);
                break;
            } catch (TimeoutException e) {
                System.out.println(elementName + " not present at x: " + x);
                x = x + 2;
            }
        } while (x < 72);
        return x;
    }

    /**
     * Create & Verify Offline Activity
     *
     * @param file
     */
    public void createOfflineActivity(String file) {
        String filepath = null;
        try {
            filepath = directory.getCanonicalPath() + java.io.File.separator + "data" + java.io.File.separator;
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dateAndTime = a.currentDateTime();
        this.name = test + " Offline " + this.dateAndTime;
        this.intro = test + " intro " + this.dateAndTime;
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Offline activity");
        createActivity(this.name, this.intro);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldActvyIntroXPATH"))).clear();
        Utility.readAndCopyContentsToTextField(driver, filepath + file, xpv.getTokenValue("fieldActvyIntroXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isElementPresentContainsTextByXPATH(driver, "Whizbang");
    }

    /**
     * Verify Student has read only access to Offline Activity
     *
     * @param offlineActivityName
     */
    public void readOnlyAccessToOfflineActivity(String offlineActivityName) {
        driver.findElement(By.xpath("//*[starts-with(text(),'" + offlineActivityName + "')]")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.xpath(xpv.getTokenValue("btnSbmtAsgnmntXPATH")))));
    }

    /**
     * View Reveal Password button for All In One Assignment
     *
     * @param allInOneAssignmentActivityNameWithRevealPassword
     */
    public void viewRevealPasswordButtonForAllInOneAssignemnt(String allInOneAssignmentActivityNameWithRevealPassword) {
        driver.findElement(By.xpath("//*[starts-with(text(),'" + allInOneAssignmentActivityNameWithRevealPassword + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("revealDocumentPasswordButtonXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("revealDocumentPasswordButtonXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("revealDocumentPasswordConfirmationButtonXPATH"), 60);
        String text = driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div[2]/span")).getText();
        if (!text.equals(xpv.getTokenValue("revealDocumentPasswordConfirmationText"))) {
            Utility.illegalStateException("Text does not match, Expected: " + xpv.getTokenValue("revealDocumentPasswordConfirmationText")
                    + "- Actual: " + text);
        }
        driver.findElement(By.xpath(xpv.getTokenValue("revealDocumentPasswordConfirmationButtonXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("revealPasswordPopUpButtonXPATH"), 60);
        text = driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div[2]/span")).getText();
        if (!text.equals(xpv.getTokenValue("revealPasswordText"))) {
            Utility.illegalStateException("Text does not match, Expected: " + xpv.getTokenValue("revealPasswordText")
                    + "- Actual: " + text);
        }
        driver.findElement(By.xpath(xpv.getTokenValue("revealPasswordPopUpButtonXPATH"))).click();
    }

    /**
     * Create All In One Assignment Activity with Reveal Password
     */
    public void createAllInOneAssignmentActivityWithRevealPassword() {
        this.dateAndTime = a.currentDateTime();
        this.name = test + " All in One-Reveal Password " + this.dateAndTime;
        this.intro = test + " intro-Reveal Password " + this.dateAndTime;
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("All in one assignment");
        createActivity(this.name, this.intro);

        //Checks whether the document password check box is selected or not if not then select it
        Boolean documentpasswordcheckbox = driver.findElement(By.xpath(xpv.getTokenValue("documentPasswordManagementCheckboxXPATH"))).isSelected();
        if (documentpasswordcheckbox == false) {
            driver.findElement(By.xpath(xpv.getTokenValue("documentPasswordManagementCheckboxXPATH"))).click();
        } else {
            Utility.illegalStateException("Document Password Management Checkbox varies, "
                    + "expected: 'Not Checked' but actual: 'Checked'");
        }
        driver.findElement(By.xpath(xpv.getTokenValue("documentPasswordTextBoxXPATH"))).sendKeys(xpv.getTokenValue("documentPassword"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), this.intro);
    }

    /**
     * Create & Verify LiveSession Activity while selecting values other than
     * "100 point and Credit/No Credit"
     */
    public void createLiveSessionActivity() {
        this.dateAndTime = a.currentDateTime();
        this.name = test + "Live Session" + this.dateAndTime;
        this.intro = test + "Live Session" + this.dateAndTime;
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Livesession activity");
        createActivity(this.name, this.intro);
        driver.findElement(By.xpath(xpv.getTokenValue("gradeDropDownXPATH"))).click();
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("gradeDropDownXPATH")))).selectByVisibleText(xpv.getTokenValue("gradeSelect"));
        ip.isTextPresentByID(driver, "ext-gen61", xpv.getTokenValue("nonAutoGradableGradePopUpText"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("nonAutoGradableGradePopUpYesButtonXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), this.intro);
    }

    /**
     * Verify graded & submitted section of Offline Activity
     *
     * @param offlineActivityName
     */
    public void verifyOfflineActivitySubmittedAndGradedSection(String offlineActivityName) {
        int x = locateElement(offlineActivityName);
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("gradeTableXPATH") + "[" + x + "]/td[3]", "N/A");
        ip.invisibilityOfElementByXpathWithText(driver, xpv.getTokenValue("gradeTableXPATH") + "[" + x + "]/td[4]", "N/A");
    }

    /**
     * Verify coursework unit is Expandable or not while changing 'Disable date
     * in section' Field
     *
     */
    public void courseworkUnitExpandableOrNotWhileChangingDisableDateField() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentDate = cal.get(Calendar.DATE);
        unitStartDate = Integer.toString(currentDate);
        int currentMonthID = currentMonth + 1;
        if (currentMonthID == 1 || currentMonthID == 3 || currentMonthID == 5 || currentMonthID == 7 || currentMonthID == 8 || currentMonthID == 10 || currentMonthID == 12) {
            unitEndDate = "31";
        } else if (currentMonthID == 4 || currentMonthID == 6 || currentMonthID == 9 || currentMonthID == 11) {
            unitEndDate = "30";
        } else if (currentMonthID == 2) {
            if ((currentYear % 4 == 0 && currentYear % 100 != 0) || currentYear % 400 == 0) {
                unitEndDate = "29";
            } else {
                unitEndDate = "28";
            }
        }
        System.out.println(unitEndDate);
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("courseUnitEditXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("courseUnitEditXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("courseUnitStartDateXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("courseUnitStartDateXPATH"))).click();
        String yearSelected = driver.findElement(By.xpath(xpv.getTokenValue("courseUnitStartYearXPATH"))).getText();
        int yearSelectedStartDate = Integer.parseInt(yearSelected);
        int yearDifference = currentYear - yearSelectedStartDate;
        if (yearDifference > 0) {
            int x = yearDifference * 12;
            for (int i = 0; i < x; i++) {
                driver.findElement(By.cssSelector(xpv.getTokenValue("courseUnitCalendarForwardButtonCSS"))).click();
            }
        } else if (yearDifference < 0) {
            int x = yearDifference * 12;
            for (int i = 0; i < x; i++) {
                driver.findElement(By.cssSelector(xpv.getTokenValue("courseUnitCalendarBackwardButtonCSS"))).click();
            }
        }
        driver.findElement(By.cssSelector(xpv.getTokenValue("courseUnitCalendarMonthCSS"))).click();
        driver.findElement(By.xpath("//option[" + currentMonthID + "]")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText(unitStartDate)));
        driver.findElement(By.linkText(unitStartDate)).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("courseUnitEndDateXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("courseUnitEndDateXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText(unitEndDate)));
        driver.findElement(By.linkText(unitEndDate)).click();
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("editCourseSettingLinkXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("editCourseSettingLinkXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("editCourseSettingsPageHeaderXPATH"), "Edit course settings");

        //Checks whether the "Disable Date in sections" check box is selected or not - if yes then deselect it
        Boolean disabledatecheckbox = driver.findElement(By.name("datelessflag")).isSelected();
        if (disabledatecheckbox == true) {
            driver.findElement(By.name("datelessflag")).click();
        }
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("showHideContentsXPATH"), "Hide Contents");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("assignmentRowUnderShowContentsXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("editCourseSettingLinkXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("editCourseSettingLinkXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("editCourseSettingsPageHeaderXPATH"), "Edit course settings");

        //Checks whether the "Disable Date in sections" check box is selected or not - if no then select it
        disabledatecheckbox = driver.findElement(By.name("datelessflag")).isSelected();
        if (disabledatecheckbox == false) {
            driver.findElement(By.name("datelessflag")).click();
        }
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("showHideContentsXPATH"), "Show Contents");
        new WebDriverWait(driver, 60).until(ExpectedConditions.not(ExpectedConditions.
                presenceOfElementLocated(By.xpath(xpv.getTokenValue("assignmentRowUnderShowContentsXPATH")))));
    }

    /**
     * Verify the elements on the right side bar of course work page
     */
    public void verifyRightSidebarOfCourseWorkPage() {
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("toolBoxTextXPATH"), "Tool Box");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("toolBoxTextBoxXPATH"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("upcomingEventsTextXPATH"), "Upcoming Events");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("calendarTextXPATH"), "Calendar");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("calendarXPATH"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("resourcesTextXPATH"), "Resources");
    }

    /**
     *
     * @return
     */
    public String getActivityName() {
        return this.name;
    }

    /**
     *
     * @return
     */
    public String getAssignmentText() {
        return this.assignmentText;
    }
}
