
package smoketest;

import org.openqa.selenium.By;
import runThrghTestNG.BaseClass;
import smoketest.Actions;
import org.testng.Assert;

public class StudentSupport extends BaseClass {
    Actions a = new Actions();
    String programName = BaseClass.program;
    
    public void verifyStudentSupport() {
        testContactUS();
        testExpressUploaderFAQ();
        testMobileAppURL();
    }
    
    public void testContactUS() {
        
        // Verifies the Days and Time Availability of Support
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("phoneNumberXPATH"), 
                                        xpv.getTokenValue(programName + "PhoneNumber"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("mondayXPATH"), 
                                        xpv.getTokenValue(programName + "MondayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("tuesdayXPATH"), 
                                        xpv.getTokenValue(programName + "TuesdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("wednesdayXPATH"), 
                                        xpv.getTokenValue(programName + "WednesdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("thursdayXPATH"), 
                                        xpv.getTokenValue(programName + "ThursdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("fridayXPATH"), 
                                        xpv.getTokenValue(programName + "FridayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("saturdayXPATH"), 
                                        xpv.getTokenValue(programName + "SaturdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sundayXPATH"), 
                                        xpv.getTokenValue(programName + "SundayTimes"));
        
        // Verifies Email Address of Student Support
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("emailSupportXPATH"));
        String email = driver.findElement(By.xpath(xpv.getTokenValue("emailSupportXPATH"))).getAttribute("href");
        Assert.assertEquals(email, xpv.getTokenValue((programName + "Email")));
    }
    
    // Verifies the Express Uploader FAQ url, specific to each program
    public void testExpressUploaderFAQ() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("expressUploadFAQXPATH"));
        String urlFAQ = driver.findElement(By.xpath(xpv.getTokenValue("expressUploadFAQXPATH"))).getAttribute("href");
        Assert.assertEquals(urlFAQ, url + "/kaltura/expressuploader.php");
    }
    
    public void testMobileAppURL() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("iOSAppXPATH"));
        String iOS = driver.findElement(By.xpath(xpv.getTokenValue("iOSAppXPATH"))).getAttribute("href");
        Assert.assertEquals(iOS, xpv.getTokenValue(programName + "AppleAppUrl"));
    }
    
}
