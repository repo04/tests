package smoketest;

import com.thoughtworks.selenium.SeleneseTestBase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WallPage extends Page {

    // Used to include date & time in Wall Post
    Date now = new Date();
    WebElement textArea;
    WebElement btnWallShare;
    IsPresent ip = new IsPresent();
    String textPost = null;
    String urlPost = null;

    public WallPage(WebDriver driver, AccountValues av) {

        super(driver, av);
    }

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

        switch (textPst) {
            case "txtWallPost":
                this.textPost = av.getTokenValue(textPst) + "by" + user + " " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                break;
            case "txtCrsPost":
                this.textPost = av.getTokenValue(textPst) + "by" + user + " " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                break;
            case "txtSclGrpPost":
                this.textPost = av.getTokenValue(textPst) + "by" + user + " " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                break;
            case "txtWrkngGrpPost":
                this.textPost = av.getTokenValue(textPst) + "by" + user + " " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                break;
            case "txtPostOnStdtWall":
                this.textPost = av.getTokenValue(textPst) + "by" + user + " " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                break;
            default:
                SeleneseTestBase.fail("Not a valid TextPost " + textPst);            
        }


        editableTxtArea.sendKeys(textPost);

        driver.switchTo().defaultContent();  // Switches back to default focus
        btnWallShare.click();
        ip.isTextPresentByCSS(driver, av.getTokenValue("textWallCSS"), textPost);

    }

    public void urlPost(String urlPst) {

        setUpWallPost();
        DateFormat dateFormat;

        WebElement linkBtn = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("linkBtnXPATH"))));
        linkBtn.click();

        WebElement linkTextBox = driver.findElement(By.xpath(av.getTokenValue("linkTextBoxXPATH")));
        linkTextBox.clear();

        //Date need to be in specific format as Getinstance include special characters   
        dateFormat = new SimpleDateFormat("ddMMMyyHHmm");

        String user = LoginPage.getUser();

        switch (urlPst) {
            case "urlWallPost":
                this.urlPost = "http://" + av.getTokenValue(urlPst) + "by" + user + dateFormat.format(now) + ".com";
                break;
            case "urlCrsPost":
                this.urlPost = "http://" + av.getTokenValue(urlPst) + "by" + user + dateFormat.format(now) + ".com";
                break;
            case "urlSclGrpPost":
                this.urlPost = "http://" + av.getTokenValue(urlPst) + "by" + user + dateFormat.format(now) + ".com";
                break;
            case "urlWrkngGrpPost":
                this.urlPost = "http://" + av.getTokenValue(urlPst) + "by" + user + dateFormat.format(now) + ".com";
                break;
            case "urlPostOnStdtWall":
                this.urlPost = "http://" + av.getTokenValue(urlPst) + "by" + user + dateFormat.format(now) + ".com";
                break;
            default:
                SeleneseTestBase.fail("Not a valid URL Post " + urlPst);            
        }

        linkTextBox.sendKeys(urlPost);
        btnWallShare.click();
        ip.isElementPresentContainsTextByXPATH(driver, urlPost);

    }

    public void setUpWallPost() {
        textArea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("wallPublishPanelXPATH"))));
        textArea.click();
        btnWallShare = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnWallShareXPATH"))));
    }

    public String getTxtPost() {
        return this.textPost;
    }

    public String getURLPost() {
        return this.urlPost;
    }
}