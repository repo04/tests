package smoketest;

import com.thoughtworks.selenium.SeleneseTestBase;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

public class SocialGroup extends BaseClass {

    Date now = new Date();
    private String sclGrpName;

    /**
     * Create & verify SocialGroup
     */
    public void buildSocialGroup() {

        String user = LoginPage.getUser();

        //Split username
        switch (user.substring(0, 7)) {
            case "student":
            case "autostu":
                if (test.equalsIgnoreCase("RegressionTests")) {
                    this.sclGrpName = "RgsnTstStdtSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else if (test.equalsIgnoreCase("SmokeTests")) {
                    this.sclGrpName = "SmkTstStdtSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else if (test.equalsIgnoreCase("CriticalTests")) {
                    this.sclGrpName = "CrtclTstStdtSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else {
                    this.sclGrpName = "DbgTstStdtSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                }
                break;
            case "teacher":
            case "autotea":
                if (test.equalsIgnoreCase("RegressionTests")) {
                    this.sclGrpName = "RgsnTstTchrSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else if (test.equalsIgnoreCase("SmokeTests")) {
                    this.sclGrpName = "SmkTstTchrSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else if (test.equalsIgnoreCase("CriticalTests")) {
                    this.sclGrpName = "CrtclTstTchrSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else {
                    this.sclGrpName = "DbgTstTchrSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                }
                break;
            default:
                SeleneseTestBase.fail("Invalid user to create Social Group: " + user);
        }

        String srtName = "Shrt" + LoginPage.getUser() + "SclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        driver.findElement(By.xpath(xpv.getTokenValue("linkStrtSclGrpXPATH"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGrpNameXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGrpNameXPATH"))).sendKeys(sclGrpName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldSrtNameXPATH"))).sendKeys(srtName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldAbtGrpXPATH"))).sendKeys("About");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldTopicXPATH"))).sendKeys("Topic");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmtSclGrp"))).click();

        //Verify SocialGroup creation
        ip.isElementPresentByLINK(driver, sclGrpName);
    }

    /**
     * Find Social Group
     *
     * @param sclGrp
     */
    public void findSocialGroup(String sclGrp) {
        driver.findElement(By.xpath(xpv.getTokenValue("btnFindSclGrp"))).click();

        //Verify 'Find a Social Group' Text
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("hdngPageXPATH"), xpv.getTokenValue("headerTxt"));
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGrpSrchXPATH"))).sendKeys(sclGrp);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSrchSclGrp"))).click();

        //Verify Social Group Present or not
        ip.isElementPresentByLINK(driver, sclGrp);
    }

    /**
     * Join Social Group
     *
     * @param sclGrp
     */
    public void joinSocialGroup(String sclGrp) {
        ip.isElementPresentContainsTextByXPATH(driver, "Join Now");
        driver.findElement(By.xpath("//*[contains(text(),'Join Now')]")).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("vrfyJndSclGrpXPATH"), "Are you sure you want to join the group \"" + sclGrp + "\"?");

        //XPATH didn't work
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        String btnID = buttons.get(1).getAttribute("id");
        driver.findElement(By.xpath("//button[@id='" + btnID + "']")).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("vrfyJndSclGrpXPATH"), "You have successfully joined the group \"" + sclGrp + "\".");
        driver.findElement(By.xpath(xpv.getTokenValue("btnOkJnSclGrp"))).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("vrfyTxtJoined"), "Joined");
    }

    /**
     * Leave Social Group
     *
     * @param stdtSclGrpName
     */
    public void leaveSocialGroup(String stdtSclGrpName) {
        Utility.optionalClickByLINK(driver, xpv.getTokenValue("btnShwMreRslts"), stdtSclGrpName);
        int i = 2;

        while (true) {
            try {
                ip.isTextPresentByXPATH(driver, "//li[" + i + "]/div/div[2]/div/a", stdtSclGrpName, 5);
                break;
            } catch (TimeoutException e) {
                System.out.println(stdtSclGrpName + " is not found at " + i + " position");
                i++;
            }
        }

        driver.findElement(By.xpath("//li[" + i + "]/div/div[3]/a")).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("vrfyRmvSclGrpXPATH"), "Are you sure you want to remove yourself from the group " + stdtSclGrpName + "?");

        //XPATH didn't work
        List<WebElement> buttons = driver.findElements(By.tagName("button"));

        String btnID = buttons.get(1).getAttribute("id");
        driver.findElement(By.xpath("//button[@id='" + btnID + "']")).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("vrfyRmvSclGrpXPATH"), "You have successfully left the group " + stdtSclGrpName);
        driver.findElement(By.xpath(xpv.getTokenValue("btnOkLvSclGrp"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + stdtSclGrpName + "')]")));
    }

    /**
     * Delete SocialGroup
     *
     * @param stdtSclGrpName
     */
    public void deleteSocialGroup(String stdtSclGrpName) {
        Utility.optionalClickByLINK(driver, xpv.getTokenValue("btnShwMreRslts"), stdtSclGrpName);
        ip.isElementPresentContainsTextByXPATH(driver, stdtSclGrpName);
        driver.findElement(By.xpath("//*[contains(text(),'" + stdtSclGrpName + "')]")).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnDeleteGrp"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnDeleteGrp"))).click();
        Utility.waitForAlertToBeAccepted(driver, 60, "Do you really want to delete this group?");
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + stdtSclGrpName + "')]")));
    }

    /**
     * @return SclGrpName
     */
    public String getSocialGroupName() {
        return this.sclGrpName;
    }
}
