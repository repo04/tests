/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

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

        ip.isElementPresentContainsTextByXPATH(driver, xpv.getTokenValue("lnkTrnEdtngOnTEXT"));
        driver.findElement(By.xpath("//*[contains(text(),'" + xpv.getTokenValue("lnkTrnEdtngOnTEXT") + "')]")).click();
        ip.isElementPresentContainsTextByXPATH(driver, quizActvtyName);
        Utility.navigateToSubMenu(driver, "//*[contains(text(),'" + quizActvtyName + "')]");
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
        driver.findElement(By.xpath(xpv.getTokenValue("fieldQzQstnNmXPATH"))).sendKeys("Capital");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldQzQstnTxtXPATH"))).sendKeys("New York City is the capital of the United States");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrrctAnsXPATH")))).selectByVisibleText("False");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSvQzQstnXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyQzQstnXPATH"), "New York City is the capital of the United States");
    }

    /**
     * User attempt to 'True/False' question in Quiz Assignment
     *
     * @param quizActvtyName
     */
    public void submitQuiz(String quizActvtyName) {
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
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtSbmtQzQstnXPATH"), "New York City is the capital of the United States");

        WebElement yesRadioButton = driver.findElement(By.xpath(xpv.getTokenValue("radioBtnFlsOptnXPATH")));
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
        driver.switchTo().window(HandleBefore);
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
         Utility.navigateToSubMenu(driver, "//div[2]/input[6]");*/
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
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[3]/span", "1 of 1");
        ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[4]/span", "0 of 1");
        driver.findElement(By.xpath("//tr[" + x + "]/td/span/a/span")).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("fieldGrdAsgntXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGrdAsgntXPATH"))).clear();
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGrdAsgntXPATH"))).sendKeys("62");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSaveGrdAsgntXPATH"))).click();
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
            Utility.navigateToSubMenu(driver, xpv.getTokenValue("imgDltActvtyXPATH"));
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
     * @param elementName
     * @return
     */
    private int locateElement(String elementName) {
        int x = 2;
        while (true) {
            try {
                ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td/a", elementName, 5);
                break;
            } catch (TimeoutException e) {
                System.out.println(elementName + " not present at x: " + x);
                x = x + 2;
            }
        }
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
}
