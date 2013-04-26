package smoketest;

import com.thoughtworks.selenium.SeleneseTestBase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;
import runThrghTestNG.Pes_CleanTestData;

public class WorkingGroup extends BaseClass {

    Date now = new Date();
    private String workingGroupName;
    private String googleDocumentName;    
    
    /**
     * PesAdmin creates & verify Working Group
     */
    public void buildWorkingGroup() {
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.workingGroupName = "RgsnTstWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.workingGroupName = "SmkTstWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("CriticalTests")) {
            this.workingGroupName = "CrtclTstWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.workingGroupName = "DbgTstWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }

        String srtName = "ShrtNmWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        driver.findElement(By.xpath(xpv.getTokenValue("link2torAdminXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("linkAddWrkGrp"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("headerCreateWrkGrp"), xpv.getTokenValue("txtCreateWrkGrp"));
        driver.findElement(By.xpath(xpv.getTokenValue("fieldWrkgGrpName"))).sendKeys(this.workingGroupName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldWrkgGrpShrtName"))).sendKeys(srtName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldWrkgGrpAbout"))).sendKeys("this is a test");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmtWrkgGrp"))).click();

        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("headerLstWrkGrp"), xpv.getTokenValue("txtLstWrkGrp"));
        ip.isElementPresentByLINK(driver, workingGroupName);
    }

    /**
     * PesAdmin adds 'n' number of members to Working Group
     *
     * @param members
     */
    public void addMembersToWorkingGroup(String[] members) {

        membersToWorkingGroup();

        Select select = new Select(driver.findElement(By.xpath(xpv.getTokenValue("addMbrsXPATH"))));
        String fullName = null;

        for (String mbr : members) {
            sw:
            switch (mbr.substring(0, 7)) {
                case "teacher":
                case "autotea":
                    fullName = Utility.getFullName(mbr) + "(Non-editing teacher)";
                    break sw;
                case "student":
                case "autostu":
                    fullName = Utility.getFullName(mbr) + "(Student)";
                    break sw;
                default:
                    SeleneseTestBase.fail("Invalid Member 'teacher'/'student' :" + mbr.substring(0, 7));
            }

            select.selectByVisibleText(fullName);
        }
        driver.findElement(By.xpath(xpv.getTokenValue("lnkAddMbrXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("btnSaveMbrsXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblMbrsUpdtdTxtXPATH"), "The group members were updated successfully.");
    }

    /**
     * PesAdmin removes 'n' number of members from Working Group
     *
     * @param members
     */
    public void removeMembersFromWorkingGroup(String[] members) {

        membersToWorkingGroup();

        Select select = new Select(driver.findElement(By.xpath(xpv.getTokenValue("rmvMbrsXPATH"))));
        String fullName = null;

        for (String mbr : members) {
            sw:
            switch (mbr.substring(0, 7)) {
                case "teacher":
                case "autotea":
                    if (Pes_CleanTestData.teacherStatus) {
                        fullName = Utility.getFullName(mbr) + "(Non-editing teacher)";
                    } else {
                        fullName = Utility.getFullName(mbr) + "()";
                    }
                    break sw;
                case "student":
                case "autostu":
                    if (Pes_CleanTestData.studentStatus) {
                        fullName = Utility.getFullName(mbr) + "(Student)";
                    } else {
                        fullName = Utility.getFullName(mbr) + "()";
                    }
                    break sw;
                default:
                    SeleneseTestBase.fail("Invalid Member 'teacher'/'student' :" + mbr.substring(0, 7));
            }
            select.selectByVisibleText(fullName);
        }
        driver.findElement(By.xpath(xpv.getTokenValue("lnkRmvMbrXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("btnSaveMbrsXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblMbrsUpdtdTxtXPATH"), "The group members were updated successfully.");
    }

    /**
     * Creates GoogleDoc
     *
     * @param workingGroup
     */
    public void createGoogleDoc(String workingGroup) {
        ip.isElementPresentContainsTextByXPATH(driver, workingGroup);
        driver.findElement(By.xpath("//*[contains(text(),'" + workingGroup + "')]")).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lnkLftPnlFilesXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lnkLftPnlFilesXPATH"))).click();
        ip.isElementPresentContainsTextByXPATH(driver, "Start a Collaborative Document");
        driver.findElement(By.xpath("//*[contains(text(),'Start a Collaborative Document')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGglDocNameXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctGglTypeXPATH")))).selectByVisibleText("Document");
        DateFormat dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
        String gglDocDesc;

        if (test.equalsIgnoreCase("RegressionTests")) {
            this.googleDocumentName = "RgsnTstGglDoc " + dateFormat.format(now);
            gglDocDesc = "RgsnTstGglDocDesc " + dateFormat.format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.googleDocumentName = "SmkTstGglDoc " + dateFormat.format(now);
            gglDocDesc = "SmkTstGglDocDesc " + dateFormat.format(now);
        } else if (test.equalsIgnoreCase("CriticalTests")) {
            this.googleDocumentName = "CrtclTstGglDoc " + dateFormat.format(now);
            gglDocDesc = "CrtclTstGglDocDesc " + dateFormat.format(now);
        } else {
            this.googleDocumentName = "DbgTstGglDoc " + dateFormat.format(now);
            gglDocDesc = "DbgTstGglDocDesc " + dateFormat.format(now);
        }
        
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocNameXPATH"))).sendKeys(googleDocumentName);
        driver.findElement(By.xpath(xpv.getTokenValue("txtAreaGglDescXPATH"))).sendKeys(gglDocDesc);

        //Incase checkbox for Collaborators is not selected
        Boolean bool = driver.findElement(By.xpath(xpv.getTokenValue("chckbxCllbrtrsXPATH"))).isSelected();
        if (!bool) {
            driver.findElement(By.xpath(xpv.getTokenValue("chckbxCllbrtrsXPATH"))).click();
        }
        String currentURL = driver.getCurrentUrl();
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmtGglDoc"))).click();
        String HandleBefore = null;
        
        //Navigating to GMAIL -- Catched exception as to continue with Test Run incase of any failure
        //Code will be refactored next week
        try {
            ip.isTitlePresent(driver, "Google Accounts");
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGglDocUsrIdXPATH"));
            WebElement gglUsrNm = driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocUsrIdXPATH")));
            WebElement gglPswd = driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocPswdXPATH")));

            //This is to verify gglUsrID field passes correct value 
            int x = 1;
            String emailUserName = ldv.getTokenValue("emailUserName");
            Boolean result;
            value:
            do {
                gglUsrNm.clear();
                gglPswd.clear();
                gglUsrNm.sendKeys(emailUserName);
                gglPswd.sendKeys(ldv.getTokenValue("emailPassword"));
                try {
                    new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpv.getTokenValue("fieldGglDocUsrIdXPATH")), emailUserName));
                    result = false;
                    break value;
                } catch (TimeoutException e) {
                    x++;
                    result = true;
                }
            } while (x < 5);

            if (result) {
                Utility.illegalStateException("Unable to send expected userName: " + emailUserName);
            }

            driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocSignInXPATH"))).click();
            ip.isTextPresentByXPATH(driver, "//li[2]/a/span/span", emailUserName);
        } catch (Exception e) {
            System.out.println("1st exptn");
            driver.get(currentURL);
            throw e;
        }
        try {
            ip.isTitlePresent(driver, "My Account");
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGglDocGrntAccessXPATH"));
        } catch (Exception e) {
            System.out.println("2nd exptn");
            Utility.clickByJavaScript(driver, "//td/a");
            ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGglDocUsrIdXPATH"));
            driver.get(currentURL);
            throw e;
        }
        try {
            HandleBefore = driver.getWindowHandle();
            driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocGrntAccessXPATH"))).click();
            Utility.waitForNumberOfWindowsToEqual(driver, 60, 2);
            int i = 1;
            for (String handle : driver.getWindowHandles()) {
                System.out.println("window handle: " + handle);
                driver.switchTo().window(handle);
                if (i == driver.getWindowHandles().size()) {
                    System.out.println("inside google window");
                    ip.isTitleContains(driver, googleDocumentName);
                    ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyGglDocXPATH"), googleDocumentName);
                    Utility.clickByJavaScript(driver, xpv.getTokenValue("btnGglDocSgnOutXPATH"));
                    ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGglDocUsrIdXPATH"));
                    driver.close();
                    driver.switchTo().window(HandleBefore);
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println("3rd exptn");
            driver.close();
            driver.switchTo().window(HandleBefore);
            throw e;
        }
        ip.isElementPresentContainsTextByXPATH(driver, googleDocumentName);
    }

    /**
     * Delete Working Group
     *
     * @param wrkngGrp
     */
    public void deleteWorkingGroup(String wrkngGrp) {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnDeleteGrp"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnDeleteGrp"))).click();

        //Get a handle to the open alert, prompt or confirmation
        final Alert alert = driver.switchTo().alert();

        //Verify alert popup
        (new WebDriverWait(driver, 60)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return alert.getText().contentEquals("Do you really want to delete this group?");
            }
        });

        //And acknowledge the alert (equivalent to clicking "OK")
        alert.accept();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblTxtVrfyDelGrp"), "The group was deleted successfully.");
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + wrkngGrp + "')]")));
    }

    /**
     * Navigate to Working Group Add/Remove Screen
     */
    private void membersToWorkingGroup() {

        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnEditWrkngGrpXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnEditWrkngGrpXPATH"))).click();
        ip.isElementPresentContainsTextByXPATH(driver, "Manage Members");
        driver.findElement(By.xpath("//*[contains(text(),'Manage Members')]")).click();

        //GU servers can take up to 6 minutes for this to load.  Known issue.
        int wait = 600;
        ip.isTextPresentByXPATH(driver, "//td[2]/h3", "Members", wait);
        ip.isTextPresentByXPATH(driver, "//td[4]/h3", "Non Members", wait);

    }

    /**
     * @return WorkingGroupName
     */
    public String getWorkingGroupName() {
        return this.workingGroupName;
    }

    /**
     * @return GoogleDocName
     */
    public String getGoogleDocName() {
        return this.googleDocumentName;
    }
}