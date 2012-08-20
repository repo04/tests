/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 *
 * @author somesh.bansal
 */
public class IsPresent {
    
    public void isTextPresentByCSS(WebDriver driver, String textPostCSS, String txtByCSS) throws Exception
    {           
        new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElement(By.cssSelector(textPostCSS), txtByCSS));        
    }
    
    public void isTextPresentByXpath(WebDriver driver, String headingTextPath, String txtByXPath) throws Exception
    {    
        new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElement(By.xpath(headingTextPath), txtByXPath));        
    }
    
    public void isElementPresentByLink(WebDriver driver, String elmntByLink) throws Exception
    {           
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.linkText(elmntByLink)));       
    }
    
    public void isElementPresentByXpath(WebDriver driver, String elmntByXPath) throws Exception
    {           
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'"+elmntByXPath+"')]")));	        
    }
    
    public void isElementPresentByID(WebDriver driver, String elmntByID) {
	new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.id(elmntByID)));		
    }  
    
}
