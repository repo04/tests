/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.smoketest;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import com.lms.tests.runThrghTestNG.BaseClass;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 *
 */
public class Help extends BaseClass {
    
    private RemoteWebDriver driver;
    
    public Help(RemoteWebDriver driver){
        this.driver = driver;        
    }

    String verify1, verify2, verify3, verify4, verify5;
    List<String> footers;

    /**
     * Verify Help Window on Home Page
     */
    public void verifyHelpWindow() {
        System.out.println("in method");
        verify1 = "- Top News: any posts or activity "
                + "from contacts or courses that have been promoted.";
        verify2 = "- Recent News: recent posts or activity from all "
                + "contacts or courses you are associated with.";
        verify3 = "- Resources at the bottom right will allow you "
                + "to go to any page provided without leaving the platform.";
        verify4 = "- Click on the month to open up the calendar function.";
        verify5 = "- Upcoming events will list all live sessions and "
                + "assignments for the week from any course you are in.";
        footers = Arrays.asList(verify1, verify2, verify3, verify4, verify5);
        ip.isElementClickableByXpath(driver, "//a[@id='show-help']/span[2]", 60);

        int i = 1;
        end:
        while (i < 6) {
            List<WebElement> elements = driver.findElement(By.id("show-help")).findElements(By.tagName("span"));
            System.out.println("Total inputs: " + elements.size());
            Utility.robotclick(elements.get(1));
            if (i < 5) {
                if (browser.equalsIgnoreCase("chrome")) {
                    try {
                        ip.isTextPresentByXPATH(driver, "//div[@id='ext-gen21']/span", "Help on This Page", 15);
                        break end;
                    } catch (TimeoutException e) {
                        System.out.println("count: " + i);
                        driver.navigate().refresh();
                        ip.isTitlePresent(driver, xpv.getTokenValue(this.program + "homePageTitle"));
                        i++;
                    }
                } else {
                    ip.isTextPresentByXPATH(driver, "//div[@id='ext-gen21']/span", "Help on This Page");
                    break end;
                }
            } else {
                Utility.illegalStateException("Selenium Chrome Browser limitation: Unable to click button with javascript;void(0) event, works fine for FF");
            }
        }

        int x = 1;
        for (String footer : footers) {
            ip.isTextPresentByXPATH(driver, "//*[@id='help_text']/p[" + x + "]", footer);
            x++;
        }
        driver.findElement(By.xpath("//div[@id='ext-gen21']/div")).click();
    }
}
