/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.smoketest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.Alert;
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
public class File extends BaseClass {

    /**
     * Upload files of multiple format(pdf, pptx, doc)
     *
     * @param file
     */
    public void uploadFiles(String[] files) {
        String filepath = null;
        try {
            filepath = directory.getCanonicalPath() + java.io.File.separator + "data" + java.io.File.separator;
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String file : files) {
            ip.isTextPresentByXPATH(driver, "//thead/tr/td/div", "File Name");
            ip.isElementClickableByXpath(driver, "//div/input", 60);
            Utility.actionBuilderClick(driver, "//div/input");
            ip.isTextPresentByXPATH(driver, "//label", "File Name:");

            WebElement elm = driver.findElement(By.xpath("//div/input[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", elm);
            elm.sendKeys(filepath + file);

            driver.findElement(By.xpath("//td[2]/table/tbody/tr/td/table/tbody/tr/td"
                    + "/table/tbody/tr[2]/td[2]/em/button")).click();
            ip.isTextPresentByXPATH(driver, "//div/div/div/div/div[2]/span", "Uploading File...");
            ip.isTextPresentByXPATH(driver, "//div/table/tbody/tr/td/div/a", file, 300);
            System.out.print("file uploaded: " + file + "\n");
        }
    }

    /**
     * Verify files in Course
     *
     * @param files
     */
    public void verifyFilesInCourse(String[] files) {
        int i = 1;
        for (String file : files) {
            ip.isTextPresentByXPATH(driver, "//div[" + i + "]/table/tbody/tr/td/div/a", file);
            i++;
        }
    }

    /**
     * Verify files in Portfolio
     *
     * @param files
     */
    public void verifyFilesInPortfolio(String[] files) {
        int i = 2;
        for (String file : files) {
            ip.isTextPresentByXPATH(driver, "//div[5]/div/div/table/tbody/tr[" + i + "]/td/a", file);
            i++;
        }
    }

    /**
     *
     * @param files
     */
    public void deleteFiles(String[] files) {
        ip.isTextPresentByXPATH(driver, "//thead/tr/td/div", "File Name");
        for (String file : files) {
            ip.isElementClickableByXpath(driver, "//td[3]/div/div", 60);
            driver.findElement(By.xpath("//td[3]/div/div")).click();
            ip.isTextPresentByXPATH(driver, "//div/div/div/div/div[2]/span", "Are you sure you want to delete this file?");
            driver.findElement(By.xpath("//div[2]/div/div/div/div/table/tbody/"
                    + "tr/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/em/button")).click();
            //Handled -> LMSII-3168 Exception
            try {
                Alert alert = new WebDriverWait(driver, 15).until(ExpectedConditions.alertIsPresent());
                String error = "Unexpected Alert present with Text as: " + alert.getText();
                alert.dismiss();
                Utility.illegalStateException(error);
            } catch (TimeoutException e) {
                //Do Nothing
            }
            new WebDriverWait(driver, 30).until(ExpectedConditions.
                    invisibilityOfElementWithText(By.xpath("//div/table/tbody/tr/td/div/a"), file));
            System.out.print("file deleted: " + file + "\n");
        }
        
        Utility.clickByJavaScript(driver, "//li[2]/ul/li[3]/a");
        for (String file : files) {
            new WebDriverWait(driver, 30).until(ExpectedConditions.
                    invisibilityOfElementWithText(By.xpath("//div[5]/div/div/table/tbody/tr[2]/td/a"), file));
        }
    }
}
