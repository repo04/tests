/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import runThrghTestNG.BaseClass;

public class Activity extends BaseClass {

    Date now = new Date();
    private String questionTitle, question, ans;
    private String name;
    private String intro;
    private String dateAndTime;
    StackTraceElement[] stackTraceElements;
    Actions a = new Actions();
    List<String> reportLabels = Arrays.asList("Info", "Overview & Regrade", "Manual Grading",
            "Item Analysis", "Preview");
    List<String> contentQuizAdministrationLinks = Arrays.asList("Edit settings", "Group overrides",
            "User overrides", "Edit quiz", "Preview", "Logs");
    List<String> pesQuizAdministrationLinks = Arrays.asList("Group overrides", "User overrides",
            "Preview", "Locally assigned roles", "Permissions", "Check permissions", "Logs");

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
        ip.isTextPresentByXPATH(driver, "//div[3]/div/div[2]", "Quiz Reports");
        int x = 1;
        for (String reportLabel : reportLabels) {
            ip.isTextPresentByXPATH(driver, "//div[3]/ul/li[" + x + "]/a/span", reportLabel);
            x++;
        }
        ip.invisibilityOfElementByXpathWithText(driver, "//div[3]/ul/li[6]/a/span", "Edit + Release");
        verifyQuizAdministrationLinksPage(contentQuizAdministrationLinks, name);
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
        String lateSubmission = new Select(driver.findElement(By.xpath("//select[@id='id_preventlate']"))).getFirstSelectedOption().getText();
        if (!lateSubmission.equalsIgnoreCase("No")) {
            Utility.illegalStateException("All in one assignment's prevent late submission value differs, "
                    + "expected: 'No' but actual: '" + lateSubmission + "'");
        }
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngActvtyTextXPATH"), this.intro);
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

        int y = 1;
        for (String reportLabel : reportLabels) {
            ip.invisibilityOfElementByXpathWithText(driver, "//div[3]/ul/li[" + y + "]/a/span", reportLabel);
            y++;
        }
        ip.invisibilityOfElementByXpathWithText(driver, "//div[3]/ul/li[6]/a/span", "Edit + Release");

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
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngGradeXPATH"), "Grades");
        int x = locateElement(quizActivityName);
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[2]", "(100%)");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/div", "(100%)");
    }

    /**
     * Submit Assignment
     *
     * @param allInOneAssignmentActivityName
     */
    public void submitAssignment(String allInOneAssignmentActivityName) {
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
        String asgmntRspns = "asgmntRspns" + dateFormat.format(now);
        driver.findElement(By.xpath("//*[starts-with(text(),'" + allInOneAssignmentActivityName + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnSbmtAsgnmntXPATH"));

        //Until Jira Ticket 'LMSII-2827' is resolved
        switch (program) {
            case "unc-mpa":
            case "wu-llm":
                ip.isElementPresentByXPATH(driver, "//input[@type='image']");
                break;
            default:
                new WebDriverWait(driver, 60).until(ExpectedConditions.
                        presenceOfElementLocated(By.cssSelector("img[alt=\"You must submit this assignment to mark it complete.\"]")));
        }
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmtAsgnmntXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblVrfyRspsXPATH"), "Write a Response");

        String HandleBefore = driver.getWindowHandle();

        //'Send for Marking' button with onclick attribute is not clicked by Selenium CLICK command for Chrome Browser
        //Robot code provides the work around to perform the operation. 
        //Hence Exception is thrown incase button is not clicked after multiple (5) tries
        int i = 1;
        end:
        while (i < 6) {
            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Font family")));
            Utility.typeInContentEditableIframe(driver, 1, asgmntRspns);

            List<WebElement> elements = driver.findElements(By.tagName("input"));
            System.out.println("Total inputs: " + elements.size());
            Utility.robotclick(elements.get(22));
            if (i < 5) {
                if (browser.equalsIgnoreCase("chrome")) {
                    try {
                        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyAsgntMrkngXPATH"), "Are you sure you want to send this assignment "
                                + "for marking? After submission, you will not be able to make any changes in any documents or text.", 30);
                        break end;
                    } catch (TimeoutException e) {
                        System.out.println("count: " + i);
                        driver.navigate().refresh();
                        ip.isTitleContains(driver, "Assignment: " + allInOneAssignmentActivityName);
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

        //Until Jira Ticket 'LMSII-2827' is resolved
        switch (program) {
            case "unc-mpa":
            case "wu-llm":
                ip.isElementPresentByXPATH(driver, "//input[@type='image']");
                break;
            default:
                new WebDriverWait(driver, 60).until(ExpectedConditions.
                        presenceOfElementLocated(By.cssSelector("img[alt=\"Completed\"]")));
        }
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
     * @param allInOneAssignmentActivityName
     */
    public void gradeAssignment(String allInOneAssignmentActivityName) {
        ip.isElementPresentContainsTextByXPATH(driver, allInOneAssignmentActivityName);

        int x = locateElement(allInOneAssignmentActivityName);
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
        driver.findElement(By.xpath("//tr[" + x + "]/td/a")).click();
        ip.isElementPresentByLINK(driver, "View 1 submitted assignments");
    }

    /**
     * Verify Assignment Grade
     *
     * @param allInOneAssignmentActivityName
     */
    public void verifyAssignmentGrade(String allInOneAssignmentActivityName) {
        ip.isElementPresentContainsTextByXPATH(driver, allInOneAssignmentActivityName);
        int x = locateElement(allInOneAssignmentActivityName);
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[2]", "62/100 (62%)");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/div", "62/100 (62%)");
    }

    /**
     * Allow Assignment to be resubmitted
     *
     * @param allInOneAssignmentActivityName
     */
    public void allowResubmitAssignment(String allInOneAssignmentActivityName, String studentUserName) {
        ip.isElementPresentContainsTextByXPATH(driver, allInOneAssignmentActivityName);
        int x = locateElement(allInOneAssignmentActivityName);
        driver.findElement(By.xpath("//tr[" + x + "]/td/span/a/span")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Allow Resubmit")));
        driver.findElement(By.linkText("Allow Resubmit")).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtAlrtAlwResbmtAsgntXPATH"), "Do you want to allow " + studentUserName + " to resubmit this assignment?");
        driver.findElement(By.xpath(xpv.getTokenValue("btnCrfrmAlwResbmtAsgntXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtAlrtAlwResbmtAsgntXPATH"), "Allowed Resubmit to " + studentUserName + " and mail sent.");
        driver.findElement(By.xpath(xpv.getTokenValue("btnResbmtdAsgntXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("lnkLftPnlGradeXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngGradeXPATH"), "Grades");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/span", "0 of 1");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[4]/span", "0 of 1");
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
     * Verify UI of Quiz page
     * 
     * @param quizActivityName
     */
    public void verifyQuizUIPage(String quizActivityName) {
        int x = 1;
        for (String reportLabel : reportLabels) {
            ip.isTextPresentByXPATH(driver, "//div[3]/ul/li[" + x + "]/a/span", reportLabel);
            driver.findElement(By.xpath("//div[3]/ul/li[" + x + "]/a/span")).click();
            switch (reportLabel) {
                case "Info":
                    if (LoginPage.getUser().contains("pes")) {
                        ip.isTextPresentByXPATH(driver, "//div[4]/div[4]/div/div[2]/p", "Grading method: Highest grade");
                    } else {
                        ip.isTextPresentByXPATH(driver, "//div[2]/p", "Grading method: Highest grade");
                    }
                    break;
                case "Overview & Regrade":
                    ip.isTextPresentByXPATH(driver, "//div[4]/p", "Showing graded and ungraded attempts for each user. "
                            + "The one attempt for each user that is graded is highlighted. "
                            + "The grading method for this quiz is Highest grade.");
                    break;
                case "Manual Grading":
                    if (LoginPage.getUser().contains("pes")) {
                        ip.isTextPresentByXPATH(driver, "//div[4]/div/h2", "Questions that need grading");
                    } else {
                        ip.isTextPresentByXPATH(driver, "//h2", "Questions that need grading");
                    }
                    break;
                case "Item Analysis":
                    if (LoginPage.getUser().contains("pes")) {
                        ip.isTextPresentByXPATH(driver, "//div[4]/div/h2", "Quiz information");
                    } else {
                        ip.isTextPresentByXPATH(driver, "//h2", "Quiz information");
                    }
                    break;
                case "Preview":
                    if (LoginPage.getUser().contains("pes")) {
                        ip.isTextPresentByXPATH(driver, "//div[4]/div/h2", quizActivityName);
                    } else {
                        ip.isTextPresentByXPATH(driver, "//h2", quizActivityName);
                    }
            }
            x++;
        }
        if (LoginPage.getUser().contains("pes")) {
            ip.isTextPresentByXPATH(driver, "//div[3]/ul/li[6]/a/span", "Edit + Release");
            driver.findElement(By.xpath("//div[3]/ul/li[6]/a/span")).click();
            ip.isTextPresentByXPATH(driver, "//div[4]/div/h2", "Edit + Release: " + quizActivityName);
            ip.isElementPresentByXPATH(driver, "//span/a");
            ip.isElementPresentByXPATH(driver, "//span/span/a");
            ip.isElementPresentByXPATH(driver, "//td[5]/input");
            String mainWindow = driver.getWindowHandle();
            driver.findElement(By.xpath("//span/a/img")).click();
            Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);
            int i = 1;
            for (String handle : driver.getWindowHandles()) {
                System.out.println("window handle: " + handle);
                driver.switchTo().window(handle);
                if (i == driver.getWindowHandles().size()) {
                    try {
                        ip.isTitleContains(driver, "Preview question: Capital");
                        ip.isTextPresentByXPATH(driver, "//div[2]/div/div", "New York City is the capital of the United States");
                        driver.close();
                    } catch (Exception e) {
                        System.out.println("Preview question: Captal" + " not found");
                        driver.close();
                        driver.switchTo().window(mainWindow);
                        throw e;
                    }
                }
                i++;
            }
            driver.switchTo().window(mainWindow);
            verifyQuizAdministrationLinksPage(pesQuizAdministrationLinks, quizActivityName);
        } else {
            ip.invisibilityOfElementByXpathWithText(driver, "//div[3]/ul/li[6]/a/span", "Edit + Release");
        }
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

    private void verifyQuizAdministrationLinksPage(List<String> quizAdministrationLinks, String quizName) {
        for (String quizAdministrationLink : quizAdministrationLinks) {
            ip.isElementPresentByLINK(driver, quizAdministrationLink);
            driver.findElement(By.linkText(quizAdministrationLink)).click();

            switch (quizAdministrationLink) {
                case "Edit settings":
                    ip.isElementClickableByXpath(driver, "//input[@id='id_name']", 60);
                    break;
                case "Group overrides":
                    ip.isElementPresentByXPATH(driver, "//input[@value='Add group override']");
                    break;
                case "User overrides":
                    ip.isElementPresentByXPATH(driver, "//input[@value='Add user override']");
                    break;
                case "Edit quiz":
                    ip.isElementPresentByXPATH(driver, "//div[4]/div/div/ul/li/a/span");
                    break;
                case "Preview":
                    //LMSII-3372
                    break;
                case "Logs":
                    ip.isElementClickableByXpath(driver, "//select[@id='menumodid']", 60);
                    Assert.assertEquals(quizName,
                            new Select(driver.findElement(By.xpath("//select[@id='menumodid']"))).getFirstSelectedOption().getText());
                    ip.isElementPresentByXPATH(driver, "//input[@value='Get these logs']");
                    break;
                case "Locally assigned roles":
                    ip.isTextPresentByXPATH(driver, "//div[4]/div/h2", "Assign roles in Quiz: " + quizName);
                    break;
                case "Permissions":
                    ip.isTextPresentByXPATH(driver, "//div[4]/div/h2", "Permissions in Quiz: " + quizName);
                    break;
                case "Check permissions":
                    ip.isTextPresentByXPATH(driver, "//div[3]/div/h2", "Check permissions in Quiz: " + quizName);
            }
        }
    }

    /**
     *
     * @return
     */
    public String getActivityName() {
        return this.name;
    }
}
