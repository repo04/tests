/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

/**
 *
 * @author somesh.bansal
 */
public class SystemCompatibility extends BaseClass{

    String programName = program;
    String browserName = browser;
    String systemCompPageHeader = "Make sure your computer is ready for " + xpv.getTokenValue(programName + "SupportName");
    String sysCompatibleCheckDesc = "Your computer has been checked for potential technical issues while using " + xpv.getTokenValue(programName + "SupportName") + ".";
    String sysCompatibleCheckDesc1 = "If you have additional questions or comments you can contact " + xpv.getTokenValue(programName + "ProgramPhone");
    String osCompatiblilityText = "Your operating system is compatible with " + xpv.getTokenValue(programName + "SupportName") + ".";
    String broadbandContext = "You need a broadband connection to use " + xpv.getTokenValue(programName + "SupportName") + ".";
    String cookieCompatibleText = "We detect that your cookie settings are compatible with " + xpv.getTokenValue(programName + "SupportName") + ".";
    String browserCompatibleText = "Your browser is compatible with " + xpv.getTokenValue(programName + "SupportName") + ".";
    String expressUploaderDesc = "ExpressUploader offers increased upload speeds for your video files by first converting and then uploading them, giving you more time to focus on what's important. You can continue to navigate " + xpv.getTokenValue(programName + "SupportName") + " while your file is uploading and, if you happen to lose your connection while uploading, the file(s) will simply resume uploading once you've reconnected. Get ExpressUploader today and see the difference for yourself.";
    
    /**
     * Verifies System Compatibility Page BreadCrumb and Introduction Part
     */
    public void breadCrumbandIntroduction() {

        //TC: C57152 Verifies the UI of System Compatibility Page
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sysCompPageTitleXPATH"), "Home > System Compatibility");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("systemCompatiblePageHeaderXPATH"), systemCompPageHeader);
        ip.isElementPresentByLINK(driver, "Step 1: Component Compatibility Check");
        ip.isElementPresentByLINK(driver, "Step 2: Meeting Connection Diagnostic");
        ip.isElementPresentByLINK(driver, "ExpressUploader");
    }

    /**
     * Verifies System Compatibility Page-Step 1: Component Compatibility Check
     */
    public void componentCompatibilityUIVerify() {

        //TC: C57169 Verify the content of "Step 1: Component Compatibility Check " section
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("componentCompatibilityChkHeadingXPATH"), "Step 1: Component Compatibility Check");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sysCompatibleCheckDescXPATH"), sysCompatibleCheckDesc);
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sysCompatibleCheckDescXPATH"), "If your computer has failed any check, please click \"More Info\" for a detailed explanation on how to get, or upgrade this item on your computer.");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sysCompatibleCheckDescXPATH"), sysCompatibleCheckDesc1, 60);
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("osXPATH"), " Operating System");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("connectionSpeedXPATH"), " Connection Speed");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("browserXPATH"), " Browser Compatibility");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("cookieXPATH"), " Cookie Settings");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sslXPATH"), " Secure Sockets Layer");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("aarXPATH"), " Adobe Acrobat Reader *");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("screenResolutionXPATH"), " Screen Resolution");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("popupWindowsAllowedXPATH"), " Pop-up Windows Allowed");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("flashXPATH"), " Flash");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("pdfReaderTextXPATH"), "If you have another PDF reader software installed you do not need to download Adobe Acrobat Reader");
    }

    /**
     * Verifies System Compatibility Page -Step 2: Meeting Connection Diagnostic
     */
    public void meetingConnectionDiagnosticUIVerify() {

        //TC: C57168 verifies the UI of "Meeting Connection Diagnostic" section
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("meetingConDiagnosticHeadingXPATH"), "Step 2: Meeting Connection Diagnostic");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("meetingConDiagnosticDescXPATH"), "This diagnostic test will ensure your computer and network connections are properly configured to provide you with the best possible Connect Pro meeting experience.");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("meetingConDiagnosticDescXPATH"), "If all tests pass successfully, you are ready to log in to your meeting.");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("adobeConectLogoXPATH"));
    }

    /**
     * Verifies the faq section (Questions Answers) also verify the related
     * browser page opens with download option while clicking on browser
     * compatible icons
     */
    public void systemCompatibilityVerifyQuestionsAndBrowserCompatibleIcons() {

        //TC: C57758 Verifies the content of 'Is my OS compatible?' section
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("isMyOsCompatibleHeadingXPATH"), "Is my OS compatible?");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("osCompatibiltyProgramTextXPATH"), osCompatiblilityText);
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("supportedOSXPATH"), "Supported Operating Systems: Windows XP, Windows Vista, Windows 7, Windows 8, Mac OS 10.5 and above.");

        //TC: C57171 Verifies the content of "Is my connection speed fast enough?" section
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("isMyConectionSpdHeadingXPATH"), "Is my connection speed fast enough?");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("broadbandConProgramTextXPATH"), broadbandContext);

        //TC: C58172/C134438 Verifies the content of "Is my Browser compatible?" section and TC: C58232 Verifies that image links under 'Is my Browser compatible' section work correctly
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("browserCompatibleHeadiingXPATH"), "Is my Browser compatible?");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("browserCompatibilityTextXPATH"), browserCompatibleText);
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("supportedBrowsersXPATH"), "Supported Browsers: Internet Explorer 8.0 and above (Exception: 9.0 is not supported on Windows XP), Firefox 14.0 and above, Safari 4.0 and above, Chrome 20.0 and above.");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("latestVersionXPATH"), "The latest versions of these browsers can be downloaded at these links:");

        //Click on IE Image on system compatiblity page and verify the window title of the page opened in new window
        driver.findElement(By.xpath(xpv.getTokenValue("ieImgXPATH"))).click();
        Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);
        Utility.verifyWindowTitle(driver, "Internet Explorer - Microsoft Windows");

        //Click on FF Image on system compatiblity page
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("ffImgXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("ffImgXPATH"))).click();
        Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);
        Utility.verifyWindowTitle(driver, "Mozilla Firefox Web Browser");
        
        //Click on Safari Image on system compatiblity page and verify the window title of the page opened in new window
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("safariImgXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("safariImgXPATH"))).click();
        Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);
        Utility.verifyWindowTitle(driver, "Apple - Safari - Browse the web in smarter, more powerful ways.");

        //Click on Chrome Image on system compatiblity page and verify the window title of the page opened in new window
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("chromeImgXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("chromeImgXPATH"))).click();
        Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);
        Utility.verifyWindowTitle(driver, "Chrome Browser");

        //TC:C58242 Verify the content of the "Is my Web browser SSL capable?" section
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sslCapableHeadingXPATH"), "Is my Web browser SSL capable?");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sslCapableDescXPATH"), "We detect that your browser supports SSL.");

        //TC:C58246 Verify the content of "Is the screen resolution on my monitor correct?" section
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("screenResolutionHeadingXPATH"), "Is the screen resolution on my monitor correct?");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("scrnResolutionInst1XPATH"), "Go to the start menu: control panel: display.");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("scrnResolutionInst2XPATH"), "Click on the settings tab.");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("scrnResolutionInst3XPATH"), "Under screen resolution move the screen resolution tab either right or left until 800x600 or 1024x768 is displayed.");

        //TC:C58233 Verify the content of "Is the screen resolution on my monitor correct?" section
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("cookieSettingHeadingXPATH"), "Are my cookie settings correct?");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("cookieSettingCompatibleTextXPATH"), cookieCompatibleText);

        //Verify the content of popup window,AAR,student support and flash Section
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("popupWindowsHeadingXPATH"), "Does my browser allow pop-up windows?");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("adobeAcrobatHeadingXPATH"), "Is Adobe Acrobat Reader installed?");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("flashInstalledHeadingXPATH"), "Is Flash installed?");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sudentSupportNoXPATH"), xpv.getTokenValue(programName + "StudentSupportNo"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("universityNameXPATH"), xpv.getTokenValue(programName + "UniversityName"));
    }

    /**
     * Verify the number of "back to top" and "more info" links available and
     * are enabled or not on System Compatibility Page
     */
    public void backToTopAndMoreInfoLinks() {

        //TC: C57155 Verifies the "Back to top" and more info links are working
        List<WebElement> backtotoplinks = driver.findElements(By.linkText("back to top"));
        System.out.println("Total back to top links: " + backtotoplinks.size());

        if (programName.equalsIgnoreCase("gu-msn") || programName.equalsIgnoreCase("unc-mba") || programName.equalsIgnoreCase("usc-mat") || programName.equalsIgnoreCase("usc-msw")) {
            if (backtotoplinks.size() != 13) {
                Utility.illegalStateException("Incorrect number of 'back to top' links available: "
                        + "Expected: 13 & Actual: " + backtotoplinks.size());
            }
        } else {
            if (backtotoplinks.size() != 12) {
                Utility.illegalStateException("Incorrect number of 'back to top' links available: "
                        + "Expected: 12 & Actual: " + backtotoplinks.size());
            }
        }

        for (WebElement a : backtotoplinks) {
            a.isEnabled();
        }

        List<WebElement> moreinfolinks = driver.findElements(By.linkText("More Info"));
        System.out.println("Total more info links: " + moreinfolinks.size());

        if (moreinfolinks.size() != 9) {
            Utility.illegalStateException("Incorrect number of 'More Info' links available: "
                    + "Expected: 9 & Actual: " + moreinfolinks.size());
        }

        for (WebElement b : moreinfolinks) {
            b.isEnabled();
        }
    }

    /**
     * Verify System Compatibility Page - Express Uploader
     */
    public void expressUploader() {

        //TC: C57170 Verifies the content of "Express Uploader" section
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("expressUploaderHeadingXPATH"), "ExpressUploader");
        driver.findElement(By.linkText("Frequently Asked Questions")).isEnabled();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("expressUploaderDescXPATH"), expressUploaderDesc);

        //Store the SystemCompatibility (Current Window) in string "winHandleBefore1"
        String winHandleBefore1 = driver.getWindowHandle();

        //TC: C57153 Verifies that User can download the Express Uploader file
        driver.findElement(By.xpath(xpv.getTokenValue("expressUploaderLinkXPATH"))).click();

        //Click on ExpressUploader button from Current window
        driver.findElement(By.xpath(xpv.getTokenValue("expressUploaderBtnXPATH"))).click();
        Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);

        //Switch to new window opened on clicking ExpressUploader button and verifying elements on the new window
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        // Perform actions on new window
        try {
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("euBrdCrumbXPATH"), "Express Uploader");
            driver.findElement(By.cssSelector("#mac > a")).isEnabled();
            driver.findElement(By.cssSelector("#win > a")).isEnabled();
            driver.close();
        } catch (Exception e) {
            driver.close();
            driver.switchTo().window(winHandleBefore1);
            throw e;
        }

        //Switching back to original SystemCompatiblity window stored in string "winHandleBefore1"
        driver.switchTo().window(winHandleBefore1);

        //TC: C58276 Verify the UI of 'Frequently asked question'
        driver.findElement(By.xpath(xpv.getTokenValue("expressUploaderFaqLinkXPATH"))).click();
        Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);

        //Switch to new window opened on clicking ExpressUploaderFAQ Link and verifying elements on the new window
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        // Perform actions on new window
        try {
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("faqSectionHeadingXPATH"), "Frequently Asked Questions");
            driver.close();
        } catch (Exception e) {
            driver.close();
            driver.switchTo().window(winHandleBefore1);
            throw e;
        }

        //Switching back to original SystemCompatiblity window stored in string "winHandleBefore1"
        driver.switchTo().window(winHandleBefore1);
    }

    /**
     * Verifies System Compatibility Page-Mobile Applications
     */
    public void mobileApplicationsUIVerify() {

        //TC: C57167 Verifies the content of "Mobile Application" section
        if (programName.equalsIgnoreCase("gu-msn") || programName.equalsIgnoreCase("unc-mba") || programName.equalsIgnoreCase("usc-mat") || programName.equalsIgnoreCase("usc-msw")) {
            ip.isElementPresentByLINK(driver, "Mobile Applications");
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("mobileAppHeadingXPATH"), "Mobile Applications");
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("mobileAppPoint1XPATH"), "View your course material, including lessons, documents, and videos");
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("mobileAppPoint2XPATH"), "Find and make contact with other members of your academic program");
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("mobileAppPoint3XPATH"), "Interact with your colleagues and instructors by posting your own photographs, documents and videos");
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("mobileAppPoint4XPATH"), "Get notified of upcoming live sessions and assignment due dates");
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("mobileAppIosImageXPATH"));
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("mobileAppAndroidImageXPATH"));
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("clckHereToSignLinkXPATH"));
        } else {
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("systemCompatiblePageHeaderXPATH"), systemCompPageHeader);
            new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("Mobile Applications")));
        }
    }

    /**
     * Verify System Compatibility Page - mobile Support Section UI
     */
    public void mobileSupportSectionUIVerify() {

        //TC:C134514 Verifies that Mobile Support Icon is displayed on Student support page
        if (programName.equalsIgnoreCase("gu-msn") || programName.equalsIgnoreCase("unc-mba") || programName.equalsIgnoreCase("usc-mat") || programName.equalsIgnoreCase("usc-msw")) {
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportPageHeadingXPATH"), "Student Support");
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportMobileAppSectionHeading"), "Mobile Applications");
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportMobileAppPoint1"), "View your course material, including lessons, documents, and videos");
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportMobileAppPoint2"), "Find and make contact with other members of your academic program");
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportMobileAppPoint3"), "Interact with your colleagues and instructors by posting your own photographs, documents and videos");
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportMobileAppPoint4"), "Get notified of upcoming live sessions and assignment due dates");
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("studentSupportMobileAppImg1"));
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("studentSupportMobileAppImg2"));
        }
    }
}
