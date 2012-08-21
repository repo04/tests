package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SocialGroup extends Page {

    DateFormat dateFormat;
    Date now;
    IsPresent ip = new IsPresent();

    /**
     *
     * @param driver
     * @param av
     */
    public SocialGroup(WebDriver driver, AccountValues av) {
        super(driver, av);
    }

    public void createSocialGroup(String sclGrpName){
    	
    	//Navigate to SocialGroup Page
        Utility.navigateToSubMenu(driver, av.getTokenValue("linkToSclGrpXPATH"));

        //Verify My Social Group Page
        ip.isElementPresentByID(driver, av.getTokenValue("fndSclGrpID"));

        driver.findElement(By.linkText(av.getTokenValue("strtSclGrpBtnLINK"))).click();

        //Verify Navigated to Social Group creation page
        ip.isElementPresentByID(driver, av.getTokenValue("sclGrpNameID"));

        //Create New Social Group
        driver.findElement(By.id(av.getTokenValue("sclGrpNameID"))).sendKeys(av.getTokenValue(sclGrpName));
        
        dateFormat = new SimpleDateFormat("ddMMMyy KKmma");
		now = new Date();
        String sclGrpShrtName = av.getTokenValue(sclGrpName) + dateFormat.format(now);
        driver.findElement(By.id(av.getTokenValue("sclGrpShrtNameID"))).sendKeys(sclGrpShrtName);
        driver.findElement(By.id(av.getTokenValue("sclGrpAbtGrpID"))).sendKeys("About the group");
        driver.findElement(By.id(av.getTokenValue("sclGrpWhoShudJoinID"))).sendKeys("other student should join");
        driver.findElement(By.id(av.getTokenValue("sclGrpTpc1ID"))).sendKeys("Global Warming");
        driver.findElement(By.id(av.getTokenValue("sclGrpTpc2ID"))).sendKeys("Social N/W sites");

        driver.findElement(By.id(av.getTokenValue("sclGrpSbmtID"))).click();

        ip.isElementPresentByLink(driver, av.getTokenValue(sclGrpName));

    }
}
