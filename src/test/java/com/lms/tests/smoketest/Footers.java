/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.smoketest;

import com.lms.tests.runThrghTestNG.BaseClass;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 *
 */
public class Footers extends BaseClass {

    String verify1, verify2, verify3, verify4, verify5, verify6;
    List<String> footers;
    String privacyConsent = null;
    String privacyContact = null;
    String aboutUS = null;
    String termsOfAgreement = null;
    String contactUSLink = null;
    String contactUSTollFree = null;

    /**
     * Verify Footers
     */
    void verifyFooters() {
        switch (program) {
            case "unc-mba":
                verify1 = "University of North Carolina at Chapel Hill";
                privacyConsent = "By using the 2NC Website, you consent to our Privacy Policy.";
                privacyContact = "Should you have other questions or concerns about this Privacy Policy or "
                        + "anything within it, please email us at privacy@2U.com or write to us "
                        + "at UNC Executive MBA Privacy Officer, 8201 Corporate Drive, "
                        + "Suite 190, Landover, MD 20785.";
                aboutUS = "The UNC Kenan-Flagler Business School is one of the world's premier centers "
                        + "for graduate study in business administration. At UNC Kenan-Flagler you gain "
                        + "skills that make you highly effective in your career the first few years "
                        + "after you graduate, but you also develop leadership skills that prepare you "
                        + "for long-term success.";
                termsOfAgreement = "2NC CAMPUS TERMS OF USE AGREEMENT";
                contactUSLink = "onlineMBAstudentsupport@unc.edu";
                contactUSTollFree = "1-877-986-2622";
                break;
            case "gu-msn":
                verify1 = "Georgetown University";
                privacyConsent = "By using the " + xpv.getTokenValue(program + "SupportName")
                        + " Website, you consent to our Privacy Policy.";
                privacyContact = "Should you have other questions or concerns about this Privacy Policy or "
                        + "anything within it, please email us at privacy@2U.com or write to us "
                        + "at Georgetown NHS Privacy Officer, 8201 Corporate Drive, "
                        + "Suite 190, Landover, MD 20785.";
                aboutUS = "Nursing@Georgetown is the online Master of Science degree in Nursing program "
                        + "from Georgetown University School of Nursing & Health Studies. Georgetown "
                        + "University School of Nursing & Health Studies (NHS) is dedicated to its "
                        + "mission, which, in part, reads - \"to improve the health and well-being of "
                        + "all people.\" A part of Georgetown University Medical Center, NHS advances "
                        + "that mission through innovative, nationally ranked academic programs that "
                        + "engage faculty and students in local, national, and global communities.";
                termsOfAgreement = xpv.getTokenValue(program + "SupportName") + " CAMPUS TERMS OF USE AGREEMENT";
                contactUSLink = "onlinenursingstudentsupport@georgetown.edu";
                contactUSTollFree = "(877) 503-4676";
                break;
            case "usc-msw":
                verify1 = "University of Southern California";
                privacyConsent = "By using the " + xpv.getTokenValue(program + "SupportName")
                        + " Website, you consent to our Privacy Policy.";
                privacyContact = "Should you have other questions or concerns about this Privacy Policy or "
                        + "anything within it, please email us at privacy@2U.com or write to us "
                        + "at MSW@USC Privacy Officer, 8201 Corporate Drive, "
                        + "Suite 190, Landover, MD 20785.";
                aboutUS = "With its reputation for educational excellence, "
                        + "cultural diversity and research funding, the University of Southern California's "
                        + "School of Social Work prepares students for leadership roles in public and private "
                        + "organizations that serve individuals, families and communities in need. "
                        + "A pioneer in innovative programs of interdisciplinary study, the school is the "
                        + "highest ranked school of social work in Southern California and among the top 10 "
                        + "in the nation.";
                termsOfAgreement = "VIRTUAL ACADEMIC CENTER TERMS OF USE AGREEMENT";
                contactUSLink = "mswhelp@usc.edu";
                contactUSTollFree = "1-877-455-4679 (-4MSW)";
                break;
            case "usc-mat":
                verify1 = "University of Southern California";
                privacyConsent = "By using the " + xpv.getTokenValue(program + "SupportName")
                        + " Website, you consent to our Privacy Policy.";
                privacyContact = "Should you have other questions or concerns about this Privacy Policy or "
                        + "anything within it, please email us at privacy@2U.com or write to us "
                        + "at USC Rossier Privacy Officer, 8201 Corporate Drive, "
                        + "Suite 190, Landover, MD 20785.";
                aboutUS = "The USC Rossier School of Education is one of the world's premier centers for "
                        + "graduate study in urban education. We prepare and develop educational leaders "
                        + "who are change agents, we lead the search for innovative, efficacious and just "
                        + "solutions through collaborative action research, and we create mutually beneficial "
                        + "partnerships to rethink curriculum, develop sound policy and improve educational "
                        + "environments. Because of our leadership work in urban education here and around the "
                        + "world we are preparing the finest educators to succeed in any and ALL educational "
                        + "circumstances.";
                termsOfAgreement = xpv.getTokenValue(program + "SupportName") + " CAMPUS TERMS OF USE AGREEMENT";
                contactUSLink = "rossier.help@usc.edu";
                contactUSTollFree = "1-888-628-5041";
                break;
            case "wu-llm":
                verify1 = "Washington University in St. Louis";
                privacyConsent = "You consent to our Privacy Policy by using the Website.";
                privacyContact = "Should you have other questions or concerns about this Privacy Policy or "
                        + "anything within it, please email us at privacy@2U.com.";
                aboutUS = "The @WashULaw Master of Laws (LL.M.) program is a dynamic online LL.M. "
                        + "program offered by the Washington University School of Law, part of the "
                        + "elite Washington University in St. Louis. Taught by highly respected faculty, "
                        + "this program is designed to help the next generation of attorneys achieve "
                        + "their career goals and learn skills to succeed in the rapidly changing field "
                        + "of international business.";
                termsOfAgreement = xpv.getTokenValue(program + "SupportName") + " CAMPUS TERMS OF USE AGREEMENT";
                contactUSLink = "studentsupport@onlinelaw.wustl.edu";
                contactUSTollFree = "1-855-927-4859 (-WashULW)";
                break;
            case "unc-mpa":
                verify1 = "University of North Carolina at Chapel Hill";
                privacyConsent = "By using the " + xpv.getTokenValue(program + "SupportName")
                        + " Website, you consent to our Privacy Policy.";
                privacyContact = "Should you have other questions or concerns about this Privacy Policy or "
                        + "anything within it, please email us at privacy@2U.com or write to us "
                        + "at UNC UNC@MPA Privacy Officer, 8201 Corporate Drive, "
                        + "Suite 190, Landover, MD 20785.";
                aboutUS = "The UNC School of Government is one of the world's premier centers for "
                        + "graduate study in public administration. The UNC MPA program attracts "
                        + "applicants with a desire to serve and strong potential for leadership. "
                        + "At MPA@UNC, you will develop the skills necessary to become a public service "
                        + "leader. The UNC MPA program derives part of its strength from the School of "
                        + "Government's unique position as the nation's largest university-based "
                        + "resource for local governments and from the regular engagement between "
                        + "the School's faculty and public officials at all levels. UNC School of "
                        + "Government faculty members are recognized experts to whom public officials "
                        + "at every level frequently turn for training, advice, and research.";
                termsOfAgreement = xpv.getTokenValue(program + "SupportName") + " CAMPUS TERMS OF USE AGREEMENT";
                contactUSLink = "studentsupport@onlinempa.unc.edu";
                contactUSTollFree = "1-855-672-8621 (-MPA-UNC1)";
                break;
            case "au-mir":
                verify1 = "American University";
                privacyConsent = "You consent to our Privacy Policy by using the Website.";
                privacyContact = "Should you have other questions or concerns about this Privacy Policy or "
                        + "anything within it, please email us at privacy@2U.com.";
                aboutUS = "International Relations Online is the innovative, online Master of Arts in "
                        + "International Relations degree offered by American University's School of "
                        + "International Service (SIS). International Relations Online is designed to "
                        + "prepare service-minded professionals with the skills needed to impact our "
                        + "increasingly complex, inter-connected world.";
                //Problem
                termsOfAgreement = "Terms of Use Agreement";
                contactUSLink = "studentsupport@ironline.american.edu";
                contactUSTollFree = "1-855-307-2847 (-AUIR)";
                break;
            case "gwu-mph":
                verify1 = "George Washington University";
                privacyConsent = "By using the " + xpv.getTokenValue(program + "SupportName")
                        + " Website, you consent to our Privacy Policy.";
                privacyContact = "Should you have other questions or concerns about this Privacy Policy or "
                        + "anything within it, please email us at privacy@2U.com or write to us "
                        + "at Master of Public Health";
                aboutUS = "Welcome to MPH@GW";
                termsOfAgreement = xpv.getTokenValue(program + "SupportName") + " WEBSITE TERMS OF USE AGREEMENT";
                contactUSLink = "studentsupport@publichealthonline.gwu.edu";
                contactUSTollFree = "1-855-494-6740 (-GW4-MPH0)";
                break;
            case "corp-son":
                verify1 = "Semester Online";
                privacyConsent = "By using the Semester Online Website, you consent to our Privacy Policy.";
                privacyContact = "Should you have other questions or concerns about this Privacy Policy or "
                        + "anything within it, please email us at privacy@2U.com or write to us "
                        + "at Semester Online Privacy Officer, 8201 Corporate Drive, "
                        + "Suite 190, Landover, MD 20785.";
                aboutUS = "Imagine a new way to experience college…";
                termsOfAgreement = "SEMESTER ONLINE WEBSITE TERMS OF USE AGREEMENT";
                contactUSLink = "StudentSupport@semesteronline.org";
                contactUSTollFree = "855-896-4493";
                break;
            case "sc-msn":
                verify1 = "Simmons College";
                privacyConsent = "By using the " + xpv.getTokenValue(program + "SupportName")
                        + " Website, you consent to our Privacy Policy.";
                privacyContact = "Should you have other questions or concerns about this Privacy Policy or "
                        + "anything within it, please email us at privacy@2U.com or write to us "
                        + "at Master of Science in Nursing Privacy Officer, 8201 Corporate Drive, "
                        + "Suite 190, Landover, MD 20785.";
                aboutUS = "Nursing@Simmons is an innovative Master of Science in Nursing program delivered online "
                        + "for aspiring family nurse practitioners (FNPs). Designed for licensed registered nurses "
                        + "with a bachelor’s degree, the online FNP program prepares them to make an even greater "
                        + "impact in their field by becoming clinical experts who can provide premium health care "
                        + "to diverse populations across the lifespan. Students collaborate on a state-of-the-art "
                        + "online learning platform that uses cutting-edge technology and familiar social networking "
                        + "tools to create an intimate and interactive learning environment. Clinical placements "
                        + "are carefully selected to maximize the learning experience and allow students to gain "
                        + "hands-on, practical skills without having to relocate. Nursing@Simmons features the "
                        + "same academically rigorous curriculum as the on-campus program, and courses are designed "
                        + "and taught by the same faculty members who teach on campus.";
                termsOfAgreement = xpv.getTokenValue(program + "SupportName") + " WEBSITE CAMPUS TERMS OF USE AGREEMENT";
                contactUSLink = "studentsupport@onlinenursing.simmons.edu";
                contactUSTollFree = "855-465-7466";
        }
        verify2 = "Terms And Conditions";
        verify3 = "Privacy Statement";
        verify4 = "About";
        verify5 = "Contact Us";
        verify6 = "Student Support";
        footers = Arrays.asList(verify1, verify2, verify3, verify4, verify5, verify6);
        verifyTitle(footers);
    }

    private void verifyTitle(List<String> footers) {
        int i = 1;
        String HandleBefore = driver.getWindowHandle();

        for (String footer : footers) {
            if (i == 1) {
                new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span/b[contains(text(),'" + footer + "')]")));
            } else if (i == 2 || i == 3) {
                ip.isTextPresentByXPATH(driver, "//div[7]/div/span[" + i + "]/a", footer);
                driver.findElement(By.xpath("//div[7]/div/span[" + i + "]/a")).click();
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
                    ip.isTitleContains(driver, footer);
                    if (i == 2) {
                        ip.isTextPresentByXPATH(driver, xpv.getTokenValue(program + "TermsOfAgreementTitleXPATH"), termsOfAgreement);
                    } else {
                        ip.isTextPresentByXPATH(driver, xpv.getTokenValue(program + "PrvcyCnstXPATH"), privacyConsent);
                        ip.isTextPresentByXPATH(driver, xpv.getTokenValue(program + "PrvcyCntctXPATH"), privacyContact);
                    }
                    driver.close();
                    driver.switchTo().window(HandleBefore);
                } catch (TimeoutException e) {
                    driver.close();
                    driver.switchTo().window(HandleBefore);
                    throw e;
                }
            } else {
                ip.isTextPresentByXPATH(driver, "//div[7]/div/span[" + i + "]/a", footer);
                driver.findElement(By.xpath("//div[7]/div/span[" + i + "]/a")).click();
                try {
                    ip.isTitleContains(driver, footer);
                    if (i == 4) {
                        switch (program) {
                            case "wu-llm":
                            case "corp-son":
                                ip.isTextPresentByXPATH(driver, "//p", aboutUS);
                                break;
                            default:
                                ip.isTextPresentByCSS(driver, "div.desc", aboutUS);
                        }
                    } else if (i == 5) {
                        driver.findElement(By.linkText(contactUSLink));
                        String tollfree = driver.findElement(By.className("desc")).getText();
                        if (!tollfree.contains(contactUSTollFree)) {
                            driver.findElement(By.linkText("Home")).click();
                            Utility.illegalStateException("TollFree Number varies, Expected: "
                                    + contactUSTollFree + " - Actual : " + tollfree);
                        }
                    }
                } catch (TimeoutException e) {
                    driver.findElement(By.linkText("Home")).click();
                    throw e;
                }
                driver.findElement(By.linkText("Home")).click();
                ip.isElementClickableByXpath(driver, xpv.getTokenValue("wallPublishPanelXPATH"), 60);
            }
            i++;
        }
    }
}
