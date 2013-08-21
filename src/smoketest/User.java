/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

public class User extends BaseClass {

    DateFormat dateFormat;
    Date now;
    private String userName;
    private String title;
    private String gender;
    private String emailDisplay;
    String defaultTimeZoneExpectedValue = xpv.getTokenValue(program + "TimeZoneDefaultValue");

    /**
     * PesAdmin creates user
     *
     * @param user
     */
    public void createUser(String user) {

        dateFormat = new SimpleDateFormat("ddMM-HHmm");
        now = new Date();

        if (test.equalsIgnoreCase("regressionTests")) {
            switch (user) {
                case "student":
                case "autostu":
                    this.emailDisplay = "Hide my email address from everyone";
                    break;
                case "coordinator":
                    this.emailDisplay = "Allow only other course members to see my email address";
                    break;
                default:
                    this.emailDisplay = "Allow everyone to see my email address";
            }
        } else {
            this.emailDisplay = "Allow only other course members to see my email address";
        }

        if (!test.equalsIgnoreCase("CriticalDataTests")) {
            switch (user) {
                case "student":
                case "autostu":
                    this.userName = "student" + dateFormat.format(now);
                    this.title = "Pupil";
                    this.gender = "1";
                    break;
                case "coordinator":
                    this.userName = "coordinator" + dateFormat.format(now);
                    this.title = "Organizer";
                    this.gender = "0";
                    break;
                default:
                    this.userName = "teacher" + dateFormat.format(now);
                    this.title = "Lecturer";
                    this.gender = "2";
            }
        } else {
            switch (user) {
                case "student":
                case "autostu":
                    this.userName = "autostudent1";
                    this.title = "Pupil";
                    this.gender = "1";
                    break;
                default:
                    this.userName = "autoteacher1";
                    this.title = "Lecturer";
                    this.gender = "2";
            }
        }
        driver.findElement(By.xpath(xpv.getTokenValue("fieldUsrnmXPATH"))).sendKeys(userName);
        if (user.contains("student") && test.equalsIgnoreCase("regressionTests")) {
            driver.findElement(By.xpath(xpv.getTokenValue("fieldPswdXPATH"))).sendKeys("Moodle2!");
        } else {
            driver.findElement(By.xpath(xpv.getTokenValue("fieldPswdXPATH"))).sendKeys(ldv.getTokenValue("password"));
        }
        driver.findElement(By.xpath(xpv.getTokenValue("fieldFirstNmXPATH"))).sendKeys(userName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldScndNmXPATH"))).sendKeys(userName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldTitleXPATH"))).sendKeys(title);
        if (!test.equalsIgnoreCase("CriticalDataTests")) {
            if (user.equalsIgnoreCase("teacher")) {
                driver.findElement(By.xpath(xpv.getTokenValue("fieldEmailXPATH"))).sendKeys("seleniumtest+" + "teacher" + dateFormat.format(now) + "@2u.com");
            } else {
                driver.findElement(By.xpath(xpv.getTokenValue("fieldEmailXPATH"))).sendKeys("seleniumtest+" + "student" + dateFormat.format(now) + "@2u.com");
            }
        } else {
            if (user.equalsIgnoreCase("teacher")) {
                driver.findElement(By.xpath(xpv.getTokenValue("fieldEmailXPATH"))).sendKeys("seleniumtest+" + "autoteacher1" + "@2u.com");
            } else {
                driver.findElement(By.xpath(xpv.getTokenValue("fieldEmailXPATH"))).sendKeys("seleniumtest+" + "autostudent1" + "@2u.com");
            }
        }
        if (test.equalsIgnoreCase("regressionTests") && user.equalsIgnoreCase("student")) {
            boolean forcePasswordChangeCheckboxStatus = driver.findElement(By.xpath(xpv.getTokenValue("forcePasswordChangeCheckboxXPATH"))).isSelected();
            if (forcePasswordChangeCheckboxStatus == false) {
                driver.findElement(By.xpath(xpv.getTokenValue("forcePasswordChangeCheckboxXPATH"))).click();
            }
        }
        driver.findElement(By.xpath(xpv.getTokenValue("fieldCityXPATH"))).sendKeys("New York");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCntryXPATH")))).selectByValue("US");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctTimeZnXPATH")))).selectByValue("America/New_York");
        new Select(driver.findElement(By.xpath("//*[@id='id_maildisplay']"))).selectByVisibleText(emailDisplay);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGenderXPATH"))).sendKeys(gender);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();

        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctFindUsrXPATH"));

        Utility.buttonRemoveUserFilter(driver, xpv.getTokenValue("btnRmvUsrFilter"));

        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldFindUsrXPATH"))).sendKeys(userName);
        driver.findElement(By.xpath(xpv.getTokenValue("btnFindUsr"))).click();

        ip.isElementPresentStartsWithTextByXPATH(driver, userName);
    }

    /**
     * Delete Users
     *
     * @param user
     */
    public void deleteUsers(String... users) {

        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlSiteAdminXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlSiteAdminXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlUsersXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsersXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlAccntsXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlAccntsXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlBrwsNwUsrXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlBrwsNwUsrXPATH"))).click();

        for (String user : users) {
            ip.isElementClickableByXpath(driver, xpv.getTokenValue("fieldFindUsrXPATH"), 60);

            Utility.buttonRemoveUserFilter(driver, xpv.getTokenValue("btnRmvUsrFilter"));
            new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
            driver.findElement(By.xpath(xpv.getTokenValue("fieldFindUsrXPATH"))).sendKeys(user);
            driver.findElement(By.xpath(xpv.getTokenValue("btnFindUsr"))).click();
            ip.isElementPresentStartsWithTextByXPATH(driver, user);
            driver.findElement(By.xpath(xpv.getTokenValue("lnkDltUsrXPATH"))).click();
            String userFullNm = user + " " + user;
            ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyCmpltlyDltUsrXPATH"), "Are you absolutely sure you want to completely delete '" + userFullNm + "' ?");
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnCntnDltUsrXPATH"));
            driver.findElement(By.xpath(xpv.getTokenValue("btnCntnDltUsrXPATH"))).click();
            new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + userFullNm + "')]")));
        }
    }

    /**
     * Verify the Default TimeZone Value in Create User Page
     */
    public void verifyDefaultTimeZoneValueWhileUserCreation() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctTimeZnXPATH"));
        String defaultTimeZoneActualValue = new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctTimeZnXPATH")))).getFirstSelectedOption().getText();
        if (!defaultTimeZoneActualValue.equalsIgnoreCase(defaultTimeZoneExpectedValue)) {
            Utility.illegalStateException("Default Time Zone Field Value varies; Expected: '" + defaultTimeZoneExpectedValue
                    + "', Actual: '" + defaultTimeZoneActualValue + "'");
        }
    }

    /**
     * Verify the presence of difference sections in Create User Page UI
     */
    public void verifySectionsOfUserCreationPage() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH"));
        ip.isTextPresentByXPATH(driver, "//legend", "General");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[2]");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[2]/legend", "Interests");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[3]");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[3]/legend", "Optional");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[4]");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[4]/legend", "Basic Contact Info");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[5]");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[5]/legend", "Personal Information");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[6]");
        switch (program) {
            case "gu-msn":
            case "usc-msw":
            case "usc-mat":
            case "corp-son":
            case "sc-msn":
                ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[6]/legend", "Educational Information");
                break;
            default:
                ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[6]/legend", "Work Information");
        }
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[7]");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[7]/legend", "ACP Connect Info");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[8]");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[8]/legend", "Kaltura Information");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[9]");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sectionCommonXPATH") + "[9]/legend", "Miscellenous");
    }

    /**
     * Verify the default state of unmask password field field in Create User
     * Page UI is unchecked
     */
    public void verifyDefaultUnmaskPasswordCheckBoxState() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("unmaskPasswordCheckboxXPATH"));
        Boolean unmaskPasswordCheckbox = driver.findElement(By.xpath(xpv.getTokenValue("unmaskPasswordCheckboxXPATH"))).isSelected();
        if (unmaskPasswordCheckbox == true) {
            Utility.illegalStateException("Unmask Password Checkbox Default State varies, "
                    + "expected: 'Not Checked' but actual: Checked'");
        }
    }

    /**
     * Verify the password encryption is off and on when unmask password check
     * box is checked and unchecked
     */
    public void verifyUnmaskPasswordCheckBoxFunctionality() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("unmaskPasswordCheckboxXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("fieldPswdXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("fieldPswdXPATH"))).sendKeys("Password1");
        String fieldPasswordEncryptionState = driver.findElement(By.xpath(xpv.getTokenValue("fieldPswdXPATH"))).getAttribute("type");
        System.out.println(fieldPasswordEncryptionState);
        if (!fieldPasswordEncryptionState.equalsIgnoreCase("password")) {
            Utility.illegalStateException("Password Encryption Status varies, "
                    + "expected: 'Encrypted' but actual: Not Encrypted'");
        }
        driver.findElement(By.xpath(xpv.getTokenValue("unmaskPasswordCheckboxXPATH"))).click();
        fieldPasswordEncryptionState = driver.findElement(By.xpath(xpv.getTokenValue("fieldPswdXPATH"))).getAttribute("type");
        System.out.println(fieldPasswordEncryptionState);
        if (fieldPasswordEncryptionState.equalsIgnoreCase("password")) {
            Utility.illegalStateException("Password Encryption Status varies, "
                    + "expected: 'Not Encrypted' but actual: Encrypted'");
        }
    }

    /**
     * Edit "List Of Interests" filed of User
     *
     * @param user
     */
    public void verifyListOfInterestsFieldUpdation(String user) {
        Utility.buttonRemoveUserFilter(driver, xpv.getTokenValue("btnRmvUsrFilter"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldFindUsrXPATH"))).sendKeys(user);
        driver.findElement(By.xpath(xpv.getTokenValue("btnFindUsr"))).click();
        ip.isElementPresentStartsWithTextByXPATH(driver, user);
        driver.findElement(By.xpath(xpv.getTokenValue("lnkEditUsrXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("listOfInterestsFldXPATH"))).clear();
        driver.findElement(By.xpath(xpv.getTokenValue("listOfInterestsFldXPATH"))).sendKeys("ListOfInterests");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctFindUsrXPATH"));
        Utility.buttonRemoveUserFilter(driver, xpv.getTokenValue("btnRmvUsrFilter"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldFindUsrXPATH"))).sendKeys(user);
        driver.findElement(By.xpath(xpv.getTokenValue("btnFindUsr"))).click();
        ip.isElementPresentStartsWithTextByXPATH(driver, user);
        driver.findElement(By.xpath(xpv.getTokenValue("lnkEditUsrXPATH"))).click();
        String listOfInterestsFldText = driver.findElement(By.xpath(xpv.getTokenValue("listOfInterestsFldXPATH"))).getAttribute("value");
        if (!listOfInterestsFldText.contains("ListOfInterests")) {
            Utility.illegalStateException("List of Interests have not been edited successfully for User, "
                    + "expected: 'Edited' but actual: Not Edited'");
        }
    }

    /**
     * Verify Browse List of Users Page
     *
     * @param user
     */
    public void verifyBrowseListOfUsersPage(String user) {
        ip.isElementPresentContainsTextByXPATH(driver, "Page");
        Utility.buttonRemoveUserFilter(driver, xpv.getTokenValue("btnRmvUsrFilter"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctFindUsrXPATH")))).selectByValue("0");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldFindUsrXPATH"))).sendKeys(user);
        driver.findElement(By.xpath(xpv.getTokenValue("btnFindUsr"))).click();
        ip.isElementPresentStartsWithTextByXPATH(driver, user);
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("firstNameSurNameColumnNameXPATH"), "First name / Surname");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("emailAddressColumnNameXPATH"), "Email address");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("cityTownColumnNameXPATH"), "City/town");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("countryColumnNameXPATH"), "Country");
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lastAccessColumnNameXPATH"), "Last access");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("lnkDltUsrXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("lnkEditUsrXPATH"));
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("lnkResetAdobePasswordXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("lnkUsrNameXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("usrFirstNameXPATH"), user, 60);
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("usrSurNameXPATH"), user, 60);
    }

    /**
     * Verify the different values in "Authentication Method" dropdown field in
     * Create User Page
     */
    public void verifyChooseAnAuthenticationMethodValuesInCreateUserPage() {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("chooseAuthenticationMethodDropDownFldXPATH"));
        String defaultAuthenticationMethodSelected = new Select(driver.findElement(By.xpath(xpv.getTokenValue("chooseAuthenticationMethodDropDownFldXPATH")))).getFirstSelectedOption().getText();
        if (!defaultAuthenticationMethodSelected.equalsIgnoreCase("Manual accounts")) {
            Utility.illegalStateException("Default value in  Choose An Authentication Method differs, "
                    + "expected: 'Manual accounts' but actual: '" + defaultAuthenticationMethodSelected + "'");
        }
        String[] dropdownValues = {"CAS server (SSO)", "External database",
            "Email-based self-registration", "FirstClass server",
            "IMAP server", "LDAP server", "Manual accounts",
            "MNet authentication", "NNTP server", "No login",
            "No authentication", "PAM (Pluggable Authentication Modules)",
            "POP3 server", "RADIUS server", "Shibboleth", "Web services authentication"};
        Select authenticationMethodDropdownValues = new Select(driver.findElement(By.xpath(xpv.getTokenValue("chooseAuthenticationMethodDropDownFldXPATH"))));
        if (dropdownValues.length != authenticationMethodDropdownValues.getOptions().size()) {
            Utility.illegalStateException("Expected Authentication Sixe: " + dropdownValues.length
                    + " ; Actual: " + authenticationMethodDropdownValues.getOptions().size());
        }
        int j = 1;
        for (WebElement i : authenticationMethodDropdownValues.getOptions()) {
            String value = i.getText();
            if (!value.contentEquals(dropdownValues[j - 1])) {
                Utility.illegalStateException("Expected Authentication Method: " + dropdownValues[j - 1]
                        + "; Actual: " + value);
            }
            j++;
        }
    }

    /**
     * Change Password from Force Password Change Page on Student First Time
     * Login
     */
    public void forceChangePasswordOnFirstLogin() {
        ip.isTextPresentByXPATH(driver, "//legend", "General");
        driver.findElement(By.xpath(xpv.getTokenValue("oldPasswordFldXPATH"))).sendKeys("Moodle2!");
        driver.findElement(By.xpath(xpv.getTokenValue("newPasswordFldXPATH"))).sendKeys(ldv.getTokenValue("password"));
        driver.findElement(By.xpath(xpv.getTokenValue("newPasswordAgainFldXPATH"))).sendKeys(ldv.getTokenValue("password"));
        driver.findElement(By.xpath(xpv.getTokenValue("saveSettingsButtonXPATH"))).click();
        Utility.waitForAlertToBeAccepted(driver, 60, "Your password has been successfully changed");
        Utility.verifyCurrentUrl(driver, xpv.getTokenValue("homePageURL"));
    }

    /**
     * @return UsrName
     */
    public String getUserName() {
        return this.userName;
    }
}
