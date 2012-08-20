package smoketest;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WallPage extends Page {
    
    public WallPage( WebDriver driver, AccountValues av )
    {
        super( driver, av );
    }

    public void textPost() {
 
        Date now = new Date();  // Used to include date / time in Wall Post for traceability
        
        // Waits 10 seconds for Wall Publishers Panel to become clickable
        WebElement textArea = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("wallPublishPanelXPATH"))));
        textArea.click();
   
        // Share button that submits post to wall
        WebElement btnWallShare = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnWallShareXPATH"))));
        List<WebElement> iframes = driver.findElements( By.tagName("iframe") ); 
        
        // Grabs the first iframe because the textArea is always the first iframe
        for( WebElement frame : iframes )
        {
            driver.switchTo().frame( frame.getAttribute("name") );
            break;
        }
        
        // Switch focus
        WebElement editableTxtArea = driver.switchTo().activeElement();
        
        // Stores string that will post to wall, including date in this format: MM/D/Yr  H:XX XM
        String textPost = av.getTokenValue("txtPostOnWall") + " " + DateFormat.getInstance().format(now);        
        editableTxtArea.sendKeys(textPost);
        
        driver.switchTo().defaultContent();  // Switches back to default focus
        btnWallShare.click();
        IsPresent ip = new IsPresent();
        
        // Verifies string / text is posted on wall, verified with time stamp
        ip.isTextPresentByCSS(driver, av.getTokenValue("textWallCSS"), textPost);  
    }
    
    public void urlPost()
    {
        Date now = new Date();
        
        WebElement textArea = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("wallPublishPanelXPATH"))));
        textArea.click();
        
        WebElement linkBtn = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("linkBtnXPATH"))));
        linkBtn.click();
        
        
        
    }
    
    public void setUpWallPost()
    {
        Date now = new Date();
        
        WebElement textArea = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("wallPublishPanelXPATH"))));
        textArea.click(); 
    }
}