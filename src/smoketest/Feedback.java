/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import org.openqa.selenium.By;
import runThrghTestNG.BaseClass;

/**
 *
 *
 */
public class Feedback extends BaseClass {
    
    /**
     * Verify feedback window
     */
    public void verifyFeedbackWindow() {
        ip.isElementClickableByXpath(driver, "//a[@id='show-feedback']/span[2]", 60);
        driver.findElement(By.xpath("//a[@id='show-feedback']/span[2]")).click();
        Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);
        Utility.verifyWindowTitle(driver, "Feedback Form - 2tor, Inc.");
    }
}
