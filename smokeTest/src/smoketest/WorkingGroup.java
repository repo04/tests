package smoketest;

import com.thoughtworks.selenium.SeleneseTestBase;
import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class WorkingGroup extends Page {

    Date now = new Date();
    IsPresent ip = new IsPresent();
    private String wrkgGrpName;

    public WorkingGroup(WebDriver driver, AccountValues av) {
        super(driver, av);
    }

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
            switch (mbr.substring(6, 10)) {
                case "tchr":
                    fullNm = mbr + "fstNm " + mbr + "sndNm(Non-editing teacher)";
                    break sw;
                case "stdt":
                    fullNm = mbr + "fstNm " + mbr + "sndNm(Student)";
                    break sw;
                default:
                    SeleneseTestBase.fail("Invalid Member 'tchr'/'stdt' :" + mbr.substring(6, 10));
            }
            select.selectByVisibleText(fullNm);
        }
        driver.findElement(By.xpath(av.getTokenValue("lnkAddMbrXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("btnSaveMbrsXPATH"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("lblMbrsUpdtdTxtXPATH"), "The members updated for the group.");
    }

    public String getWrkngGrp() {
        return this.wrkgGrpName;
    }
}