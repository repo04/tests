
package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IsPresent {
    
    public void isTextPresentByCSS( WebDriver driver, String locationCSS, String actualText )
    {
        new WebDriverWait( driver, 10).until(ExpectedConditions.textToBePresentInElement(By.cssSelector(locationCSS), actualText));
    }
    
}
