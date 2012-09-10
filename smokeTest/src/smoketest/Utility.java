package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    public static void optionalClickByLINK(WebDriver driver, String path, String grp) {
        while (true) {
            try {
                new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText(path)));
                driver.findElement(By.linkText(path)).click();
            } catch (Exception e) {
                break;
            }
        }
        ip.isElementPresentContainsTextByXPATH(driver, grp);       
    }
}