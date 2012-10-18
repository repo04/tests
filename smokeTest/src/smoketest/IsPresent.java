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

    /**
     * Driver checks if the given txtByCSS is present in the specified element
     * by CSS
     *
     * @param driver
     * @param textPostCSS
     * @param txtByCSS
     */
    public void isTextPresentByCSS(WebDriver driver, String textPostCSS, String txtByCSS) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElement(By.cssSelector(textPostCSS), txtByCSS));
    }

    /**
     * Driver checks if the given txtByXPATH is present in the specified element
     * by XPATH
     *
     * @param driver
     * @param headingTextPath
     * @param txtByXPATH
     */
    public void isTextPresentByXPATH(WebDriver driver, String headingTextPath, String txtByXPATH) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElement(By.xpath(headingTextPath), txtByXPATH));
    }

    /**
     * Driver checks if the given txtByXPATH is present in the specified element
     * by XPATH till allocated time
     *
     * @param driver
     * @param headingTextPath
     * @param txtByXPATH
     * @param wait
     */
    public void isTextPresentByXPATH(WebDriver driver, String headingTextPath, String txtByXPATH, int wait) {
        new WebDriverWait(driver, wait).until(ExpectedConditions.textToBePresentInElement(By.xpath(headingTextPath), txtByXPATH));
    }

    /**
     * Driver checks if an element containing Text is present by XPATH on the
     * DOM of a page
     *
     * @param driver
     * @param elmntByXPATH
     */
    public void isElementPresentContainsTextByXPATH(WebDriver driver, String elmntByXPATH) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + elmntByXPATH + "')]")));
    }

    /**
     * Driver checks if an element starts with Text is present by XPATH on the
     * DOM of a page
     *
     * @param driver
     * @param elmntByXPATH
     */
    public void isElementPresentStartsWithTextByXPATH(WebDriver driver, String elmntByXPATH) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[starts-with(text(),'" + elmntByXPATH + "')]")));
    }

    /**
     * Driver checks if an element is present by LINK on the DOM of a page
     *
     * @param driver
     * @param elmntByLINK
     */
    public void isElementPresentByLINK(WebDriver driver, String elmntByLINK) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.linkText(elmntByLINK)));
    }

    /**
     * Driver checks if an element is present by ID on the DOM of a page
     *
     * @param driver
     * @param elmntByID
     */
    public void isElementPresentByID(WebDriver driver, String elmntByID) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.id(elmntByID)));
    }

    /**
     * Driver checks if an element is present by XPATH on the DOM of a page
     *
     * @param driver
     * @param elmtByXPATH
     */
    public void isElementPresentByXPATH(WebDriver driver, String elmtByXPATH) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elmtByXPATH)));
    }

    /**
     * Driver checks if the current title matches with isTitle
     *
     * @param driver
     * @param isTitle
     */
    public void isTitlePresent(WebDriver driver, String isTitle) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.titleIs(isTitle));
    }
    
    /**
     * Driver checks if the current title contains isTitle
     * 
     * @param driver
     * @param isTitle 
     */
    public void isTitleContains(WebDriver driver, String isTitle) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.titleContains(isTitle));
    }
}
