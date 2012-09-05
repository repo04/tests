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
    private String grpName;

    // Assumes user is at 'My Social Groups'
    public void buildSocialGroup() {

        String user = LoginPage.getUser();

        switch (user.substring(0, 7)) {
            case "student":
                this.grpName = "SmkTstStdtSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                break;
            case "teacher":
                this.grpName = "SmkTstTchrSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                break;
            default:
                SeleneseTestBase.fail("Invalid user to create Social Group: " + user);
        }

        String srtName = "Shrt" + LoginPage.getUser() + "SclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);

        driver.findElement(By.xpath(av.getTokenValue("linkStrtSclGrpXPATH"))).click();

        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldGrpNameXPATH"));

        driver.findElement(By.xpath(av.getTokenValue("fieldGrpNameXPATH"))).sendKeys(grpName);
        driver.findElement(By.xpath(av.getTokenValue("fieldSrtNameXPATH"))).sendKeys(srtName);
        driver.findElement(By.xpath(av.getTokenValue("fieldAbtGrpXPATH"))).sendKeys("About");
        driver.findElement(By.xpath(av.getTokenValue("fieldTopicXPATH"))).sendKeys("Topic");

        driver.findElement(By.xpath(av.getTokenValue("btnSbmtSclGrp"))).click();

        // Verifies new Group
        ip.isElementPresentByLINK(driver, grpName);
    }
    
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

    public void leaveSocialGroup(String stdtSclGrpName) {

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

    public void deleteSocialGroup(String stdtSclGrpName) {
        ip.isElementPresentContainsTextByXPATH(driver, stdtSclGrpName);
        driver.findElement(By.xpath("//*[contains(text(),'" + stdtSclGrpName + "')]")).click();
        ip.isElementPresentByXPATH(driver, "//input[@id='sgroup_delete']");
        driver.findElement(By.xpath("//input[@id='sgroup_delete']")).click();

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

    public String getSclGrpName() {
        return this.grpName;
    }
}
