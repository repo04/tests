
package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WorkingGroup extends Page {
    Date now = new Date();
    IsPresent ip = new IsPresent();
    
    public WorkingGroup( WebDriver driver, AccountValues av ) {
        super( driver, av );
    }
    
    
}
