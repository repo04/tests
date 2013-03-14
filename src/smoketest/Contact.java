/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

/**
 *
 * @author somesh.bansal
 */
public class Contact extends BaseClass {

    /**
     * Add user as contact
     *
     * @param user
     */
    void addUserAsContact(String user) {
        String userName, imagePath;
        int i = 1;
        int x = 1;
        WebElement elm;
        loop:
        while (true) {
            userName = driver.findElement(By.xpath("//div[4]/div/div[" + i + "]/div[2]/a")).getText();
            if (userName.equalsIgnoreCase(Utility.getFullName(user))) {
                do {
                    if (i == 1) {
                        imagePath = "//div[3]/a[2]/img";
                        elm = driver.findElement(By.xpath(imagePath));
                    } else {
                        imagePath = "//div[" + i + "]/div[3]/a[2]/img";
                        elm = driver.findElement(By.xpath(imagePath));
                    }
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elm);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", elm);
                    elm.click();
                    try {
                        ip.isTextPresentByXPATH(driver, "//div[2]/span", "Add a personal message: (optional)", 15);
                        break loop;
                    } catch (TimeoutException e) {
                        x++;
                        driver.navigate().refresh();
                        ip.isElementClickableByXpath(driver, "//div[4]/div/div[1]/div[2]/a", 60);
                    }
                } while (x < 6);
                Utility.illegalStateException("Selenium Chrome Browser limitation: Unable to click on Image button after multiple tries also, works fine for FF");
            }
            i++;
        }
        new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElement(By.id("ext-gen67"),
                "Add " + Utility.getFullName(user) + " as contact"));
        driver.findElement(By.xpath("//div/textarea")).sendKeys("Add as contact");
        driver.findElement(By.xpath("//button")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.xpath(imagePath))));
    }
}
