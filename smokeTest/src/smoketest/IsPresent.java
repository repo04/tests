/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IsPresent {

    // Appears it checks for the first instance of the CSS, starting from top of page working down.
    public void isTextPresentByCSS(WebDriver driver, String textPostCSS, String txtByCSS) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElement(By.cssSelector(textPostCSS), txtByCSS));        
    }

    public void isTextPresentByXPATH(WebDriver driver, String headingTextPath, String txtByXPATH) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElement(By.xpath(headingTextPath), txtByXPATH));
    }

    public void isElementPresentContainsTextByXPATH(WebDriver driver, String elmntByXPATH) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + elmntByXPATH + "')]")));
    }

    public void isElementPresentStartsWithTextByXPATH(WebDriver driver, String elmntByXPATH) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[starts-with(text(),'" + elmntByXPATH + "')]")));
    }

    public void isElementPresentByLINK(WebDriver driver, String elmntByLINK) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.linkText(elmntByLINK)));
    }

    public void isElementPresentByID(WebDriver driver, String elmntByID) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.id(elmntByID)));
    }

    public void isElementPresentByXPATH(WebDriver driver, String elmtByXPATH) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elmtByXPATH)));
    }

    public void isTitlePresent(WebDriver driver, String isTitle) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs(isTitle));        
    }
}
