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
            switch (textPst) {
                case "txtCrsSctnPost":
                    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//td/div/img")));
                    driver.findElement(By.xpath("//td/div/img")).click();
                    driver.findElement(By.xpath("//div[11]/div/div[1]")).click();
                    break;
                case "txtAncmntPost":
                    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//td/div/img")));
                    driver.findElement(By.xpath("//td/div/img")).click();
                    driver.findElement(By.xpath("//div[11]/div/div[2]")).click();
                    ip.isTextPresentByXPATH(driver, "//div[11]/div/div/div/div", "Post Announcement - Settings");
                    dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElementValue(By.id("endtime-date"), dateFormat.format(now)));
                    driver.findElement(By.xpath("//div/fieldset/div/div/div/div/div/input")).click();
                    driver.findElement(By.xpath("//div[11]/div[2]/div[2]/div/div/div/div/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]")).click();
                    break;
                case "txtCrsPostCmntsOn":
                    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//td/div/img")));
                    driver.findElement(By.xpath("//td/div/img")).click();
                    driver.findElement(By.xpath("//div[11]/div/div[3]")).click();
                    ip.isTextPresentByXPATH(driver, "//label/span", "On - Starts a course level discussion");
                    driver.findElement(By.xpath("//fieldset/div/div/div/div/div/input")).click();
                    driver.findElement(By.xpath("//div[2]/div/div/div/div/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button")).click();
                    break;
                case "txtCrsPostCmntsOff":
                    new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//td/div/img")));
                    driver.findElement(By.xpath("//td/div/img")).click();
                    driver.findElement(By.xpath("//div[11]/div/div[3]")).click();
                    ip.isTextPresentByXPATH(driver, "//label/span", "On - Starts a course level discussion");
                    driver.findElement(By.xpath("//fieldset/div/div/div[2]/div/div/input")).click();
                    driver.findElement(By.xpath("//div[2]/div/div/div/div/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button")).click();
                    break;
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
     * Delete Post
     * 
     * @param tchrUrlCrsPost 
     */
    public void deleteCourseURLPost(String tchrUrlCrsPost) {
        ip.isTextPresentByXPATH(driver, "//li/div/div[4]/div/a", tchrUrlCrsPost);
        Utility.navigateToSubMenu(driver, "//li/div/div/a");                
        ip.isTextPresentByXPATH(driver, "//div/div/div[2]/span", "Are you sure you want to delete this post");
        driver.findElement(By.xpath("//div[2]/div/div/div/div/table/tbody/tr/td/table/tbody"
                + "/tr/td[2]/table/tbody/tr[2]/td[2]/em/button")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + tchrUrlCrsPost + "')]")));
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