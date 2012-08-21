package smoketest;

import java.util.Date;
import org.openqa.selenium.WebDriver;

public class SocialGroup extends Page {
    
    Date now = new Date();
    IsPresent ip;
    
    public SocialGroup( WebDriver driver, AccountValues av ) {
        
        super( driver, av );
    }
    
    
}
