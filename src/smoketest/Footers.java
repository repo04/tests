/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

/**
 *
 *
 */
public class Footers extends BaseClass {

    String vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6;
    List<String> footers;
    String prgrmTitle = null;

    /**
     * Verify Footers
     */
    void verifyFooters() {
        switch (BaseClass.program) {
            case "unc":
                vrfy1 = "University of North Carolina at Chapel Hill";
                prgrmTitle = "UNC Kenan-Flagler Business School MBA@UNC:";
                break;
            case "gu":
                vrfy1 = "Georgetown University";
                prgrmTitle = "Nursing@Georgetown:";
                break;
            case "vac":
                vrfy1 = "University of Southern California";
                prgrmTitle = "USC School of Social Work: MSW@USC:";
                break;
            case "usc":
                vrfy1 = "University of Southern California";
                prgrmTitle = "USC Rossier School of Education:";
                break;
            case "llm":
                vrfy1 = "Washington University in St. Louis";
                prgrmTitle = "@WashULaw | Washington University in St.Louis:";
                break;
            case "mpa":
                vrfy1 = "University of North Carolina at Chapel Hill";
                prgrmTitle = "MPA@UNC | UNC School of Government:";
        }
        vrfy2 = "Terms And Conditions";
        vrfy3 = "Privacy Statement";
        vrfy4 = "About";
        vrfy5 = "Contact Us";
        vrfy6 = "Student Support";
        footers = Arrays.asList(vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6);
        verifyTitle(footers);
    }

    private void verifyTitle(List<String> footers) {
        int i = 1;
        String HandleBefore = driver.getWindowHandle();
        
        for (String footer : footers) {
            if (i == 1) {
                new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span/b[contains(text(),'" + footer + "')]")));
            } else if (i == 2 || i == 3) {
                ip.isTextPresentByXPATH(driver, "//div[7]/div/span[" + i + "]/a", footer);
                driver.findElement(By.xpath("//div[7]/div/span[" + i + "]/a")).click();
                Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);
                int y = 1;
                for (String handle : driver.getWindowHandles()) {
                    System.out.println("window handle: " + handle);
                    if (y == driver.getWindowHandles().size()) {
                        driver.switchTo().window(handle);
                    }
                    y++;
                }
                try {
                    ip.isTitleContains(driver, prgrmTitle + " " + footer);
                    driver.close();
                    driver.switchTo().window(HandleBefore);
                } catch (TimeoutException e) {
                    driver.close();
                    driver.switchTo().window(HandleBefore);
                    throw e;
                }
            }else{
                ip.isTextPresentByXPATH(driver, "//div[7]/div/span[" + i + "]/a", footer);
                driver.findElement(By.xpath("//div[7]/div/span[" + i + "]/a")).click();
                ip.isTitleContains(driver, prgrmTitle + " " + footer);
                driver.findElement(By.linkText("Home")).click();
            }
            i++;
        }
    }
}
