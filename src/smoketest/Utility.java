package smoketest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

        try {
            driver.findElement(By.name("filter[realname][0]")).click();
            driver.findElement(By.id("id_removeselected")).click();
        } catch (Exception e) {
            System.out.println("button not found:");
        }

        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(btnRmvUsrFilter)));
    }

    /**
     * Throw IllegalStateException with user message
     *
     * @param msg
     */
    public static void illegalStateException(String msg) {
        throw new IllegalStateException(msg);
    }

    /**
     * Click using Webdriver AdavnceUserInterations API
     *
     * @param driver
     * @param path
     */
    public static void actionBuilderClick(WebDriver driver, String path) {
        WebElement elm = driver.findElement(By.xpath(path));
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driver);
        builder.click(elm).perform();
    }

    /**
     *
     * @param driver
     * @param xpv
     * @param usrNm
     */
    public static void usrEmailLogin(WebDriver driver, XpathValues xpv, String usrNm) {
        driver.get("https://mail.google.com/");
        ip.isTitlePresent(driver, "Gmail: Email from Google");
        WebElement gglUsrNm = driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocUsrIdXPATH")));
        WebElement gglPswd = driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocPswdXPATH")));
        value:
        while (true) {
            gglUsrNm.clear();
            gglPswd.clear();
            gglUsrNm.sendKeys(usrNm);
            gglPswd.sendKeys("Newuser321");
            try {
                new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpv.getTokenValue("fieldGglDocUsrIdXPATH")), usrNm));
                break value;
            } catch (TimeoutException e) {
            }
        }
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocSignInXPATH"))).click();
        ip.isTitleContains(driver, usrNm + "@gmail.com - Gmail");
    }

    /**
     * User confirms no mail is present before execution
     *
     * @param driver
     */
    public static void cnfrmNoEmlPrsnt(WebDriver driver) {
        loopEml:
        while (true) {
            try {
                ip.isElementClickableByXpath(driver, "//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div", 30);
                driver.findElement(By.xpath("//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div")).click();

                ip.isElementClickableByXpath(driver, "//tr[1]/td[5]/div/span", 30);
                driver.findElement(By.xpath("//div/span/div")).click();
                ip.isElementClickableByXpath(driver, "//div[2]/div/div/div/div/div/div/div/div/div/div[2]/div[3]/div/div", 30);
                driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div/div/div/div/div[2]/div[3]/div/div")).click();
                ip.isElementClickableByXpath(driver, "//tr[1]/td[5]/div/span", 30);
            } catch (TimeoutException e) {
                break loopEml;
            }
        }
    }

    /**
     *
     * @param driver
     */
    public static void usrEmailLogout(WebDriver driver) {
        ip.isElementPresentByXPATH(driver, "//td[2]/a");
        try {
            Utility.navigateToSubMenu(driver, "//td[2]/a");
        } catch (UnhandledAlertException e) {
            driver.switchTo().alert().accept();
        }
        ip.isTitlePresent(driver, "Gmail: Email from Google");
    }

    /**
     * Wait max 60sec for specified 'number' of Windows to be present/open
     *
     * @param driver
     * @param timeout
     * @param numberOfWindows
     */
    public static void waitForNumberOfWindowsToEqual(WebDriver driver, int timeout, final int numberOfWindows) {
        new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (driver.getWindowHandles().size() == numberOfWindows);
            }
        });
    }

    /**
     * Wait max 60sec for Alert to be present & with specified text present
     *
     * @param driver
     * @param timeout
     */
    public static void waitForAlertToBeAccepted(WebDriver driver, int timeout, final String text) {
        new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                Boolean switched = false;
                try {
                    driver.switchTo().alert();
                    if (driver.switchTo().alert().getText().contains(text)) {
                        driver.switchTo().alert().accept();
                        switched = true;
                    }
                } catch (Exception Ex) {
                    // Couldn't switch!
                }
                return switched;
            }
        });
    }

    /**
     * Get Current NewYork Date
     * 
     * @param driver
     * @return 
     */
    public static String getCurrentNewYorkDate(WebDriver driver) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        String dt = (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH)
                + "/" + c.get(Calendar.YEAR);
        System.out.println("Date in US->" + dt);
        return dt;
    }

    /**
     * Get Next NewYork Date
     * 
     * @param driver
     * @param currentDate
     * @return 
     */
    public static String getNextNewYorkDate(WebDriver driver, String currentDate) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            c.setTime(sdf.parse(currentDate));
        } catch (ParseException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.add(Calendar.DATE, 1);  // number of days to add
        String nextDate = sdf.format(c.getTime());  // dt is now the new date
        System.out.println("New date->" + nextDate);        
        return nextDate;
    }

    /**
     * 
     * @param string 
     */
    public static void copyContents(String string) {
        
        StringSelection stringSelection = new StringSelection("Somesh");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
        }
        //robot.keyPress(KeyEvent.VK_ENTER);
        //robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}