package com.lms.tests.smoketest;

import com.lms.tests.runThrghTestNG.BaseClass;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WallPage extends BaseClass {

    Date now = new Date();
    WebElement textArea;
    WebElement buttonWallShare;
    WebElement linkButton;
    String textPost = null;
    String urlPost = null;
    String commentPost = null;
    DateFormat dateFormat;

    /**
     * Post Text on Wall
     *
     * @param textPost
     */
    public void textPost(String textPost) {
        setUpWallPost();
        linkButton = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("linkBtnXPATH"))));
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        //Grabs the first iframe because the textArea is always the first iframe
        for (WebElement frame : iframes) {
            driver.switchTo().frame(frame.getAttribute("name"));
            break;
        }
        //Switch focus
        WebElement editableTxtArea = driver.switchTo().activeElement();
        String user = LoginPage.getUser();
        if (!textPost.contains("HTML")) {
            this.textPost = xpv.getTokenValue(textPost) + "by" + user + " "
                    + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.textPost = xpv.getTokenValue(textPost + "1") + "by" + user + " "
                    + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now) + xpv.getTokenValue(textPost + "2");
        }
        editableTxtArea.sendKeys(this.textPost);

        //Switches back to default focus
        driver.switchTo().defaultContent();

        if (user.contains("Admin")) {
            ip.isElementClickableByXpath(driver, "//td/div/img", 60);
            driver.findElement(By.xpath("//td/div/img")).click();

            switch (textPost) {
                case "txtCrsSctnPost":
                    driver.findElement(By.xpath("//div[11]/div/div[1]")).click();
                    break;
                case "txtAncmntCrsPost":
                    driver.findElement(By.xpath("//div[11]/div/div[2]")).click();
                    ip.isTextPresentByXPATH(driver, "//div[11]/div[2]/div/div/div/div/div/div/form/fieldset/div/div/div[2]/label",
                            "End Time:");
                    Utility.verifyDatePresentInElementValue(driver, By.id("endtime-date"));
                    driver.findElement(By.id("endtime-date")).click();
                    ip.isTextPresentByXPATH(driver, "//li/div/table/tbody/tr[3]/td", "Today");
                    driver.findElement(By.xpath("//td/table/tbody/tr[6]/td[7]/a")).click();
                    driver.findElement(By.id("audience_allcoursesections")).click();
                    driver.findElement(By.xpath("//div[11]/div[2]/div[2]/div/div/div/div/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]")).click();
                    break;
                case "txtCrsPostCmntsOn":
                    driver.findElement(By.xpath("//div[11]/div/div[3]")).click();
                    ip.isTextPresentByXPATH(driver, "//label/span", "On - Starts a course level discussion");
                    driver.findElement(By.xpath("//fieldset/div/div/div/div/div/input")).click();
                    driver.findElement(By.xpath("//div[2]/div/div/div/div/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button")).click();
                    break;
                case "txtCrsPostCmntsOff":
                    driver.findElement(By.xpath("//div[11]/div/div[3]")).click();
                    ip.isTextPresentByXPATH(driver, "//label/span", "On - Starts a course level discussion");
                    driver.findElement(By.xpath("//fieldset/div/div/div[2]/div/div/input")).click();
                    driver.findElement(By.xpath("//div[2]/div/div/div/div/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button")).click();
            }
        } else {
            new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.id("sharetype")));
        }
        buttonWallShare.click();
        ip.isTextPresentByCSS(driver, xpv.getTokenValue("textWallCSS"), this.textPost);
    }

    /**
     * Post URL on Wall
     *
     * @param urlPost
     */
    public void urlPost(String urlPost) {
        setUpWallPost();
        linkButton = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("linkBtnXPATH"))));
        DateFormat dateFormat;

        //Date need to be in specific format as Getinstance include special characters   
        dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
        String user = LoginPage.getUser();
        this.urlPost = xpv.getTokenValue(urlPost) + "by" + user + dateFormat.format(now) + ".com";

        linkButton.click();
        WebElement linkTextBox = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[3]")));

        int i = 1;
        value:
        while (i < 6) {
            Utility.actionBuilderClick(driver, "//div/input[3]");
            linkTextBox.sendKeys(this.urlPost);
            linkTextBox.clear();
            linkTextBox.sendKeys("http://" + this.urlPost);
            if (i < 5) {
                try {
                    new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//div/input[3]"), "http://" + this.urlPost));
                    break value;
                } catch (TimeoutException e) {
                    System.out.println("i: " + i);
                    i++;
                }
            } else {
                Utility.illegalStateException("Selenium is unable to get focus on URL Textboxfield after multiple tries also, this is an automation limitation");
            }
        }
        buttonWallShare.click();
        ip.isElementPresentContainsTextByXPATH(driver, "http://" + this.urlPost);
        if (urlPost.contains("urlCrsPost")) {
            String mainWindow = driver.getWindowHandle();
            driver.findElement(By.linkText("http://" + this.urlPost)).click();
            Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);
            int z = 1;
            for (String handle : driver.getWindowHandles()) {
                System.out.println("window handle: " + handle);
                driver.switchTo().window(handle);
                if (z > 1) {
                    driver.close();
                }
                z++;
            }
            driver.switchTo().window(mainWindow);
        }
    }

    /**
     * Comment on Teacher's Course post
     *
     * @param urlCoursePost
     * @param txtCmntOnTchrCrsPst
     */
    public void textCommentPost(String urlCoursePost, String txtCmntOnTchrCrsPst) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("wallPublishPanelXPATH"))));
        ip.isElementPresentContainsTextByXPATH(driver, "http://" + urlCoursePost);
        driver.findElement(By.xpath("//a/label")).click();
        WebElement cmntTxtArea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//li/div/div/div/textarea")));
        this.commentPost = xpv.getTokenValue(txtCmntOnTchrCrsPst) + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        cmntTxtArea.click();
        cmntTxtArea.sendKeys(this.commentPost);
        driver.findElement(By.xpath("//div[2]/a[2]")).click();
        ip.isTextPresentByXPATH(driver, "//div[3]/div[3]", this.commentPost);
    }

    /**
     * Click on TextArea, Enable share button & verify WYSIWYGEditor TestArea
     */
    public void setUpWallPost() {
        textArea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("wallPublishPanelXPATH"))));
        Utility.actionBuilderClick(driver, xpv.getTokenValue("wallPublishPanelXPATH"));
        try {
            buttonWallShare = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("btnWallShareXPATH"))));
        } catch (TimeoutException e) {
            Utility.illegalStateException("Selenium is unable to click on TextArea, this is an Automation Limitation");
        }
        verifyWYSIWYGEditor();
    }

    /**
     * Recommend URL Course Post
     *
     * @param teacherUrlCoursePost
     */
    public void recommendURLCoursePost(String teacherUrlCoursePost) {
        ip.isTextPresentByXPATH(driver, "//div[3]/div/a", "http://" + teacherUrlCoursePost);
        driver.findElement(By.cssSelector("span.icon.like"));
        ip.isElementClickableByXpath(driver, "//label[2]/a", 60);
        driver.findElement(By.xpath("//label[2]/a")).click();
        ip.isTextPresentByXPATH(driver, "//label[3]", "(You Recommend This)");
    }

    /**
     * Delete post
     *
     * @param post
     */
    public void deletePost(String post) {
        WebElement postElement = null;
        String path;
        if (post.contains("urlcoursepost")) {
            path = "//li/div/div[4]/div/a";
            ip.isTextPresentByXPATH(driver, path, post);
            postElement = driver.findElement(By.xpath("//*[contains(text(),'" + post + "')]"));
            Utility.clickByJavaScript(driver, "//li/div/div/a");
            ip.isTextPresentByXPATH(driver, "//div/div/div/div/div/div[2]/span", "Are you sure you want to delete this post");
        } else if (post.contains("urlstudentworkinggrouppost")) {
            path = "//li/div/div[4]/div/a";
            ip.isTextPresentByXPATH(driver, path, post);
            postElement = driver.findElement(By.xpath("//*[contains(text(),'" + post + "')]"));
            Utility.clickByJavaScript(driver, "//li/div/div/a");
            ip.isTextPresentByXPATH(driver, "//div[14]/div[2]/div/div/div/div/div/div[2]/span", "Are you sure you want to delete this post");
        } else {
            path = "//li/div/div[3]/div[3]";
            ip.isTextPresentByXPATH(driver, path, post);
            postElement = driver.findElement(By.xpath("//*[contains(text(),'" + post + "')]"));
            Utility.clickByJavaScript(driver, "//li/div/div/a[2]");
            ip.isTextPresentByXPATH(driver, "//div[*]/div[2]/div/div/div/div/div/div[2]/span", "Are you sure you want to delete this announcement");
        }
        driver.findElement(By.xpath("//div[2]/div/div/div/div/table/tbody/tr/td/table/tbody"
                + "/tr/td[2]/table/tbody/tr[2]/td[2]/em/button")).click();

        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.stalenessOf(postElement));
        } catch (Exception e) {
            //Do nothing
        }
        driver.navigate().refresh();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("wallPublishPanelXPATH"), 60);
        try {
            ip.isTextPresentByXPATH(driver, path, post, 10);
            Utility.illegalStateException("Unable to delete post: " + post);
        } catch (TimeoutException e) {
            //Do Nothing
        }
    }

    /**
     * Verify comment on Post
     *
     * @param studentTextCommentOnTeacherCoursePost
     */
    public void verifyCommentOnPost(String studentTextCommentOnTeacherCoursePost) {
        ip.isElementClickableByXpath(driver, "//a/label", 60);
        driver.findElement(By.xpath("//a/label")).click();
        ip.isTextPresentByXPATH(driver, "//li[2]/div/div[3]/div[3]", studentTextCommentOnTeacherCoursePost);
    }

    /**
     * Verify WYSIWYG Editor
     */
    private void verifyWYSIWYGEditor() {
        ip.isElementPresentByXPATH(driver, "//select");
        for (int x = 3; x < 19; x++) {
            if (x != 6 && x != 9 && x != 12 && x != 16) {
                ip.isElementPresentByXPATH(driver, "//td[" + x + "]/table/tbody/tr[2]/td[2]/em/button");
            }
        }
    }

    /**
     * @return TxtPost
     */
    public String getTextPost() {
        return this.textPost;
    }

    /**
     * @return URLPost
     */
    public String getURLPost() {
        return this.urlPost;
    }

    /**
     * @return CommentOnTchrCrsPost
     */
    public String getTextCommentPost() {
        return this.commentPost;
    }
}