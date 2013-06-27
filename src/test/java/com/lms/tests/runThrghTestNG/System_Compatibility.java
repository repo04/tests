/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.lms.tests.smoketest.Actions;
import com.lms.tests.smoketest.Utility;
import org.testng.annotations.AfterClass;

public class System_Compatibility extends BaseClass {

    Actions a;

    /**
     * Navigate to SystemCompatibility Page
     */
    @BeforeClass(groups = {"prerequisite"})
    public void navigateToSystemCompatibilityPage() throws Exception {
        a = new Actions(getWebdriver());
        a.navigateToSystemCompatibility();
    }

    /**
     * Verify the content of system Compatibility Page
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "systemCompatibility.contentVerify"})
    public void testSystemCompatibilityUIVerify() throws Exception {
        a = new Actions(getWebdriver());
        a.systemCompatibilityUIVerify();
    }

    /**
     * Verifies content of Step 1: Component Compatibility Check
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "systemCompatibility.contentVerify"})
    public void testComponentCompatibilityUIVerify() throws Exception {
        a = new Actions(getWebdriver());
        a.systemCompatibilityComponentCompatibilityUIVerify();
    }

    /**
     * Verifies content of Step 2: Meeting Connection Diagnostic
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "systemCompatibility.contentVerify"})
    public void testSystemCompatibilityMeetingConnectionDiagnosticUIVerify() throws Exception {
        a = new Actions(getWebdriver());
        a.systemCompatibilityMeetingConnectionDiagnosticUIVerify();
    }

    /**
     * Verifies content of Step 2: Meeting Connection Diagnostic
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "systemCompatibility.faqSectionUIVerify"})
    public void testSystemCompatibilityVerifyQuestionsAndBrowserCompatibleIcons() throws Exception {
        a = new Actions(getWebdriver());
        a.systemCompatibilityVerifyQuestionsAndBrowserCompatibleIcons();
    }

    /**
     * Verify content and functionality of ExpressUploader
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "systemCompatibility.expressUploader"})
    public void testSystemCompatibilityExpressUploader() throws Exception {
        a = new Actions(getWebdriver());
        a.systemCompatibilityExpressUploader();
    }

    /**
     * Verify System Compatibility Page - mobile Support Section UI
     *
     * @throws Exception
     */
    /*@Test(groups = {"systemCompatibility.mobileSupportUIVerify"})
     public void testSystemCompatibilityMobileSupportSectionUIVerify() throws Exception {
     a.systemCompatibilityMobileSupportSectionUIVerify();
     }*/
    /**
     * Verifies content of Mobile Applications section
     *
     * @throws Exception
     */
    @Test(groups = {"regressionSmoke", "systemCompatibility.mobileApplicationsUIVerify"})
    public void testSystemCompatibilityMobileApplicationsUIVerify() throws Exception {
        a = new Actions(getWebdriver());
        a.systemCompatibilityMobileApplicationsUIVerify();
    }

    /**
     * Verify the number of "back to top" and "more info" links available and
     * are enabled or not on System Compatibility Page
     *
     * throws Exception
     */
    @Test(groups = {"regressionSmoke", "systemCompatibility.backToTopAndMoreInfoLinksVerify"})
    public void testSystemCompatibilityVerifyBackToTopAndMoreInfoLinks() throws Exception {
        a = new Actions(getWebdriver());
        a.systemCompatibilityVerifyBackToTopAndMoreInfoLinks();
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run, User logsOut
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"}, alwaysRun = true)
    public void testNavigateToHomePage() throws Exception {
        getWebdriver().get(getURL());
        Utility.verifyCurrentUrl(getWebdriver(), xpv.getTokenValue("loginPageURL"));
    }
}
