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

        //Check and perform functionality if Element is present or not 
        //Limitation - Webdriver throws 'NoSuchElementException' incase element is not found
        Boolean wait;
        try {
            driver.findElement(By.xpath(btnRmvUsrFilter));
            wait = false;
        } catch (NoSuchElementException e) {
            wait = true;
        }

        if (!wait) {
            driver.findElement(By.xpath(btnRmvUsrFilter)).click();
            new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(btnRmvUsrFilter)));
        }
    }
}