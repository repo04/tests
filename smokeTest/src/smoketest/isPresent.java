/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.SeleneseTestBase;

/**
 *
 * @author somesh.bansal
 */
public class IsPresent extends SeleneseTestBase{
    
    public void isTextPresent(WebDriver driver, String textPosted, String wallCSS) throws Exception
    {           
        for (int second = 0;; second++) {
            if (second >= 60) fail("Did not find Text: "+ textPosted +" posted on wall");
            try { if (textPosted.equals(driver.findElement(By.cssSelector(wallCSS)).getText())) break; } catch (Exception e) {}
            Thread.sleep(1000);
	}
        
    }
    
    public void isElementPresent(WebDriver driver, String urlPosted) throws Exception
    {           
        for (int second = 0;; second++) {
            if (second >= 60) fail("Did not find URL: "+ urlPosted +" posted on wall");
            try { if (urlPosted.equals(driver.findElement(By.xpath("//a[contains(text(),'"+urlPosted+"')]")).getText())) break; } catch (Exception e) {}
            Thread.sleep(1000);
	}        
        
    }  
    
}
