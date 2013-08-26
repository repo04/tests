/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lms.tests.runThrghTestNG.BaseClass;

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
                        ip.isTextPresentByXPATH(driver, "//div/div/div/div/div[2]/span", "Add a personal message: (optional)", 15);
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
        ip.isTextPresentByXPATH(driver, "//div[10]/div/div/div/div/span", "Add " + Utility.getFullName(user) + " as contact", 60);
        driver.findElement(By.xpath("//div/textarea")).sendKeys("Add as contact");
        driver.findElement(By.xpath("//button")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.xpath("//div[" + i + "]/div[3]/a/img"))));
        new WebDriverWait(driver, 15).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.xpath("//div[" + i + "]/div[3]/img"))));
    }

    /**
     * User confirm contact request
     *
     * @param user
     */
    void confirmContactRequest(String user) {
        int i = locateContact(user);
        driver.findElement(By.xpath("//li[" + i + "]/div/div/a/span")).click();
        ip.isElementClickableByXpath(driver, "//td[2]/table/tbody/tr[2]/td[2]/em/button", 60);
        driver.findElement(By.xpath("//td[2]/table/tbody/tr[2]/td[2]/em/button")).click();
        ip.isTextPresentByXPATH(driver, "//li[1]/div/div[2]/div/a", Utility.getFullName(user));
        ip.isTextPresentByXPATH(driver, "//li[1]/div/div/a/span", "Send Email");
    }

    /**
     * Locate Contact in My Contacts Page
     *
     * @param user
     * @return
     */
    private int locateContact(String user) {
        int i = 1;
        ip.isElementClickableByXpath(driver, "//li[" + i + "]/div/div[2]/div/a", 60);
        Boolean contact = true;
        do {
            String name = driver.findElement(By.xpath("//li[" + i + "]/div/div[2]/div/a")).getText();
            if (name.equalsIgnoreCase(Utility.getFullName(user))) {
                contact = false;
                break;
            }
            i++;
        } while (i < 6);
        if (contact) {
            Utility.illegalStateException("Contact not found: " + user);
        }
        return i;
    }

    /**
     * Verify Email Address Is Not Visible When "Hide My Email Address From
     * EveryOne" Is Selected For User
     *
     * @param user
     */
    void verifyUsersEmailAddressIsNotVisibleAsHideEmailAddressFromEveryoneIsSelected(String user) {
        int i = locateContact(user);
        driver.findElement(By.xpath("//li[" + i + "]/div/div[2]/div/a")).click();
        ip.isElementClickableByXpath(driver, "//div[11]/div/div[2]/a", 60);
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpv.getTokenValue("usrEmailXPATH"))));
    }

    /**
     * Verify Email Address Is Visible When "Allow everyone to see my Email
     * address" Is Selected For User
     *
     * @param user
     */
    void verifyUsersEmailAddressIsVisibleAsAllowEveryoneToSeeEmailAddressIsSelected(String user) {
        int i = locateContact(user);
        driver.findElement(By.xpath("//li[" + i + "]/div/div[2]/div/a")).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElement(By.xpath(xpv.getTokenValue("usrEmailXPATH")), "@2u.com"));
    }
}
