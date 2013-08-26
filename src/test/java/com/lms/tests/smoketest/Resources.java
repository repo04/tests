/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.smoketest;

import com.lms.tests.runThrghTestNG.BaseClass;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 *
 *
 */
public class Resources extends BaseClass {

    String resource1, resource2, resource3, resource4, resource5, resource6, resource7,
            resource8, resource9, resource10, resource11, resource12;
    String href1, href2, href3, href4, href5, href6, href7, href8, href9, href10,
            href11, href12, href13;
    List<String> resources = new ArrayList<>();
    List<String> hrefs = new ArrayList<>();
    int count;

    /**
     * Verify Resources
     */
    public void verifyResources() {
        switch (program) {
            case "unc-mba":
                //Jira Ticket not located
                count = 7;
                resource1 = "Career Connections";
                resource2 = "Connect Carolina";
                resource3 = "KF Intranet";
                resource4 = "UNC Onyen";
                resource5 = "Faculty Profiles";
                resource6 = "Leadership Team";
                resource7 = "KF Email";
                addToResourceList(resource1, resource2, resource3, resource4, resource5, resource6, resource7);

                href1 = "https://www.2nc.unc.edu/local/symplicity.php";
                href2 = "http://www.unc.edu/myunc/";
                href3 = "https://onlinemba.kenan-flagler.unc.edu/Pages/default.aspx"; //LMSII-1549
                href4 = "https://onyen.unc.edu/cgi-bin/unc_id/services";
                href5 = "http://onlinemba.unc.edu/about/faculty-profiles/";
                href6 = "http://onlinemba.unc.edu/about/leadership-team/";
                href7 = "https://webmail.business.unc.edu/owa";
                addToHrefList(href1, href2, href3, href4, href5, href6, href7);
                break;
            case "gu-msn":
                //Jira Ticket LMSII-622
                count = 5;
                resource1 = "HoyaMail";
                resource2 = "Library";
                resource3 = "MyAccess";
                resource4 = "NetID Mgmt";
                resource5 = "Handbooks & Info";
                addToResourceList(resource1, resource2, resource3, resource4, resource5);

                href1 = "http://apps.georgetown.edu/"; // Updated LMSII-2110
                href2 = "http://dml.georgetown.edu/";
                href3 = "https://myaccess.georgetown.edu/pls/bninbp/twbkwbis.P_WWWLogin";
                href4 = "https://netid-mgmt.georgetown.edu/";
                href5 = "http://nhs.georgetown.edu/students/graduate/forms/#OnlineGraduateStudents"; // Updated LMSII-1929
                addToHrefList(href1, href2, href3, href4, href5);
                break;
            case "usc-msw":
            case "usc-mat":
                //To be verified
                count = 4;
                resource1 = "myUSC";
                resource2 = "Catalogue";
                resource3 = "Email";
                resource4 = "Docs";
                addToResourceList(resource1, resource2, resource3, resource4);

                href1 = "http://my.usc.edu/";
                href2 = "http://www.usc.edu/dept/publications/cat2011/";
                href3 = "https://email.usc.edu/";
                href4 = "http://docs.google.com/a/usc.edu/";
                addToHrefList(href1, href2, href3, href4);
                break;
            case "unc-mpa":
                count = 6;
                resource1 = "MyUNC";
                resource2 = "Heelmail - UNC Email";
                resource3 = "UNC Onyen";
                resource4 = "UNC Library";
                resource5 = "Faculty Profiles";
                resource6 = "Leadership Team";
                resource7 = "Daily Tarheel";
                addToResourceList(resource1, resource2, resource3, resource4, resource5, resource6);

                href1 = "http://www.unc.edu/myunc/";
                href2 = "http://heelmail.unc.edu/";
                href3 = "https://onyen.unc.edu/cgi-bin/unc_id/services";
                href4 = "http://www.lib.unc.edu/";
                href5 = "http://onlinempa.unc.edu/academics/faculty-and-staff/";
                href6 = "http://onlinempa.unc.edu/about/leadership-team/";
                href7 = "http://www.dailytarheel.com/";
                addToHrefList(href1, href2, href3, href4, href5, href6);
                break;
            case "wu-llm":
                count = 6;
                resource1 = "MyLaw";
                resource2 = "E-Mail";
                resource3 = "WebSTAC";
                resource4 = "Career Services";
                resource5 = "Library Home Page";
                resource6 = "Library Database";
                addToResourceList(resource1, resource2, resource3, resource4, resource5, resource6);

                href1 = "https://mylaw.wustl.edu/";
                href2 = "http://go.wustl.edu/";
                href3 = "https://acadinfo.wustl.edu/";
                href4 = "http://law.wustl.edu/career_services/";
                href5 = "http://law.wustl.edu/library";
                href6 = "http://law.wustl.edu/library/pages.aspx?id=7813";
                addToHrefList(href1, href2, href3, href4, href5, href6);
                break;
            case "au-mir":
                count = 3;
                resource1 = "My AU";
                resource2 = "Email";
                resource3 = "Faculty Profiles";
                addToResourceList(resource1, resource2, resource3);

                href1 = "http://myau.american.edu/";
                href2 = "http://mail.student.american.edu/";
                href3 = "http://www.american.edu/sis/faculty";
                addToHrefList(href1, href2, href3);
                break;
            case "gwu-mph":
                count = 12;
                resource1 = "Email >";
                resource2 = "Gweb >";
                resource3 = "Colonial Central >";
                resource4 = "Himmelfarb Health Sciences Library >";
                resource5 = "Public Health & Health Services Portal >";
                resource6 = "SPHSS Jobs Database >";
                resource7 = "Writing Center >";
                resource8 = "University Counseling Center >";
                resource9 = "Career Center >";
                resource10 = "Financing Your Education >";
                resource11 = "Disability Support Services >";
                resource12 = "The GW Bookstore >";
                addToResourceList(resource1, resource2, resource3, resource4, resource5, resource6, resource7,
                        resource8, resource9, resource10, resource11, resource12);

                href1 = "https://my.gwu.edu/mod/email/?";
                href2 = "https://banweb.gwu.edu/PRODCartridge/twbkwbis.P_WWWLogin";
                href3 = "http://colonialcentral.gwu.edu/";
                href4 = "http://www.gwumc.edu/library/";
                href5 = "http://www.gwumc.edu/library/portals/pubhlthhs/educate.cfm";
                href6 = "http://sphhs.gwu.edu/studentres/careers/jobs/index.cfm";
                href7 = "http://www.gwu.edu/~gwriter/";
                href8 = "http://gwired.gwu.edu/counsel";
                href9 = "http://gwired.gwu.edu/career/";
                href10 = "http://www.gwu.edu/costs-financial-planning";
                href11 = "http://gwired.gwu.edu/dss/students/";
                href12 = "http://www.bkstr.com/webapp/wcs/stores/servlet/StoreCatalogDisplay?"
                        + "langId=-1&storeId=10370&demoKey=d&catalogId=10001";
                addToHrefList(href1, href2, href3, href4, href5, href6, href7,
                        href8, href9, href10, href11, href12);
                break;
            case "corp-son":
                count = 2;
                resource1 = "Semester Online Twitter Page >";
                resource2 = "Semester Online Facebook Page >";
                addToResourceList(resource1, resource2);

                href1 = "https://twitter.com/SemesterOnline";
                href2 = "https://www.facebook.com/SemesterOnline";
                addToHrefList(href1, href2);
                break;
            case "sc-msn":
                count = 3;
                resource1 = "Simmons College Facebook Page";
                resource2 = "Simmons College Twitter Page";
                resource3 = "Simmons College Youtube Page";
                addToResourceList(resource1, resource2, resource3);

                href1 = "https://www.facebook.com/SimmonsNursingOnline";
                href2 = "https://twitter.com/SimmonsNursing";
                href3 = "https://www.youtube.com/user/SimmonsNursingOnline";
                addToHrefList(href1, href2, href3);
                break;
            default:
                Utility.illegalStateException("No program available: " + program);
        }
        resourceWindow(count, resources, hrefs);
    }

    private void addToResourceList(String... resourceNames) {
        for (String resourceName : resourceNames) {
            resources.add(resourceName);
        }
    }

    private void addToHrefList(String... hrefLinks) {
        for (String hrefLink : hrefLinks) {
            hrefs.add(hrefLink);
        }
    }

    /**
     *
     * @param i
     * @param resources
     * @param hrefs
     */
    private void resourceWindow(int i, List<String> resources, List<String> hrefs) {
        List<WebElement> lists = driver.findElement(By.className("topnav_dropdown")).findElements(By.tagName("li"));

        //Navigating through all Link lists, verifying hrefs respective to Resource Text
        int x = 0;
        if (i == lists.size()) {
            lst:
            for (WebElement list : lists) {
                res:
                for (String resource : resources) {
                    if (resource.equals(list.findElement(By.tagName("a")).getText())) {
                        hrf:
                        for (String href : hrefs) {
                            if (href.equals(list.findElement(By.tagName("a")).getAttribute("href"))) {
                                break res;
                            } else {
                                Utility.illegalStateException("Mismatch in href --> Actual: '"
                                        + list.findElement(By.tagName("a")).getAttribute("href") + "', Expected: '" + href
                                        + "' for Resource: '" + resource + "'");
                            }
                        }
                    } else {
                        Utility.illegalStateException("Mismatch in resource --> Actual: '"
                                + list.findElement(By.tagName("a")).getText() + "', Expected: '" + resource + "'");
                    }
                }
                resources.remove(x);
                hrefs.remove(x);
            }
        }
    }
}
