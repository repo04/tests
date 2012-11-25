/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import runThrghTestNG.BaseClass;

/**
 *
 *
 */
public class Resources extends BaseClass {

    Date now = new Date();
    String vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6, vrfy7;
    List<String> resources;

    /**
     * Verify Resources
     */
    public void verifyResources() {
        switch (BaseClass.program) {
            case "unc":
                vrfy1 = "Career Connections";
                vrfy2 = "Connect Carolina";
                vrfy3 = "KF Intranet";
                vrfy4 = "UNC Onyen";
                vrfy5 = "Faculty Profiles";
                vrfy6 = "Leadership Team";
                vrfy7 = "KF Email";
                resources = Arrays.asList(vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6, vrfy7);
                break;
            case "gu":
                vrfy1 = "HoyaMail";
                vrfy2 = "Library";
                vrfy3 = "MyAccess";
                vrfy4 = "NetID Mgmt";
                vrfy5 = "Handbooks & Info";
                resources = Arrays.asList(vrfy1, vrfy2, vrfy3, vrfy4, vrfy5);
                break;
            case "vac":
            case "usc":
                vrfy1 = "myUSC";
                vrfy2 = "Catalogue";
                vrfy3 = "Email";
                vrfy4 = "Docs";
                resources = Arrays.asList(vrfy1, vrfy2, vrfy3, vrfy4);
                break;
        }
        verifyWindow(resources);
    }

    /**
     * 
     * @param resources 
     */
    private void verifyWindow(List<String> resources) {

        int i = 1;
        String HandleBefore = driver.getWindowHandle();
        for (String resource : resources) {
            ip.isTextPresentByXPATH(driver, "//div[3]/div[2]/div/ul/li[" + i + "]/a", resource);
            driver.findElement(By.xpath("//div[3]/div[2]/div/ul/li[" + i + "]/a")).click();
            Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);
            int y = 1;
            for (String handle : driver.getWindowHandles()) {
                System.out.println("window handle: " + handle);
                if (y == driver.getWindowHandles().size()) {
                    driver.switchTo().window(handle);
                }
                y++;
            }
            try {
                switch (resource) {
                    case "Career Connections":
                    case "Leadership Team":
                    case "myUSC":
                    case "Catalogue":
                        ip.isTitleContains(driver, resource);
                        break;
                    case "Connect Carolina":
                        ip.isTitlePresent(driver, "MyUNC : UNC Campus !Portal");
                        break;
                    case "KF Intranet":
                        ip.isTitlePresent(driver, "Microsoft ISA Server 2006");
                        break;
                    case "UNC Onyen":
                        ip.isTitleContains(driver, "Onyen");
                        break;
                    case "Faculty Profiles":
                        ip.isTitleContains(driver, "Faculty Member Profiles");
                        break;
                    case "KF Email":
                        ip.isTitleContains(driver, "Microsoft Exchange - Outlook Web Access");
                        break;
                    case "HoyaMail":
                        ip.isTitlePresent(driver, "Georgetown Google Apps: Sign In - Georgetown University");
                        break;
                    case "Library":
                        ip.isTitlePresent(driver, "Welcome to Dahlgren Memorial Library -- Georgetown University");
                        break;
                    case "MyAccess":
                        ip.isTitlePresent(driver, "User Login");
                        break;
                    case "NetID Mgmt":
                        ip.isTitlePresent(driver, "Service Management");
                        break;
                    case "Handbooks & Info":
                        ip.isTitlePresent(driver, "Useful Forms and Quick Links - School of Nursing & Health Studies");
                        break;
                    case "Email":
                        ip.isTitlePresent(driver, "USC Web Mail");
                        break;
                    case "Docs":
                        ip.isTitlePresent(driver, "USCnet Login");
                        break;
                }
                driver.close();
                driver.switchTo().window(HandleBefore);
            } catch (TimeoutException e) {
                driver.close();
                driver.switchTo().window(HandleBefore);
                throw e;
            }
            i++;
        }
    }
}
