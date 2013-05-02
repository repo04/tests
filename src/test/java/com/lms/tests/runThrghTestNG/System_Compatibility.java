/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.lms.tests.smoketest.Actions;


public class System_Compatibility {

    Actions a = new Actions();

    /**
     * Navigate to SystemCompatiblity Page
     */
    @BeforeClass(groups = {"prerequisite"})
    public void navigateToSystemCompatibilityPage() throws Exception {
        a.navigateToSystemCompatiblity();
    }
    
    /**
     * Verify the content of system Compatiblity Page
     *
     * @throws Exception
     */
    @Test(groups = {"systemCompatiblity.contentVerify"})
    public void testSystemCompabilityUIVerify() throws Exception {
        a.systemCompabilityUIVerify();
    }
    
    /**
     * Verifies content of Step 1: Component Compatibility Check
     *
     * @throws Exception
     */
    @Test(groups = {"systemCompatiblity.contentVerify"})
    public void testComponentCompatibilityUIVerify() throws Exception {
        a.systemCompabilitycomponentCompatibilityUIVerify();
    }
    
    /**
     * Verifies content of Step 2: Meeting Connection Diagnostic
     *
     * @throws Exception
     */
    @Test(groups = {"systemCompatiblity.contentVerify"})
    public void testSystemCompabilityMeetingConnectionDiagnosticUIVerify() throws Exception {
        a.systemCompabilitymeetingConnectionDiagnosticUIVerify();
    }
    
    /**
     * Verifies content of Step 2: Meeting Connection Diagnostic
     *
     * @throws Exception
     */
    @Test(groups = {"systemCompatiblity.faqSectionUIVerify"})
    public void testSystemCompabilityVerifyQuestionsAndBrowserCompatibleIcons() throws Exception {
        a.systemCompabilityVerifyQuestionsAndBrowserCompatibleIcons();
    }
    
    /**
     * Verify content and functionality of Express Uploader
     *
     * @throws Exception
     */
    @Test(groups = {"systemCompatiblity.expressUploader"})
    public void testSystemCompabilityExpressUploader() throws Exception {
        a.systemCompabilityexpressUploader();
    }
    
    /**
     * Verify System Compatibility Page - mobile Support Section UI
     *
     * @throws Exception
     */
    @Test(groups = {"systemCompatiblity.mobileSupportUIVerify"})
    public void testSystemCompabilityMobileSupportSectionUIVerify() throws Exception {
        a.systemCompabilitymobileSupportSectionUIVerify();
    }
 
    /**
     * Verifies content of Mobile Application section
     *
     * @throws Exception
     */
    @Test(groups = {"systemCompatiblity.mobileApplicationUIVerify"})
    public void testSystemCompabilitymobileApplicationsUIVerify() throws Exception {
        a.systemCompabilitymobileApplicationsUIVerify();
    }
    
    /**
     * Verify the number of "back to top" and "more info" links available and
     * are enabled or not on System Compatibility Page
     *
     * throws Exception
     */
    @Test(groups = {"systemCompatiblity.backToTopAndMoreInfoLinkVerify"})
    public void testSystemCompabilityVerifyBackToTopAndMoreInfoLinks()throws Exception {
        a.systemCompabilityVerifyBackToTopAndMoreInfoLinks();
    }
} 
