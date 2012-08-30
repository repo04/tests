package smoketest;

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
        
        this.textPost = av.getTokenValue(textPst) + "by" + user + " " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        editableTxtArea.sendKeys(textPost);
        
        // Switches back to default focus
        driver.switchTo().defaultContent();  
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
        this.urlPost = "http://" + av.getTokenValue(urlPst) + "by" + user + dateFormat.format(now) + ".com";
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