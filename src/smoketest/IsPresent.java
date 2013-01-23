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
     * Driver checks if the given TEXT is present in the specified element by
     * CSS
     *
     * @param driver
     * @param css
     * @param text
     */
    public void isTextPresentByCSS(WebDriver driver, String css, String text) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElement(By.cssSelector(css), text));
    }

    /**
     * Driver checks if the given TEXT is present in the specified element by
     * PATH
     *
     * @param driver
     * @param path
     * @param text
     */
    public void isTextPresentByXPATH(WebDriver driver, String path, String text) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElement(By.xpath(path), text));
    }

    /**
     * Driver checks if the given TEXT is present in the specified element by
     * XPATH till allocated time
     *
     * @param driver
     * @param path
     * @param text
     * @param wait
     */
    public void isTextPresentByXPATH(WebDriver driver, String path, String text, int wait) {
        new WebDriverWait(driver, wait).until(ExpectedConditions.textToBePresentInElement(By.xpath(path), text));
    }

    /**
     * Driver checks if an element containing Text is present by XPATH on the
     * DOM of a page
     *
     * @param driver
     * @param elementText
     */
    public void isElementPresentContainsTextByXPATH(WebDriver driver, String elementText) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + elementText + "')]")));
    }

    /**
     * Driver checks if an element starts with Text is present by XPATH on the
     * DOM of a page
     *
     * @param driver
     * @param elementText
     */
    public void isElementPresentStartsWithTextByXPATH(WebDriver driver, String elementText) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[starts-with(text(),'" + elementText + "')]")));
    }

    /**
     * Driver checks if an element is present by LINK on the DOM of a page
     *
     * @param driver
     * @param elementByLINK
     */
    public void isElementPresentByLINK(WebDriver driver, String elementByLINK) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.linkText(elementByLINK)));
    }

    /**
     * Driver checks if an element is present by ID on the DOM of a page
     *
     * @param driver
     * @param elementByID
     */
    public void isElementPresentByID(WebDriver driver, String elementByID) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.id(elementByID)));
    }

    /**
     * Driver checks if an element is present by XPATH on the DOM of a page
     *
     * @param driver
     * @param elementByXPATH
     */
    public void isElementPresentByXPATH(WebDriver driver, String elementByXPATH) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementByXPATH)));
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

    /**
     * Driver finds an element by XPATH if its visible and enabled such that can
     * be clicked
     *
     * @param driver
     * @param elementClickable
     */
    public void isElementClickableByXpath(WebDriver driver, String elementClickable, int time) {
        new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(By.xpath(elementClickable)));
    }
}
