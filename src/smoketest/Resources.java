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
    String vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6, vrfy7,
            vrfy8, vrfy9, vrfy10, vrfy11, vrfy12, vrfy13;
    List<String> resources;

    /**
     * Verify Resources
     */
    public void verifyResources() {
        switch (program) {
            case "unc-mba":
                vrfy1 = "Career Connections";
                vrfy2 = "Connect Carolina";
                vrfy3 = "KF Intranet";
                vrfy4 = "UNC Onyen";
                vrfy5 = "Faculty Profiles";
                vrfy6 = "Leadership Team";
                vrfy7 = "KF Email";
                resources = Arrays.asList(vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6, vrfy7);
                break;
            case "gu-msn":
                vrfy1 = "HoyaMail";
                vrfy2 = "Library";
                vrfy3 = "MyAccess";
                vrfy4 = "NetID Mgmt";
                vrfy5 = "Handbooks & Info";
                resources = Arrays.asList(vrfy1, vrfy2, vrfy3, vrfy4, vrfy5);
                break;
            case "usc-msw":
            case "usc-mat":
                vrfy1 = "myUSC";
                vrfy2 = "Catalogue";
                vrfy3 = "Email";
                vrfy4 = "Docs";
                resources = Arrays.asList(vrfy1, vrfy2, vrfy3, vrfy4);
                break;
            case "wu-llm":
                vrfy1 = "MyLaw";
                vrfy2 = "E-Mail";
                vrfy3 = "WebSTAC";
                vrfy4 = "Career Services";
                vrfy5 = "Library Home Page";
                vrfy6 = "Library Database";
                resources = Arrays.asList(vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6);
                break;
            case "unc-mpa":
                vrfy1 = "MyUNC";
                vrfy2 = "Heelmail - UNC Email";
                vrfy3 = "UNC Onyen";
                vrfy4 = "Faculty Profiles";
                vrfy5 = "Leadership Team";
                vrfy6 = "Daily Tarheel";
                resources = Arrays.asList(vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6);
                break;
            case "au-mir":
                vrfy1 = "My AU";
                vrfy2 = "Email";
                vrfy3 = "Faculty Profiles";
                resources = Arrays.asList(vrfy1, vrfy2, vrfy3);
                break;
            case "gwu-mph":
                vrfy1 = "Email";
                vrfy2 = "Gweb";
                vrfy3 = "Colonial Central";
                vrfy4 = "Himmelfarb Health Sciences Library";
                vrfy5 = "Public Health & Health Services Portal";
                vrfy6 = "SPHSS Jobs Database";
                vrfy7 = "Writing Center";
                vrfy8 = "University Counseling Center";
                vrfy9 = "Career Center";
                vrfy10 = "Financing Your Education";
                vrfy11 = "Disability Support Services";
                vrfy12 = "The GW Bookstore";
                resources = Arrays.asList(vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6, vrfy7);
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
                        ip.isTitlePresent(driver, "University Counseling Center "
                                + "- The George Washington University");
                        break;
                    case "Career Center":
                        ip.isTitlePresent(driver, "The GW Career Center "
                                + "- The George Washington University");
                        break;
                    case "Financing Your Education":
                        ip.isTitlePresent(driver, "Costs & Financial Planning  "
                                + "| The George Washington University");
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
