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
    String verify1, verify2, verify3, verify4, verify5, verify6, verify7,
            verify8, verify9, verify10, verify11, verify12, verify13;
    List<String> resources;

    /**
     * Verify Resources
     */
    public void verifyResources() {
        switch (program) {
            case "unc-mba":
                verify1 = "Career Connections";
                verify2 = "Connect Carolina";
                verify3 = "KF Intranet";
                verify4 = "UNC Onyen";
                verify5 = "Faculty Profiles";
                verify6 = "Leadership Team";
                verify7 = "KF Email";
                resources = Arrays.asList(verify1, verify2, verify3, verify4, verify5, verify6, verify7);
                break;
            case "gu-msn":
                verify1 = "HoyaMail";
                verify2 = "Library";
                verify3 = "MyAccess";
                verify4 = "NetID Mgmt";
                verify5 = "Handbooks & Info";
                resources = Arrays.asList(verify1, verify2, verify3, verify4, verify5);
                break;
            case "usc-msw":
            case "usc-mat":
                verify1 = "myUSC";
                verify2 = "Catalogue";
                verify3 = "Email";
                verify4 = "Docs";
                resources = Arrays.asList(verify1, verify2, verify3, verify4);
                break;
            case "wu-llm":
                verify1 = "MyLaw";
                verify2 = "E-Mail";
                verify3 = "WebSTAC";
                verify4 = "Career Services";
                verify5 = "Library Home Page";
                verify6 = "Library Database";
                resources = Arrays.asList(verify1, verify2, verify3, verify4, verify5, verify6);
                break;
            case "unc-mpa":
                verify1 = "MyUNC";
                verify2 = "Heelmail - UNC Email";
                verify3 = "UNC Onyen";
                verify4 = "Faculty Profiles";
                verify5 = "Leadership Team";
                verify6 = "Daily Tarheel";
                resources = Arrays.asList(verify1, verify2, verify3, verify4, verify5, verify6);
                break;
            case "au-mir":
                verify1 = "My AU";
                verify2 = "Email";
                verify3 = "Faculty Profiles";
                resources = Arrays.asList(verify1, verify2, verify3);
                break;
            case "gwu-mph":
                verify1 = "Email";
                verify2 = "Gweb";
                verify3 = "Colonial Central";
                verify4 = "Himmelfarb Health Sciences Library";
                verify5 = "Public Health & Health Services Portal";
                verify6 = "SPHSS Jobs Database";
                verify7 = "Writing Center";
                verify8 = "University Counseling Center";
                verify9 = "Career Center";
                verify10 = "Financing Your Education";
                verify11 = "Disability Support Services";
                verify12 = "The GW Bookstore";
                resources = Arrays.asList(verify1, verify2, verify3, verify4, verify5, verify6, verify7,
                        verify8, verify9, verify10, verify11, verify12);
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

            //To be implemented based on discussion
            /*List<WebElement> lists = driver.findElement(By.className("topnav_dropdown")).findElements(By.tagName("li"));
             for (WebElement list : lists) {
             }*/

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
                    case "myUSC":
                    case "Catalogue":
                    case "WebSTAC":
                    case "Writing Center":
                        ip.isTitleContains(driver, resource);
                        break;
                    case "Connect Carolina":
                    case "MyUNC":
                        ip.isTitlePresent(driver, "MyUNC : UNC Campus !Portal");
                        break;
                    case "KF Intranet":
                        ip.isTitlePresent(driver, "Microsoft ISA Server 2006");
                        break;
                    case "UNC Onyen":
                        ip.isTitleContains(driver, "Onyen");
                        break;
                    case "Faculty Profiles":
                        if (program.contains("unc-mba")) {
                            ip.isTitlePresent(driver, "Faculty Member Profiles | MBA@UNC");
                        } else if (program.contains("unc-mba")) {
                            ip.isTitlePresent(driver, "Faculty and Staff");
                        } else if (program.contains("au-mir")) {
                            ip.isTitlePresent(driver, "Faculty | SIS | American University");
                        }
                        break;
                    case "Leadership Team":
                        if (program.contains("unc-mba")) {
                            ip.isTitlePresent(driver, "Kenan Flagler Business School Leadership Team | MBA@UNC");
                        } else {
                            ip.isTitlePresent(driver, "Chapel Hill School of Government Leadership Team | MPA@UNC");
                        }
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
                        ip.isTitlePresent(driver, "Useful Documents and Quick Links - School of Nursing & Health Studies");
                        break;
                    case "Email":
                        if (program.contains("usc-msw") || program.contains("usc-mat")) {
                            ip.isTitlePresent(driver, "USC Web Mail");
                        } else if (program.contains("au-mir")) {
                            ip.isTitlePresent(driver, "American University");
                        } else if (program.contains("gwu-mph")) {
                            ip.isTitlePresent(driver, "Email Log-In - MyGW - "
                                    + "The George Washington University Web Portal");
                        }
                        break;
                    case "Docs":
                        ip.isTitlePresent(driver, "USCnet Login");
                        break;
                    case "MyLaw":
                        ip.isTitlePresent(driver, "MyLaw Login");
                        break;
                    case "E-Mail":
                        ip.isTitlePresent(driver, "Welcome to GO WUSTL | go.wustl.edu | Washington University in St. Louis");
                        break;
                    case "Career Services":
                        ip.isTitlePresent(driver, "WULS: Career Services Overview");
                        break;
                    case "Library Home Page":
                        ip.isTitlePresent(driver, "WULS: Law Library");
                        break;
                    case "Library Database":
                        ip.isTitlePresent(driver, "WULS: Law Library and Technology");
                        break;
                    case "Heelmail - UNC Email":
                        ip.isTitlePresent(driver, "UNC-Chapel Hill Single Sign-On");
                        break;
                    case "Daily Tarheel":
                        ip.isTitlePresent(driver, "The Daily Tar Heel :: Serving UNC students and the community since 1893");
                        break;
                    case "My AU":
                        ip.isTitlePresent(driver, "myAU Portal Login");
                        break;
                    case "Gweb":
                        ip.isTitlePresent(driver, "Banner Secured Area");
                        break;
                    case "Colonial Central":
                        ip.isTitlePresent(driver, "COLONIAL CENTRAL");
                        break;
                    case "Himmelfarb Health Sciences Library":
                        ip.isTitlePresent(driver, "Himmelfarb Library Homepage");
                        break;
                    case "Public Health & Health Services Portal":
                        ip.isTitlePresent(driver, "Public Health&Health Services Portal "
                                + "- Education - Himmelfarb Library");
                        break;
                    case "SPHSS Jobs Database":
                        ip.isTitlePresent(driver, "Job Database | Career Connection "
                                + "| School of Public Health and Health Services "
                                + "| George Washington University");
                        break;
                    case "University Counseling Center":
                        //TBD
                        //ip.isTitlePresent(driver, "University Counseling Center - The George Washington University");
                        ip.isTitlePresent(driver, "University Counseling Center "
                                + "| Division of Student Affairs "
                                + "| The George Washington University");
                        break;
                    case "Career Center":
                        ip.isTitlePresent(driver, "The GW Career Center "
                                + "- The George Washington University");
                        break;
                    case "Financing Your Education":
                        ip.isTitlePresent(driver, "Costs & Financial Planning | The George Washington University");
                        break;
                    case "Disability Support Services":
                        ip.isTitlePresent(driver, "Disability Support Services "
                                + "- The George Washington University");
                        break;
                    case "The GW Bookstore":
                        ip.isTitlePresent(driver, "GW Bookstore : Shop the George Washington University "
                                + "Bookstore For New & Used Textbooks, Rent Textbooks, "
                                + "Digital Textbooks, Apparel, Gifts & Supplies");
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
