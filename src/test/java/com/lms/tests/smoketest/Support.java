
package com.lms.tests.smoketest;

import org.openqa.selenium.By;
import com.lms.tests.runThrghTestNG.BaseClass;
import org.testng.Assert;

public class Support extends BaseClass {
        
    /**
     * Verifies Support page
     */
    public void verifySupport(String role) {
        testContactUS(role);
        testExpressUploaderFAQ();
    }

    /**
     * Verifies Mobile section on Support page
     */
    public void verifySupportMobileAppURL(String role) {
        testMobileAppURL();
    }

    public void testContactUS(String role) {
        // Verifies the Days and Time Availability of Support
        switch (program) {
            case "wu-llm":
                ip.isTextPresentByXPATH(driver, xpv.getTokenValue(program + "phoneNumberXPATH"),
                        xpv.getTokenValue(program + role + "PhoneNumber"));
                break;
            default:
                ip.isTextPresentByXPATH(driver, xpv.getTokenValue("phoneNumberXPATH"),
                        xpv.getTokenValue(program + role + "PhoneNumber"));
        }

        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("mondayXPATH"),
                xpv.getTokenValue(program + role + "MondayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("tuesdayXPATH"),
                xpv.getTokenValue(program + role + "TuesdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("wednesdayXPATH"),
                xpv.getTokenValue(program + role + "WednesdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("thursdayXPATH"),
                xpv.getTokenValue(program + role + "ThursdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("fridayXPATH"),
                xpv.getTokenValue(program + role + "FridayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("saturdayXPATH"),
                xpv.getTokenValue(program + role + "SaturdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sundayXPATH"),
                xpv.getTokenValue(program + role + "SundayTimes"));

        // Verifies Email Address of Student Support
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("emailSupportXPATH"));
        String email = driver.findElement(By.xpath(xpv.getTokenValue("emailSupportXPATH"))).getAttribute("href");
        Assert.assertEquals(email, xpv.getTokenValue((program + role + "Email")));
    }

    // Verifies the Express Uploader FAQ url, specific to each program
    public void testExpressUploaderFAQ() {
        String urlFAQ;
        switch (program) {
            case "usc-mat":
                ip.isElementClickableByXpath(driver, xpv.getTokenValue("usc-matExpressUploadFAQXPATH"), 60);
                urlFAQ = driver.findElement(By.xpath(xpv.getTokenValue("usc-matExpressUploadFAQXPATH"))).getAttribute("href");
                break;
            case "gu-msn":
            case "unc-mba":
            case "usc-msw":
                ip.isElementClickableByXpath(driver, xpv.getTokenValue("expressUploadFAQXPATH"), 60);
                urlFAQ = driver.findElement(By.xpath(xpv.getTokenValue("expressUploadFAQXPATH"))).getAttribute("href");
                break;
            default:
                ip.isElementClickableByXpath(driver, xpv.getTokenValue("expressUploadFAQWithNoMobileSectionXPATH"), 60);
                urlFAQ = driver.findElement(By.xpath(xpv.getTokenValue("expressUploadFAQWithNoMobileSectionXPATH"))).getAttribute("href");
        }
        Assert.assertEquals(urlFAQ, url + "/kaltura/expressuploader.php");
    }

    public void testMobileAppURL() {
        // Verify Mobile Path for iTunes Store
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("iOSAppXPATH"), 60);
        String iOS = driver.findElement(By.xpath(xpv.getTokenValue("iOSAppXPATH"))).getAttribute("href");
        Assert.assertEquals(iOS, xpv.getTokenValue(program + "AppleAppUrl"));

        // Verify Mobile Path for Android Store
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("androidAppXPATH"), 60);
        String android = driver.findElement(By.xpath(xpv.getTokenValue("androidAppXPATH"))).getAttribute("href");
        Assert.assertEquals(android, xpv.getTokenValue(program + "AndroidAppUrl"));
    }    
}
