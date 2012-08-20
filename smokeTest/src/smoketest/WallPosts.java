/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 *
 * @author somesh.bansal
 */
public class WallPosts extends Page {
    
    public WallPosts(WebDriver driver, AccountValues av) {
		super(driver, av);
	}

    public void textPost() throws Exception
    {  
      	WebElement txtarea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id(av.getTokenValue("txtareaID"))));
        
        //Click on textarea
        Utility.myButtonClick(txtarea);
        
        //Post URL
        WebElement btnWallShare = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnWallShareID"))));
                
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		
		for (WebElement frame: iframes)
		{
			driver.switchTo().frame(frame.getAttribute("name"));
			break;
		}
                
        WebElement editable = driver.switchTo().activeElement();
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("mmss");	
    	Date date = new Date();
        String txtPost=av.getTokenValue("txtPostOnWall")+dateFormat.format(date);
        Utility.mySendKeys(editable, txtPost);
        driver.switchTo().defaultContent();
        Utility.myButtonClick(btnWallShare);
        
        //Verify Text is Posted or not
        IsPresent ip = new IsPresent();
        ip.isTextPresentByCSS(driver, av.getTokenValue("textWallCSS"), txtPost);                
    }
    
    public void urlPost() throws Exception
    {
    	WebElement txtarea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id(av.getTokenValue("txtareaID"))));
        
        //Click on Textarea 
        Utility.myButtonClick(txtarea);
        WebElement linkBtn = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("linkBtnXPath"))));
        Utility.myButtonClick(linkBtn);        
        
        //Post URL
        WebElement linkTextBox = driver.findElement(By.id(av.getTokenValue("linkTextBoxID")));
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("mmss");	
    	Date date = new Date();
    	String urlPost=av.getTokenValue("urlPostOnWall")+dateFormat.format(date)+".com";
        Utility.mySendKeys(linkTextBox, urlPost);
        
        //Share on Wall
        WebElement btnWallShare = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnWallShareID"))));        
        Utility.myButtonClick(btnWallShare);
                
        //Verify URL is Posted or not
        IsPresent ip = new IsPresent();
        ip.isElementPresentByXpath(driver, urlPost);        
    } 
}
