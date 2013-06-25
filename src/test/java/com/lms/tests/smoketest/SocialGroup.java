package com.lms.tests.smoketest;

import com.thoughtworks.selenium.SeleneseTestBase;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lms.tests.runThrghTestNG.BaseClass;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SocialGroup extends BaseClass {

    private RemoteWebDriver driver;

    public SocialGroup(RemoteWebDriver driver) {
        this.driver = driver;
    }
    Date now = new Date();
    private String socialGroupName;

    /**
     * Create & verify SocialGroup
     */
    public void buildSocialGroup() {

        String user = LoginPage.getUser();
        String groupMemberName = null;

        switch (getProgram()) {
            case "gu-msn":
                groupMemberName = "Hoyas";
                break;
            case "usc-msw":
            case "usc-mat":
                groupMemberName = "Trojans";
                break;
            case "unc-mba":
            case "unc-mpa":
                groupMemberName = "Tar Heels";
                break;
            case "wu-llm":
                groupMemberName = "Bears";
                break;
            //This will be corrected once LMS-2809 is resolved
            case "au-mir":
            case "gwu-mph":
            case "corp-son":
                groupMemberName = "student";
        }

        driver.findElement(By.xpath(xpv.getTokenValue("linkStrtSclGrpXPATH"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGrpNameXPATH"));
        ip.isTextPresentByXPATH(driver, "//b/div/font", "Topics are keywords or labels that make a group easy to find. "
                + "Enter up to 7 short and simple topics to describe your group. "
                + "For example, a group called \"New York City\" might have topics like Statue of Liberty, "
                + "Hot Dogs, Yankees, or Empire State Building. "
                + "Be creative in describing your group so fellow " + groupMemberName + " want to join!");

        //Split username
        switch (user.substring(0, 7)) {
            case "student":
            case "autostu":
                if (test.equalsIgnoreCase("RegressionTests")) {
                    this.socialGroupName = "RgsnTstStdtSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else if (test.equalsIgnoreCase("SmokeTests")) {
                    this.socialGroupName = "SmkTstStdtSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else if (test.equalsIgnoreCase("CriticalTests")) {
                    this.socialGroupName = "CrtclTstStdtSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else {
                    this.socialGroupName = "DbgTstStdtSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                }
                break;
            case "teacher":
            case "autotea":
                if (test.equalsIgnoreCase("RegressionTests")) {
                    this.socialGroupName = "RgsnTstTchrSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else if (test.equalsIgnoreCase("SmokeTests")) {
                    this.socialGroupName = "SmkTstTchrSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else if (test.equalsIgnoreCase("CriticalTests")) {
                    this.socialGroupName = "CrtclTstTchrSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else {
                    this.socialGroupName = "DbgTstTchrSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                }
                break;
            default:
                SeleneseTestBase.fail("Invalid user to create Social Group: " + user);
        }

        String srtName = "Shrt" + LoginPage.getUser() + "SclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGrpNameXPATH"))).sendKeys(this.socialGroupName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldSrtNameXPATH"))).sendKeys(srtName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldAbtGrpXPATH"))).sendKeys("About");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldTopicXPATH"))).sendKeys("Topic");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmtSclGrp"))).click();

        //Verify SocialGroup creation
        ip.isElementPresentByLINK(driver,
                this.socialGroupName);
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
     * @param studentSocialGroupName
     */
    public void leaveSocialGroup(String studentSocialGroupName) {
        Utility.optionalClickByLINK(driver, xpv.getTokenValue("btnShwMreRslts"), studentSocialGroupName);
        int i = 2;

        while (true) {
            try {
                ip.isTextPresentByXPATH(driver, "//li[" + i + "]/div/div[2]/div/a", studentSocialGroupName, 5);
                break;
            } catch (TimeoutException e) {
                System.out.println(studentSocialGroupName + " is not found at " + i + " position");
                i++;
            }
        }

        driver.findElement(By.xpath("//li[" + i + "]/div/div[3]/a")).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("vrfyRmvSclGrpXPATH"), "Are you sure you want to remove yourself from the group " + studentSocialGroupName + "?");

        //XPATH didn't work
        List<WebElement> buttons = driver.findElements(By.tagName("button"));

        String btnID = buttons.get(1).getAttribute("id");
        driver.findElement(By.xpath("//button[@id='" + btnID + "']")).click();
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("vrfyRmvSclGrpXPATH"), "You have successfully left the group " + studentSocialGroupName);
        driver.findElement(By.xpath(xpv.getTokenValue("btnOkLvSclGrp"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + studentSocialGroupName + "')]")));
    }

    /**
     * Delete SocialGroup
     *
     * @param studentSocialGroupName
     */
    public void deleteSocialGroup(String studentSocialGroupName) {
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnDeleteGrp"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnDeleteGrp"))).click();
        Utility.waitForAlertToBeAccepted(driver, 60, "Do you really want to delete this group?");
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + studentSocialGroupName + "')]")));
    }

    /**
     * Verify post exits on Social Group wall
     *
     * @param studentUrlPostOnTeacherSocialGroup
     */
    public void verifyPostOnSocialGroupWall(String studentUrlPostOnTeacherSocialGroup) {
        ip.isElementPresentContainsTextByXPATH(driver, studentUrlPostOnTeacherSocialGroup);
    }

    /**
     * @return SclGrpName
     */
    public String getSocialGroupName() {
        return this.socialGroupName;
    }
}
