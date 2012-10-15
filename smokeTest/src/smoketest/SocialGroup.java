package smoketest;

import com.thoughtworks.selenium.SeleneseTestBase;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
                this.sclGrpName = "SmkTstStdtSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                break;
            case "teacher":
            case "autotea":
                this.sclGrpName = "SmkTstTchrSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                break;
            default:
                SeleneseTestBase.fail("Invalid user to create Social Group: " + user);
        }

        String srtName = "Shrt" + LoginPage.getUser() + "SclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        driver.findElement(By.xpath(av.getTokenValue("linkStrtSclGrpXPATH"))).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldGrpNameXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("fieldGrpNameXPATH"))).sendKeys(sclGrpName);
        driver.findElement(By.xpath(av.getTokenValue("fieldSrtNameXPATH"))).sendKeys(srtName);
        driver.findElement(By.xpath(av.getTokenValue("fieldAbtGrpXPATH"))).sendKeys("About");
        driver.findElement(By.xpath(av.getTokenValue("fieldTopicXPATH"))).sendKeys("Topic");
        driver.findElement(By.xpath(av.getTokenValue("btnSbmtSclGrp"))).click();

        //Verify SocialGroup creation
        ip.isElementPresentByLINK(driver, sclGrpName);
    }

    /**
     * Find Social Group
     *
     * @param sclGrp
     */
    public void findSocialGroup(String sclGrp) {
        driver.findElement(By.xpath(av.getTokenValue("btnFindSclGrp"))).click();

        //Verify 'Find a Social Group' Text
        ip.isTextPresentByXPATH(driver, av.getTokenValue("hdngPageXPATH"), av.getTokenValue("headerTxt"));
        driver.findElement(By.xpath(av.getTokenValue("fieldGrpSrchXPATH"))).sendKeys(sclGrp);
        driver.findElement(By.xpath(av.getTokenValue("btnSrchSclGrp"))).click();

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
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyJndSclGrpXPATH"), "Are you sure you want to join the group \"" + sclGrp + "\"?");

        //XPATH didn't work
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        String btnID = buttons.get(1).getAttribute("id");
        driver.findElement(By.xpath("//button[@id='" + btnID + "']")).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyJndSclGrpXPATH"), "You have successfully joined the group \"" + sclGrp + "\".");
        driver.findElement(By.xpath(av.getTokenValue("btnOkJnSclGrp"))).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyTxtJoined"), "Joined");
    }

    /**
     * Leave Social Group
     *
     * @param stdtSclGrpName
     */
    public void leaveSocialGroup(String stdtSclGrpName) {
        Utility.optionalClickByLINK(driver, av.getTokenValue("btnShwMreRslts"), stdtSclGrpName);
        ip.isElementPresentContainsTextByXPATH(driver, stdtSclGrpName);
        ip.isElementPresentContainsTextByXPATH(driver, "Leave Group");
        driver.findElement(By.xpath("//*[contains(text(),'Leave Group')]")).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyRmvSclGrpXPATH"), "Are you sure you want to remove yourself from the group " + stdtSclGrpName + "?");

        //XPATH didn't work
        List<WebElement> buttons = driver.findElements(By.tagName("button"));

        String btnID = buttons.get(1).getAttribute("id");
        driver.findElement(By.xpath("//button[@id='" + btnID + "']")).click();
        ip.isTextPresentByXPATH(driver, av.getTokenValue("vrfyRmvSclGrpXPATH"), "You have successfully left the group " + stdtSclGrpName);
        driver.findElement(By.xpath(av.getTokenValue("btnOkLvSclGrp"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + stdtSclGrpName + "')]")));
    }

    /**
     * Delete SocialGroup
     *
     * @param stdtSclGrpName
     */
    public void deleteSocialGroup(String stdtSclGrpName) {
        Utility.optionalClickByLINK(driver, av.getTokenValue("btnShwMreRslts"), stdtSclGrpName);
        ip.isElementPresentContainsTextByXPATH(driver, stdtSclGrpName);
        driver.findElement(By.xpath("//*[contains(text(),'" + stdtSclGrpName + "')]")).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnDeleteGrp"));
        driver.findElement(By.xpath(av.getTokenValue("btnDeleteGrp"))).click();

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
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + stdtSclGrpName + "')]")));
    }

    /**
     * @return SclGrpName
     */
    public String getSclGrpName() {
        return this.sclGrpName;
    }
}
