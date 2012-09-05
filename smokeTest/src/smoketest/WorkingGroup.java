package smoketest;

import com.thoughtworks.selenium.SeleneseTestBase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import runThrghTestNG.BaseClass;

public class WorkingGroup extends BaseClass {

    Date now = new Date();
    private String wrkgGrpName;
    private String gglDocName;

    public void BuildWorkingGroup() {

        this.wrkgGrpName = "SmkTstWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String srtName = "ShrtNmWrkngGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);

        driver.findElement(By.xpath(av.getTokenValue("link2torAdminXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("linkAddWrkGrp"))).click();

        ip.isTextPresentByXPATH(driver, av.getTokenValue("headerCreateWrkGrp"), av.getTokenValue("txtCreateWrkGrp"));

        driver.findElement(By.xpath(av.getTokenValue("fieldWrkgGrpName"))).sendKeys(this.wrkgGrpName);
        driver.findElement(By.xpath(av.getTokenValue("fieldWrkgGrpShrtName"))).sendKeys(srtName);
        driver.findElement(By.xpath(av.getTokenValue("fieldWrkgGrpAbout"))).sendKeys("this is a test");

        driver.findElement(By.xpath(av.getTokenValue("btnSbmtWrkgGrp"))).click();

        ip.isTextPresentByXPATH(driver, av.getTokenValue("headerLstWrkGrp"), av.getTokenValue("txtLstWrkGrp"));
        ip.isElementPresentByLINK(driver, wrkgGrpName);
    }

    public void addMbrsToWrkngGrp(String[] members) {

        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnEditWrkngGrpXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("btnEditWrkngGrpXPATH"))).click();
        ip.isElementPresentContainsTextByXPATH(driver, "Manage Members");
        driver.findElement(By.xpath("//*[contains(text(),'Manage Members')]")).click();

        ip.isTextPresentByXPATH(driver, "//td[2]/h3", "Members");
        ip.isTextPresentByXPATH(driver, "//td[4]/h3", "Non Members");

        Select select = new Select(driver.findElement(By.xpath(av.getTokenValue("slctMbrsXPATH"))));
        String fullNm = null;
        
        for (String mbr : members) {
            sw:
                switch (mbr.substring(0, 7))  {
                    case "teacher":
                        fullNm = mbr + "fstNm " + mbr + "sndNm(Non-editing teacher)";
                        break sw;
                    case "student":
                        fullNm = mbr + "fstNm " + mbr + "sndNm(Student)";
                        break sw;
                    default:
                        SeleneseTestBase.fail("Invalid Member 'tchr'/'stdt' :" + mbr.substring(0, 7));
                }
            
            select.selectByVisibleText(fullNm);
        }
        driver.findElement(By.xpath(av.getTokenValue("lnkAddMbrXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("btnSaveMbrsXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("lblMbrsUpdtdTxtXPATH"), "The members updated for the group.");
    }

    public void createGoogleDoc(String wrkngGrp) {
        ip.isElementPresentContainsTextByXPATH(driver, wrkngGrp);
        driver.findElement(By.xpath("//*[contains(text(),'" + wrkngGrp + "')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("lnkLftPnlFilesXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("lnkLftPnlFilesXPATH"))).click();
        ip.isElementPresentContainsTextByXPATH(driver, "Start a Collaborative Document");
        driver.findElement(By.xpath("//*[contains(text(),'Start a Collaborative Document')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldGglDocNameXPATH"));
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctGglTypeXPATH")))).selectByVisibleText("Document");
        DateFormat dateFormat = new SimpleDateFormat("ddMMMyyHHmm");

        this.gglDocName = "SmkTstGglDoc " + dateFormat.format(now);
        String gglDocDesc = "SmkTstGglDocDesc " + dateFormat.format(now);
        driver.findElement(By.xpath(av.getTokenValue("fieldGglDocNameXPATH"))).sendKeys(gglDocName);
        driver.findElement(By.xpath(av.getTokenValue("txtAreaGglDescXPATH"))).sendKeys(gglDocDesc);
        Boolean bool = driver.findElement(By.xpath(av.getTokenValue("chckbxCllbrtrsXPATH"))).isSelected();

        if (!bool) {
            driver.findElement(By.xpath(av.getTokenValue("chckbxCllbrtrsXPATH"))).click();
        }
        driver.findElement(By.xpath(av.getTokenValue("btnSbmtGglDoc"))).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldGglDocUsrIdXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("fieldGglDocUsrIdXPATH"))).sendKeys("tutordemo2");
        driver.findElement(By.xpath(av.getTokenValue("fieldGglDocPswdXPATH"))).sendKeys("Newuser@123");
        driver.findElement(By.xpath(av.getTokenValue("fieldGglDocSignInXPATH"))).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldGglDocGrntAccessXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("fieldGglDocGrntAccessXPATH"))).click();

        ip.isElementPresentContainsTextByXPATH(driver, gglDocName);

    }

    public String getWrkngGrp() {
        return this.wrkgGrpName;
    }

    public String getGoogleDocName() {
        return this.gglDocName;
    }
}