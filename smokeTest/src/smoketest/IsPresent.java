
package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IsPresent {
    
    // Appears it checks for the first instance of the CSS, starting from top of page working down.
    public void isTextPresentByCSS( WebDriver driver, String locationCSS, String actualText )
    {
        new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElement(By.cssSelector(locationCSS), actualText));
    }
    
    public void isTextPresentByXPATH( WebDriver driver, String xpath, String s ) {
        
        new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElement(By.xpath(xpath), s));
    }
    
    public void isElementPresentByXPATH( WebDriver driver, String elementByXPATH ) {
        
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'"+ elementByXPATH +"')]")));
 
    }
    
    public void isElementPresentByLink( WebDriver driver, String elementByLINK) {
        
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.linkText(elementByLINK)));
    }
    
    public void isElementPresentByID( WebDriver driver, String elementByID ) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id(elementByID)));
    }
    
    public void isElementPresentContainsTextByXPATH(WebDriver driver, String elmntByXPATH) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + elmntByXPATH + "')]")));
    }
    
    public void isElementPresentStartsWithTextByXPATH(WebDriver driver, String elmntByXPATH) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[starts-with(text(),'" + elmntByXPATH + "')]")));
    }
    
    public void isTitlePresent(WebDriver driver, String isTitle) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs(isTitle));        
    }
}

