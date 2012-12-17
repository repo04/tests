package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

public class WallPage extends BaseClass {

    Date now = new Date();
    WebElement textArea;
    WebElement btnWallShare;
    WebElement linkBtn;
    String textPost = null;
    String urlPost = null;
    String cmntPost = null;
    DateFormat dateFormat;

    /**
     * Post Text on Wall
     *
     * @param textPst
     */
    public void textPost(String textPst) {

        setUpWallPost();
        linkBtn = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("linkBtnXPATH"))));
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        //Grabs the first iframe because the textArea is always the first iframe
        for (WebElement frame : iframes) {
            driver.switchTo().frame(frame.getAttribute("name"));
            break;
        }
        //Switch focus
        WebElement editableTxtArea = driver.switchTo().activeElement();
        String user = LoginPage.getUser();
        this.textPost = xpv.getTokenValue(textPst) + "by" + user.substring(0, 7) + " " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        editableTxtArea.sendKeys(textPost);

        //Switches back to default focus
        driver.switchTo().defaultContent();

        if (user.contains("Admin")) {
            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//td/div/img")));
            driver.findElement(By.xpath("//td/div/img")).click();

            switch (textPst) {
                case "txtCrsSctnPost":
                    driver.findElement(By.xpath("//div[11]/div/div[1]")).click();
                    break;
                case "txtAncmntCrsPost":
                    driver.findElement(By.xpath("//div[@id='ext-gen359']/div[2]")).click();
                    ip.isTextPresentByXPATH(driver, "//div[11]/div[2]/div/div/div/div/div/div/form/fieldset/div/div/div[2]/label",
                            "End Time:");
                    new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElementValue(By.id("endtime-date"), Utility.getCurrentNewYorkDate(driver)));
                    //((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", driver.findElement(By.id("endtime-date")), Utility.getNextNewYorkDate(driver, Utility.getCurrentNewYorkDate(driver)));
                    //new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElementValue(By.id("endtime-date"), Utility.getNextNewYorkDate(driver, Utility.getCurrentNewYorkDate(driver))));
                    driver.findElement(By.id("endtime-date")).click();
                    ip.isTextPresentByXPATH(driver, "//li/div/table/tbody/tr[3]/td", "Today");
                    driver.findElement(By.xpath("//tr[6]/td[7]/a")).click();
                    driver.findElement(By.id("audience_allcoursesections")).click();
                    driver.findElement(By.xpath("//div[11]/div[2]/div[2]/div/div/div/div/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]")).click();
                    break;
                case "txtCrsPostCmntsOn":
                    driver.findElement(By.xpath("//div[@id='ext-gen359']/div[3]")).click();
                    ip.isTextPresentByXPATH(driver, "//label/span", "On - Starts a course level discussion");
                    driver.findElement(By.xpath("//fieldset/div/div/div/div/div/input")).click();
                    driver.findElement(By.xpath("//div[2]/div/div/div/div/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button")).click();
                    break;
                case "txtCrsPostCmntsOff":
                    driver.findElement(By.xpath("//div[@id='ext-gen359']/div[3]")).click();
                    ip.isTextPresentByXPATH(driver, "//label/span", "On - Starts a course level discussion");
                    driver.findElement(By.xpath("//fieldset/div/div/div[2]/div/div/input")).click();
                    driver.findElement(By.xpath("//div[2]/div/div/div/div/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button")).click();
            }
        }
        btnWallShare.click();
        ip.isTextPresentByCSS(driver, xpv.getTokenValue("textWallCSS"), textPost);
    }

    /**
     * Post URL on Wall
     *
     * @param urlPst
     */
    public void urlPost(String urlPst) {

        setUpWallPost();
        linkBtn = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("linkBtnXPATH"))));
        DateFormat dateFormat;

        //Date need to be in specific format as Getinstance include special characters   
        dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
        String user = LoginPage.getUser();
        this.urlPost = xpv.getTokenValue(urlPst) + "by" + user.substring(0, 7) + dateFormat.format(now) + ".com";

        linkBtn.click();
        WebElement linkTextBox = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[3]")));

        int i = 1;
        value:
        while (i < 6) {
            Utility.actionBuilderClick(driver, "//div/input[3]");
            linkTextBox.sendKeys(urlPost);
            linkTextBox.clear();
            linkTextBox.sendKeys("http://" + urlPost);
            if (i < 5) {
                try {
                    new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//div/input[3]"), "http://" + urlPost));
                    break value;
                } catch (TimeoutException e) {
                    System.out.println("i: " + i);
                    i++;
                }
            } else {
                Utility.illegalStateException("Selenium is unable to get focus on URL Textboxfield after multiple tries also, this is an automation limitation");
            }
        }
        btnWallShare.click();
        ip.isElementPresentContainsTextByXPATH(driver, "http://" + urlPost);
    }

    /**
     * Comment on Teacher's Course post
     *
     * @param urlCrsPost
     * @param txtCmntOnTchrCrsPst
     */
    public void textCommentPost(String urlCrsPost, String txtCmntOnTchrCrsPst) {
        textArea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("wallPublishPanelXPATH"))));
        ip.isElementPresentContainsTextByXPATH(driver, "http://" + urlCrsPost);
        driver.findElement(By.xpath("//a/label")).click();
        WebElement cmntTxtArea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//li/div/div/div/textarea")));
        this.cmntPost = xpv.getTokenValue(txtCmntOnTchrCrsPst) + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        cmntTxtArea.click();
        cmntTxtArea.sendKeys(cmntPost);
        driver.findElement(By.xpath("//div[2]/a[2]")).click();
        ip.isTextPresentByXPATH(driver, "//div[3]/div[3]", cmntPost);
    }

    /**
     * Click on TextArea & enable share button
     */
    public void setUpWallPost() {
        textArea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("wallPublishPanelXPATH"))));
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Utility.actionBuilderClick(driver, xpv.getTokenValue("wallPublishPanelXPATH"));
        try {
            btnWallShare = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("btnWallShareXPATH"))));
        } catch (TimeoutException e) {
            Utility.illegalStateException("Selenium is unable to click on TextArea, this is an Automation Limitation");
        }
    }

    /**
     * Recommend URL Course Post
     *
     * @param tchrUrlCrsPost
     */
    public void recommendURLCoursePost(String tchrUrlCrsPost) {
        ip.isTextPresentByXPATH(driver, "//div[3]/div/a", "http://" + tchrUrlCrsPost);
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[2]/a")));
        driver.findElement(By.xpath("//label[2]/a")).click();
        ip.isTextPresentByXPATH(driver, "//label[3]", "(You Recommend This)");
    }

    /**
     * Delete Post / Announcement
     *
     * @param tchrUrlCrsPost
     */
    public void deletePost(String deletePost) {
        if (deletePost.contains("urlcrspost")) {
            ip.isTextPresentByXPATH(driver, "//li/div/div[4]/div/a", deletePost);
            Utility.navigateToSubMenu(driver, "//li/div/div/a");
            ip.isTextPresentByXPATH(driver, "//div/div/div[2]/span", "Are you sure you want to delete this post");
        } else {
            ip.isTextPresentByXPATH(driver, "//li/div/div[3]/div[3]", deletePost);
            Utility.navigateToSubMenu(driver, "//li/div/div/a[2]");
            ip.isTextPresentByXPATH(driver, "//div[*]/div[2]/div/div/div/div/div/div[2]/span", "Are you sure you want to delete this announcement");
        }
        driver.findElement(By.xpath("//div[2]/div/div/div/div/table/tbody/tr/td/table/tbody"
                + "/tr/td[2]/table/tbody/tr[2]/td[2]/em/button")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + deletePost + "')]")));
    }

    /**
     * Verify comment on Post
     *
     * @param stdtTxtCmntOnTchrCrsPost
     */
    public void verifyCommentOnPost(String stdtTxtCmntOnTchrCrsPost) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/label")));
        driver.findElement(By.xpath("//a/label")).click();
        ip.isTextPresentByXPATH(driver, "//li[2]/div/div[3]/div[3]", stdtTxtCmntOnTchrCrsPost);
    }

    /**
     * @return TxtPost
     */
    public String getTxtPost() {
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
        return this.cmntPost;
    }
}