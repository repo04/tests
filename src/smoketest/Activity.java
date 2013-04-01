/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.lang.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    private String pswdQuizName;
    private String questionTitle, question, ans;
    private String glossaryName;
    private String glossaryEntryName;
    private String glossaryCategoryName;
    private String lessonActivityName;
    StackTraceElement[] stackTraceElements; 

    
    public void createLessonActivity() {
        String currentDateTime = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        System.out.println("######&(*&#(@&*$&*(@#&$*(&@*#$&(*&$*(&*(#&*&$#*@&$*(@#&$*(@#&*(@#&(*^%&^%&^@&*^%&*");
    }
    
    
    public String getExecutingMethod() {
        String temp = Thread.currentThread().getStackTrace()[2].getMethodName();
        return temp;
    }
    /**
 * Create & Verify Forum Activity
     */
    public void crtForumActvty() {
        String forumIntro;
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.forumName = "RgsnTstForum " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            forumIntro = "RgsnTstForumIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.forumName = "SmkTstForum " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            forumIntro = "SmkTstForumIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.forumName = "DbgTstForum " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            forumIntro = "DbgTstForumIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Forum");
        createActivity(forumName, forumIntro);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), forumIntro);
    }

    /**
     * Create & Verify Quiz Activity
     */
    public void crtQuizActvty() {
        String quizIntro;
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.quizName = "RgsnTstQuiz " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            quizIntro = "RgsnTstQuizIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.quizName = "SmkTstQuiz " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            quizIntro = "SmkTstQuizIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.quizName = "DbgTstQuiz " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            quizIntro = "DbgTstQuizIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Quiz");
        createActivity(quizName, quizIntro);
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctQuizAttmpts")))).selectByVisibleText("Unlimited");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), quizIntro);
    }

    /**
     *
     */
    public void crtPswdQuizActivity() {
        String quizIntro;
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.pswdQuizName = "RgsnTstPswdQuiz " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            quizIntro = "RgsnTstPswdQuizIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.pswdQuizName = "SmkTstPswdQuiz " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            quizIntro = "SmkTstPswdQuizIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.pswdQuizName = "DbgTstPswdQuiz " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            quizIntro = "DbgTstPswdQuizIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Quiz");
        createActivity(pswdQuizName, quizIntro);
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctQuizAttmpts")))).selectByVisibleText("Unlimited");
        driver.findElement(By.xpath("//fieldset[6]/div[2]/div/div[2]/input")).sendKeys("Password1");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), quizIntro);
    }

    /**
     * Create & Verify AllInOneAsgnmnt Activity
     */
    public void createAllInOneAssignmentActivity() {
        String allInOneAsgnmntIntro;
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.allInOneAsgnmntName = "RgsnTstAllInOneAsgnmnt " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            allInOneAsgnmntIntro = "RgsnTstAllInOneAsgnmntIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.allInOneAsgnmntName = "SmkTstAllInOneAsgnmnt " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            allInOneAsgnmntIntro = "SmkTstAllInOneAsgnmntIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.allInOneAsgnmntName = "DbgTstAllInOneAsgnmnt " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            allInOneAsgnmntIntro = "DbgTstAllInOneAsgnmntIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("All in one assignment");
        createActivity(allInOneAsgnmntName, allInOneAsgnmntIntro);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), allInOneAsgnmntIntro);
    }

    /**
     * Create Glossary activity
     */
    public void crtGlossaryActvty() {
        String glossaryIntro;
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.glossaryName = "RgsnTstGlossary " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            glossaryIntro = "RgsnTstGlossaryIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.glossaryName = "DbgTstGlossary " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            glossaryIntro = "DbgTstGlossaryIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddAnActvtyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddAnActvtyXPATH")))).selectByVisibleText("Glossary");
        createActivity(glossaryName, glossaryIntro);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), glossaryIntro);
    }

    /**
     * Create & Verify Page Resource
     */
    public void createPageResource() {
        String pageContent;
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.pageName = "RgsnTstPage " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            pageContent = "RgsnTstPageContent " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.pageName = "SmkTstPage " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            pageContent = "SmkTstPageContent " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.pageName = "DbgTstPage " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            pageContent = "DbgTstPageContent " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddRescXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddRescXPATH")))).selectByVisibleText("Page");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldActvyNameXPATH"));

        driver.findElement(By.xpath(xpv.getTokenValue("fieldActvyNameXPATH"))).sendKeys(pageName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldRescContentXPATH"))).sendKeys(pageContent);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngRescTextXPATH"), pageContent);
    }

    /**
     * Create - Syllabus Activity
     */
    public void createSyllabusActivity() {
        String sylbsIntro;
        sylbsIntro = "RgsnTstSylbsIntro " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctAddSyllbsActvyXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctAddSyllbsActvyXPATH")))).selectByVisibleText("Page");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldActvyNameXPATH"));

        driver.findElement(By.xpath(xpv.getTokenValue("fieldActvyNameXPATH"))).sendKeys("Syllabus");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldRescContentXPATH"))).sendKeys(sylbsIntro);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngRescTextXPATH"), sylbsIntro);
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
        ip.isElementClickableByXpath(driver, "//div/input[2]", 60);
        driver.findElement(By.xpath("//div/input[2]")).click();
        ip.isTextPresentByXPATH(driver, "//h2", glossaryName);
        String conceptEntry;
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.glossaryEntryName = "RgsnTstGlossaryConceptEntry " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            conceptEntry = "RgsnTstGlossaryConceptEntryDef " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.glossaryEntryName = "DbgTstGlossaryConceptEntry " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            conceptEntry = "DbgTstGlossaryConceptEntryDef " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }
        driver.findElement(By.xpath("//input[@id='id_concept']")).sendKeys(this.glossaryEntryName);
        ip.isElementClickableByXpath(driver, "//*[@id='id_definition_editor_toolbargroup']/span", 60);

        Utility.typeInContentEditableIframe(driver, 1, conceptEntry);
        driver.findElement(By.xpath("//fieldset/input")).click();
        ip.isTextPresentByXPATH(driver, "//h3/span", this.glossaryEntryName);
        driver.findElement(By.linkText("Browse by category")).click();
        ip.isTextPresentByXPATH(driver, "//td[2]/b", "All categories");
        ip.isTextPresentByXPATH(driver, "//div[4]/div[3]/div", "No entries found in this section");
        new Select(driver.findElement(By.xpath("//select"))).selectByIndex(1);
        ip.isTextPresentByXPATH(driver, "//td[2]/b", "Entries without category");
        if (LoginPage.getUser().contains("teacher")) {
            ip.isTextPresentByXPATH(driver, "//h3/span", this.glossaryEntryName);
        } else {
            ip.isTextPresentByXPATH(driver, "//table[3]/tbody/tr/td/div/h3/span", this.glossaryEntryName);
        }
        driver.findElement(By.linkText("Browse by date")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("By creation date")));
        driver.findElement(By.cssSelector("a[title=\"By creation date ascending\"]")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("By creation date")));
        driver.findElement(By.cssSelector("a[title=\"By creation date change to descending\"]")).click();
        ip.isTextPresentByXPATH(driver, "//h3/span", this.glossaryEntryName);
        driver.findElement(By.linkText("Browse by Author")).click();
        ip.isTextPresentByXPATH(driver, "//h2", Utility.getFullName(LoginPage.getUser()));
        ip.isTextPresentByXPATH(driver, "//h3/span", this.glossaryEntryName);
        driver.findElement(By.linkText("Browse by alphabet")).click();
        if (LoginPage.getUser().contains("teacher")) {
            ip.isTextPresentByXPATH(driver, "//h3/span", this.glossaryEntryName);
        } else {
            ip.isTextPresentByXPATH(driver, "//table[2]/tbody/tr/td/div/h3/span", this.glossaryEntryName);
        }
    }

    /**
     * Create Glossary category
     * 
     * @param glossaryName 
     */
    public void createGlossaryCategory(String glossaryName) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Browse by category")));
        driver.findElement(By.linkText("Browse by category")).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div[3]/div", "No entries found in this section");
        driver.findElement(By.xpath("//td/div/form/div/input")).click();
        ip.isElementClickableByXpath(driver, "//div/input", 60);
        driver.findElement(By.xpath("//div/input")).click();
        ip.isElementClickableByXpath(driver, "//td[2]/input", 60);
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.glossaryCategoryName = "RgsnTstGlossaryCategoryName " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.glossaryCategoryName = "DbgTstGlossaryCategoryName " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }
        driver.findElement(By.xpath("//td[2]/input")).sendKeys(this.glossaryCategoryName);
        new Select(driver.findElement(By.xpath("//select"))).selectByValue("1");
        driver.findElement(By.xpath("//div/input[6]")).click();
        ip.isTextPresentByXPATH(driver, "//td/span", this.glossaryCategoryName);
        ip.isTextPresentByXPATH(driver, "//td/span[2]", "(0 Entries)");
        driver.findElement(By.xpath("//form/div/input")).click();
        ip.isElementClickableByXpath(driver, "//select", 60);
        List<WebElement> allOptions = driver.findElement(By.xpath("//select")).findElements(By.tagName("option"));
        int i = 0;
        for (WebElement option : allOptions) {
            if (this.glossaryCategoryName.equals(option.getText())) {
                break;
            }
            i++;
        }
        new Select(driver.findElement(By.xpath("//select"))).selectByIndex(i);
        ip.isTextPresentByXPATH(driver, "//td[2]/b", this.glossaryCategoryName);
    }   
    
    /**
     * Edit Glossary entry
     * 
     * @param glossaryName
     * @param stdtGlossaryEntryName
     * @param glossaryCategoryName
     * @param tchrGlossaryEntryName 
     */
    public void editGlossaryEntry(String glossaryName, String stdtGlossaryEntryName, String glossaryCategoryName, String tchrGlossaryEntryName) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title=\"Edit\"] > img.iconsmall")));
        driver.findElement(By.cssSelector("a[title=\"Edit\"] > img.iconsmall")).click();
        ip.isTextPresentByXPATH(driver, "//h2", glossaryName);
        new Select(driver.findElement(By.xpath("//div[3]/div[2]/select"))).selectByVisibleText(glossaryCategoryName);
        driver.findElement(By.xpath("//fieldset/input")).click();
        ip.isTextPresentByXPATH(driver, "//h3/span", stdtGlossaryEntryName);
        driver.findElement(By.linkText("Browse by category")).click();
        ip.isTextPresentByXPATH(driver, "//b", "All categories");
        ip.isTextPresentByXPATH(driver, "//h2", glossaryCategoryName.toUpperCase());
        ip.isTextPresentByXPATH(driver, "//h3/span", stdtGlossaryEntryName);
        new Select(driver.findElement(By.xpath("//select"))).selectByIndex(1);
        ip.isTextPresentByXPATH(driver, "//td[2]/b", "Entries without category");
        ip.isTextPresentByXPATH(driver, "//h3/span", tchrGlossaryEntryName);
        List<WebElement> allOptions = driver.findElement(By.xpath("//select")).findElements(By.tagName("option"));
        int i = 0;
        for (WebElement option : allOptions) {
            if (glossaryCategoryName.equals(option.getText())) {
                break;
            }
            i++;
        }
        new Select(driver.findElement(By.xpath("//select"))).selectByIndex(i);
        ip.isTextPresentByXPATH(driver, "//h3/span", stdtGlossaryEntryName);
    }

    /**
     * Create Activity
     *
     * @param forumName
     * @param forumIntro
     */
    private void createActivity(String forumName, String forumIntro) {
        WebElement actvtyNm = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("fieldActvyNameXPATH"))));
        WebElement actvtyIntroNm = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("fieldActvyIntroXPATH"))));

        //This is to verify actvtyName passes correct value 
        value:
        while (true) {
            actvtyNm.clear();
            actvtyIntroNm.clear();
            actvtyNm.sendKeys(forumName);
            actvtyIntroNm.sendKeys(forumIntro);
            try {
                new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpv.getTokenValue("fieldActvyNameXPATH")), forumName));
                new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpv.getTokenValue("fieldActvyIntroXPATH")), forumIntro));
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
        if (quizActvtyName.contains("PswdQuiz")) {
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
        ip.isElementPresentContainsTextByXPATH(driver, quizActvtyName);
        Utility.clickByJavaScript(driver, "//*[contains(text(),'" + quizActvtyName + "')]");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnEditQzXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnEditQzXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtEditQzScrnXPATH"), "Editing quiz: " + quizActvtyName);
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
     * @param quizActvtyName
     */
    public void submitQuiz(String quizActvtyName, String password) {
        driver.findElement(By.xpath("//*[starts-with(text(),'" + quizActvtyName + "')]")).click();
        int i = 1;
        int rows;

        Boolean attempt;
        try {
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyPrvAtmptXPATH"), "Summary of your previous attempts");
            attempt = true;
        } catch (TimeoutException e) {
            attempt = false;
        }

        if (attempt) {
            rows = driver.findElements(By.xpath("//div[@id='region-main']/div/table/tbody/tr")).size();
            System.out.println("rows: " + rows);
            i = rows + 1;
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

        driver.findElement(By.xpath(xpv.getTokenValue("btnNxtSbmtQzQstnXPATH"))).click();
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
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngGradeXPATH"), "Grades");
        int x = locateElement(quizActvtyName);
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[2]", "(100%)");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/div", "(100%)");
    }

    /**
     * Submit Assignment
     *
     * @param allInOneAsgnmntAvtvtyName
     */
    public void submitAssignment(String allInOneAsgnmntAvtvtyName) {
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
        String asgmntRspns = "asgmntRspns" + dateFormat.format(now);
        driver.findElement(By.xpath("//*[starts-with(text(),'" + allInOneAsgnmntAvtvtyName + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnSbmtAsgnmntXPATH"));
        new WebDriverWait(driver, 60).until(ExpectedConditions.
                presenceOfElementLocated(By.cssSelector("img[alt=\"You must submit this assignment to mark it complete.\"]")));
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmtAsgnmntXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblVrfyRspsXPATH"), "Write a Response");

        String HandleBefore = driver.getWindowHandle();
        int i = 1;
        end:
        while (i < 6) {
            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Font family")));
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            System.out.println("iframes count:" + iframes.size());
            for (WebElement frame : iframes) {
                System.out.println("Iframe ID: " + frame.getAttribute("id"));
                driver.switchTo().frame(frame.getAttribute("id"));
                break;
            }

            //Switch focus
            WebElement editableTxtArea = driver.switchTo().activeElement();
            editableTxtArea.sendKeys(Keys.chord(Keys.CONTROL, "a"), asgmntRspns);
            driver.switchTo().defaultContent();

            List<WebElement> elements = driver.findElements(By.tagName("input"));
            System.out.println("Total inputs: " + elements.size());
            Utility.robotclick(elements.get(22));
            if (i < 5) {
                if (brwsr.equalsIgnoreCase("chrome")) {
                    try {
                        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyAsgntMrkngXPATH"), "Are you sure you want to send this assignment "
                                + "for marking? After submission, you will not be able to make any changes in any documents or text.", 30);
                        break end;
                    } catch (TimeoutException e) {
                        System.out.println("count: " + i);
                        driver.navigate().refresh();
                        ip.isTitleContains(driver, "Assignment: " + allInOneAsgnmntAvtvtyName);
                        i++;
                    }
                } else {
                    ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyAsgntMrkngXPATH"), "Are you sure you want to send this assignment "
                            + "for marking? After submission, you will not be able to make any changes in any documents or text.");
                    break end;
                }
            } else {
                Utility.illegalStateException("Selenium Chrome Browser limitation: Unable to click button with onclick event, works fine for FF");
            }
        }
        driver.findElement(By.xpath(xpv.getTokenValue("btnCnfrmAsgntMrkngXPATH"))).click();

        //Temporary solution as Feedback window is not stable
        boolean wndwFnd;
        try {
            Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);
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
        new WebDriverWait(driver, 60).until(ExpectedConditions.
                presenceOfElementLocated(By.cssSelector("img[alt=\"Completed\"]")));
        ip.isTextPresentByXPATH(driver, "//td/div/p", asgmntRspns);

        //************NOT TO BE DELETED AS OF NOW************//
        //Temporary solution till the time 'BUG' is resolved
        /*if (program.contains("prod")) {
         System.out.println("in prod txtVrfyRspsXPATH");
         driver.switchTo().window(HandleBefore);
         //div/table/tbody/tr[2]/td/div
         ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyRspsXPATH"), asgmntRspns);
         } else {
         System.out.println("not in prod txtVrfyRspsXPATH");
         driver.switchTo().window(HandleBefore);
         ip.isTextPresentByXPATH(driver, "//div/table/tbody/tr/td/div", asgmntRspns);
         }*/

        //driver.findElement(By.xpath("//input[@name='formarking']")).click();
        //driver.findElement(By.name("formarking")).click();
        //driver.findElement(By.cssSelector("input[name=\"formarking\"]")).click();
        /*driver.findElement(By.xpath("//div[2]/input[6]")).click();
         Utility.actionBuilderClick(driver, "//div[2]/input[6]");
         Utility.clickByJavaScript(driver, "//div[2]/input[6]");*/
        //WebElement elmd = driver.findElement(By.xpath("//div[2]/input[6]"));
        //Utility.actionBuilderClick(driver, "//input[@name='formarking']");
        // org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driver);
        //builder.keyDown(Keys.CONTROL).click(elmd);

        /*int z = 1;
         while (true) {
         driver.findElements(By.tagName("input")).get(22).click();
         try {
         ip.isTextPresentByXPATH(driver, "//div[2]/span", "Are you sure you want to send this assignment "
         + "for marking? After submission, you will not be able to make any changes in any documents or text.", 15);
         break;
         } catch (TimeoutException e) {
         System.out.println("count: " + z);
         driver.navigate().refresh();
         ip.isTitlePresent(driver, "AutoCourse-DoNotTouch: Assignment: AutoAllInOneAsgnmnt");
         z++;
         }
         }*/
        //WebElement hiddenElement = driver.findElement(By.cssSelector("input[name=\"formarking\"]"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].click", hiddenElement);
        //((JavascriptExecutor) driver).executeScript("sendForMarking(this.form,'Es5rmlssXc')");

        /*
         * **** javascript:sendForMarking(this.form,'Es5rmlssXc');******
         * org.openqa.selenium.interactions.Actions builder = new
         * org.openqa.selenium.interactions.Actions(driver);
         builder.keyDown(Keys.CONTROL).moveToElement(elements.get(22)).click().keyUp(Keys.CONTROL).build().perform();
         */
        //WebElement element = driver.findElements(By.tagName("input")).get(22);        
        //((JavascriptExecutor) driver).executeScript("arguments[0].click", element);
        //((JavascriptExecutor) driver).executeScript("'input[name='formarking']').trigger('click')");
        //((JavascriptExecutor) driver).executeScript("//div[2]/input[6]').trigger('click')");
        //element.click();
        //element.sendKeys(Keys.ENTER);
        /*List<WebElement> allOptions = driver.findElements(By.tagName("input"));
         System.out.println("Total inputs: " + allOptions.size());
        
        
         click:
         for (WebElement option : allOptions) {
         //System.out.println(String.format("Value is: %s", option.getAttribute("value")));
         int i = 0;
         System.out.println("value of i:" + i);
         if (option.getAttribute("name").equalsIgnoreCase("formarking")) {
         System.out.println("Clicked");
         option.click();
         break click;
         }
         i++;
         }*/
        /*WebElement updateButton = driver.findElement(By.xpath("//input[@value='Send for marking']"));
         ((JavascriptExecutor) driver).executeScript(updateButton.getAttribute("onclick"));*/

        //************NOT TO BE DELETED AS OF NOW************//        
    }

    /**
     * Grade Assignment
     *
     * @param allInOneAsgnmntAvtvtyName
     */
    public void gradeAssignment(String allInOneAsgnmntAvtvtyName) {
        ip.isElementPresentContainsTextByXPATH(driver, allInOneAsgnmntAvtvtyName);

        int x = locateElement(allInOneAsgnmntAvtvtyName);
        int y = x + 1;
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/span", "1 of 1");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[4]/span", "0 of 1");
        driver.findElement(By.xpath("//tr[" + x + "]/td/span/a/span")).click();
        ip.isElementClickableByXpath(driver, "//tr[" + y + "]/td/div/table/tbody/tr[2]/td[3]/div/div/input", 60);
        driver.findElement(By.xpath("//tr[" + y + "]/td/div/table/tbody/tr[2]/td[3]/div/div/input")).clear();
        driver.findElement(By.xpath("//tr[" + y + "]/td/div/table/tbody/tr[2]/td[3]/div/div/input")).sendKeys("62");
        driver.findElement(By.xpath("//tr[" + y + "]/td/div/div/a")).click();
        Utility.waitForAlertToBeAccepted(driver, 60, "Your grading changes have been saved.");
        driver.findElement(By.xpath(xpv.getTokenValue("lnkLftPnlGradeXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngGradeXPATH"), "Grades");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[4]/span", "1 of 1");
    }

    /**
     * Verify Assignment Grade
     *
     * @param allInOneAsgnmntAvtvtyName
     */
    public void verifyAssignmentGrade(String allInOneAsgnmntAvtvtyName) {
        ip.isElementPresentContainsTextByXPATH(driver, allInOneAsgnmntAvtvtyName);
        int x = locateElement(allInOneAsgnmntAvtvtyName);
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[2]", "62/100 (62%)");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/div", "62/100 (62%)");
    }

    /**
     * Allow Assignment to be resubmitted
     *
     * @param allInOneAsgnmntAvtvtyName
     */
    public void allowResubmitAssignment(String allInOneAsgnmntAvtvtyName, String stdtUsrName) {
        ip.isElementPresentContainsTextByXPATH(driver, allInOneAsgnmntAvtvtyName);
        int x = locateElement(allInOneAsgnmntAvtvtyName);
        driver.findElement(By.xpath("//tr[" + x + "]/td/span/a/span")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Allow Resubmit")));
        driver.findElement(By.linkText("Allow Resubmit")).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtAlrtAlwResbmtAsgntXPATH"), "Do you want to allow " + stdtUsrName + " to resubmit this assignment?");
        driver.findElement(By.xpath(xpv.getTokenValue("btnCrfrmAlwResbmtAsgntXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtAlrtAlwResbmtAsgntXPATH"), "Allowed Resubmit to " + stdtUsrName + " and mail sent.");
        driver.findElement(By.xpath(xpv.getTokenValue("btnResbmtdAsgntXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("lnkLftPnlGradeXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngGradeXPATH"), "Grades");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/span", "0 of 1");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[4]/span", "0 of 1");
    }

    /**
     * Delete all Activities
     *
     * @param frmActvyName
     * @param quizActvtyName
     * @param allInOneAsgnmntAvtvtyName
     * @param pageActvtyName
     */
    public void deleteActivites(String... activities) {
        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        for (String activity : activities) {
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("imgDltActvtyXPATH"));
            Utility.clickByJavaScript(driver, xpv.getTokenValue("imgDltActvtyXPATH"));
            if (activity.contains("Forum")) {
                ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/p", "Are you absolutely sure you want to completely delete Forum '" + activity + "' ?");
            } else if (activity.contains("Quiz")) {
                ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/p", "Are you absolutely sure you want to completely delete Quiz '" + activity + "' ?");
            } else if (activity.contains("Asgnmnt")) {
                ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/p", "Are you absolutely sure you want to completely delete Assignment '" + activity + "' ?");
            } else {
                ip.isTextPresentByXPATH(driver, "//div[4]/div/div/div/p", "Are you absolutely sure you want to completely delete Page '" + activity + "' ?");
            }
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnCnfrmDltActvtyXPATH"));
            driver.findElement(By.xpath(xpv.getTokenValue("btnCnfrmDltActvtyXPATH"))).click();
        }
    }

    /**
     *
     * @param pswdQzName
     */
    public void generateQuizPassword(String pswdQzName) {
        driver.findElement(By.xpath("//*[starts-with(text(),'" + pswdQzName + "')]")).click();
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
    public String getAllInOneAssignmentActivityName() {
        return this.allInOneAsgnmntName;
    }

    /**
     * @return PageName
     */
    public String getPageActvyName() {
        return this.pageName;
    }

    /**
     * @return
     */
    public String getPswdQuizActivity() {
        return this.pswdQuizName;
    }

    /**
     * @return Glossary Activity Name
     */
    public String getGlossaryActvyName() {
        return this.glossaryName;
    }

    /**
     * @return
     */
    public String getGlossaryEntryName() {
        return this.glossaryEntryName;
    }

    /**
     * @return 
     */
    public String getGlossaryCategoryName() {
        return this.glossaryCategoryName;
    }
}
