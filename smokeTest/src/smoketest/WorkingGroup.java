
package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WorkingGroup extends Page {
    Date now = new Date();
    IsPresent ip = new IsPresent();
    private String wrkgGrpName;
    
    public WorkingGroup( WebDriver driver, AccountValues av ) {
        super( driver, av );
    }
    
    public void BuildWorkingGroup() {
        
        this.wrkgGrpName = "SmokeTest " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String srtName = "ShortName " +  DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        
        driver.findElement(By.xpath(av.getTokenValue("link2torAdminXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("linkAddWrkGrp"))).click();
        
        ip.isTextPresentByXPATH(driver, av.getTokenValue("headerCreateWrkGrp"), av.getTokenValue("txtCreateWrkGrp")  );
        
        driver.findElement(By.xpath(av.getTokenValue("fieldWrkgGrpName"))).sendKeys(this.wrkgGrpName);
        driver.findElement(By.xpath(av.getTokenValue("fieldWrkgGrpShrtName"))).sendKeys(srtName);
        driver.findElement(By.xpath(av.getTokenValue("fieldWrkgGrpAbout"))).sendKeys("this is a test");
        
        driver.findElement(By.xpath(av.getTokenValue("btnSbmtWrkgGrp"))).click();
        
        ip.isTextPresentByXPATH(driver, av.getTokenValue("headerLstWrkGrp"), av.getTokenValue("txtLstWrkGrp"));

        ip.isElementPresentByLINK(driver, wrkgGrpName);
        
    }
}
