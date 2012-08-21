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
    DateFormat dateFormat;
    Date now;
    WebElement textArea;
    WebElement btnWallShare;
    IsPresent ip = new IsPresent();
    
    public WallPage( WebDriver driver, AccountValues av ) {
        
        super( driver, av );
    }

    public void textPost() {

        setUpWallPost();

        List<WebElement> iframes = driver.findElements( By.tagName("iframe") ); 
        
        // Grabs the first iframe because the textArea is always the first iframe
        for( WebElement frame : iframes )
        {
            driver.switchTo().frame( frame.getAttribute("name") );
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
        ip.isTextPresentByCSS(driver, av.getTokenValue("textWallCSS"), textPost);        
    }
    
    public void urlPost()
    {
        setUpWallPost();  
        
        WebElement linkBtn = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("linkBtnXPATH"))));
        linkBtn.click();
        
        WebElement linkTextBox = driver.findElement(By.xpath(av.getTokenValue("linkTextBoxXPATH")));
        linkTextBox.clear();
        
        // Stores string that will post to wall. Date need to be in specific format as Getinstance include special characters   
        dateFormat = new SimpleDateFormat("ddMMMyyKKmma");
		now = new Date();
        String urlPost = av.getTokenValue( "urlPostOnWall") + dateFormat.format(now) + ".com";
        linkTextBox.sendKeys( urlPost );
        btnWallShare.click();
        ip.isElementPresentContainsTextByXPATH( driver, urlPost );
                
    }
    
    public void setUpWallPost()
    {
        textArea = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("wallPublishPanelXPATH"))));
        textArea.click();
        btnWallShare = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnWallShareXPATH"))));
    }
}