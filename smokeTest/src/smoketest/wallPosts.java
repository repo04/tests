/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author somesh.bansal
 */
public class wallPosts {
    
//    private AccountValues av;
//    WebDriver driver;
//    WebElement txtarea = driver.findElement(By.id(av.getTokenValue("txtareaID")));
//    WebElement btnWallShare = driver.findElement(By.id(av.getTokenValue("btnWallShareID")));
//    
//    public void textPost(String university) throws Exception
//    {  
//        // Creates AccountValue object so we can access token values in property file
//        av = new AccountValues(university);
//        
//        //Click on textarea and Text is posted
//        Utility.myButtonClick(txtarea, av.getTokenValue("txtareaID"));
//        btnWallShare.isDisplayed();
//        driver.switchTo().frame(av.getTokenValue("frameID"));
//        WebElement editable = driver.switchTo().activeElement();
//	Utility.mySendKeys(editable, av.getTokenValue("wallText"));
//	driver.switchTo().defaultContent();
//        Utility.myButtonClick(btnWallShare, av.getTokenValue("btnWallShareID"));
//        
//        //Verify Text is Posted or not
//        IsPresent ip = new IsPresent();
//	ip.isTextPresent(driver, av.getTokenValue("txtPostOnWall"), av.getTokenValue("textWallCSS"));                
//    }
//    
//    public void urlPost(String university) throws Exception
//    {
//        av = new AccountValues(university);
//        
//        //Click on textarea and URL is posted
//        Utility.myButtonClick(txtarea, av.getTokenValue("txtareaID"));
//        WebElement btnLink = driver.findElement(By.id(av.getTokenValue("btnLinkID")));
//        btnLink.isDisplayed();
//        Utility.mySendKeys(btnLink, av.getTokenValue("btnLinkID"));
//        Utility.myButtonClick(btnWallShare, av.getTokenValue("btnWallShareID"));
//        
//        //Verify URL is Posted or not
//        IsPresent ip = new IsPresent();
//	ip.isElementPresent(driver, av.getTokenValue("urlPostOnWall"));        
//    } 
}
