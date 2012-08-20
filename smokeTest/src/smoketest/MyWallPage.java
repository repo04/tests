package smoketest;

import org.openqa.selenium.WebDriver;

public class MyWallPage extends Page {
    
    public MyWallPage( WebDriver driver, AccountValues av, String myWall )
    {
        super( driver, av );
        
        driver.get( av.getTokenValue(myWall) );
    }

}