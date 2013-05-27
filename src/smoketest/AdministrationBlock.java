/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.util.List;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

public class AdministrationBlock extends BaseClass {

    String programName = BaseClass.program;
    String studentSupportMessage = "This is support message created through Automation by pesadmin";
    String studentLoginMessage = "This is login message created through Automation by pesadmin for student";
    String teacherLoginMessage = "This is login message created through Automation by pesadmin for teacher";
    String emailDomain = xpv.getTokenValue(programName + "EmailDomain");
    WebElement textArea;
    WebElement postAnnouncement;
    WebElement linkBtn;
    String textPost = null;
    Date now = new Date();
    String urlPost = null;

    /**
     * Verifies the UI of 2tor Site administration section
     */
    public void verifySiteAdministrationSection() {
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportSubHeaderXPATH"), "Student Support");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("reportSubHeaderXPATH"), "Report");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("adobeSubHeaderXPATH"), "ADOBE Connect Pro");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("workingGroupSubHeaderXPATH"), "Working Groups");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("socialGroupSubHeaderXPATH"), "Social Groups");
    }

    /**
     * Verifies the UI of Student Support section
     */
    public void verifySiteAdminStudentSupportSection() {
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("videoTutorialXPATH"), "Video Tutorials");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("supportMessageXPATH"), "Support Message");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("loginMessageXPATH"), "Login Message");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("userStickyNotesXPATH"), "User Sticky Notes");
    }

    /**
     * Verifies the UI and functionality of Video Tutorials
     */
    public void verifySiteAdminStudentSupportVideoTutorialsPage() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("byTagSearchBoxXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("searchButtonXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("uploadVideo2XPATH"));

        //Click on upload image
        Utility.actionBuilderClick(driver, "//td/a/img");
        ip.isElementPresentByID(driver, "drag-handle");
        ip.isTextPresentByCSS(driver, xpv.getTokenValue("uploadWindowTitleTxtXPATH"), "Upload Video");
    }

    /**
     * Verifies the UI and functionality of Support Message
     */
    public void verifySiteAdminStudentSupportMessagePage() {
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportLoginmessageLabelXPATH"), "Message");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("loginmessageBoxXPATH"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportmessageTextXPATH"), "This message is displayed on the");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportmessageTextXPATH"), ", just under the phone support schedule.");

        //The below lines of Code are related to posting of Support Message which affects all system users - so currently we are skipping this
        /*
         driver.findElement(By.xpath(xpv.getTokenValue("loginmessageBoxXPATH"))).clear();
         driver.findElement(By.xpath(xpv.getTokenValue("loginmessageBoxXPATH"))).sendKeys(studentSupportMessage);
         driver.findElement(By.xpath(xpv.getTokenValue("saveChangesButtonXPATH"))).click();
         driver.findElement(By.linkText("Faculty Support")).click();
         //Verifies the student support message through pesadmin which is set by the pes admin
         ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportPagePesAdminPostXPATH"), studentSupportMessage);
         */
    }

    /**
     * Verifies the UI and functionality of posting of login message to students
     */
    public void verifySiteAdminStudentSupportLoginMessagePage() {
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportLoginmessageLabelXPATH"), "Message");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("loginmessageBoxXPATH"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("LoginMessageShowCheckboxLabelXPATH"), "Show");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("LoginMessageShowCheckboxXPATH"));

        /**
         * The below lines of code affects all system users - so currently we
         * are skipping this
         *
         * new
         * Select(driver.findElement(By.xpath(xpv.getTokenValue("postToMembersDropdownXPATH")))).selectByVisibleText("Post
         * to Students"); ip.isElementPresentByXPATH(driver,
         * xpv.getTokenValue("loginmessageBoxXPATH"));
         * driver.findElement(By.xpath(xpv.getTokenValue("loginmessageBoxXPATH"))).clear();
         * driver.findElement(By.xpath(xpv.getTokenValue("loginmessageBoxXPATH"))).sendKeys(studentLoginMessage);
         * //Checks whether the show check box is selected or not if not then
         * select it Boolean
         * showchkbox=driver.findElement(By.xpath(xpv.getTokenValue("LoginMessageShowCheckboxXPATH"))).isSelected();
         * if(showchkbox == false) {
         * driver.findElement(By.xpath(xpv.getTokenValue("LoginMessageShowCheckboxXPATH"))).click();
         * } ip.isElementClickableByXpath(driver,
         * xpv.getTokenValue("saveChangesButtonXPATH"), 60);
         *
         * driver.findElement(By.xpath(xpv.getTokenValue("saveChangesButtonXPATH"))).click();
         */
    }

    /**
     * Verifies the UI and all fields/columns of course Roster Report
     */
    public void verifySiteAdminReportCourseRostersPage() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("categoryDropdownXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("coursesDropdownXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("searchByNameTxtBoxXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("searchBtnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("exportBtnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("courseColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("sectionColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("nameColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("emailColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("roleColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("guIDColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("lastLoginColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("programColumnXPATH"));

        //Verifies if the Category dropdown first value is selected or not
        WebElement element = new Select(driver.findElement(By.xpath(xpv.getTokenValue("categoryDropdownXPATH")))).getFirstSelectedOption();
        element.isSelected();

        //Verifies if the Category dropdown values- Active and Archive are selectable or not
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("categoryDropdownXPATH")))).selectByVisibleText("Active");
        WebElement active = new Select(driver.findElement(By.xpath(xpv.getTokenValue("categoryDropdownXPATH")))).getFirstSelectedOption();
        active.isSelected();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("categoryDropdownXPATH"), 30);
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("categoryDropdownXPATH")))).selectByVisibleText("Archive");
        WebElement archive = new Select(driver.findElement(By.xpath(xpv.getTokenValue("categoryDropdownXPATH")))).getFirstSelectedOption();
        archive.isSelected();
    }

    /**
     * Verifies the UI and elements of deleted Live Session
     */
    public void verifySiteAdminReportDeletedLiveSessionPage() {
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("dateFromLblTxtXPATH"), "Date From:");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("dateToLblTxtXPATH"), "Date To:");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("dateFromTxtBoxXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("dateToTxtBoxXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("filterBtnXPATH"));
        //ip.isElementPresentByXPATH(driver, xpv.getTokenValue("viewRecordingBtnXPATH"));
    }

    /**
     * Verifies the UI of "Email Not In Domain"
     */
    public void verifySiteAdminReportEmailNotInDomainPage() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("emailNotInDomainFirstNameXpath"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("emailNotInDomainEmailAddressXpath"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("emailNotInDomainCityXpath"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("emailNotInDomainCountryXpath"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("emailNotInDomainLastAcessXpath"));
    }

    /**
     * Verifies the UI of Report Settings
     */
    public void verifySiteAdministrationReportSection() {
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentServicesXPATH"), "Student Services");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentServicesConfigurationXPATH"), "Student Services Configuration");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("courseRosterXPATH"), "Course Rosters");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("wallMonitorXPATH"), "Wall Monitor");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("deletedLiveSessionXPATH"), "Deleted Live Session");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("emailNotInDomainXPATH"), "Email Not in Domain");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentEngagementReportXPATH"), "Student Engagement Report");
    }

    /**
     * Verifies that pes admin can not access the Student Services & student
     * Services Configuration reports and also verifies that error messages are
     * appearing or not
     */
    public void verifyGetAccessDeniedForStudentServicesAndConfigurationReports() {
        driver.findElement(By.linkText("Student Services")).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("deniedPermissionMessageXPATH"), "Sorry, but you do not currently have permissions to do that (coursereport/studentservices:view ???)");
        driver.findElement(By.xpath(xpv.getTokenValue("continueBtnXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("link2torAdminXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("link2torAdminXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("studentServicesConfigurationXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("deniedPermissionMessageXPATH"), "Access denied");
        driver.findElement(By.xpath(xpv.getTokenValue("continueBtnXPATH"))).click();
    }

    /**
     * Verifies the UI and fields/columns of Student Engagement Report
     */
    public void verifySiteAdminReportStudentEngagementReportPage() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("studentNameColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("studentEmailColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("lastLMSloginColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("lastLiveSessionColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("locationOfSessionColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("lastWallPostColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("locationOfPostColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("numberOfgroupsColumnXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("searchByNameTxtBoxXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("searchbuttonXPATH"));
    }

    /**
     * Verifies the University Domain Email IDs are not present in "Email Not In
     * Domain" list
     */
    public void verifyUniversityDomainNotPresentInEmailNotInDomainList() {
        int rows = driver.findElements(By.xpath("//*[@id='region-main']/div/table/tbody/tr")).size();
        System.out.println("rows: " + rows);
        for (int i = 1; i <= rows; i++) {
            new WebDriverWait(driver, 60).until(ExpectedConditions.not(ExpectedConditions.
                    textToBePresentInElement(By.xpath("//*[@id='region-main']/div/table/tbody/tr[" + i + "]/td[2]"), emailDomain)));
        }
    }

    /**
     * Verifies if pes admin is able to see the related sections to the courses
     * in the section drop down
     */
    public void verifySectionDropdownCourseRostersPage(String courseShortName, String groupCourse) {
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sectionColumnXPATH"), "Section", 60);
        ip.isElementClickableByXpath(driver, "//td[3]/div/div/a/img", 60);
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("coursesDropdownXPATH")))).selectByVisibleText(courseShortName);
        WebElement courseNameSelected = new Select(driver.findElement(By.xpath(xpv.getTokenValue("coursesDropdownXPATH")))).getFirstSelectedOption();
        courseNameSelected.isSelected();
        ip.isTextPresentByXPATH(driver, "//div/table/tbody/tr/td/div", courseShortName);
        ip.isElementClickableByXpath(driver, "//td[3]/div/div/a/img", 60);
        driver.findElement(By.xpath(xpv.getTokenValue("searchByNameTxtBoxXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("sectionDropdownXPATH"), 60);
        Select sectionDropdown = new Select(driver.findElement(By.xpath(xpv.getTokenValue("sectionDropdownXPATH"))));
        List<WebElement> options = sectionDropdown.getOptions();
        if (options.size() != 2) {
            Utility.illegalStateException("All sections dropdown size varies; Expected:2, Actual:" + options.size());
        }
        int size = 1;
        for (WebElement option : options) {
            if (size > 1) {
                if (!option.getText().equalsIgnoreCase(groupCourse)) {
                    Utility.illegalStateException("Group Section varies; Expected: '" + groupCourse
                            + "', Actual: '" + option.getText() + "'");
                }
            }
            size++;
        }
    }
    
    //Following functional test methods affect all system users - so currently we are skipping this
    /**
     * Pes admin disables the Student support message created previously
     *
     */
    /*
     public void disableStudentSupportMessageByPesAdmin() {
     ip.isElementPresentByXPATH(driver, xpv.getTokenValue("loginmessageBoxXPATH"));
     driver.findElement(By.xpath(xpv.getTokenValue("loginmessageBoxXPATH"))).clear();
     driver.findElement(By.xpath(xpv.getTokenValue("saveChangesButtonXPATH"))).click();
     }*/
    
    /**
     * Pes admin disables the login message created previously
     *
     */
    /*
     public void disableLoginMessageByPesAdmin() {
     //Pes admin clears the login message for students
     new Select(driver.findElement(By.xpath(xpv.getTokenValue("postToMembersDropdownXPATH")))).selectByVisibleText("Post to Students");
     driver.findElement(By.xpath(xpv.getTokenValue("loginmessageBoxXPATH"))).clear();
     //Pes admin clears the login message for faculty
     new Select(driver.findElement(By.xpath(xpv.getTokenValue("postToMembersDropdownXPATH")))).selectByVisibleText("Post to Faculty");
     driver.findElement(By.xpath(xpv.getTokenValue("loginmessageBoxXPATH"))).clear();
     ip.isElementPresentByXPATH(driver, xpv.getTokenValue("LoginMessageShowCheckboxXPATH"));
     //Checks whether the show check box is selected or not if yes then uncheck it
     Boolean showchkbox=driver.findElement(By.xpath(xpv.getTokenValue("LoginMessageShowCheckboxXPATH"))).isSelected();
     if(showchkbox == true)
     {
     driver.findElement(By.xpath(xpv.getTokenValue("LoginMessageShowCheckboxXPATH"))).click();
     }
     ip.isElementClickableByXpath(driver, xpv.getTokenValue("saveChangesButtonXPATH"), 60);
     driver.findElement(By.xpath(xpv.getTokenValue("saveChangesButtonXPATH"))).click();
     }
     */
    
    /**
     * Verifies the login message which is entered by the pes admin after login
     * with student
     */
    /*
     public void studentVerificationLoginMessage() {
     ip.isTextPresentByID(driver, "ext-gen79", studentLoginMessage);
     driver.findElement(By.id("ext-gen69")).click();
     }
     */
    
    /**
     * Verifies the support message which is set by the pes admin after login
     * with student
     */
    /*
     public void studentVerificationSupportMessage() {
     ip.isTextPresentByXPATH(driver, xpv.getTokenValue("studentSupportPagePesAdminPostXPATH"), studentSupportMessage,30);
     } */
    
    /**
     * Verifies functionality of posting of login message to faculty
     */
    /*
     public void teacherSupportSettingsLoginMessage() {
     new Select(driver.findElement(By.xpath(xpv.getTokenValue("postToMembersDropdownXPATH")))).selectByVisibleText("Post to Faculty");
     driver.findElement(By.xpath(xpv.getTokenValue("loginmessageBoxXPATH"))).clear();
     driver.findElement(By.xpath(xpv.getTokenValue("loginmessageBoxXPATH"))).sendKeys(teacherLoginMessage);
     //Checks whether the show check box is selected or not if not then select it
     Boolean showchkbox=driver.findElement(By.xpath(xpv.getTokenValue("LoginMessageShowCheckboxXPATH"))).isSelected();
     if(showchkbox == false)
     {
     driver.findElement(By.xpath(xpv.getTokenValue("LoginMessageShowCheckboxXPATH"))).click();
     }
     ip.isElementClickableByXpath(driver, xpv.getTokenValue("saveChangesButtonXPATH"), 60);

     //No notification is displayed on LMS once Message is saved. Jayita, this is a bug & I will get this fileds
     driver.findElement(By.xpath(xpv.getTokenValue("saveChangesButtonXPATH"))).click();
     } */
    
    /**
     * Verifies the login message which is entered by the pes admin after login
     * with faculty
     */
    /*
     public void facultyVerificationLoginMessage() {
     ip.isTextPresentByID(driver, "ext-gen79", teacherLoginMessage);
     driver.findElement(By.id("ext-gen69")).click();
     } */
    
    /**
     * Reverifies the login message does not appear after re-login with student
     */
    /*
     public void loginMessageAlertNotPresent() {
     boolean loginMessage=driver.getPageSource().contains(studentLoginMessage);
     if(loginMessage==true){
     driver.findElement(By.id("ext-gen69")).click();
     System.out.println("Login Message is Present on reverification");
     }
     else {
     System.out.println("Login Message is not Present on reverification");
     }
     }*/
    
    /**
     * Create & verify Sticky Notes Post Text
     *
     */
    /*
     public void studentSupportSettingsStickyNotesPostText() {
     setUpWallPost();
     linkBtn = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("stickyNotePostlnkBtnXPATH"))));
     List <WebElement> iframes = driver.findElements(By.tagName("iframe"));

     //Grabs the first iframe because the textArea is always the first iframe
     for (WebElement frame : iframes) {
     driver.switchTo().frame(frame.getAttribute("name"));
     break;
     }
     //Switch focus
     WebElement editableTxtArea = driver.switchTo().activeElement();
     this.textPost = "Sticky Note TextPost by pesadmin " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
     editableTxtArea.sendKeys(textPost);
     String stickyNotePostText= this.textPost;
     //Switches back to default focus
     driver.switchTo().defaultContent();
     //Click on post announcement link
     postAnnouncement.click();
     driver.findElement(By.id("ext-gen78")).click();
     driver.findElement(By.id("ext-gen81")).click();
     ip.isElementClickableByXpath(driver, "//table[@id='submit_id']/tbody/tr[2]/td[2]", 60);
     driver.findElement(By.xpath("//table[@id='submit_id']/tbody/tr[2]/td[2]")).click();
     //Verifies the text of currently created Sticky Notes Post Text
     ip.isTextPresentByCSS(driver, xpv.getTokenValue("textWallCSS"), stickyNotePostText);
     }
     */
    
    /**
     * Create & verify Sticky Notes Post URL
     */
    /* public void studentSupportSettingsStickyNotesPostURL() {
     setUpWallPost();
     linkBtn = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("linkBtnXPATH"))));
     DateFormat dateFormat;

     //Date need to be in specific format as Getinstance include special characters
     dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
     this.urlPost = "Sticky Note URLPost by pesadmin " + dateFormat.format(now) + ".com";
     linkBtn.click();
     WebElement linkTextBox = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div/input[3]")));
     //Through the below code we are making multiple number of retries so that if selenium is unable to set focus on WYSIWYG editor in 1 try, it will retry multiple times set in value "i"
     int i = 1;
     value:
     while (i < 6) {
     Utility.actionBuilderClick(driver, "//div/input[3]");
     linkTextBox.sendKeys(urlPost);
     linkTextBox.clear();
     linkTextBox.sendKeys("http://" + urlPost);
     if (i < 5) {
     try {
     new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//div/input[3]"), "http://" + urlPost));
     break value;
     } catch (TimeoutException e) {
     System.out.println("i: " + i);
     i++;
     }
     } else {
     Utility.illegalStateException("Selenium is unable to get focus on URL Textboxfield after multiple tries also, this is an automation limitation");
     }
     }
     postAnnouncement.click();
     //Click on end date calendar
     driver.findElement(By.id("ext-gen78")).click();
     driver.findElement(By.id("ext-gen81")).click();
     driver.findElement(By.xpath("//table[@id='submit_id']/tbody/tr[2]/td[2]")).click();
     //Verifies the text of currently created Sticky Notes Post URL
     ip.isElementPresentContainsTextByXPATH(driver, "http://" + urlPost);
     }
     */
    
    /**
     * Click on TextArea & enable post announcement link
     */
    /*
     public void setUpWallPost() {
     textArea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("userStickyNotesPostWallXPATH"))));
     for (String handle : driver.getWindowHandles()) {
     driver.switchTo().window(handle);
     }
     Utility.actionBuilderClick(driver, xpv.getTokenValue("userStickyNotesPostWallXPATH"));
     try {
     postAnnouncement = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("postAnnouncementXPATH"))));
     } catch (TimeoutException e) {
     Utility.illegalStateException("Selenium is unable to click on TextArea, this is an Automation Limitation");
     }
     }
     */
}
