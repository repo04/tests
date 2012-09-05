package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utility{

    // Uses js to click on hidden elements on the page
    public static void navigateToSubMenu(WebDriver driver, String menuXPATH) {
        WebElement hiddenElement = driver.findElement(By.xpath(menuXPATH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", hiddenElement);
    }
}
