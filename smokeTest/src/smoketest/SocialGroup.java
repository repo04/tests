package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SocialGroup extends Page {
    
    Date now = new Date();
    IsPresent ip;
    
    public SocialGroup( WebDriver driver, AccountValues av ) {
        
        super( driver, av );
    }
    
    // Assumes user is at 'My Social Groups'
    public void createSocialGroup() {
            
        String grpName = "SmokeTest " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        
        driver.findElement(By.xpath(av.getTokenValue("linkStrtSclGrpXPATH")) ).click();
        
        driver.findElement(By.xpath(av.getTokenValue("fieldGrpNameXPATH"))).sendKeys(grpName);
        driver.findElement(By.xpath(av.getTokenValue("fieldSrtNameXPATH"))).sendKeys("Short Name");
        driver.findElement(By.xpath(av.getTokenValue("fieldAbtGrpXPATH"))).sendKeys("About");
        driver.findElement(By.xpath(av.getTokenValue("fieldTopicXPATH"))).sendKeys("Topic");
        
        driver.findElement(By.xpath(av.getTokenValue("btnSbmtSclGrp"))).click();
        
        ip.isElementPresentByLink( driver, grpName );
    }
}
