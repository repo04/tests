package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SocialGroup extends Page {
    
    Date now = new Date();
    IsPresent ip = new IsPresent();
    private String grpName;
    
    public SocialGroup( WebDriver driver, AccountValues av ) {
        
        super( driver, av );
    }
    
    // Assumes user is at 'My Social Groups'
    public void buildSocialGroup() {
            
        this.grpName = "SmokeTest " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String srtName = "ShortName " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        
        driver.findElement(By.xpath(av.getTokenValue("linkStrtSclGrpXPATH")) ).click();
    
        ip.isElementPresentByID(driver, av.getTokenValue("sclGrpNameID"));
        
        driver.findElement(By.xpath(av.getTokenValue("fieldGrpNameXPATH"))).sendKeys(grpName);
        driver.findElement(By.xpath(av.getTokenValue("fieldSrtNameXPATH"))).sendKeys(srtName);
        driver.findElement(By.xpath(av.getTokenValue("fieldAbtGrpXPATH"))).sendKeys("About");
        driver.findElement(By.xpath(av.getTokenValue("fieldTopicXPATH"))).sendKeys("Topic");
        
        driver.findElement(By.xpath(av.getTokenValue("btnSbmtSclGrp"))).click();
        
       
        ip.isElementPresentByLink( driver, grpName );   // Verifies new Group
    }
    
    public void joinSocialGroup(String s) {
        
        driver.findElement(By.xpath(av.getTokenValue("btnFindSclGrp"))).click();
        ip.isTextPresentByXPATH( driver,  av.getTokenValue("headerAreaXPATH"), av.getTokenValue("headerTxtXPATH"));
        
        driver.findElement(By.xpath(av.getTokenValue("fieldGrpSrchXPATH"))).sendKeys(s);
        driver.findElement(By.xpath(av.getTokenValue("btnSbmtSrchGrp"))).click();;
        
        ip.isTextPresentByXPATH(driver, av.getTokenValue("txtSrchRsltsXPATH"), av.getTokenValue("txtSrchRslts"));
        
        //ip.isTextPresentByXPATH(driver, av.getTokenValue("linkFrstSrchRslt"), s);
        //driver.findElement(By.linkText("Join Now"));
        //driver.findElement(By.id(av.getTokenValue("linkJoinSclGrp"))).click();
        //driver.findElement(By.xpath(av.getTokenValue("btnYesJoinGrp"))).click();
    }
    
    public void leaveSocialGroup( String s ) {
        
    }
    
    public void removeSocialGroup( String s ) {
        
    }
    
    public String getSclGrpName() {
        return this.grpName;
    }
}
