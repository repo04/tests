package smoketest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
    public static void clickByJavaScript(WebDriver driver, String menuXPATH) {
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
     * @param buttonRemoveUserFilter
     */
    public static void buttonRemoveUserFilter(WebDriver driver, String btnRmvUsrFilter) {

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
    public static void userEmailLogIn(WebDriver driver, XpathValues xpv, String usrNm) {
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
    public static void confirmNoEmailPresent(WebDriver driver) {
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
    public static void userEmailLogOut(WebDriver driver) {
        ip.isElementPresentByXPATH(driver, "//td[2]/a");
        try {
            Utility.clickByJavaScript(driver, "//td[2]/a");
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
     * Verify Title of Window
     *
     * @param driver
     * @param text
     */
    public static void verifyWindowTitle(WebDriver driver, String text) {
        String HandleBefore = driver.getWindowHandle();
        int i = 1;
        for (String handle : driver.getWindowHandles()) {
            System.out.println("window handle: " + handle);
            driver.switchTo().window(handle);
            if (i == driver.getWindowHandles().size()) {
                try {
                    ip.isTitleContains(driver, text);
                    driver.close();
                } catch (Exception e) {
                    System.out.println("FeedBack Window not found");
                    driver.close();
                    driver.switchTo().window(HandleBefore);
                    throw e;
                }
            }
            i++;
        }
        driver.switchTo().window(HandleBefore);
    }

    /**
     * Wait 60sec for Alert with expected TEXT is present
     *
     * @param driver
     * @param timeout
     * @param text
     */
    public static void waitForAlertToBeAccepted(WebDriver driver, int timeout, final String text) {
        Alert alert = new WebDriverWait(driver, 60).until(ExpectedConditions.alertIsPresent());
        if (alert.getText().contains(text)) {
            alert.accept();
        } else {
            String error = "Incorrect Alert present with Text as '" + alert.getText() + "'. "
                    + "Expected text: '" + text + "'";
            alert.dismiss();
            Utility.illegalStateException(error);
        }
    }

    /**
     * Get Current NewYork Date in MM/dd/yyyy
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
     * Get Next NewYork Date in MM/dd/yyyy
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
            robot.delay(1000);
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

    /**
     * Chrome Browser element Click limitation minimized by ROBOT functionality
     *
     * @param element
     */
    public static void robotclick(WebElement element) {
        Robot robot = null;
        try {
            robot = new Robot();
            robot.delay(1000);
        } catch (AWTException ex) {
            System.out.println("excptn:");
            Logger.getLogger(Activity.class.getName()).log(Level.SEVERE, null, ex);
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        element.click();
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * Verify specific TEXT is present in current URL
     *
     * @param driver
     * @param textInUrl
     */
    public static void verifyCurrentUrl(WebDriver driver, String textInUrl) {
        if (!driver.getCurrentUrl().contains(textInUrl)) {
            Utility.illegalStateException("Actual URL: '" + textInUrl + "' is not present "
                    + "in Expected URL: " + driver.getCurrentUrl());
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public static String getFullName(String name) {
        return name + " " + name;
    }

    /**
     * Type in Content Editable iframe
     *
     * @param driver
     * @param i -> Select iframe index
     * @param conceptEntry
     */
    public static void typeInContentEditableIframe(WebDriver driver, int i, String conceptEntry) {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("iframes count:" + iframes.size());
        int x = 1;
        loop:
        for (WebElement frame : iframes) {
            if (x == i) {
                System.out.println("Iframe ID: " + frame.getAttribute("id"));
                driver.switchTo().frame(frame.getAttribute("id"));
                break loop;
            }
            x++;
        }

        //Switch focus
        WebElement editableTxtArea = driver.switchTo().activeElement();
        editableTxtArea.sendKeys(Keys.chord(Keys.CONTROL, "a"), conceptEntry);
        driver.switchTo().defaultContent();
    }

    /**
     * Verify Date Present In Element Value field
     * 
     * @param driver
     * @param id
     */
    public static void verifyDatePresentInElementValue(WebDriver driver, By id) {
        String regex = "^(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((20)\\d\\d)$";
        int x = 1;

        do {
            String fetchdate = driver.findElement(id).getAttribute("value");
            if (!fetchdate.isEmpty()) {
                if (!Pattern.matches(regex, fetchdate)) {
                    Utility.illegalStateException("Date (" + fetchdate + ") does not match the expected (mm/dd/yyyy) format");
                }
                break;
            }
            x++;
        } while (x < 2401);

        if (x > 2400) {
            Utility.illegalStateException("Timed out after 60 seconds waiting for presence of DATE located by ID: " + id);
        }
    }
}
