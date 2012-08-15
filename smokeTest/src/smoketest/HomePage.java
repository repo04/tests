package smoketest;

import org.openqa.selenium.WebDriver;

public class HomePage 
{
    public HomePage( WebDriver driver, String pageTitle )
    {
        if( !pageTitle.equals(driver.getTitle()) )
        {
            throw new IllegalStateException( "Did not successfuly navigate to Home Page. "
                                           + " Current Page: " + driver.getTitle() );
        }
    }
}
