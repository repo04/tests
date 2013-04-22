
package com.lms.tests.smoketest;

import org.openqa.selenium.By;
import com.lms.tests.runThrghTestNG.BaseClass;
import org.testng.Assert;

public class StudentSupport extends BaseClass {
        
    public void verifyStudentSupport() {
        testContactUS();
        testExpressUploaderFAQ();
    }
    
    public void verifyStudentSupportMobileAppURL() {
        testMobileAppURL();
    }
    
    public void testContactUS() {
        
        // Verifies the Days and Time Availability of Support
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("phoneNumberXPATH"), 
                                        xpv.getTokenValue(program + "PhoneNumber"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("mondayXPATH"), 
                                        xpv.getTokenValue(program + "MondayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("tuesdayXPATH"), 
                                        xpv.getTokenValue(program + "TuesdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("wednesdayXPATH"), 
                                        xpv.getTokenValue(program + "WednesdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("thursdayXPATH"), 
                                        xpv.getTokenValue(program + "ThursdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("fridayXPATH"), 
                                        xpv.getTokenValue(program + "FridayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("saturdayXPATH"), 
                                        xpv.getTokenValue(program + "SaturdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sundayXPATH"), 
                                        xpv.getTokenValue(program + "SundayTimes"));
        
        // Verifies Email Address of Student Support
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("emailSupportXPATH"));
        String email = driver.findElement(By.xpath(xpv.getTokenValue("emailSupportXPATH"))).getAttribute("href");
        Assert.assertEquals(email, xpv.getTokenValue((program + "Email")));
    }
    
    // Verifies the Express Uploader FAQ url, specific to each program
    public void testExpressUploaderFAQ() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("expressUploadFAQXPATH"));
        String urlFAQ = driver.findElement(By.xpath(xpv.getTokenValue("expressUploadFAQXPATH"))).getAttribute("href");
        Assert.assertEquals(urlFAQ, url + "/kaltura/expressuploader.php");
    }
    
    public void testMobileAppURL() {
        
        // Verify Mobile Path for iTunes Store
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("iOSAppXPATH"));
        String iOS = driver.findElement(By.xpath(xpv.getTokenValue("iOSAppXPATH"))).getAttribute("href");
        Assert.assertEquals(iOS, xpv.getTokenValue(program + "AppleAppUrl"));
        
        // Verify Mobile Path for Android Store
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("androidAppXPATH"));
        String android = driver.findElement(By.xpath(xpv.getTokenValue("androidAppXPATH"))).getAttribute("href");
        Assert.assertEquals(android, xpv.getTokenValue(program + "AndroidAppUrl"));
    }    
}
