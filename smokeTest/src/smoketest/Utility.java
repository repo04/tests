package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

    public static IsPresent ip = new IsPresent();

    /**
     * Uses js to click on hidden element on the page by XPATH
     *
     * @param driver
     * @param menuXPATH
     */
    public static void navigateToSubMenu(WebDriver driver, String menuXPATH) {
        WebElement hiddenElement = driver.findElement(By.xpath(menuXPATH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", hiddenElement);
    }

    /**
     * Find Group from whole list
     *
     * @param driver
     * @param path
     * @param grp
     */
    public static void optionalClickByLINK(WebDriver driver, String path, String grp) {

        loopGrp:
        while (true) {
            try {
                ip.isElementPresentContainsTextByXPATH(driver, grp);
                break loopGrp;
            } catch (TimeoutException e) {
                // Do nothing
            }
            try {
                if (driver.findElement(By.linkText(path)).isDisplayed()) {
                    driver.findElement(By.linkText(path)).click();
                }
            } catch (NoSuchElementException ex) {
                break loopGrp;
            }
        }
        ip.isElementPresentContainsTextByXPATH(driver, grp);
    }

    /**
     * Remove user to check for another filter
     *
     * @param driver
     * @param btnRmvUsrFilter
     */
    public static void btnRmUsrFilter(WebDriver driver, String btnRmvUsrFilter) {

        try {
            driver.findElement(By.name("filter[realname][0]")).click();
            driver.findElement(By.id("id_removeselected")).click();
        } catch (Exception e) {
            System.out.println("button not found:");
        }

        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(btnRmvUsrFilter)));
    }

    /**
     * Throw IllegalStateException with user message
     * 
     * @param msg 
     */
    public static void illegalStateException(String msg) {
        throw new IllegalStateException(msg);
    }

    /**
     * Click using Webdriver AdavnceUserInterations API
     * 
     * @param driver
     * @param path 
     */
    public static void actionBuilderClick(WebDriver driver, String path) {
        WebElement elm = driver.findElement(By.xpath(path));
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driver);
        builder.click(elm).perform();
    }
}