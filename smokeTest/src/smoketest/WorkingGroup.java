
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
        String srtName = "ShoretName " +  DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        
        driver.findElement(By.xpath(av.getTokenValue("link2torAdminXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("linkAddWrkGrp"))).click();
        
        ip.isTextPresentByXPATH(driver, av.getTokenValue("headerCreateWrkGrp"), av.getTokenValue("txtCreateWrkGrp")  );
    }
}
