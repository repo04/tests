
package simpleTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 * May be removing this class
 */
public class Button 
{
    WebDriver driver;
    String location;
    
    public Button( WebDriver driver, String location )
    {
        this.driver = driver;
        this.location = location;
    }
    
    public void buttonClick()
    {
        driver.findElement(By.xpath(location)).click();
    }
}
