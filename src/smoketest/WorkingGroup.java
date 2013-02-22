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
    private String wrkgGrpName;
    private String gglDocName;

    /**
     * PesAdmin creates & verify Working Group
     */
    public void buildWorkingGroup() {
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.wrkgGrpName = "RgsnTstWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.wrkgGrpName = "SmkTstWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("CriticalTests")) {
            this.wrkgGrpName = "CrtclTstWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.wrkgGrpName = "DbgTstWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }

        String srtName = "ShrtNmWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        driver.findElement(By.xpath(xpv.getTokenValue("link2torAdminXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("linkAddWrkGrp"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("headerCreateWrkGrp"), xpv.getTokenValue("txtCreateWrkGrp"));
        driver.findElement(By.xpath(xpv.getTokenValue("fieldWrkgGrpName"))).sendKeys(this.wrkgGrpName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldWrkgGrpShrtName"))).sendKeys(srtName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldWrkgGrpAbout"))).sendKeys("this is a test");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmtWrkgGrp"))).click();

        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("headerLstWrkGrp"), xpv.getTokenValue("txtLstWrkGrp"));
        ip.isElementPresentByLINK(driver, wrkgGrpName);
    }

    /**
     * PesAdmin adds 'n' number of members to Working Group
     *
     * @param members
     */
    public void addMembersToWorkingGroup(String[] members) {

        mbrsToWrkngGrp();

        Select select = new Select(driver.findElement(By.xpath(xpv.getTokenValue("addMbrsXPATH"))));
        String fullNm = null;

        for (String mbr : members) {
            sw:
            switch (mbr.substring(0, 7)) {
                case "teacher":
                    fullNm = mbr + " " + mbr + "(Non-editing teacher)";
                    break sw;
                case "autotea":
                    fullNm = "auto teacher1(Non-editing teacher)";
                    break sw;
                case "student":
                    fullNm = mbr + " " + mbr + "(Student)";
                    break sw;
                case "autostu":
                    fullNm = "auto student1(Student)";
                    break sw;
                default:
                    SeleneseTestBase.fail("Invalid Member 'tchr'/'stdt' :" + mbr.substring(0, 7));
            }

            select.selectByVisibleText(fullNm);
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
    public void rmvMbrsFrmWrkngGrp(String[] members) {

        mbrsToWrkngGrp();

        Select select = new Select(driver.findElement(By.xpath(xpv.getTokenValue("rmvMbrsXPATH"))));
        String fullNm = null;

        for (String mbr : members) {
            sw:
            switch (mbr.substring(0, 7)) {
                case "teacher":
                    if (Pes_CleanTestData.tchrStatus) {
                        fullNm = mbr + " " + mbr + "(Non-editing teacher)";
                    } else {
                        fullNm = mbr + " " + mbr + "()";
                    }
                    break sw;
                case "autotea":
                    if (Pes_CleanTestData.tchrStatus) {
                        fullNm = "auto teacher1(Non-editing teacher)";
                    } else {
                        fullNm = "auto teacher1()";
                    }
                    break sw;
                case "student":
                    if (Pes_CleanTestData.stdtStatus) {
                        fullNm = mbr + " " + mbr + "(Student)";
                    } else {
                        fullNm = mbr + " " + mbr + "()";
                    }
                    break sw;
                case "autostu":
                    if (Pes_CleanTestData.stdtStatus) {
                        fullNm = "auto student1(Student)";
                    } else {
                        fullNm = "auto student1()";
                    }
                    break sw;
                default:
                    SeleneseTestBase.fail("Invalid Member 'tchr'/'stdt' :" + mbr.substring(0, 7));
            }
            select.selectByVisibleText(fullNm);
        }
        driver.findElement(By.xpath(xpv.getTokenValue("lnkRmvMbrXPATH"))).click();
        driver.findElement(By.xpath(xpv.getTokenValue("btnSaveMbrsXPATH"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("lblMbrsUpdtdTxtXPATH"), "The group members were updated successfully.");
    }

    /**
     * Creates GoogleDoc
     *
     * @param wrkngGrp
     */
    public void createGoogleDoc(String wrkngGrp) {
        ip.isElementPresentContainsTextByXPATH(driver, wrkngGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + wrkngGrp + "')]")).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lnkLftPnlFilesXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lnkLftPnlFilesXPATH"))).click();
        ip.isElementPresentContainsTextByXPATH(driver, "Start a Collaborative Document");
        driver.findElement(By.xpath("//*[contains(text(),'Start a Collaborative Document')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGglDocNameXPATH"));
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctGglTypeXPATH")))).selectByVisibleText("Document");
        DateFormat dateFormat = new SimpleDateFormat("ddMMMyyHHmm");
        String gglDocDesc;

        if (test.equalsIgnoreCase("SmokeTests")) {
            this.gglDocName = "SmkTstGglDoc " + dateFormat.format(now);
            gglDocDesc = "SmkTstGglDocDesc " + dateFormat.format(now);
        } else if (test.equalsIgnoreCase("CriticalTests")) {
            this.gglDocName = "CrtclTstGglDoc " + dateFormat.format(now);
            gglDocDesc = "CrtclTstGglDocDesc " + dateFormat.format(now);
        } else {
            this.gglDocName = "DbgTstGglDoc " + dateFormat.format(now);
            gglDocDesc = "DbgTstGglDocDesc " + dateFormat.format(now);
        }

        driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocNameXPATH"))).sendKeys(gglDocName);
        driver.findElement(By.xpath(xpv.getTokenValue("txtAreaGglDescXPATH"))).sendKeys(gglDocDesc);

        //Incase checkbox for Collaborators is not selected
        Boolean bool = driver.findElement(By.xpath(xpv.getTokenValue("chckbxCllbrtrsXPATH"))).isSelected();
        if (!bool) {
            driver.findElement(By.xpath(xpv.getTokenValue("chckbxCllbrtrsXPATH"))).click();
        }

        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmtGglDoc"))).click();
        ip.isTitlePresent(driver, "Google Accounts");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGglDocUsrIdXPATH"));
        WebElement gglUsrNm = driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocUsrIdXPATH")));
        WebElement gglPswd = driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocPswdXPATH")));

        //This is to verify gglUsrID field passes correct value 
        value:
        while (true) {
            gglUsrNm.clear();
            gglPswd.clear();
            gglUsrNm.sendKeys("tutordemo2");
            gglPswd.sendKeys("Newuser@123");
            try {
                new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpv.getTokenValue("fieldGglDocUsrIdXPATH")), "tutordemo2"));
                break value;
            } catch (TimeoutException e) {
            }
        }
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocSignInXPATH"))).click();
        ip.isTitlePresent(driver, "My Account");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGglDocGrntAccessXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocGrntAccessXPATH"))).click();

        String HandleBefore = driver.getWindowHandle();
        int i = 1;
        for (String handle : driver.getWindowHandles()) {
            System.out.println("window handle: " + handle);
            driver.switchTo().window(handle);
            if (i == driver.getWindowHandles().size()) {
                try {
                    System.out.println("inside google window");
                    ip.isTitleContains(driver, gglDocName);
                    ip.isTextPresentByXPATH(driver, xpv.getTokenValue("txtVrfyGglDocXPATH"), gglDocName);
                    Utility.navigateToSubMenu(driver, xpv.getTokenValue("btnGglDocSgnOutXPATH"));
                    ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGglDocUsrIdXPATH"));
                    driver.close();
                } catch (Exception e) {
                    System.out.println("GglDoc exptn");
                    driver.close();
                    driver.switchTo().window(HandleBefore);
                    throw e;
                }
            }
            i++;
        }
        driver.switchTo().window(HandleBefore);
        ip.isElementPresentContainsTextByXPATH(driver, gglDocName);
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
    private void mbrsToWrkngGrp() {

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
     * @return WrkngGrpName
     */
    public String getWrkngGrp() {
        return this.wrkgGrpName;
    }

    /**
     * @return GoogleDocName
     */
    public String getGoogleDocName() {
        return this.gglDocName;
    }
}