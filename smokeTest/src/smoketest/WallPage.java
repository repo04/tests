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

    //@deprecated
    public void textPost() {

        /*setUpWallPost();

         List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

         // Grabs the first iframe because the textArea is always the first iframe
         for (WebElement frame : iframes) {
         driver.switchTo().frame(frame.getAttribute("name"));
         break;
         }
         // Switch focus
         WebElement editableTxtArea = driver.switchTo().activeElement();

         // Stores string that will post to wall. Date need to be in specific format as Getinstance include special characters   
         dateFormat = new SimpleDateFormat("ddMMMyy KKmma");
         now = new Date();
         String textPost = av.getTokenValue("txtPostOnWall") + " " + dateFormat.format(now);
         editableTxtArea.sendKeys(textPost);

         driver.switchTo().defaultContent();  // Switches back to default focus
         btnWallShare.click();
         ip.isTextPresentByCSS(driver, av.getTokenValue("textWallCSS"), textPost);*/
    }

    //@deprecated
    public void urlPost() {
        /*setUpWallPost();

         WebElement linkBtn = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("linkBtnXPATH"))));
         linkBtn.click();

         WebElement linkTextBox = driver.findElement(By.xpath(av.getTokenValue("linkTextBoxXPATH")));
         linkTextBox.clear();

         // Stores string that will post to wall. Date need to be in specific format as Getinstance include special characters   
         dateFormat = new SimpleDateFormat("ddMMMyyKKmma");
         now = new Date();
         String urlPost = av.getTokenValue("urlPostOnWall") + dateFormat.format(now) + ".com";
         linkTextBox.sendKeys(urlPost);
         btnWallShare.click();
         ip.isElementPresentContainsTextByXPATH(driver, urlPost);*/
    }

    //New Class
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
            default:
                SeleneseTestBase.fail("Not a valid TextPost " + textPst);
                break;
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
        dateFormat = new SimpleDateFormat("ddMMMyyKKmma");

        String user = LoginPage.getUser();

        switch (urlPst) {
            default:
                SeleneseTestBase.fail("Not a valid URL Post " + urlPst);
                break;
            case "urlWallPost":
                this.urlPost = "http://" + av.getTokenValue(urlPst) + "by" + user + dateFormat.format(now) + ".com";
                break;
            case "urlCrsPost":
                this.urlPost = "http://" + av.getTokenValue(urlPst) + "by" + user + dateFormat.format(now) + ".com";
                break;
            case "urlSclGrpPost":
                this.urlPost = "http://" + av.getTokenValue(urlPst) + "by" + user + dateFormat.format(now) + ".com";
                break;
            case "WrkngGrpPost":
                this.urlPost = "http://" + av.getTokenValue(urlPst) + "by" + user + dateFormat.format(now) + ".com";
                break;
            case "urlPostOnStdtWall":
                this.urlPost = "http://" + av.getTokenValue(urlPst) + "by" + user + dateFormat.format(now) + ".com";
                break;
        }

        linkTextBox.sendKeys(urlPost);
        btnWallShare.click();
        ip.isElementPresentContainsTextByXPATH(driver, urlPost);

    }

    public void setUpWallPost() {
        textArea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("wallPublishPanelXPATH"))));
        textArea.click();

        /*WebElement element = driver.findElement(By.id("wall_publisher_textarea_noedit"));
         JavascriptExecutor executor = (JavascriptExecutor) driver;
         executor.executeScript("arguments[0].click();", element);

         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("window.document.getElementById('wall_publisher_textarea_noedit').click()");*/
        btnWallShare = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnWallShareXPATH"))));
    }

    public String getTxtPost() {
        return this.textPost;
    }

    public String getURLPost() {
        return this.urlPost;
    }
}