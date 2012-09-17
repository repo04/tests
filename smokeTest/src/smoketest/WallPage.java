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
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

public class WallPage extends BaseClass {

    Date now = new Date();
    WebElement textArea;
    WebElement btnWallShare;
    String textPost = null;
    String urlPost = null;

    /**
     * Post Text on Wall
     *
     * @param textPst
     */
    public void textPost(String textPst) {

        setUpWallPost();
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        //Grabs the first iframe because the textArea is always the first iframe
        for (WebElement frame : iframes) {
            driver.switchTo().frame(frame.getAttribute("name"));
            break;
        }
        //Switch focus
        WebElement editableTxtArea = driver.switchTo().activeElement();
        String user = LoginPage.getUser();
        this.textPost = av.getTokenValue(textPst) + "by" + user.substring(0, 7) + " " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        editableTxtArea.sendKeys(textPost);

        //Switches back to default focus
        driver.switchTo().defaultContent();
        btnWallShare.click();
        ip.isTextPresentByCSS(driver, av.getTokenValue("textWallCSS"), textPost);

    }

    /**
     * Post URL on Wall
     *
     * @param urlPst
     */
    public void urlPost(String urlPst) {

        setUpWallPost();
        DateFormat dateFormat;

        //Date need to be in specific format as Getinstance include special characters   
        dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
        String user = LoginPage.getUser();
        this.urlPost = av.getTokenValue(urlPst) + "by" + user.substring(0, 7) + dateFormat.format(now) + ".com";

        WebElement linkBtn = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("linkBtnXPATH"))));
        linkBtn.click();
        WebElement linkTextBox = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[3]")));
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driver);
                        
        int i = 1;
        value:
        while (i < 6) {
            builder.click(linkTextBox).perform();
            linkTextBox.sendKeys(urlPost);
            if (i < 5) {
                try {
                    new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//div/input[3]"), "http://" + urlPost));
                    break value;
                } catch (TimeoutException e) {
                    System.out.println("i: " + i);
                    i++;
                }
            }
            else{
                Utility.illegalStateException("Unable to get focus on URL Textboxfield after multiple tries also");
            }
        }
        btnWallShare.click();
        ip.isElementPresentContainsTextByXPATH(driver, "http://" + urlPost);
    }

    /**
     * Click on TextArea & enable share button
     */
    public void setUpWallPost() {
        textArea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("wallPublishPanelXPATH"))));
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        textArea.sendKeys(Keys.ENTER);
        btnWallShare = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnWallShareXPATH"))));
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
}