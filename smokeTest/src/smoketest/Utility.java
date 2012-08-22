
package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Utility 
{
    public Utility()
    {
        
    }
    

    // Compares actual to expected page title to determine if Current Page is correct
    public static void myVerifyCurrentPage( WebDriver driver, String page )
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs(page));
        
    }
    
    // Uses js to click on hidden elements on the page
    public static void navigateToSubMenu( WebDriver driver, String menuXPATH )
    {
        WebElement hiddenElement = driver.findElement( By.xpath(menuXPATH) );
        ( (JavascriptExecutor)driver).executeScript("arguments[0].click()", hiddenElement );
    }

}
