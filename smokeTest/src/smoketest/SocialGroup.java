package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SocialGroup extends Page {

    Date now = new Date();
    IsPresent ip = new IsPresent();
    private String grpName;

    /**
     * @param driver
     * @param av
     */
    public SocialGroup(WebDriver driver, AccountValues av) {
        super(driver, av);
    }

    // Assumes user is at 'My Social Groups'
    public void buildSocialGroup() {

        this.grpName = "SmkTstSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String srtName = "ShrtSclGrp " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);

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

    public void joinSocialGroup() {
    }

    public String getSclGrpName() {
        return this.grpName;
    }
}