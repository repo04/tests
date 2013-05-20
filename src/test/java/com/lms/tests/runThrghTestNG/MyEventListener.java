/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 *
 * @author somesh.bansal
 */
public class MyEventListener implements WebDriverEventListener {

    private RemoteWebDriver webDriver;

    public MyEventListener(WebDriver driver) {
        this.webDriver = (RemoteWebDriver) driver;
    }

    @Override
    public void onException(Throwable thrwbl, WebDriver wd) {
        System.out.println(wd);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("message: " + thrwbl.getMessage());
        String filename = generateRandomFilename(thrwbl);
        System.out.println("image: " + filename);
        File screenshot = ((TakesScreenshot) new Augmenter().augment(wd)).
                getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //createScreenCaptureJPEG(filename);
    }

    private String generateRandomFilename(Throwable arg0) {
        Calendar c = Calendar.getInstance();
        String filename = arg0.getMessage();
        int i = filename.indexOf('\n');
        filename = filename.substring(0, i).replaceAll("\\s", "_").replaceAll(":", "") + ".jpg";
        try {
            filename = BaseClass.directory.getCanonicalPath() + File.separator + "target" + File.separator
                    + "surefire-reports" + File.separator + c.get(Calendar.YEAR)
                    + "-" + c.get(Calendar.MONTH)
                    + "-" + c.get(Calendar.DAY_OF_MONTH)
                    + "-" + c.get(Calendar.HOUR_OF_DAY)
                    + "-" + c.get(Calendar.MINUTE)
                    + "-" + c.get(Calendar.SECOND)
                    + ".jpg";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    private void createScreenCaptureJPEG(String filename) {
        try {
            BufferedImage img = getScreenAsBufferedImage();
            File output = new File(filename);
            ImageIO.write(img, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage getScreenAsBufferedImage() {
        BufferedImage img = null;
        try {
            Robot r;
            r = new Robot();
            Toolkit t = Toolkit.getDefaultToolkit();
            Rectangle rect = new Rectangle(t.getScreenSize());
            img = r.createScreenCapture(rect);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public void beforeNavigateTo(String string, WebDriver wd) {
        //System.out.println("Before navigating to url printing the browser associated capabilities");
        //System.out.println(this.webDriver.getCapabilities());
    }

    @Override
    public void afterNavigateTo(String string, WebDriver wd) {
        // does nothing
    }

    @Override
    public void beforeNavigateBack(WebDriver wd) {
        // does nothing
    }

    @Override
    public void afterNavigateBack(WebDriver wd) {
        // does nothing
    }

    @Override
    public void beforeNavigateForward(WebDriver wd) {
        // does nothing
    }

    @Override
    public void afterNavigateForward(WebDriver wd) {
        // does nothing
    }

    @Override
    public void beforeFindBy(By by, WebElement we, WebDriver wd) {
        // does nothing
    }

    @Override
    public void afterFindBy(By by, WebElement we, WebDriver wd) {
        // does nothing
    }

    @Override
    public void beforeClickOn(WebElement we, WebDriver wd) {
        // does nothing
    }

    @Override
    public void afterClickOn(WebElement we, WebDriver wd) {
        // does nothing
    }

    @Override
    public void beforeChangeValueOf(WebElement we, WebDriver wd) {
        // does nothing
    }

    @Override
    public void afterChangeValueOf(WebElement we, WebDriver wd) {
        // does nothing
    }

    @Override
    public void beforeScript(String string, WebDriver wd) {
        // does nothing
    }

    @Override
    public void afterScript(String string, WebDriver wd) {
        // does nothing
    }
}
