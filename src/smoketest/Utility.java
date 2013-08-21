package smoketest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
import org.testng.Reporter;
import runThrghTestNG.BaseClass;

public class Utility extends BaseClass {

    public static IsPresent ip = new IsPresent();
    public static String str;

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
     * Uses js to click on hidden element on the page by CSS
     * 
     * @param driver
     * @param menuCSS 
     */
    public static void clickByJavaScriptUsingCSS(WebDriver driver, String menuCSS) {
        WebElement hiddenElement = driver.findElement(By.cssSelector(menuCSS));
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
    public static void buttonRemoveUserFilter(WebDriver driver, String buttonRemoveUserFilter) {

        try {
            driver.findElement(By.name("filter[realname][0]")).click();
            driver.findElement(By.id("id_removeselected")).click();
        } catch (Exception e) {
            System.out.println("button not found:");
        }

        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(buttonRemoveUserFilter)));
    }

    /**
     * Throw IllegalStateException with user message
     *
     * @param message
     */
    public static void illegalStateException(String message) {
        throw new IllegalStateException(message);
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
     * @param userName
     */
    public static void userEmailLogIn(WebDriver driver, XpathValues xpv, String emailUsername, String emailPassword) {
        driver.get("https://mail.google.com/");
        ip.isTitlePresent(driver, "Gmail: Email from Google");
        WebElement gglUsrNm = driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocUsrIdXPATH")));
        WebElement gglPswd = driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocPswdXPATH")));
        Boolean result;
        int x = 1;
        value:
        do {
            gglUsrNm.clear();
            gglPswd.clear();
            gglUsrNm.sendKeys(emailUsername);
            gglPswd.sendKeys(emailPassword);
            try {
                new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpv.getTokenValue("fieldGglDocUsrIdXPATH")), emailUsername));
                result = false;
                break value;
            } catch (TimeoutException e) {
                x++;
                result = true;
            }
        } while (x < 5);

        if (result) {
            Utility.illegalStateException("Unable to send expected userName: " + emailUsername);
        }
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocSignInXPATH"))).click();
        try {
            ip.isTitleContains(driver, emailUsername + " - 2U Mail");
        } catch (Exception e) {
            driver.get(BaseClass.url);
            verifyCurrentUrl(driver, xpv.getTokenValue("loginPageURL"));
            throw e;
        }
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
            Alert alert = new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent());
            handleAlertException(alert);
        } catch (UnhandledAlertException e) {
            Alert alert = driver.switchTo().alert();
            handleAlertException(alert);
            System.out.println("Inside UnhandledAlertException");
        } catch (TimeoutException e) {
            //Do Nothing
            System.out.println("No unknown modal dialog present");
        }
        ip.isTitlePresent(driver, "Gmail: Email from Google");
    }

    /**
     *
     * @param alert
     */
    private static void handleAlertException(Alert alert) {
        String error = "###**Unexpected Alert located with Text as: " + alert.getText() + "**###";
        alert.accept();
        Reporter.log(error, true);
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
                    System.out.println(text + " not found");
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
     * OnClick attribute of Input Element is handled for Chrome Browser by ROBOT
     * functionality
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
     * @param iframeIndex
     * @param textInIframe
     */
    public static void typeInContentEditableIframe(WebDriver driver, int iframeIndex, String textInIframe) {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("iframes count:" + iframes.size());
        int x = 1;
        loop:
        for (WebElement frame : iframes) {
            String iframeID = frame.getAttribute("id");
            System.out.println("Iframe ID: " + iframeID);
            if (x == iframeIndex) {
                driver.switchTo().frame(iframeID);
                break loop;
            }
            x++;
        }
        
        //Switch focus
        WebElement editableTxtArea = driver.switchTo().activeElement();
        editableTxtArea.sendKeys(Keys.chord(Keys.CONTROL, "a"), textInIframe);
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

    /**
     * Read contents from a file and paste the contents in a text box field in website
     *
     * @param filePathName
     * @param xPath
     */
    public static void readAndCopyContentsToTextField(WebDriver driver, String filePathName, String xPath) {
        try {
            FileInputStream fstream = new FileInputStream(filePathName);
            try (DataInputStream in = new DataInputStream(fstream)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String str;
                while ((str = br.readLine()) != null) {
                    driver.findElement(By.xpath(xPath)).sendKeys(str);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    /**
     * 
     * @param driver
     * @param iframeIndex
     * @return 
     */
    public static String getTextFromContentEditableIframe(WebDriver driver, int iframeIndex) {
        List<WebElement> iFrames = driver.findElements(By.tagName("iframe"));
        System.out.println("iFrames count:" + iFrames.size());
        int x = 1;
        for (WebElement iframe : iFrames) {
            String iframeID = iframe.getAttribute("id");
            System.out.println("Iframe ID: " + iframeID);
            if (x == iframeIndex) {
                driver.switchTo().frame(iframeID);
            }
            x++;
        }

        //Switch focus
        WebElement editableTxtArea = driver.switchTo().activeElement();
        String text = editableTxtArea.getText();
        driver.switchTo().defaultContent();
        return text;
    }
}
